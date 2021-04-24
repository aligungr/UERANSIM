//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "octet.hpp"
#include "common.hpp"

#include <sstream>

Json ToJson(const octet &v)
{
    return "0x" + utils::IntToHex((uint8_t)v);
}

Json ToJson(const octet2 &v)
{
    return "0x" + utils::IntToHex((uint16_t)v);
}

Json ToJson(const octet3 &v)
{
    std::stringstream stream;
    stream << std::setfill('0') << std::setw(6) << std::hex << (uint32_t)v;
    return "0x" + stream.str();
}

Json ToJson(const octet4 &v)
{
    return "0x" + utils::IntToHex((uint32_t)v);
}

Json ToJson(const octet8 &v)
{
    return "0x" + utils::IntToHex((uint64_t)v);
}