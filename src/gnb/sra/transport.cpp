//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

namespace nr::gnb
{

void GnbSraTask::receiveSraMessage(const InetAddress &addr, const sra::SraMessage &msg)
{
    int ueId = updateUeInfo(addr, msg.sti);

    switch (msg.msgType)
    {
    case sra::EMessageType::CELL_INFO_REQUEST: {
        handleCellInfoRequest(ueId, (const sra::SraCellInfoRequest &)msg);
        break;
    }
    case sra::EMessageType::PDU_DELIVERY: {
        handleUplinkPduDelivery(ueId, (const sra::SraPduDelivery &)msg);
        break;
    }
    default:
        m_logger->err("Unhandled SRA message received with type[%d]", static_cast<int>(msg.msgType));
        break;
    }
}

void GnbSraTask::sendSraMessage(int ueId, const sra::SraMessage &msg)
{
    if (!m_ueCtx.count(ueId))
    {
        m_logger->err("SRA message sending failure, UE[%d] not exists", ueId);
        return;
    }

    OctetString stream{};
    sra::EncodeSraMessage(msg, stream);
    m_udpTask->send(m_ueCtx[ueId]->addr, stream);
}

} // namespace nr::gnb
