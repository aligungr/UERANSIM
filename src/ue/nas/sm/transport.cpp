//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sm.hpp"

#include <stdexcept>

#include <lib/nas/utils.hpp>
#include <ue/nas/mm/mm.hpp>

namespace nr::ue
{

static ENasTransportHint MapMsgTypeToHint(nas::EMessageType msgType)
{
    switch (msgType)
    {
    case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_REQUEST:
        return ENasTransportHint::PDU_SESSION_ESTABLISHMENT_REQUEST;
    case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_ACCEPT:
        return ENasTransportHint::PDU_SESSION_ESTABLISHMENT_ACCEPT;
    case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_REJECT:
        return ENasTransportHint::PDU_SESSION_ESTABLISHMENT_REJECT;
    case nas::EMessageType::PDU_SESSION_AUTHENTICATION_COMMAND:
        return ENasTransportHint::PDU_SESSION_AUTHENTICATION_COMMAND;
    case nas::EMessageType::PDU_SESSION_AUTHENTICATION_COMPLETE:
        return ENasTransportHint::PDU_SESSION_AUTHENTICATION_COMPLETE;
    case nas::EMessageType::PDU_SESSION_AUTHENTICATION_RESULT:
        return ENasTransportHint::PDU_SESSION_AUTHENTICATION_RESULT;
    case nas::EMessageType::PDU_SESSION_MODIFICATION_REQUEST:
        return ENasTransportHint::PDU_SESSION_MODIFICATION_REQUEST;
    case nas::EMessageType::PDU_SESSION_MODIFICATION_REJECT:
        return ENasTransportHint::PDU_SESSION_MODIFICATION_REJECT;
    case nas::EMessageType::PDU_SESSION_MODIFICATION_COMMAND:
        return ENasTransportHint::PDU_SESSION_MODIFICATION_COMMAND;
    case nas::EMessageType::PDU_SESSION_MODIFICATION_COMPLETE:
        return ENasTransportHint::PDU_SESSION_MODIFICATION_COMPLETE;
    case nas::EMessageType::PDU_SESSION_MODIFICATION_COMMAND_REJECT:
        return ENasTransportHint::PDU_SESSION_MODIFICATION_COMMAND_REJECT;
    case nas::EMessageType::PDU_SESSION_RELEASE_REQUEST:
        return ENasTransportHint::PDU_SESSION_RELEASE_REQUEST;
    case nas::EMessageType::PDU_SESSION_RELEASE_REJECT:
        return ENasTransportHint::PDU_SESSION_RELEASE_REJECT;
    case nas::EMessageType::PDU_SESSION_RELEASE_COMMAND:
        return ENasTransportHint::PDU_SESSION_RELEASE_COMMAND;
    case nas::EMessageType::PDU_SESSION_RELEASE_COMPLETE:
        return ENasTransportHint::PDU_SESSION_RELEASE_COMPLETE;
    case nas::EMessageType::FIVEG_SM_STATUS:
        return ENasTransportHint::FIVEG_SM_STATUS;
    default:
        throw std::runtime_error("failure in MapMsgTypeToHint");
    }
}

void NasSm::sendSmMessage(int psi, const nas::SmMessage &msg)
{
    auto &session = m_pduSessions[psi];

    nas::UlNasTransport m;
    m.payloadContainerType.payloadContainerType = nas::EPayloadContainerType::N1_SM_INFORMATION;
    nas::EncodeNasMessage(msg, m.payloadContainer.data);
    m.pduSessionId = nas::IEPduSessionIdentity2{};
    m.pduSessionId->value = psi;

    if (msg.messageType == nas::EMessageType::PDU_SESSION_ESTABLISHMENT_REQUEST ||
        msg.messageType == nas::EMessageType::PDU_SESSION_MODIFICATION_REQUEST)
    {
        m.requestType = nas::IERequestType{};
        m.requestType->requestType =
            session->isEmergency ? nas::ERequestType::INITIAL_EMERGENCY_REQUEST : nas::ERequestType::INITIAL_REQUEST;

        if (!session->isEmergency)
        {
            if (session->sNssai.has_value())
                m.sNssai = nas::utils::SNssaiFrom(*session->sNssai);

            if (session->apn.has_value())
                m.dnn = nas::utils::DnnFromApn(*session->apn);
        }
    }

    m_mm->deliverUlTransport(m, MapMsgTypeToHint(msg.messageType));
}

void NasSm::receiveSmMessage(const nas::SmMessage &msg)
{
    switch (msg.messageType)
    {
    case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_ACCEPT:
        receiveEstablishmentAccept((const nas::PduSessionEstablishmentAccept &)msg);
        break;
    case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_REJECT:
        receiveEstablishmentReject((const nas::PduSessionEstablishmentReject &)msg);
        break;
    case nas::EMessageType::PDU_SESSION_RELEASE_REJECT:
        receiveReleaseReject((const nas::PduSessionReleaseReject &)msg);
        break;
    case nas::EMessageType::PDU_SESSION_RELEASE_COMMAND:
        receiveReleaseCommand((const nas::PduSessionReleaseCommand &)msg);
        break;
    case nas::EMessageType::FIVEG_SM_STATUS:
        receiveSmStatus((const nas::FiveGSmStatus &)msg);
        break;
    default:
        m_logger->err("Unhandled NAS SM message received: %d", (int)msg.messageType);
        break;
    }
}

void NasSm::receiveSmStatus(const nas::FiveGSmStatus &msg)
{
    m_logger->err("SM Status received with cause [%s]", nas::utils::EnumToString(msg.smCause.value));

    if (msg.smCause.value == nas::ESmCause::INVALID_PTI_VALUE)
    {
        // "The UE shall abort any ongoing 5GSM procedure related to the received PTI value and stop any related timer."
        abortProcedureByPti(msg.pti);
    }
    else if (msg.smCause.value == nas::ESmCause::MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED)
    {
        // "The UE shall abort any ongoing 5GSM procedure related to the PTI or PDU session Id and stop any related
        // timer."
        abortProcedureByPtiOrPsi(msg.pti, msg.pduSessionId);
    }
}

void NasSm::sendSmCause(const nas::ESmCause &cause, int pti, int psi)
{
    m_logger->warn("Sending SM Cause[%s] for PSI[%d]", nas::utils::EnumToString(cause), psi);

    nas::FiveGSmStatus smStatus;
    smStatus.smCause.value = cause;
    smStatus.pti = pti;
    smStatus.pduSessionId = psi;

    nas::UlNasTransport ulTransport;
    ulTransport.payloadContainerType.payloadContainerType = nas::EPayloadContainerType::N1_SM_INFORMATION;
    nas::EncodeNasMessage(smStatus, ulTransport.payloadContainer.data);
    ulTransport.pduSessionId = nas::IEPduSessionIdentity2{};
    ulTransport.pduSessionId->value = psi;

    m_mm->deliverUlTransport(ulTransport, ENasTransportHint::FIVEG_SM_STATUS);
}

void NasSm::receiveForwardingFailure(const nas::SmMessage &msg, nas::EMmCause cause,
                                     const std::optional<nas::IEGprsTimer3> &backoffTimer)
{
    // TODO: other actions such as congestion control etc

    m_logger->err("SM forwarding failure for message type[%d] with cause[%s]", static_cast<int>(msg.messageType),
                  nas::utils::EnumToString(cause));

    if (!checkPtiAndPsi(msg))
        return;

    abortProcedureByPti(msg.pti);
}

} // namespace nr::ue
