//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <cstdint>
#include <cstdlib>

namespace crypto
{

void HmacSha256(uint8_t out[32], const uint8_t *data, size_t dataLen, const uint8_t *key, size_t keyLen);

void AesCmac(uint8_t *cmac, const uint8_t *key, const uint8_t *msg, uint32_t len);

}