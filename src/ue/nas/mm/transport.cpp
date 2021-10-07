//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <lib/nas/utils.hpp>
#include <ue/nas/enc.hpp>
#include <ue/nas/sm/sm.hpp>
#include <ue/rrc/task.hpp>

#include <asn/rrc/ASN_RRC_EstablishmentCause.h>

namespace nr::ue
{

static bool IsUplinkSmMessage(nas::EMessageType type)
{
    switch (type)
    {
    case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_REQUEST:
    case nas::EMessageType::PDU_SESSION_AUTHENTICATION_COMPLETE:
    case nas::EMessageType::PDU_SESSION_MODIFICATION_REQUEST:
    case nas::EMessageType::PDU_SESSION_MODIFICATION_COMPLETE:
    case nas::EMessageType::PDU_SESSION_MODIFICATION_COMMAND_REJECT:
    case nas::EMessageType::PDU_SESSION_RELEASE_REQUEST:
    case nas::EMessageType::PDU_SESSION_RELEASE_COMPLETE:
        return true;
    default:
        return false;
    }
}

void NasMm::receiveDlNasTransport(const nas::DlNasTransport &msg)
{
    m_timers->t3346.stop();

    if (msg.payloadContainerType.payloadContainerType != nas::EPayloadContainerType::N1_SM_INFORMATION)
    {
        m_logger->err("Unhandled DL NAS Transport payload container type [%d]",
                      (int)msg.payloadContainerType.payloadContainerType);
        return;
    }

    if (msg.payloadContainer.data.length() == 0)
    {
        m_logger->err("Empty payload container received with type[%d]",
                      (int)msg.payloadContainerType.payloadContainerType);
        return;
    }

    OctetView buff{msg.payloadContainer.data.data(), static_cast<size_t>(msg.payloadContainer.data.length())};
    auto nasMessage = nas::DecodeNasMessage(buff);

    if (nasMessage->epd != nas::EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES)
    {
        m_logger->err("Bad payload container in DL NAS Transport, ignoring received message");
        return;
    }

    auto &smMessage = ((nas::SmMessage &)*nasMessage);

    if (msg.pduSessionId && (int)msg.pduSessionId->value != smMessage.pduSessionId)
    {
        m_logger->err("PSI mismatch in DL NAS Transport, ignoring received message");
        return;
    }

    if (msg.mmCause)
    {
        if (!IsUplinkSmMessage(smMessage.messageType))
        {
            m_logger->err("MM Cause with invalid SM message type in DL NAS Transport, ignoring received message");
            return;
        }

        switch (msg.mmCause->value)
        {
        case nas::EMmCause::PAYLOAD_NOT_FORWARDED:
        case nas::EMmCause::DNN_NOT_SUPPORTED_OR_NOT_SUBSCRIBED:
        case nas::EMmCause::MAX_PDU_SESSIONS_REACHED: {
            m_sm->receiveForwardingFailure(smMessage, msg.mmCause->value, std::nullopt);
            break;
        }
        case nas::EMmCause::RESTRICTED_SERVICE_AREA: {
            if (m_rmState == ERmState::RM_REGISTERED)
            {
                switchMmState(EMmSubState::MM_REGISTERED_NON_ALLOWED_SERVICE);
                mobilityUpdatingRequired(ERegUpdateCause::RESTRICTED_SERVICE_AREA);
            }
            m_sm->receiveForwardingFailure(smMessage, msg.mmCause->value, std::nullopt);
            break;
        }
        case nas::EMmCause::CONGESTION:
        case nas::EMmCause::INSUFFICIENT_RESOURCES_FOR_SLICE_AND_DNN:
        case nas::EMmCause::INSUFFICIENT_RESOURCES_FOR_SLICE: {
            m_sm->receiveForwardingFailure(smMessage, msg.mmCause->value, msg.backOffTimerValue);
            break;
        }
        default: {
            m_logger->warn("Unhandled MM Cause [%d] in DL NAS Transport", static_cast<int>(msg.mmCause->value));
            m_sm->receiveForwardingFailure(smMessage, msg.mmCause->value, std::nullopt);
            break;
        }
        }
    }
    else
    {
        m_sm->receiveSmMessage(smMessage);
    }
}

EProcRc NasMm::deliverUlTransport(const nas::UlNasTransport &msg, ENasTransportHint hint)
{
    // 5.4.5.2.6 Abnormal cases in the UE
    // "The UE shall not send the UL NAS TRANSPORT message when the UE is in non-allowed area or
    // is not in allowed area and .."
    if (isInNonAllowedArea() && !isHighPriority())
    {
        // "1) the Payload container type IE is set to "N1 SM information", the Request type IE is set to "initial
        // request", "existing PDU session" or "modification request" and the UE is not configured for high priority
        // access in selected PLMN;" or
        // "2) the Payload container type IE is set to "SMS" and the UE is not configured for high priority access in
        // selected PLMN."
        if ((msg.payloadContainerType.payloadContainerType == nas::EPayloadContainerType::N1_SM_INFORMATION &&
             (msg.requestType && (msg.requestType->requestType == nas::ERequestType::INITIAL_REQUEST ||
                                  msg.requestType->requestType == nas::ERequestType::EXISTING_PDU_SESSION ||
                                  msg.requestType->requestType == nas::ERequestType::MODIFICATION_REQUEST))) ||
            msg.payloadContainerType.payloadContainerType == nas::EPayloadContainerType::SMS)
        {
            m_logger->err("Ul Nas Transport procedure canceled, UE is not in allowed area");
            return EProcRc::STAY;
        }
    }

    // Perform UAC for PDU session establishment and modification
    if (hint == ENasTransportHint::PDU_SESSION_ESTABLISHMENT_REQUEST ||
        hint == ENasTransportHint::PDU_SESSION_MODIFICATION_REQUEST)
    {
        if (performUac() != EUacResult::ALLOWED)
        {
            return EProcRc::STAY;
        }
    }

    // Send the UL NAS Transport Message
    auto rc = sendNasMessage(msg);
    if (rc != EProcRc::OK)
        return rc;

    return EProcRc::OK;
}

} // namespace nr::ue
