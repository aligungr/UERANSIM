#pragma once

#include <cstdint>

namespace EEA3_128
{

void EEA3(uint8_t *pKey, uint32_t count, uint32_t bearer, uint32_t direction, uint32_t length, uint32_t *pData);

} // namespace EEA3_128