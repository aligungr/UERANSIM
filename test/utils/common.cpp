//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <cstdint>
#include <cstdlib>
#include <ctime>

#include <ext/doctest/doctest.h>
#include <test/support/fake_clock.hpp>
#include <utils/common.hpp>

static int64_t SystemMillis()
{
    return static_cast<int64_t>(std::time(nullptr)) * 1000;
}

TEST_CASE("utils/common: CurrentTimeMillis reads like the time since the epoch and never goes back")
{
    int64_t previous = utils::CurrentTimeMillis();
    CHECK(std::llabs(previous - SystemMillis()) < 2000);

    for (int i = 0; i < 100'000; i++)
    {
        int64_t current = utils::CurrentTimeMillis();
        REQUIRE(current >= previous);
        previous = current;
    }
}

TEST_CASE("utils/common: CurrentTimeMillis follows an override until it is removed")
{
    {
        FakeClock clock;
        CHECK_EQ(utils::CurrentTimeMillis(), FakeClock::START);
        clock.advance(1500);
        CHECK_EQ(utils::CurrentTimeMillis(), FakeClock::START + 1500);
    }

    CHECK(std::llabs(utils::CurrentTimeMillis() - SystemMillis()) < 2000);
}

TEST_CASE("utils/common: CurrentTimeStamp is the time of day, whatever CurrentTimeMillis says")
{
    FakeClock clock;
    clock.advance(-FakeClock::START);

    // NTP counts the seconds from 1900, 2208988800 more than Unix time does.
    int64_t ntpSeconds = static_cast<int64_t>(std::time(nullptr)) + 2208988800;
    CHECK(std::llabs(utils::CurrentTimeStamp().seconds64() - ntpSeconds) <= 1);
}
