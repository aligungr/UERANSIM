//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "udp_server.hpp"
#include "udp_error.hpp"

#include <cstring>

namespace udp
{

UdpServer::UdpServer(const std::string &address, uint16_t port) : socket{Socket::CreateAndBindUdp({address, port})}
{
}

int UdpServer::Receive(uint8_t *buffer, size_t bufferSize, int timeoutMs, InetAddress &outPeerAddress) const
{
    return socket.receive(buffer, bufferSize, timeoutMs, outPeerAddress);
}

void UdpServer::Send(const InetAddress &address, const uint8_t *buffer, size_t bufferSize) const
{
    socket.send(address, buffer, bufferSize);
}

} // namespace nr::udp