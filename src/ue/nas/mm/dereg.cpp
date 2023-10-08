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
#include <ue/nas/sm/sm.hpp>

namespace nr::ue
{

static nas::IEDeRegistrationType MakeDeRegistrationType(EDeregCause deregCause)
{
    nas::IEDeRegistrationType res{};
    res.accessType = nas::EDeRegistrationAccessType::THREEGPP_ACCESS;
    res.reRegistrationRequired = nas::EReRegistrationRequired::NOT_REQUIRED;
    res.switchOff = deregCause == EDeregCause::SWITCH_OFF || deregCause == EDeregCause::USIM_REMOVAL
                        ? nas::ESwitchOff::SWITCH_OFF
                        : nas::ESwitchOff::NORMAL_DE_REGISTRATION;
    return res;
}

EProcRc NasMm::sendDeregistration(EDeregCause deregCause)
{
    auto currentTai = m_base->shCtx.getCurrentTai();
    if (!currentTai.hasValue())
        return EProcRc::STAY;

    if (m_rmState != ERmState::RM_REGISTERED)
    {
        m_logger->warn("De-registration could not be triggered. UE is already de-registered");
        return EProcRc::CANCEL;
    }

    if (m_mmState == EMmState::MM_DEREGISTERED_INITIATED)
        return EProcRc::CANCEL;
    if (m_mmSubState == EMmSubState::MM_REGISTERED_UPDATE_NEEDED)
        return EProcRc::STAY;

    // 5.2.3.2.3 "shall not initiate de-registration procedure unless timer T3346 is running and the current TAI is part
    // of the TAI list."
    if (m_mmSubState == EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE)
    {
        if (!m_timers->t3346.isRunning() ||
            !nas::utils::TaiListContains(m_storage->taiList->get(), nas::VTrackingAreaIdentity{currentTai}))
        {
            return EProcRc::STAY;
        }
    }

    // Perform Unified Access Control
    if (m_cmState == ECmState::CM_IDLE && performUac() != EUacResult::ALLOWED)
    {
        m_logger->warn("Performing local de-registration due to UAC");
        performLocalDeregistration();
        return EProcRc::CANCEL;
    }

    m_logger->debug("Starting de-registration procedure due to [%s]", ToJson(deregCause).str().c_str());

    updateProvidedGuti();

    auto request = std::make_unique<nas::DeRegistrationRequestUeOriginating>();
    request->deRegistrationType = MakeDeRegistrationType(deregCause);

    if (m_usim->m_currentNsCtx)
    {
        request->ngKSI.tsc = m_usim->m_currentNsCtx->tsc;
        request->ngKSI.ksi = m_usim->m_currentNsCtx->ngKsi;
    }
    else
    {
        request->ngKSI.ksi = nas::IENasKeySetIdentifier::NOT_AVAILABLE_OR_RESERVED;
        request->ngKSI.tsc = nas::ETypeOfSecurityContext::NATIVE_SECURITY_CONTEXT;
    }

    request->mobileIdentity = getOrGeneratePreferredId();

    auto rc = sendNasMessage(*request);
    if (rc != EProcRc::OK)
        return rc;

    m_lastDeregistrationRequest = std::move(request);
    m_lastDeregCause = deregCause;
    m_timers->t3521.resetExpiryCount();

    if (m_lastDeregistrationRequest->deRegistrationType.switchOff == nas::ESwitchOff::NORMAL_DE_REGISTRATION)
    {
        if (m_mmState == EMmState::MM_REGISTERED || m_mmState == EMmState::MM_REGISTERED_INITIATED)
            m_timers->t3521.start();
    }

    m_sm->localReleaseAllSessions();

    switchMmState(EMmSubState::MM_DEREGISTERED_INITIATED_PS);

    // TODO: Bu ikisinin burada olması gerektiğinden emin değilim
    if (deregCause == EDeregCause::SWITCH_OFF)
    {
        onSwitchOff();
        m_base->appTask->push(std::make_unique<NmUeNasToApp>(NmUeNasToApp::PERFORM_SWITCH_OFF));
    }
    else if (deregCause == EDeregCause::USIM_REMOVAL)
    {
        onSimRemoval();
        m_logger->info("SIM card has been invalidated");
        m_usim->invalidate();
    }

    return EProcRc::OK;
}

void NasMm::receiveDeregistrationAccept(const nas::DeRegistrationAcceptUeOriginating &msg)
{
    m_logger->debug("De-registration accept received");

    if (m_mmState != EMmState::MM_DEREGISTERED_INITIATED)
    {
        m_logger->warn("De-registration accept message ignored. UE is not in MM_DEREGISTERED_INITIATED");
        sendMmStatus(nas::EMmCause::MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE);
        return;
    }

    m_timers->t3521.stop();
    m_timers->t3519.stop();

    m_storage->storedSuci->clear();

    m_sm->localReleaseAllSessions();

    if (m_lastDeregCause == EDeregCause::DISABLE_5G)
        switchMmState(EMmSubState::MM_NULL_PS);
    else
        switchMmState(EMmSubState::MM_DEREGISTERED_PS);

    m_logger->info("De-registration is successful");
}

void NasMm::receiveDeregistrationRequest(const nas::DeRegistrationRequestUeTerminated &msg)
{
    if (m_rmState != ERmState::RM_REGISTERED)
    {
        m_logger->warn("De-registration message ignored. UE is already de-registered");
        return;
    }

    if (msg.deRegistrationType.accessType == nas::EDeRegistrationAccessType::NON_THREEGPP_ACCESS)
    {
        m_logger->warn("De-registration message ignored. Access type mismatch");
        sendMmStatus(nas::EMmCause::SEMANTICALLY_INCORRECT_MESSAGE);
        return;
    }

    m_logger->debug("Network initiated de-registration request received");

    bool forceIgnoreReregistration = false;
    bool forceLocalReleaseNas = false;

    // 5.5.1.2.7 Abnormal cases in the UE (de-registration collision)
    if (m_mmState == EMmState::MM_REGISTERED_INITIATED)
    {
        if (msg.deRegistrationType.reRegistrationRequired == nas::EReRegistrationRequired::REQUIRED)
            forceLocalReleaseNas = true;
    }
    // 5.5.2.2.6 Abnormal cases in the UE (de-registration collision)
    else if (m_mmState == EMmState::MM_DEREGISTERED_INITIATED)
    {
        // "De-registration containing de-registration type "switch off", If the UE receives a DEREGISTRATION REQUEST
        // message before the UE-initiated de-registration procedure has been completed, this message shall be ignored
        // and the UE-initiated de-registration procedure shall continue."
        if (m_lastDeregistrationRequest->deRegistrationType.switchOff == nas::ESwitchOff::SWITCH_OFF)
        {
            m_logger->debug("Network-initiated de-registration request is ignored due to abnormal cases");
            return;
        }

        // "If the DEREGISTRATION REQUEST message received by the UE contains de-registration type "re-registration
        // required", and the UE-initiated de-registration procedure is with de-registration type "normal
        // de-registration", the UE need not initiate the registration procedure for initial registration."
        if (msg.deRegistrationType.reRegistrationRequired == nas::EReRegistrationRequired::REQUIRED)
        {
            m_logger->debug(
                "Network-initiated de-registration request received but re-registration-required is ignored");
            forceIgnoreReregistration = true;
        }
    }
    // 5.6.1.7 Abnormal cases in the UE (de-registration collision)
    else if (m_mmState == EMmState::MM_SERVICE_REQUEST_INITIATED)
    {
        // "UE shall progress the DEREGISTRATION REQUEST message and the service request procedure shall be aborted."
        // (no specific action is required herein to abort service request procedure)
        (void)0;
    }

    bool reRegistrationRequired =
        msg.deRegistrationType.reRegistrationRequired == nas::EReRegistrationRequired::REQUIRED &&
        !forceIgnoreReregistration;

    m_sm->localReleaseAllSessions();

    if (reRegistrationRequired)
    {
        m_timers->t3346.stop();
        m_timers->t3396.stop();
        m_timers->t3584.stop();
        m_timers->t3585.stop();
    }

    sendNasMessage(nas::DeRegistrationAcceptUeTerminated{});

    // "Upon sending a DEREGISTRATION ACCEPT message, the UE shall delete the rejected NSSAI as specified in
    // subclause 4.6.2.2."
    m_storage->rejectedNssaiInTa->clear();
    m_storage->rejectedNssaiInPlmn->clear();

    // Handle 5.5.2.3.4 Abnormal cases in the UE, item b)
    auto handleAbnormal = [this]() {
        // "The UE shall delete 5G-GUTI, TAI list, last visited registered TAI, list of equivalent PLMNs, ngKSI, shall
        // set the 5GS update status to 5U2 NOT UPDATED and shall start timer T3502. A UE not supporting S1 mode may
        // enter the state 5GMM-DEREGISTERED.PLMN-SEARCH in order to perform a PLMN selection according to 3GPP
        // TS 23.122 [5]; otherwise the UE shall enter the state 5GMM-DEREGISTERED.ATTEMPTING-REGISTRATION."
        m_storage->storedGuti->clear();
        m_storage->taiList->clear();
        m_storage->lastVisitedRegisteredTai->clear();
        m_storage->equivalentPlmnList->clear();
        m_usim->m_currentNsCtx = {};
        m_usim->m_nonCurrentNsCtx = {};
        switchUState(E5UState::U2_NOT_UPDATED);
        m_timers->t3502.start();
        switchMmState(EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
    };

    // "If the de-registration type indicates "re-registration not required", the UE shall take the actions depending on
    // the received 5GMM cause value. Otherwise ignore the 5GMM cause value."
    if (msg.deRegistrationType.reRegistrationRequired == nas::EReRegistrationRequired::NOT_REQUIRED &&
        msg.mmCause.has_value())
    {
        auto cause = msg.mmCause->value;
        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED || cause == nas::EMmCause::PLMN_NOT_ALLOWED ||
            cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA ||
            cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA || cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
        {
            switchUState(E5UState::U3_ROAMING_NOT_ALLOWED);
            m_storage->storedGuti->clear();
            m_storage->lastVisitedRegisteredTai->clear();
            m_storage->taiList->clear();
            m_usim->m_currentNsCtx = {};
            m_usim->m_nonCurrentNsCtx = {};
        }

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED)
            m_usim->invalidate();

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED || cause == nas::EMmCause::PLMN_NOT_ALLOWED ||
            cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA)
            m_storage->equivalentPlmnList->clear();

        if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::TA_NOT_ALLOWED ||
            cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA || cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA ||
            cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
            resetRegAttemptCounter();

        if (cause == nas::EMmCause::PLMN_NOT_ALLOWED)
        {
            Plmn plmn = m_base->shCtx.getCurrentPlmn();
            if (plmn.hasValue())
                m_storage->forbiddenPlmnList->add(plmn);
        }

        if (cause == nas::EMmCause::TA_NOT_ALLOWED)
        {
            Tai tai = m_base->shCtx.getCurrentTai();
            if (tai.hasValue())
            {
                m_storage->forbiddenTaiListRps->add(tai);
                m_storage->serviceAreaList->mutate([&tai](auto &value) {
                    nas::utils::RemoveFromServiceAreaList(value, nas::VTrackingAreaIdentity{tai});
                });
            }
        }

        if (cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA || cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
        {
            Tai tai = m_base->shCtx.getCurrentTai();
            if (tai.hasValue())
            {
                m_storage->forbiddenTaiListRoaming->add(tai);
                m_storage->serviceAreaList->mutate([&tai](auto &value) {
                    nas::utils::RemoveFromServiceAreaList(value, nas::VTrackingAreaIdentity{tai});
                });
            }
        }

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED)
            switchMmState(EMmSubState::MM_DEREGISTERED_PS);

        if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA)
            switchMmState(EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);

        if (cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
            switchMmState(EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE);

        if (cause == nas::EMmCause::CONGESTION)
        {
            m_timers->t3346.stop();
            switchUState(E5UState::U2_NOT_UPDATED);
            switchMmState(EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);

            if (msg.t3346Value.has_value() && nas::utils::HasValue(*msg.t3346Value))
                m_timers->t3346.start(*msg.t3346Value);
        }

        if (cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
        {
            switchMmState(EMmSubState::MM_NULL_PS);
            setN1Capability(false);
        }

        if (cause != nas::EMmCause::ILLEGAL_UE && cause != nas::EMmCause::ILLEGAL_ME &&
            cause != nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED && cause != nas::EMmCause::PLMN_NOT_ALLOWED &&
            cause != nas::EMmCause::TA_NOT_ALLOWED && cause != nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA &&
            cause != nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA && cause != nas::EMmCause::CONGESTION &&
            cause != nas::EMmCause::N1_MODE_NOT_ALLOWED)
        {
            handleAbnormal();
            return;
        }
    }

    if (msg.deRegistrationType.reRegistrationRequired == nas::EReRegistrationRequired::NOT_REQUIRED &&
        !msg.mmCause.has_value())
    {
        handleAbnormal();
        return;
    }

    if (reRegistrationRequired)
    {
        // TODO: Perform re-registration, but consider forceLocalReleaseNas
        //  if 'forceLocalReleaseNas' is true, local release nas before re-registration.
        //  See "5.5.1.2.7 g) De-registration procedure collision."
        //  The UE should also re-establish any previously established PDU sessions
        (void)forceLocalReleaseNas;
    }
}

void NasMm::performLocalDeregistration()
{
    m_logger->debug("Performing local de-registration");

    m_timers->t3521.stop();
    m_timers->t3519.stop();

    m_storage->storedSuci->clear();

    m_sm->localReleaseAllSessions();

    switchMmState(EMmSubState::MM_DEREGISTERED_PS);
}

} // namespace nr::ue
