#pragma once

#include <cstdint>

namespace Snow3G
{
    void Initialize(uint32_t* k, uint32_t* IV);
    void GenerateKeyStream(uint32_t n, uint32_t *ks);
}