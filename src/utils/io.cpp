//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "io.hpp"

#include <cstring>
#include <fstream>
#include <ostream>
#include <queue>
#include <stack>

#include <arpa/inet.h>
#include <dirent.h>
#include <net/if.h>
#include <netinet/in.h>
#include <sys/ioctl.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <netdb.h>

#include <utils/constants.hpp>
#include <utils/libc_error.hpp>

static void AppendEntries(const std::string &path, std::vector<std::string> &vec)
{
    struct dirent *files;
    DIR *dir = opendir(path.c_str());
    if (dir == nullptr)
        return;
    while ((files = readdir(dir)) != nullptr)
    {
        if (strcmp(files->d_name, ".") != 0 && strcmp(files->d_name, "..") != 0)
        {
            std::string fullPath = path;
            io::AppendPath(fullPath, files->d_name);
            vec.push_back(fullPath);
        }
    }

    closedir(dir);
}

namespace io
{

std::string ReadAllText(const std::string &file)
{
    std::ifstream ifs{};
    ifs.exceptions(std::ifstream::failbit | std::ifstream::badbit);
    ifs.open(file);
    std::string content((std::istreambuf_iterator<char>(ifs)), (std::istreambuf_iterator<char>()));
    return content;
}

void CreateDirectory(const std::string &path)
{
    if (!Exists(path))
    {
        if (mkdir(path.c_str(), 0777) != 0)
            throw LibError("Directory '" + path + "' could not be created:", errno);
        RelaxPermissions(path);
    }
}

bool Exists(const std::string &path)
{
    return IsRegularFile(path) || IsDirectory(path);
}

void WriteAllText(const std::string &path, const std::string &content)
{
    std::ofstream ofs{};
    ofs.exceptions(std::ofstream::failbit | std::ofstream::badbit);
    ofs.open(path);
    ofs << content;
    ofs.close();
    RelaxPermissions(path);
}

void RelaxPermissions(const std::string &path)
{
    if (chmod(path.c_str(), 0777) != 0)
        throw LibError("Permission could not set for '" + path + "':", errno);
}

void PreOrderEntries(const std::string &root, std::vector<std::string> &visitor)
{
    std::stack<std::string> stack{};
    stack.push(root);

    while (!stack.empty())
    {
        std::string top = stack.top();
        stack.pop();

        visitor.push_back(top);

        for (auto &entry : GetEntries(top))
            stack.push(entry);
    }
}

bool Remove(const std::string &path)
{
    int success = 0;
    int failed = 0;

    std::vector<std::string> entries;
    PreOrderEntries(path, entries);
    while (!entries.empty())
    {
        std::string s = entries.back();
        entries.pop_back();
        int r = remove(s.c_str());
        if (r == 0)
            success++;
        else
            failed++;
    }

    return failed == 0;
}

std::vector<std::string> GetEntries(const std::string &path)
{
    std::vector<std::string> res{};
    AppendEntries(path, res);
    return res;
}

std::vector<std::string> GetAllEntries(const std::string &path)
{
    std::vector<std::string> entries{};
    PreOrderEntries(path, entries);
    return entries;
}

bool IsDirectory(const std::string &path)
{
    struct stat s = {};
    if (::stat(path.c_str(), &s) == 0)
        return S_ISDIR(s.st_mode);
    if (errno == EACCES)
        throw LibError("Unable to get stats for '" + path + "':", errno);
    return false;
}

bool IsRegularFile(const std::string &path)
{
    struct stat s = {};
    if (::stat(path.c_str(), &s) == 0)
        return S_ISREG(s.st_mode);
    if (errno == EACCES)
        throw LibError("Unable to get stats for '" + path + "':", errno);
    return false;
}

std::string GetStem(const std::string &path)
{
    char *c = strdup(path.c_str());
    const char *p = strrchr(c, cons::DIR_SEPARATOR);
    if (p == nullptr)
    {
        free(c);
        return path;
    }
    std::string s{p + 1};
    free(c);
    return s;
}

void AppendPath(std::string &source, const std::string &target)
{
    if (source.empty())
    {
        source += target;
        return;
    }

    if (source[source.size() - 1] != cons::DIR_SEPARATOR)
        source += std::string(1, cons::DIR_SEPARATOR);
    source += target;
}

std::string GetIp4OfInterface(const std::string &ifName)
{
    std::string res;

    struct ifreq ifr = {};

    int fd = socket(AF_INET, SOCK_DGRAM, 0);
    if (fd <= 0)
        return "";

    ifr.ifr_addr.sa_family = AF_INET;
    strncpy(ifr.ifr_name, ifName.c_str(), IFNAMSIZ - 1);

    if (ioctl(fd, SIOCGIFADDR, &ifr))
    {
        close(fd);
        return "";
    }

    close(fd);

    auto address = ((struct sockaddr_in *)&ifr.ifr_addr)->sin_addr;

    char str[INET_ADDRSTRLEN] = {0};
    if (inet_ntop(AF_INET, &address, str, INET_ADDRSTRLEN) == nullptr)
        return "";

    return std::string{str};
}

std::string GetIp6OfInterface(const std::string &ifName)
{
    std::string res;

    struct ifreq ifr = {};

    int fd = socket(AF_INET6, SOCK_DGRAM, 0);
    if (fd <= 0)
        return "";

    ifr.ifr_addr.sa_family = AF_INET6;
    strncpy(ifr.ifr_name, ifName.c_str(), IFNAMSIZ - 1);

    if (ioctl(fd, SIOCGIFADDR, &ifr))
    {
        close(fd);
        return "";
    }

    close(fd);

    auto address = ((struct sockaddr_in *)&ifr.ifr_addr)->sin_addr;

    char str[INET6_ADDRSTRLEN] = {0};
    if (inet_ntop(AF_INET6, &address, str, INET6_ADDRSTRLEN) == nullptr)
        return "";

    return std::string{str};
}

std::string GetHostByName(const std::string &name)
{
    struct addrinfo hints = {};
    struct addrinfo *res;

    hints.ai_family = AF_UNSPEC;

    if (getaddrinfo(name.c_str(), NULL, &hints, &res))
        return "";

    if (res->ai_family == AF_INET)
    {
        char str[INET_ADDRSTRLEN] = {0};
        if (inet_ntop(AF_INET, &((struct sockaddr_in*)res->ai_addr)->sin_addr, str, INET_ADDRSTRLEN) == nullptr)
        {
            freeaddrinfo(res);
            return "";
        }
        freeaddrinfo(res);
        return std::string{str};
    }
    else if (res->ai_family == AF_INET6)
    {
        char str[INET6_ADDRSTRLEN] = {0};
        if (inet_ntop(AF_INET6, &((struct sockaddr_in6*)res->ai_addr)->sin6_addr, str, INET6_ADDRSTRLEN) == nullptr)
        {
            freeaddrinfo(res);
            return "";
        }
        freeaddrinfo(res);
        return std::string{str};
    }
    else
    {
        freeaddrinfo(res);
        return "";
    }
}

} // namespace io
