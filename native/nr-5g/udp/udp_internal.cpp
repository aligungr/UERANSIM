//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "udp_internal.hpp"
#include "udp_error.hpp"

#include <arpa/inet.h>
#include <common.hpp>
#include <cstring>
#include <netdb.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>

namespace nr::udp
{

[[noreturn]] static void ThrowError(const std::string &prefix)
{
    throw UdpError(prefix);
}

[[noreturn]] static void ThrowError(const std::string &prefix, int err)
{
    char *msg = strdup(strerror(err));
    std::string str(msg);
    free(msg);
    throw UdpError(prefix + str);
}

int CreateSocket(bool isIpV4)
{
    int sd = socket(isIpV4 ? AF_INET : AF_INET6, SOCK_DGRAM, IPPROTO_UDP);
    if (sd < 0)
        ThrowError("UDP socket could not be created");
    return sd;
}

void BindSocket(int sd, const std::string &address, uint16_t port)
{
    auto *addr = (sockaddr *)std::malloc(sizeof(sockaddr));
    std::memset(addr, 0, sizeof(sockaddr_in));

    int ipVersion = utils::GetIpVersion(address);

    if (ipVersion == 4)
    {
        auto addr4 = (sockaddr_in *)addr;
        addr4->sin_family = AF_INET;
        addr4->sin_port = htons(port);

        if (inet_pton(AF_INET, address.c_str(), &(addr4->sin_addr)) != 1)
            ThrowError("Bad IPv4 address.");
    }
    else if (ipVersion == 6)
        ThrowError("IPv6 for UDP is not supported yet.");
    else
        ThrowError("Bad IPv4 or IPv6 address.");

    int rc = bind(sd, addr, sizeof(sockaddr));
    if (rc != 0)
        ThrowError("UDP bind failed: ", errno);
}

void CloseSocket(int sd)
{
    close(sd);
}

int TimedReceive(int sd, uint8_t *buffer, size_t bufferSize, int timeoutMs, PeerAddress &outAddress)
{
    fd_set s;
    FD_ZERO(&s);
    FD_SET(sd, &s);

    timeval timeout{};
    timeout.tv_sec = timeoutMs / 1000;
    timeout.tv_usec = (timeoutMs % 1000) * 1000;

    int rc = select(sd + 1, &s, &s, &s, &timeout);
    if (rc == -1)
        ThrowError("UDP select failed: ", errno);

    if (rc > 0)
    {
        sockaddr_storage peer_addr{};
        socklen_t peer_addr_len = sizeof(struct sockaddr_storage);

        rc = recvfrom(sd, buffer, bufferSize, 0, (struct sockaddr *)&peer_addr, &peer_addr_len);
        if (rc == -1)
            ThrowError("UDP recv failed: ", errno);

        switch (peer_addr.ss_family)
        {
        case AF_INET: {
            auto &addr = *((sockaddr_in *)&peer_addr);
            outAddress.isIp4 = true;
            outAddress.address4 = ntohl(addr.sin_addr.s_addr);
            outAddress.port = ntohs(addr.sin_port);
            break;
        }
        case AF_INET6: {
            auto &addr = *((sockaddr_in6 *)&peer_addr);
            outAddress.isIp4 = false;
            std::memcpy(outAddress.address6, &addr.sin6_addr, 16);
            outAddress.port = ntohs(addr.sin6_port);
            break;
        }
        default:
            ThrowError("UDP invalid ss_family: ", errno);
        }

        return rc;
    }

    return 0;
}

} // namespace nr::udp