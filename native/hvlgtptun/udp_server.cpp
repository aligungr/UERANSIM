#include "udp_server.hpp"
#include "utils.hpp"

#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>

udp_server::udp_server(const std::string &addr, int port) : f_port(port), f_addr(addr)
{
    udp_utils::get_address_info(addr, port, &f_addrinfo);
    f_socket = udp_utils::new_socket();
    int r = bind(f_socket, f_addrinfo->ai_addr, f_addrinfo->ai_addrlen);
    if (r != 0)
    {
        perror("could not create UDP socket");
        exit(EXIT_FAILURE);
    }
}

udp_server::~udp_server()
{
    freeaddrinfo(f_addrinfo);
    close(f_socket);
}

int udp_server::recv(char *msg, size_t max_size)
{
    return ::recv(f_socket, msg, max_size, 0);
}