//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <cstring>

#include <ext/doctest/doctest.h>

extern "C"
{
#include <ext/micro-ecc/uECC.h>
}

// Two fixed private keys (chosen arbitrarily)
static const uint8_t PRIV_A[32] = {0xC9, 0xAF, 0xA9, 0xD8, 0x45, 0xBA, 0x75, 0x16, 0x6B, 0x5C, 0x21,
                                   0x57, 0x67, 0xB1, 0xD6, 0x93, 0x4E, 0x50, 0xC3, 0xDB, 0x36, 0xE8,
                                   0x9B, 0x12, 0x7B, 0x8A, 0x62, 0x2B, 0x12, 0x0F, 0x67, 0x21};

static const uint8_t PRIV_B[32] = {0x3F, 0x49, 0xD5, 0x23, 0xE1, 0x81, 0xDC, 0x35, 0x17, 0xAC, 0x7C,
                                   0x6E, 0x09, 0xF8, 0x83, 0x7A, 0xA3, 0x60, 0x10, 0x4B, 0x5B, 0x5E,
                                   0x79, 0x94, 0x4E, 0x8A, 0xB2, 0xC5, 0x0C, 0x02, 0x11, 0x58};

TEST_CASE("ext/micro-ecc/uECC: computes a public key")
{
    uint8_t pubKey[64] = {};
    CHECK(uECC_compute_public_key(PRIV_A, pubKey, uECC_secp256r1()) == 1);

    // Public key must not be all-zero
    uint8_t zeros[64] = {};
    CHECK(std::memcmp(pubKey, zeros, 64) != 0);
}

TEST_CASE("ext/micro-ecc/uECC: compress/decompress round-trip recovers the public key")
{
    uint8_t pubKey[64] = {};
    uECC_compute_public_key(PRIV_A, pubKey, uECC_secp256r1());

    uint8_t compressed[33] = {};
    uECC_compress(pubKey, compressed, uECC_secp256r1());

    uint8_t decompressed[64] = {};
    uECC_decompress(compressed, decompressed, uECC_secp256r1());

    // Round-trip must recover original 64-byte key
    CHECK(std::memcmp(pubKey, decompressed, 64) == 0);
}

TEST_CASE("ext/micro-ecc/uECC: shared secret is symmetric")
{
    uint8_t pubA[64] = {};
    uint8_t pubB[64] = {};
    uECC_compute_public_key(PRIV_A, pubA, uECC_secp256r1());
    uECC_compute_public_key(PRIV_B, pubB, uECC_secp256r1());

    // shared_secret(privA, pubB) == shared_secret(privB, pubA)
    uint8_t secretAB[32] = {};
    uint8_t secretBA[32] = {};
    CHECK(uECC_shared_secret(pubB, PRIV_A, secretAB, uECC_secp256r1()) == 1);
    CHECK(uECC_shared_secret(pubA, PRIV_B, secretBA, uECC_secp256r1()) == 1);
    CHECK(std::memcmp(secretAB, secretBA, 32) == 0);

    // Shared secret must not be all-zero
    uint8_t zeros[32] = {};
    CHECK(std::memcmp(secretAB, zeros, 32) != 0);
}
