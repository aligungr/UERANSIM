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

static void EncodeDefault(uint8_t *buffer, rls::EMessageType msgType, uint64_t sti)
{
    buffer[0] = 0x03; // (Just for old RLS compatibility)

    buffer[1] = cons::Major;
    buffer[2] = cons::Minor;
    buffer[3] = cons::Patch;
    buffer[4] = static_cast<uint8_t>(msgType);

    octet8::SetTo(octet8{sti}, buffer + 5);
}

int EncodeRlsMessage(const RlsMessage &msg, uint8_t *buffer)
{
    buffer[0] = 0x03; // (Just for old RLS compatibility)

    buffer[1] = cons::Major;
    buffer[2] = cons::Minor;
    buffer[3] = cons::Patch;
    buffer[4] = static_cast<uint8_t>(msg.msgType);

    octet8::SetTo(octet8{msg.sti}, buffer + 5);

    if (msg.msgType == EMessageType::HEARTBEAT)
    {
        auto &m = (const RlsHeartBeat &)msg;
        octet4::SetTo(octet4{m.simPos.x}, buffer + 13);
        octet4::SetTo(octet4{m.simPos.y}, buffer + 17);
        octet4::SetTo(octet4{m.simPos.z}, buffer + 21);
        return 25;
    }
    else if (msg.msgType == EMessageType::HEARTBEAT_ACK)
    {
        auto &m = (const RlsHeartBeatAck &)msg;
        octet4::SetTo(octet4{m.dbm}, buffer + 13);
        return 17;
    }
    else if (msg.msgType == EMessageType::PDU_TRANSMISSION)
    {
        auto &m = (const RlsPduTransmission &)msg;
        buffer[13] = static_cast<uint8_t>(m.pduType);
        octet4::SetTo(octet4{m.pduId}, buffer + 14);
        octet4::SetTo(octet4{m.payload}, buffer + 18);
        octet4::SetTo(octet4{m.pdu.length()}, buffer + 22);
        std::memcpy(buffer + 26, m.pdu.data(), static_cast<size_t>(m.pdu.length()));
        return 26 + m.pdu.length();
    }
    else if (msg.msgType == EMessageType::PDU_TRANSMISSION_ACK)
    {
        auto &m = (const RlsPduTransmissionAck &)msg;
        octet4::SetTo(octet4{m.pduIds.size()}, buffer + 13);
        buffer += 17;
        for (auto pduId : m.pduIds)
        {
            octet4::SetTo(octet4{pduId}, buffer);
            buffer += 4;
        }
        return 17 + static_cast<int>(m.pduIds.size()) * 4;
    }
    return 0;
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

bool DecodeRlsHeader(const uint8_t *buffer, size_t size, EMessageType &msgType, uint64_t &sti)
{
    auto first = buffer[0];
    if (first != 3)
        return false;

    if (buffer[1] != cons::Major)
        return false;
    if (buffer[2] != cons::Minor)
        return false;
    if (buffer[3] != cons::Patch)
        return false;

    msgType = static_cast<EMessageType>(buffer[4]);

    uint64_t v = 0;
    for (int i = 0; i < 8; i++)
    {
        v <<= 8;
        v |= static_cast<uint64_t>(buffer[5 + i]);
    }
    sti = v;
    return true;
}

void DecodeHeartbeatAck(const uint8_t *buffer, size_t size, int &dbm)
{
    dbm = OctetView{buffer + 13, size - 13}.read4I();
}

OctetView DecodePduTransmissionAck(const uint8_t *buffer, size_t size)
{
    int count = OctetView{buffer + 13, size - 13}.read4I();
    const uint8_t *pduIds = buffer + 17;

    int viewSize = std::min(static_cast<int>(size) - 17, 4 * count);
    return {pduIds, static_cast<size_t>(viewSize)};
}

void DecodePduTransmission(const uint8_t *buffer, size_t size, EPduType &pduType, uint32_t &pduId, uint32_t &payload,
                           const uint8_t *&pduData, size_t &pduLength)
{
    auto view = OctetView{buffer + 13, size - 13};

    pduType = static_cast<EPduType>((uint8_t)view.read());
    pduId = view.read4UI();
    payload = view.read4UI();
    pduLength = view.read4I();
    pduData = buffer + view.currentIndex() + 13;
}

void EncodeHeartbeat(CompoundBuffer &buffer, uint64_t sti, const Vector3 &simPos)
{
    buffer.reset();
    EncodeDefault(buffer.cmAddress(), EMessageType::HEARTBEAT, sti);
    octet4::SetTo(octet4{simPos.x}, buffer.cmAddress() + 13);
    octet4::SetTo(octet4{simPos.y}, buffer.cmAddress() + 17);
    octet4::SetTo(octet4{simPos.z}, buffer.cmAddress() + 21);
    buffer.setCmSize(25);
}

void EncodePduTransmissionAck(CompoundBuffer &buffer, uint64_t sti, const std::vector<uint32_t> &pduIds)
{
    buffer.reset();

    uint8_t *data = buffer.cmAddress();

    EncodeDefault(data, EMessageType::PDU_TRANSMISSION_ACK, sti);

    octet4::SetTo(octet4{pduIds.size()}, data + 13);
    data += 17;
    for (auto pduId : pduIds)
    {
        octet4::SetTo(octet4{pduId}, data);
        data += 4;
    }
    buffer.setCmSize(17ull + pduIds.size() * 4ull);
}

void EncodePduTransmission(CompoundBuffer &buffer, uint64_t sti, rls::EPduType pduType, uint32_t payload,
                           uint32_t pduId)
{
    buffer.setTailCapacity(26);
    auto tail = buffer.tailAddress();
    EncodeDefault(tail, EMessageType::PDU_TRANSMISSION, sti);
    tail[13] = static_cast<uint8_t>(pduType);
    octet4::SetTo(octet4{pduId}, tail + 14);
    octet4::SetTo(octet4{payload}, tail + 18);
    octet4::SetTo(octet4{buffer.cmSize()}, tail + 22);
}

} // namespace rls
