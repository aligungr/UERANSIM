//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "udp_server.hpp"
#include "udp_error.hpp"
#include "udp_internal.hpp"

#include <common.hpp>
#include <cstring>

namespace nr::udp
{

UdpServer::UdpServer(const std::string &address, uint16_t port)
{
    try
    {
        sd = CreateSocket(utils::GetIpVersion(address) == 4);
        BindSocket(sd, address, port);
    }
    catch (const UdpError &e)
    {
        CloseSocket(sd);
        throw;
    }
}

UdpServer::~UdpServer()
{
    CloseSocket(sd);
}

int UdpServer::receive(uint8_t *buffer, size_t bufferSize, int timeoutMs, PeerAddress &outPeerAddress) const
{
    return TimedReceive(sd, buffer, bufferSize, timeoutMs, outPeerAddress);
}

} // namespace nr::udp