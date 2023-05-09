/* SHA512
 * Daniel Beer <dlbeer@gmail.com>, 22 Apr 2014
 *
 * This file is in the public domain.
 */

#ifndef SHA512_H_
#define SHA512_H_

#if !defined(COMPACT_DISABLE_ED25519) || !defined(COMPACT_DISABLE_X25519_DERIVE)
#include <stdint.h>
#include <stddef.h>
#include <string.h>

/* SHA512 state. State is updated as data is fed in, and then the final
 * hash can be read out in slices.
 *
 * Data is fed in as a sequence of full blocks terminated by a single
 * partial block.
 */
struct sha512_state {
	uint64_t  h[8];
};

/* Initial state */
extern const struct sha512_state sha512_initial_state;

/* Set up a new context */
static inline void sha512_init(struct sha512_state *s)
{
	memcpy(s, &sha512_initial_state, sizeof(*s));
}

/* Feed a full block in */
#define SHA512_BLOCK_SIZE  128

void sha512_block(struct sha512_state *s, const uint8_t *blk);

/* Feed the last partial block in. The total stream size must be
 * specified. The size of the block given is assumed to be (total_size %
 * SHA512_BLOCK_SIZE). This might be zero, but you still need to call
 * this function to terminate the stream.
 */
void sha512_final(struct sha512_state *s, const uint8_t *blk,
		  size_t total_size);

/* Fetch a slice of the hash result. */
#define SHA512_HASH_SIZE  64

void sha512_get(const struct sha512_state *s, uint8_t *hash,
		unsigned int offset, unsigned int len);

#endif
#endif
