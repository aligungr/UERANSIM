#pragma once

#include <cstdint>
#include <utility>

namespace Bits
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

inline uint16_t Ranged8(const std::initializer_list<std::pair<uint8_t, int>> &list)
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

} // namespace Bits