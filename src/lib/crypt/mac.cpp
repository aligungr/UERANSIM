//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mac.hpp"

#include <crypt-ext/cmac.hpp>
#include <crypt-ext/hmac-sha256.h>

namespace crypto
{

void HmacSha256(uint8_t *out, const uint8_t *data, size_t dataLen, const uint8_t *key, size_t keyLen)
{
    hmac_sha256(out, data, dataLen, key, keyLen);
}

void AesCmac(uint8_t *cmac, const uint8_t *key, const uint8_t *msg, uint32_t len)
{
    external_aes_cmac(cmac, key, msg, len);
}

} // namespace crypto
