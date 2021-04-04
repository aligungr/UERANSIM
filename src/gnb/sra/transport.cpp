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
    switch (msg.msgType)
    {
    case sra::EMessageType::CELL_INFO_REQUEST:
        handleCellInfoRequest(addr, (const sra::SraCellInfoRequest &)msg);
        break;
    default:
        m_logger->err("Unhandled SRA message received with type[%d]", static_cast<int>(msg.msgType));
        break;
    }
}

void GnbSraTask::sendSraMessage(const InetAddress &addr, const sra::SraMessage &msg)
{
    OctetString stream{};
    sra::EncodeSraMessage(msg, stream);
    m_udpTask->send(addr, stream);
}

} // namespace nr::gnb
