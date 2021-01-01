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
        buffer.seek(m_bitIndex);
        buffer.write(bit);
        m_bitIndex = buffer.currentIndex();
    }

    void writeBits(int value, int len)
    {
        BitBuffer buffer{m_data.data()};
        buffer.seek(m_bitIndex);
        buffer.writeBits(value, len);
        m_bitIndex = buffer.currentIndex();
    }

    int bitLength() const
    {
        return m_bitIndex;
    }

    int octetLength() const
    {
        return Bits::NearDiv(bitLength(), 8) / 8;
    }
};