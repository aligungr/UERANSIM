//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <common.hpp>
#include <octet.hpp>
#include <string>
#include <vector>

namespace nr::gnb
{

struct GnbConfig
{
    int gnbId;
    std::string name;
    Plmn plmn;
    int tac;
    std::vector<Nssai> nssais;
    EPagingDrx pagingDrx;
};

} // namespace nr::gnb