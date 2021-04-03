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

void GnbSasTask::receiveSasMessage(const InetAddress &addr, const sas::SasMessage &msg)
{
    switch (msg.msgType)
    {
    case sas::SasMessageType::CELL_INFO_REQUEST:
        handleCellInfoRequest(addr, (const sas::SasCellInfoRequest &)msg);
        break;
    default:
        m_logger->err("Unhandled SAS message received with type[%d]", static_cast<int>(msg.msgType));
        break;
    }
}

void GnbSasTask::sendSasMessage(const InetAddress &addr, const sas::SasMessage &msg)
{
    OctetString stream{};
    sas::EncodeSasMessage(msg, stream);
    m_udpTask->send(addr, stream);
}

} // namespace nr::gnb
