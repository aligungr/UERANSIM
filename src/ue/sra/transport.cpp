//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <ue/nts.hpp>
#include <ue/rrc/task.hpp>
#include <utils/constants.hpp>

namespace nr::ue
{

void UeSraTask::receiveSraMessage(const InetAddress &address, sra::SraMessage &msg)
{
    switch (msg.msgType)
    {
    case sra::EMessageType::CELL_INFO_RESPONSE: {
        receiveCellInfoResponse((const sra::SraCellInfoResponse &)msg);
        break;
    case sra::EMessageType::PDU_DELIVERY: {
        deliverDownlinkPdu((sra::SraPduDelivery &)msg);
        break;
    }
    default:
        m_logger->err("Unhandled SRA message type[%d]", static_cast<int>(msg.msgType));
        break;
    }
    }
}

void UeSraTask::sendSraMessage(const InetAddress &address, const sra::SraMessage &msg)
{
    OctetString stream{};
    sra::EncodeSraMessage(msg, stream);
    m_udpTask->send(address, stream);
}

void UeSraTask::deliverUplinkPdu(sra::EPduType pduType, OctetString &&pdu, OctetString &&payload)
{
    if (!m_servingCell.has_value())
    {
        m_logger->warn("SRA uplink delivery requested without a serving cell");
        return;
    }

    sra::SraPduDelivery msg{m_sti};
    msg.pduType = pduType;
    msg.pdu = std::move(pdu);
    msg.payload = std::move(payload);
    sendSraMessage(InetAddress{m_servingCell->linkIp, cons::PortalPort}, msg);
}

void UeSraTask::deliverDownlinkPdu(sra::SraPduDelivery &msg)
{
    if (msg.pduType == sra::EPduType::RRC)
    {
        auto *nw = new NwUeSraToRrc(NwUeSraToRrc::RRC_PDU_DELIVERY);
        nw->channel = static_cast<rrc::RrcChannel>(msg.payload.get4I(0));
        nw->pdu = std::move(msg.pdu);
        m_base->rrcTask->push(nw);
    }
}

} // namespace nr::ue
