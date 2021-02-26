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
#include <ue/nas/task.hpp>
#include <ue/rrc/task.hpp>
#include <utils/common.hpp>

namespace nr::ue
{

void NasMm::onTimerExpire(nas::NasTimer &timer)
{
    switch (timer.getCode())
    {
    case 3346: {
        if (m_autoBehaviour && m_mmSubState == EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE)
        {
            sendRegistration(nas::ERegistrationType::INITIAL_REGISTRATION);
        }
        break;
    }
    case 3512: {
        if (m_autoBehaviour && m_mmState == EMmState::MM_REGISTERED && m_cmState == ECmState::CM_CONNECTED)
        {
            sendRegistration(nas::ERegistrationType::PERIODIC_REGISTRATION_UPDATING);
        }
        break;
    }
    case 3510: {
        // The UE shall abort the registration procedure for initial registration and the NAS signalling connection, if
        // any, shall be released locally if the initial registration request is not for emergency services..
        if (m_mmState == EMmState::MM_REGISTERED_INITIATED && m_lastRegistrationRequest)
        {
            switchRmState(ERmState::RM_DEREGISTERED);
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
            switchUState(E5UState::U2_NOT_UPDATED);

            if (m_lastRegistrationRequest->registrationType.registrationType !=
                nas::ERegistrationType::EMERGENCY_REGISTRATION)
            {
                localReleaseConnection();
            }

            handleCommonAbnormalRegFailure(m_lastRegistrationRequest->registrationType.registrationType);
        }

        break;
    }
    case 3521: {
        if (timer.getExpiryCount() == 5)
        {
            timer.resetExpiryCount();
            if (m_mmState == EMmState::MM_DEREGISTERED_INITIATED && m_lastDeregistrationRequest != nullptr)
            {
                m_logger->debug("De-registration aborted");

                if (m_lastDeregDueToDisable5g)
                    switchMmState(EMmState::MM_NULL, EMmSubState::MM_NULL_NA);
                else if (m_lastDeregistrationRequest->deRegistrationType.switchOff ==
                         nas::ESwitchOff::NORMAL_DE_REGISTRATION)
                    switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
            }
        }
        else
        {
            if (m_mmState == EMmState::MM_DEREGISTERED_INITIATED && m_lastDeregistrationRequest != nullptr)
            {
                m_logger->debug("Retrying de-registration request");

                sendNasMessage(*m_lastDeregistrationRequest);
                m_timers->t3521.start(false);
            }
        }
        break;
    }
    }
}

} // namespace nr::ue
