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

void GnbRlsTask::receiveRlsMessage(const InetAddress &addr, rls::RlsMessage &msg)
{
    if (!m_powerOn)
    {
        // ignore received RLS message
        return;
    }

    int ueId = updateUeInfo(addr, msg.sti);

    switch (msg.msgType)
    {
    case rls::EMessageType::CELL_INFO_REQUEST: {
        handleCellInfoRequest(ueId, (const rls::RlsCellInfoRequest &)msg);
        break;
    }
    case rls::EMessageType::PDU_DELIVERY: {
        handleUplinkPduDelivery(ueId, (rls::RlsPduDelivery &)msg);
        break;
    }
    default:
        m_logger->err("Unhandled RLS message received with type[%d]", static_cast<int>(msg.msgType));
        break;
    }
}

void GnbRlsTask::sendRlsMessage(int ueId, const rls::RlsMessage &msg)
{
    if (!m_ueCtx.count(ueId))
    {
        m_logger->err("RLS message sending failure, UE[%d] not exists", ueId);
        return;
    }

    OctetString stream{};
    rls::EncodeRlsMessage(msg, stream);
    m_udpTask->send(m_ueCtx[ueId]->addr, stream);
}

} // namespace nr::gnb
