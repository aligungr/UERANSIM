//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
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
    // Minimum header: 1 (compat) + 3 (version) + 1 (msgType) + 8 (sti) = 13 bytes
    if (stream.remainingSize() < 13)
        return nullptr;

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

    // Validate message type before reading further
    if (msgType != EMessageType::HEARTBEAT && msgType != EMessageType::HEARTBEAT_ACK &&
        msgType != EMessageType::PDU_TRANSMISSION && msgType != EMessageType::PDU_TRANSMISSION_ACK)
        return nullptr;

    uint64_t sti = stream.read8UL();

    if (msgType == EMessageType::HEARTBEAT)
    {
        // Need 12 bytes (3 x int32)
        if (stream.remainingSize() < 12)
            return nullptr;
        auto res = std::make_unique<RlsHeartBeat>(sti);
        res->simPos.x = stream.read4I();
        res->simPos.y = stream.read4I();
        res->simPos.z = stream.read4I();
        return res;
    }
    else if (msgType == EMessageType::HEARTBEAT_ACK)
    {
        // Need 4 bytes (int32)
        if (stream.remainingSize() < 4)
            return nullptr;
        auto res = std::make_unique<RlsHeartBeatAck>(sti);
        res->dbm = stream.read4I();
        return res;
    }
    else if (msgType == EMessageType::PDU_TRANSMISSION)
    {
        // Need 1 (pduType) + 4 (pduId) + 4 (payload) + 4 (pduLength) = 13 bytes minimum
        if (stream.remainingSize() < 13)
            return nullptr;

        auto res = std::make_unique<RlsPduTransmission>(sti);
        auto rawPduType = static_cast<uint8_t>(stream.read());
        if (rawPduType != static_cast<uint8_t>(EPduType::RRC) &&
            rawPduType != static_cast<uint8_t>(EPduType::DATA))
            return nullptr;
        res->pduType = static_cast<EPduType>(rawPduType);

        res->pduId = stream.read4UI();
        res->payload = stream.read4UI();

        uint32_t pduLength = stream.read4UI();
        if (pduLength > 16384)
            return nullptr;
        if (stream.remainingSize() < pduLength)
            return nullptr;

        res->pdu = stream.readOctetString(static_cast<int>(pduLength));
        return res;
    }
    else if (msgType == EMessageType::PDU_TRANSMISSION_ACK)
    {
        static constexpr uint32_t MAX_ACK_COUNT = 4096;

        // Need 4 bytes for count
        if (stream.remainingSize() < 4)
            return nullptr;

        auto res = std::make_unique<RlsPduTransmissionAck>(sti);
        auto count = stream.read4UI();
        if (count > MAX_ACK_COUNT)
            return nullptr;
        // Need count * 4 bytes for pduIds
        if (stream.remainingSize() < static_cast<size_t>(count) * 4)
            return nullptr;

        res->pduIds.reserve(count);
        for (uint32_t i = 0; i < count; i++)
            res->pduIds.push_back(stream.read4UI());
        return res;
    }

    return nullptr;
}

} // namespace rls
