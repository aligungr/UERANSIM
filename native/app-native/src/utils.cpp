// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <cassert>
#include <iostream>
#include <sstream>
#include <string>
#include <iomanip>

#include "utils.hpp"

addrinfo udp_utils::make_hints()
{
    addrinfo hints;
    memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_DGRAM;
    hints.ai_protocol = IPPROTO_UDP;
    return hints;
}

int udp_utils::get_address_info(const std::string &addr, int port, addrinfo **info_out)
{
    char decimal_port[16];
    snprintf(decimal_port, sizeof(decimal_port), "%d", port);
    decimal_port[sizeof(decimal_port) / sizeof(decimal_port[0]) - 1] = '\0';
    addrinfo hints = udp_utils::make_hints();
    int r = getaddrinfo(addr.c_str(), decimal_port, &hints, info_out);

    if (r != 0 || *info_out == nullptr)
    {
        perror("invalid address or port for UDP socket");
        exit(EXIT_FAILURE);
    }

    return r;
}

int udp_utils::new_socket()
{
    int fd = socket(AF_INET, SOCK_DGRAM | SOCK_CLOEXEC, IPPROTO_UDP);
    if (fd < 0)
    {
        perror("could not create UDP socket");
        exit(EXIT_FAILURE);
    }
    return fd;
}

int udp_utils::new_socket(const std::string &src_addr, int src_port)
{
    struct addrinfo *f_addrinfo;

    udp_utils::get_address_info(src_addr, src_port, &f_addrinfo);
    int fd = udp_utils::new_socket();
    int r = bind(fd, f_addrinfo->ai_addr, f_addrinfo->ai_addrlen);
    if (r != 0)
    {
        perror("could bind UDP socket");
        exit(EXIT_FAILURE);
    }
    return fd;
}

sockaddr_in udp_utils::in_address(const std::string &addr, int port)
{
    sockaddr_in sin;
    memset(&sin, 0, sizeof(sin));

    sin.sin_family = AF_INET;
    sin.sin_addr.s_addr = inet_addr(addr.c_str());
    sin.sin_port = htons(port);
    return sin;
}

void utils::debug(const std::string &tag, const std::string &msg)
{
    printf("[%s] %s\n", tag.c_str(), msg.c_str());
}

// TODO: IPv6
uint32_t utils::ip_to_uint32(const std::string &ip)
{
    uint32_t v;
    assert(inet_pton(AF_INET, ip.c_str(), &v) == 1);
    return v;
}

// TODO: IPv6
std::string utils::uint32_to_ip(uint32_t ip)
{
    std::stringstream ss("");
    ss << (ip >> 24 & 0xFF) << ".";
    ss << (ip >> 16 & 0xFF) << ".";
    ss << (ip >> 8 & 0xFF) << ".";
    ss << (ip & 0xFF);
    return ss.str();
}

std::vector<uint8_t> utils::hex_to_bytes(const std::string &hex)
{
    std::vector<uint8_t> bytes;

    for (unsigned int i = 0; i < hex.length(); i += 2)
    {
        std::string byteString = hex.substr(i, 2);
        uint8_t byte = (uint8_t)strtol(byteString.c_str(), nullptr, 16);
        bytes.push_back(byte);
    }

    return bytes;
}

std::string utils::bytes_to_hex(const uint8_t *data, size_t size)
{
    std::stringstream ss;
    ss << std::hex;
    for (size_t i = 0; i < size; i++)
        ss << std::setw(2) << std::setfill('0') << (int)data[i];
    return ss.str();
}

std::string utils::bytes_to_hex(const std::vector<uint8_t> &data)
{
    return bytes_to_hex(data.data(), data.size());
}

std::vector<std::string> utils::lines(const std::string &str)
{
    std::vector<std::string> strings;

    std::string::size_type pos = 0;
    std::string::size_type prev = 0;
    while ((pos = str.find("\n", prev)) != std::string::npos)
    {
        strings.push_back(str.substr(prev, pos - prev));
        prev = pos + 1;
    }

    strings.push_back(str.substr(prev));
    return strings;
}
