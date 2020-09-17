#include "udp_client.hpp"
#include "utils.hpp"

#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>

udp_client::udp_client(const std::string &addr, int port) : f_port(port), f_addr(addr)
{
    int r = udp_utils::get_address_info(addr, port, &f_addrinfo);
    if (r != 0 || f_addrinfo == NULL)
    {
        perror("invalid address or port");
        exit(EXIT_FAILURE);
    }
    f_socket = socket(f_addrinfo->ai_family, SOCK_DGRAM | SOCK_CLOEXEC, IPPROTO_UDP);
    if (f_socket == -1)
    {
        freeaddrinfo(f_addrinfo);
        perror("could not create socket");
        exit(EXIT_FAILURE);
    }
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
