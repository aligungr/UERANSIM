/* Curve25519 (Montgomery form)
 * Daniel Beer <dlbeer@gmail.com>, 18 Apr 2014
 *
 * This file is in the public domain.
 */

#ifndef C25519_H_
#define C25519_H_

#ifndef COMPACT_DISABLE_X25519
#include <stdint.h>
#include "f25519.h"

/* Curve25519 has the equation over F(p = 2^255-19):
 *
 *    y^2 = x^3 + 486662x^2 + x
 *
 * 486662 = 4A+2, where A = 121665. This is a Montgomery curve.
 *
 * For more information, see:
 *
 *    Bernstein, D.J. (2006) "Curve25519: New Diffie-Hellman speed
 *    records". Document ID: 4230efdfa673480fc079449d90f322c0.
 */

/* This is the site of a Curve25519 exponent (private key) */
#define C25519_EXPONENT_SIZE  32

/* Having generated 32 random bytes, you should call this function to
 * finalize the generated key.
 */
static inline void c25519_prepare(uint8_t *key)
{
	key[0] &= 0xf8;
	key[31] &= 0x7f;
	key[31] |= 0x40;
}

/* X-coordinate of the base point */
extern const uint8_t c25519_base_x[F25519_SIZE];

/* X-coordinate scalar multiply: given the X-coordinate of q, return the
 * X-coordinate of e*q.
 *
 * result and q are field elements. e is an exponent.
 */
void c25519_smult(uint8_t *result, const uint8_t *q, const uint8_t *e);
#endif
#endif
