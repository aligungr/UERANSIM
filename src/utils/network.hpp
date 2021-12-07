//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "octet_string.hpp"

#include <cstdint>
#include <string>

#include <sys/socket.h>

struct InetAddress
{
  private:
    sockaddr_storage storage;
    socklen_t len;

  public:
    InetAddress();
    explicit InetAddress(const std::string &address);
    explicit InetAddress(const OctetString &address, uint16_t port);
    InetAddress(const sockaddr_storage &storage, socklen_t len);
    InetAddress(const std::string &address, uint16_t port);

    [[nodiscard]] inline const sockaddr *getSockAddr() const
    {
        return reinterpret_cast<const sockaddr *>(&storage);
    }

    [[nodiscard]] inline socklen_t getSockLen() const
    {
        return len;
    }

    [[nodiscard]] int getIpVersion() const;
    [[nodiscard]] uint16_t getPort() const;
};

class Socket
{
  private:
    int fd;
    int domain;

  public:
    Socket();
    Socket(int domain, int type, int protocol);

  public:
    void bind(const InetAddress &address) const;
    int receive(uint8_t *buffer, size_t bufferSize, int timeoutMs, InetAddress &outAddress) const;
    void send(const InetAddress &address, const uint8_t *buffer, size_t size) const;
    void close();
    [[nodiscard]] bool hasFd() const;
    [[nodiscard]] InetAddress getAddress() const;
    [[nodiscard]] int getIpVersion() const;

    /* Socket options */
    void setReuseAddress() const;

  public:
    static Socket CreateAndBindUdp(const InetAddress &address);
    static Socket CreateAndBindTcp(const InetAddress &address);
    static Socket CreateUdp4();
    static Socket CreateUdp6();

    static bool Select(const std::vector<Socket> &inReadSockets, const std::vector<Socket> &inWriteSockets,
                       std::vector<Socket> &outReadSockets, std::vector<Socket> &outWriteSockets, int timeout = 0);

    static Socket Select(const std::vector<Socket> &readSockets, const std::vector<Socket> &writeSockets,
                         int timeout = 0);

    static bool Select(const Socket &socket, int timeout = 0);
};
