//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "common_types.hpp"
#include "octet.hpp"
#include "octet_string.hpp"
#include "time_stamp.hpp"

#include <algorithm>
#include <iomanip>
#include <sstream>
#include <string>
#include <vector>

namespace utils
{

std::vector<uint8_t> HexStringToVector(const std::string &hex);
std::string VectorToHexString(const std::vector<uint8_t> &hex);
int GetIpVersion(const std::string &address);
OctetString IpToOctetString(const std::string &address);
std::string OctetStringToIp(const OctetString &address);
int64_t CurrentTimeMillis();
TimeStamp CurrentTimeStamp();
int NextId();
int ParseInt(const std::string &str);
int ParseInt(const char *str);
bool TryParseInt(const std::string &str, int &output);
bool TryParseInt(const char *str, int &output);
void Sleep(int ms);
bool IsNumeric(const std::string &str);
void AssertNodeName(const std::string &str);
void Trim(std::string &str);
void Trim(std::stringstream &str);
bool IsLittleEndian();

template <typename T>
inline void ClearAndDelete(std::vector<T *> &vector)
{
    for (T *item : vector)
        delete item;
    vector.clear();
}

template <typename T, typename P>
inline void EraseWhere(std::vector<T> &vector, P predicate)
{
    vector.erase(std::remove_if(vector.begin(), vector.end(), std::forward<P>(predicate)), vector.end());
}

template <typename T>
static std::string IntToHex(T i)
{
    std::stringstream stream;
    if constexpr (sizeof(T) == 1)
        stream << std::setfill('0') << std::setw(sizeof(T) * 2) << std::hex << static_cast<int>(i);
    else
        stream << std::setfill('0') << std::setw(sizeof(T) * 2) << std::hex << i;
    return stream.str();
}

template <class T>
inline void HashCombine(std::size_t &seed, const T &v)
{
    std::hash<T> hasher{};
    seed ^= hasher(v) + 0x9e3779b9 + (seed << 6) + (seed >> 2);
}

} // namespace utils