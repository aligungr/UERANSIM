// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include <cassert>
#include <cstdlib>
#include <iostream>
#include <iomanip>

#include "ip.hpp"
#include "utils.hpp"

#define BIT4_M(x) ((data[x] >> 4 & 0b1111))
#define BIT4_L(x) ((data[x] & 0b1111))

#define SET_BIT4_M(x)                     \
    {                                     \
        data[(x)] &= 0b00001111;          \
        data[(x)] |= (((v) << 4) & 0b11110000); \
    }
#define SET_BIT4_L(x)              \
    {                              \
        data[(x)] &= 0b11110000;   \
        data[(x)] |= ((v) & 0b00001111); \
    }

#define OCTET1(x) (data[x])
#define OCTET2(x) ((OCTET1(x) << 8) | OCTET1(x + 1))
#define OCTET4(x) ((OCTET1(x) << 24) | OCTET1(x + 1) << 16 | OCTET1(x + 2) << 8 | OCTET1(x + 3))

#define SET_OCTET1(x) \
    {                 \
        data[x] = v;  \
    }
#define SET_OCTET2(x)                \
    {                                \
        data[x] = ((v >> 8) & 0xFF); \
        data[x + 1] = (v & 0xFF);    \
    }
#define SET_OCTET4(x)                     \
    {                                     \
        data[x] = ((v >> 24) & 0xFF);     \
        data[x + 1] = ((v >> 16) & 0xFF); \
        data[x + 2] = ((v >> 8) & 0xFF);  \
        data[x + 3] = ((v)&0xFF);         \
    }

int ip_header::ver() const { return BIT4_M(0); }
void ip_header::set_ver(int v) { SET_BIT4_M(0); }

int ip_header::hl() const { return BIT4_L(0); }
void ip_header::set_hl(int v) { SET_BIT4_L(0); }

uint8_t ip_header::tos() const { return OCTET1(1); }
void ip_header::set_tos(uint8_t v) { SET_OCTET1(1); }

uint16_t ip_header::len() const { return OCTET2(2); }
void ip_header::set_len(uint16_t v) { SET_OCTET2(2); }

uint16_t ip_header::id() const { return OCTET2(4); }
void ip_header::set_id(uint16_t v) { SET_OCTET2(4); }

uint16_t ip_header::off() const { return OCTET2(6); }
void ip_header::set_off(uint16_t v) { SET_OCTET2(6); }

uint8_t ip_header::ttl() const { return OCTET1(8); }
void ip_header::set_ttl(uint8_t v) { SET_OCTET1(8); }

uint8_t ip_header::p() const { return OCTET1(9); }
void ip_header::set_p(uint8_t v) { SET_OCTET1(9); }

uint16_t ip_header::sum() const { return OCTET2(10); }
void ip_header::set_sum(uint16_t v) { SET_OCTET2(10); }

uint32_t ip_header::src() const { return OCTET4(12); }
void ip_header::set_src(uint32_t v) { SET_OCTET4(12); }

uint32_t ip_header::dst() const { return OCTET4(16); }
void ip_header::set_dst(uint32_t v) { SET_OCTET4(16); }

uint8_t *ip_header::payload() { return data + 20; }

void ip_header::debug_print() const
{
    std::cout << "ver:" << ver() << std::endl;
    std::cout << "hl:" << hl() << std::endl;
    std::cout << "tos:" << tos() << std::endl;
    std::cout << "len:" << len() << std::endl;
    std::cout << "id:" << id() << std::endl;
    std::cout << "off:" << off() << std::endl;
    std::cout << "ttl:" << ttl() << std::endl;
    std::cout << "p:" << p() << std::endl;
    std::cout << "sum:" << sum() << std::endl;
    std::cout << "src:" << src() << std::endl;
    std::cout << "dst:" << dst() << std::endl;
}

bool ip_header::has_opt() const { return hl() > 5; }

uint8_t icmp_header::type() const { return OCTET1(0); }
void icmp_header::set_type(uint8_t v) { SET_OCTET1(0); }

uint8_t icmp_header::code() const { return OCTET1(1); }
void icmp_header::set_code(uint8_t v) { SET_OCTET1(1); }

uint16_t icmp_header::checksum() const { return OCTET2(2); }
void icmp_header::set_checksum(uint16_t v) { SET_OCTET2(2); }

uint32_t icmp_header::uu() const { return OCTET4(4); }
void icmp_header::set_uu(uint32_t v) { SET_OCTET4(4); }

uint16_t icmp_header::id() const { return OCTET2(4); }
void icmp_header::set_id(uint16_t v) { SET_OCTET2(4); }

uint16_t icmp_header::sequence() const { return OCTET2(6); }
void icmp_header::set_sequence(uint16_t v) { SET_OCTET2(6); }

uint32_t icmp_header::gateway() const { return uu(); }
void icmp_header::set_gateway(uint32_t v) { set_uu(v); }

uint16_t icmp_header::mtu() const { return OCTET2(10); }
void icmp_header::set_mtu(uint16_t v) { SET_OCTET2(10); }

void ip_packet::print_data() const
{
    std::cout << utils::bytes_to_hex(this->data, this->size) << std::endl;
}

uint16_t udp_header::source() const { return OCTET2(0); }
void udp_header::set_source(uint16_t v) { SET_OCTET2(0); }

uint16_t udp_header::dest() const { return OCTET2(2); }
void udp_header::set_dest(uint16_t v) { SET_OCTET2(2); }

uint16_t udp_header::len() const { return OCTET2(4); }
void udp_header::set_len(uint16_t v) { SET_OCTET2(4); }

uint16_t udp_header::check() const { return OCTET2(6); }
void udp_header::set_check(uint16_t v) { SET_OCTET2(6); }

uint8_t *udp_header::payload() { return data + 8; }

uint16_t tcp_header::source() const { return OCTET2(0); }
void tcp_header::set_source(uint16_t v) { SET_OCTET2(0); }

uint16_t tcp_header::dest() const { return OCTET2(2); }
void tcp_header::set_dest(uint16_t v) { SET_OCTET2(2); }

uint32_t tcp_header::seq() const { return OCTET4(4); }
void tcp_header::set_seq(uint32_t v) { SET_OCTET4(4); }

uint32_t tcp_header::ack() const { return OCTET4(8); }
void tcp_header::set_ack(uint32_t v) { SET_OCTET4(8); }

int tcp_header::off() const { return OCTET1(12) >> 4 & 0xF; }
void tcp_header::set_off(int v) { v &= 0xF; v <<= 4; SET_OCTET1(12); }

uint8_t tcp_header::flags() const { return OCTET1(13); }
void tcp_header::set_flags(uint8_t v) { SET_OCTET1(13); }

uint16_t tcp_header::window() const { return OCTET2(14); }
void tcp_header::set_window(uint16_t v) { SET_OCTET2(14); }

uint16_t tcp_header::check() const { return OCTET2(16); }
void tcp_header::set_check(uint16_t v) { SET_OCTET2(16); }

uint16_t tcp_header::urp() const { return OCTET2(18); }
void tcp_header::set_urp(uint16_t v) { SET_OCTET2(18); }

uint8_t *tcp_header::payload() { return data + 4 * off(); }
