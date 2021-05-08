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

void NasMm::receiveDlNasTransport(const nas::DlNasTransport &msg)
{
    if (msg.payloadContainerType.payloadContainerType != nas::EPayloadContainerType::N1_SM_INFORMATION)
    {
        m_logger->err("Unhandled DL NAS Transport type [%d]", (int)msg.payloadContainerType.payloadContainerType);
        return;
    }

    OctetView buff{msg.payloadContainer.data.data(), static_cast<size_t>(msg.payloadContainer.data.length())};
    auto sm = nas::DecodeNasMessage(buff);
    if (sm->epd != nas::EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES)
    {
        m_logger->err("Bad payload container in DL NAS Transport");
        return;
    }

    m_sm->receiveSmMessage((const nas::SmMessage &)(*sm));
}

void NasMm::deliverUlTransport(const nas::UlNasTransport &msg)
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
            return;
        }
    }

    // Send the UL NAS Transport Message
    sendNasMessage(msg);
}

} // namespace nr::ue
