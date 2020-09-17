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

    int get_address_info(const std::string &addr, int port, addrinfo** info_out);
}