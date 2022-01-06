//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <array>
#include <cstddef>
#include <cstdint>

class FdBase
{
  public:
    static constexpr const int PS_START = 0;
    static constexpr const int PS_END = 15;
    static constexpr const int RLS_IP4 = 16;
    static constexpr const int RLS_IP6 = 17;

    static constexpr const int SIZE = 18;

  private:
    std::array<int, SIZE> m_ids;
    std::array<int, SIZE> m_fds;
    size_t m_dice;

  public:
    FdBase();
    ~FdBase();

  public:
    void allocate(int id, int fd);
    void release(int id);
    [[nodiscard]] bool contains(int id) const;
    size_t read(uint8_t *buffer, size_t size, int timeout, int &outId);
    void write(int id, uint8_t *buffer, size_t size);

  private:
    int performSelect(int timeout);
};