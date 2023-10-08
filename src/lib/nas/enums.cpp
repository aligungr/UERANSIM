//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "enums.hpp"

namespace nas
{

Json ToJson(const EPduSessionType &v)
{
    switch (v)
    {
    case EPduSessionType::IPV4:
        return "IPv4";
    case EPduSessionType::IPV6:
        return "IPv6";
    case EPduSessionType::IPV4V6:
        return "IPv4v6";
    case EPduSessionType::UNSTRUCTURED:
        return "unstructured";
    case EPduSessionType::ETHERNET:
        return "ethernet";
    default:
        return "?";
    }
}

} // namespace nas
