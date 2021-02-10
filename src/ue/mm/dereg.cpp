//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

namespace nr::ue
{

void NasMm::sendDeregistration(nas::ESwitchOff switchOff)
{
    switchMmState(EMmState::MM_DEREGISTERED_INITIATED, EMmSubState::MM_DEREGISTERED_INITIATED_NA);

    nas::DeRegistrationRequestUeOriginating request;
    request.deRegistrationType.accessType = nas::EDeRegistrationAccessType::THREEGPP_ACCESS;
    request.deRegistrationType.reRegistrationRequired = nas::EReRegistrationRequired::NOT_REQUIRED;
    request.deRegistrationType.switchOff = switchOff;

    if (m_currentNsCtx.has_value())
    {
        request.ngKSI.tsc = m_currentNsCtx->tsc;
        request.ngKSI.ksi = m_currentNsCtx->ngKsi;
    }

    if (m_storedGuti.type != nas::EIdentityType::NO_IDENTITY)
        request.mobileIdentity = m_storedGuti;
    else
        request.mobileIdentity = getOrGenerateSuci();

    sendNasMessage(request);

    if (switchOff != nas::ESwitchOff::SWITCH_OFF)
    {
        if (m_mmState == EMmState::MM_REGISTERED || m_mmState == EMmState::MM_DEREGISTERED_INITIATED)
            m_timers->t3521.start();
    }
}

void NasMm::receiveDeregistrationAccept(const nas::DeRegistrationAcceptUeOriginating &msg)
{
    m_timers->t3521.stop();
    m_timers->t3519.stop();

    m_storedSuci = {};

    switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
    switchRmState(ERmState::RM_DEREGISTERED);

    m_logger->info("De-registration is successful");
}

void NasMm::receiveDeregistrationRequest(const nas::DeRegistrationRequestUeTerminated &msg)
{
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
