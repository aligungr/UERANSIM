//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

namespace nr::ue
{

void UeSraTask::receiveSraMessage(const InetAddress &address, const sra::SraMessage &msg)
{
    switch (msg.msgType)
    {
    case sra::SraMessageType::CELL_INFO_RESPONSE: {
        receiveCellInfoResponse((const sra::SraCellInfoResponse &)msg);
        break;
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

} // namespace nr::ue
