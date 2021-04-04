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

void GnbRlsTask::receiveSraMessage(const InetAddress &addr, rls::SraMessage &msg)
{
    if (!m_powerOn)
    {
        // ignore received SRA message
        return;
    }

    int ueId = updateUeInfo(addr, msg.sti);

    switch (msg.msgType)
    {
    case rls::EMessageType::CELL_INFO_REQUEST: {
        handleCellInfoRequest(ueId, (const rls::SraCellInfoRequest &)msg);
        break;
    }
    case rls::EMessageType::PDU_DELIVERY: {
        handleUplinkPduDelivery(ueId, (rls::SraPduDelivery &)msg);
        break;
    }
    default:
        m_logger->err("Unhandled SRA message received with type[%d]", static_cast<int>(msg.msgType));
        break;
    }
}

void GnbRlsTask::sendSraMessage(int ueId, const rls::SraMessage &msg)
{
    if (!m_ueCtx.count(ueId))
    {
        m_logger->err("SRA message sending failure, UE[%d] not exists", ueId);
        return;
    }

    OctetString stream{};
    rls::EncodeRlsMessage(msg, stream);
    m_udpTask->send(m_ueCtx[ueId]->addr, stream);
}

} // namespace nr::gnb
