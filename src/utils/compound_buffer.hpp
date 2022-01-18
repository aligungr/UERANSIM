//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>
#include <cstdlib>

#include <utils/octet_view.hpp>

class CompoundBuffer
{
  private:
    uint8_t *m_buffer;
    size_t m_capacity;
    size_t m_cursor;
    size_t m_cmSize;
    size_t m_tailCapacity;

  public:
    explicit inline CompoundBuffer(size_t capacity)
        : m_buffer{new uint8_t[capacity]}, m_capacity{capacity}, m_cursor{capacity / 2}, m_cmSize{}, m_tailCapacity{}
    {
    }

    CompoundBuffer(const CompoundBuffer &v) = delete;

    inline ~CompoundBuffer()
    {
        delete[] m_buffer;
    }

  public:
    inline void reset()
    {
        m_cursor = m_capacity / 2;
        m_cmSize = 0;
        m_tailCapacity = 0;
    }

    inline uint8_t *cmAddress()
    {
        return m_buffer + m_cursor;
    }

    [[nodiscard]] inline size_t cmCapacity() const
    {
        return m_capacity - m_cursor;
    }

    inline void setCmSize(size_t size)
    {
        m_cmSize = size;
    }

    [[nodiscard]] size_t cmSize() const
    {
        return m_cmSize;
    }

    inline void setTailCapacity(size_t capacity)
    {
        m_tailCapacity = capacity;
    }

    [[nodiscard]] inline size_t tailCapacity() const
    {
        return m_tailCapacity;
    }

    inline uint8_t *tailAddress()
    {
        return cmAddress() - m_tailCapacity;
    }

    inline uint8_t *data()
    {
        return tailAddress();
    }

    [[nodiscard]] inline size_t size() const
    {
        return m_tailCapacity + m_cmSize;
    }
};