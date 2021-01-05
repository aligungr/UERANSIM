//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <BIT_STRING.h>
#include <OCTET_STRING.h>
#include <PrintableString.h>
#include <asn_SEQUENCE_OF.h>
#include <asn_application.h>
#include <bit_buffer.hpp>
#include <cstdlib>
#include <functional>
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

void SetOctetString(OCTET_STRING_t &target, uint8_t value);
void SetOctetString(OCTET_STRING_t &target, octet2 value);
void SetOctetString(OCTET_STRING_t &target, octet3 value);
void SetOctetString(OCTET_STRING_t &target, octet4 value);

void SetBitString(BIT_STRING_t &target, octet4 value);

template <size_t BitCount>
inline int GetBitStringInt(const BIT_STRING_t &source)
{
    size_t sourceBitLength = source.size * 8 - source.bits_unused;
    if (sourceBitLength < BitCount)
    {
        // invalid data return 0, (or throw?)
        return 0;
    }
    return BitBuffer{source.buf}.readBits(BitCount);
}

octet GetOctet1(const OCTET_STRING_t &source);
octet2 GetOctet2(const OCTET_STRING_t &source);
octet3 GetOctet3(const OCTET_STRING_t &source);
octet4 GetOctet4(const OCTET_STRING_t &source);

template <typename T>
inline void ForeachItem(const T &list, std::function<void(typename asn::AsnTraits_ListItemType<T>::value &)> fun)
{
    for (int i = 0; i < list.list.count; i++)
        fun(*list.list.array[i]);
}

} // namespace asn