//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "convert.hpp"
#include <atomic>
#include <cassert>
#include <chrono>
#include <regex>

static std::atomic<int> idCounter = 1;

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
    assert(hex.length() % 2 == 0);

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
    int res = ++idCounter;
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
