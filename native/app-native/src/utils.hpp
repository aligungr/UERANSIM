// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#pragma once

#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <cstdlib>
#include <cstdio>
#include <cstring>
#include <string>
#include <vector>

namespace udp_utils
{
    addrinfo make_hints();

    int get_address_info(const std::string &addr, int port, addrinfo **info_out);

    int new_socket();

    int new_socket(const std::string &src_addr, int src_port);

    sockaddr_in in_address(const std::string &addr, int port);

} // namespace udp_utils

namespace utils
{
    std::vector<uint8_t> hex_to_bytes(const std::string &hex);

    std::string bytes_to_hex(const uint8_t *data, size_t size);

    std::string bytes_to_hex(const std::vector<uint8_t> &data);

    void debug(const std::string &tag, const std::string &msg);

    uint32_t ip_to_uint32(const std::string &ip);

    std::string uint32_to_ip(uint32_t ip);

    std::vector<std::string> lines(const std::string &str);

} // namespace utils