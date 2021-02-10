//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <nas/utils.hpp>

namespace nr::ue
{

void NasMm::sendRegistration(nas::ERegistrationType registrationType, nas::EFollowOnRequest followOn)
{
    // The UE shall mark the 5G NAS security context on the USIM or in the non-volatile memory as invalid when the UE
    // initiates an initial registration procedure
    if (registrationType == nas::ERegistrationType::INITIAL_REGISTRATION)
    {
        m_currentNsCtx = {};
        m_nonCurrentNsCtx = {};
    }

    switchMmState(EMmState::MM_REGISTERED_INITIATED, EMmSubState::MM_REGISTERED_INITIATED_NA);

    nas::IENasKeySetIdentifier ngKsi;

    if (m_currentNsCtx.has_value())
    {
        ngKsi.tsc = m_currentNsCtx->tsc;
        ngKsi.ksi = m_currentNsCtx->ngKsi;
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

    if (m_storedGuti.type != nas::EIdentityType::NO_IDENTITY)
    {
        request->mobileIdentity = m_storedGuti;
    }
    else
    {
        auto suci = getOrGenerateSuci();
        if (suci.type != nas::EIdentityType::NO_IDENTITY)
        {
            request->mobileIdentity = suci;
            if (!m_timers->t3519.isRunning())
                m_timers->t3519.start();
        }
        else if (m_base->config->imei.has_value())
        {
            request->mobileIdentity.type = nas::EIdentityType::IMEI;
            request->mobileIdentity.value = *m_base->config->imei;
        }
        else if (m_base->config->imeiSv.has_value())
        {
            request->mobileIdentity.type = nas::EIdentityType::IMEISV;
            request->mobileIdentity.value = *m_base->config->imeiSv;
        }
        else
        {
            request->mobileIdentity.type = nas::EIdentityType::NO_IDENTITY;
        }
    }

    if (m_lastVisitedRegisteredTai.has_value())
        request->lastVisitedRegisteredTai = m_lastVisitedRegisteredTai.value();

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

    m_taiList = msg.taiList;

    if (msg.t3512Value.has_value() && nas::utils::HasValue(msg.t3512Value.value()))
    {
        m_timers->t3512.start(*msg.t3512Value);
        m_logger->debug("T3512 started with int[%d]", m_timers->t3512.getInterval());
    }

    if (msg.mobileIdentity.has_value() && msg.mobileIdentity->type == nas::EIdentityType::GUTI)
    {
        m_storedGuti = msg.mobileIdentity.value();
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
        m_nas->push(new NwUeNasToNas(NwUeNasToNas::ESTABLISH_INITIAL_SESSIONS));
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

    auto unhandledRejectCase = [cause, this]() {
        m_logger->err("Registration rejected with unhandled MMCause: %s", nas::utils::EnumToString(cause));
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
        switchRmState(ERmState::RM_DEREGISTERED);
    };

    if (regType == nas::ERegistrationType::INITIAL_REGISTRATION)
    {
        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED || cause == nas::EMmCause::PLMN_NOT_ALLOWED ||
            cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA ||
            cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
        {
            m_storedGuti = {};
            m_lastVisitedRegisteredTai = {};
            m_taiList = {};
            m_currentNsCtx = {};
            m_nonCurrentNsCtx = {};

            // TODO Normally UE switches to PLMN SEARCH, but this leads to endless registration attempt again and again.
            // due to RLS.
            // switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);

            switchRmState(ERmState::RM_DEREGISTERED);
        }
        else if (cause == nas::EMmCause::CONGESTION)
        {
            if (msg.t3346value.has_value() && nas::utils::HasValue(*msg.t3346value))
            {
                switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);
                switchRmState(ERmState::RM_DEREGISTERED);

                m_timers->t3346.stop();
                if (msg.sht != nas::ESecurityHeaderType::NOT_PROTECTED)
                    m_timers->t3346.start(*msg.t3346value);
                else
                    m_timers->t3346.start(nas::IEGprsTimer2{5});
            }
            else
            {
                // TODO abnormal case see 5.5.1.2.7.
                unhandledRejectCase();
            }
        }
        else if (cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
        {
            m_storedGuti = {};
            m_lastVisitedRegisteredTai = {};
            m_taiList = {};
            m_currentNsCtx = {};
            m_nonCurrentNsCtx = {};

            switchMmState(EMmState::MM_NULL, EMmSubState::MM_NULL_NA);
            switchRmState(ERmState::RM_DEREGISTERED);
        }
        else
        {
            // TODO
            unhandledRejectCase();
        }
    }
    else if (regType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
    {
        if (cause == nas::EMmCause::PEI_NOT_ACCEPTED)
        {
            // TODO
            unhandledRejectCase();
        }
        else
        {
            // TODO: abnormal case
            unhandledRejectCase();
        }
    }
    else
    {
        // TODO
        unhandledRejectCase();
    }
}

} // namespace nr::ue