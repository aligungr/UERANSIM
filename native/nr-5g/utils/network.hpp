//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>
#include <libnet.h>
#include <octet_string.hpp>
#include <string>

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

    inline const sockaddr *getSockAddr() const
    {
        return reinterpret_cast<const sockaddr *>(&storage);
    }

    inline socklen_t getSockLen() const
    {
        return len;
    }
};

class Socket
{
  private:
    int fd;

  public:
    Socket();
    Socket(int domain, int type, int protocol);

  public:
    void bind(const InetAddress &address) const;
    int receive(uint8_t *buffer, size_t bufferSize, int timeoutMs, InetAddress &outAddress) const;
    void send(const InetAddress &address, const uint8_t *buffer, size_t size) const;
    bool hasFd() const;
    void close();

    /* Socket options */
    void setReuseAddress() const;

  public:
    static Socket CreateAndBindUdp(const InetAddress &address);
    static Socket CreateAndBindTcp(const InetAddress &address);
    static Socket CreateUdp4();
    static Socket CreateUdp6();
    static Socket CreateTcp4();
    static Socket CreateTcp6();

    static bool Select(const std::vector<Socket> &inReadSockets, const std::vector<Socket> &inWriteSockets,
                       std::vector<Socket> &outReadSockets, std::vector<Socket> &outWriteSockets, int timeout = 0);

    static Socket Select(const std::vector<Socket> &readSockets, const std::vector<Socket> &writeSockets,
                         int timeout = 0);

    static bool Select(const Socket &socket, int timeout = 0);
};