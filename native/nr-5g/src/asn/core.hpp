#pragma once

#include "../utils/bit_string.hpp"
#include "ber_tlv.hpp"

#include <cstdint>
#include <utility>

namespace asn
{

class AsnValue
{
  public:
    virtual BerTag getBerTag() const = 0;
    virtual int getBerLength() const = 0;
    virtual void berEncode(OctetBuffer &buffer) const = 0;
};

class AsnBool : public AsnValue
{
  private:
    const bool value;

  public:
    explicit AsnBool(bool value) : value(value)
    {
    }

    BerTag getBerTag() const override
    {
        return {BerClass::UNIVERSAL, false, 1};
    }

    int getBerLength() const override
    {
        return 1;
    }

    void berEncode(OctetBuffer &buffer) const override
    {
        BerEncodeType(buffer, getBerTag());
        BerEncodeLength(buffer, getBerLength());
        buffer.write(value);
    }
};

class AsnInteger : public AsnValue
{
  private:
    const int64_t value;

  public:
    explicit AsnInteger(int64_t value) : value(value)
    {
    }

    int getBerLength() const override
    {
        return 8;
    }

    BerTag getBerTag() const override
    {
        return {BerClass::UNIVERSAL, false, 2};
    }

    void berEncode(OctetBuffer &buffer) const override
    {
        BerEncodeType(buffer, getBerTag());
        BerEncodeLength(buffer, getBerLength());
        buffer.write8(value);
    }
};

class AsnBitString : public AsnValue
{
  private:
    std::vector<uint8_t> data;
    int leadingPadding;

  public:
    AsnBitString(std::vector<uint8_t> &&data, int leadingPadding)
        : data(std::move(data)), leadingPadding(leadingPadding)
    {
    }

    BerTag getBerTag() const override
    {
        return {BerClass::UNIVERSAL, false, 3};
    }

    int getBerLength() const override
    {
        return static_cast<int>(data.size()) + 1;
    }

    void berEncode(OctetBuffer &buffer) const override
    {
        BerEncodeType(buffer, getBerTag());
        BerEncodeLength(buffer, getBerLength());

        buffer.write(leadingPadding);
        for (uint8_t i : data)
            buffer.write(i);
    }
};

} // namespace asn