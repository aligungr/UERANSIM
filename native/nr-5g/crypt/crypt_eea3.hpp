//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <octet_string.hpp>

namespace crypto::eea3
{

uint32_t EIA3(const uint8_t *pKey, uint32_t count, uint32_t direction, uint32_t bearer, uint32_t length,
              const uint32_t *pData);
void EEA3(const uint8_t *pKey, uint32_t count, uint32_t bearer, uint32_t direction, uint32_t length, uint32_t *pData);

} // namespace crypt::eea3
