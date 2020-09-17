#include "utils.hpp"

addrinfo udp_utils::make_hints()
{
    addrinfo hints;
    memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_DGRAM;
    hints.ai_protocol = IPPROTO_UDP;
    return hints;
}

int udp_utils::get_address_info(const std::string& addr, int port, addrinfo** info_out)
{
    char decimal_port[16];
    snprintf(decimal_port, sizeof(decimal_port), "%d", port);
    decimal_port[sizeof(decimal_port) / sizeof(decimal_port[0]) - 1] = '\0';
    addrinfo hints = udp_utils::make_hints();
    int r = getaddrinfo(addr.c_str(), decimal_port, &hints, info_out);

    if (r != 0 || *info_out == nullptr)
    {
        perror("invalid address or port for UDP socket");
        exit(EXIT_FAILURE);
    }
    
    return r;
}

int udp_utils::new_socket()
{
    int fd = socket(AF_INET, SOCK_DGRAM | SOCK_CLOEXEC, IPPROTO_UDP);
    if (fd < 0)
    {
        perror("could not create UDP socket");
        exit(EXIT_FAILURE);
    }
    return fd;
}