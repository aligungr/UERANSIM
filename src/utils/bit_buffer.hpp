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
#include <cstdlib>

class BitBuffer
{
    uint8_t *m_data;
    size_t m_index; // bit index

  public:
    explicit BitBuffer(uint8_t *data) : m_data(data), m_index(0)
    {
    }

    inline void seek(int index)
    {
        m_index = index;
    }

    inline int peek()
    {
        size_t octetIndex = m_index / 8;
        size_t bitIndex = m_index % 8;
        return (m_data[octetIndex] >> bitIndex) & 0b1;
    }

    inline int read()
    {
        size_t octetIndex = m_index / 8;
        size_t bitIndex = m_index % 8;
        m_index++;
        return (m_data[octetIndex] >> (7 - bitIndex)) & 0b1;
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

	inline int64_t readBitsLong(int len)
	{
		assert(len > 0 && len < 64);

		int64_t i = 0;
		while (len--)
		{
			i <<= 1LL;
			i |= read();
		}

		return i;
	}

    inline void write(bool bit)
    {
        size_t octetIndex = m_index / 8;
        size_t bitIndex = m_index % 8;

        std::bitset<8> oct(m_data[octetIndex]);
        oct.set(7 - bitIndex, bit);

        m_data[octetIndex] = oct.to_ulong() & 0xFF;
        m_index++;
    }

    inline void writeBits(int value, int len)
    {
        if (len == 0)
            return;

        assert(len > 0 && len <= 32);

        for (int i = 0; i < len; i++)
            write((value >> (len - 1 - i)) & 0b1);
    }

    inline void writeBits(int64_t value, int len)
    {
        if (len == 0)
            return;

        assert(len > 0 && len <= 64);

        for (int i = 0; i < len; i++)
            write((value >> (len - 1LL - i)) & 1LL);
    }

    /**
     * Returns total number of octets written. (Read and write are considered as same op.)
     * */
    inline size_t writtenOctets() const
    {
        return (m_index + (8u - (m_index % 8u)) % 8) / 8u;
    }

    inline size_t currentIndex() const
    {
        return m_index;
    }

    inline void octetAlign()
    {
        writeBits(0, (8 - (static_cast<int>(m_index) % 8)) % 8);
    }
};
