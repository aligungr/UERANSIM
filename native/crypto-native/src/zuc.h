// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#pragma once

#include <cstdint>

namespace Zuc
{

void Initialize(uint8_t *pKey, uint8_t *pIv);

void GenerateKeyStream(uint32_t *pKeyStream, uint32_t nKeyStream);

} // namespace Zuc
