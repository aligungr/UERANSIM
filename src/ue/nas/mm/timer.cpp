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
#include <ue/rrc/task.hpp>

namespace nr::ue
{

void NasMm::onTimerExpire(UeTimer &timer)
{
    auto logExpired = [this, &timer]() {
        m_logger->debug("NAS timer[%d] expired [%d]", timer.getCode(), timer.getExpiryCount());
    };

    switch (timer.getCode())
    {
    case 3346: {
        if (m_mmSubState == EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE)
        {
            logExpired();
            initialRegistrationRequired(EInitialRegCause::T3346_EXPIRY);
        }
        else if (m_mmSubState == EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE)
        {
            logExpired();
            mobilityUpdatingRequired(ERegUpdateCause::T3346_EXPIRY_IN_ATT_UPD);
        }
        else if (m_mmSubState == EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION)
        {
            logExpired();
            initialRegistrationRequired(EInitialRegCause::T3346_EXPIRY_IN_ATT_REG);
        }
        break;
    }
    case 3502: {
        if (m_mmSubState == EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION ||
            m_mmSubState == EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE)
        {
            logExpired();
            resetRegAttemptCounter();

            if (m_mmSubState == EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION)
                initialRegistrationRequired(EInitialRegCause::T3502_EXPIRY_IN_ATT_REG);
            if (m_mmSubState == EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE)
                mobilityUpdatingRequired(ERegUpdateCause::T3502_EXPIRY_IN_ATT_UPD);
        }
        break;
    }
    case 3510: {
        if (m_mmState == EMmState::MM_REGISTERED_INITIATED)
        {
            logExpired();

            auto regType = m_lastRegistrationRequest->registrationType.registrationType;
            if (regType == nas::ERegistrationType::INITIAL_REGISTRATION ||
                regType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
            {
                // The UE shall abort the registration procedure for initial registration and the NAS signalling
                // connection, if any, shall be released locally if the initial registration request is not for
                // emergency services..
                switchMmState(EMmSubState::MM_DEREGISTERED_PS);
                switchUState(E5UState::U2_NOT_UPDATED);

                if (m_lastRegistrationRequest->registrationType.registrationType !=
                    nas::ERegistrationType::EMERGENCY_REGISTRATION)
                {
                    localReleaseConnection(false);
                }

                handleAbnormalInitialRegFailure(regType);
            }
            else if (regType == nas::ERegistrationType::MOBILITY_REGISTRATION_UPDATING ||
                     regType == nas::ERegistrationType::PERIODIC_REGISTRATION_UPDATING)
            {
                localReleaseConnection(false);
                handleAbnormalMobilityRegFailure(regType);
            }
        }
        break;
    }
    case 3511: {
        if (m_mmSubState == EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE)
        {
            logExpired();
            mobilityUpdatingRequired(ERegUpdateCause::T3511_EXPIRY_IN_ATT_UPD);
        }
        else if (m_mmSubState == EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION)
        {
            logExpired();
            initialRegistrationRequired(EInitialRegCause::T3511_EXPIRY_IN_ATT_REG);
        }
        break;
    }
    case 3512: {
        if (m_mmState == EMmState::MM_REGISTERED && m_cmState == ECmState::CM_IDLE)
        {
            logExpired();

            if (m_registeredForEmergency)
                performLocalDeregistration();
            else
                mobilityUpdatingRequired(ERegUpdateCause::T3512_EXPIRY);
        }
        break;
    }
    case 3516: {
        m_usim->m_rand = {};
        m_usim->m_resStar = {};
        break;
    }
    case 3517: {
        if (m_mmState == EMmState::MM_SERVICE_REQUEST_INITIATED)
        {
            logExpired();

            switchMmState(EMmSubState::MM_REGISTERED_PS);

            if (m_cmState == ECmState::CM_IDLE && m_lastServiceReqCause != EServiceReqCause::EMERGENCY_FALLBACK)
            {
                if (!hasEmergency() && !isHighPriority() && m_lastServiceReqCause != EServiceReqCause::IDLE_PAGING &&
                    m_lastServiceReqCause != EServiceReqCause::IDLE_3GPP_NOTIFICATION_N3GPP &&
                    m_lastServiceReqCause != EServiceReqCause::CONNECTED_3GPP_NOTIFICATION_N3GPP)
                    m_serCounter++;

                if (m_serCounter >= 5)
                    m_timers->t3525.start();
            }
        }
        break;
    }
    case 3519: {
        m_storage->storedSuci->clear();
        break;
    }
    case 3520: {
        logExpired();
        networkFailingTheAuthCheck(false);
        break;
    }
    case 3521: {
        if (timer.getExpiryCount() == 5)
        {
            timer.resetExpiryCount();
            if (m_mmState == EMmState::MM_DEREGISTERED_INITIATED && m_lastDeregistrationRequest != nullptr)
            {
                logExpired();
                m_logger->debug("De-registration aborted");

                if (m_lastDeregCause == EDeregCause::DISABLE_5G)
                    switchMmState(EMmSubState::MM_NULL_PS);
                else if (m_lastDeregistrationRequest->deRegistrationType.switchOff ==
                         nas::ESwitchOff::NORMAL_DE_REGISTRATION)
                    switchMmState(EMmSubState::MM_DEREGISTERED_PS);
            }
        }
        else
        {
            if (m_mmState == EMmState::MM_DEREGISTERED_INITIATED && m_lastDeregistrationRequest != nullptr)
            {
                logExpired();
                m_logger->debug("Retransmitting De-registration Request due to T3521 expiry");

                sendNasMessage(*m_lastDeregistrationRequest);
                m_timers->t3521.start(false);
            }
        }
        break;
    }
    }
}

} // namespace nr::ue
