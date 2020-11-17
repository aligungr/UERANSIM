// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

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