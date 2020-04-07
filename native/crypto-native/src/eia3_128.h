#pragma once

#include <cstdint>

namespace EIA3_128
{
    uint32_t EIA3(uint8_t *IK, uint32_t COUNT, uint32_t DIRECTION, uint32_t BEARER, uint32_t LENGTH, uint32_t *M);
}