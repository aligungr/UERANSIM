#pragma once

#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <cstdlib>
#include <cstdio>
#include <cstring>
#include <string>

namespace udp_utils
{
    addrinfo make_hints();

    int get_address_info(const std::string &addr, int port, addrinfo **info_out);

    int new_socket();

    int new_socket(const std::string &src_addr, int src_port);

    sockaddr_in in_address(const std::string &addr, int port);

} // namespace udp_utils