#include "udp_server.hpp"
#include "utils.hpp"

#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>

udp_server::udp_server(const std::string &addr, int port) : f_port(port), f_addr(addr)
{
    f_socket = udp_utils::new_socket(addr, port);
}

udp_server::~udp_server()
{
    close(f_socket);
}

int udp_server::recv(char *msg, size_t max_size)
{
    return ::recv(f_socket, msg, max_size, 0);
}