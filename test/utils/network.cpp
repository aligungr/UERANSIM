//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <cstring>

#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>

#include <ext/doctest/doctest.h>
#include <utils/network.hpp>

TEST_CASE("utils/network: two addresses are equal when their IP and port are")
{
    CHECK(InetAddress{"127.0.0.1", 4997} == InetAddress{"127.0.0.1", 4997});
    CHECK(InetAddress{"127.0.0.1", 4997} != InetAddress{"127.0.0.2", 4997});
    CHECK(InetAddress{"127.0.0.1", 4997} != InetAddress{"127.0.0.1", 4998});

    CHECK(InetAddress{"::1", 4997} == InetAddress{"::1", 4997});
    CHECK(InetAddress{"::1", 4997} != InetAddress{"::2", 4997});
    CHECK(InetAddress{"::1", 4997} != InetAddress{"::1", 4998});

    CHECK(InetAddress{"127.0.0.1", 4997} != InetAddress{"::ffff:127.0.0.1", 4997});
}

TEST_CASE("utils/network: a received address equals the same address parsed from text")
{
    // What recvfrom() fills in may differ from what getaddrinfo() does outside the IP and the port, such as in the
    // padding of a sockaddr_in.
    sockaddr_storage storage;
    std::memset(&storage, 0xAB, sizeof(storage));
    auto &sin = reinterpret_cast<sockaddr_in &>(storage);
    sin.sin_family = AF_INET;
    sin.sin_port = htons(4997);
    sin.sin_addr.s_addr = htonl(INADDR_LOOPBACK);

    CHECK(InetAddress{storage, sizeof(sockaddr_in)} == InetAddress{"127.0.0.1", 4997});
}

TEST_CASE("utils/network: an address is written as its IP and port")
{
    CHECK_EQ(InetAddress{"127.0.0.1", 4997}.toString(), "127.0.0.1:4997");
    CHECK_EQ(InetAddress{"::1", 4997}.toString(), "[::1]:4997");
}
