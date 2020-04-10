#pragma once

#include <cstdint>

namespace EEA3_128
{
    void EEA3(uint8_t *CK, uint32_t COUNT, uint32_t BEARER, uint32_t DIRECTION, uint32_t LENGTH, uint32_t *M, uint32_t *C);
}