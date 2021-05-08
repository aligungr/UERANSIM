//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <lib/nas/utils.hpp>
#include <ue/app/task.hpp>
#include <ue/nas/task.hpp>
#include <ue/nas/usim/usim.hpp>
#include <ue/rrc/task.hpp>
#include <utils/common.hpp>

namespace nr::ue
{

NasMm::NasMm(TaskBase *base, UeTimers *timers) : m_base{base}, m_timers{timers}, m_sm{nullptr}
{
    m_logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "nas");

    m_rmState = ERmState::RM_DEREGISTERED;
    m_cmState = ECmState::CM_IDLE;
    m_mmState = EMmState::MM_DEREGISTERED;
    m_mmSubState = EMmSubState::MM_DEREGISTERED_NA;
}

void NasMm::onStart(NasSm *sm, Usim *usim)
{
    m_sm = sm;
    m_usim = usim;
    triggerMmCycle();
}

void NasMm::onQuit()
{
    // TODO
}

void NasMm::triggerMmCycle()
{
    m_base->nasTask->push(new NwUeNasToNas(NwUeNasToNas::PERFORM_MM_CYCLE));
}

void NasMm::performMmCycle()
{
    if (m_mmState == EMmState::MM_NULL)
        return;

    if (!m_usim->isValid())
    {
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NO_SUPI);
        return;
    }

    if (m_mmSubState == EMmSubState::MM_DEREGISTERED_NA)
    {
        if (switchToECallInactivityIfNeeded())
            return;

        if (m_usim->isValid())
        {
            if (m_cmState == ECmState::CM_IDLE)
                switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
            else
            {
                if (m_usim->m_servingCell.has_value())
                {
                    auto cellCategory = m_usim->m_servingCell->cellCategory;

                    if (cellCategory == ECellCategory::SUITABLE_CELL)
                        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE);
                    else if (cellCategory == ECellCategory::ACCEPTABLE_CELL)
                        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE);
                    else
                        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
                }
                else
                {
                    switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
                }
            }
        }
        else
        {
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NO_SUPI);
        }
    }

    if (m_mmSubState == EMmSubState::MM_DEREGISTERED_PLMN_SEARCH ||
        m_mmSubState == EMmSubState::MM_DEREGISTERED_NO_CELL_AVAILABLE ||
        m_mmSubState == EMmSubState::MM_REGISTERED_PLMN_SEARCH ||
        m_mmSubState == EMmSubState::MM_REGISTERED_NO_CELL_AVAILABLE)
    {
        int64_t current = utils::CurrentTimeMillis();
        int64_t backoff = (m_mmSubState == EMmSubState::MM_DEREGISTERED_PLMN_SEARCH ||
                           m_mmSubState == EMmSubState::MM_REGISTERED_PLMN_SEARCH)
                              ? -1
                              : 1500;

        if (current - m_lastPlmnSearchTrigger > backoff)
        {
            m_base->rrcTask->push(new NwUeNasToRrc(NwUeNasToRrc::PLMN_SEARCH_REQUEST));
            m_lastPlmnSearchTrigger = current;
        }
        return;
    }

    if (m_mmSubState == EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE)
    {
        if (!m_timers->t3346.isRunning())
            sendInitialRegistration(EInitialRegCause::MM_DEREG_NORMAL_SERVICE);
        return;
    }

    if (m_mmState == EMmState::MM_REGISTERED)
    {
        if (startECallInactivityIfNeeded())
            return;
    }

    if (m_mmSubState == EMmSubState::MM_REGISTERED_NA)
    {
        if (m_usim->m_servingCell.has_value())
        {
            auto cellCategory = m_usim->m_servingCell->cellCategory;

            if (cellCategory == ECellCategory::SUITABLE_CELL)
                switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_NORMAL_SERVICE);
            else if (cellCategory == ECellCategory::ACCEPTABLE_CELL)
                switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_LIMITED_SERVICE);
            else
                switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_PLMN_SEARCH);
        }
        else
        {
            switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_PLMN_SEARCH);
        }
    }
}

void NasMm::switchMmState(EMmState state, EMmSubState subState)
{
    ERmState oldRmState = m_rmState;
    if (state == EMmState::MM_DEREGISTERED || state == EMmState::MM_REGISTERED_INITIATED)
        m_rmState = ERmState::RM_DEREGISTERED;
    else if (state == EMmState::MM_REGISTERED || state == EMmState::MM_SERVICE_REQUEST_INITIATED ||
             state == EMmState::MM_DEREGISTERED_INITIATED)
        m_rmState = ERmState::RM_REGISTERED;

    onSwitchRmState(oldRmState, m_rmState);

    if (m_base->nodeListener)
    {
        m_base->nodeListener->onSwitch(app::NodeType::UE, m_base->config->getNodeName(), app::StateType::RM,
                                       ToJson(oldRmState).str(), ToJson(m_rmState).str());
    }

    EMmState oldState = m_mmState;
    EMmSubState oldSubState = m_mmSubState;

    m_mmState = state;
    m_mmSubState = subState;

    onSwitchMmState(oldState, m_mmState, oldSubState, m_mmSubState);

    if (m_base->nodeListener)
    {
        m_base->nodeListener->onSwitch(app::NodeType::UE, m_base->config->getNodeName(), app::StateType::MM,
                                       ToJson(oldSubState).str(), ToJson(subState).str());
        m_base->nodeListener->onSwitch(app::NodeType::UE, m_base->config->getNodeName(), app::StateType::MM_SUB,
                                       ToJson(oldState).str(), ToJson(state).str());
    }

    if (state != oldState || subState != oldSubState)
        m_logger->info("UE switches to state [%s]", ToJson(subState).str().c_str());

    triggerMmCycle();
}

void NasMm::switchCmState(ECmState state)
{
    ECmState oldState = m_cmState;
    m_cmState = state;

    if (state != oldState)
        m_logger->info("UE switches to state [%s]", ToJson(state).str().c_str());

    onSwitchCmState(oldState, m_cmState);

    auto *statusUpdate = new NwUeStatusUpdate(NwUeStatusUpdate::CM_STATE);
    statusUpdate->cmState = m_cmState;
    m_base->appTask->push(statusUpdate);

    if (m_base->nodeListener)
    {
        m_base->nodeListener->onSwitch(app::NodeType::UE, m_base->config->getNodeName(), app::StateType::CM,
                                       ToJson(oldState).str(), ToJson(m_cmState).str());
    }

    triggerMmCycle();
}

void NasMm::switchUState(E5UState state)
{
    E5UState oldState = m_usim->m_uState;
    m_usim->m_uState = state;

    onSwitchUState(oldState, m_usim->m_uState);

    if (m_base->nodeListener)
    {
        m_base->nodeListener->onSwitch(app::NodeType::UE, m_base->config->getNodeName(), app::StateType::U5,
                                       ToJson(oldState).str(), ToJson(m_usim->m_uState).str());
    }

    if (state != oldState)
        m_logger->info("UE switches to state [%s]", ToJson(state).str().c_str());

    triggerMmCycle();
}

void NasMm::onSwitchMmState(EMmState oldState, EMmState newState, EMmSubState oldSubState, EMmSubState newSubSate)
{
    // The UE shall mark the 5G NAS security context on the USIM or in the non-volatile memory as invalid when the UE
    // initiates an initial registration procedure as described in subclause 5.5.1.2 or when the UE leaves state
    // 5GMM-DEREGISTERED for any other state except 5GMM-NULL.
    if (oldState == EMmState::MM_DEREGISTERED && newState != EMmState::MM_DEREGISTERED && newState != EMmState::MM_NULL)
    {
        if (m_usim->m_currentNsCtx || m_usim->m_nonCurrentNsCtx)
        {
            m_logger->debug("Deleting NAS security context");

            m_usim->m_currentNsCtx = {};
            m_usim->m_nonCurrentNsCtx = {};
        }
    }

    // If the UE enters the 5GMM state 5GMM-DEREGISTERED or 5GMM-NULL,
    // The RAND and RES* values stored in the ME shall be deleted and timer T3516, if running, shall be stopped
    if (newState == EMmState::MM_DEREGISTERED || newState == EMmState::MM_NULL)
    {
        m_usim->m_rand = {};
        m_usim->m_resStar = {};
        m_timers->t3516.stop();
    }
}

void NasMm::onSwitchRmState(ERmState oldState, ERmState newState)
{
}

void NasMm::onSwitchCmState(ECmState oldState, ECmState newState)
{
    if (oldState == ECmState::CM_CONNECTED && newState == ECmState::CM_IDLE)
    {
        // 5.5.1.2.7 Abnormal cases in the UE (in registration)
        if (m_mmState == EMmState::MM_REGISTERED_INITIATED)
        {
            // "Lower layer failure or release of the NAS signalling connection received from lower layers before the
            // REGISTRATION ACCEPT or REGISTRATION REJECT message is received. The UE shall abort the registration
            // procedure for initial registration and proceed as ..."

            auto regType = m_lastRegistrationRequest->registrationType.registrationType;

            if (regType == nas::ERegistrationType::INITIAL_REGISTRATION ||
                regType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
            {
                switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
                switchUState(E5UState::U2_NOT_UPDATED);

                handleAbnormalInitialRegFailure(regType);
            }
            else
            {
                handleAbnormalMobilityRegFailure(regType);
            }
        }
        // 5.5.2.2.6 Abnormal cases in the UE (in de-registration)
        else if (m_mmState == EMmState::MM_DEREGISTERED_INITIATED)
        {
            // The de-registration procedure shall be aborted and the UE proceeds as follows:
            // if the de-registration procedure was performed due to disabling of 5GS services, the UE shall enter the
            // 5GMM-NULL state;
            if (m_lastDeregCause == EDeregCause::DISABLE_5G)
                switchMmState(EMmState::MM_NULL, EMmSubState::MM_NULL_NA);
            // if the de-registration type "normal de-registration" was requested for reasons other than disabling of
            // 5GS services, the UE shall enter the 5GMM-DEREGISTERED state.
            else if (m_lastDeregistrationRequest->deRegistrationType.switchOff ==
                     nas::ESwitchOff::NORMAL_DE_REGISTRATION)
                switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
        }

        // If the UE enters the 5GMM-IDLE, the RAND and RES* values stored
        //  in the ME shall be deleted and timer T3516, if running, shall be stopped
        m_usim->m_rand = {};
        m_usim->m_resStar = {};
        m_timers->t3516.stop();
    }
}

void NasMm::onSwitchUState(E5UState oldState, E5UState newState)
{
}

} // namespace nr::ue
