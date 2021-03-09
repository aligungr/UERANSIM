//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"
#include <nas/utils.hpp>
#include <ue/app/task.hpp>
#include <ue/sm/sm.hpp>

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

void NasMm::sendDeregistration(EDeregCause deregCause)
{
    if (m_rmState != ERmState::RM_REGISTERED)
    {
        m_logger->warn("De-registration could not be triggered. UE is already de-registered");
        return;
    }

    m_logger->debug("Starting de-registration procedure due to [%s]", ToJson(deregCause).str().c_str());

    auto request = std::make_unique<nas::DeRegistrationRequestUeOriginating>();
    request->deRegistrationType = MakeDeRegistrationType(deregCause);

    if (m_storage.m_currentNsCtx)
    {
        request->ngKSI.tsc = m_storage.m_currentNsCtx->tsc;
        request->ngKSI.ksi = m_storage.m_currentNsCtx->ngKsi;
    }
    else
    {
        request->ngKSI.ksi = nas::IENasKeySetIdentifier::NOT_AVAILABLE_OR_RESERVED;
        request->ngKSI.tsc = nas::ETypeOfSecurityContext::NATIVE_SECURITY_CONTEXT;
    }

    request->mobileIdentity = getOrGeneratePreferredId();

    sendNasMessage(*request);
    m_lastDeregistrationRequest = std::move(request);
    m_lastDeregCause = deregCause;
    m_timers->t3521.resetExpiryCount();

    if (m_lastDeregistrationRequest->deRegistrationType.switchOff == nas::ESwitchOff::NORMAL_DE_REGISTRATION)
    {
        if (m_mmState == EMmState::MM_REGISTERED || m_mmState == EMmState::MM_REGISTERED_INITIATED)
            m_timers->t3521.start();
    }

    // TODO: Bu ikisinin burada olması gerektiğinden emin değilim
    if (deregCause == EDeregCause::SWITCH_OFF)
        m_base->appTask->push(new NwUeNasToApp(NwUeNasToApp::PERFORM_SWITCH_OFF));
    else if (deregCause == EDeregCause::USIM_REMOVAL)
    {
        m_logger->info("SIM card has been invalidated");
        m_storage.invalidateSim();
    }

    m_sm->localReleaseAllSessions();

    if (m_lastDeregistrationRequest->deRegistrationType.switchOff == nas::ESwitchOff::NORMAL_DE_REGISTRATION)
        switchMmState(EMmState::MM_DEREGISTERED_INITIATED, EMmSubState::MM_DEREGISTERED_INITIATED_NA);
    else
    {
        switchRmState(ERmState::RM_DEREGISTERED);
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
    }
}

void NasMm::receiveDeregistrationAccept(const nas::DeRegistrationAcceptUeOriginating &msg)
{
    m_logger->debug("De-registration accept received");

    if (m_mmSubState != EMmSubState::MM_DEREGISTERED_INITIATED_NA)
    {
        m_logger->warn("De-registration accept message ignored. UE is not in MM_DEREGISTERED_INITIATED");
        sendMmStatus(nas::EMmCause::MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE);
        return;
    }

    m_timers->t3521.stop();
    m_timers->t3519.stop();

    m_storage.m_storedSuci = {};

    m_sm->localReleaseAllSessions();

    switchRmState(ERmState::RM_DEREGISTERED);

    if (m_lastDeregCause == EDeregCause::DISABLE_5G)
        switchMmState(EMmState::MM_NULL, EMmSubState::MM_NULL_NA);
    else
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);

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
    switchRmState(ERmState::RM_DEREGISTERED);

    // "Upon sending a DEREGISTRATION ACCEPT message, the UE shall delete the rejected NSSAI as specified in
    // subclause 4.6.2.2."
    m_storage.m_rejectedNssaiInTa = {};
    m_storage.m_rejectedNssaiInPlmn = {};

    // Handle 5.5.2.3.4 Abnormal cases in the UE, item b)
    auto handleAbnormal = [this]() {
        // "The UE shall delete 5G-GUTI, TAI list, last visited registered TAI, list of equivalent PLMNs, ngKSI, shall
        // set the 5GS update status to 5U2 NOT UPDATED and shall start timer T3502. A UE not supporting S1 mode may
        // enter the state 5GMM-DEREGISTERED.PLMN-SEARCH in order to perform a PLMN selection according to 3GPP
        // TS 23.122 [5]; otherwise the UE shall enter the state 5GMM-DEREGISTERED.ATTEMPTING-REGISTRATION."
        m_storage.m_storedGuti = {};
        m_storage.m_taiList = {};
        m_storage.m_lastVisitedRegisteredTai = {};
        m_storage.m_equivalentPlmnList = {};
        m_storage.m_currentNsCtx = {};
        m_storage.m_nonCurrentNsCtx = {};
        switchUState(E5UState::U2_NOT_UPDATED);
        m_timers->t3502.start();
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
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
            m_storage.m_storedGuti = {};
            m_storage.m_lastVisitedRegisteredTai = {};
            m_storage.m_taiList = {};
            m_storage.m_currentNsCtx = {};
            m_storage.m_nonCurrentNsCtx = {};
        }

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED)
            m_storage.invalidateSim();

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED || cause == nas::EMmCause::PLMN_NOT_ALLOWED ||
            cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA)
            m_storage.m_equivalentPlmnList = {};

        if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::TA_NOT_ALLOWED ||
            cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA || cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA ||
            cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
            m_regCounter = 0;

        if (cause == nas::EMmCause::PLMN_NOT_ALLOWED)
        {
            // todo: add to forbidden plmn
        }

        if (cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA ||
            cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
        {
            // todo: add to forbidden tai
        }

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED)
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);

        if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA)
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);

        if (cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE);

        if (cause == nas::EMmCause::CONGESTION)
        {
            m_timers->t3346.stop();
            switchUState(E5UState::U2_NOT_UPDATED);
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);

            if (msg.t3346Value.has_value() && nas::utils::HasValue(*msg.t3346Value))
                m_timers->t3346.start(*msg.t3346Value);
        }

        if (cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
        {
            switchMmState(EMmState::MM_NULL, EMmSubState::MM_NULL_NA);
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

} // namespace nr::ue
