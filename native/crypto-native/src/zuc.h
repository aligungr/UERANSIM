#pragma once

#include <cstdint>

namespace Zuc
{
    void Initialization(uint8_t *k, uint8_t *iv);
    void GenerateKeyStream(uint32_t *pKeyStream, uint32_t KeyStreamLen);
}
