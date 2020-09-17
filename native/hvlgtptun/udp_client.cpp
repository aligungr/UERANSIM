#include "udp_client.hpp"
#include "utils.hpp"

#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>

udp_client::udp_client(const std::string &addr, int port) : f_port(port), f_addr(addr)
{
    udp_utils::get_address_info(addr, port, &f_addrinfo);
    f_socket = udp_utils::new_socket();
}

udp_client::~udp_client()
{
    freeaddrinfo(f_addrinfo);
    close(f_socket);
}

int udp_client::get_socket() const
{
    return f_socket;
}

int udp_client::get_port() const
{
    return f_port;
}

std::string udp_client::get_addr() const
{
    return f_addr;
}

int udp_client::send(const char *msg, size_t size)
{
    return sendto(f_socket, msg, size, 0, f_addrinfo->ai_addr, f_addrinfo->ai_addrlen);
}
