//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <utils/octet_string.hpp>

namespace crypto::eea3
{

uint32_t EIA3(const uint8_t *pKey, uint32_t count, uint32_t direction, uint32_t bearer, uint32_t length,
              const uint32_t *pData);
void EEA3(const uint8_t *pKey, uint32_t count, uint32_t bearer, uint32_t direction, uint32_t length, uint32_t *pData);

} // namespace crypt::eea3
