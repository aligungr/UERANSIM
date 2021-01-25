//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gtp_encode.hpp"

namespace gtp
{

static bool EncodeExtensionHeader(const GtpExtHeader &header, OctetString &stream)
{
    if (header.type == ExtHeaderType::PduSessionContainerExtHeader)
    {
        stream.appendOctet(0b10000101);

        OctetString inner;

        auto &pduSession = ((const PduSessionContainerExtHeader &)header).pduSessionInformation;
        if (!PduSessionInformation::Encode(*pduSession, inner))
            return false;

        stream.appendOctet((inner.length() + 2) / 4);
        stream.append(inner);
    }
    else if (header.type == ExtHeaderType::PdcpPduNumberExtHeader)
    {
        stream.appendOctet(0b11000000);
        stream.appendOctet(1);
        stream.appendOctet2(((const PdcpPduNumberExtHeader &)header).pdcpPduNumber);
    }
    else if (header.type == ExtHeaderType::UdpPortExtHeader)
    {
        stream.appendOctet(0b01000000);
        stream.appendOctet(1);
        stream.appendOctet2(((const UdpPortExtHeader &)header).port);
    }
    else if (header.type == ExtHeaderType::LongPdcpPduNumberExtHeader)
    {
        int num = ((const LongPdcpPduNumberExtHeader &)header).pdcpPduNumber;

        stream.appendOctet(0b10000010);
        stream.appendOctet(2);
        stream.appendOctet(num >> 16 & 0b11);
        stream.appendOctet(num >> 8 & 0xFF);
        stream.appendOctet(num & 0xFF);
    }
    else if (header.type == ExtHeaderType::NrRanContainerExtHeader)
    {
        // TODO: See 38.425, NrRanContainerExtHeader not implemented yet
        return false;
    }

    return true;
}

bool EncodeGtpMessage(const GtpMessage &gtp, OctetString &stream)
{
    int initialLength = stream.length();

    bool pn = gtp.nPduNum.has_value();
    bool s = gtp.seq.has_value();
    bool e = !gtp.extHeaders.empty();

    bool any = pn || s || e;

    int pt = 1;
    int version = 1;

    stream.appendOctet(bits::Ranged8({{3, version}, {1, pt}, {1, 0}, {1, e}, {1, s}, {1, pn}}));

    stream.appendOctet(gtp.msgType);
    stream.appendOctet2(0); // Dummy length for now.
    stream.appendOctet4(gtp.teid);

    if (any)
    {
        stream.appendOctet2(!gtp.seq.has_value() ? 0 : gtp.seq.value());
        stream.appendOctet(!gtp.nPduNum.has_value() ? 0 : gtp.nPduNum.value());

        for (auto &header : gtp.extHeaders)
            if (!EncodeExtensionHeader(*header, stream))
                return false;

        stream.appendOctet(0); // no more extension headers.
    }

    stream.append(gtp.payload);

    // assigning length field
    int length = stream.length() - initialLength;
    stream.data()[initialLength + 2] = (uint8_t)(length >> 8 & 0xFF);
    stream.data()[initialLength + 3] = (uint8_t)(length & 0xFF);

    return true; // success
}

static UdpPortExtHeader *DecodeUdpPortExtHeader(int len, const OctetBuffer &stream)
{
    if (len != 1)
        return nullptr; // length must be 1 for UdpPortExtHeader

    auto *res = new UdpPortExtHeader();
    res->port = stream.read2US();
    return res;
}

static LongPdcpPduNumberExtHeader *DecodeLongPdcpPduNumberExtHeader(int len, const OctetBuffer &stream)
{
    if (len != 2)
        return nullptr; // length must be 2 for LongPdcpPduNumberExtHeader

    int num = stream.readI() & 0b11;
    num <<= 8;
    num |= stream.readI();
    num <<= 8;
    num |= stream.readI();

    auto *res = new LongPdcpPduNumberExtHeader();
    res->pdcpPduNumber = num;
    return res;
}

static NrRanContainerExtHeader *DecodeNrRanContainerExtHeader(int len, const OctetBuffer &stream)
{
    // obtain actual length in octets. (but not used)
    len = 4 * len - 2;

    // TODO: See 38.425, NrRanContainerExtHeader not implemented yet
    return nullptr;
}

static PduSessionContainerExtHeader *DecodePduSessionContainerExtHeader(int len, const OctetBuffer &stream)
{
    // obtain actual length in octets. (but not used)
    len = 4 * len - 2;

    auto *res = new PduSessionContainerExtHeader();
    res->pduSessionInformation = PduSessionInformation::Decode(stream);
    return res;
}

static PdcpPduNumberExtHeader *DecodePdcpPduNumberExtHeader(int len, const OctetBuffer &stream)
{
    if (len != 1)
        return nullptr; // length must be 1 for PdcpPduNumberExtHeader

    auto *res = new PdcpPduNumberExtHeader();
    res->pdcpPduNumber = stream.read2US();
    return res;
}

GtpMessage *DecodeGtpMessage(const OctetBuffer &stream)
{
    auto *res = new GtpMessage();

    size_t fistIndex = stream.currentIndex();

    uint8_t flags = stream.read();

    int version = bits::BitRange8<5, 7>(flags);
    if (version != 1)
    {
        // "GTP-U version not implemented"
        return nullptr;
    }

    int protocolType = bits::BitAt<4>(flags);
    if (protocolType != 1)
    {
        // GTP' not implemented
        return nullptr;
    }

    bool nextExtensionHeaderPresent = bits::BitAt<2>(flags);
    bool sequenceNumberPresent = bits::BitAt<1>(flags);
    bool nPduNumberPresent = bits::BitAt<0>(flags);

    res->msgType = stream.read();
    int gtpLen = stream.read2I();
    res->teid = stream.read4UI();

    if (sequenceNumberPresent || nPduNumberPresent || nextExtensionHeaderPresent)
    {
        auto seq = stream.read2I();
        auto nPduNum = stream.read();
        auto nextExtHeaderType = stream.readI();

        if (sequenceNumberPresent)
            res->seq = seq;
        if (nPduNumberPresent)
            res->nPduNum = nPduNum;
        if (nextExtensionHeaderPresent)
        {
            while (nextExtHeaderType != 0)
            {
                int len = stream.readI(); // NOTE: len is actually 4 times length

                GtpExtHeader *header = nullptr;

                switch (nextExtHeaderType)
                {
                case 0b01000000:
                    header = DecodeUdpPortExtHeader(len, stream);
                    break;
                case 0b10000010:
                    header = DecodeLongPdcpPduNumberExtHeader(len, stream);
                    break;
                case 0b10000100:
                    header = DecodeNrRanContainerExtHeader(len, stream);
                    break;
                case 0b10000101:
                    header = DecodePduSessionContainerExtHeader(len, stream);
                    break;
                case 0b11000000:
                    header = DecodePdcpPduNumberExtHeader(len, stream);
                    break;
                case 0b10000001: // Not used in gNB
                case 0b10000011: // Not used in gNB
                    break;
                default:
                    // GTP next extension header type is invalid
                    return nullptr;
                }

                if (header != nullptr)
                    res->extHeaders.push_back(std::unique_ptr<GtpExtHeader>(header));

                nextExtHeaderType = stream.readI();
            }
        }
    }

    size_t read = stream.currentIndex() - fistIndex;
    size_t rem = gtpLen - (read - 8);
    res->payload = stream.readOctetString(rem);

    return res;
}

} // namespace gtp
