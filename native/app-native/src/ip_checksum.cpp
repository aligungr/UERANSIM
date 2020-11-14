// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include "ip_checksum.hpp"
#include "ip.hpp"

static uint32_t ip_checksum_add(uint32_t current, const uint8_t *data, size_t len)
{
    uint32_t checksum = current;
    size_t left = len;
    const uint16_t *data_16 = reinterpret_cast<const uint16_t *>(data);
    while (left > 1)
    {
        checksum += *data_16;
        data_16++;
        left -= 2;
    }
    if (left)
    {
        checksum += *(uint8_t *)data_16;
    }
    return checksum;
}

static uint16_t ip_checksum_fold(uint32_t temp_sum)
{
    while (temp_sum > 0xffff)
    {
        temp_sum = (temp_sum >> 16) + (temp_sum & 0xFFFF);
    }
    return temp_sum;
}

static uint16_t ip_checksum_finish(uint32_t temp_sum)
{
    return ~ip_checksum_fold(temp_sum);
}

static uint16_t ip_checksum(const uint8_t *data, int len)
{
    uint32_t temp_sum;
    temp_sum = ip_checksum_add(0, data, len);
    return ip_checksum_finish(temp_sum);
}

void ip_checksum_assign(uint8_t *ip_data)
{
    ip_data[10] = 0;
    ip_data[11] = 0;

    size_t header_len = ip_header{ip_data}.hl() * 4;

    uint16_t csum = ip_checksum(ip_data, header_len);

    ip_data[10] = (csum) & 0xFF;
    ip_data[11] = (csum >> 8) & 0xFF;
}