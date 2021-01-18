//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "network.hpp"
#include "libc_error.hpp"

#include <cstring>
#include <netdb.h>
#include <stdexcept>
#include <sys/socket.h>
#include <sys/types.h>

static std::string OctetStringToIpString(const OctetString &address)
{
    if (address.length() != 4 && address.length() != 16)
        throw std::runtime_error("Bad Inet address octet string length");

    int domain = address.length() == 4 ? AF_INET : AF_INET6;
    unsigned char buf[sizeof(struct in6_addr)] = {0};
    char str[INET6_ADDRSTRLEN] = {0};

    if (domain == 4)
    {
        auto *p = reinterpret_cast<in_addr *>(buf);
        p->s_addr = (in_addr_t)octet4{address.data()[0], address.data()[1], address.data()[2], address.data()[3]};
    }
    else
    {
        auto *p = reinterpret_cast<in6_addr *>(buf);
        std::memcpy(p, address.data(), 16);
    }

    if (inet_ntop(domain, buf, str, INET6_ADDRSTRLEN) == nullptr)
        throw LibError("Bad Inet address, inet_ntop failure:", errno);

    return std::string{str};
}

InetAddress::InetAddress() : storage{}, len{}
{
}

InetAddress::InetAddress(const sockaddr_storage &storage, socklen_t len) : storage{}, len{}
{
    std::memcpy(&this->storage, &storage, sizeof(storage));
    std::memcpy(&this->len, &len, sizeof(len));
}

InetAddress::InetAddress(const std::string &address) : InetAddress(address, 0)
{
}

InetAddress::InetAddress(const std::string &address, uint16_t port) : storage{}, len{}
{
    addrinfo hints{};
    memset(&hints, 0, sizeof(struct addrinfo));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_DGRAM;
    hints.ai_flags = AI_PASSIVE;
    hints.ai_protocol = 0;
    hints.ai_canonname = nullptr;
    hints.ai_addr = nullptr;
    hints.ai_next = nullptr;

    struct addrinfo *result;
    int s = getaddrinfo(address.c_str(), std::to_string(port).c_str(), &hints, &result);
    if (s != 0)
    {
        throw LibError("Bad Inet address: " + address, errno);
    }

    if (result->ai_family != AF_INET && result->ai_family == AF_INET6)
    {
        freeaddrinfo(result);
        throw std::runtime_error("Bad Inet address: " + address);
    }

    this->len = result->ai_addrlen;
    std::memcpy(&this->storage, result->ai_addr, this->len);
    freeaddrinfo(result);
}

InetAddress::InetAddress(const OctetString &address, uint16_t port) : InetAddress(OctetStringToIpString(address), port)
{
}

Socket::~Socket()
{
    if (fd)
        close(fd);
}

Socket::Socket(int domain, int type, int protocol)
{
    int sd = socket(domain, type, protocol);
    if (sd < 0)
        throw LibError("Socket could not be created:", errno);
    this->fd = sd;
}

Socket Socket::CreateUdp4()
{
    return Socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
}

Socket Socket::CreateUdp6()
{
    return Socket(AF_INET6, SOCK_DGRAM, IPPROTO_UDP);
}

Socket Socket::CreateTcp4()
{
    return Socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
}

Socket Socket::CreateTcp6()
{
    return Socket(AF_INET6, SOCK_STREAM, IPPROTO_TCP);
}

Socket::Socket() : fd(0)
{
}

void Socket::bind(const InetAddress &address) const
{
    int rc = ::bind(fd, address.getSockAddr(), address.getSockLen());
    if (rc != 0)
        throw LibError("Socket bind failed:", errno);
}

int Socket::receive(uint8_t *buffer, size_t bufferSize, int timeoutMs, InetAddress &outAddress) const
{
    fd_set s;
    FD_ZERO(&s);
    FD_SET(fd, &s);

    timeval timeout{};
    timeout.tv_sec = timeoutMs / 1000;
    timeout.tv_usec = (timeoutMs % 1000) * 1000;

    int rc = select(fd + 1, &s, &s, &s, &timeout);
    if (rc == -1)
        throw LibError("select failed: ", errno);

    if (rc > 0)
    {
        sockaddr_storage peerAddr{};
        socklen_t peerAddrLen = sizeof(struct sockaddr_storage);

        rc = recvfrom(fd, buffer, bufferSize, 0, (struct sockaddr *)&peerAddr, &peerAddrLen);
        if (rc == -1)
            throw LibError("recvfrom recv failed: ", errno);

        outAddress = InetAddress{peerAddr, peerAddrLen};
        return rc;
    }

    return 0;
}

void Socket::send(const InetAddress &address, const uint8_t *buffer, size_t size) const
{
    ssize_t rc = sendto(fd, buffer, size, MSG_DONTWAIT, address.getSockAddr(), address.getSockLen());
    if (static_cast<size_t>(rc) != size)
        throw LibError("sendto failed: ", errno);
}

Socket Socket::CreateAndBindUdp(const InetAddress &address)
{
    Socket s(address.getSockAddr()->sa_family, SOCK_DGRAM, IPPROTO_UDP);
    s.bind(address);
    return s;
}

Socket Socket::CreateAndBindTcp(const InetAddress &address)
{
    Socket s(address.getSockAddr()->sa_family, SOCK_STREAM, IPPROTO_TCP);
    s.bind(address);
    return s;
}
