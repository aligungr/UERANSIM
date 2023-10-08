//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <cstdint>

namespace crypto::snow3g
{
    void Initialize(const uint32_t* pKey, const uint32_t* pIv);
    void GenerateKeyStream(uint32_t *pKeyStream, uint32_t nKeyStream);
}