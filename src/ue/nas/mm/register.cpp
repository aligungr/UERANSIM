//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <algorithm>
#include <lib/nas/utils.hpp>
#include <ue/nas/task.hpp>

namespace nr::ue
{

EProcRc NasMm::sendInitialRegistration(EInitialRegCause regCause)
{
    if (m_rmState != ERmState::RM_DEREGISTERED)
    {
        m_logger->warn("Registration could not be triggered. UE is not in RM-DEREGISTERED state.");
        return EProcRc::CANCEL;
    }

    if (m_mmState == EMmState::MM_REGISTERED_INITIATED)
        return EProcRc::CANCEL;
    if (m_mmState == EMmState::MM_DEREGISTERED_INITIATED)
        return EProcRc::STAY;
    if (m_mmSubState == EMmSubState::MM_REGISTERED_UPDATE_NEEDED)
        return EProcRc::STAY;

    bool isEmergencyReg = regCause == EInitialRegCause::EMERGENCY_SERVICES;

    // 5.5.1.2.7 Abnormal cases in the UE
    // a) Timer T3346 is running.
    if (m_timers->t3346.isRunning() && !isEmergencyReg && !hasEmergency())
    {
        // From 24.501: A UE configured with one or more access identities equal to 1, 2, or 11-15 applicable in the
        // selected PLMN as specified in subclause 4.5.2. Definition derived from 3GPP TS 22.261
        bool highPriority = isHighPriority();

        // The UE shall not start the registration procedure for initial registration in the following case
        if (!highPriority && regCause != EInitialRegCause::DUE_TO_DEREGISTRATION)
        {
            m_logger->debug("Initial registration canceled, T3346 is running");
            return EProcRc::STAY;
        }
    }

    // Perform Unified Access Control
    if (m_cmState == ECmState::CM_IDLE && performUac() != EUacResult::ALLOWED)
    {
        return EProcRc::STAY;
    }

    m_logger->debug("Sending %s",
                    nas::utils::EnumToString(isEmergencyReg ? nas::ERegistrationType::EMERGENCY_REGISTRATION
                                                            : nas::ERegistrationType::INITIAL_REGISTRATION));

    updateProvidedGuti();

    // The UE shall mark the 5G NAS security context on the USIM or in the non-volatile memory as invalid when the UE
    // initiates an initial registration procedure
    m_usim->m_currentNsCtx = {};

    // Prepare requested NSSAI
    bool isDefaultNssai{};
    auto requestedNssai = makeRequestedNssai(isDefaultNssai);

    // Prepare FOR pending field
    nas::EFollowOnRequest followOn = nas::EFollowOnRequest::FOR_PENDING;

    // Create registration request
    auto request = std::make_unique<nas::RegistrationRequest>();
    request->registrationType =
        nas::IE5gsRegistrationType{followOn, isEmergencyReg ? nas::ERegistrationType::EMERGENCY_REGISTRATION
                                                            : nas::ERegistrationType::INITIAL_REGISTRATION};

    // TODO: Wireshark cannot decode the message if this IE is used, check later
    // request->networkSlicingIndication = nas::IENetworkSlicingIndication{};
    // request->networkSlicingIndication->dcni =
    //    isDefaultNssai ? nas::EDefaultConfiguredNssaiIndication::CREATED_FROM_DEFAULT_CONFIGURED_NSSAI
    //                   : nas::EDefaultConfiguredNssaiIndication::NOT_CREATED_FROM_DEFAULT_CONFIGURED_NSSAI;

    // Assign MM capability
    request->mmCapability = nas::IE5gMmCapability{};
    request->mmCapability->s1Mode = nas::EEpcNasSupported::NOT_SUPPORTED;
    request->mmCapability->hoAttach = nas::EHandoverAttachSupported::NOT_SUPPORTED;
    request->mmCapability->lpp = nas::ELtePositioningProtocolCapability::NOT_SUPPORTED;

    // Assign other fields
    request->mobileIdentity = getOrGeneratePreferredId();
    if (m_storage->lastVisitedRegisteredTai->get().hasValue())
        request->lastVisitedRegisteredTai = nas::IE5gsTrackingAreaIdentity{m_storage->lastVisitedRegisteredTai->get()};
    if (!requestedNssai.slices.empty())
        request->requestedNSSAI = nas::utils::NssaiFrom(requestedNssai);
    request->ueSecurityCapability = createSecurityCapabilityIe();
    request->updateType =
        nas::IE5gsUpdateType(nas::ESmsRequested::NOT_SUPPORTED, nas::ENgRanRadioCapabilityUpdate::NOT_NEEDED);

    // Assign ngKSI
    if (m_usim->m_currentNsCtx)
    {
        request->nasKeySetIdentifier.tsc = m_usim->m_currentNsCtx->tsc;
        request->nasKeySetIdentifier.ksi = m_usim->m_currentNsCtx->ngKsi;
    }

    // Send the message
    auto rc = sendNasMessage(*request);
    if (rc != EProcRc::OK)
        return rc;

    // Switch MM state
    switchMmState(EMmSubState::MM_REGISTERED_INITIATED_PS);

    m_lastRegistrationRequest = std::move(request);
    m_lastRegWithoutNsc = m_usim->m_currentNsCtx == nullptr;

    // Process timers
    m_timers->t3510.start();
    m_timers->t3502.stop();
    m_timers->t3511.stop();

    return EProcRc::OK;
}

EProcRc NasMm::sendMobilityRegistration(ERegUpdateCause updateCause)
{
    if (m_rmState == ERmState::RM_DEREGISTERED)
    {
        m_logger->warn("Mobility updating could not be triggered. UE is in RM-DEREGISTERED state.");
        return EProcRc::CANCEL;
    }

    if (m_mmState == EMmState::MM_REGISTERED_INITIATED)
        return EProcRc::CANCEL;
    if (m_mmState == EMmState::MM_DEREGISTERED_INITIATED)
        return EProcRc::STAY;

    if (updateCause == ERegUpdateCause::T3512_EXPIRY && m_mmSubState == EMmSubState::MM_REGISTERED_NON_ALLOWED_SERVICE)
    {
        if (!isHighPriority() && !hasEmergency())
            return EProcRc::STAY;
    }

    // 5.5.1.3.7 Abnormal cases in the UE
    // a) Timer T3346 is running.
    if (m_timers->t3346.isRunning())
    {
        bool allowed = m_cmState == ECmState::CM_CONNECTED || updateCause == ERegUpdateCause::PAGING_OR_NOTIFICATION ||
                       isHighPriority() || hasEmergency() || updateCause == ERegUpdateCause::CONFIGURATION_UPDATE;
        if (!allowed)
        {
            m_logger->debug("Mobility updating canceled, T3346 is running");
            return EProcRc::STAY;
        }
    }

    // 5.6.1.7 Abnormal cases in the UE
    // d) Registration procedure for mobility and periodic registration update is triggered
    if (m_mmState == EMmState::MM_SERVICE_REQUEST_INITIATED)
    {
        m_timers->t3517.stop();
    }

    // Perform Unified Access Control
    if (m_cmState == ECmState::CM_IDLE && performUac() != EUacResult::ALLOWED)
    {
        return EProcRc::STAY;
    }

    m_logger->debug("Sending %s with update cause [%s]",
                    nas::utils::EnumToString(updateCause == ERegUpdateCause::T3512_EXPIRY
                                                 ? nas::ERegistrationType::PERIODIC_REGISTRATION_UPDATING
                                                 : nas::ERegistrationType::MOBILITY_REGISTRATION_UPDATING),
                    ToJson(updateCause).str().c_str());

    // "if the registration procedure for mobility and periodic update was triggered due to the last CONFIGURATION
    // UPDATE COMMAND message that indicates "registration requested" including: ... the UE NAS shall not provide the
    // lower layers with the 5G-S-TMSI or the registered GUAMI; "
    updateProvidedGuti(updateCause != ERegUpdateCause::CONFIGURATION_UPDATE);

    // Prepare FOR pending field
    nas::EFollowOnRequest followOn = nas::EFollowOnRequest::FOR_PENDING;

    // Prepare requested NSSAI
    bool isDefaultNssai{};
    auto requestedNssai = makeRequestedNssai(isDefaultNssai);

    // Create registration request
    auto request = std::make_unique<nas::RegistrationRequest>();
    request->registrationType =
        nas::IE5gsRegistrationType{followOn, updateCause == ERegUpdateCause::T3512_EXPIRY
                                                 ? nas::ERegistrationType::PERIODIC_REGISTRATION_UPDATING
                                                 : nas::ERegistrationType::MOBILITY_REGISTRATION_UPDATING};

    // Assign update type
    request->updateType = nas::IE5gsUpdateType{};
    request->updateType->smsRequested = nas::ESmsRequested::NOT_SUPPORTED;
    request->updateType->ngRanRcu = updateCause == ERegUpdateCause::RADIO_CAP_CHANGE
                                        ? nas::ENgRanRadioCapabilityUpdate::NEEDED
                                        : nas::ENgRanRadioCapabilityUpdate::NOT_NEEDED;

    // Assign uplink data status
    if (updateCause == ERegUpdateCause::FALLBACK_INDICATION || updateCause == ERegUpdateCause::CONNECTION_RECOVERY)
    {
        request->uplinkDataStatus = nas::IEUplinkDataStatus{};
        request->uplinkDataStatus->psi = m_sm->getUplinkDataStatus();
    }

    // Assign PDU session status
    if (m_cmState == ECmState::CM_IDLE)
    {
        request->pduSessionStatus = nas::IEPduSessionStatus{};
        request->pduSessionStatus->psi = m_sm->getPduSessionStatus();
    }

    // MM capability should be included if it is not a periodic registration
    if (updateCause != ERegUpdateCause::T3512_EXPIRY)
    {
        request->mmCapability = nas::IE5gMmCapability{};
        request->mmCapability->s1Mode = nas::EEpcNasSupported::NOT_SUPPORTED;
        request->mmCapability->hoAttach = nas::EHandoverAttachSupported::NOT_SUPPORTED;
        request->mmCapability->lpp = nas::ELtePositioningProtocolCapability::NOT_SUPPORTED;
    }

    // Assign other fields
    if (m_storage->lastVisitedRegisteredTai->get().hasValue())
        request->lastVisitedRegisteredTai = nas::IE5gsTrackingAreaIdentity{m_storage->lastVisitedRegisteredTai->get()};
    request->mobileIdentity = getOrGeneratePreferredId();
    if (!requestedNssai.slices.empty())
        request->requestedNSSAI = nas::utils::NssaiFrom(requestedNssai);
    request->ueSecurityCapability = createSecurityCapabilityIe();

    // TODO: Wireshark cannot decode the message if this IE is used, check later
    // request->networkSlicingIndication = nas::IENetworkSlicingIndication{};
    // request->networkSlicingIndication->dcni =
    //    isDefaultNssai ? nas::EDefaultConfiguredNssaiIndication::CREATED_FROM_DEFAULT_CONFIGURED_NSSAI
    //                   : nas::EDefaultConfiguredNssaiIndication::NOT_CREATED_FROM_DEFAULT_CONFIGURED_NSSAI;

    // Send the message
    auto rc = sendNasMessage(*request);
    if (rc != EProcRc::OK)
        return rc;
    m_lastRegistrationRequest = std::move(request);
    m_lastRegWithoutNsc = m_usim->m_currentNsCtx == nullptr;

    // Switch state
    switchMmState(EMmSubState::MM_REGISTERED_INITIATED_PS);

    // Process timers
    m_timers->t3510.start();
    m_timers->t3502.stop();
    m_timers->t3511.stop();

    return EProcRc::OK;
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

    auto regType = m_lastRegistrationRequest->registrationType.registrationType;
    if (regType == nas::ERegistrationType::INITIAL_REGISTRATION ||
        regType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
        receiveInitialRegistrationAccept(msg);
    else
        receiveMobilityRegistrationAccept(msg);

    // The RAND and RES* values stored in the ME shall be deleted and timer T3516, if running, shall be stopped
    m_usim->m_rand = {};
    m_usim->m_resStar = {};
    m_timers->t3516.stop();
}

void NasMm::receiveInitialRegistrationAccept(const nas::RegistrationAccept &msg)
{
    Tai currentTai = m_base->shCtx.getCurrentTai();
    Plmn currentPlmn = currentTai.plmn;

    if (!currentTai.hasValue())
        return;

    // Store the TAI list as a registration area
    if (msg.taiList.has_value())
    {
        if (nas::utils::TaiListSize(*msg.taiList) == 0)
        {
            m_logger->err("Invalid TAI list size");
            sendMmStatus(nas::EMmCause::SEMANTICALLY_INCORRECT_MESSAGE);
            return;
        }

        m_storage->taiList->set(*msg.taiList);
    }

    if (nas::utils::TaiListContains(m_storage->taiList->get(), nas::VTrackingAreaIdentity{currentTai}))
        m_storage->lastVisitedRegisteredTai->set(currentTai);

    // Store the service area list
    m_storage->serviceAreaList->set(msg.serviceAreaList.value_or(nas::IEServiceAreaList{}));

    updateForbiddenTaiListsForAllowedIndications();

    // Store the E-PLMN list and ..
    m_storage->equivalentPlmnList->clear();
    if (msg.equivalentPLMNs.has_value())
        for (auto &item : msg.equivalentPLMNs->plmns)
            m_storage->equivalentPlmnList->add(nas::utils::PlmnFrom(item));
    // .. if the initial registration procedure is not for emergency services, the UE shall remove from the list any
    // PLMN code that is already in the list of "forbidden PLMNs". ..
    if (!hasEmergency())
    {
        m_storage->forbiddenPlmnList->forEach(
            [this](auto &forbiddenPlmn) { m_storage->equivalentPlmnList->remove(forbiddenPlmn); });
    }
    // .. in addition, the UE shall add to the stored list the PLMN code of the registered PLMN that sent the list
    m_storage->equivalentPlmnList->add(currentPlmn);

    // Upon receipt of the REGISTRATION ACCEPT message, the UE shall reset the registration attempt counter, enter state
    // 5GMM-REGISTERED and set the 5GS update status to 5U1 UPDATED.
    resetRegAttemptCounter();
    switchMmState(EMmSubState::MM_REGISTERED_NORMAL_SERVICE);
    switchUState(E5UState::U1_UPDATED);

    // If the REGISTRATION ACCEPT message included a T3512 value IE, the UE shall use the value in the T3512 value IE as
    // periodic registration update timer (T3512).
    if (msg.t3512Value.has_value() && nas::utils::HasValue(msg.t3512Value.value()))
        m_timers->t3512.start(*msg.t3512Value);

    // Registration complete is sent conditionally
    bool sendComplete = false;

    // "If the REGISTRATION ACCEPT message contains a 5G-GUTI, the UE shall return a REGISTRATION COMPLETE message to
    // the AMF to acknowledge the received 5G-GUTI, stop timer T3519 if running, and delete any stored SUCI."
    if (msg.mobileIdentity.has_value())
    {
        if (msg.mobileIdentity->type == nas::EIdentityType::GUTI)
        {
            m_storage->storedSuci->clear();
            m_storage->storedGuti->set(*msg.mobileIdentity);
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
            SingleSlice slice;
            slice.sst = rejectedSlice.sst;
            slice.sd = rejectedSlice.sd;

            auto &nssai = rejectedSlice.cause == nas::ERejectedSNssaiCause::NA_IN_PLMN ? m_storage->rejectedNssaiInPlmn
                                                                                       : m_storage->rejectedNssaiInTa;
            nssai->mutate([slice](auto &value) { value.addIfNotExists(slice); });
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
    m_storage->allowedNssai->set(nas::utils::NssaiTo(msg.allowedNSSAI.value_or(nas::IENssai{})));

    // Process configured NSSAI IE
    if (msg.configuredNSSAI.has_value())
    {
        m_storage->configuredNssai->set(nas::utils::NssaiTo(msg.configuredNSSAI.value_or(nas::IENssai{})));
        sendComplete = true;
    }

    // Store the network feature support
    if (msg.networkFeatureSupport)
    {
        if (m_nwFeatureSupport)
        {
            m_nwFeatureSupport->imsVoPs3gpp = msg.networkFeatureSupport->imsVoPs3gpp;
            m_nwFeatureSupport->imsVoPsN3gpp = msg.networkFeatureSupport->imsVoPsN3gpp;
            m_nwFeatureSupport->emc = msg.networkFeatureSupport->emc;
            m_nwFeatureSupport->emf = msg.networkFeatureSupport->emf;
            m_nwFeatureSupport->iwkN26 = msg.networkFeatureSupport->iwkN26;
            m_nwFeatureSupport->mpsi = msg.networkFeatureSupport->mpsi;

            if (msg.networkFeatureSupport->emcn3)
                m_nwFeatureSupport->emcn3 = msg.networkFeatureSupport->emcn3;
            if (msg.networkFeatureSupport->mcsi)
                m_nwFeatureSupport->mcsi = msg.networkFeatureSupport->mcsi;
        }
        else
        {
            m_nwFeatureSupport = msg.networkFeatureSupport;
        }
    }

    if (sendComplete)
    {
        m_logger->debug("Sending Registration Complete");
        sendNasMessage(nas::RegistrationComplete{});
    }

    auto regType = m_lastRegistrationRequest->registrationType.registrationType;

    if (regType == nas::ERegistrationType::INITIAL_REGISTRATION)
        m_registeredForEmergency = false;
    else if (regType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
        m_registeredForEmergency = true;

    m_logger->info("%s is successful", nas::utils::EnumToString(regType));
}

void NasMm::receiveMobilityRegistrationAccept(const nas::RegistrationAccept &msg)
{
    Tai currentTai = m_base->shCtx.getCurrentTai();
    Plmn currentPlmn = currentTai.plmn;

    if (!currentTai.hasValue())
        return;

    // "The UE, upon receiving a REGISTRATION ACCEPT message, shall delete its old TAI list and store the received TAI
    // list. If there is no TAI list received, the UE shall consider the old TAI list as valid."
    if (msg.taiList.has_value())
    {
        if (nas::utils::TaiListSize(*msg.taiList) == 0)
        {
            m_logger->err("Invalid TAI list size");
            sendMmStatus(nas::EMmCause::SEMANTICALLY_INCORRECT_MESSAGE);
            return;
        }

        m_storage->taiList->set(*msg.taiList);
    }

    if (nas::utils::TaiListContains(m_storage->taiList->get(), nas::VTrackingAreaIdentity{currentTai}))
        m_storage->lastVisitedRegisteredTai->set(currentTai);

    // Store the E-PLMN list and ..
    m_storage->equivalentPlmnList->clear();
    if (msg.equivalentPLMNs.has_value())
        for (auto &item : msg.equivalentPLMNs->plmns)
            m_storage->equivalentPlmnList->add(nas::utils::PlmnFrom(item));
    // .. if the initial registration procedure is not for emergency services, the UE shall remove from the list any
    // PLMN code that is already in the list of "forbidden PLMNs". ..
    if (!hasEmergency())
    {
        m_storage->forbiddenPlmnList->forEach(
            [this](auto &forbiddenPlmn) { m_storage->equivalentPlmnList->remove(forbiddenPlmn); });
    }
    // .. in addition, the UE shall add to the stored list the PLMN code of the registered PLMN that sent the list
    m_storage->equivalentPlmnList->add(currentPlmn);

    // Store the service area list
    m_storage->serviceAreaList->set(msg.serviceAreaList.value_or(nas::IEServiceAreaList{}));
    updateForbiddenTaiListsForAllowedIndications();

    // "Upon receipt of the REGISTRATION ACCEPT message, the UE shall reset the registration attempt counter and service
    // request attempt counter, enter state 5GMM-REGISTERED and set the 5GS update status to 5U1 UPDATED."
    resetRegAttemptCounter();
    m_serCounter = 0;
    switchMmState(EMmSubState::MM_REGISTERED_NORMAL_SERVICE);
    switchUState(E5UState::U1_UPDATED);

    // "If the ACCEPT message included a T3512 value IE, the UE shall use the value in T3512 value IE as
    // periodic registration update timer (T3512). If the T3512 value IE is not included, the UE shall use the value
    // currently stored, e.g. from a prior REGISTRATION ACCEPT message."
    if (msg.t3512Value.has_value() && nas::utils::HasValue(*msg.t3512Value))
        m_timers->t3512.start(*msg.t3512Value);

    // Registration complete is sent conditionally
    bool sendComplete = false;

    // "If the REGISTRATION ACCEPT message contains a 5G-GUTI, the UE shall return a REGISTRATION COMPLETE message to
    // the AMF to acknowledge the received 5G-GUTI, stop timer T3519 if running, and delete any stored SUCI."
    if (msg.mobileIdentity.has_value())
    {
        if (msg.mobileIdentity->type == nas::EIdentityType::GUTI)
        {
            m_storage->storedSuci->clear();
            m_storage->storedGuti->set(*msg.mobileIdentity);
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

            auto &nssai = rejectedSlice.cause == nas::ERejectedSNssaiCause::NA_IN_PLMN ? m_storage->rejectedNssaiInPlmn
                                                                                       : m_storage->rejectedNssaiInTa;
            nssai->mutate([slice](auto &value) { value.addIfNotExists(slice); });
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
    m_storage->allowedNssai->set(nas::utils::NssaiTo(msg.allowedNSSAI.value_or(nas::IENssai{})));

    // Process configured NSSAI IE
    if (msg.configuredNSSAI.has_value())
    {
        m_storage->configuredNssai->set(nas::utils::NssaiTo(msg.configuredNSSAI.value_or(nas::IENssai{})));
        sendComplete = true;
    }

    // Store the network feature support
    if (msg.networkFeatureSupport)
    {
        if (m_nwFeatureSupport)
        {
            m_nwFeatureSupport->imsVoPs3gpp = msg.networkFeatureSupport->imsVoPs3gpp;
            m_nwFeatureSupport->imsVoPsN3gpp = msg.networkFeatureSupport->imsVoPsN3gpp;
            m_nwFeatureSupport->emc = msg.networkFeatureSupport->emc;
            m_nwFeatureSupport->emf = msg.networkFeatureSupport->emf;
            m_nwFeatureSupport->iwkN26 = msg.networkFeatureSupport->iwkN26;
            m_nwFeatureSupport->mpsi = msg.networkFeatureSupport->mpsi;

            if (msg.networkFeatureSupport->emcn3)
                m_nwFeatureSupport->emcn3 = msg.networkFeatureSupport->emcn3;
            if (msg.networkFeatureSupport->mcsi)
                m_nwFeatureSupport->mcsi = msg.networkFeatureSupport->mcsi;
        }
        else
        {
            m_nwFeatureSupport = msg.networkFeatureSupport;
        }
    }

    // The service request attempt counter shall be reset when registration procedure for mobility and periodic
    // registration update is successfully completed
    m_serCounter = 0;

    if (sendComplete)
        sendNasMessage(nas::RegistrationComplete{});

    auto regType = m_lastRegistrationRequest->registrationType.registrationType;
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

    if (regType == nas::ERegistrationType::INITIAL_REGISTRATION ||
        regType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
        receiveInitialRegistrationReject(msg);
    else
        receiveMobilityRegistrationReject(msg);

    // The RAND and RES* values stored in the ME shall be deleted and timer T3516, if running, shall be stopped
    m_usim->m_rand = {};
    m_usim->m_resStar = {};
    m_timers->t3516.stop();
}

void NasMm::receiveInitialRegistrationReject(const nas::RegistrationReject &msg)
{
    auto cause = msg.mmCause.value;
    auto regType = m_lastRegistrationRequest->registrationType.registrationType;

    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::FAILURE)
            receiveEapFailureMessage(*msg.eapMessage->eap);
        else
            m_logger->warn("Network sent EAP with type of %s in RegistrationReject, ignoring EAP IE.",
                           nas::utils::EnumToString(msg.eapMessage->eap->code));
    }

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

        handleAbnormalInitialRegFailure(regType);
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
            m_storage->storedGuti->clear();
            m_storage->lastVisitedRegisteredTai->clear();
            m_storage->taiList->clear();
            m_usim->m_currentNsCtx = {};
            m_usim->m_nonCurrentNsCtx = {};
        }

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED)
        {
            m_usim->invalidate();
        }

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED)
        {
            switchMmState(EMmSubState::MM_DEREGISTERED_PS);
        }

        if (cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA ||
            cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
        {
            switchMmState(EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE);
        }

        if (cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
        {
            switchMmState(EMmSubState::MM_NULL_PS);
            setN1Capability(false);
        }

        if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
        {
            switchMmState(EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
        }

        if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::TA_NOT_ALLOWED ||
            cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA || cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA ||
            cause == nas::EMmCause::N1_MODE_NOT_ALLOWED || cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
        {
            resetRegAttemptCounter();
        }

        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA)
        {
            m_storage->equivalentPlmnList->clear();
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

        if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
        {
            Plmn plmn = m_base->shCtx.getCurrentPlmn();
            if (plmn.hasValue())
                m_storage->forbiddenPlmnList->add(m_base->shCtx.getCurrentPlmn());
        }

        if (cause == nas::EMmCause::CONGESTION)
        {
            if (msg.t3346value.has_value() && nas::utils::HasValue(*msg.t3346value))
            {
                switchUState(E5UState::U2_NOT_UPDATED);
                switchMmState(EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);

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
            switchMmState(EMmSubState::MM_DEREGISTERED_NO_SUPI);
        else
        {
            // Spec says that upper layers should be informed as well, for additional action for emergency
            // registration, but no need for now.
            handleAbnormalCase();
        }
    }
}

void NasMm::receiveMobilityRegistrationReject(const nas::RegistrationReject &msg)
{
    auto cause = msg.mmCause.value;
    auto regType = m_lastRegistrationRequest->registrationType.registrationType;

    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::FAILURE)
            receiveEapFailureMessage(*msg.eapMessage->eap);
        else
            m_logger->warn("Network sent EAP with type of %s in RegistrationReject, ignoring EAP IE.",
                           nas::utils::EnumToString(msg.eapMessage->eap->code));
    }

    auto handleAbnormalCase = [this, regType, cause]() {
        m_logger->debug("Handling Registration Reject abnormal case");

        // Upon reception of the 5GMM causes #95, #96, #97, #99 and #111 the UE should set the registration attempt
        // counter to 5.
        int n = static_cast<int>(cause);
        if (n == 95 || n == 96 || n == 97 || n == 99 || n == 111)
            m_regCounter = 5;

        handleAbnormalMobilityRegFailure(regType);
    };

    if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
        cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED || cause == nas::EMmCause::PLMN_NOT_ALLOWED ||
        cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA ||
        cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA || cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
    {
        switchUState(E5UState::U3_ROAMING_NOT_ALLOWED);
    }

    if (cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED ||
        cause == nas::EMmCause::UE_IDENTITY_CANNOT_BE_DERIVED_FROM_NETWORK)
    {
        switchUState(E5UState::U2_NOT_UPDATED);
    }

    if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
        cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED || cause == nas::EMmCause::PLMN_NOT_ALLOWED ||
        cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA ||
        cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA || cause == nas::EMmCause::N1_MODE_NOT_ALLOWED ||
        cause == nas::EMmCause::UE_IDENTITY_CANNOT_BE_DERIVED_FROM_NETWORK)
    {
        m_storage->storedGuti->clear();
        m_storage->lastVisitedRegisteredTai->clear();
        m_storage->taiList->clear();
        m_usim->m_currentNsCtx = {};
        m_usim->m_nonCurrentNsCtx = {};
    }

    if (cause == nas::EMmCause::IMPLICITY_DEREGISTERED)
    {
        m_usim->m_currentNsCtx = {};
        m_usim->m_nonCurrentNsCtx = {};
    }

    if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
        cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED)
    {
        m_usim->invalidate();
    }

    if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
        cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED ||
        cause == nas::EMmCause::UE_IDENTITY_CANNOT_BE_DERIVED_FROM_NETWORK)
    {
        switchMmState(EMmSubState::MM_DEREGISTERED_PS);
    }

    if (cause == nas::EMmCause::IMPLICITY_DEREGISTERED)
    {
        switchMmState(EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE);
    }

    if (cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA ||
        cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
    {
        switchMmState(EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE);
    }

    if (cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
    {
        switchMmState(EMmSubState::MM_NULL_PS);
        setN1Capability(false);
    }

    if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
    {
        switchMmState(EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
    }

    if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::TA_NOT_ALLOWED ||
        cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA || cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA ||
        cause == nas::EMmCause::N1_MODE_NOT_ALLOWED || cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
    {
        resetRegAttemptCounter();
    }

    if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
        cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA)
    {
        m_storage->equivalentPlmnList->clear();
    }

    if (cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA || cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
    {
        Tai tai = m_base->shCtx.getCurrentTai();
        if (tai.hasValue())
        {
            m_storage->forbiddenTaiListRoaming->add(tai);
            m_storage->serviceAreaList->mutate(
                [&tai](auto &value) { nas::utils::RemoveFromServiceAreaList(value, nas::VTrackingAreaIdentity{tai}); });
        }
    }

    if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
    {
        Plmn plmn = m_base->shCtx.getCurrentPlmn();
        if (plmn.hasValue())
            m_storage->forbiddenPlmnList->add(plmn);
    }

    if (cause == nas::EMmCause::CONGESTION)
    {
        if (msg.t3346value.has_value() && nas::utils::HasValue(*msg.t3346value))
        {
            if (!hasEmergency())
            {
                switchUState(E5UState::U2_NOT_UPDATED);
                switchMmState(EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);
            }

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
        cause != nas::EMmCause::N1_MODE_NOT_ALLOWED && cause != nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED &&
        cause != nas::EMmCause::IMPLICITY_DEREGISTERED &&
        cause != nas::EMmCause::UE_IDENTITY_CANNOT_BE_DERIVED_FROM_NETWORK)
    {
        handleAbnormalCase();
    }

    if (hasEmergency())
    {
        // Spec says that upper layers should be informed as well, for additional action for emergency
        // registration, but no need for now.
        handleAbnormalCase();
    }
}

void NasMm::handleAbnormalInitialRegFailure(nas::ERegistrationType regType)
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
            switchMmState(EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);
        }
    }
    else
    {
        // The UE shall delete 5G-GUTI, TAI list, last visited TAI, list of equivalent PLMNs and ngKSI, ..
        m_storage->storedGuti->clear();
        m_storage->taiList->clear();
        m_storage->lastVisitedRegisteredTai->clear();
        m_storage->equivalentPlmnList->clear();
        m_usim->m_currentNsCtx = {};
        m_usim->m_nonCurrentNsCtx = {};

        // .. start timer T3502 ..
        m_timers->t3502.start();

        // .. and shall set the 5GS update status to 5U2 NOT UPDATED. The state is changed to
        // 5GMM-DEREGISTERED.ATTEMPTING-REGISTRATION or optionally to 5GMM-DEREGISTERED.PLMN-SEARCH in order to perform
        // a PLMN selection according to 3GPP TS 23.122 [5].
        switchUState(E5UState::U2_NOT_UPDATED);
        switchMmState(EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);
    }
}

void NasMm::handleAbnormalMobilityRegFailure(nas::ERegistrationType regType)
{
    // "Timer T3510 shall be stopped if still running"
    m_timers->t3510.stop();

    // "The registration attempt counter shall be incremented, unless it was already set to 5."
    if (m_regCounter != 5)
        m_regCounter++;

    // "If the registration attempt counter is less than 5:"
    if (m_regCounter < 5)
    {
        auto tai = m_base->shCtx.getCurrentTai();
        bool includedInTaiList =
            tai.hasValue() && nas::utils::TaiListContains(m_storage->taiList->get(), nas::VTrackingAreaIdentity{tai});

        // "If the TAI of the current serving cell is not included in the TAI list or the 5GS update status is different
        // to 5U1 UPDATED"
        if (!includedInTaiList || m_storage->uState->get() != E5UState::U1_UPDATED)
        {
            // "The UE shall start timer T3511, shall set the 5GS update status to 5U2 NOT UPDATED and change to state
            // 5GMM-REGISTERED.ATTEMPTING-REGISTRATION-UPDATE. When timer T3511 expires and the registration update
            // procedure is triggered again"
            m_timers->t3511.start(); // todo
            switchUState(E5UState::U2_NOT_UPDATED);
            switchMmState(EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE);
        }

        // "If the TAI of the current serving cell is included in the TAI list, the 5GS update status is equal to 5U1
        // UPDATED, and the UE is not performing the registration procedure after an inter-system change from S1 mode to
        // N1 mode"
        if (includedInTaiList && m_storage->uState->get() == E5UState::U1_UPDATED)
        {
            // "The UE shall keep the 5GS update status to 5U1 UPDATED and enter state 5GMM-REGISTERED.NORMAL-SERVICE."
            switchMmState(EMmSubState::MM_REGISTERED_NORMAL_SERVICE);
            // "The UE shall start timer T3511"
            m_timers->t3511.start();
        }
    }
    else
    {
        // "The UE shall start timer T3502, shall set the 5GS update status to 5U2 NOT UPDATED."
        m_timers->t3502.start();
        switchUState(E5UState::U2_NOT_UPDATED);

        // "The UE shall delete the list of equivalent PLMNs and shall change to state
        // 5GMM-REGISTERED.ATTEMPTING-REGISTRATION-UPDATE UPDATE"
        m_storage->equivalentPlmnList->clear();
        switchMmState(EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE);
    }
}

void NasMm::resetRegAttemptCounter()
{
    // "When the registration attempt counter is reset,
    // the UE shall stop timer T3519 if running, and delete any stored SUCI"
    m_regCounter = 0;
    m_timers->t3519.stop();
    m_storage->storedSuci->clear();
}

} // namespace nr::ue