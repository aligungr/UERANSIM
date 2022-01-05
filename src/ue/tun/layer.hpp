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
#include <memory>
#include <string>

#include <ue/types.hpp>
#include <utils/scoped_thread.hpp>

namespace nr::ue
{

class TunLayer
{
  private:
    UeTask *m_ue;
    std::array<int, 16> m_fd;
    std::unique_ptr<ScopedThread> m_receiverThread;
    int m_dice;

  public:
    explicit TunLayer(UeTask *base);
    ~TunLayer();

  public:
    std::string allocate(int psi, const std::string &ipAddress, bool configureRouting, std::string &outError);
    void release(int psi);

    void write(int psi, const uint8_t *buffer, size_t size);
    size_t read(uint8_t *buffer, size_t size, int &outPsi);

  private:
    bool performSelect(int &outPsi, int &outFd);
};

} // namespace nr::ue
