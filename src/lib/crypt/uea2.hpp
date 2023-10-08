//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <cstdint>

namespace crypto::uea2
{

void F8(const uint8_t *pKey, uint32_t count, uint32_t bearer, uint32_t dir, uint8_t *pData, uint32_t length);
uint32_t F9(const uint8_t *pKey, uint32_t count, uint32_t fresh, uint32_t dir, const uint8_t *pData, uint64_t length);

} // namespace crypt::uea2
