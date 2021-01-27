//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <BIT_STRING.h>
#include <NativeEnumerated.h>
#include <OCTET_STRING.h>
#include <PrintableString.h>
#include <asn_SEQUENCE_OF.h>
#include <asn_application.h>
#include <bit_buffer.hpp>
#include <cstdlib>
#include <cstring>
#include <functional>
#include <memory>
#include <octet.hpp>
#include <octet_buffer.hpp>
#include <stdexcept>
#include <string>
#include <type_traits>

namespace asn
{

template <typename T>
inline T *New()
{
    return (T *)calloc(1, sizeof(T));
}

template <typename T>
inline void Free(asn_TYPE_descriptor_t &desc, T *ptr)
{
    ASN_STRUCT_FREE(desc, ptr);
}

template <typename T>
struct AsnTraits_ListItemType
{
    typedef typename std::remove_reference<decltype(*T{}.list.array[0])>::type value;
};

template <typename T>
void SequenceAdd(T &list, typename AsnTraits_ListItemType<T>::value *item)
{
    ASN_SEQUENCE_ADD(&list, item);
}

void SetPrintableString(PrintableString_t &target, const std::string &value);
std::string GetPrintableString(const PrintableString_t &source);

void SetOctetString1(OCTET_STRING_t &target, uint8_t value);
void SetOctetString2(OCTET_STRING_t &target, octet2 value);
void SetOctetString3(OCTET_STRING_t &target, octet3 value);
void SetOctetString4(OCTET_STRING_t &target, octet4 value);
void SetOctetString(OCTET_STRING_t &target, const OctetString &value);
OctetString GetOctetString(const OCTET_STRING_t &source);
OctetString GetOctetString(const BIT_STRING_t &source);

void SetBitString(BIT_STRING_t &target, octet4 value);
void SetBitString(BIT_STRING_t &target, const OctetString &value);

template <size_t BitCount>
inline void SetBitStringInt(int value, BIT_STRING_t &target)
{
    static_assert(BitCount >= 1 && BitCount <= 32);

    if (target.buf != nullptr)
        free(target.buf);
    target.size = bits::NearDiv(BitCount, 8) / 8;
    target.buf = static_cast<uint8_t *>(calloc(1, target.size));
    target.bits_unused = (8 - (static_cast<int>(BitCount) % 8)) % 8;
    BitBuffer{target.buf}.writeBits(value, BitCount);
}

template <size_t BitCount>
inline void SetBitStringLong(int64_t value, BIT_STRING_t &target)
{
    static_assert(BitCount >= 1 && BitCount <= 64);

    if (target.buf != nullptr)
        free(target.buf);
    target.size = bits::NearDiv(BitCount, 8) / 8;
    target.buf = static_cast<uint8_t *>(calloc(1, target.size));
    target.bits_unused = (8 - (static_cast<int>(BitCount) % 8)) % 8;
    BitBuffer{target.buf}.writeBits(value, BitCount);
}

template <size_t BitCount>
inline int GetBitStringInt(const BIT_STRING_t &source)
{
    static_assert(BitCount >= 1 && BitCount <= 32);

    size_t sourceBitLength = source.size * 8 - source.bits_unused;
    if (sourceBitLength < BitCount)
    {
        // invalid data return 0, (or throw?)
        return 0;
    }
    return BitBuffer{source.buf}.readBits(BitCount);
}

template <size_t BitCount>
inline int64_t GetBitStringLong(const BIT_STRING_t &source)
{
    static_assert(BitCount >= 1 && BitCount <= 64);

    size_t sourceBitLength = source.size * 8 - source.bits_unused;
    if (sourceBitLength < BitCount)
    {
        // invalid data return 0, (or throw?)
        return 0;
    }
    return BitBuffer{source.buf}.readBitsLong(BitCount);
}

octet GetOctet1(const OCTET_STRING_t &source);
octet2 GetOctet2(const OCTET_STRING_t &source);
octet3 GetOctet3(const OCTET_STRING_t &source);
octet4 GetOctet4(const OCTET_STRING_t &source);

uint64_t GetUnsigned64(const INTEGER_t &source);
int64_t GetSigned64(const INTEGER_t &source);

void SetUnsigned64(uint64_t value, INTEGER_t &target);
void SetSigned64(int64_t value, INTEGER_t &target);

template <typename T>
inline void ForeachItem(const T &list, std::function<void(typename asn::AsnTraits_ListItemType<T>::value &)> fun)
{
    for (int i = 0; i < list.list.count; i++)
        fun(*list.list.array[i]);
}

template <typename T>
inline bool DeepCopy(asn_TYPE_descriptor_t &desc, const T &source, T *target)
{
    auto res = asn_encode_to_new_buffer(nullptr, ATS_CANONICAL_XER, &desc, &source);
    if (res.buffer == nullptr || res.result.encoded < 0)
        return false; // failure

    std::memset(target, 0, sizeof(T));

    if (xer_decode(nullptr, &desc, reinterpret_cast<void **>(&target), res.buffer, res.result.encoded).code != RC_OK)
    {
        free(res.buffer);
        return false; // failure
    }
    free(res.buffer);
    return true; // success
}

template <typename T>
struct Deleter
{
    asn_TYPE_descriptor_t *desc;

    explicit Deleter() : desc(nullptr)
    {
    }

    explicit Deleter(asn_TYPE_descriptor_t &desc) : desc(&desc)
    {
    }

    inline void operator()(T *ptr)
    {
        if (desc != nullptr)
            Free(*desc, ptr);
    }
};

template <typename T>
using Unique = std::unique_ptr<T, asn::Deleter<T>>;

template <typename T>
inline Unique<T> WrapUnique(T *ptr, asn_TYPE_descriptor_t &desc)
{
    return asn::Unique<T>(ptr, asn::Deleter<T>{desc});
}

} // namespace asn