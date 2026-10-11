//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <utils/octet_string.hpp>

namespace crypto::eea3
{

// `length` is in bits. Only the (length + 7) / 8 bytes it covers are read, or ciphered in place.
uint32_t EIA3(const uint8_t *pKey, uint32_t count, uint32_t direction, uint32_t bearer, uint32_t length,
              const uint8_t *pData);
void EEA3(const uint8_t *pKey, uint32_t count, uint32_t bearer, uint32_t direction, uint32_t length, uint8_t *pData);

} // namespace crypt::eea3
