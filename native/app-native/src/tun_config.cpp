// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include "tun_config.hpp"

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

#define IF_PREFIX "uesimtun"
#define ROUTING_TABLE_NAME "uesimtable"
#define MAX_INTERFACE_COUNT 1024
#define ROUTING_TABLE_ID 1453

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

[[noreturn]] static void fatal_error(const std::string &msg)
{
    std::cerr << "Fatal Error: " << msg << std::endl;
    exit(1);
}

static std::string exec_strict(const std::string &cmd)
{
    std::string output;
    if (exec_output(cmd.c_str(), output))
    {
        std::cerr << "Command execution failed." << std::endl;
        std::cerr << " The command was: " << cmd << std::endl;
        std::cerr << " The output is: '" << output << "'" << std::endl;
        fatal_error("Command execution failure");
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
    {
        perror("ioctl(SIOCSIFADDR)");
        exit(1);
    }
    if (ioctl(sockfd, SIOCGIFFLAGS, &ifr) < 0)
    {
        perror("ioctl(SIOCGIFFLAGS)");
        exit(1);
    }

    ifr.ifr_flags |= IFF_UP | IFF_RUNNING;

    if (ioctl(sockfd, SIOCSIFFLAGS, &ifr) < 0)
    {
        perror("ioctl(SIOCSIFFLAGS)");
        exit(1);
    }

    close(sockfd);
}

static void configure_rt_tables()
{
    std::ifstream ifs;
    ifs.open("/etc/iproute2/rt_tables");
    if (!ifs.is_open() || !ifs.good())
        fatal_error("Could not open '/etc/iproute2/rt_tables'");

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

        if (line == std::string(ROUTING_TABLE_NAME))
            found = true;
    }

    ifs.close();

    if (!found)
    {
        int availableId = ROUTING_TABLE_ID;
        while (nums.count(availableId))
            availableId++;

        std::ofstream ofs;

        ofs.open("/etc/iproute2/rt_tables", std::ios_base::app);
        if (!ofs.is_open() || !ofs.good())
            fatal_error("Could not open '/etc/iproute2/rt_tables'");

        ofs << "\n"
            << availableId << "\t" << ROUTING_TABLE_NAME << std::endl;
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
            fatal_error("ip rule list lookup command could not parsed");

        if (!strcmp(from_ip, ip_addr.c_str()) && !strcmp(table_name, ROUTING_TABLE_NAME))
        {
            std::stringstream rule;
            rule << "from " << from_ip << " lookup " << table_name;
            will_remove.push_back(rule.str());
        }
    }

    for (auto &line : will_remove)
        exec_strict("ip rule del " + line);
}

static void add_new_ip_rules(const std::string &ip_addr)
{
    std::stringstream cmd;
    cmd << "ip rule add from " << ip_addr << " table " << ROUTING_TABLE_NAME;
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

static void remove_existing_ip_routes(const std::string &interface_name)
{
    if (!is_fib_table_exists(ROUTING_TABLE_NAME))
        return;

    std::string list_command = "ip route list table " ROUTING_TABLE_NAME;

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
            fatal_error("ip route list command could not parsed");

        if (!strcmp(if_name, interface_name.c_str()))
        {
            std::stringstream rule;
            rule << "ip route del default dev " << interface_name << " table " << ROUTING_TABLE_NAME;
            will_remove.push_back(rule.str());
        }
    }

    for (auto &line : will_remove)
        exec_strict(line);
}

static void add_ip_routes(const std::string &if_name)
{
    std::stringstream cmd;
    cmd << "ip route add default dev " << if_name << " table " << ROUTING_TABLE_NAME;

    std::string output = exec_strict(cmd.str());
}

int tun_alloc(const char *if_prefix, char **allocated_name)
{
    const char *ifname = next_interface_name(if_prefix);
    if (!ifname)
        fatal_error("TUN interface name could not be allocated.");

    char tun_name[IFNAMSIZ];
    strcpy(tun_name, ifname);

    struct ifreq ifr;
    int fd, err;

    if ((fd = open("/dev/net/tun", O_RDWR)) < 0)
    {
        perror("Opening /dev/net/tun");
        return fd;
    }

    memset(&ifr, 0, sizeof(ifr));

    ifr.ifr_flags = IFF_TUN | IFF_NO_PI;

    strncpy(ifr.ifr_name, tun_name, IFNAMSIZ);

    if ((err = ioctl(fd, TUNSETIFF, (void *)&ifr)) < 0)
    {
        perror("ioctl(TUNSETIFF)");
        close(fd);
        return err;
    }

    strcpy(tun_name, ifr.ifr_name);
    if (strcmp(tun_name, ifname))
        fatal_error("TUN interface name could not be allocated.");

    *allocated_name = strdup(tun_name);
    return fd;
}

void configure_tun_interface(const char *tun_name, const char *ip_addr)
{
    tun_set_ip_and_up(tun_name, ip_addr);
    configure_rt_tables();
    remove_existing_ip_rules(ip_addr);
    add_new_ip_rules(ip_addr);
    remove_existing_ip_routes(tun_name);
    add_ip_routes(tun_name);
}

// TODO: remove following comments
/*static void example_usage()
{
    if (geteuid() != 0)
    {
        std::cerr << "Error: sudo privilage is required to invoke ioctl()" << std::endl;
        return;
    }

    char *tun_name;
    int tun_fd = tun_alloc(IF_PREFIX, &tun_name);

    configure_tun_interface(tun_name, "10.45.0.2");

    while (1)
        {;}
}
*/