#pragma once

#include <cstring>
#include <string>

class udp_server
{
public:
    udp_server(const std::string &addr, int port);
    ~udp_server();

    int get_socket() const;
    int get_port() const;
    std::string get_addr() const;

    int recv(char *msg, size_t max_size);
    int timed_recv(char *msg, size_t max_size, int max_wait_ms);

private:
    int f_socket;
    int f_port;
    std::string f_addr;
    struct addrinfo *f_addrinfo;
};