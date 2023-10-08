//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "random.hpp"

#include <atomic>
#include <chrono>

static std::atomic<int64_t> s_counter;

Random::Random() : Random(std::chrono::high_resolution_clock::now().time_since_epoch().count() + s_counter++)
{
}

Random::Random(int64_t seed) : m_seed{seed}
{
}

int64_t Random::nextL()
{
    m_seed = (8253729LL * m_seed + 2396403LL);
    m_seed &= 0x7FFFFFFFFFFFFFFFLL;
    return m_seed;
}

int64_t Random::nextL(int end)
{
    return nextL() % static_cast<int64_t>(end);
}

int64_t Random::nextL(int start, int end)
{
    return nextL(end - start) + start;
}

uint64_t Random::nextUL()
{
    return static_cast<uint64_t>(nextL());
}

uint64_t Random::nextUL(int end)
{
    return static_cast<uint64_t>(nextL(end));
}

uint64_t Random::nextUL(int start, int end)
{
    return static_cast<uint64_t>(nextL(start, end));
}

int32_t Random::nextI()
{
    return static_cast<int32_t>(nextL() & 0x7FFFFFFFLL);
}

int32_t Random::nextI(int end)
{
    return static_cast<int32_t>(nextL(end) & 0x7FFFFFFFLL);
}

int32_t Random::nextI(int start, int end)
{
    return static_cast<int32_t>(nextL(start, end) & 0x7FFFFFFFLL);
}

uint32_t Random::nextUI()
{
    return static_cast<uint32_t>(nextI());
}

uint32_t Random::nextUI(int end)
{
    return static_cast<uint32_t>(nextI(end));
}

uint32_t Random::nextUI(int start, int end)
{
    return static_cast<uint32_t>(nextI(start, end));
}