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

inline std::vector<char> HexToBytes(const std::string &hex)
{
    std::vector<char> bytes;

    for (unsigned int i = 0; i < hex.length(); i += 2)
    {
        std::string byteString = hex.substr(i, 2);
        char byte = (char)strtol(byteString.c_str(), nullptr, 16);
        bytes.push_back(byte);
    }

    return bytes;
}