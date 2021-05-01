//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
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

// TODO: add bound check
class OctetView
{
    const uint8_t *data;
    mutable size_t index;
    size_t size;

  public:
    OctetView(const uint8_t *data, size_t size);
    explicit OctetView(const OctetString &data);

    inline octet peek() const
    {
        return data[index];
    }

    inline octet peek(int offset) const
    {
        return data[index + offset];
    }

    inline int peekI() const
    {
        return (int)peek();
    }

    inline int peekI(int offset) const
    {
        return (int)peek(offset);
    }

    inline octet read() const
    {
        return data[index++];
    }

    inline int readI() const
    {
        return (int)read();
    }

    inline octet2 read2() const
    {
        return octet2{read(), read()};
    }

    inline uint16_t read2US() const
    {
        return (uint16_t)read2();
    }

    inline int read2I() const
    {
        return (int)read2();
    }

    inline octet3 read3() const
    {
        return {read(), read(), read()};
    }

    inline int read3I() const
    {
        return (int)read3();
    }

    inline octet4 read4() const
    {
        return {read(), read(), read(), read()};
    }

    inline int read4I() const
    {
        return (int)read4();
    }

    inline uint32_t read4UI() const
    {
        return (uint32_t)read4();
    }

    inline octet8 read8() const
    {
        return {read(), read(), read(), read(), read(), read(), read(), read()};
    }

    inline uint64_t read8UL() const
    {
        return (uint64_t)read8();
    }

    inline int64_t read8L() const
    {
        return (int64_t)read8();
    }

    inline size_t currentIndex() const
    {
        return index;
    }

    inline bool hasNext() const
    {
        return index < size;
    }

    OctetString readOctetString(int length) const;
    OctetString readOctetString(size_t length) const;
    OctetString readOctetString() const;
    std::string readUtf8String(int length) const;
    std::string readUtf8String(size_t length) const;
};
