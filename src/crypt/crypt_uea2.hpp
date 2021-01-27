//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>

namespace crypto::uea2
{

void F8(const uint8_t *pKey, uint32_t count, uint32_t bearer, uint32_t dir, uint8_t *pData, uint32_t length);
uint32_t F9(const uint8_t *pKey, uint32_t count, uint32_t fresh, uint32_t dir, const uint8_t *pData, uint64_t length);

} // namespace crypt::uea2
