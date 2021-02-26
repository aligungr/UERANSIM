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

void NasMm::sendDeregistration(nas::ESwitchOff switchOff, bool dueToDisable5g)
{
    if (m_rmState != ERmState::RM_REGISTERED)
    {
        m_logger->warn("De-registration could not triggered. UE is already de-registered");
        return;
    }

    m_logger->debug("Starting de-registration procedure. switch-off[%d] disable-5g[%d]",
                    switchOff == nas::ESwitchOff::SWITCH_OFF ? 1 : 0, (int)dueToDisable5g);

    auto request = std::make_unique<nas::DeRegistrationRequestUeOriginating>();
    request->deRegistrationType.accessType = nas::EDeRegistrationAccessType::THREEGPP_ACCESS;
    request->deRegistrationType.reRegistrationRequired = nas::EReRegistrationRequired::NOT_REQUIRED;
    request->deRegistrationType.switchOff = switchOff;

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
    m_lastDeregDueToDisable5g = dueToDisable5g;
    m_timers->t3521.resetExpiryCount();

    if (switchOff != nas::ESwitchOff::SWITCH_OFF)
    {
        if (m_mmState == EMmState::MM_REGISTERED || m_mmState == EMmState::MM_REGISTERED_INITIATED)
            m_timers->t3521.start();
    }

    switchMmState(EMmState::MM_DEREGISTERED_INITIATED, EMmSubState::MM_DEREGISTERED_INITIATED_NA);

    m_sm->localReleaseAllSessions();

    if (switchOff == nas::ESwitchOff::SWITCH_OFF)
        m_base->appTask->push(new NwUeNasToApp(NwUeNasToApp::PERFORM_SWITCH_OFF));
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

    switchRmState(ERmState::RM_DEREGISTERED);

    if (m_lastDeregDueToDisable5g)
        switchMmState(EMmState::MM_NULL, EMmSubState::MM_NULL_NA);
    else
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);

    m_lastDeregDueToDisable5g = false;

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
        // De-registration containing de-registration type "switch off", If the UE receives a DEREGISTRATION REQUEST
        // message before the UE-initiated de-registration procedure has been completed, this message shall be ignored
        // and the UE-initiated de-registration procedure shall continue.
        if (m_lastDeregistrationRequest->deRegistrationType.switchOff == nas::ESwitchOff::SWITCH_OFF)
        {
            m_logger->debug("Network-initiated de-registration request is ignored due to abnormal cases");
            return;
        }

        // If the DEREGISTRATION REQUEST message received by the UE contains de-registration type "re-registration
        // required", and the UE-initiated de-registration procedure is with de-registration type "normal
        // de-registration", the UE need not initiate the registration procedure for initial registration.
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

    // If the de-registration type indicates "re-registration not required", the UE shall take the actions depending on
    // the received 5GMM cause value. Otherwise ignore the 5GMM cause value.
    if (msg.deRegistrationType.reRegistrationRequired == nas::EReRegistrationRequired::NOT_REQUIRED)
    {
        if (msg.mmCause.has_value())
        {
            switch (msg.mmCause->value)
            {
            case nas::EMmCause::ILLEGAL_UE:
            case nas::EMmCause::ILLEGAL_ME:
            case nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED: {
                switchUState(E5UState::U3_ROAMING_NOT_ALLOWED);
                m_storage.invalidateSim__();
                switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
                break;
            }
            // case nas::EMmCause::PLMN_NOT_ALLOWED: {
            //    switchUState(E5UState::U3_ROAMING_NOT_ALLOWED);
            //    invalidateAcquiredParams();
            //    // TODO: add to forbidden plmn list, otherwise endless plmn search may occur.
            //    switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
            //    break;
            //}
            case nas::EMmCause::TA_NOT_ALLOWED: {
                switchUState(E5UState::U3_ROAMING_NOT_ALLOWED);
                m_storage.discardUsim();
                switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE);
                break;
            }
            case nas::EMmCause::N1_MODE_NOT_ALLOWED: {
                switchUState(E5UState::U3_ROAMING_NOT_ALLOWED);
                m_storage.discardUsim();
                switchMmState(EMmState::MM_NULL, EMmSubState::MM_NULL_NA);
                break;
            }
            case nas::EMmCause::CONGESTION: {
                switchUState(E5UState::U2_NOT_UPDATED);
                m_timers->t3346.stop();
                switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);
                if (msg.t3346Value.has_value() && msg.t3346Value->value != 0)
                    m_timers->t3346.start(*msg.t3346Value);
                break;
            }
            default: {
                m_logger->err("Unhandled network-initiated de-registration cause[%s]",
                              nas::utils::EnumToString(msg.mmCause->value));

                switchUState(E5UState::U3_ROAMING_NOT_ALLOWED);
                m_storage.invalidateSim__();
                switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
                break;
            }
            }
        }
    }

    if (reRegistrationRequired)
    {
        // TODO: Perform re-registration, but consider forceLocalReleaseNas
        //  if 'forceLocalReleaseNas' is true, local release nas before re-registration.
        //  See "5.5.1.2.7 g) De-registration procedure collision."
    }
}

} // namespace nr::ue
