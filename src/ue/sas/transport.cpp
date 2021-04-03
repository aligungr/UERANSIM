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

void UeSasTask::receiveSasMessage(const InetAddress &address, const sas::SasMessage &msg)
{
    switch (msg.msgType)
    {
    case sas::SasMessageType::CELL_INFO_RESPONSE: {
        receiveCellInfoResponse((const sas::SasCellInfoResponse &)msg);
        break;
    default:
        m_logger->err("Unhandled SAS message type[%d]", static_cast<int>(msg.msgType));
        break;
    }
    }
}

void UeSasTask::sendSasMessage(const InetAddress &address, const sas::SasMessage &msg)
{
    OctetString stream{};
    sas::EncodeSasMessage(msg, stream);
    m_udpTask->send(address, stream);
}

} // namespace nr::ue
