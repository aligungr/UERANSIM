#pragma once

#include "../utils/octet_buffer.hpp"

namespace asn
{

enum class BerClass : uint8_t
{
    UNIVERSAL = 0b00,
    APPLICATION = 0b01,
    CONTEXT_SPECIFIC = 0b10,
    PRIVATE = 0b11
};

struct BerTag
{
    BerClass tagClass;
    bool isConstructed;
    int tagNumber;

    BerTag() : tagClass{BerClass::UNIVERSAL}, isConstructed(false), tagNumber(0)
    {
    }

    BerTag(BerClass tagClass, bool isConstructed, int tagNumber)
        : tagClass(tagClass), isConstructed(isConstructed), tagNumber(tagNumber)
    {
    }
};

void BerEncodeType(OctetBuffer &buffer, const BerTag &tag);
void BerEncodeLength(OctetBuffer &buffer, int length);
void BerEncodeEndOfContents(OctetBuffer &buffer);

void BerDecodeType(OctetBuffer &buffer, BerTag &tag);
void BerDecodeLength(OctetBuffer &buffer, int &length);

} // namespace asn