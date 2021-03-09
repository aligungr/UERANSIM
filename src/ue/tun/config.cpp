//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "config.hpp"

#include <arpa/inet.h>
#include <array>
#include <cerrno>
#include <cstdarg>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <fcntl.h>
#include <fstream>
#include <ifaddrs.h>
#include <iostream>
#include <linux/if_tun.h>
#include <memory>
#include <mutex>
#include <net/if.h>
#include <set>
#include <sstream>
#include <string>
#include <sys/ioctl.h>
#include <sys/select.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <vector>

#include <utils/libc_error.hpp>

#define ROUTING_TABLE_PREFIX "rt_"
#define MAX_INTERFACE_COUNT 1024

static std::mutex configMutex;

static int ExecOutput(const char *cmd, std::string &output)
{
    char buffer[128];
    std::string result;
    FILE *pipe = popen(cmd, "r");
    if (!pipe)
    {
        output = "popen() failed!";
        return -1;
    }
    try
    {
        while (fgets(buffer, sizeof buffer, pipe) != nullptr)
            result += buffer;
    }
    catch (...)
    {
        pclose(pipe);
        output = "";
        return -1;
    }
    output = result;

    int status = pclose(pipe);
    return WEXITSTATUS(status);
}

static std::string ExecStrict(const std::string &cmd)
{
    std::string output;
    if (ExecOutput(cmd.c_str(), output))
    {
        throw LibError("Command execution failed. The command was: " + cmd + ". The output is: '" + output +
                       "Command execution failure");
    }
    return output;
}

static const char *NextInterfaceName(const std::string &prefix)
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

static void TunSetIpAndUp(const char *ifName, const char *ipAddr, int mtu)
{
    ifreq ifr{};
    memset(&ifr, 0, sizeof(struct ifreq));

    sockaddr_in sai{};
    memset(&sai, 0, sizeof(struct sockaddr));

    int sockFd;
    char *p;

    sockFd = socket(AF_INET, SOCK_DGRAM, 0);

    strcpy(ifr.ifr_name, ifName);

    sai.sin_family = AF_INET;
    sai.sin_port = 0;

    sai.sin_addr.s_addr = inet_addr(ipAddr);

    p = (char *)&sai;
    memcpy((((char *)&ifr + offsetof(struct ifreq, ifr_addr))), p, sizeof(struct sockaddr));

    if (ioctl(sockFd, SIOCSIFADDR, &ifr) < 0)
        throw LibError("ioctl(SIOCSIFADDR)", errno);
    if (ioctl(sockFd, SIOCGIFFLAGS, &ifr) < 0)
        throw LibError("ioctl(SIOCGIFFLAGS)", errno);

    ifr.ifr_mtu = mtu;
    if (ioctl(sockFd, SIOCSIFMTU, &ifr) < 0)
        throw LibError("ioctl(SIOCSIFMTU)", errno);

    ifr.ifr_flags |= IFF_UP | IFF_RUNNING;
    if (ioctl(sockFd, SIOCSIFFLAGS, &ifr) < 0)
        throw LibError("ioctl(SIOCSIFFLAGS)", errno);

    close(sockFd);
}

static void ConfigureRtTables(const std::string &table_name)
{
    std::ifstream ifs;
    ifs.open("/etc/iproute2/rt_tables");
    if (!ifs.is_open() || !ifs.good())
        throw LibError("Could not open '/etc/iproute2/rt_tables'");

    std::string line;

    bool found = false;
    std::set<int> nums;

    while (std::getline(ifs, line))
    {
        auto pos = line.find('#');
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
            throw LibError("Could not open '/etc/iproute2/rt_tables'");

        ofs << "\n" << availableId << "\t" << table_name << std::endl;
        ofs.close();
    }
}

static void RemoveExistingIpRules(const std::string &ip_addr)
{
    std::string list_command = "ip rule list from " + ip_addr;
    std::string output = ExecStrict(list_command);

    std::stringstream ss;
    ss << output;

    std::vector<std::string> lines;
    std::vector<std::string> will_remove;

    std::string s;
    while (std::getline(ss, s))
        lines.push_back(s);

    for (auto &line : lines)
    {
        int num = 0;
        char from_ip[512] = {0};
        char table_name[512] = {0};

        if (sscanf(line.c_str(), "%d: from %s lookup %s", &num, from_ip, table_name) != 3)
            throw LibError("ip rule list lookup command could not parsed");

        if (!strcmp(from_ip, ip_addr.c_str()))
        {
            std::stringstream rule;
            rule << "from " << from_ip << " lookup " << table_name;
            will_remove.push_back(rule.str());
        }
    }

    for (auto &line : will_remove)
        ExecStrict("ip rule del " + line);
}

static void AddNewIpRules(const std::string &ip_addr, const std::string &table_name)
{
    std::stringstream cmd;
    cmd << "ip rule add from " << ip_addr << " table " << table_name;
    ExecStrict(cmd.str());
}

static bool IsFibTableExists(const std::string &table_name)
{
    std::string output = ExecStrict("ip route show table all");

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

static void RemoveExistingIpRoutes(const std::string &interface_name, const std::string &table_name)
{
    if (!IsFibTableExists(table_name))
        return;

    std::string list_command = "ip route list table " + table_name;

    std::string output = ExecStrict(list_command);

    std::stringstream ss;
    ss << output;

    std::vector<std::string> lines;
    std::vector<std::string> will_remove;

    std::string s;
    while (std::getline(ss, s))
        lines.push_back(s);

    for (auto &line : lines)
    {
        char if_name[IF_NAMESIZE + 8] = {0};

        if (sscanf(line.c_str(), "default dev %s scope link", if_name) != 1)
            throw LibError("ip route list command could not parsed");

        if (!strcmp(if_name, interface_name.c_str()))
        {
            std::stringstream rule;
            rule << "ip route del default dev " << interface_name << " table " << table_name;
            will_remove.push_back(rule.str());
        }
    }

    for (auto &line : will_remove)
        ExecStrict(line);
}

static void AddIpRoutes(const std::string &if_name, const std::string &table_name)
{
    std::stringstream cmd;
    cmd << "ip route add default dev " << if_name << " table " << table_name;

    std::string output = ExecStrict(cmd.str());
}

namespace nr::ue::tun
{

int AllocateTun(const char *ifPrefix, char **allocatedName)
{
    // acquire the configuration lock
    const std::lock_guard<std::mutex> lock(configMutex);

    const char *ifName = NextInterfaceName(ifPrefix);
    if (!ifName)
        throw LibError("TUN interface name could not be allocated.", errno);

    char tunName[IFNAMSIZ];
    strcpy(tunName, ifName);

    ifreq ifr{};
    int fd;

    if ((fd = open("/dev/net/tun", O_RDWR)) < 0)
        throw LibError("Open failure /dev/net/tun");

    memset(&ifr, 0, sizeof(ifr));

    ifr.ifr_flags = IFF_TUN | IFF_NO_PI;

    strncpy(ifr.ifr_name, tunName, IFNAMSIZ);

    if (ioctl(fd, TUNSETIFF, (void *)&ifr) < 0)
    {
        close(fd);
        throw LibError("ioctl(TUNSETIFF)", errno);
    }

    strcpy(tunName, ifr.ifr_name);
    if (strcmp(tunName, ifName) != 0)
        throw LibError("TUN interface name could not be allocated.");

    *allocatedName = strdup(tunName);
    return fd;
}

void ConfigureTun(const char *tunName, const char *ipAddr, int mtu, bool configureRoute)
{
    // acquire the configuration lock
    const std::lock_guard<std::mutex> lock(configMutex);

    TunSetIpAndUp(tunName, ipAddr, mtu);
    if (configureRoute)
    {
        std::string table_name = ROUTING_TABLE_PREFIX + std::string(tunName);

        ConfigureRtTables(table_name);
        RemoveExistingIpRules(ipAddr);
        AddNewIpRules(ipAddr, table_name);
        RemoveExistingIpRoutes(tunName, table_name);
        AddIpRoutes(tunName, table_name);
    }
}

} // namespace nr::ue::tun
