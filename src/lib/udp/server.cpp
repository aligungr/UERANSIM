//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "server.hpp"

#include <cstring>
#include <utils/common.hpp>

namespace udp
{

UdpServer::UdpServer() : sockets{Socket::CreateUdp4(), Socket::CreateUdp6()}
{
}

UdpServer::UdpServer(const std::string &address, uint16_t port) : sockets{Socket::CreateAndBindUdp({address, port})}
{
}

int UdpServer::Receive(uint8_t *buffer, size_t bufferSize, int timeoutMs, InetAddress &outPeerAddress) const
{
    auto socket = Socket::Select(sockets, {}, timeoutMs);
    if (!socket.hasFd())
        return 0;
    return socket.receive(buffer, bufferSize, timeoutMs, outPeerAddress);
}

void UdpServer::Send(const InetAddress &address, const uint8_t *buffer, size_t bufferSize) const
{
    int version = address.getIpVersion();
    if (version != 4 && version != 6)
        throw std::runtime_error{"UdpServer::Send failure: Invalid IP version"};

    for (const Socket &s : sockets)
    {
        if (s.hasFd() && s.getIpVersion() == version)
        {
            s.send(address, buffer, bufferSize);
            return;
        }
    }

    throw std::runtime_error{"UdpServer::Send failure: No IP socket found"};
}

UdpServer::~UdpServer()
{
    for (auto &s : sockets)
        s.close();
}

} // namespace udp
