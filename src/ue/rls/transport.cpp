//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <ue/app/task.hpp>
#include <ue/nts.hpp>
#include <ue/rrc/task.hpp>
#include <utils/constants.hpp>

namespace nr::ue
{

void UeRlsTask::receiveRlsMessage(const InetAddress &address, rls::RlsMessage &msg)
{
    switch (msg.msgType)
    {
    case rls::EMessageType::CELL_INFO_RESPONSE: {
        receiveCellInfoResponse((const rls::RlsCellInfoResponse &)msg);
        break;
    case rls::EMessageType::PDU_DELIVERY: {
        deliverDownlinkPdu((rls::RlsPduDelivery &)msg);
        break;
    }
    default:
        m_logger->err("Unhandled RLS message type[%d]", static_cast<int>(msg.msgType));
        break;
    }
    }
}

void UeRlsTask::sendRlsMessage(const InetAddress &address, const rls::RlsMessage &msg)
{
    OctetString stream{};
    rls::EncodeRlsMessage(msg, stream);
    m_udpTask->send(address, stream);
}

void UeRlsTask::deliverUplinkPdu(rls::EPduType pduType, OctetString &&pdu, OctetString &&payload)
{
    if (!m_servingCell.has_value())
    {
        m_logger->warn("RLS uplink delivery requested without a serving cell");
        return;
    }

    rls::RlsPduDelivery msg{m_sti};
    msg.pduType = pduType;
    msg.pdu = std::move(pdu);
    msg.payload = std::move(payload);
    sendRlsMessage(InetAddress{m_servingCell->linkIp, cons::PortalPort}, msg);
}

void UeRlsTask::deliverDownlinkPdu(rls::RlsPduDelivery &msg)
{
    if (msg.pduType == rls::EPduType::RRC)
    {
        auto *nw = new NwUeRlsToRrc(NwUeRlsToRrc::RRC_PDU_DELIVERY);
        nw->channel = static_cast<rrc::RrcChannel>(msg.payload.get4I(0));
        nw->pdu = std::move(msg.pdu);
        m_base->rrcTask->push(nw);
    }
    else if (msg.pduType == rls::EPduType::DATA)
    {
        auto *nw = new NwUeRlsToApp(NwUeRlsToApp::DATA_PDU_DELIVERY);
        nw->psi = msg.payload.get4I(0);
        nw->pdu = std::move(msg.pdu);
        m_base->appTask->push(nw);
    }
}

} // namespace nr::ue
