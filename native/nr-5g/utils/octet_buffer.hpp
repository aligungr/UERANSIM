#pragma once

#include "bits.hpp"

#include <cstdint>
#include <cstdlib>
#include <utility>

class OctetBuffer
{
    uint8_t *data;
    size_t index;

  public:
    explicit OctetBuffer(uint8_t *data) : data(data), index(0)
    {
    }

    inline uint8_t peek()
    {
        return data[index];
    }

    inline uint8_t read()
    {
        return data[index++];
    }

    inline uint16_t read2()
    {
        uint16_t oct0 = read();
        uint16_t oct1 = read();
        return (oct0 << 8u) | oct1;
    }

    inline uint32_t read4()
    {
        uint32_t oct0 = read();
        uint32_t oct1 = read();
        uint32_t oct2 = read();
        uint32_t oct3 = read();
        return (oct0 << 24u) | (oct1 << 16u) | (oct2 << 8u) | oct3;
    }

    inline void write(uint8_t octet)
    {
        data[index++] = octet;
    }

    inline void writeRanged(const std::initializer_list<std::pair<uint8_t, int>> &list)
    {
        write(Bits::Ranged8(list));
    }

    inline void write2(uint16_t octet)
    {
        write(octet & 0xFF);
        write((octet >> 8) & 0xFF);
    }

    inline void write4(uint32_t octet)
    {
        write(octet & 0xFF);
        write((octet >> 8) & 0xFF);
        write((octet >> 16) & 0xFF);
        write((octet >> 24) & 0xFF);
    }

    inline void write8(uint64_t octet)
    {
        write(octet & 0xFF);
        write((octet >> 8) & 0xFF);
        write((octet >> 16) & 0xFF);
        write((octet >> 24) & 0xFF);
        write((octet >> 32) & 0xFF);
        write((octet >> 40) & 0xFF);
        write((octet >> 48) & 0xFF);
        write((octet >> 56) & 0xFF);
    }

    inline size_t currentIndex() const
    {
        return index;
    }
};
