//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>

namespace crypto::zuc
{

void Initialize(const uint8_t *pKey, const uint8_t *pIv);
void GenerateKeyStream(uint32_t *pKeyStream, uint32_t nKeyStream);

} // namespace crypt::zuc