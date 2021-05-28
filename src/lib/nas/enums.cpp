//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
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
