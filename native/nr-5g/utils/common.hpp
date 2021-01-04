//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <octet.hpp>
#include <optional>

enum class EPagingDrx
{
    V32 = 32,
    V64 = 64,
    V128 = 128,
    V256 = 256
};

struct Plmn
{
    int mcc;
    int mnc;
    bool isLong;

    octet3 toOctet3() const;
};

struct Nssai
{
    int sst;
    std::optional<int> sd;

    Nssai(int sst, std::optional<int> sd) : sst(sst), sd(sd)
    {
    }
};
