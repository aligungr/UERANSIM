//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "bits.hpp"
#include "octet.hpp"

#include <cstdint>
#include <cstdlib>
#include <utility>

class OctetString;

class OctetBuffer
{
    uint8_t *data;
    size_t index;
    size_t size;

  public:
    OctetBuffer(uint8_t *data, size_t size);
    explicit OctetBuffer(uint8_t *data);
    explicit OctetBuffer(OctetString &data);

    inline octet peek()
    {
        return data[index];
    }

    inline int peekI()
    {
        return (int)peek();
    }

    inline octet read()
    {
        return data[index++];
    }

    inline int readI()
    {
        return (int)read();
    }

    inline octet2 read2()
    {
        return octet2{read(), read()};
    }

    inline int16_t read2S()
    {
        return (int16_t)read2();
    }

    inline uint16_t read2US()
    {
        return (uint16_t)read2();
    }

    inline int read2I()
    {
        return (int)read2();
    }

    inline octet3 read3()
    {
        return {read(), read(), read()};
    }

    inline int read3I()
    {
        return (int)read3();
    }

    inline octet4 read4()
    {
        return {read(), read(), read(), read()};
    }

    inline int read4I()
    {
        return (int)read4();
    }

    inline uint32_t read4UI()
    {
        return (uint32_t)read4();
    }

    inline octet8 read8()
    {
        return {read(), read(), read(), read(), read(), read(), read(), read()};
    }

    inline uint64_t read8UL()
    {
        return (uint64_t)read8();
    }

    inline int64_t read8L()
    {
        return (int64_t)read8();
    }

    inline void write(uint8_t octet)
    {
        data[index++] = octet;
    }

    inline void writeRanged(const std::initializer_list<std::pair<uint8_t, int>> &list)
    {
        write(bits::Ranged8(list));
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

    inline bool hasNext() const
    {
        return index < size;
    }

    OctetString readOctetString(int length);
};
