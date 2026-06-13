/* Copyright 2015, Kenneth MacKay. Licensed under the BSD 2-clause license. */

#ifndef _UECC_PLATFORM_SPECIFIC_H_
#define _UECC_PLATFORM_SPECIFIC_H_

#include "types.h"

/*
 * UERANSIM: Built-in default RNG intentionally DISABLED.
 * The caller must supply an RNG via uECC_set_rng() if needed.
 * For SUCI Profile B we only use uECC_compute_public_key(),
 * uECC_shared_secret(), uECC_compress(), and uECC_decompress(),
 * none of which require an RNG when initial_Z randomisation
 * is not used.
 */

#endif /* _UECC_PLATFORM_SPECIFIC_H_ */
