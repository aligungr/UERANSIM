/* Arithmetic mod p = 2^255-19
 * Daniel Beer <dlbeer@gmail.com>, 8 Jan 2014
 *
 * This file is in the public domain.
 */

#ifndef F25519_H_
#define F25519_H_

#include <stdint.h>
#include <string.h>

/* Field elements are represented as little-endian byte strings. All
 * operations have timings which are independent of input data, so they
 * can be safely used for cryptography.
 *
 * Computation is performed on un-normalized elements. These are byte
 * strings which fall into the range 0 <= x < 2p. Use f25519_normalize()
 * to convert to a value 0 <= x < p.
 *
 * Elements received from the outside may greater even than 2p.
 * f25519_normalize() will correctly deal with these numbers too.
 */
#define F25519_SIZE  32

/* Identity constants */
#ifdef FULL_C25519_CODE
extern const uint8_t f25519_zero[F25519_SIZE];
#endif
extern const uint8_t f25519_one[F25519_SIZE];

/* Load a small constant */
void f25519_load(uint8_t *x, uint32_t c);

/* Copy two points */
static inline void f25519_copy(uint8_t *x, const uint8_t *a)
{
	memcpy(x, a, F25519_SIZE);
}

/* Normalize a field point x < 2*p by subtracting p if necessary */
void f25519_normalize(uint8_t *x);

/* Compare two field points in constant time. Return one if equal, zero
 * otherwise. This should be performed only on normalized values.
 */
uint8_t f25519_eq(const uint8_t *x, const uint8_t *y);

/* Conditional copy. If condition == 0, then zero is copied to dst. If
 * condition == 1, then one is copied to dst. Any other value results in
 * undefined behaviour.
 */
void f25519_select(uint8_t *dst,
		   const uint8_t *zero, const uint8_t *one,
		   uint8_t condition);

/* Add/subtract two field points. The three pointers are not required to
 * be distinct.
 */
void f25519_add(uint8_t *r, const uint8_t *a, const uint8_t *b);
void f25519_sub(uint8_t *r, const uint8_t *a, const uint8_t *b);

/* Unary negation */
void f25519_neg(uint8_t *r, const uint8_t *a);

/* Multiply two field points. The __distinct variant is used when r is
 * known to be in a different location to a and b.
 */
#ifdef FULL_C25519_CODE
void f25519_mul(uint8_t *r, const uint8_t *a, const uint8_t *b);
#endif
void f25519_mul__distinct(uint8_t *r, const uint8_t *a, const uint8_t *b);

/* Multiply a point by a small constant. The two pointers are not
 * required to be distinct.
 *
 * The constant must be less than 2^24.
 */
void f25519_mul_c(uint8_t *r, const uint8_t *a, uint32_t b);

/* Take the reciprocal of a field point. The __distinct variant is used
 * when r is known to be in a different location to x.
 */
#ifdef FULL_C25519_CODE
void f25519_inv(uint8_t *r, const uint8_t *x);
#endif
void f25519_inv__distinct(uint8_t *r, const uint8_t *x);

/* Compute one of the square roots of the field element, if the element
 * is square. The other square is -r.
 *
 * If the input is not square, the returned value is a valid field
 * element, but not the correct answer. If you don't already know that
 * your element is square, you should square the return value and test.
 */
void f25519_sqrt(uint8_t *r, const uint8_t *x);

#endif
