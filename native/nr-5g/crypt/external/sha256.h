/* Sha256.h -- SHA-256 Hash
2010-06-11 : Igor Pavlov : Public domain */

#pragma once

#include <stdint.h>
#include <stdlib.h>

#define SHA256_DIGEST_SIZE 32

#ifdef __cplusplus
extern "C"
{
#endif

    typedef struct sha256_t
    {
        uint32_t state[8];
        uint64_t count;
        unsigned char buffer[64];
    } sha256_t;

    void sha256_init(sha256_t *p);
    void sha256_update(sha256_t *p, const unsigned char *data, size_t size);
    void sha256_final(sha256_t *p, unsigned char *digest);
    void sha256_hash(unsigned char *buf, const unsigned char *data, size_t size);

#ifdef __cplusplus
}
#endif