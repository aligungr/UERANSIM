#pragma once

#include <bitset>
#include <cassert>
#include <cstdint>
#include <cstdlib>

class BitBuffer
{
    uint8_t *data;
    size_t index; // bit index

  public:
    explicit BitBuffer(uint8_t *data) : data(data), index(0)
    {
    }

    inline int peek()
    {
        size_t octetIndex = index / 8;
        size_t bitIndex = index % 8;
        return (data[octetIndex] >> bitIndex) & 0b1;
    }

    inline int read()
    {
        size_t octetIndex = index / 8;
        size_t bitIndex = index % 8;
        index++;
        return (data[octetIndex] >> (7 - bitIndex)) & 0b1;
    }

    inline int readBits(int len)
    {
        assert(len > 0 && len < 32);

        int i = 0;
        while (len--)
        {
            i <<= 1;
            i |= read();
        }

        return i;
    }

    inline void write(bool bit)
    {
        size_t octetIndex = index / 8;
        size_t bitIndex = index % 8;

        std::bitset<8> oct(data[octetIndex]);
        oct.set(7 - bitIndex, bit);

        data[octetIndex] = oct.to_ulong() & 0xFF;
        index++;
    }

    inline void writeBits(int value, int len)
    {
        if (len == 0)
            return;

        assert(len > 0 && len < 32);

        for (int i = 0; i < len; i++)
            write((value >> (len - 1 - i)) & 0b1);
    }

    /**
     * Returns total number of octets written. (Read and write are considered as same op.)
     * */
    inline size_t writtenOctets() const
    {
        return (index + (8u - (index % 8u)) % 8) / 8u;
    }

    inline void octetAlign()
    {
        writeBits(0, (8 - (static_cast<int>(index) % 8)) % 8);
    }
};
