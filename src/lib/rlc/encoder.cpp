//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "encoder.hpp"

#include <utils/bit_buffer.hpp>
#include <utils/bits.hpp>

namespace rlc
{

UmdPdu *RlcEncoder::DecodeUmd(uint8_t *data, int size, bool isShortSn)
{
    auto *pdu = new UmdPdu();
    pdu->si = static_cast<ESegmentInfo>(bits::BitRange8<6, 7>(data[0]));
    pdu->so = 0;
    pdu->sn = 0;
    pdu->isProcessed = false;

    int index = 1;

    if (pdu->si != ESegmentInfo::FULL)
    {
        if (isShortSn)
        {
            pdu->sn = bits::BitRange8<0, 5>(data[index]);
        }
        else
        {
            pdu->sn = bits::BitRange8<0, 3>(data[index]);
            pdu->sn <<= 8;
            index++;
            pdu->sn |= bits::BitRange8<0, 7>(data[index]);
        }

        if (si::requiresSo(pdu->si))
        {
            pdu->so = (data[index] << 8) | data[index + 1];
            index += 2;
        }
    }

    pdu->size = size - index;
    pdu->data = new uint8_t[pdu->size];
    std::memcpy(pdu->data, data + index, pdu->size);

    return pdu;
}

int RlcEncoder::EncodeUmd(uint8_t *buffer, bool isShortSn, ESegmentInfo si, int so, int sn, uint8_t *data, int size)
{
    int index = 0;

    uint8_t octet0 = ((int)si) << 6;

    int remainingSn = -1;

    if (si != ESegmentInfo::FULL)
    {
        if (isShortSn)
        {
            octet0 |= sn & 0b11111;
        }
        else
        {
            octet0 |= (sn >> 8) & 0b111;
            remainingSn = sn & 0b11111111;
        }
    }

    buffer[index++] = octet0;

    if (remainingSn != -1)
        buffer[index++] = remainingSn & 0xFF;

    if (si::requiresSo(si))
    {
        buffer[index++] = (so >> 8) & 0xFF;
        buffer[index++] = so & 0xFF;
    }

    std::memcpy(buffer + index, data, size);
    index += size;

    return index;
}

AmdPdu *RlcEncoder::DecodeAmd(uint8_t *data, int size, bool isShortSn)
{
    uint8_t octet = data[0];

    if (octet >> 7 != 1)
    {
        // it is control pdu.
        return nullptr;
    }

    auto *pdu = new AmdPdu();
    pdu->isProcessed = false;
    pdu->so = 0;

    pdu->p = (octet >> 6) & 0b1;
    pdu->si = static_cast<ESegmentInfo>(bits::BitRange8<4, 5>(octet));
    pdu->sn = isShortSn ? bits::BitRange8<0, 3>(octet) : bits::BitRange8<0, 1>(octet);

    int index = 1;
    octet = data[index++];
    pdu->sn <<= 8;
    pdu->sn |= octet;

    if (!isShortSn)
    {
        octet = data[index++];

        pdu->sn <<= 8;
        pdu->sn |= octet;
    }

    if (si::requiresSo(pdu->si))
    {
        pdu->so = (data[index] << 8) | data[index + 1];
        index += 2;
    }

    pdu->size = size - index;
    pdu->data = new uint8_t[pdu->size];
    std::memcpy(pdu->data, data + index, pdu->size);

    return pdu;
}

int RlcEncoder::EncodeAmd(uint8_t *buffer, bool isShortSn, ESegmentInfo si, int so, int sn, uint8_t *data, int size,
                          bool p)
{
    uint8_t octet = p ? 0b11000000 : 0b10000000;
    octet |= ((int)si) << 4;

    if (isShortSn)
        octet |= (sn >> 8) & 0b1111;
    else
        octet |= (sn >> 16) & 0b111;

    int index = 0;
    buffer[index++] = octet;

    if (!isShortSn)
        buffer[index++] = (sn >> 8) & 0xFF;
    buffer[index++] = sn & 0xFF;

    if (si::requiresSo(si))
    {
        buffer[index++] = (so >> 8) & 0xFF;
        buffer[index++] = so & 0xFF;
    }

    std::memcpy(buffer + index, data, size);
    index += size;
    return index;
}

StatusPdu *RlcEncoder::DecodeStatus(uint8_t *data, int size, bool isShortSn)
{
    BitBuffer buffer{data};

    if (buffer.read() != 0)
    {
        // it is data pdu.
        return nullptr;
    }

    int pduType = buffer.readBits(3);
    if (pduType != 0)
    {
        // it is not a STATUS PDU
        return nullptr;
    }

    auto *pdu = new StatusPdu();
    pdu->ackSn = buffer.readBits(isShortSn ? 12 : 18);

    bool e1 = buffer.read();

    // consume reserved
    buffer.readBits(isShortSn ? 7 : 1);

    while (e1)
    {
        int nackSn = buffer.readBits(isShortSn ? 12 : 18);
        e1 = buffer.read();
        bool e2 = buffer.read();
        bool e3 = buffer.read();

        // consume reserved
        buffer.readBits(isShortSn ? 1 : 3);

        int soStart = -1;
        int soEnd = -1;
        int nackRange = -1;

        if (e2)
        {
            soStart = buffer.readBits(16);
            soEnd = buffer.readBits(16);
        }

        if (e3)
            nackRange = buffer.readBits(8);

        NackBlock nackBlock{};
        nackBlock.nackSn = nackSn;
        nackBlock.soStart = soStart;
        nackBlock.soEnd = soEnd;
        nackBlock.nackRange = nackRange;

        pdu->nackBlocks.push_back(nackBlock);
    }

    return pdu;
}

int RlcEncoder::EncodeStatus(uint8_t *buf, const StatusPdu &pdu, bool isShortSn)
{
    BitBuffer buffer{buf};

    buffer.write(false);
    buffer.writeBits(0, 3); // STATUS PDU
    buffer.writeBits(pdu.ackSn, isShortSn ? 12 : 18);

    if (pdu.nackBlocks.empty())
    {
        buffer.write(false);

        // insert reserved bits
        buffer.writeBits(0, isShortSn ? 7 : 1);

        buffer.octetAlign();
        return buffer.writtenOctets();
    }

    buffer.write(true);

    // insert reserved bits
    buffer.writeBits(0, isShortSn ? 7 : 1);

    for (size_t i = 0; i < pdu.nackBlocks.size(); i++)
    {
        auto block = pdu.nackBlocks[i];

        buffer.writeBits(block.nackSn, isShortSn ? 12 : 18);
        buffer.write(i != pdu.nackBlocks.size() - 1); // E1

        bool e2, e3;

        if (block.soEnd == -1 || block.soStart == -1)
        {
            if (block.soEnd != -1 || block.soStart != -1)
            {
                // Bug found. Must be all -1 or no one -1.
                assert(false);
            }

            e2 = false;
            buffer.write(false);
        }
        else
        {
            e2 = true;
            buffer.write(true);
        }

        e3 = block.nackRange != -1;
        buffer.write(e3);

        // insert reserved bits
        buffer.writeBits(0, isShortSn ? 1 : 3);

        if (e2)
        {
            buffer.writeBits(block.soStart, 16);
            buffer.writeBits(block.soEnd, 16);
        }

        if (e3)
            buffer.writeBits(block.nackRange, 8);
    }

    buffer.octetAlign();
    return buffer.writtenOctets();
}

} // namespace rlc
