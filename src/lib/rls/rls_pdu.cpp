//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "rls_pdu.hpp"

#include <utils/constants.hpp>

namespace rls
{

void EncodeRlsMessage(const RlsMessage &msg, OctetString &stream)
{
    stream.appendOctet(0x03); // (Just for old RLS compatibility)

    stream.appendOctet(cons::Major);
    stream.appendOctet(cons::Minor);
    stream.appendOctet(cons::Patch);
    stream.appendOctet(static_cast<uint8_t>(msg.msgType));
    stream.appendOctet8(msg.sti);
    if (msg.msgType == EMessageType::HEARTBEAT)
    {
        auto &m = (const RlsHeartBeat &)msg;
        stream.appendOctet4(m.simPos.x);
        stream.appendOctet4(m.simPos.y);
        stream.appendOctet4(m.simPos.z);
    }
    else if (msg.msgType == EMessageType::HEARTBEAT_ACK)
    {
        auto &m = (const RlsHeartBeatAck &)msg;
        stream.appendOctet4(m.dbm);
    }
    else if (msg.msgType == EMessageType::PDU_TRANSMISSION)
    {
        auto &m = (const RlsPduTransmission &)msg;
        stream.appendOctet(static_cast<uint8_t>(m.pduType));
        stream.appendOctet4(m.pduId);
        stream.appendOctet4(m.payload);
        stream.appendOctet4(m.pdu.length());
        stream.append(m.pdu);
    }
    else if (msg.msgType == EMessageType::PDU_TRANSMISSION_ACK)
    {
        auto &m = (const RlsPduTransmissionAck &)msg;
        stream.appendOctet4(static_cast<uint32_t>(m.pduIds.size()));
        for (auto pduId : m.pduIds)
            stream.appendOctet4(pduId);
    }
}

std::unique_ptr<RlsMessage> DecodeRlsMessage(const OctetView &stream)
{
    auto first = stream.readI(); // (Just for old RLS compatibility)
    if (first != 3)
        return nullptr;

    if (stream.read() != cons::Major)
        return nullptr;
    if (stream.read() != cons::Minor)
        return nullptr;
    if (stream.read() != cons::Patch)
        return nullptr;

    auto msgType = static_cast<EMessageType>(stream.readI());
    uint64_t sti = stream.read8UL();

    if (msgType == EMessageType::HEARTBEAT)
    {
        auto res = std::make_unique<RlsHeartBeat>(sti);
        res->simPos.x = stream.read4I();
        res->simPos.y = stream.read4I();
        res->simPos.z = stream.read4I();
        return res;
    }
    else if (msgType == EMessageType::HEARTBEAT_ACK)
    {
        auto res = std::make_unique<RlsHeartBeatAck>(sti);
        res->dbm = stream.read4I();
        return res;
    }
    else if (msgType == EMessageType::PDU_TRANSMISSION)
    {
        auto res = std::make_unique<RlsPduTransmission>(sti);
        res->pduType = static_cast<EPduType>((uint8_t)stream.read());
        res->pduId = stream.read4UI();
        res->payload = stream.read4UI();
        res->pdu = stream.readOctetString(stream.read4I());
        return res;
    }
    else if (msgType == EMessageType::PDU_TRANSMISSION_ACK)
    {
        auto res = std::make_unique<RlsPduTransmissionAck>(sti);
        auto count = stream.read4UI();
        res->pduIds.reserve(count);
        for (uint32_t i = 0; i < count; i++)
            res->pduIds.push_back(stream.read4UI());
        return res;
    }

    return nullptr;
}

} // namespace rls
