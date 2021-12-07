//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <string>
#include <utils/network.hpp>

namespace udp
{

class UdpServer
{
  private:
    std::vector<Socket> sockets;

  public:
    UdpServer();
    UdpServer(const std::string &address, uint16_t port);
    ~UdpServer();

    int Receive(uint8_t *buffer, size_t bufferSize, int timeoutMs, InetAddress &outPeerAddress) const;
    void Send(const InetAddress &address, const uint8_t *buffer, size_t bufferSize) const;
};

} // namespace udp
