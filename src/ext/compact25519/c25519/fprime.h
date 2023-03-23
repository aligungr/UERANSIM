/* Arithmetic in prime fields
 * Daniel Beer <dlbeer@gmail.com>, 10 Jan 2014
 *
 * This file is in the public domain.
 */

#ifndef FPRIME_H_
#define FPRIME_H_

#ifndef COMPACT_DISABLE_ED25519
#include <stdint.h>
#include <string.h>

/* Maximum size of a field element (or a prime). Field elements are
 * always manipulated and stored in normalized form, with 0 <= x < p.
 * You can use normalize() to convert a denormalized bitstring to normal
 * form.
 *
 * Operations are constant with respect to the value of field elements,
 * but not with respect to the modulus.
 *
 * The modulus is a number p, such that 2p-1 fits in FPRIME_SIZE bytes.
 */
#define FPRIME_SIZE  32

#ifdef FULL_C25519_CODE
/* Useful constants */
extern const uint8_t fprime_zero[FPRIME_SIZE];
extern const uint8_t fprime_one[FPRIME_SIZE];
#endif

#ifdef FULL_C25519_CODE
/* Load a small constant */
void fprime_load(uint8_t *x, uint32_t c);
#endif

/* Load a large constant */
void fprime_from_bytes(uint8_t *n,
		       const uint8_t *x, size_t len,
		       const uint8_t *modulus);

/* Copy an element */
static inline void fprime_copy(uint8_t *x, const uint8_t *a)
{
	memcpy(x, a, FPRIME_SIZE);
}

#ifdef FULL_C25519_CODE
/* Normalize a field element */
void fprime_normalize(uint8_t *x, const uint8_t *modulus);

/* Compare two field points in constant time. Return one if equal, zero
 * otherwise. This should be performed only on normalized values.
 */
uint8_t fprime_eq(const uint8_t *x, const uint8_t *y);

#endif
/* Conditional copy. If condition == 0, then zero is copied to dst. If
 * condition == 1, then one is copied to dst. Any other value results in
 * undefined behaviour.
 */
void fprime_select(uint8_t *dst,
		   const uint8_t *zero, const uint8_t *one,
		   uint8_t condition);

/* Add one value to another. The two pointers must be distinct. */
void fprime_add(uint8_t *r, const uint8_t *a, const uint8_t *modulus);
#ifdef FULL_C25519_CODE
void fprime_sub(uint8_t *r, const uint8_t *a, const uint8_t *modulus);
#endif

/* Multiply two values to get a third. r must be distinct from a and b */
void fprime_mul(uint8_t *r, const uint8_t *a, const uint8_t *b,
		const uint8_t *modulus);

#ifdef FULL_C25519_CODE
/* Compute multiplicative inverse. r must be distinct from a */
void fprime_inv(uint8_t *r, const uint8_t *a, const uint8_t *modulus);
#endif
#endif
#endif
