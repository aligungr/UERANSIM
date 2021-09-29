//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>
#include <functional>
#include <string>

class Random
{
  private:
    int64_t m_seed;

  public:
    Random();
    explicit Random(int64_t seed);

    int64_t nextL();
    int64_t nextL(int end);
    int64_t nextL(int start, int end);

    uint64_t nextUL();
    uint64_t nextUL(int end);
    uint64_t nextUL(int start, int end);

    int32_t nextI();
    int32_t nextI(int end);
    int32_t nextI(int start, int end);

    uint32_t nextUI();
    uint32_t nextUI(int end);
    uint32_t nextUI(int start, int end);

    template <typename T>
    static inline Random Mixed(const T &v)
    {
        auto hash = static_cast<int64_t>(std::hash<T>{}(v));
        Random r;
        r.m_seed ^= hash;
        return r;
    }
};