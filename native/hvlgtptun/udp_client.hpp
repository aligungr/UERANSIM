#pragma once

#include <cstring>
#include <string>

class udp_client
{
public:
    udp_client(const std::string &addr, int port);
    ~udp_client();

    int send(const char *msg, size_t size);

private:
    int f_socket;
    int f_port;
    std::string f_addr;
    struct addrinfo *f_addrinfo;
};
