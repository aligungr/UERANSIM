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

UdpServer::UdpServer(): sockets{}
{
    sockets.push_back(Socket::CreateUdp6());
    sockets.push_back(Socket::CreateUdp4());
}

UdpServer::UdpServer(const std::string &address, uint16_t port): sockets{}
{
    sockets.push_back(Socket::CreateAndBindUdp({address, port}));
}

int UdpServer::Receive(uint8_t *buffer, size_t bufferSize, int timeoutMs, InetAddress &outPeerAddress)
{
    // Use the first socket ready for receiving data
    // Warning: this may lead to starvation since there is no round robin implemented yet in `Socket::Select`
    std::vector<Socket> ws;
    return Socket::Select(sockets, ws, timeoutMs).receive(buffer, bufferSize, 0, outPeerAddress);
}

int UdpServer::Send(const InetAddress &address, const uint8_t *buffer, size_t bufferSize) const
{
    int version = address.getIpVersion();
    // invalid family
    if (!version)
        return -1;
    // send on first socket matching ip version
    for(const Socket &s : sockets)
    {
        if (s.getIpVersion() == version)
            return s.send(address, buffer, bufferSize);
    }
    // no socket found
    return -1;
}

UdpServer::~UdpServer()
{
    for (Socket &s : sockets)
        s.close();
}

} // namespace udp
