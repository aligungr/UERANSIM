//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "network.hpp"
#include "libc_error.hpp"

#include <cstring>

#include <arpa/inet.h>
#include <netdb.h>
#include <random>
#include <stdexcept>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>

static std::string OctetStringToIpString(const OctetString &address)
{
    if (address.length() != 4 && address.length() != 16 && address.length() != 20)
        throw std::runtime_error("Bad Inet address octet string length");

    // (If the length is 20, the address contains IPv4v6 which is 4+16 length. In this case
    //  we prefer IPv4, and take the first 4 octets.)
    int domain = (address.length() == 4 || address.length() == 20) ? AF_INET : AF_INET6;

    unsigned char buf[sizeof(struct in6_addr)] = {0};
    char str[INET6_ADDRSTRLEN] = {0};

    if (domain == AF_INET)
        std::memcpy(buf, address.data(), 4);
    else
        std::memcpy(buf, address.data(), 16);

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
        throw LibError("Bad Inet address: " + address, errno);

    if (result->ai_family != AF_INET && result->ai_family != AF_INET6)
    {
        freeaddrinfo(result);
        throw std::runtime_error("Bad Inet address: " + address);
    }

    this->len = result->ai_addrlen;
    std::memcpy(&this->storage, result->ai_addr, this->len);
    freeaddrinfo(result);
}

int InetAddress::getIpVersion() const
{
    if (storage.ss_family == AF_INET)
        return 4;
    if (storage.ss_family == AF_INET6)
        return 6;
    return 0;
}

InetAddress::InetAddress(const OctetString &address, uint16_t port) : InetAddress(OctetStringToIpString(address), port)
{
}

uint16_t InetAddress::getPort() const
{
    if (storage.ss_family == AF_INET)
    {
        auto &sin = reinterpret_cast<const sockaddr_in &>(storage);
        return static_cast<uint16_t>(ntohs(sin.sin_port));
    }
    else if (storage.ss_family == AF_INET6)
    {
        auto &sin6 = reinterpret_cast<const sockaddr_in6 &>(storage);
        return static_cast<uint16_t>(ntohs(sin6.sin6_port));
    }
    return 0;
}

Socket::Socket(int domain, int type, int protocol)
{
    int sd = socket(domain, type, protocol);
    if (sd < 0)
        throw LibError("Socket could not be created:", errno);
    this->fd = sd;
    this->domain = domain;
}

Socket Socket::CreateUdp4()
{
    return {AF_INET, SOCK_DGRAM, IPPROTO_UDP};
}

Socket Socket::CreateUdp6()
{
    return {AF_INET6, SOCK_DGRAM, IPPROTO_UDP};
}

Socket::Socket() : fd(-1), domain(0)
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
    fd_set s1;
    FD_ZERO(&s1);
    FD_SET(fd, &s1);

    // fd_set s2;
    // FD_ZERO(&s2);
    // FD_SET(fd, &s2);

    fd_set s3;
    FD_ZERO(&s3);
    FD_SET(fd, &s3);

    timeval timeout{};
    timeout.tv_sec = timeoutMs / 1000;
    timeout.tv_usec = (timeoutMs % 1000) * 1000;

    int rc = select(fd + 1, &s1, /*&s2 no write operation for unnecessary selection */ nullptr, &s3, &timeout);
    if (rc == -1)
        throw LibError("select failed: ", errno);

    if (rc > 0 && FD_ISSET(fd, &s1))
    {
        sockaddr_storage peerAddr{};
        socklen_t peerAddrLen = sizeof(struct sockaddr_storage);

        auto r = recvfrom(fd, buffer, bufferSize, 0, (struct sockaddr *)&peerAddr, &peerAddrLen);
        if (r == -1)
            throw LibError("recvfrom recv failed: ", errno);

        outAddress = InetAddress{peerAddr, peerAddrLen};
        return static_cast<int>(r);
    }

    return 0;
}

void Socket::send(const InetAddress &address, const uint8_t *buffer, size_t size) const
{
    ssize_t rc = sendto(fd, buffer, size, MSG_DONTWAIT, address.getSockAddr(), address.getSockLen());
    if (rc == -1)
    {
        int err = errno;
        if (err != EAGAIN)
            throw LibError("sendto failed: ", errno);
    }
}

bool Socket::hasFd() const
{
    return fd >= 0;
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

bool Socket::Select(const std::vector<Socket> &inReadSockets, const std::vector<Socket> &inWriteSockets,
                    std::vector<Socket> &outReadSockets, std::vector<Socket> &outWriteSockets, int timeout)
{
    assert(inReadSockets.size() + inWriteSockets.size() < FD_SETSIZE);

    int max = 0;

    fd_set readFds, writeFds;
    FD_ZERO(&readFds);
    FD_ZERO(&writeFds);

    for (const Socket &s : inReadSockets)
    {
        FD_SET(s.fd, &readFds);
        max = std::max(max, s.fd);
    }

    for (const Socket &s : inWriteSockets)
    {
        FD_SET(s.fd, &writeFds);
        max = std::max(max, s.fd);
    }

    timeval to{};
    to.tv_sec = timeout / 1000;
    to.tv_usec = (timeout % 1000) * 1000;

    int ret = select(max + 1, &readFds, &writeFds, nullptr, timeout > 0 ? &to : nullptr);
    if (ret < 0)
        return false;

    for (const Socket &s : inReadSockets)
        if (FD_ISSET(s.fd, &readFds))
            outReadSockets.push_back(s);
    for (const Socket &s : inWriteSockets)
        if (FD_ISSET(s.fd, &writeFds))
            outWriteSockets.push_back(s);

    return outReadSockets.size() + outWriteSockets.size() > 0;
}

Socket Socket::Select(const std::vector<Socket> &readSockets, const std::vector<Socket> &writeSockets, int timeout)
{
    std::vector<Socket> rs, ws;
    Select(readSockets, writeSockets, rs, ws, timeout);

    // Return a socket chosen at random from selection to avoid starvation
    auto r = static_cast<size_t>(rand());
    if (!rs.empty())
        return rs[r % rs.size()];
    if (!ws.empty())
        return ws[r % ws.size()];
    return {};
}

bool Socket::Select(const Socket &socket, int timeout)
{
    return Select({socket}, {socket}, timeout).hasFd();
}

void Socket::close()
{
    if (fd >= 0)
        ::close(fd);
    fd = -1;
}

void Socket::setReuseAddress() const
{
    int reuse = 1;
    if (setsockopt(fd, SOL_SOCKET, SO_REUSEADDR, (const char *)&reuse, sizeof(reuse)) < 0)
        throw LibError("setsockopt SO_REUSEADDR failed: ", errno);
}

InetAddress Socket::getAddress() const
{
    struct sockaddr_storage storage = {};
    socklen_t len = sizeof(storage);

    if (getsockname(fd, reinterpret_cast<struct sockaddr *>(&storage), &len) != 0)
        throw LibError("getsockname failed: ", errno);

    return {storage, len};
}

int Socket::getIpVersion() const
{
    if (domain == AF_INET6)
        return 6;
    if (domain == AF_INET)
        return 4;
    return 0;
}
