//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <bitset>
#include <cassert>
#include <cstdint>
#include <utility>

struct octet
{
  private:
    uint8_t value;

  public:
    octet() : value(0)
    {
    }

    /* no explicit */ octet(int32_t value) : value(static_cast<uint8_t>(value & 0xFF))
    {
    }

    /* no explicit */ octet(uint32_t value) : value(static_cast<uint8_t>(value & 0xFF))
    {
    }

    /* no explicit */ constexpr operator uint8_t() const
    {
        return value;
    }

    explicit constexpr operator int() const
    {
        return value;
    }

    inline bool bit(int index) const
    {
        assert(index >= 0 && index <= 7);
        std::bitset<8> bitset = value;
        return bitset[index];
    }
};

struct octet2
{
  private:
    uint16_t value;

  public:
    octet2() : value(0)
    {
    }

    explicit octet2(int32_t value) : value(static_cast<uint8_t>(value & 0xFFFF))
    {
    }

    explicit octet2(uint32_t value) : value(static_cast<uint8_t>(value & 0xFFFF))
    {
    }

    octet2(uint8_t octet0, uint8_t octet1)
        : value{static_cast<uint16_t>((static_cast<uint32_t>(octet0) << 8U) | (static_cast<uint32_t>(octet1)))}
    {
    }

    inline uint8_t operator[](int index) const
    {
        assert(index >= 0 && index <= 1);
        return (value >> (8 - index * 8)) & 0xFF;
    }

    explicit constexpr operator int() const
    {
        return value;
    }
};

struct octet3
{
  private:
    uint32_t value;

  public:
    octet3() : value(0)
    {
    }

    explicit octet3(int32_t value) : value(static_cast<uint32_t>(value & 0xFFFFFF))
    {
    }

    explicit octet3(uint32_t value) : value(value & 0xFFFFFF)
    {
    }

    octet3(uint8_t octet0, uint8_t octet1, uint8_t octet2)
        : value{(static_cast<uint32_t>(octet0) << 16U) | (static_cast<uint32_t>(octet1) << 8U) |
                (static_cast<uint32_t>(octet2))}
    {
    }

    inline uint8_t operator[](int index) const
    {
        assert(index >= 0 && index <= 2);
        return (value >> (16 - index * 8)) & 0xFF;
    }

    explicit constexpr operator int() const
    {
        return value;
    }
};

struct octet4
{
  private:
    uint32_t value;

  public:
    octet4() : value(0)
    {
    }

    explicit octet4(int32_t value) : value(static_cast<uint32_t>(value))
    {
    }

    explicit octet4(uint32_t value) : value(value)
    {
    }

    octet4(uint8_t octet0, uint8_t octet1, uint8_t octet2, uint8_t octet3)
        : value{(static_cast<uint32_t>(octet0) << 24U) | (static_cast<uint32_t>(octet1) << 16U) |
                ((static_cast<uint32_t>(octet2) << 8U)) | (static_cast<uint32_t>(octet3))}
    {
    }

    inline uint8_t operator[](int index) const
    {
        assert(index >= 0 && index <= 3);
        return (value >> (24 - index * 8)) & 0xFF;
    }

    explicit constexpr operator int() const
    {
        return value;
    }
};