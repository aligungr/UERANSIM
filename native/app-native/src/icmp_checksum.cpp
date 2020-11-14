// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include "icmp_checksum.hpp"
#include "utils.hpp"

#include <cassert>

static uint16_t icmp_checksum(uint16_t *icmph, size_t len)
{
    uint16_t ret = 0;
    uint32_t sum = 0;
    uint16_t odd_byte;

    while (len > 1)
    {
        sum += *icmph++;
        len -= 2;
    }

    if (len == 1)
    {
        *(uint8_t *)(&odd_byte) = *(uint8_t *)icmph;
        sum += odd_byte;
    }

    sum = (sum >> 16) + (sum & 0xffff);
    sum += (sum >> 16);
    ret = ~sum;

    return ret;
}

void icmp_checksum_assign(uint8_t *data, size_t size)
{
    assert(size % 2 == 0); // Normally valid but not implemented this case

    data[2] = 0;
    data[3] = 0;

    uint16_t csum = icmp_checksum(reinterpret_cast<uint16_t*>(data), size);

    // Assuming this machine is LE
    data[2] = csum;
    data[3] = csum >> 8;
}