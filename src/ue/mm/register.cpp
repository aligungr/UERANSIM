//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <algorithm>
#include <nas/utils.hpp>
#include <ue/nas/task.hpp>
#include <utils/common.hpp>

namespace nr::ue
{

void NasMm::sendRegistration(nas::ERegistrationType regType, bool dueToDereg)
{
    if (m_mmState != EMmState::MM_DEREGISTERED)
    {
        m_logger->warn("Registration could not be triggered. UE is not in MM-DEREGISTERED state.");
        return;
    }

    // 5.5.1.2.7 Abnormal cases in the UE
    // a) Timer T3346 is running.
    if (m_timers->t3346.isRunning() && regType == nas::ERegistrationType::INITIAL_REGISTRATION && !hasEmergency())
    {
        // From 24.501: A UE configured with one or more access identities equal to 1, 2, or 11-15 applicable in the
        // selected PLMN as specified in subclause 4.5.2. Definition derived from 3GPP TS 22.261
        bool isHighPriority = false; // TODO: Assign this property properly.

        // The UE shall not start the registration procedure for initial registration in the following case
        if (!isHighPriority && !dueToDereg)
        {
            m_logger->debug("Initial registration canceled, T3346 is running");
            return;
        }
    }

    m_logger->debug("Sending %s", nas::utils::EnumToString(regType));

    // The UE shall mark the 5G NAS security context on the USIM or in the non-volatile memory as invalid when the UE
    // initiates an initial registration procedure
    if (regType == nas::ERegistrationType::INITIAL_REGISTRATION)
        m_storage.m_currentNsCtx = {};

    // Switch MM state
    switchMmState(EMmState::MM_REGISTERED_INITIATED, EMmSubState::MM_REGISTERED_INITIATED_NA);

    // Prepare requested NSSAI
    bool isDefaultNssai{};
    auto requestedNssai = makeRequestedNssai(isDefaultNssai);

    // Prepare FOR pending field
    nas::EFollowOnRequest followOn = nas::EFollowOnRequest::FOR_PENDING;

    // Create registration request
    auto request = std::make_unique<nas::RegistrationRequest>();
    request->registrationType = nas::IE5gsRegistrationType{followOn, regType};
    if (m_storage.m_currentNsCtx)
    {
        request->nasKeySetIdentifier.tsc = m_storage.m_currentNsCtx->tsc;
        request->nasKeySetIdentifier.ksi = m_storage.m_currentNsCtx->ngKsi;
    }
    request->requestedNSSAI = nas::utils::NssaiFrom(requestedNssai);
    request->ueSecurityCapability = createSecurityCapabilityIe();
    request->updateType =
        nas::IE5gsUpdateType(nas::ESmsRequested::NOT_SUPPORTED, nas::ENgRanRadioCapabilityUpdate::NOT_NEEDED);

#if 0
    // TODO: Wireshark cannot decode the message if this IE is used, check later
    request->networkSlicingIndication = nas::IENetworkSlicingIndication{};
    request->networkSlicingIndication->dcni =
        isDefaultNssai ? nas::EDefaultConfiguredNssaiIndication::CREATED_FROM_DEFAULT_CONFIGURED_NSSAI
                       : nas::EDefaultConfiguredNssaiIndication::NOT_CREATED_FROM_DEFAULT_CONFIGURED_NSSAI;
#endif

    // MM capability should be included if it is not a periodic registration
    if (regType != nas::ERegistrationType::PERIODIC_REGISTRATION_UPDATING)
    {
        request->mmCapability = nas::IE5gMmCapability{};
        request->mmCapability->s1Mode = nas::EEpcNasSupported::NOT_SUPPORTED;
        request->mmCapability->hoAttach = nas::EHandoverAttachSupported::NOT_SUPPORTED;
        request->mmCapability->lpp = nas::ELtePositioningProtocolCapability::NOT_SUPPORTED;
    }

    // Assign mobile identity
    request->mobileIdentity = getOrGeneratePreferredId();

    // Assign last visited registered TAI if available
    if (m_storage.m_lastVisitedRegisteredTai.has_value())
        request->lastVisitedRegisteredTai = *m_storage.m_lastVisitedRegisteredTai;

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
        m_logger->warn("Registration Accept ignored since the MM state is not MM_REGISTERED_INITIATED");
        sendMmStatus(nas::EMmCause::MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE);
        return;
    }

    m_logger->debug("Registration accept received");

    if (msg.registrationResult.registrationResult == nas::E5gsRegistrationResult::NON_THREEGPP_ACCESS)
    {
        m_logger->err("Non-3GPP registration accept is ignored");
        sendMmStatus(nas::EMmCause::SEMANTICALLY_INCORRECT_MESSAGE);
        return;
    }

    // Store the TAI list as a registration area
    m_storage.m_taiList = msg.taiList.value_or(nas::IE5gsTrackingAreaIdentityList{});
    // Store the service area list
    m_storage.m_serviceAreaList = msg.serviceAreaList.value_or(nas::IEServiceAreaList{});

    // Store the E-PLMN list and ..
    m_storage.m_equivalentPlmnList = msg.equivalentPLMNs.value_or(nas::IEPlmnList{});
    // .. if the initial registration procedure is not for emergency services, the UE shall remove from the list any
    // PLMN code that is already in the list of "forbidden PLMNs". ..
    if (!hasEmergency())
    {
        utils::EraseWhere(m_storage.m_equivalentPlmnList.plmns, [this](auto &plmn) {
            return std::any_of(m_storage.m_forbiddenPlmnList.plmns.begin(), m_storage.m_forbiddenPlmnList.plmns.end(),
                               [&plmn](auto &forbidden) { return nas::utils::DeepEqualsV(plmn, forbidden); });
        });
    }
    // .. in addition, the UE shall add to the stored list the PLMN code of the registered PLMN that sent the list
    nas::utils::AddToPlmnList(m_storage.m_equivalentPlmnList, nas::utils::PlmnFrom(m_storage.m_currentPlmn));

    // Upon receipt of the REGISTRATION ACCEPT message, the UE shall reset the registration attempt counter, enter state
    // 5GMM-REGISTERED and set the 5GS update status to 5U1 UPDATED.
    m_regCounter = 0;
    switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_NORMAL_SERVICE);
    switchRmState(ERmState::RM_REGISTERED);
    switchUState(E5UState::U1_UPDATED);

    // If the REGISTRATION ACCEPT message included a T3512 value IE, the UE shall use the value in the T3512 value IE as
    // periodic registration update timer (T3512).
    if (msg.t3512Value.has_value() && nas::utils::HasValue(msg.t3512Value.value()))
    {
        m_timers->t3512.start(*msg.t3512Value);
        m_logger->debug("T3512 started with int[%d]", m_timers->t3512.getInterval());
    }

    // Registration complete is sent conditionally
    bool sendComplete = false;

    // Process the received GUTI
    if (msg.mobileIdentity.has_value())
    {
        if (msg.mobileIdentity->type == nas::EIdentityType::GUTI)
        {
            m_storage.m_storedGuti = *msg.mobileIdentity;
            m_timers->t3519.stop();
            sendComplete = true;
        }
        else
        {
            m_logger->warn("GUTI was expected in registration accept but another identity type received");
        }
    }

    // Process rejected NSSAI
    if (msg.rejectedNSSAI.has_value())
    {
        for (auto &rejectedSlice : msg.rejectedNSSAI->list)
        {
            SingleSlice slice{};
            slice.sst = rejectedSlice.sst;
            slice.sd = rejectedSlice.sd;

            auto &list = rejectedSlice.cause == nas::ERejectedSNssaiCause::NA_IN_PLMN ? m_storage.m_rejectedNssaiInPlmn
                                                                                      : m_storage.m_rejectedNssaiInTa;
            list.addIfNotExists(slice);
        }
    }

    // Process network slicing subscription indication
    if (msg.networkSlicingIndication.has_value() &&
        msg.networkSlicingIndication->nssci == nas::ENetworkSlicingSubscriptionChangeIndication::CHANGED)
    {
        handleNetworkSlicingSubscriptionChange();
        sendComplete = true;
    }

    // Store the allowed NSSAI
    m_storage.m_allowedNssai = nas::utils::NssaiTo(msg.allowedNSSAI.value_or(nas::IENssai{}));

    // Process configured NSSAI IE
    if (msg.configuredNSSAI.has_value())
    {
        m_storage.m_configuredNssai = nas::utils::NssaiTo(msg.configuredNSSAI.value_or(nas::IENssai{}));
        sendComplete = true;
    }

    // Store the network feature support
    m_nwFeatureSupport = msg.networkFeatureSupport.value_or(nas::IE5gsNetworkFeatureSupport{});

    if (sendComplete)
        sendNasMessage(nas::RegistrationComplete{});

    auto regType = m_lastRegistrationRequest->registrationType.registrationType;

    if (regType == nas::ERegistrationType::INITIAL_REGISTRATION ||
        regType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
    {
        m_base->nasTask->push(new NwUeNasToNas(NwUeNasToNas::ESTABLISH_INITIAL_SESSIONS));
    }

    if (regType == nas::ERegistrationType::INITIAL_REGISTRATION)
        m_registeredForEmergency = false;
    else if (regType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
        m_registeredForEmergency = true;
    else
    {
        // Other registration types will *not* alter 'is registered for emergency' state
    }

    m_logger->info("%s is successful", nas::utils::EnumToString(regType));
}

void NasMm::receiveRegistrationReject(const nas::RegistrationReject &msg)
{
    if (m_mmState != EMmState::MM_REGISTERED_INITIATED)
    {
        m_logger->warn("Registration Reject ignored since the MM state is not MM_REGISTERED_INITIATED");
        sendMmStatus(nas::EMmCause::MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE);
        return;
    }

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

    auto handleAbnormalCase = [this, regType, cause]() {
        m_logger->debug("Handling Registration Reject abnormal case");

        // If the registration request is not an initial registration request for emergency services, upon reception of
        // the 5GMM causes #95, #96, #97, #99 and #111 the UE should set the registration attempt counter to 5.
        if (regType == nas::ERegistrationType::INITIAL_REGISTRATION)
        {
            int n = static_cast<int>(cause);
            if (n == 95 || n == 96 || n == 97 || n == 99 || n == 111)
                m_regCounter = 5;
        }

        handleCommonAbnormalRegFailure(regType);
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
            m_storage.invalidateSim();
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
            nas::utils::AddToPlmnList(m_storage.m_forbiddenPlmnList, nas::utils::PlmnFrom(m_storage.m_currentPlmn));
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
        if (cause == nas::EMmCause::PEI_NOT_ACCEPTED)
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NO_SUPI);
        else
        {
            // Spec perseveringly says that upper layers should be informed as well, for additional action for emergency
            // registration, but no need for now.
            handleAbnormalCase();
        }
    }
    else
    {
        // TODO
    }
}

void NasMm::handleCommonAbnormalRegFailure(nas::ERegistrationType regType)
{
    // Timer T3510 shall be stopped if still running
    m_timers->t3510.stop();

    // If the registration procedure is neither an initial registration for emergency services nor for establishing an
    // emergency PDU session with registration type not set to "emergency registration", the registration attempt
    // counter shall be incremented, unless it was already set to 5.
    if (regType != nas::ERegistrationType::EMERGENCY_REGISTRATION && !hasEmergency() && m_regCounter != 5)
        m_regCounter++;

    // If the registration attempt counter is less than 5:
    if (m_regCounter < 5)
    {
        // If the initial registration request is not for emergency services, timer T3511 is started and the state is
        // changed to 5GMM-DEREGISTERED.ATTEMPTING-REGISTRATION. When timer T3511 expires the registration procedure for
        // initial registration shall be restarted, if still required.
        if (!hasEmergency())
        {
            m_timers->t3511.start();
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);
        }
    }
    else
    {
        // The UE shall delete 5G-GUTI, TAI list, last visited TAI, list of equivalent PLMNs and ngKSI, ..
        m_storage.m_storedGuti = {};
        m_storage.m_taiList = {};
        m_storage.m_lastVisitedRegisteredTai = {};
        m_storage.m_equivalentPlmnList = {};
        m_storage.m_currentNsCtx = {};
        m_storage.m_nonCurrentNsCtx = {};

        // .. start timer T3502 ..
        m_timers->t3502.start();

        // .. and shall set the 5GS update status to 5U2 NOT UPDATED. The state is changed to
        // 5GMM-DEREGISTERED.ATTEMPTING-REGISTRATION or optionally to 5GMM-DEREGISTERED.PLMN-SEARCH in order to perform
        // a PLMN selection according to 3GPP TS 23.122 [5].
        switchUState(E5UState::U2_NOT_UPDATED);
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);
    }
}

} // namespace nr::ue