//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cassert>
#include <cstdint>

struct octet2
{
    const uint16_t value;

    inline uint8_t operator[](int index) const
    {
        assert(index >= 0 && index <= 1);
        return (value >> (8 - index * 8)) & 0xFF;
    }
};

struct octet3
{
    const uint32_t value;

    explicit octet3(int32_t value) : value(static_cast<uint32_t>(value))
    {
    }

    explicit octet3(uint32_t value) : value(value)
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
};

struct octet4
{
    const uint32_t value;

    explicit octet4(int32_t value) : value(static_cast<uint32_t>(value))
    {
    }

    explicit octet4(uint32_t value) : value(value)
    {
    }

    inline uint8_t operator[](int index) const
    {
        assert(index >= 0 && index <= 3);
        return (value >> (24 - index * 8)) & 0xFF;
    }
};