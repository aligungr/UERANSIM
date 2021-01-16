//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <memory>
#include <octet.hpp>
#include <optional>
#include <vector>

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
    bool isLongMnc;
};

struct SliceSupport
{
    octet sst{};
    std::optional<octet3> sd{};
};

enum class PduSessionType
{
    IPv4,
    IPv6,
    IPv4v6,
    ETHERNET,
    UNSTRUCTURED
};

struct PlmnSupport
{
    Plmn plmn;
    std::vector<std::unique_ptr<SliceSupport>> sliceSupportList;
};
