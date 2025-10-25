//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "common.hpp"
#include "constants.hpp"

#include <cstring>
#include <algorithm>
#include <atomic>
#include <cctype>
#include <chrono>
#include <regex>
#include <sstream>
#include <stdexcept>
#include <thread>

#include <arpa/inet.h>

static_assert(sizeof(char) == sizeof(uint8_t));
static_assert(sizeof(int) == sizeof(uint32_t));
static_assert(sizeof(long) == sizeof(uint32_t) || sizeof(long) == sizeof(uint64_t));
static_assert(sizeof(float) == sizeof(uint32_t));
static_assert(sizeof(double) == sizeof(uint64_t));
static_assert(sizeof(long long) == sizeof(uint64_t));

static std::atomic<int> g_idCounter = 1;

static bool IPv6FromString(const char *szAddress, uint8_t *address)
{
    auto asciiToHex = [](char c) -> int {
        c |= 0x20;
        if (c >= '0' && c <= '9')
            return c - '0';
        else if (c >= 'a' && c <= 'f')
            return (c - 'a') + 10;
        else
            return -1;
    };

    uint16_t acc = 0;
    uint8_t colons = 0;
    uint8_t pos = 0;

    memset(address, 0, 16);

    for (uint8_t i = 1; i <= 39; i++)
    {
        if (szAddress[i] == ':')
        {
            if (szAddress[i - 1] == ':')
                colons = 14;
            else if (colons)
                colons -= 2;
        }
        else if (szAddress[i] == '\0')
            break;
    }
    for (uint8_t i = 0; i <= 39 && pos < 16; i++)
    {
        if (szAddress[i] == ':' || szAddress[i] == '\0')
        {
            address[pos] = acc >> 8;
            address[pos + 1] = acc;
            acc = 0;

            if (colons && i && szAddress[i - 1] == ':')
                pos = colons;
            else
                pos += 2;
        }
        else
        {
            int val = asciiToHex(szAddress[i]);
            if (val == -1)
                return false;
            else
            {
                acc <<= 4;
                acc |= static_cast<uint8_t>(val);
            }
        }
        if (szAddress[i] == '\0')
            break;
    }
    return true;
}

int utils::GetIpVersion(const std::string &address)
{
    const std::regex regex4(R"(\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\.|$)){4}\b)");
    const std::regex regex6(
        "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-"
        "9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-"
        "fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-"
        "9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|"
        "fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,"
        "1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:(("
        "25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))");

    if (std::regex_match(address, regex4))
        return 4;
    if (std::regex_match(address, regex6))
        return 6;
    return 0;
}

std::vector<uint8_t> utils::HexStringToVector(const std::string &hex)
{
    if (hex.length() % 2 != 0)
        throw std::runtime_error("hex string has an odd length");

    for (char c : hex)
    {
        if (c >= '0' && c <= '9')
            continue;
        if (c >= 'a' && c <= 'f')
            continue;
        if (c >= 'A' && c <= 'F')
            continue;
        throw std::runtime_error("hex string contains invalid characters");
    }

    std::vector<uint8_t> bytes;
    for (unsigned int i = 0; i < hex.length(); i += 2)
    {
        std::string byteString = hex.substr(i, 2);
        char byte = (char)strtol(byteString.c_str(), nullptr, 16);
        bytes.push_back(byte);
    }
    return bytes;
}

int utils::NextId()
{
    int res = ++g_idCounter;
    if (res == 0)
    {
        // ID counter overflows.
        std::terminate();
    }
    return res;
}

int64_t utils::CurrentTimeMillis()
{
    auto time = std::chrono::system_clock::now();
    auto sinceEpoch = time.time_since_epoch();
    auto millis = std::chrono::duration_cast<std::chrono::milliseconds>(sinceEpoch);
    int64_t now = millis.count();
    return now;
}

TimeStamp utils::CurrentTimeStamp()
{
    int64_t tms = CurrentTimeMillis();

    int64_t baseTime;
    if (tms < 2085978496000LL)
        baseTime = tms - (-2208988800000LL);
    else
        baseTime = tms - 2085978496000LL;

    int64_t seconds = baseTime / 1000;
    int64_t fraction = ((baseTime % 1000) * 0x100000000LL) / 1000;

    if (tms < 2085978496000LL)
        seconds |= 0x80000000LL;

    int64_t time = (seconds << 32LL) | fraction;
    return TimeStamp(time);
}

OctetString utils::IpToOctetString(const std::string &address)
{
    int ipVersion = GetIpVersion(address);
    if (ipVersion == 4)
    {
        int bytes[4];
        char dot;

        std::stringstream ss(address);
        ss >> bytes[0] >> dot >> bytes[1] >> dot >> bytes[2] >> dot >> bytes[3] >> dot;

        std::vector<uint8_t> data(4);
        data[0] = bytes[0];
        data[1] = bytes[1];
        data[2] = bytes[2];
        data[3] = bytes[3];

        return OctetString(std::move(data));
    }
    else if (ipVersion == 6)
    {
        std::vector<uint8_t> data(16);
        if (!IPv6FromString(address.c_str(), data.data()))
            return {};
        return OctetString(std::move(data));
    }
    else
        return {};
}

std::string utils::VectorToHexString(const std::vector<uint8_t> &hex)
{
    std::string str(hex.size() * 2, '0');
    for (size_t i = 0; i < hex.size(); i++)
    {
        uint8_t octet = hex[i];
        int big = (octet >> 4) & 0xF;
        int little = octet & 0xF;

        char bigChar = static_cast<char>(big < 10 ? '0' + big : 'A' + (big - 10));
        char littleChar = static_cast<char>(little < 10 ? '0' + little : 'A' + (little - 10));

        str[i * 2] = bigChar;
        str[i * 2 + 1] = littleChar;
    }
    return str;
}

bool utils::TryParseInt(const std::string &str, int &output)
{
    return TryParseInt(str.c_str(), output);
}

bool utils::TryParseInt(const char *str, int &output)
{
    int base = 10;
    if (strlen(str) > 2)
    {
        if (str[0] == '0' && str[1] == 'x')
            base = 16;
        else if (str[0] == '0' && str[1] == 'b')
            base = 2;
    }

    try
    {
        output = std::stoi(str, nullptr, base);
        return true;
    }
    catch (...)
    {
        return false;
    }
}

int utils::ParseInt(const std::string &str)
{
    return ParseInt(str.c_str());
}

int utils::ParseInt(const char *str)
{
    int n = 0;
    TryParseInt(str, n);
    return n;
}

void utils::Sleep(int ms)
{
    std::this_thread::sleep_for(std::chrono::milliseconds(ms));
}

std::string utils::OctetStringToIp(const OctetString &address)
{
    if (address.length() == 4)
    {
        char buffer[20] = {0};
        sprintf(buffer, "%d.%d.%d.%d", address.getI(0), address.getI(1), address.getI(2), address.getI(3));
        return std::string{buffer};
    }
    return address.toHexString();
}

void utils::AssertNodeName(const std::string &str)
{
    if (str.length() < cons::MinNodeName)
        throw std::runtime_error("Node name assertion failed: string'" + str + "' is too short");
    if (str.length() > cons::MaxNodeName)
        throw std::runtime_error("Node name assertion failed: string'" + str + "' is too long");

    for (char c : str)
    {
        if (c >= '0' && c <= '9')
            continue;
        if (c >= 'a' && c <= 'z')
            continue;
        if (c >= 'A' && c <= 'Z')
            continue;
        if (c == '-' || c == '_')
            continue;
        throw std::runtime_error("Node name assertion failed: string '" + str +
                                 "' contains illegal character: " + std::string(1, c));
    }
}

bool utils::IsNumeric(const std::string &str)
{
    return !str.empty() && std::all_of(str.begin(), str.end(), [](char c) { return (c >= '0' && c <= '9'); });
}

void utils::Trim(std::string &s)
{
    if (s.length() == 0)
        return;
    s.erase(s.begin(), std::find_if(s.begin(), s.end(), [](unsigned char ch) { return !std::isspace(ch); }));
    s.erase(std::find_if(s.rbegin(), s.rend(), [](unsigned char ch) { return !std::isspace(ch); }).base(), s.end());
}

void utils::Trim(std::stringstream &s)
{
    std::string str{};
    str = s.str();
    Trim(str);
    s.str(str);
}

bool utils::IsLittleEndian()
{
    return htonl(1453) != 1453;
}
