//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "test_util.hpp"

int main()
{
    TEST_ASSERT_EQ(1 + 1, 2);
    TEST_ASSERT(true, "trivial assertion");

    return test_summary();
}
