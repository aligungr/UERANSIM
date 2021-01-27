//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>

struct TimeStamp
{
    const int64_t ntpTime;

    explicit TimeStamp(const int64_t ntpTime) : ntpTime(ntpTime)
    {
    }

    inline int64_t ntpValue() const
    {
        return ntpTime;
    }

    inline int64_t seconds64() const
    {
        return (ntpTime >> 32) & 0xffffffffLL;
    }

    inline int64_t fraction64() const
    {
        return ntpTime & 0xffffffffLL;
    }

    inline int seconds32() const
    {
        return static_cast<int>((ntpTime >> 32) & 0xffffffffLL);
    }

    inline int fraction32() const
    {
        return static_cast<int>(ntpTime & 0xffffffffLL);
    }
};