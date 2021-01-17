//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <string>

#include <udp_utils.hpp>

namespace nr::udp
{

class UdpServer
{
  private:
    int sd;

  public:
    UdpServer(const std::string &address, uint16_t port);
    ~UdpServer();

    int receive(uint8_t *buffer, size_t bufferSize, int timeoutMs, PeerAddress &outPeerAddress) const;
};

} // namespace nr::udp
