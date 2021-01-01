#include "ber_tlv.hpp"

#include <cassert>

namespace asn
{

void BerEncodeType(OctetBuffer &buffer, const BerTag &tag)
{
    int tagNumber = tag.tagNumber;
    bool isConstructed = tag.isConstructed;
    auto tagClass = static_cast<uint32_t>(tag.tagClass);

    if (tagNumber <= 30)
    {
        buffer.writeRanged({{2, tagClass}, {1, isConstructed}, {5, tagNumber}});
        return;
    }

    buffer.writeRanged({{2, tagClass}, {1, isConstructed}, {5, ~0}});

    int bitCount = Bits::NearDiv(Bits::Mrb32(tagNumber), 7);
    int octetCount = bitCount / 7;

    for (int i = 0; i < octetCount; i++)
        buffer.writeRanged({{1, i != octetCount - 1}, {7, (tagNumber >> ((octetCount - i - 1) * 7))}});
}

void BerEncodeLength(OctetBuffer &buffer, int length)
{
    if (length < 0)
    {
        // indefinite length
        buffer.write(0b10000000);
        return;
    }

    if (length <= 127)
    {
        buffer.write(length & 0b01111111);
        return;
    }

    int bitCount = Bits::NearDiv(Bits::Mrb32(length), 7);
    int octetCount = bitCount / 7;

    buffer.writeRanged({{1, 1}, {7, octetCount}});
    for (int i = 0; i < octetCount; i++)
        buffer.writeRanged({{1, 1}, {7, ((length >> ((octetCount - i - 1) * 7)))}});
}

void BerEncodeEndOfContents(OctetBuffer &buffer)
{
    buffer.write2(0);
}

void BerDecodeType(OctetBuffer &buffer, BerTag &tag)
{
    uint8_t octet = buffer.read();

    tag.tagClass = static_cast<BerClass>(Bits::BitRange8<6, 7>(octet));
    tag.isConstructed = Bits::BitRange8<5, 5>(octet);

    int tagNum = Bits::BitRange8<0, 4>(octet);
    if (tagNum != 0b11111)
    {
        tag.tagNumber = tagNum;
        return;
    }

    int readBits = 0;
    tagNum = 0;

    while (true)
    {
        octet = buffer.read();

        tagNum <<= 7;
        tagNum |= Bits::BitRange8<0, 6>(octet);
        readBits += 7;

        if (!Bits::BitRange8<7, 7>(octet))
            break;
    }

    if (readBits > 32)
    {
        // we don't support such a large tags.
        assert(false);
    }

    tag.tagNumber = tagNum;
}

void BerDecodeLength(OctetBuffer &buffer, int &length)
{
    uint8_t octet = buffer.read();
    if (octet == 0b10000000)
    {
        // indefinite length
        length = -1;
        return;
    }

    int octetCount = Bits::BitRange8<0, 6>(octet);
    int len = 0;

    for (int i = 0; i < octetCount; i++)
    {
        octet = buffer.read();
        len <<= 7;
        len |= Bits::BitRange8<0, 6>(octet);
    }

    length = len;
}

} // namespace asn
