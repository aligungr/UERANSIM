//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "bit_buffer.hpp"
#include "bits.hpp"

#include <cstdint>
#include <vector>

class BitString
{
    std::vector<uint8_t> m_data;
    size_t m_bitIndex;

  public:
    BitString() : m_data{}, m_bitIndex{}
    {
    }

    void write(bool bit)
    {
        BitBuffer buffer{m_data.data()};
        buffer.seek(static_cast<int>(m_bitIndex));
        buffer.write(bit);
        m_bitIndex = buffer.currentIndex();
    }

    void writeBits(int value, int len)
    {
        BitBuffer buffer{m_data.data()};
        buffer.seek(static_cast<int>(m_bitIndex));
        buffer.writeBits(value, len);
        m_bitIndex = buffer.currentIndex();
    }

    [[nodiscard]] inline int bitLength() const
    {
        return static_cast<int>(m_bitIndex);
    }

    [[nodiscard]] inline int octetLength() const
    {
        return bits::NearDiv(bitLength(), 8) / 8;
    }
};