//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
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