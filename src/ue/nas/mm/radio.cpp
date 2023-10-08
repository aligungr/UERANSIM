//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "mm.hpp"

#include <algorithm>

#include <lib/nas/utils.hpp>
#include <ue/app/task.hpp>
#include <ue/nas/sm/sm.hpp>
#include <ue/rrc/task.hpp>
#include <utils/common.hpp>

namespace nr::ue
{

void NasMm::performPlmnSelection()
{
    int64_t currentTime = utils::CurrentTimeMillis();

    // After some timeout in PLMN_SEARCH states, NO_CELL_AVAILABLE state is selected
    if (currentTime - m_lastTimeMmStateChange >= 5'000LL)
    {
        if (m_mmSubState == EMmSubState::MM_REGISTERED_PLMN_SEARCH)
        {
            switchMmState(EMmSubState::MM_REGISTERED_NO_CELL_AVAILABLE);
            return;
        }
        else if (m_mmSubState == EMmSubState::MM_DEREGISTERED_PLMN_SEARCH)
        {
            switchMmState(EMmSubState::MM_DEREGISTERED_NO_CELL_AVAILABLE);
            return;
        }
    }

    // If the state is PLMN_SEARCH instead of NO_CELL_AVAILABLE, then we log the errors more intensely.
    int64_t loggingThreshold = m_mmSubState == EMmSubState::MM_DEREGISTERED_PLMN_SEARCH ||
                                       m_mmSubState == EMmSubState::MM_REGISTERED_PLMN_SEARCH
                                   ? 2'000LL
                                   : 30'000LL;

    bool logFailures = currentTime - m_lastTimePlmnSearchFailureLogged >= loggingThreshold;

    // If the device just switched on, then no error logging for PLMN selection failures
    if (m_lastTimePlmnSearchFailureLogged == 0)
    {
        m_lastTimePlmnSearchFailureLogged = currentTime;
        logFailures = false;
    }

    Plmn lastSelectedPlmn = m_base->shCtx.selectedPlmn.get();

    std::unordered_set<Plmn> plmns = m_base->shCtx.availablePlmns.get();

    if (!m_usim->isValid() || plmns.empty())
    {
        if (logFailures)
        {
            if (!m_usim->isValid())
                m_logger->warn("No PLMN can be selected, USIM is invalid");
            else
                m_logger->err("PLMN selection failure, no cells in coverage");
            m_lastTimePlmnSearchFailureLogged = currentTime;
        }

        m_base->shCtx.selectedPlmn.set({});
        return;
    }

    // Determine candidate PLMNs from the list. Candidates are in priority order
    std::vector<Plmn> candidates;

    // Highest priority is for HPLMN, so just look for HPLMN first.
    for (auto &plmn : plmns)
        if (plmn == m_base->config->hplmn)
            candidates.push_back(plmn);

    // Then again look for the all PLMNS
    for (auto &plmn : plmns)
    {
        if (plmn == m_base->config->hplmn)
            continue; // If it's the HPLMN, it's already added above
        if (m_storage->forbiddenPlmnList->contains(plmn))
            continue;
        if (nas::utils::ServiceAreaListForbidsPlmn(m_storage->serviceAreaList->get(), nas::utils::PlmnFrom(plmn)))
            continue;
        if (m_storage->equivalentPlmnList->contains(plmn))
            candidates.push_back(plmn);
    }

    Plmn selected = candidates.empty() ? Plmn{} : candidates[0];

    if (!selected.hasValue())
    {
        if (logFailures)
        {
            m_logger->err("No PLMN could be selected among [%d] PLMNs", static_cast<int>(plmns.size()));
            m_lastTimePlmnSearchFailureLogged = currentTime;
        }
    }
    else if (lastSelectedPlmn != selected)
    {
        m_logger->info("Selected plmn[%s]", ToJson(selected).str().c_str());
        m_base->rrcTask->push(std::make_unique<NmUeNasToRrc>(NmUeNasToRrc::RRC_NOTIFY));

        resetRegAttemptCounter();
    }

    m_base->shCtx.selectedPlmn.set(selected);
}

void NasMm::handleActiveCellChange(const Tai &prevTai)
{
    if (m_cmState == ECmState::CM_CONNECTED)
    {
        // TODO
        m_logger->err("Serving cell change in CM-CONNECTED");
        return;
    }

    auto currentCell = m_base->shCtx.currentCell.get();
    Tai currentTai = Tai{currentCell.plmn, currentCell.tac};

    if (currentCell.hasValue() && !m_storage->equivalentPlmnList->contains(currentCell.plmn))
        m_timers->t3346.stop();

    if (currentCell.hasValue() && prevTai != currentTai)
    {
        // "Additionally, the registration attempt counter shall be reset when the UE is in substate
        // 5GMM-DEREGISTERED.ATTEMPTING-REGISTRATION or 5GMM-REGISTERED.ATTEMPTING-REGISTRATION-UPDATE, and a new
        // tracking area is entered"
        if (m_mmSubState == EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION ||
            m_mmSubState == EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE)
        {
            resetRegAttemptCounter();
        }

        // "Shall initiate a registration procedure for mobility and periodic registration update when the tracking area
        // of the serving cell has changed, if timer T3346 is not running, the PLMN identity of the new cell is not in
        // one of the forbidden PLMN lists and the tracking area is not in one of the lists of 5GS forbidden tracking
        // area"
        if (m_mmSubState == EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE && !m_timers->t3346.isRunning() &&
            currentCell.category == ECellCategory::SUITABLE_CELL)
        {
            mobilityUpdatingRequired(ERegUpdateCause::TAI_CHANGE_IN_ATT_UPD);
        }

        // "shall initiate an initial registration procedure when the tracking area of the serving cell has changed, if
        // timer T3346 is not running, the PLMN identity of the new cell is not in one of the forbidden PLMN lists and
        // the tracking area of the new cell is not in one of the lists of 5GS forbidden tracking areas"
        if (m_mmSubState == EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION && !m_timers->t3346.isRunning() &&
            currentCell.category == ECellCategory::SUITABLE_CELL)
        {
            initialRegistrationRequired(EInitialRegCause::TAI_CHANGE_IN_ATT_REG);
        }
    }

    if (currentCell.hasValue() && prevTai.plmn != currentTai.plmn)
    {
        // "Shall initiate a registration procedure for mobility and periodic registration update when entering a new
        // PLMN, if timer T3346 is running and the new PLMN is not equivalent to the PLMN where the UE started timer
        // T3346, the PLMN identity of the new cell is not in the forbidden PLMN lists, and the tracking area is not in
        // one of the lists of 5GS forbidden tracking areas"
        if (m_mmSubState == EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE && m_timers->t3346.isRunning() &&
            !m_storage->equivalentPlmnList->contains(currentTai.plmn) &&
            currentCell.category == ECellCategory::SUITABLE_CELL)
        {
            mobilityUpdatingRequired(ERegUpdateCause::PLMN_CHANGE_IN_ATT_UPD);
        }

        // "shall initiate an initial registration procedure when entering a new PLMN, if timer T3346 is running and the
        // new PLMN is not equivalent to the PLMN where the UE started timer T3346, the PLMN identity of the new cell is
        // not in the forbidden PLMN lists and the tracking area is not in one of the lists of 5GS forbidden tracking
        // areas"
        if (m_mmSubState == EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION && m_timers->t3346.isRunning() &&
            !m_storage->equivalentPlmnList->contains(currentTai.plmn) &&
            currentCell.category == ECellCategory::SUITABLE_CELL)
        {
            initialRegistrationRequired(EInitialRegCause::PLMN_CHANGE_IN_ATT_REG);
        }
    }

    if (m_mmState == EMmState::MM_REGISTERED)
    {
        switchMmState(EMmSubState::MM_REGISTERED_PS);
    }
    else if (m_mmState == EMmState::MM_DEREGISTERED)
    {
        switchMmState(EMmSubState::MM_DEREGISTERED_PS);
    }
    else if (m_mmState == EMmState::MM_REGISTERED_INITIATED || m_mmState == EMmState::MM_DEREGISTERED_INITIATED ||
             m_mmState == EMmState::MM_SERVICE_REQUEST_INITIATED)
    {
        // This should never happen
        m_logger->err("Active cell change in [CM-IDLE] state while MM specific procedure is ongoing");
        switchMmState(EMmSubState::MM_DEREGISTERED_PS);
    }
}

void NasMm::handleRrcConnectionSetup()
{
    // TODO
    switchCmState(ECmState::CM_CONNECTED);
}

void NasMm::handleRrcConnectionRelease()
{
    // TODO
    switchCmState(ECmState::CM_IDLE);
}

void NasMm::handleRrcEstablishmentFailure()
{
    m_logger->err("RRC Establishment failure");

    /* Handle NAS count */
    {
        // "After each new or retransmitted outbound SECURITY PROTECTED 5GS NAS MESSAGE message, the sender shall
        // increase the NAS COUNT number by one, except for the initial NAS messages if the lower layers indicated the
        // failure to establish the RRC connection"
        bool hasNsCtx = m_usim->m_currentNsCtx &&
                        (m_usim->m_currentNsCtx->integrity != nas::ETypeOfIntegrityProtectionAlgorithm::IA0 ||
                         m_usim->m_currentNsCtx->ciphering != nas::ETypeOfCipheringAlgorithm::EA0);
        if (hasNsCtx && m_usim->m_currentNsCtx->uplinkCount.sqn != 0 &&
            m_usim->m_currentNsCtx->uplinkCount.overflow.operator int() != 0)
        {
            m_usim->m_currentNsCtx->rollbackCountOnEncrypt();
        }
    }

    /* Handle MM state changes */
    {
        if (m_mmState == EMmState::MM_REGISTERED_INITIATED)
        {
            switchMmState(m_rmState == ERmState::RM_REGISTERED
                              ? EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE
                              : EMmSubState::MM_DEREGISTERED_INITIAL_REGISTRATION_NEEDED);
        }
        else if (m_mmState == EMmState::MM_SERVICE_REQUEST_INITIATED)
        {
            switchMmState(EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE);
        }
        else if (m_mmState == EMmState::MM_DEREGISTERED_INITIATED)
        {
        }
    }
}

void NasMm::handleRadioLinkFailure()
{
    // TODO

    if (m_cmState == ECmState::CM_CONNECTED)
    {
        m_logger->err("Radio link failure detected");
    }

    handleRrcConnectionRelease();

    if (m_mmState == EMmState::MM_REGISTERED)
        switchMmState(EMmSubState::MM_REGISTERED_PS);
    else if (m_mmState == EMmState::MM_DEREGISTERED)
        switchMmState(EMmSubState::MM_DEREGISTERED_PS);
}

void NasMm::localReleaseConnection(bool treatBarred)
{
    if (m_cmState != ECmState::CM_IDLE)
        m_logger->info("Performing local release of NAS connection");

    auto w = std::make_unique<NmUeNasToRrc>(NmUeNasToRrc::LOCAL_RELEASE_CONNECTION);
    w->treatBarred = treatBarred;
    m_base->rrcTask->push(std::move(w));
}

void NasMm::handlePaging(const std::vector<GutiMobileIdentity> &tmsiIds)
{
    if (m_mmSubState == EMmSubState::MM_DEREGISTERED_ECALL_INACTIVE)
        return;

    auto guti = m_storage->storedGuti->get();

    if (guti.type == nas::EIdentityType::NO_IDENTITY)
        return;

    bool tmsiMatches = false;
    for (auto &tmsi : tmsiIds)
    {
        if (tmsi.amfSetId == guti.gutiOrTmsi.amfSetId && tmsi.amfPointer == guti.gutiOrTmsi.amfPointer &&
            tmsi.tmsi == guti.gutiOrTmsi.tmsi)
        {
            tmsiMatches = true;
            break;
        }
    }

    if (!tmsiMatches)
        return;

    if (m_mmSubState == EMmSubState::MM_DEREGISTERED_ECALL_INACTIVE)
    {
        m_logger->debug("Ignoring received Paging due to eCall inactive");
        return;
    }

    m_timers->t3346.stop();

    if (m_mmState == EMmState::MM_REGISTERED_INITIATED || m_mmState == EMmState::MM_DEREGISTERED_INITIATED ||
        m_mmState == EMmState::MM_SERVICE_REQUEST_INITIATED)
    {
        m_logger->debug("Ignoring received Paging, another procedure already initiated");
        return;
    }

    m_logger->debug("Responding to received Paging");

    if (m_cmState == ECmState::CM_CONNECTED)
        mobilityUpdatingRequired(ERegUpdateCause::PAGING_OR_NOTIFICATION);
    else
        serviceRequestRequired(EServiceReqCause::IDLE_PAGING);
}

void NasMm::updateProvidedGuti(bool provide)
{
    if (m_cmState != ECmState::CM_IDLE)
        return;

    auto &guti = m_storage->storedGuti->get();
    if (!provide || guti.type == nas::EIdentityType::NO_IDENTITY)
    {
        m_base->shCtx.providedGuti.set({});
        m_base->shCtx.providedTmsi.set({});
    }
    else
    {
        auto tai = m_base->shCtx.getCurrentTai();
        if (tai.hasValue() && nas::utils::TaiListContains(m_storage->taiList->get(), nas::VTrackingAreaIdentity{tai}))
        {
            m_base->shCtx.providedGuti.set({});
            m_base->shCtx.providedTmsi.set(guti.gutiOrTmsi);
        }
        else
        {
            m_base->shCtx.providedGuti.set(guti.gutiOrTmsi);
            m_base->shCtx.providedTmsi.set({});
        }
    }
}

void NasMm::handleRrcFallbackIndication()
{
    // TODO: RRC does not send this indication yet

    if (m_cmState == ECmState::CM_IDLE)
    {
        m_logger->err("RRC fallback indication in CM-IDLE");
        return;
    }

    bool pendingProc = hasPendingProcedure();
    bool pendingData = m_sm->anyUplinkDataPending();

    switchCmState(ECmState::CM_IDLE);

    if (!pendingProc)
    {
        if (pendingData)
            serviceRequestRequired(EServiceReqCause::FALLBACK_INDICATION);
        else
            mobilityUpdatingRequired(ERegUpdateCause::FALLBACK_INDICATION);
    }
    else
    {
        if (m_procCtl.initialRegistration || m_procCtl.deregistration)
            (void)0;
        else if (m_procCtl.mobilityRegistration)
            mobilityUpdatingRequired(ERegUpdateCause::FALLBACK_INDICATION);
        else
            serviceRequestRequired(EServiceReqCause::FALLBACK_INDICATION);
    }
}

} // namespace nr::ue