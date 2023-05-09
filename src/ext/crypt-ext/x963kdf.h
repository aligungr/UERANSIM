/* X963kdf.h -- X9.63 Key Derivation Function
2023-03-22 : Stephane G. : Public domain */

#pragma once

// #include <stdint.h>
// #include <stdlib.h>
#include "sha256.h"
#include <math.h>
#include <string.h>

#define SHA256_DIGEST_SIZE 32

#ifdef __cplusplus
extern "C"
{
#endif

    void x963kdf(unsigned char *buf, const unsigned char *sharedSecret, const unsigned char *sharedInfo, size_t size);

#ifdef __cplusplus
}
#endif