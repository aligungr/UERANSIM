#pragma once

#include <cstdint>

namespace Zuc
{

void Initialize(uint8_t *pKey, uint8_t *pIv);

void GenerateKeyStream(uint32_t *pKeyStream, uint32_t nKeyStream);

} // namespace Zuc
