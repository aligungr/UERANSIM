// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#pragma once

#include <cstdint>

namespace UEA2
{

void f8(uint8_t *pKey, uint32_t count, uint32_t bearer, uint32_t dir, uint8_t *pData, uint32_t length);

uint32_t f9(uint8_t *pKey, uint32_t count, uint32_t fresh, uint32_t dir, uint8_t *pData, uint64_t length);

} // namespace UEA2
