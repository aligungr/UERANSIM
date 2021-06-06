//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <lib/nas/utils.hpp>
#include <ue/nas/sm/sm.hpp>

namespace nr::ue
{

EProcRc NasMm::sendServiceRequest(EServiceReqCause reqCause)
{
    // "The procedure shall only be initiated by the UE when the following conditions are fulfilled ..."
    if (m_mmState == EMmState::MM_REGISTERED_INITIATED || m_mmState == EMmState::MM_DEREGISTERED_INITIATED)
    {
        m_logger->warn("Service Request canceled, MM specific procedure is ongoing");
        return EProcRc::STAY;
    }
    if (m_mmState == EMmState::MM_SERVICE_REQUEST_INITIATED)
    {
        m_logger->debug("Service Request canceled, already in 5GMM-SERVICE-REQUEST-INITIATED");
        return EProcRc::CANCEL;
    }
    if (m_storage->uState->get() != E5UState::U1_UPDATED)
    {
        m_logger->err("Service Request canceled, UE not in 5U1 UPDATED state");
        return EProcRc::STAY;
    }
    Tai currentTai = m_base->shCtx.getCurrentTai();
    if (!currentTai.hasValue())
    {
        m_logger->err("Service Request canceled, no active cell exists");
        return EProcRc::STAY;
    }
    if (!nas::utils::TaiListContains(m_storage->taiList->get(), nas::VTrackingAreaIdentity{currentTai}))
    {
        m_logger->err("Service Request canceled, current TAI is not in the TAI list");
        return EProcRc::CANCEL;
    }

    if (m_mmSubState == EMmSubState::MM_REGISTERED_NON_ALLOWED_SERVICE)
    {
        if (reqCause != EServiceReqCause::IDLE_PAGING &&
            reqCause != EServiceReqCause::CONNECTED_3GPP_NOTIFICATION_N3GPP &&
            reqCause != EServiceReqCause::IDLE_3GPP_NOTIFICATION_N3GPP && !isHighPriority() && !hasEmergency())
        {
            m_logger->debug("Service Request canceled, registered in non allowed service");
            return EProcRc::CANCEL;
        }
    }

    if (m_mmSubState == EMmSubState::MM_REGISTERED_UPDATE_NEEDED)
    {
        if (reqCause != EServiceReqCause::IDLE_PAGING &&
            reqCause != EServiceReqCause::CONNECTED_3GPP_NOTIFICATION_N3GPP &&
            reqCause != EServiceReqCause::IDLE_3GPP_NOTIFICATION_N3GPP)
        {
            return EProcRc::STAY;
        }
    }

    // 5.6.1.7 Abnormal cases in the UE
    // a) Timer T3525
    if (m_timers->t3525.isRunning())
    {
        if (reqCause != EServiceReqCause::IDLE_PAGING &&
            reqCause != EServiceReqCause::CONNECTED_3GPP_NOTIFICATION_N3GPP &&
            reqCause != EServiceReqCause::IDLE_3GPP_NOTIFICATION_N3GPP &&
            reqCause != EServiceReqCause::EMERGENCY_FALLBACK && !isHighPriority() && !hasEmergency())
        {
            m_logger->debug("Service Request canceled, T3346 is running");
            return EProcRc::STAY;
        }
    }
    // c) Timer T3346 is running.
    if (m_timers->t3346.isRunning())
    {
        if (reqCause != EServiceReqCause::IDLE_PAGING &&
            reqCause != EServiceReqCause::CONNECTED_3GPP_NOTIFICATION_N3GPP &&
            reqCause != EServiceReqCause::IDLE_3GPP_NOTIFICATION_N3GPP && !isHighPriority() && !hasEmergency() &&
            reqCause != EServiceReqCause::EMERGENCY_FALLBACK)
        {
            m_logger->debug("Service Request canceled, T3346 is running");
            return EProcRc::STAY;
        }
    }

    // Perform Unified Access Control
    if (performUac() != EUacResult::ALLOWED)
    {
        return EProcRc::STAY;
    }

    m_logger->debug("Sending Service Request due to [%s]", ToJson(reqCause).str().c_str());

    updateProvidedGuti();

    auto request = std::make_unique<nas::ServiceRequest>();

    if (reqCause == EServiceReqCause::IDLE_PAGING)
    {
        if (m_sm->anyUplinkDataPending())
        {
            request->uplinkDataStatus = nas::IEUplinkDataStatus{};
            request->uplinkDataStatus->psi = m_sm->getUplinkDataStatus();
        }
        request->serviceType.serviceType = nas::EServiceType::MOBILE_TERMINATED_SERVICES;
    }
    else if (reqCause == EServiceReqCause::CONNECTED_3GPP_NOTIFICATION_N3GPP)
    {
        // TODO: This case not handled since the non-3gpp access is not supported
        m_logger->err("Unhandled case in ServiceRequest");
    }
    else if (reqCause == EServiceReqCause::IDLE_UPLINK_SIGNAL_PENDING)
    {
        if (isHighPriority())
            request->serviceType.serviceType = nas::EServiceType::HIGH_PRIORITY_ACCESS;
        else if (hasEmergency())
            request->serviceType.serviceType = nas::EServiceType::EMERGENCY_SERVICES;
        else
            request->serviceType.serviceType = nas::EServiceType::SIGNALLING;

        if (isInNonAllowedArea())
        {
            // TODO: This case not handled since PS data off not supported
            m_logger->err("Unhandled case in ServiceRequest");
        }
    }
    else if (reqCause == EServiceReqCause::IDLE_UPLINK_DATA_PENDING ||
             reqCause == EServiceReqCause::CONNECTED_UPLINK_DATA_PENDING)
    {
        request->uplinkDataStatus = nas::IEUplinkDataStatus{};
        request->uplinkDataStatus->psi = m_sm->getUplinkDataStatus();

        if (isHighPriority())
            request->serviceType.serviceType = nas::EServiceType::HIGH_PRIORITY_ACCESS;
        else if (m_sm->anyEmergencyUplinkDataPending())
            request->serviceType.serviceType = nas::EServiceType::EMERGENCY_SERVICES;
        else
            request->serviceType.serviceType = nas::EServiceType::DATA;
    }
    else if (reqCause == EServiceReqCause::NON_3GPP_AS_ESTABLISHED)
    {
        // TODO: This case not handled since the non-3gpp access is not supported
        m_logger->err("Unhandled case found in ServiceRequest");
    }
    else if (reqCause == EServiceReqCause::IDLE_3GPP_NOTIFICATION_N3GPP)
    {
        // TODO: This case not handled since the non-3gpp access is not supported
        m_logger->err("Unhandled case occurred in ServiceRequest");
    }
    else if (reqCause == EServiceReqCause::EMERGENCY_FALLBACK)
    {
        request->serviceType.serviceType = nas::EServiceType::EMERGENCY_SERVICES_FALLBACK;
    }
    else if (reqCause == EServiceReqCause::FALLBACK_INDICATION)
    {
        if (isHighPriority())
            request->serviceType.serviceType = nas::EServiceType::HIGH_PRIORITY_ACCESS;
        else
        {
            // From 5.6.1.2:
            //  "a) if the pending message is an UL NAS TRANSPORT message with the Request type IE set to "initial
            //  emergency request" or "existing emergency PDU session", the UE shall set the Service type IE in the
            //  SERVICE REQUEST message to "emergency services"; or
            //  b) otherwise, the UE shall set the Service type IE in the SERVICE REQUEST message to "signalling"."
            // Just check if the UE has an emergency
            request->serviceType.serviceType =
                hasEmergency() ? nas::EServiceType::EMERGENCY_SERVICES : nas::EServiceType::SIGNALLING;
        }
    }

    // Assign ngKSI
    if (m_usim->m_currentNsCtx)
    {
        request->ngKSI.tsc = m_usim->m_currentNsCtx->tsc;
        request->ngKSI.ksi = m_usim->m_currentNsCtx->ngKsi;
    }

    // Assign TMSI (TMSI is a part of GUTI)
    request->tmsi = m_storage->storedGuti->get();
    if (request->tmsi.type != nas::EIdentityType::NO_IDENTITY)
    {
        request->tmsi.type = nas::EIdentityType::TMSI;
        request->tmsi.gutiOrTmsi.plmn = {};
        request->tmsi.gutiOrTmsi.amfRegionId = {};
    }

    // Assign PDU session status
    request->pduSessionStatus = nas::IEPduSessionStatus{};
    request->pduSessionStatus->psi = m_sm->getPduSessionStatus();

    // Send the message and process the timers
    auto rc = sendNasMessage(*request);
    if (rc != EProcRc::OK)
        return rc;
    m_lastServiceRequest = std::move(request);
    m_lastServiceReqCause = reqCause;
    m_timers->t3517.start();

    switchMmState(EMmSubState::MM_SERVICE_REQUEST_INITIATED_PS);

    return EProcRc::OK;
}

void NasMm::receiveServiceAccept(const nas::ServiceAccept &msg)
{
    if (m_mmState != EMmState::MM_SERVICE_REQUEST_INITIATED)
    {
        m_logger->warn("Service Accept ignored since the MM state is not MM_SERVICE_REQUEST_INITIATED");
        sendMmStatus(nas::EMmCause::MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE);
        return;
    }

    if (m_lastServiceReqCause != EServiceReqCause::EMERGENCY_FALLBACK)
    {
        m_logger->info("Service Accept received");
        m_serCounter = 0;
        m_timers->t3517.stop();
        switchMmState(EMmSubState::MM_REGISTERED_PS);
    }
    else
    {
        // todo: emergency fallback
    }

    // Handle PDU session status
    if (msg.pduSessionStatus.has_value())
    {
        auto statusInUe = m_sm->getPduSessionStatus();
        auto statusInNw = msg.pduSessionStatus->psi;
        for (int i = 1; i < 16; i++)
            if (statusInUe[i] && !statusInNw[i])
                m_sm->localReleaseSession(i);
    }

    // Handle PDU session reactivation result
    if (msg.pduSessionReactivationResult.has_value())
    {
        // todo: not handled since non-3gpp access is not supported
    }

    // Handle PDU session reactivation result error cause
    if (msg.pduSessionReactivationResultErrorCause.has_value())
    {
        for (auto &item : msg.pduSessionReactivationResultErrorCause->values)
            m_logger->err("PDU session reactivation result error PSI[%d] cause[%s]", item.pduSessionId,
                          nas::utils::EnumToString(item.causeValue));
    }

    // Handle EAP message
    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::FAILURE)
            receiveEapFailureMessage(*msg.eapMessage->eap);
        else
            m_logger->warn("Network sent EAP with inconvenient type in ServiceAccept, ignoring EAP IE.");
    }
}

void NasMm::receiveServiceReject(const nas::ServiceReject &msg)
{
    if (m_mmState != EMmState::MM_SERVICE_REQUEST_INITIATED || !m_lastServiceRequest)
    {
        m_logger->warn("Service Reject ignored since the MM state is not MM_SERVICE_REQUEST_INITIATED");
        sendMmStatus(nas::EMmCause::MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE);
        return;
    }

    if (msg.sht == nas::ESecurityHeaderType::NOT_PROTECTED)
        m_logger->warn("Not protected Service Reject message received");

    // "On receipt of the SERVICE REJECT message, if the UE is in state 5GMM-SERVICE-REQUEST-INITIATED and the message
    // is integrity protected, the UE shall reset the service request attempt counter and stop timer T3517 if running."
    m_serCounter = 0;
    m_timers->t3517.stop();

    // The RAND and RES* values stored in the ME shall be deleted and timer T3516, if running, shall be stopped
    m_usim->m_rand = {};
    m_usim->m_resStar = {};
    m_timers->t3516.stop();

    auto cause = msg.mmCause.value;
    m_logger->err("Service Reject received with cause [%s]", nas::utils::EnumToString(cause));

    auto handleAbnormalCase = [this]() {
        m_logger->debug("Handling Service Reject abnormal case");

        switchMmState(EMmSubState::MM_REGISTERED_PS);
        m_timers->t3517.stop();
    };

    // Handle PDU session status
    if (msg.pduSessionStatus.has_value() && msg.sht != nas::ESecurityHeaderType::NOT_PROTECTED)
    {
        auto statusInUe = m_sm->getPduSessionStatus();
        auto statusInNw = msg.pduSessionStatus->psi;
        for (int i = 1; i < 16; i++)
            if (statusInUe[i] && !statusInNw[i])
                m_sm->localReleaseSession(i);
    }

    // Handle EAP message
    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::FAILURE)
            receiveEapFailureMessage(*msg.eapMessage->eap);
        else
            m_logger->warn("Network sent EAP with inconvenient type in ServiceReject, ignoring EAP IE.");
    }

    /* Handle MM Cause */

    if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
        cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED || cause == nas::EMmCause::PLMN_NOT_ALLOWED ||
        cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA ||
        cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA || cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
        switchUState(E5UState::U3_ROAMING_NOT_ALLOWED);

    if (cause == nas::EMmCause::UE_IDENTITY_CANNOT_BE_DERIVED_FROM_NETWORK ||
        cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
        switchUState(E5UState::U2_NOT_UPDATED);

    if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
        cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED ||
        cause == nas::EMmCause::UE_IDENTITY_CANNOT_BE_DERIVED_FROM_NETWORK ||
        cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::TA_NOT_ALLOWED ||
        cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
    {
        m_storage->storedGuti->clear();
        m_storage->lastVisitedRegisteredTai->clear();
        m_storage->taiList->clear();
        m_usim->m_currentNsCtx = {};
        m_usim->m_nonCurrentNsCtx = {};
    }

    if (cause == nas::EMmCause::IMPLICITY_DEREGISTERED)
    {
        m_usim->m_nonCurrentNsCtx = {};
    }

    if (cause == nas::EMmCause::PLMN_NOT_ALLOWED)
    {
        m_storage->equivalentPlmnList->clear();
    }

    if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
    {
        Plmn plmn = m_base->shCtx.getCurrentPlmn();
        if (plmn.hasValue())
            m_storage->forbiddenPlmnList->add(plmn);
    }

    if (cause == nas::EMmCause::TA_NOT_ALLOWED)
    {
        Tai tai = m_base->shCtx.getCurrentTai();
        if (tai.hasValue())
        {
            m_storage->forbiddenTaiListRps->add(tai);
            m_storage->serviceAreaList->mutate(
                [&tai](auto &value) { nas::utils::RemoveFromServiceAreaList(value, nas::VTrackingAreaIdentity{tai}); });
        }
    }

    if (cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA || cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
    {
        Tai tai = m_base->shCtx.getCurrentTai();
        if (tai.hasValue())
        {
            m_storage->forbiddenTaiListRoaming->add(tai);
            m_storage->taiList->mutate(
                [&tai](auto &value) { nas::utils::RemoveFromTaiList(value, nas::VTrackingAreaIdentity{tai}); });
        }
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

    if (cause == nas::EMmCause::PLMN_NOT_ALLOWED || cause == nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
    {
        switchMmState(EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
    }

    if (cause == nas::EMmCause::TA_NOT_ALLOWED)
    {
        switchMmState(EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE);
    }

    if (cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA)
    {
        switchMmState(EMmSubState::MM_REGISTERED_PLMN_SEARCH);
    }

    if (cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
    {
        switchMmState(EMmSubState::MM_REGISTERED_LIMITED_SERVICE);
    }

    if (cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
    {
        switchMmState(EMmSubState::MM_NULL_PS);
        setN1Capability(false);
    }

    if (cause == nas::EMmCause::UE_IDENTITY_CANNOT_BE_DERIVED_FROM_NETWORK)
    {
        if (m_lastServiceReqCause != EServiceReqCause::EMERGENCY_FALLBACK)
            initialRegistrationRequired(EInitialRegCause::DUE_TO_SERVICE_REJECT);
    }

    if (cause == nas::EMmCause::IMPLICITY_DEREGISTERED)
    {
        if (!hasEmergency())
            initialRegistrationRequired(EInitialRegCause::DUE_TO_SERVICE_REJECT);
    }

    if (cause == nas::EMmCause::CONGESTION)
    {
        if (msg.t3346Value.has_value() && nas::utils::HasValue(*msg.t3346Value))
        {
            if (!hasEmergency())
            {
                switchMmState(EMmSubState::MM_REGISTERED_PS);

                m_timers->t3517.stop();
            }

            m_timers->t3346.stop();

            if (msg.sht != nas::ESecurityHeaderType::NOT_PROTECTED)
                m_timers->t3346.start(*msg.t3346Value);
            else
                m_timers->t3346.start(nas::IEGprsTimer2{5});
        }
        else
        {
            handleAbnormalCase();
        }
    }

    if (cause == nas::EMmCause::RESTRICTED_SERVICE_AREA)
    {
        switchMmState(EMmSubState::MM_REGISTERED_NON_ALLOWED_SERVICE);

        if (m_lastServiceRequest->serviceType.serviceType != nas::EServiceType::ELEVATED_SIGNALLING)
            mobilityUpdatingRequired(ERegUpdateCause::RESTRICTED_SERVICE_AREA);
    }

    if (hasEmergency())
    {
        // Spec says that upper layers should be informed as well, for additional action for emergency
        // registration, but no need for now.
        handleAbnormalCase();
    }

    if (cause != nas::EMmCause::ILLEGAL_UE && cause != nas::EMmCause::ILLEGAL_ME &&
        cause != nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED &&
        cause != nas::EMmCause::UE_IDENTITY_CANNOT_BE_DERIVED_FROM_NETWORK &&
        cause != nas::EMmCause::IMPLICITY_DEREGISTERED && cause != nas::EMmCause::PLMN_NOT_ALLOWED &&
        cause != nas::EMmCause::TA_NOT_ALLOWED && cause != nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA &&
        cause != nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA && cause != nas::EMmCause::CONGESTION &&
        cause != nas::EMmCause::N1_MODE_NOT_ALLOWED && cause != nas::EMmCause::RESTRICTED_SERVICE_AREA &&
        cause != nas::EMmCause::SERVING_NETWORK_NOT_AUTHORIZED)
    {
        handleAbnormalCase();
    }
}

} // namespace nr::ue
