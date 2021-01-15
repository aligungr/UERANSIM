//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <bitset>
#include <cstdint>
#include <type_traits>
#include <utility>

namespace bits
{

static_assert(sizeof(int) == 4);

template <int start, int end>
inline int BitRange8(uint8_t octet)
{
    static_assert(start >= 0 && start <= 7);
    static_assert(end >= 0 && end <= 7);
    static_assert(start <= end);

    octet >>= start;
    int delta = end - start + 1;
    return octet & ((1 << delta) - 1);
}

template <int index>
inline int BitAt(uint8_t octet)
{
    return BitRange8<index, index>(octet);
}

template <int start, int end>
inline void SetBitRange8(uint8_t &octet, int value)
{
    static_assert(start >= 0 && start <= 7);
    static_assert(end >= 0 && end <= 7);
    static_assert(start <= end);

    std::bitset<8> v1 = octet;
    std::bitset<8> v2 = value;

    for (int i = start; i <= end; i++)
        v1[i] = v2[i - start];

    octet = v1.to_ulong() & 0xFF;
}

template <int index>
inline void SetBitAt(uint8_t &octet, bool value)
{
    SetBitRange8<index, index>(octet, value);
}

inline int Clz32(uint32_t num)
{
    return num == 0 ? 32 : __builtin_clz(num);
}

inline int Mrb32(uint32_t num)
{
    return 32 - Clz32(num);
}

template <uint8_t... TIndexes>
constexpr inline uint32_t Mask32()
{
    return ((1u << TIndexes) | ... | 0u);
}

inline uint32_t Ranged32(const std::initializer_list<std::pair<uint8_t, int>> &list)
{
    uint32_t val = 0;
    for (auto &i : list)
    {
        val <<= i.first;
        val |= (i.second & ((1u << i.first) - 1u));
    }
    return val;
}

inline uint16_t Ranged16(const std::initializer_list<std::pair<uint8_t, int>> &list)
{
    uint32_t val = 0;
    for (auto &i : list)
    {
        val <<= i.first;
        val |= (i.second & ((1u << i.first) - 1u));
    }
    return val & 0xFFFF;
}

inline uint8_t Ranged8(const std::initializer_list<std::pair<uint8_t, int>> &list)
{
    uint32_t val = 0;
    for (auto &i : list)
    {
        val <<= i.first;
        val |= (i.second & ((1u << i.first) - 1u));
    }
    return val & 0xFF;
}

inline unsigned NearDiv(unsigned val, unsigned div)
{
    return (div) * ((val + (div - 1u)) / (div));
}

inline int NearDiv(int val, int div)
{
    return (div) * ((val + (div - 1)) / (div));
}

template <typename T1, typename T2, typename T3, typename T4>
inline uint8_t Bmp4Enc1111(T1 v1, T2 v2, T3 v3, T4 v4)
{
    return Ranged8(
        {{1, static_cast<int>(v1)}, {1, static_cast<int>(v2)}, {1, static_cast<int>(v3)}, {1, static_cast<int>(v4)}});
}

template <typename T1, typename T2, typename T3>
inline uint8_t Bmp4Enc112(T1 v1, T2 v2, T3 v3)
{
    return Ranged8({{1, static_cast<int>(v1)}, {1, static_cast<int>(v2)}, {2, static_cast<int>(v3)}});
}

template <typename T1, typename T2, typename T3>
inline uint8_t Bmp4Enc121(T1 v1, T2 v2, T3 v3)
{
    return Ranged8({{1, static_cast<int>(v1)}, {2, static_cast<int>(v2)}, {1, static_cast<int>(v3)}});
}

template <typename T1, typename T2, typename T3>
inline uint8_t Bmp4Enc211(T1 v1, T2 v2, T3 v3)
{
    return Ranged8({{2, static_cast<int>(v1)}, {1, static_cast<int>(v2)}, {1, static_cast<int>(v3)}});
}

template <typename T1, typename T2>
inline uint8_t Bmp4Enc13(T1 v1, T2 v2)
{
    return Ranged8({{1, static_cast<int>(v1)}, {3, static_cast<int>(v2)}});
}

template <typename T1, typename T2>
inline uint8_t Bmp4Enc22(T1 v1, T2 v2)
{
    return Ranged8({{2, static_cast<int>(v1)}, {2, static_cast<int>(v2)}});
}

template <typename T1, typename T2>
inline uint8_t Bmp4Enc31(T1 v1, T2 v2)
{
    return Ranged8({{3, static_cast<int>(v1)}, {1, static_cast<int>(v2)}});
}

template <typename T1>
inline uint8_t Bmp4Enc4(T1 v1)
{
    return Ranged8({{4, static_cast<int>(v1)}});
}

template <typename T1, typename T2, typename T3, typename T4>
inline void Bmp4Dec1111(int v, T1 v1, T2 v2, T3 v3, T4 v4)
{
    if constexpr (!std::is_same<T1, nullptr_t>::value)
        *v1 = static_cast<typename std::remove_pointer<T1>::type>(BitRange8<3, 3>(v));
    if constexpr (!std::is_same<T2, nullptr_t>::value)
        *v2 = static_cast<typename std::remove_pointer<T2>::type>(BitRange8<2, 2>(v));
    if constexpr (!std::is_same<T3, nullptr_t>::value)
        *v3 = static_cast<typename std::remove_pointer<T3>::type>(BitRange8<1, 1>(v));
    if constexpr (!std::is_same<T4, nullptr_t>::value)
        *v4 = static_cast<typename std::remove_pointer<T4>::type>(BitRange8<0, 0>(v));
}

template <typename T1, typename T2, typename T3>
inline void Bmp4Dec112(int v, T1 v1, T2 v2, T3 v3)
{
    if constexpr (!std::is_same<T1, nullptr_t>::value)
        *v1 = static_cast<typename std::remove_pointer<T1>::type>(BitRange8<3, 3>(v));
    if constexpr (!std::is_same<T2, nullptr_t>::value)
        *v2 = static_cast<typename std::remove_pointer<T2>::type>(BitRange8<2, 2>(v));
    if constexpr (!std::is_same<T3, nullptr_t>::value)
        *v3 = static_cast<typename std::remove_pointer<T3>::type>(BitRange8<0, 1>(v));
}

template <typename T1, typename T2, typename T3>
inline void Bmp4Dec121(int v, T1 v1, T2 v2, T3 v3)
{
    if constexpr (!std::is_same<T1, nullptr_t>::value)
        *v1 = static_cast<typename std::remove_pointer<T1>::type>(BitRange8<3, 3>(v));
    if constexpr (!std::is_same<T2, nullptr_t>::value)
        *v2 = static_cast<typename std::remove_pointer<T2>::type>(BitRange8<1, 2>(v));
    if constexpr (!std::is_same<T3, nullptr_t>::value)
        *v3 = static_cast<typename std::remove_pointer<T3>::type>(BitRange8<0, 0>(v));
}

template <typename T1, typename T2, typename T3>
inline void Bmp4Dec211(int v, T1 v1, T2 v2, T3 v3)
{
    if constexpr (!std::is_same<T1, nullptr_t>::value)
        *v1 = static_cast<typename std::remove_pointer<T1>::type>(BitRange8<2, 3>(v));
    if constexpr (!std::is_same<T2, nullptr_t>::value)
        *v2 = static_cast<typename std::remove_pointer<T2>::type>(BitRange8<1, 1>(v));
    if constexpr (!std::is_same<T3, nullptr_t>::value)
        *v3 = static_cast<typename std::remove_pointer<T3>::type>(BitRange8<0, 0>(v));
}

template <typename T1, typename T2>
inline void Bmp4Dec13(int v, T1 v1, T2 v2)
{
    if constexpr (!std::is_same<T1, nullptr_t>::value)
        *v1 = static_cast<typename std::remove_pointer<T1>::type>(BitRange8<3, 3>(v));
    if constexpr (!std::is_same<T2, nullptr_t>::value)
        *v2 = static_cast<typename std::remove_pointer<T2>::type>(BitRange8<0, 2>(v));
}

template <typename T1, typename T2>
inline void Bmp4Dec22(int v, T1 v1, T2 v2)
{
    if constexpr (!std::is_same<T1, nullptr_t>::value)
        *v1 = static_cast<typename std::remove_pointer<T1>::type>(BitRange8<2, 3>(v));
    if constexpr (!std::is_same<T2, nullptr_t>::value)
        *v2 = static_cast<typename std::remove_pointer<T2>::type>(BitRange8<0, 1>(v));
}

template <typename T1, typename T2>
inline void Bmp4Dec31(int v, T1 v1, T2 v2)
{
    if constexpr (!std::is_same<T1, nullptr_t>::value)
        *v1 = static_cast<typename std::remove_pointer<T1>::type>(BitRange8<1, 3>(v));
    if constexpr (!std::is_same<T2, nullptr_t>::value)
        *v2 = static_cast<typename std::remove_pointer<T2>::type>(BitRange8<0, 0>(v));
}

template <typename T1>
inline void Bmp4Dec4(int v, T1 v1)
{
    if constexpr (!std::is_same<T1, nullptr_t>::value)
        *v1 = static_cast<typename std::remove_pointer<T1>::type>(BitRange8<0, 3>(v));
}

} // namespace bits