#pragma once

#include <cstring>
#include <string>

class udp_client
{
public:
    udp_client(const std::string &addr, int port);
    ~udp_client();

    int get_socket() const;
    int get_port() const;
    std::string get_addr() const;

    int send(const char *msg, size_t size);

private:
    int f_socket;
    int f_port;
    std::string f_addr;
    struct addrinfo *f_addrinfo;
};
