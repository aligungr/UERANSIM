/*
 * hmac-sha256.h
 * Copyright (C) 2017 Adrian Perez <aperez@igalia.com>
 *
 * Distributed under terms of the MIT license.
 */

#ifndef HMAC_SHA256_H
#define HMAC_SHA256_H

#include <stddef.h>
#include <stdint.h>

#define HMAC_SHA256_DIGEST_SIZE 32 /* Same as SHA-256's output size. */

#ifdef __cplusplus
extern "C"
{
#endif

    void hmac_sha256(uint8_t out[HMAC_SHA256_DIGEST_SIZE], const uint8_t *data, size_t data_len, const uint8_t *key,
                     size_t key_len);

#ifdef __cplusplus
}
#endif

#endif /* !HMAC_SHA256_H */
