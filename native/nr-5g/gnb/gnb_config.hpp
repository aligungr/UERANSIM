//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <common_types.hpp>
#include <octet.hpp>
#include <string>
#include <vector>

namespace nr::gnb
{

struct GnbAmfConfig
{
    std::string address;
    uint16_t port;
};

struct GnbConfig
{
    int64_t nci;     // 36-bit
    int gnbIdLength; // 22..32 bit
    std::string name;
    Plmn plmn;
    int tac;
    std::vector<SliceSupport> nssais;
    EPagingDrx pagingDrx;
    std::vector<GnbAmfConfig> amfConfigs;
    std::string ngapIp;
    std::string gtpIp;
    bool ignoreStreamIds;

    inline int getGnbId() const
    {
        return static_cast<int>((nci >> (36LL - static_cast<int64_t>(gnbIdLength))) & ((1 << (gnbIdLength + 1)) - 1));
    }
};

} // namespace nr::gnb