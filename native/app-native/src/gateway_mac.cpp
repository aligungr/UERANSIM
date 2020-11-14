// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include "gateway_mac.hpp"
#include <cstdlib>
#include <string>
#include <vector>
#include <cstdio>
#include <cstring>

#define ARP_CACHE "/proc/net/arp"
#define ARP_STRING_LEN 200
#define ARP_BUFFER_LEN (ARP_STRING_LEN + 1)

#define ARP_LINE_FORMAT "%100s %*s 0x%100s %100s %*s %100s"

struct entry
{
    std::string ip;
    std::string device;
    std::string mac;
};

static std::vector<entry> list_entries()
{
    std::vector<entry> res;

    FILE *arpCache = fopen(ARP_CACHE, "r");
    if (!arpCache)
    {
        perror("Arp Cache: Failed to open file \"" ARP_CACHE "\"");
        exit(EXIT_FAILURE);
        return res;
    }

    char header[ARP_BUFFER_LEN];
    if (!fgets(header, sizeof(header), arpCache))
    {
        exit(EXIT_FAILURE);
        return res;
    }

    char ipAddr[ARP_BUFFER_LEN], hwAddr[ARP_BUFFER_LEN], device[ARP_BUFFER_LEN], state[ARP_BUFFER_LEN];
    while (fscanf(arpCache, ARP_LINE_FORMAT, ipAddr, state, hwAddr, device) == 4)
        res.push_back({ipAddr, device, hwAddr});
    fclose(arpCache);

    return res;
}

char *find_gateway_mac(const char *interface, const char *ip)
{
    std::string iface = interface;
    std::string ipadd = ip;

    bool found = false;
    char *mac = nullptr;

    for (auto &e : list_entries())
    {
        if (e.device == iface && e.ip == ipadd)
        {
            if (found)
            {
                puts("gw failure: multiple found");
                exit(EXIT_FAILURE);
                return nullptr;
            }
            else
            {
                found = true;
                mac = strdup(e.mac.c_str());
            }
        }
    }

    if (!found)
    {
        puts("gw failure: not found");
        exit(EXIT_FAILURE);
        return nullptr;
    }

    return mac;
}