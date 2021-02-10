//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sm.hpp"
#include <ue/mm/mm.hpp>
#include <nas/utils.hpp>

namespace nr::ue
{

void NasSm::sendSmMessage(int psi, const nas::SmMessage &msg)
{
    auto &session = m_pduSessions[psi];

    nas::UlNasTransport m{};
    m.payloadContainerType.payloadContainerType = nas::EPayloadContainerType::N1_SM_INFORMATION;
    nas::EncodeNasMessage(msg, m.payloadContainer.data);
    m.pduSessionId = nas::IEPduSessionIdentity2{};
    m.pduSessionId->value = psi;
    m.requestType = nas::IERequestType{};
    m.requestType->requestType = nas::ERequestType::INITIAL_REQUEST; // TODO

    if (session.sNssai.has_value())
        m.sNssa = nas::utils::SNssaiFrom(*session.sNssai);

    if (session.apn.has_value())
        m.dnn = nas::utils::DnnFromApn(*session.apn);

    m_mm->sendNasMessage(m);
}

void NasSm::receiveSmMessage(const nas::SmMessage &msg)
{
    // TODO: trigger on receive

    switch (msg.messageType)
    {
    case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_ACCEPT:
        receivePduSessionEstablishmentAccept((const nas::PduSessionEstablishmentAccept &)msg);
        break;
    case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_REJECT:
        receivePduSessionEstablishmentReject((const nas::PduSessionEstablishmentReject &)msg);
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
    receiveSmCause(msg.smCause);
}

void NasSm::receiveSmCause(const nas::IE5gSmCause &msg)
{
    m_logger->err("SM cause received: %s", nas::utils::EnumToString(msg.value));
}

} // namespace nr::ue