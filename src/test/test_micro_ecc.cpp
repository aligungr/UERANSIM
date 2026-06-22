//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "test_util.hpp"

#include <cstring>

extern "C"
{
#include <ext/micro-ecc/uECC.h>
}

static void test_compute_public_key()
{
    // Fixed 32-byte private key (arbitrary hex literal)
    const uint8_t privKey[32] = {0xC9, 0xAF, 0xA9, 0xD8, 0x45, 0xBA, 0x75, 0x16, 0x6B, 0x5C, 0x21,
                                 0x57, 0x67, 0xB1, 0xD6, 0x93, 0x4E, 0x50, 0xC3, 0xDB, 0x36, 0xE8,
                                 0x9B, 0x12, 0x7B, 0x8A, 0x62, 0x2B, 0x12, 0x0F, 0x67, 0x21};

    uint8_t pubKey[64] = {};
    int rc = uECC_compute_public_key(privKey, pubKey, uECC_secp256r1());
    TEST_ASSERT(rc == 1, "uECC_compute_public_key succeeds");

    // Public key must not be all-zero
    uint8_t zeros[64] = {};
    TEST_ASSERT(std::memcmp(pubKey, zeros, 64) != 0, "public key is non-zero");
}

static void test_compress_decompress_roundtrip()
{
    const uint8_t privKey[32] = {0xC9, 0xAF, 0xA9, 0xD8, 0x45, 0xBA, 0x75, 0x16, 0x6B, 0x5C, 0x21,
                                 0x57, 0x67, 0xB1, 0xD6, 0x93, 0x4E, 0x50, 0xC3, 0xDB, 0x36, 0xE8,
                                 0x9B, 0x12, 0x7B, 0x8A, 0x62, 0x2B, 0x12, 0x0F, 0x67, 0x21};

    uint8_t pubKey[64] = {};
    uECC_compute_public_key(privKey, pubKey, uECC_secp256r1());

    // Compress
    uint8_t compressed[33] = {};
    uECC_compress(pubKey, compressed, uECC_secp256r1());

    // Decompress
    uint8_t decompressed[64] = {};
    uECC_decompress(compressed, decompressed, uECC_secp256r1());

    // Round-trip must recover original 64-byte key
    TEST_ASSERT(std::memcmp(pubKey, decompressed, 64) == 0,
                "compress/decompress round-trip matches original public key");
}

static void test_shared_secret_symmetry()
{
    // Two fixed keypairs (private keys chosen arbitrarily)
    const uint8_t privA[32] = {0xC9, 0xAF, 0xA9, 0xD8, 0x45, 0xBA, 0x75, 0x16, 0x6B, 0x5C, 0x21,
                               0x57, 0x67, 0xB1, 0xD6, 0x93, 0x4E, 0x50, 0xC3, 0xDB, 0x36, 0xE8,
                               0x9B, 0x12, 0x7B, 0x8A, 0x62, 0x2B, 0x12, 0x0F, 0x67, 0x21};

    const uint8_t privB[32] = {0x3F, 0x49, 0xD5, 0x23, 0xE1, 0x81, 0xDC, 0x35, 0x17, 0xAC, 0x7C,
                               0x6E, 0x09, 0xF8, 0x83, 0x7A, 0xA3, 0x60, 0x10, 0x4B, 0x5B, 0x5E,
                               0x79, 0x94, 0x4E, 0x8A, 0xB2, 0xC5, 0x0C, 0x02, 0x11, 0x58};

    uint8_t pubA[64] = {};
    uint8_t pubB[64] = {};
    uECC_compute_public_key(privA, pubA, uECC_secp256r1());
    uECC_compute_public_key(privB, pubB, uECC_secp256r1());

    // shared_secret(privA, pubB) == shared_secret(privB, pubA)
    uint8_t secretAB[32] = {};
    uint8_t secretBA[32] = {};
    int rc1 = uECC_shared_secret(pubB, privA, secretAB, uECC_secp256r1());
    int rc2 = uECC_shared_secret(pubA, privB, secretBA, uECC_secp256r1());

    TEST_ASSERT(rc1 == 1, "shared_secret(A->B) succeeds");
    TEST_ASSERT(rc2 == 1, "shared_secret(B->A) succeeds");
    TEST_ASSERT(std::memcmp(secretAB, secretBA, 32) == 0, "shared secrets are symmetric (A*B == B*A)");

    // Shared secret must not be all-zero
    uint8_t zeros[32] = {};
    TEST_ASSERT(std::memcmp(secretAB, zeros, 32) != 0, "shared secret is non-zero");
}

void run_micro_ecc_tests()
{
    test_compute_public_key();
    test_compress_decompress_roundtrip();
    test_shared_secret_symmetry();
}
