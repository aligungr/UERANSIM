//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <cstdint>

namespace crypto::zuc
{

void Initialize(const uint8_t *pKey, const uint8_t *pIv);
void GenerateKeyStream(uint32_t *pKeyStream, uint32_t nKeyStream);

} // namespace crypt::zuc