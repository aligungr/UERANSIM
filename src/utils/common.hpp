//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "common_types.hpp"
#include "octet.hpp"
#include "octet_string.hpp"
#include "time_stamp.hpp"
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
uint64_t Random64();
void Sleep(int ms);
bool IsRoot();
bool IsNumeric(const std::string &str);
void AssertNodeName(const std::string &str);
void Trim(std::string &str);
void Trim(std::stringstream &str);

template <typename T>
inline void ClearAndDelete(std::vector<T *> &vector)
{
    for (T *item : vector)
        delete item;
    vector.clear();
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

} // namespace utils