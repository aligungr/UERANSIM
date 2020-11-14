// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#pragma once

#include <cstdint>
#include <cstddef>
#include <vector>
#include <netinet/ip.h>

class ip_header
{
    uint8_t *data;

public:
    ip_header(uint8_t *pData) : data(pData) {}

    int ver() const;
    void set_ver(int v);

    int hl() const;
    void set_hl(int v);

    uint8_t tos() const;
    void set_tos(uint8_t v);

    uint16_t len() const;
    void set_len(uint16_t v);

    uint16_t id() const;
    void set_id(uint16_t v);

    uint16_t off() const;
    void set_off(uint16_t v);

    uint8_t ttl() const;
    void set_ttl(uint8_t v);

    uint8_t p() const;
    void set_p(uint8_t v);

    uint16_t sum() const;
    void set_sum(uint16_t v);

    uint32_t src() const;
    void set_src(uint32_t v);

    uint32_t dst() const;
    void set_dst(uint32_t v);

    uint8_t *payload();

    void debug_print() const;

    bool has_opt() const;
};

struct ip_packet
{
    uint8_t *data;
    size_t size;

    ip_packet(uint8_t *pData, size_t nSize) : data(pData), size(nSize) {}

    ip_header ip_hdr() { return ip_header{data}; }

    void print_data() const;
};

class icmp_header
{
    uint8_t *data;

public:
    icmp_header(uint8_t *pData) : data(pData) {}

    uint8_t type() const;
    void set_type(uint8_t v);

    uint8_t code() const;
    void set_code(uint8_t v);

    uint16_t checksum() const;
    void set_checksum(uint16_t v);

    uint32_t uu() const;
    void set_uu(uint32_t v);

    uint16_t id() const;
    void set_id(uint16_t v);

    uint16_t sequence() const;
    void set_sequence(uint16_t v);

    uint32_t gateway() const;
    void set_gateway(uint32_t v);

    uint16_t mtu() const;
    void set_mtu(uint16_t v);
};

class udp_header
{
    uint8_t *data;

public:
    udp_header(uint8_t *pData) : data(pData) {}

    uint16_t source() const;
    void set_source(uint16_t v);

    uint16_t dest() const;
    void set_dest(uint16_t v);

    uint16_t len() const;
    void set_len(uint16_t v);

    uint16_t check() const;
    void set_check(uint16_t v);

    uint8_t *payload();
};

class tcp_header
{
    uint8_t *data;

public:
    tcp_header(uint8_t *pData) : data(pData) {}

    uint16_t source() const;
    void set_source(uint16_t v);

    uint16_t dest() const;
    void set_dest(uint16_t v);

    uint32_t seq() const;
    void set_seq(uint32_t v);

    uint32_t ack() const;
    void set_ack(uint32_t v);

    int off() const;
    void set_off(int v);

    uint8_t flags() const;
    void set_flags(uint8_t v);

    uint16_t window() const;
    void set_window(uint16_t v);

    uint16_t check() const;
    void set_check(uint16_t v);

    uint16_t urp() const;
    void set_urp(uint16_t v);

    uint8_t *payload();
};