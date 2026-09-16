//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
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

#include <lib/app/base_app.hpp>
#include <utils/libc_error.hpp>

#define ROUTING_TABLE_PREFIX "rt_"
#define MAX_INTERFACE_COUNT 1024
#define MAX_NAMESPACE_NAME_LEN 256

static std::mutex configMutex;
// Guards the namespace deletion registry only. Kept separate from configMutex (which is held across
// blocking 'ip' executions) so the at-exit cleanup never blocks on a long-running command.
static std::mutex namespaceRegistryMutex;
static char namespacesToDelete[MAX_INTERFACE_COUNT][MAX_NAMESPACE_NAME_LEN];
static int namespacesToDeleteCount = 0;
static int initialized = 0;

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

static std::string ExecLoose(const std::string &cmd)
{
    std::string output;
    if (ExecOutput(cmd.c_str(), output))
        return "";
    return output;
}

static bool IsValidNamespaceName(const std::string &nsName)
{
    return !nsName.empty() && nsName.size() < MAX_NAMESPACE_NAME_LEN && nsName.find('/') == std::string::npos;
}

static int NetmaskToPrefixLength(const std::string &netmask)
{
    in_addr addr{};
    if (inet_pton(AF_INET, netmask.c_str(), &addr) != 1)
        return -1;

    uint32_t mask = ntohl(addr.s_addr);
    int prefixLength = 0;
    bool zeroSeen = false;
    for (int bit = 31; bit >= 0; --bit)
    {
        bool set = (mask & (1u << bit)) != 0;
        if (set)
        {
            if (zeroSeen)
                return -1;
            ++prefixLength;
        }
        else
        {
            zeroSeen = true;
        }
    }

    return prefixLength;
}

static bool LinksHaveNonLoopback(const std::string &linkOutput)
{
    // 'ip -o link show' prints one interface per line as "<index>: <name>: ...".
    std::stringstream ss(linkOutput);
    std::string line;
    while (std::getline(ss, line))
    {
        std::string index, name;
        std::stringstream ls(line);
        ls >> index >> name;
        if (!name.empty() && name != "lo:")
            return true;
    }
    return false;
}

static void SaveNamespaceForDeletion(const std::string &nsName)
{
    const std::lock_guard<std::mutex> lock(namespaceRegistryMutex);

    if (!IsValidNamespaceName(nsName) || namespacesToDeleteCount >= MAX_INTERFACE_COUNT)
        return;

    for (int i = 0; i < namespacesToDeleteCount; ++i)
    {
        if (std::strcmp(namespacesToDelete[i], nsName.c_str()) == 0)
            return;
    }

    std::snprintf(namespacesToDelete[namespacesToDeleteCount++], MAX_NAMESPACE_NAME_LEN, "%s", nsName.c_str());
}

static void RemoveSavedNamespace(const std::string &nsName)
{
    const std::lock_guard<std::mutex> lock(namespaceRegistryMutex);

    for (int i = 0; i < namespacesToDeleteCount; ++i)
    {
        if (std::strcmp(namespacesToDelete[i], nsName.c_str()) == 0)
        {
            for (int j = i; j < namespacesToDeleteCount - 1; ++j)
                std::snprintf(namespacesToDelete[j], MAX_NAMESPACE_NAME_LEN, "%s", namespacesToDelete[j + 1]);
            --namespacesToDeleteCount;
            return;
        }
    }
}

static void DeleteSavedNamespaces()
{
    // Snapshot the registry under the lock, then run the deletions outside it so we never hold a
    // mutex while a blocking 'ip' command executes.
    std::vector<std::string> namespaces;
    {
        const std::lock_guard<std::mutex> lock(namespaceRegistryMutex);
        for (int i = 0; i < namespacesToDeleteCount; ++i)
            namespaces.emplace_back(namespacesToDelete[i]);
        namespacesToDeleteCount = 0;
    }

    for (const auto &ns : namespaces)
        ExecLoose("ip netns delete " + ns);
}

static void InitializeTunHandling()
{
    if (initialized)
        return;

    // Reuse the framework's signal/exit handling instead of installing our own handlers, which would
    // otherwise override app::BaseSignalHandler. RunAtExit is the same mechanism proc_table uses.
    app::RunAtExit(DeleteSavedNamespaces);
    initialized = 1;
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

static void TunSetIpAndUp(const char *ifName, const char *ipAddr, const char *netmask, int mtu)
{
    ifreq ifr{};
    memset(&ifr, 0, sizeof(struct ifreq));

    sockaddr_in sai{};
    memset(&sai, 0, sizeof(struct sockaddr));
    
    sockaddr_in netmask_addr{};
    memset(&netmask_addr, 0, sizeof(struct sockaddr_in));

    netmask_addr.sin_family = AF_INET;
    netmask_addr.sin_addr.s_addr = inet_addr(netmask);

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

    memcpy(&ifr.ifr_netmask, &netmask_addr, sizeof(struct sockaddr));
    if (ioctl(sockFd, SIOCSIFNETMASK, &ifr) < 0)
	throw LibError("ioctl(SIOCSIFNETMASK)", errno);

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

// Brings the interface up and sets its MTU without assigning an IPv4 address. Used for IPv6-only PDU
// sessions where no IPv4 address is available.
static void TunSetMtuAndUp(const char *ifName, int mtu)
{
    ifreq ifr{};
    memset(&ifr, 0, sizeof(struct ifreq));

    int sockFd = socket(AF_INET, SOCK_DGRAM, 0);
    strcpy(ifr.ifr_name, ifName);

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

// Assigns the link-local IPv6 address derived from the network-provided interface identifier. The
// global address is later obtained by the kernel itself via stateless address autoconfiguration
// (RFC 4862) once a Router Advertisement arrives over the PDU session.
static void TunSetIpv6Address(const std::string &ifName, const std::string &ipv6Addr, int prefixLength)
{
    ExecStrict("ip -6 addr add " + ipv6Addr + "/" + std::to_string(prefixLength) + " dev " + ifName + " scope link");
}

// Best-effort sysctl write; not fatal since the kernel defaults (accept_ra=1, autoconf=1) are already
// sufficient for autoconfiguration on a non-forwarding host in most cases.
static void SetIpv6SysctlParam(const std::string &ifName, const std::string &param, const std::string &value,
                                const std::string &nsName, bool useNamespace)
{
    std::string inner = "echo " + value + " > /proc/sys/net/ipv6/conf/" + ifName + "/" + param;
    std::string cmd = useNamespace ? ("ip netns exec " + nsName + " sh -c \"" + inner + "\"") : inner;
    ExecLoose(cmd);
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

int AllocateTun(const char *ifPrefix, char **allocatedName, const char *nsName, bool useNamespace)
{
    // acquire the configuration lock
    const std::lock_guard<std::mutex> lock(configMutex);

    if (useNamespace)
        InitializeTunHandling();

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

    if (useNamespace)
    {
        std::string namespaceName = nsName ? nsName : "";
        if (!IsValidNamespaceName(namespaceName))
        {
            close(fd);
            throw LibError("Invalid namespace name.");
        }

        // If the namespace already exists, reclaim it only when stale. A UE's TUN device is
        // non-persistent and dies with the process, so a crashed UE leaves a namespace with only 'lo';
        // one that still holds a device belongs to a live UE and must not be touched.
        std::string existingLinks = ExecLoose("ip netns exec " + namespaceName + " ip -o link show");
        if (!existingLinks.empty())
        {
            if (LinksHaveNonLoopback(existingLinks))
            {
                close(fd);
                throw LibError("Network namespace already in use: " + namespaceName);
            }
            ExecLoose("ip netns delete " + namespaceName);
        }

        bool namespaceCreated = false;
        try
        {
            ExecStrict("ip netns add " + namespaceName);
            namespaceCreated = true;
            SaveNamespaceForDeletion(namespaceName);
            ExecLoose("ip netns exec " + namespaceName + " ip link set lo up");
            ExecStrict("ip link set " + std::string(tunName) + " netns " + namespaceName);
            ExecStrict("ip netns exec " + namespaceName + " ip link show " + std::string(tunName));
        }
        catch (...)
        {
            // Roll back only what we created: close the fd and, if we added the namespace, remove it.
            close(fd);
            if (namespaceCreated)
            {
                ExecLoose("ip netns delete " + namespaceName);
                RemoveSavedNamespace(namespaceName);
            }
            throw;
        }
    }

    *allocatedName = strdup(tunName);
    return fd;
}

void ConfigureTun(const char *tunName, const char *ipv4Addr, const char *netmask, const char *ipv6Addr,
                  int ipv6PrefixLength, int mtu, const char *nsName, bool useNamespace, bool configureRoute)
{
    // acquire the configuration lock
    const std::lock_guard<std::mutex> lock(configMutex);

    bool hasIpv4 = ipv4Addr != nullptr && *ipv4Addr != '\0';
    bool hasIpv6 = ipv6Addr != nullptr && *ipv6Addr != '\0';
    if (!hasIpv4 && !hasIpv6)
        throw LibError("No IPv4 or IPv6 address to configure on the TUN interface.");

    if (useNamespace)
    {
        std::string namespaceName = nsName ? nsName : "";
        if (!IsValidNamespaceName(namespaceName))
            throw LibError("Invalid namespace name.");

        if (hasIpv4)
        {
            int prefixLength = NetmaskToPrefixLength(netmask ? netmask : "");
            if (prefixLength < 0)
                throw LibError("Invalid netmask.");
            ExecStrict("ip netns exec " + namespaceName + " ip addr add " + std::string(ipv4Addr) + "/" +
                       std::to_string(prefixLength) + " dev " + tunName);
        }
        if (hasIpv6)
        {
            ExecStrict("ip netns exec " + namespaceName + " ip -6 addr add " + std::string(ipv6Addr) + "/" +
                       std::to_string(ipv6PrefixLength) + " dev " + tunName + " scope link");
            SetIpv6SysctlParam(tunName, "accept_ra", "2", namespaceName, true);
            SetIpv6SysctlParam(tunName, "autoconf", "1", namespaceName, true);
        }

        ExecStrict("ip netns exec " + namespaceName + " ip link set " + tunName + " mtu " + std::to_string(mtu));
        ExecStrict("ip netns exec " + namespaceName + " ip link set " + tunName + " up");
        if (configureRoute)
        {
            if (hasIpv4)
                ExecStrict("ip netns exec " + namespaceName + " ip route replace default dev " + tunName);
            if (hasIpv6)
                ExecStrict("ip netns exec " + namespaceName + " ip -6 route replace default dev " + tunName);
        }
        return;
    }

    if (hasIpv4)
        TunSetIpAndUp(tunName, ipv4Addr, netmask, mtu);
    else
        TunSetMtuAndUp(tunName, mtu);

    if (hasIpv6)
    {
        TunSetIpv6Address(tunName, ipv6Addr, ipv6PrefixLength);
        SetIpv6SysctlParam(tunName, "accept_ra", "2", "", false);
        SetIpv6SysctlParam(tunName, "autoconf", "1", "", false);
    }

    if (configureRoute)
    {
        if (hasIpv4)
        {
            std::string table_name = ROUTING_TABLE_PREFIX + std::string(tunName);

            ConfigureRtTables(table_name);
            RemoveExistingIpRules(ipv4Addr);
            AddNewIpRules(ipv4Addr, table_name);
            RemoveExistingIpRoutes(tunName, table_name);
            AddIpRoutes(tunName, table_name);
        }
        if (hasIpv6)
        {
            // Unlike IPv4, the UE's global IPv6 address is not known until stateless address
            // autoconfiguration completes, so it cannot be used up front for source based policy
            // routing (as is done for IPv4 above via 'rt_tables'). A plain default route is installed
            // instead, which is sufficient for a single TUN interface. Multiple concurrent IPv6 PDU
            // sessions without namespaces should use 'useNamespace' for proper isolation.
            ExecLoose("ip -6 route replace default dev " + std::string(tunName));
        }
    }
}

void CleanupTun(const char *tunName, const char *nsName, bool useNamespace)
{
    const std::lock_guard<std::mutex> lock(configMutex);

    if (!useNamespace || nsName == nullptr || *nsName == '\0')
        return;

    std::string namespaceName = nsName;
    ExecLoose("ip netns delete " + namespaceName);
    RemoveSavedNamespace(namespaceName);
}

} // namespace nr::ue::tun
