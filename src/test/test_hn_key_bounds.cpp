//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "test_util.hpp"
#include <ue/nas/mm/hn_key_bounds.hpp>

void run_hn_key_bounds_tests()
{
    // Scheme 1 (Profile A): exactly 64 hex chars
    {
        HnKeyHexBounds bounds = hnKeyHexBounds(1);
        TEST_ASSERT_EQ(bounds.min, 64);
        TEST_ASSERT_EQ(bounds.max, 64);
    }

    // Scheme 2 (Profile B): 66–130 range, exact check via validateProfileBKeyHexLen
    {
        HnKeyHexBounds bounds = hnKeyHexBounds(2);
        TEST_ASSERT_EQ(bounds.min, 66);
        TEST_ASSERT_EQ(bounds.max, 130);

        // 66 hex (compressed) is valid
        TEST_ASSERT(validateProfileBKeyHexLen(66), "scheme 2 accepts 66-hex");
        // 130 hex (uncompressed) is valid
        TEST_ASSERT(validateProfileBKeyHexLen(130), "scheme 2 accepts 130-hex");
        // 64 hex (Profile A length) is rejected for scheme 2
        TEST_ASSERT(!validateProfileBKeyHexLen(64), "scheme 2 rejects 64-hex");
        // 128 hex is rejected for scheme 2
        TEST_ASSERT(!validateProfileBKeyHexLen(128), "scheme 2 rejects 128-hex");
        // 67 hex (odd, in-between) is rejected
        TEST_ASSERT(!validateProfileBKeyHexLen(67), "scheme 2 rejects 67-hex");
    }

    // Scheme 0 (Null): sentinel {0, 0} — key not required
    {
        HnKeyHexBounds bounds = hnKeyHexBounds(0);
        TEST_ASSERT_EQ(bounds.min, 0);
        TEST_ASSERT_EQ(bounds.max, 0);
    }
}
