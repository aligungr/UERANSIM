//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"
#include <nas/utils.hpp>
#include <ue/nas/sm/sm.hpp>

namespace nr::ue
{

void NasMm::sendServiceRequest(EServiceReqCause reqCause)
{
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
            // TODO: fallback indication not supported yet
        }
    }

    // Assign ngKSI
    if (m_usim->m_currentNsCtx)
    {
        request->ngKSI.tsc = m_usim->m_currentNsCtx->tsc;
        request->ngKSI.ksi = m_usim->m_currentNsCtx->ngKsi;
    }

    // Assign TMSI (TMSI is a part of GUTI)
    request->tmsi = m_usim->m_storedGuti;
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
    sendNasMessage(*request);
    m_lastServiceRequest = std::move(request);
    m_lastServiceReqCause = reqCause;
    m_timers->t3517.start();
    switchMmState(EMmState::MM_SERVICE_REQUEST_INITIATED, EMmSubState::MM_SERVICE_REQUEST_INITIATED_NA);
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
        switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_NA);
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
    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::FAILURE)
            receiveEapFailureMessage(*msg.eapMessage->eap);
        else
            m_logger->warn("Network sent EAP with inconvenient type in ServiceAccept, ignoring EAP IE.");
    }

    // TODO
}

} // namespace nr::ue
