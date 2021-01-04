//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "common.hpp"
#include <octet.hpp>
#include <string>
#include <vector>

namespace Convert
{

static_assert(sizeof(char) == sizeof(uint8_t));
static_assert(sizeof(int) == sizeof(uint32_t));
static_assert(sizeof(long) == sizeof(uint64_t));

std::vector<uint8_t> HexStringToVector(const std::string &hex);

octet3 PlmnToOctet3(const Plmn &plmn);

int GetIpVersion(const std::string &address);

} // namespace Convert