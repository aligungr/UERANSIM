//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"
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

    auto request = std::make_unique<nas::DeRegistrationRequestUeOriginating>();
    request->deRegistrationType.accessType = nas::EDeRegistrationAccessType::THREEGPP_ACCESS;
    request->deRegistrationType.reRegistrationRequired = nas::EReRegistrationRequired::NOT_REQUIRED;
    request->deRegistrationType.switchOff = switchOff;

    if (m_currentNsCtx.has_value())
    {
        request->ngKSI.tsc = m_currentNsCtx->tsc;
        request->ngKSI.ksi = m_currentNsCtx->ngKsi;
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

    // TODO local release of all PDU sessions
    // TODO: switch off the UE if it's switch off
}

void NasMm::receiveDeregistrationAccept(const nas::DeRegistrationAcceptUeOriginating &msg)
{
    if (m_mmSubState != EMmSubState::MM_DEREGISTERED_INITIATED_NA)
    {
        m_logger->warn("De-registration accept message ignored. UE is not in MM_DEREGISTERED_INITIATED");
        sendMmStatus(nas::EMmCause::MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE);
        return;
    }

    m_timers->t3521.stop();
    m_timers->t3519.stop();

    m_storedSuci = {};

    switchRmState(ERmState::RM_DEREGISTERED);

    if (m_lastDeregDueToDisable5g)
        switchMmState(EMmState::MM_NULL, EMmSubState::MM_NULL_NA);
    else
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);

    m_lastDeregistrationRequest = nullptr;
    m_lastDeregDueToDisable5g = false;

    m_logger->info("De-registration is successful");
}

void NasMm::receiveDeregistrationRequest(const nas::DeRegistrationRequestUeTerminated &msg)
{
    bool forceIgnoreReregistration = false; // todo use this

    // 5.5.2.2.6 Abnormal cases in the UE (de-registration collision)
    if (m_mmState == EMmState::MM_DEREGISTERED_INITIATED)
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

        return;
    }

    // TODO: this function is not complete

    if (msg.deRegistrationType.reRegistrationRequired == nas::EReRegistrationRequired::REQUIRED)
    {
        m_timers->t3346.stop();
        m_timers->t3396.stop();
        m_timers->t3584.stop();
        m_timers->t3585.stop();
    }

    sendNasMessage(nas::DeRegistrationAcceptUeTerminated{});
    switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
    switchRmState(ERmState::RM_DEREGISTERED);
}

} // namespace nr::ue
