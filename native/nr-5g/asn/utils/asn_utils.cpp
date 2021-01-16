//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "asn_utils.hpp"
#include <cstring>
#include <octet_string.hpp>

namespace asn
{

void SetPrintableString(PrintableString_t &target, const std::string &value)
{
    if (OCTET_STRING_fromBuf(&target, value.c_str(), value.length()) != 0)
        throw std::runtime_error("OCTET_STRING_fromBuf failed");
}

void SetOctetString(OCTET_STRING_t &target, uint8_t value)
{
    uint8_t buffer[1] = {value};

    if (OCTET_STRING_fromBuf(&target, reinterpret_cast<char *>(buffer), 1) != 0)
        throw std::runtime_error("OCTET_STRING_fromBuf failed");
}

void SetOctetString(OCTET_STRING_t &target, octet2 value)
{
    uint8_t buffer[2] = {value[0], value[1]};

    if (OCTET_STRING_fromBuf(&target, reinterpret_cast<char *>(buffer), 2) != 0)
        throw std::runtime_error("OCTET_STRING_fromBuf failed");
}

void SetOctetString(OCTET_STRING_t &target, octet3 value)
{
    uint8_t buffer[3] = {value[0], value[1], value[2]};

    if (OCTET_STRING_fromBuf(&target, reinterpret_cast<char *>(buffer), 3) != 0)
        throw std::runtime_error("OCTET_STRING_fromBuf failed");
}

void SetOctetString(OCTET_STRING_t &target, octet4 value)
{
    uint8_t buffer[4] = {value[0], value[1], value[2], value[3]};

    if (OCTET_STRING_fromBuf(&target, reinterpret_cast<char *>(buffer), 4) != 0)
        throw std::runtime_error("OCTET_STRING_fromBuf failed");
}

void SetOctetString(OCTET_STRING_t &target, const OctetString &value)
{
    if (OCTET_STRING_fromBuf(&target, reinterpret_cast<const char *>(value.data()), value.length()) != 0)
        throw std::runtime_error("OCTET_STRING_fromBuf failed");
}

void SetBitString(BIT_STRING_t &target, octet4 value)
{
    if (target.buf)
        free(target.buf);

    target.buf = static_cast<uint8_t *>(calloc(4, 1));
    target.buf[0] = value[0];
    target.buf[1] = value[1];
    target.buf[2] = value[2];
    target.buf[3] = value[3];
    target.size = 4;
    target.bits_unused = 0;
}

void SetBitString(BIT_STRING_t &target, const OctetString &value)
{
    if (target.buf)
        free(target.buf);
    target.buf = static_cast<uint8_t *>(calloc(value.length(), 1));
    std::memcpy(target.buf, value.data(), value.length());
    target.size = value.length();
    target.bits_unused = 0;
}

std::string GetPrintableString(const PrintableString_t &source)
{
    std::string r;
    r.reserve(source.size);
    for (size_t i = 0; i < source.size; i++)
        r += (char)source.buf[i];
    return r;
}

octet GetOctet1(const OCTET_STRING_t &source)
{
    return source.size < 1 ? octet{0} : OctetBuffer{source.buf, source.size}.read();
}

octet2 GetOctet2(const OCTET_STRING_t &source)
{
    return source.size < 2 ? octet2{0} : OctetBuffer{source.buf, source.size}.read2();
}

octet3 GetOctet3(const OCTET_STRING_t &source)
{
    return source.size < 3 ? octet3{0} : OctetBuffer{source.buf, source.size}.read3();
}

octet4 GetOctet4(const OCTET_STRING_t &source)
{
    return source.size < 4 ? octet4{0} : OctetBuffer{source.buf, source.size}.read4();
}

OctetString GetOctetString(const OCTET_STRING_t &source)
{
    std::vector<uint8_t> v;
    v.reserve(source.size);
    std::memcpy(v.data(), source.buf, source.size);
    return OctetString{std::move(v)};
}

OctetString GetOctetString(const BIT_STRING_t &source)
{
    std::vector<uint8_t> v;
    v.reserve(source.size);
    std::memcpy(v.data(), source.buf, source.size);
    return OctetString{std::move(v)};
}

uint64_t GetUnsigned64(const INTEGER_t &source)
{
    uint64_t res = 0;
    if (asn_INTEGER2ulong(&source, &res) != 0)
    {
        // ignore the error
        res = 0;
    }
    return res;
}

int64_t GetSigned64(const INTEGER_t &source)
{
    int64_t res = 0;
    if (asn_INTEGER2long(&source, &res) != 0)
    {
        // ignore the error
        res = 0;
    }
    return res;
}

void SetUnsigned64(uint64_t value, INTEGER_t &target)
{
    if (asn_ulong2INTEGER(&target, value) != 0)
    {
        if (target.buf != nullptr)
        {
            free(target.buf);
            target.buf = nullptr;
        }
        target.size = 0;
    }
}

void SetSigned64(int64_t value, INTEGER_t &target)
{
    if (asn_long2INTEGER(&target, value) != 0)
    {
        if (target.buf != nullptr)
        {
            free(target.buf);
            target.buf = nullptr;
        }
        target.size = 0;
    }
}

} // namespace asn