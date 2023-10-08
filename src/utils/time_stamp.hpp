//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <cstdint>

struct TimeStamp
{
    const int64_t ntpTime;

    explicit TimeStamp(const int64_t ntpTime) : ntpTime(ntpTime)
    {
    }

    [[nodiscard]] inline int64_t ntpValue() const
    {
        return ntpTime;
    }

    [[nodiscard]] inline int64_t seconds64() const
    {
        return (ntpTime >> 32) & 0xffffffffLL;
    }

    [[nodiscard]] inline int64_t fraction64() const
    {
        return ntpTime & 0xffffffffLL;
    }

    [[nodiscard]] inline int seconds32() const
    {
        return static_cast<int>((ntpTime >> 32) & 0xffffffffLL);
    }

    [[nodiscard]] inline int fraction32() const
    {
        return static_cast<int>(ntpTime & 0xffffffffLL);
    }
};