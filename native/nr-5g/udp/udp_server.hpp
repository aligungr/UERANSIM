//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <network.hpp>
#include <string>

namespace udp
{

class UdpServer
{
  private:
    Socket socket;

  public:
    UdpServer();
    UdpServer(const std::string &address, uint16_t port);
    ~UdpServer();

    int Receive(uint8_t *buffer, size_t bufferSize, int timeoutMs, InetAddress &outPeerAddress) const;
    void Send(const InetAddress &address, const uint8_t *buffer, size_t bufferSize) const;
};

} // namespace udp
