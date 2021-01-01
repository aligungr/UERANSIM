#include "ber_decoder.hpp"

#include <cassert>

#define TAG_BOOLEAN 1
#define TAG_INTEGER 2
#define TAG_BIT_STRING 3

namespace asn
{

static void TagControl(BerTag tag)
{
    if (tag.tagNumber == TAG_BOOLEAN && (tag.isConstructed || tag.tagClass != BerClass::UNIVERSAL))
        goto error;
    if (tag.tagNumber == TAG_INTEGER && (tag.isConstructed || tag.tagClass != BerClass::UNIVERSAL))
        goto error;
    if (tag.tagNumber == TAG_BIT_STRING && (tag.isConstructed || tag.tagClass != BerClass::UNIVERSAL))
        goto error;

    // TODO diğer typelar

    return;
error:
    assert(false);
}

static void LengthControl(BerTag tag, int length)
{
    if (tag.tagNumber == TAG_BOOLEAN && length != 1)
        goto error;
    if (tag.tagNumber == TAG_INTEGER && length <= 0)
        goto error;
    if (tag.tagNumber == TAG_BIT_STRING && length <= 0)
        goto error;

    // TODO diğer typelar

    return;
error:
    assert(false);
}

AsnValue *DecodeBer(OctetBuffer &buffer)
{
    BerTag tag;
    BerDecodeType(buffer, tag);
    TagControl(tag);

    int length;
    BerDecodeLength(buffer, length);
    LengthControl(tag, length);

    switch (tag.tagNumber)
    {
    case TAG_BOOLEAN:
        return new AsnBool(buffer.read());
    case TAG_INTEGER: {
        int64_t val = 0;
        for (int i = 0; i < length; i++)
        {
            val <<= 8;
            val |= buffer.read();
        }
        return new AsnInteger(val);
    }
    case TAG_BIT_STRING: {
        uint8_t padding = buffer.read() % 8;
        std::vector<uint8_t> data;
        data.reserve(length - 1);
        for (int i = 0; i < length - 1; i++)
            data.push_back(buffer.read());
        return new AsnBitString(std::move(data), padding);
    }
    default:
        assert(false);
        return nullptr;
    }
}

} // namespace asn