//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <cstdio>
#include <cstdlib>
#include <string>

static int g_testsPassed = 0;
static int g_testsFailed = 0;

#define TEST_ASSERT(cond, msg)                                                                                         \
    do                                                                                                                 \
    {                                                                                                                  \
        if (cond)                                                                                                      \
        {                                                                                                              \
            g_testsPassed++;                                                                                           \
            std::printf("  PASS: %s\n", (msg));                                                                        \
        }                                                                                                              \
        else                                                                                                           \
        {                                                                                                              \
            g_testsFailed++;                                                                                           \
            std::printf("  FAIL: %s  [%s:%d]\n", (msg), __FILE__, __LINE__);                                           \
        }                                                                                                              \
    } while (0)

#define TEST_ASSERT_EQ(a, b)                                                                                           \
    do                                                                                                                 \
    {                                                                                                                  \
        auto _a = (a);                                                                                                 \
        auto _b = (b);                                                                                                 \
        if (_a == _b)                                                                                                  \
        {                                                                                                              \
            g_testsPassed++;                                                                                           \
            std::printf("  PASS: %s == %s\n", #a, #b);                                                                 \
        }                                                                                                              \
        else                                                                                                           \
        {                                                                                                              \
            g_testsFailed++;                                                                                           \
            std::printf("  FAIL: %s == %s  [%s:%d]\n", #a, #b, __FILE__, __LINE__);                                    \
        }                                                                                                              \
    } while (0)

inline int test_summary()
{
    int total = g_testsPassed + g_testsFailed;
    std::printf("\n%d/%d passed\n", g_testsPassed, total);
    return g_testsFailed == 0 ? 0 : 1;
}
