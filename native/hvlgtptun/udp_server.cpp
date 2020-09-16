#include "udp_server.hpp"

#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>

udp_server::udp_server(const std::string &addr, int port) : f_port(port), f_addr(addr)
{
    char decimal_port[16];
    snprintf(decimal_port, sizeof(decimal_port), "%d", f_port);
    decimal_port[sizeof(decimal_port) / sizeof(decimal_port[0]) - 1] = '\0';
    struct addrinfo hints;
    memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_DGRAM;
    hints.ai_protocol = IPPROTO_UDP;
    int r(getaddrinfo(addr.c_str(), decimal_port, &hints, &f_addrinfo));
    if (r != 0 || f_addrinfo == NULL)
    {
        perror(("invalid address or port for UDP socket: \"" + addr + ":" + decimal_port + "\"").c_str());
        exit(EXIT_FAILURE);
    }
    f_socket = socket(f_addrinfo->ai_family, SOCK_DGRAM | SOCK_CLOEXEC, IPPROTO_UDP);
    if (f_socket == -1)
    {
        freeaddrinfo(f_addrinfo);
        perror(("could not create UDP socket for: \"" + addr + ":" + decimal_port + "\"").c_str());
        exit(EXIT_FAILURE);
    }
    r = bind(f_socket, f_addrinfo->ai_addr, f_addrinfo->ai_addrlen);
    if (r != 0)
    {
        freeaddrinfo(f_addrinfo);
        close(f_socket);
        perror(("could not bind UDP socket with: \"" + addr + ":" + decimal_port + "\"").c_str());
        exit(EXIT_FAILURE);
    }
}

udp_server::~udp_server()
{
    freeaddrinfo(f_addrinfo);
    close(f_socket);
}

int udp_server::get_socket() const
{
    return f_socket;
}

int udp_server::get_port() const
{
    return f_port;
}

std::string udp_server::get_addr() const
{
    return f_addr;
}

int udp_server::recv(char *msg, size_t max_size)
{
    return ::recv(f_socket, msg, max_size, 0);
}

int udp_server::timed_recv(char *msg, size_t max_size, int max_wait_ms)
{
    fd_set s;
    FD_ZERO(&s);
    FD_SET(f_socket, &s);
    struct timeval timeout;
    timeout.tv_sec = max_wait_ms / 1000;
    timeout.tv_usec = (max_wait_ms % 1000) * 1000;
    int retval = select(f_socket + 1, &s, &s, &s, &timeout);
    if (retval == -1)
    {
        return -1;
    }
    if (retval > 0)
    {
        return ::recv(f_socket, msg, max_size, 0);
    }

    errno = EAGAIN;
    return -1;
}
