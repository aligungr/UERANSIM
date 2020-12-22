// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include "tun_config.hpp"
#include "utils.hpp"

#include <arpa/inet.h>
#include <array>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <errno.h>
#include <fcntl.h>
#include <fstream>
#include <ifaddrs.h>
#include <iostream>
#include <linux/if_tun.h>
#include <memory>
#include <net/if.h>
#include <set>
#include <sstream>
#include <stdarg.h>
#include <string>
#include <sys/ioctl.h>
#include <sys/select.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <sys/types.h>
#include <unistd.h>
#include <vector>
#include <mutex>

#define IF_PREFIX "uesimtun"
#define ROUTING_TABLE_PREFIX "rt_"
#define MAX_INTERFACE_COUNT 1024

static std::mutex config_mutex;

static int exec_output(const char *cmd, std::string &output)
{
    char buffer[128];
    std::string result = "";
    FILE *pipe = popen(cmd, "r");
    if (!pipe)
    {
        output = "popen() failed!";
        return -1;
    }
    try
    {
        while (fgets(buffer, sizeof buffer, pipe) != NULL)
            result += buffer;
    }
    catch (...)
    {
        pclose(pipe);
        output = "";
        return -1;
    }
    output = result;
    return WEXITSTATUS(pclose(pipe));
}

static std::string exec_strict(const std::string &cmd)
{
    std::string output;
    if (exec_output(cmd.c_str(), output))
    {
        throw tun_config_error({"Command execution failed.",
                                " The command was: " + cmd,
                                " The output is: '" + output,
                                "Command execution failure"});
    }
    return output;
}

static const char *next_interface_name(const std::string &prefix)
{
    std::set<std::string> names;

    struct ifaddrs *addrs, *tmp;

    getifaddrs(&addrs);
    tmp = addrs;

    while (tmp)
    {
        std::string name = tmp->ifa_name;
        if (name.rfind(prefix, 0) == 0)
            names.insert(name);
        tmp = tmp->ifa_next;
    }

    freeifaddrs(addrs);

    for (int i = 0; i < MAX_INTERFACE_COUNT; i++)
    {
        std::string name = prefix + std::to_string(i);
        if (!names.count(name))
            return strdup(name.c_str());
    }
    return nullptr;
}

static void tun_set_ip_and_up(const char *if_name, const char *ip_addr)
{
    struct ifreq ifr;
    memset(&ifr, 0, sizeof(struct ifreq));

    struct sockaddr_in sai;
    memset(&sai, 0, sizeof(struct sockaddr));

    int sockfd;
    char *p;

    sockfd = socket(AF_INET, SOCK_DGRAM, 0);

    strcpy(ifr.ifr_name, if_name);

    sai.sin_family = AF_INET;
    sai.sin_port = 0;

    sai.sin_addr.s_addr = inet_addr(ip_addr);

    p = (char *)&sai;
    memcpy((((char *)&ifr + offsetof(struct ifreq, ifr_addr))), p, sizeof(struct sockaddr));

    if (ioctl(sockfd, SIOCSIFADDR, &ifr) < 0)
        throw tun_config_error("ioctl(SIOCSIFADDR)");
    if (ioctl(sockfd, SIOCGIFFLAGS, &ifr) < 0)
        throw tun_config_error("ioctl(SIOCGIFFLAGS)");

    ifr.ifr_flags |= IFF_UP | IFF_RUNNING;

    if (ioctl(sockfd, SIOCSIFFLAGS, &ifr) < 0)
        throw tun_config_error("ioctl(SIOCSIFFLAGS)");

    close(sockfd);
}

static void configure_rt_tables(const std::string &table_name)
{
    std::ifstream ifs;
    ifs.open("/etc/iproute2/rt_tables");
    if (!ifs.is_open() || !ifs.good())
        throw tun_config_error("Could not open '/etc/iproute2/rt_tables'");

    std::string line;

    bool found = false;
    std::set<int> nums;

    while (std::getline(ifs, line))
    {
        auto pos = line.find("#");
        if (pos != std::string::npos)
            line = line.substr(0, pos);
        if (line.length() == 0)
            continue;

        std::stringstream ss;
        ss << line;

        int num;
        ss >> num >> line;

        nums.insert(num);

        if (line == table_name)
            found = true;
    }

    ifs.close();

    if (!found)
    {
        int availableId = 1000;
        while (nums.count(availableId))
            availableId++;

        std::ofstream ofs;

        ofs.open("/etc/iproute2/rt_tables", std::ios_base::app);
        if (!ofs.is_open() || !ofs.good())
            throw tun_config_error("Could not open '/etc/iproute2/rt_tables'");

        ofs << "\n"
            << availableId << "\t" << table_name << std::endl;
        ofs.close();
    }
}

static void remove_existing_ip_rules(const std::string &ip_addr)
{
    std::string list_command = "ip rule list from " + ip_addr;
    std::string output = exec_strict(list_command);

    std::stringstream ss;
    ss << output;

    std::vector<std::string> lines;
    std::vector<std::string> will_remove;

    std::string line;
    while (std::getline(ss, line))
        lines.push_back(line);

    for (auto &line : lines)
    {
        int num = 0;
        char from_ip[512] = {0};
        char table_name[512] = {0};

        if (sscanf(line.c_str(), "%d: from %s lookup %s", &num, from_ip, table_name) != 3)
            throw tun_config_error("ip rule list lookup command could not parsed");

        if (!strcmp(from_ip, ip_addr.c_str()))
        {
            std::stringstream rule;
            rule << "from " << from_ip << " lookup " << table_name;
            will_remove.push_back(rule.str());
        }
    }

    for (auto &line : will_remove)
        exec_strict("ip rule del " + line);
}

static void add_new_ip_rules(const std::string &ip_addr, const std::string &table_name)
{
    std::stringstream cmd;
    cmd << "ip rule add from " << ip_addr << " table " << table_name;
    exec_strict(cmd.str());
}

static bool is_fib_table_exists(const std::string &table_name)
{
    std::string output = exec_strict("ip route show table all");

    std::stringstream ss;
    ss << output;

    std::string token;
    while (ss >> token)
    {
        if (token == "table")
        {
            ss >> token;
            if (token == table_name)
                return true;
        }
    }

    return false;
}

static void remove_existing_ip_routes(const std::string &interface_name, const std::string &table_name)
{
    if (!is_fib_table_exists(table_name))
        return;

    std::string list_command = "ip route list table " + table_name;

    std::string output = exec_strict(list_command);

    std::stringstream ss;
    ss << output;

    std::vector<std::string> lines;
    std::vector<std::string> will_remove;

    std::string line;
    while (std::getline(ss, line))
        lines.push_back(line);

    for (auto &line : lines)
    {
        char if_name[IF_NAMESIZE + 8] = {0};

        if (sscanf(line.c_str(), "default dev %s scope link", if_name) != 1)
            throw tun_config_error("ip route list command could not parsed");

        if (!strcmp(if_name, interface_name.c_str()))
        {
            std::stringstream rule;
            rule << "ip route del default dev " << interface_name << " table " << table_name;
            will_remove.push_back(rule.str());
        }
    }

    for (auto &line : will_remove)
        exec_strict(line);
}

static void add_ip_routes(const std::string &if_name, const std::string &table_name)
{
    std::stringstream cmd;
    cmd << "ip route add default dev " << if_name << " table " << table_name;

    std::string output = exec_strict(cmd.str());
}

int tun_alloc(const char *if_prefix, char **allocated_name)
{
    // acquire the configuration lock
    const std::lock_guard<std::mutex> lock(config_mutex);

    const char *ifname = next_interface_name(if_prefix);
    if (!ifname)
        throw tun_config_error("TUN interface name could not be allocated.");

    char tun_name[IFNAMSIZ];
    strcpy(tun_name, ifname);

    struct ifreq ifr;
    int fd, err;

    if ((fd = open("/dev/net/tun", O_RDWR)) < 0)
        throw tun_config_error("Opening /dev/net/tun");

    memset(&ifr, 0, sizeof(ifr));

    ifr.ifr_flags = IFF_TUN | IFF_NO_PI;

    strncpy(ifr.ifr_name, tun_name, IFNAMSIZ);

    if ((err = ioctl(fd, TUNSETIFF, (void *)&ifr)) < 0)
    {
        close(fd);
        throw tun_config_error("ioctl(TUNSETIFF)");
    }

    strcpy(tun_name, ifr.ifr_name);
    if (strcmp(tun_name, ifname))
        throw tun_config_error("TUN interface name could not be allocated.");

    *allocated_name = strdup(tun_name);
    return fd;
}

void configure_tun_interface(const char *tun_name, const char *ip_addr, bool configure_route)
{
    // acquire the configuration lock
    const std::lock_guard<std::mutex> lock(config_mutex);

    tun_set_ip_and_up(tun_name, ip_addr);
    if (configure_route)
    {
        std::string table_name = ROUTING_TABLE_PREFIX + std::string(tun_name);

        configure_rt_tables(table_name);
        remove_existing_ip_rules(ip_addr);
        add_new_ip_rules(ip_addr, table_name);
        remove_existing_ip_routes(tun_name, table_name);
        add_ip_routes(tun_name, table_name);
    }
}
