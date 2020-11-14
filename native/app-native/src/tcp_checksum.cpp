// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include "tcp_checksum.hpp"

#include <cstdint>
#include <cassert>
#include <iostream>
#define PROTO_TCP 6
#define PROTO_UDP 17

static uint32_t net_checksum_add(int len, const uint8_t *buf)
{
    uint32_t sum = 0;
    int i;

    for (i = 0; i < len; i++)
    {
        if (i & 1)
            sum += (uint32_t)buf[i];
        else
            sum += (uint32_t)buf[i] << 8;
    }
    return sum;
}

static uint16_t net_checksum_finish(uint32_t sum)
{
    while (sum >> 16)
        sum = (sum & 0xFFFF) + (sum >> 16);
    return ~sum;
}

static uint16_t net_checksum_tcpudp(uint16_t length, uint16_t proto,
                             const uint8_t *addrs, const uint8_t *buf)
{
    uint32_t sum = 0;

    sum += net_checksum_add(length, buf); // payload
    sum += net_checksum_add(8, addrs);    // src + dst address
    sum += proto + length;                // protocol & length
    return net_checksum_finish(sum);
}

void tcp_udp_checksum_assign(uint8_t *ip_data, int length)
{
    int hlen, plen, proto, csum_offset;
    uint16_t csum;

    if ((ip_data[0] & 0xf0) != 0x40) {
        assert(false/* not IPv4 */);
        return; 
    }
    hlen = (ip_data[0] & 0x0f) * 4;
    plen = (ip_data[2] << 8 | ip_data[3]) - hlen;
    proto = ip_data[9];

    switch (proto)
    {
    case PROTO_TCP:
        csum_offset = 16;
        break;
    case PROTO_UDP:
        csum_offset = 6;
        break;
    default:
        return;
    }

    if (plen < csum_offset + 2)
        return;

    ip_data[hlen + csum_offset] = 0;
    ip_data[hlen + csum_offset + 1] = 0;
    csum = net_checksum_tcpudp(plen, proto, ip_data + 12, ip_data + hlen);
    ip_data[hlen + csum_offset] = csum >> 8;
    ip_data[hlen + csum_offset + 1] = csum & 0xff;
}