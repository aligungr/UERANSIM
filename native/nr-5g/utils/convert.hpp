//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "common_types.hpp"
#include <octet.hpp>
#include <octet_string.hpp>
#include <string>
#include <vector>

namespace utils
{

static_assert(sizeof(char) == sizeof(uint8_t));
static_assert(sizeof(int) == sizeof(uint32_t));
static_assert(sizeof(long) == sizeof(uint64_t));

std::vector<uint8_t> HexStringToVector(const std::string &hex);
int GetIpVersion(const std::string &address);
OctetString IpToOctetString(const std::string &address);

template <typename T>
inline void ClearAndDelete(std::vector<T *> &vector)
{
    for (T *item : vector)
        delete item;
    vector.clear();
}

int NextId();

} // namespace utils