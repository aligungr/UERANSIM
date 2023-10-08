//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "proto.hpp"

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
    int length = stream.length() - initialLength - 8;
    stream.data()[initialLength + 2] = (uint8_t)(length >> 8 & 0xFF);
    stream.data()[initialLength + 3] = (uint8_t)(length & 0xFF);

    return true; // success
}

static std::unique_ptr<UdpPortExtHeader> DecodeUdpPortExtHeader(int len, const OctetView &stream)
{
    if (len != 1)
        return nullptr; // length must be 1 for UdpPortExtHeader

    auto res = std::make_unique<UdpPortExtHeader>();
    res->port = stream.read2US();
    return res;
}

static std::unique_ptr<LongPdcpPduNumberExtHeader> DecodeLongPdcpPduNumberExtHeader(int len, const OctetView &stream)
{
    if (len != 2)
        return nullptr; // length must be 2 for LongPdcpPduNumberExtHeader

    int num = stream.readI() & 0b11;
    num <<= 8;
    num |= stream.readI();
    num <<= 8;
    num |= stream.readI();

    auto res = std::make_unique<LongPdcpPduNumberExtHeader>();
    res->pdcpPduNumber = num;
    return res;
}

static std::unique_ptr<NrRanContainerExtHeader> DecodeNrRanContainerExtHeader(int len, const OctetView &stream)
{
    // obtain actual length in octets. (but not used)
    len = 4 * len - 2;

    // TODO: See 38.425, NrRanContainerExtHeader not implemented yet
    return nullptr;
}

static std::unique_ptr<PduSessionContainerExtHeader> DecodePduSessionContainerExtHeader(int len,
                                                                                        const OctetView &stream)
{
    // obtain actual length in octets. (but not used)
    len = 4 * len - 2;

    auto res = std::make_unique<PduSessionContainerExtHeader>();
    res->pduSessionInformation = PduSessionInformation::Decode(stream);
    return res;
}

static std::unique_ptr<PdcpPduNumberExtHeader> DecodePdcpPduNumberExtHeader(int len, const OctetView &stream)
{
    if (len != 1)
        return nullptr; // length must be 1 for PdcpPduNumberExtHeader

    auto res = std::make_unique<PdcpPduNumberExtHeader>();
    res->pdcpPduNumber = stream.read2US();
    return res;
}

std::unique_ptr<GtpMessage> DecodeGtpMessage(const OctetView &stream)
{
    auto res = std::make_unique<GtpMessage>();

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

                std::unique_ptr<GtpExtHeader> header = nullptr;

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
                    res->extHeaders.push_back(std::move(header));

                nextExtHeaderType = stream.readI();
            }
        }
    }

    size_t read = stream.currentIndex() - fistIndex;
    size_t rem = gtpLen - (read - 8);
    res->payload = stream.readOctetString(rem);

    return res;
}

std::unique_ptr<PduSessionInformation> PduSessionInformation::Decode(const OctetView &stream)
{
    size_t startIndex = stream.currentIndex();

    uint8_t octet = stream.read();

    int type = bits::BitRange8<4, 7>(octet);
    if (type != 0 && type != 1)
        return nullptr;

    if (type == 0)
    {
        auto res = std::make_unique<DlPduSessionInformation>();

        auto snp = bits::BitAt<2>(octet);
        res->qmp = bits::BitAt<3>(octet);

        octet = stream.read();

        res->qfi = bits::BitRange8<0, 5>(octet);
        res->rqi = bits::BitAt<6>(octet);
        auto ppp = bits::BitAt<7>(octet);

        if (ppp)
        {
            octet = stream.read();
            res->ppi = bits::BitRange8<5, 7>(octet);
        }

        if (res->qmp)
            res->dlSendingTs = stream.read8L();

        if (snp)
            res->dlQfiSeq = stream.read3I();

        // Consuming padding if any. See 5.5.3.5
        size_t read = stream.currentIndex() - startIndex;
        if ((read - 2) % 4 != 0)
        {
            size_t padding = 4 - ((read - 2) % 4);
            stream.readOctetString(padding);
        }

        return res;
    }
    else
    {
        auto res = std::make_unique<UlPduSessionInformation>();

        auto snp = bits::BitAt<0>(octet);
        auto ulDelay = bits::BitAt<1>(octet);
        auto dlDelay = bits::BitAt<2>(octet);
        res->qmp = bits::BitAt<3>(octet);

        octet = stream.read();
        res->qfi = bits::BitRange8<5, 7>(octet);

        if (res->qmp)
        {
            res->dlSendingTsRepeated = stream.read8L();
            res->dlReceivedTs = stream.read8L();
            res->ulSendingTs = stream.read8L();
        }

        if (dlDelay)
            res->dlDelayResult = stream.read4UI();

        if (ulDelay)
            res->ulDelayResult = stream.read4UI();

        if (snp)
            res->ulQfiSeq = stream.read3I();

        // Consuming padding if any. See 5.5.3.5
        size_t read = stream.currentIndex() - startIndex;
        if ((read - 2) % 4 != 0)
        {
            size_t padding = 4 - ((read - 2) % 4);
            stream.readOctetString(padding);
        }

        return res;
    }
}

bool PduSessionInformation::Encode(const PduSessionInformation &pdu, OctetString &stream)
{
    if (pdu.pduType != 0 && pdu.pduType != 1)
        return false;

    int initialLength = stream.length();

    if (pdu.pduType == 0)
    {
        auto &dl = dynamic_cast<const DlPduSessionInformation &>(pdu);

        auto snp = dl.dlQfiSeq.has_value();

        stream.appendOctet(bits::Ranged8({
            {4, pdu.pduType},
            {1, dl.qmp},
            {1, snp},
            {2, 0},
        }));

        stream.appendOctet(bits::Ranged8({
            {1, dl.ppi.has_value()},
            {1, dl.rqi},
            {6, dl.qfi},
        }));

        if (dl.ppi.has_value())
        {
            stream.appendOctet(bits::Ranged8({
                {3, dl.ppi.value()},
                {5, 0},
            }));
        }

        if (dl.qmp)
            stream.appendOctet8(dl.dlSendingTs.has_value() ? dl.dlSendingTs.value() : 0L);

        if (snp)
            stream.appendOctet3(dl.dlQfiSeq.value());
    }
    else
    {
        auto &ul = dynamic_cast<const UlPduSessionInformation &>(pdu);

        auto snp = ul.ulQfiSeq.has_value();

        stream.appendOctet(bits::Ranged8({
            {4, pdu.pduType},
            {1, ul.qmp},
            {1, ul.dlDelayResult.has_value()},
            {1, ul.ulDelayResult.has_value()},
            {1, snp},
        }));

        stream.appendOctet(bits::Ranged8({
            {2, 0},
            {6, ul.qfi},
        }));

        if (ul.qmp)
        {
            stream.appendOctet8(!ul.dlSendingTsRepeated.has_value() ? 0 : ul.dlSendingTsRepeated.value());
            stream.appendOctet8(!ul.dlReceivedTs.has_value() ? 0 : ul.dlReceivedTs.value());
            stream.appendOctet8(!ul.ulSendingTs.has_value() ? 0 : ul.ulSendingTs.value());
        }

        if (ul.dlDelayResult.has_value())
            stream.appendOctet4(ul.dlDelayResult.value());

        if (ul.ulDelayResult.has_value())
            stream.appendOctet4(ul.ulDelayResult.value());

        if (snp)
            stream.appendOctet3(ul.ulQfiSeq.value());
    }

    // Adjusting padding. See 5.5.3.5
    int written = stream.length() - initialLength;
    if ((written - 2) % 4 != 0)
    {
        int padding = 4 - ((written - 2) % 4);
        stream.appendPadding(padding);
    }
    return true;
}

} // namespace gtp