//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
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

static EMmState GetMmStateFromSubState(EMmSubState subState)
{
    switch (subState)
    {
    case EMmSubState::MM_NULL_PS:
        return EMmState::MM_NULL;
    case EMmSubState::MM_DEREGISTERED_PS:
    case EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE:
    case EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE:
    case EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION:
    case EMmSubState::MM_DEREGISTERED_PLMN_SEARCH:
    case EMmSubState::MM_DEREGISTERED_NO_SUPI:
    case EMmSubState::MM_DEREGISTERED_NO_CELL_AVAILABLE:
    case EMmSubState::MM_DEREGISTERED_ECALL_INACTIVE:
    case EMmSubState::MM_DEREGISTERED_INITIAL_REGISTRATION_NEEDED:
        return EMmState::MM_DEREGISTERED;
    case EMmSubState::MM_REGISTERED_INITIATED_PS:
        return EMmState::MM_REGISTERED_INITIATED;
    case EMmSubState::MM_REGISTERED_PS:
    case EMmSubState::MM_REGISTERED_NORMAL_SERVICE:
    case EMmSubState::MM_REGISTERED_NON_ALLOWED_SERVICE:
    case EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE:
    case EMmSubState::MM_REGISTERED_LIMITED_SERVICE:
    case EMmSubState::MM_REGISTERED_PLMN_SEARCH:
    case EMmSubState::MM_REGISTERED_NO_CELL_AVAILABLE:
    case EMmSubState::MM_REGISTERED_UPDATE_NEEDED:
        return EMmState::MM_REGISTERED;
    case EMmSubState::MM_DEREGISTERED_INITIATED_PS:
        return EMmState::MM_DEREGISTERED_INITIATED;
    case EMmSubState::MM_SERVICE_REQUEST_INITIATED_PS:
        return EMmState::MM_SERVICE_REQUEST_INITIATED;
    }

    std::terminate();
}

NasMm::NasMm(TaskBase *base, NasTimers *timers) : m_base{base}, m_timers{timers}, m_sm{}, m_usim{}, m_procCtl{}
{
    m_logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "nas");

    m_rmState = ERmState::RM_DEREGISTERED;
    m_cmState = ECmState::CM_IDLE;
    m_mmState = EMmState::MM_DEREGISTERED;
    m_mmSubState = EMmSubState::MM_DEREGISTERED_PS;

    m_storage = new MmStorage(m_base);
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
    m_base->nasTask->push(std::make_unique<NmUeNasToNas>(NmUeNasToNas::PERFORM_MM_CYCLE));
}

void NasMm::performMmCycle()
{
    /* Do nothing in case of MM-NULL */
    if (m_mmState == EMmState::MM_NULL)
        return;

    auto currentCell = m_base->shCtx.currentCell.get();
    Tai currentTai = Tai{currentCell.plmn, currentCell.tac};

    /* Perform substate selection in case of primary substate */
    if (m_mmSubState == EMmSubState::MM_DEREGISTERED_PS)
    {
        if (currentCell.hasValue())
        {
            if (!m_usim->isValid())
                switchMmState(EMmSubState::MM_DEREGISTERED_NO_SUPI);
            else if (isInNonAllowedArea())
            {
                // TODO: check this later
                switchMmState(EMmSubState::MM_REGISTERED_NON_ALLOWED_SERVICE);
            }
            else if (currentCell.category == ECellCategory::SUITABLE_CELL)
                switchMmState(EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE);
            else if (currentCell.category == ECellCategory::ACCEPTABLE_CELL)
                switchMmState(EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE);
            else
                switchMmState(EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
        }
        else
        {
            switchMmState(EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
        }
        return;
    }

    if (m_mmSubState == EMmSubState::MM_REGISTERED_PS)
    {
        auto cell = m_base->shCtx.currentCell.get();
        if (cell.hasValue())
        {
            if (isInNonAllowedArea())
            {
                // TODO: check this later
                switchMmState(EMmSubState::MM_REGISTERED_NON_ALLOWED_SERVICE);
            }
            else if (cell.category == ECellCategory::SUITABLE_CELL)
                switchMmState(EMmSubState::MM_REGISTERED_NORMAL_SERVICE);
            else if (cell.category == ECellCategory::ACCEPTABLE_CELL)
                switchMmState(EMmSubState::MM_REGISTERED_LIMITED_SERVICE);
            else
                switchMmState(EMmSubState::MM_REGISTERED_PLMN_SEARCH);
        }
        else
        {
            switchMmState(EMmSubState::MM_REGISTERED_PLMN_SEARCH);
        }
        return;
    }

    /* PLMN selection related */
    if (m_mmSubState == EMmSubState::MM_REGISTERED_PLMN_SEARCH ||
        m_mmSubState == EMmSubState::MM_REGISTERED_NO_CELL_AVAILABLE ||
        m_mmSubState == EMmSubState::MM_DEREGISTERED_PLMN_SEARCH ||
        m_mmSubState == EMmSubState::MM_DEREGISTERED_NO_CELL_AVAILABLE)
    {
        performPlmnSelection();
    }

    /* eCall inactivity related */
    if (m_mmSubState != EMmSubState::MM_REGISTERED_PLMN_SEARCH &&
        m_mmSubState != EMmSubState::MM_REGISTERED_NO_CELL_AVAILABLE &&
        m_mmSubState != EMmSubState::MM_DEREGISTERED_PLMN_SEARCH &&
        m_mmSubState != EMmSubState::MM_DEREGISTERED_NO_CELL_AVAILABLE)
    {
        if (switchToECallInactivityIfNeeded())
            return;
    }
    if (m_mmState == EMmState::MM_REGISTERED)
    {
        if (startECallInactivityIfNeeded())
            return;
    }

    /* Try to start procedures */
    invokeProcedures();

    /* Registration related */
    if (m_mmSubState == EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE && !m_timers->t3346.isRunning())
        initialRegistrationRequired(EInitialRegCause::MM_DEREG_NORMAL_SERVICE);
    else if (m_mmSubState == EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE && hasEmergency())
        initialRegistrationRequired(EInitialRegCause::EMERGENCY_SERVICES);
    else if (hasEmergency())
    {
        if (m_mmSubState == EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE ||
            m_mmSubState == EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION ||
            m_mmSubState == EMmSubState::MM_DEREGISTERED_NO_SUPI ||
            m_mmSubState == EMmSubState::MM_REGISTERED_NON_ALLOWED_SERVICE ||
            m_mmSubState == EMmSubState::MM_REGISTERED_LIMITED_SERVICE)
            initialRegistrationRequired(EInitialRegCause::EMERGENCY_SERVICES);
    }

    if (m_mmSubState == EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE && hasEmergency())
    {
        // 5.2.3.2.3; f)
        // "may perform de-registration locally and initiate a registration procedure for initial registration for
        // emergency services even if timer T3346 is running"

        // UE will try to send initial emergency registration after local de-registration
        performLocalDeregistration();
    }

    if (m_mmSubState == EMmSubState::MM_DEREGISTERED_ECALL_INACTIVE && hasEmergency())
    {
        // "If the UE receives a request from upper layers to establish an eCall over IMS, the UE enters state
        // 5GMM-DEREGISTERED.ATTEMPTING-REGISTRATION. The UE then uses the relevant 5GMM and 5GSM procedures to
        // establish the eCall over IMS at the earliest opportunity"
        switchMmState(EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);
    }

    /* Process TAI changes if any */
    if (currentTai.hasValue() &&
        !nas::utils::TaiListContains(m_storage->taiList->get(), nas::VTrackingAreaIdentity{currentTai}))
    {
        auto hasAnyTai = !m_storage->taiList->get().list.empty();
        if (hasAnyTai && m_rmState == ERmState::RM_REGISTERED)
            mobilityUpdatingRequired(ERegUpdateCause::ENTER_UNLISTED_TRACKING_AREA);
    }
    else
        m_storage->lastVisitedRegisteredTai->set(currentTai);
}

void NasMm::switchMmState(EMmSubState subState)
{
    EMmState state = GetMmStateFromSubState(subState);

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

    m_lastTimeMmStateChange = utils::CurrentTimeMillis();

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

    auto statusUpdate = std::make_unique<NmUeStatusUpdate>(NmUeStatusUpdate::CM_STATE);
    statusUpdate->cmState = m_cmState;
    m_base->appTask->push(std::move(statusUpdate));

    if (m_base->nodeListener)
    {
        m_base->nodeListener->onSwitch(app::NodeType::UE, m_base->config->getNodeName(), app::StateType::CM,
                                       ToJson(oldState).str(), ToJson(m_cmState).str());
    }

    triggerMmCycle();
}

void NasMm::switchUState(E5UState state)
{
    E5UState oldState = m_storage->uState->get();
    m_storage->uState->set(state);

    onSwitchUState(oldState, state);

    if (m_base->nodeListener)
    {
        m_base->nodeListener->onSwitch(app::NodeType::UE, m_base->config->getNodeName(), app::StateType::U5,
                                       ToJson(oldState).str(), ToJson(state).str());
    }

    if (state != oldState)
        m_logger->info("UE switches to state [%s]", ToJson(state).str().c_str());

    triggerMmCycle();
}

void NasMm::onSwitchMmState(EMmState oldState, EMmState newState, EMmSubState oldSubState, EMmSubState newSubSate)
{
    // "The UE shall mark the 5G NAS security context on the USIM or in the non-volatile memory as invalid when the UE
    // initiates an initial registration procedure as described in subclause 5.5.1.2 or when the UE leaves state
    // 5GMM-DEREGISTERED for any other state except 5GMM-NULL."
    if (oldState == EMmState::MM_DEREGISTERED && newState != EMmState::MM_DEREGISTERED && newState != EMmState::MM_NULL)
    {
        if (m_usim->m_currentNsCtx || m_usim->m_nonCurrentNsCtx)
        {
            m_logger->debug("Deleting NAS security context");

            m_usim->m_currentNsCtx = {};
            m_usim->m_nonCurrentNsCtx = {};
        }
    }

    // "If the UE enters the 5GMM state 5GMM-DEREGISTERED or 5GMM-NULL,
    // The RAND and RES* values stored in the ME shall be deleted and timer T3516, if running, shall be stopped"
    if (newState == EMmState::MM_DEREGISTERED || newState == EMmState::MM_NULL)
    {
        m_usim->m_rand = {};
        m_usim->m_resStar = {};
        m_timers->t3516.stop();
    }

    // If NAS layer starts PLMN SEARCH in CM-CONNECTED, we switch to CM-IDLE. Because PLMN search is an idle
    // operation and RRC expects it in RRC-IDLE state. (This may happen in for example initial registration reject with
    // switch to PLMN search state)
    if (m_cmState == ECmState::CM_CONNECTED && (m_mmSubState == EMmSubState::MM_DEREGISTERED_PLMN_SEARCH ||
                                                m_mmSubState == EMmSubState::MM_REGISTERED_PLMN_SEARCH))
    {
        localReleaseConnection(false);
    }

    // "Timer T3512 is stopped when the UE enters ... the 5GMM-DEREGISTERED state over 3GPP access"
    if (newState == EMmState::MM_DEREGISTERED)
        m_timers->t3512.stop();
}

void NasMm::onSwitchRmState(ERmState oldState, ERmState newState)
{
    if (oldState == ERmState::RM_REGISTERED && newState == ERmState::RM_REGISTERED)
    {
        // "The UE shall delete (List of equivalent PLMNs) ...  when the UE registered for emergency services
        // enters the state 5GMM-DEREGISTERED"
        if (m_registeredForEmergency)
            m_storage->equivalentPlmnList->clear();
    }
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
                switchMmState(EMmSubState::MM_DEREGISTERED_PS);
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
            // "The de-registration procedure shall be aborted and the UE proceeds as follows:
            // if the de-registration procedure was performed due to disabling of 5GS services, the UE shall enter the
            // 5GMM-NULL state;"
            if (m_lastDeregCause == EDeregCause::DISABLE_5G)
                switchMmState(EMmSubState::MM_NULL_PS);
            // "if the de-registration type "normal de-registration" was requested for reasons other than disabling of
            // 5GS services, the UE shall enter the 5GMM-DEREGISTERED state."
            else if (m_lastDeregistrationRequest->deRegistrationType.switchOff ==
                     nas::ESwitchOff::NORMAL_DE_REGISTRATION)
                switchMmState(EMmSubState::MM_DEREGISTERED_PS);
        }

        // "If the UE enters the 5GMM-IDLE, the RAND and RES* values stored in the ME shall be deleted and timer T3516,
        // if running, shall be stopped"
        m_usim->m_rand = {};
        m_usim->m_resStar = {};
        m_timers->t3516.stop();

        // "Timer T3512 is reset and started with its initial value, when the UE changes from 5GMM-CONNECTED over 3GPP
        // access to 5GMM-IDLE mode over 3GPP access"
        m_timers->t3512.start();
    }

    if (oldState == ECmState::CM_IDLE && newState == ECmState::CM_CONNECTED)
    {
        // "Timer T3512 is stopped when the UE enters 5GMM-CONNECTED mode over 3GPP access or the 5GMM-DEREGISTERED
        // state over 3GPP access"
        m_timers->t3512.stop();
    }
}

void NasMm::onSwitchUState(E5UState oldState, E5UState newState)
{
}

void NasMm::onSwitchOn()
{
    resetRegAttemptCounter();
}

void NasMm::onSimInsertion()
{
    resetRegAttemptCounter();

    switchMmState(EMmSubState::MM_DEREGISTERED_PS);
}

void NasMm::onSwitchOff()
{
    m_storage->serviceAreaList->clear();

    m_storage->forbiddenTaiListRoaming->clear();
    m_storage->forbiddenTaiListRps->clear();
}

void NasMm::onSimRemoval()
{
    m_storage->equivalentPlmnList->clear();

    m_storage->serviceAreaList->clear();

    m_storage->forbiddenTaiListRoaming->clear();
    m_storage->forbiddenTaiListRps->clear();

    switchMmState(EMmSubState::MM_DEREGISTERED_PS);
}

} // namespace nr::ue
