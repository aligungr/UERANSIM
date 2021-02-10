//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>
#include <memory>

class UniqueBuffer
{
    uint8_t *m_data;
    size_t m_size;

  public:
    inline UniqueBuffer() noexcept : m_data(nullptr), m_size(0)
    {
    }

    inline UniqueBuffer(uint8_t *data, size_t length) noexcept : m_data(data), m_size(length)
    {
    }

    inline ~UniqueBuffer() noexcept
    {
        delete[] m_data;
        m_data = nullptr;
        m_size = 0;
    }

    UniqueBuffer(const UniqueBuffer &m) = delete;

    inline UniqueBuffer(UniqueBuffer &&m) noexcept : m_data(m.m_data), m_size(m.m_size)
    {
        m.m_data = nullptr;
        m.m_size = 0;
    }

    UniqueBuffer &operator=(const UniqueBuffer &m) = delete;

    inline UniqueBuffer &operator=(UniqueBuffer &&m) noexcept
    {
        m_data = m.m_data;
        m_size = m.m_size;

        m.m_data = nullptr;
        m.m_size = 0;

        return *this;
    }

    [[nodiscard]] inline const uint8_t *data() const
    {
        return m_data;
    }

    [[nodiscard]] inline const uint8_t *data()
    {
        return m_data;
    }

    [[nodiscard]] inline size_t size() const
    {
        return m_size;
    }
};
