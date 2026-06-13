//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "test_util.hpp"

extern void run_micro_ecc_tests();

int main()
{
    TEST_ASSERT_EQ(1 + 1, 2);
    TEST_ASSERT(true, "trivial assertion");

    run_micro_ecc_tests();

    return test_summary();
}
