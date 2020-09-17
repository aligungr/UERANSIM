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
    return getaddrinfo(addr.c_str(), decimal_port, &hints, info_out);
}