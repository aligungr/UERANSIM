//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <cstdint>
#include <stdexcept>

#include <utils/common.hpp>

// Takes the place of the clock behind utils::CurrentTimeMillis() for as long as it exists. Time stands still until
// advance() moves it.
class FakeClock
{
    static inline int64_t s_now{};
    static inline bool s_exists{};

    static int64_t Now()
    {
        return s_now;
    }

  public:
    // An arbitrary time of day in 2026, so that the values look like the real clock's.
    static constexpr int64_t START = 1'790'000'000'000;

    FakeClock()
    {
        if (s_exists)
            throw std::logic_error("only one FakeClock can exist at a time");
        s_exists = true;
        s_now = START;
        utils::OverrideCurrentTimeMillis(Now);
    }

    ~FakeClock()
    {
        utils::OverrideCurrentTimeMillis(nullptr);
        s_exists = false;
    }

    FakeClock(const FakeClock &) = delete;
    FakeClock &operator=(const FakeClock &) = delete;

    void advance(int64_t ms)
    {
        s_now += ms;
    }

    [[nodiscard]] int64_t now() const
    {
        return s_now;
    }
};
