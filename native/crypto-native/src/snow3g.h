#pragma once

#include <cstdint>

namespace Snow3G
{
    void Initialize(uint32_t* pKey, uint32_t* pIv);
    void GenerateKeyStream(uint32_t *pKeyStream, uint32_t nKeyStream);
}