/* Curve25519 (Montgomery form)
 * Daniel Beer <dlbeer@gmail.com>, 18 Apr 2014
 *
 * This file is in the public domain.
 */

#include "c25519.h"

#ifndef COMPACT_DISABLE_X25519
const uint8_t c25519_base_x[F25519_SIZE] = {9};

/* Double an X-coordinate */
static void xc_double(uint8_t *x3, uint8_t *z3,
		      const uint8_t *x1, const uint8_t *z1)
{
	/* Explicit formulas database: dbl-1987-m
	 *
	 * source 1987 Montgomery "Speeding the Pollard and elliptic
	 *   curve methods of factorization", page 261, fourth display
	 * compute X3 = (X1^2-Z1^2)^2
	 * compute Z3 = 4 X1 Z1 (X1^2 + a X1 Z1 + Z1^2)
	 */
	uint8_t x1sq[F25519_SIZE];
	uint8_t z1sq[F25519_SIZE];
	uint8_t x1z1[F25519_SIZE];
	uint8_t a[F25519_SIZE];

	f25519_mul__distinct(x1sq, x1, x1);
	f25519_mul__distinct(z1sq, z1, z1);
	f25519_mul__distinct(x1z1, x1, z1);

	f25519_sub(a, x1sq, z1sq);
	f25519_mul__distinct(x3, a, a);

	f25519_mul_c(a, x1z1, 486662);
	f25519_add(a, x1sq, a);
	f25519_add(a, z1sq, a);
	f25519_mul__distinct(x1sq, x1z1, a);
	f25519_mul_c(z3, x1sq, 4);
}

/* Differential addition */
static void xc_diffadd(uint8_t *x5, uint8_t *z5,
		       const uint8_t *x1, const uint8_t *z1,
		       const uint8_t *x2, const uint8_t *z2,
		       const uint8_t *x3, const uint8_t *z3)
{
	/* Explicit formulas database: dbl-1987-m3
	 *
	 * source 1987 Montgomery "Speeding the Pollard and elliptic curve
	 *   methods of factorization", page 261, fifth display, plus
	 *   common-subexpression elimination
	 * compute A = X2+Z2
	 * compute B = X2-Z2
	 * compute C = X3+Z3
	 * compute D = X3-Z3
	 * compute DA = D A
	 * compute CB = C B
	 * compute X5 = Z1(DA+CB)^2
	 * compute Z5 = X1(DA-CB)^2
	 */
	uint8_t da[F25519_SIZE];
	uint8_t cb[F25519_SIZE];
	uint8_t a[F25519_SIZE];
	uint8_t b[F25519_SIZE];

	f25519_add(a, x2, z2);
	f25519_sub(b, x3, z3); /* D */
	f25519_mul__distinct(da, a, b);

	f25519_sub(b, x2, z2);
	f25519_add(a, x3, z3); /* C */
	f25519_mul__distinct(cb, a, b);

	f25519_add(a, da, cb);
	f25519_mul__distinct(b, a, a);
	f25519_mul__distinct(x5, z1, b);

	f25519_sub(a, da, cb);
	f25519_mul__distinct(b, a, a);
	f25519_mul__distinct(z5, x1, b);
}

void c25519_smult(uint8_t *result, const uint8_t *q, const uint8_t *e)
{
	/* Current point: P_m */
	uint8_t xm[F25519_SIZE];
	uint8_t zm[F25519_SIZE] = {1};

	/* Predecessor: P_(m-1) */
	uint8_t xm1[F25519_SIZE] = {1};
	uint8_t zm1[F25519_SIZE] = {0};

	int i;

	/* Note: bit 254 is assumed to be 1 */
	f25519_copy(xm, q);

	for (i = 253; i >= 0; i--) {
		const int bit = (e[i >> 3] >> (i & 7)) & 1;
		uint8_t xms[F25519_SIZE];
		uint8_t zms[F25519_SIZE];

		/* From P_m and P_(m-1), compute P_(2m) and P_(2m-1) */
		xc_diffadd(xm1, zm1, q, f25519_one, xm, zm, xm1, zm1);
		xc_double(xm, zm, xm, zm);

		/* Compute P_(2m+1) */
		xc_diffadd(xms, zms, xm1, zm1, xm, zm, q, f25519_one);

		/* Select:
		 *   bit = 1 --> (P_(2m+1), P_(2m))
		 *   bit = 0 --> (P_(2m), P_(2m-1))
		 */
		f25519_select(xm1, xm1, xm, bit);
		f25519_select(zm1, zm1, zm, bit);
		f25519_select(xm, xm, xms, bit);
		f25519_select(zm, zm, zms, bit);
	}

	/* Freeze out of projective coordinates */
	f25519_inv__distinct(zm1, zm);
	f25519_mul__distinct(result, zm1, xm);
	f25519_normalize(result);
}
#endif
