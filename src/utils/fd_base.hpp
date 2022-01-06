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
  private:
    std::array<int, 32> m_ids;
    std::array<int, 32> m_fds;
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