//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <nas/utils.hpp>
#include <ue/nas/task.hpp>

namespace nr::ue
{

void NasMm::sendRegistration(nas::ERegistrationType registrationType, nas::EFollowOnRequest followOn)
{
    if (m_mmState != EMmState::MM_DEREGISTERED)
    {
        m_logger->warn("Registration could be triggered. UE is not in MM-DEREGISTERED state.");
        return;
    }

    // The UE shall mark the 5G NAS security context on the USIM or in the non-volatile memory as invalid when the UE
    // initiates an initial registration procedure
    if (registrationType == nas::ERegistrationType::INITIAL_REGISTRATION)
        m_storage.discardCurrentSecurity();

    switchMmState(EMmState::MM_REGISTERED_INITIATED, EMmSubState::MM_REGISTERED_INITIATED_NA);

    nas::IENasKeySetIdentifier ngKsi;

    if (m_storage.m_currentNsCtx.has_value())
    {
        ngKsi.tsc = m_storage.m_currentNsCtx->tsc;
        ngKsi.ksi = m_storage.m_currentNsCtx->ngKsi;
    }

    auto request = std::make_unique<nas::RegistrationRequest>();
    request->registrationType = nas::IE5gsRegistrationType{followOn, registrationType};
    request->nasKeySetIdentifier = ngKsi;
    request->requestedNSSAI = nas::utils::NssaiFrom(m_base->config->nssais);
    request->ueSecurityCapability = createSecurityCapabilityIe();
    request->updateType =
        nas::IE5gsUpdateType(nas::ESmsRequested::NOT_SUPPORTED, nas::ENgRanRadioCapabilityUpdate::NOT_NEEDED);

    if (registrationType != nas::ERegistrationType::PERIODIC_REGISTRATION_UPDATING)
    {
        request->mmCapability = nas::IE5gMmCapability{};
        request->mmCapability->s1Mode = nas::EEpcNasSupported::NOT_SUPPORTED;
        request->mmCapability->hoAttach = nas::EHandoverAttachSupported::NOT_SUPPORTED;
        request->mmCapability->lpp = nas::ELtePositioningProtocolCapability::NOT_SUPPORTED;
    }

    request->mobileIdentity = getOrGeneratePreferredId();

    if (m_storage.m_lastVisitedRegisteredTai.has_value())
        request->lastVisitedRegisteredTai = m_storage.m_lastVisitedRegisteredTai.value();

    m_timers->t3510.start();
    m_timers->t3502.stop();
    m_timers->t3511.stop();

    sendNasMessage(*request);
    m_lastRegistrationRequest = std::move(request);
}

void NasMm::receiveRegistrationAccept(const nas::RegistrationAccept &msg)
{
    if (m_mmState != EMmState::MM_REGISTERED_INITIATED)
    {
        m_logger->warn("Registration Accept ignored since the MM state is MM_REGISTERED_INITIATED");
        return;
    }

    bool sendCompleteMes = false;

    if (msg.taiList.has_value())
        m_storage.m_taiList = *msg.taiList;
    else
        m_storage.m_taiList = {};

    if (msg.t3512Value.has_value() && nas::utils::HasValue(msg.t3512Value.value()))
    {
        m_timers->t3512.start(*msg.t3512Value);
        m_logger->debug("T3512 started with int[%d]", m_timers->t3512.getInterval());
    }

    if (msg.mobileIdentity.has_value() && msg.mobileIdentity->type == nas::EIdentityType::GUTI)
    {
        m_storage.m_storedGuti = msg.mobileIdentity.value();
        m_timers->t3519.stop();

        sendCompleteMes = true;
    }

    if (sendCompleteMes)
        sendNasMessage(nas::RegistrationComplete{});

    switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_NORMAL_SERVICE);
    switchRmState(ERmState::RM_REGISTERED);

    auto regType = m_lastRegistrationRequest->registrationType.registrationType;
    m_logger->info("%s is successful", nas::utils::EnumToString(regType));

    if (regType == nas::ERegistrationType::INITIAL_REGISTRATION ||
        regType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
    {
        m_base->nasTask->push(new NwUeNasToNas(NwUeNasToNas::ESTABLISH_INITIAL_SESSIONS));
    }
}

void NasMm::receiveRegistrationReject(const nas::RegistrationReject &msg)
{
    auto cause = msg.mmCause.value;
    auto regType = m_lastRegistrationRequest->registrationType.registrationType;

    m_logger->err("%s failed [%s]", nas::utils::EnumToString(regType), nas::utils::EnumToString(cause));

    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::FAILURE)
            receiveEapFailureMessage(*msg.eapMessage->eap);
        else
            m_logger->warn("Network sent EAP with type of %s in RegistrationReject, ignoring EAP IE.",
                           nas::utils::EnumToString(msg.eapMessage->eap->code));
    }

    switchRmState(ERmState::RM_DEREGISTERED);

    auto handleAbnormalCase = [cause, this]() {
        m_logger->debug("Handling Registration Reject abnormal case");
        // todo
        m_storage.invalidateSim__();
        switchMmState(EMmState::MM_NULL, EMmSubState::MM_NULL_NA);
    };

    if (regType == nas::ERegistrationType::INITIAL_REGISTRATION)
    {
        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED || cause == nas::EMmCause::PLMN_NOT_ALLOWED ||
            cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA ||
            cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA || cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
        {
            switchUState(E5UState::U3_ROAMING_NOT_ALLOWED);
        }

        if (cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
        {
            switchUState(E5UState::U2_NOT_UPDATED);
        }

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED || cause == nas::EMmCause::PLMN_NOT_ALLOWED ||
            cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA ||
            cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA || cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
        {
            m_storage.m_storedGuti = {};
            m_storage.m_lastVisitedRegisteredTai = {};
            m_storage.m_taiList = {};
            m_storage.m_currentNsCtx = {};
            m_storage.m_nonCurrentNsCtx = {};
        }

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED)
        {
            m_storage.invalidateSim__();
        }

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED)
        {
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
        }

        if (cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA ||
            cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
        {
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE);
        }

        if (cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
        {
            switchMmState(EMmState::MM_NULL, EMmSubState::MM_NULL_NA);
            setN1Capability(false);
        }

        if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
        {
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
        }

        if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::TA_NOT_ALLOWED ||
            cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA || cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA ||
            cause == nas::EMmCause::N1_MODE_NOT_ALLOWED || cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
        {
            m_regCounter = 0;
        }

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA)
        {
            m_storage.m_equivalentPlmnList = {};
        }

        if (cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA || cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
        {
            // TODO add to forbidden tai
        }

        if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
        {
            // todo add to forbidden plmn
        }

        if (cause == nas::EMmCause::CONGESTION)
        {
            if (msg.t3346value.has_value() && nas::utils::HasValue(*msg.t3346value))
            {
                switchUState(E5UState::U2_NOT_UPDATED);
                switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);

                m_timers->t3346.stop();
                if (msg.sht != nas::ESecurityHeaderType::NOT_PROTECTED)
                    m_timers->t3346.start(*msg.t3346value);
                else
                    m_timers->t3346.start(nas::IEGprsTimer2{5});
            }
            else
            {
                handleAbnormalCase();
            }
        }

        if (cause != nas::EMmCause::ILLEGAL_UE && cause != nas::EMmCause::ILLEGAL_ME &&
            cause != nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED && cause != nas::EMmCause::PLMN_NOT_ALLOWED &&
            cause != nas::EMmCause::TA_NOT_ALLOWED && cause != nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA &&
            cause != nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA && cause != nas::EMmCause::CONGESTION &&
            cause != nas::EMmCause::N1_MODE_NOT_ALLOWED && cause != nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
        {
            handleAbnormalCase();
        }
    }
    else if (regType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
    {
        // TODO
    }
    else
    {
        // TODO
    }
}

void NasMm::incrementRegistrationAttempt()
{
    m_regCounter++;
}

} // namespace nr::ue