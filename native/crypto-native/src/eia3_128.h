// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#pragma once

#include <cstdint>

namespace EIA3_128
{

uint32_t EIA3(uint8_t *pKey, uint32_t count, uint32_t direction, uint32_t bearer, uint32_t length, uint32_t *pData);

} // namespace EIA3_128