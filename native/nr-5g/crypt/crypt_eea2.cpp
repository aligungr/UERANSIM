//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "crypt_eea2.hpp"
#include "external/aes.hpp"
#include <bit_buffer.hpp>
#include <octet_string.hpp>

namespace crypto::eea2
{

static void Cipher(const uint8_t *key, const uint8_t *iv, uint8_t *buffer, size_t length)
{
    auto *ctx = static_cast<AES_ctx *>(calloc(1, sizeof(AES_ctx)));
    AES_init_ctx_iv(ctx, key, iv);
    AES_CTR_xcrypt_buffer(ctx, buffer, length);
    free(ctx);
}

static void ComputeIv(uint8_t *iv, uint32_t count, int bearer, int direction)
{
    BitBuffer buf{iv};
    buf.writeBits(static_cast<int>(count), 32);
    buf.writeBits(bearer, 5);
    buf.write(direction);
}

void Encrypt(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key)
{
    uint8_t iv[16] = {0};
    ComputeIv(iv, count, bearer, direction);
    Cipher(key.data(), iv, message.data(), message.length());
}

void Decrypt(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key)
{
    uint8_t iv[16] = {0};
    ComputeIv(iv, count, bearer, direction);
    Cipher(key.data(), iv, message.data(), message.length());
}

} // namespace crypt::eea2