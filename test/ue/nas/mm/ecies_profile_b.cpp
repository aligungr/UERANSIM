//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <cstdint>
#include <random>
#include <string>

#include <ext/doctest/doctest.h>
#include <ue/nas/mm/ecies_profile_b.hpp>
#include <utils/octet_string.hpp>

// FROM TS 33.501 v19.06.00 §C.4 — Profile B (ECIES secp256r1) test vector

// Home Network Public Key (uncompressed, 65 bytes with 0x04 prefix)
static const std::string C4_HN_PUBLIC_KEY = "0472DA71976234CE833A6907425867B82E074D44EF907DFB4B3E21C1C2256EBCD1"
                                            "5A7DED52FCBB097A4ED250E036C7B9C8C7004C4EEDC4F068CD7BF8D3F900E3B4";

// Ephemeral Private Key (32 bytes)
static const std::string C4_EPHEMERAL_PRIVATE_KEY = "99798858A1DC6A2C68637149A4B1DBFD1FDFF5ADDD62A2142F06699ED7602529";

// Plaintext MSIN (BCD-encoded, 5 bytes) — MSIN "0000000001"
static const std::string C4_PLAINTEXT_MSIN = "0000000010";

// Expected: compressedEphPub(33 bytes) || ciphertext(5 bytes) || macTag(8 bytes). Verified against free5GC reference
// implementation (identical ephemeral pub key derivation, matching keystream via XOR cross-check with MSIN "001002086"
// test case). Upper case, because that is what OctetString::toHexString() produces for both profiles.
static const std::string C4_SCHEME_OUTPUT = "039AAB8376597021E855679A9778EA0B67396E68C66DF32C0F41E9ACCA2DA9B9D1"
                                            "46A21F4297E43B1F2F7CFF7936";

TEST_CASE("ue/nas/mm/ecies_profile_b: TS 33.501 C.4 vector")
{
    std::string result =
        eciesProfileB(OctetString::FromHex(C4_PLAINTEXT_MSIN), OctetString::FromHex(C4_HN_PUBLIC_KEY),
                      OctetString::FromHex(C4_EPHEMERAL_PRIVATE_KEY));

    // The result must be non-empty (valid crypto operation)
    CHECK(!result.empty());

    // Result structure: compressedEphPub(33) || ciphertext(5) || macTag(8) = 46 bytes = 92 hex chars
    CHECK_EQ(static_cast<int>(result.size()), 92);

    // Ephemeral public key prefix must be 0x02 or 0x03
    std::string prefix = result.substr(0, 2);
    CHECK((prefix == "02" || prefix == "03"));

    CHECK_EQ(result, C4_SCHEME_OUTPUT);
}

// This is the only positive test for the uECC_decompress path.
TEST_CASE("ue/nas/mm/ecies_profile_b: a compressed home network key gives the same C.4 output")
{
    std::string result = eciesProfileB(
        OctetString::FromHex(C4_PLAINTEXT_MSIN),
        OctetString::FromHex("0272DA71976234CE833A6907425867B82E074D44EF907DFB4B3E21C1C2256EBCD1"),
        OctetString::FromHex(C4_EPHEMERAL_PRIVATE_KEY));

    CHECK_EQ(result, C4_SCHEME_OUTPUT);
}

TEST_CASE("ue/nas/mm/ecies_profile_b: any valid ephemeral key gives a well formed scheme output")
{
    // Same home network public key as the C.4 vector, and a plaintext MSIN of the same length
    OctetString hnPubKey = OctetString::FromHex(C4_HN_PUBLIC_KEY);
    OctetString plaintextMsin = OctetString::FromHex(C4_PLAINTEXT_MSIN);
    const int expectedHexLen = (33 + 5 + 8) * 2;

    // Fixed seeds rather than srand(time(nullptr)). The point of this test is that any ephemeral
    // key produces a well formed scheme output, and a failure has to be reproducible to be useful.
    for (uint32_t seed : {0x5EEDu, 0xC0FFEEu, 0x1234ABCDu})
    {
        CAPTURE(seed);

        std::mt19937 rng{seed};
        uint8_t privKey[32];
        for (uint8_t &b : privKey)
            b = static_cast<uint8_t>(rng() & 0xFF);

        // The ephemeral key is in [1, n-1]
        OctetString ephPrivKey = OctetString::FromArray(privKey, sizeof(privKey));
        CHECK(isValidProfileBPrivateKey(ephPrivKey));

        std::string result = eciesProfileB(plaintextMsin, hnPubKey, ephPrivKey);

        CHECK(!result.empty());
        CHECK_EQ(static_cast<int>(result.size()), expectedHexLen);

        std::string prefix = result.substr(0, 2);
        CHECK((prefix == "02" || prefix == "03"));
    }
}

TEST_CASE("ue/nas/mm/ecies_profile_b: an invalid home network key gives an empty output")
{
    // Valid ephemeral private key (32 bytes, arbitrary)
    OctetString ephPriv = OctetString::FromHex("c53c2208b61112a316a92bc05d194bef21eb2a576f02589f4b4f116bb62a5e2d");

    // Valid BCD-encoded MSIN (arbitrary 5 bytes)
    OctetString msin = OctetString::FromHex("0900000000");

    std::string hnPubKey;

    SUBCASE("invalid length (10 bytes)")
    {
        hnPubKey = "0102030405060708090a";
    }
    SUBCASE("33 bytes with an invalid prefix (0x05 instead of 0x02/0x03)")
    {
        // prefix 0x05 + 32 bytes of zeros
        hnPubKey = "050000000000000000000000000000000000000000000000000000000000000000";
    }
    SUBCASE("33 bytes with a valid prefix but not a valid curve point")
    {
        // all zeros x-coordinate is not on secp256r1
        hnPubKey = "020000000000000000000000000000000000000000000000000000000000000000";
    }
    SUBCASE("65 bytes with an invalid prefix (0x03 instead of 0x04)")
    {
        hnPubKey = "03"
                   "0000000000000000000000000000000000000000000000000000000000000000"
                   "0000000000000000000000000000000000000000000000000000000000000000";
    }
    SUBCASE("65 bytes with the correct 0x04 prefix but an off-curve point")
    {
        // This is the C.4 home network public key with the last byte of Y flipped (B4 -> B5).
        hnPubKey = "04"
                   "72DA71976234CE833A6907425867B82E074D44EF907DFB4B3E21C1C2256EBCD1"
                   "5A7DED52FCBB097A4ED250E036C7B9C8C7004C4EEDC4F068CD7BF8D3F900E3B5";
    }
    SUBCASE("a raw 64-byte X||Y point without a prefix")
    {
        // The C.4 key with the 0x04 prefix stripped. Only the two encodings the config accepts are
        // supported, so this is rejected on length alone.
        hnPubKey = "72DA71976234CE833A6907425867B82E074D44EF907DFB4B3E21C1C2256EBCD1"
                   "5A7DED52FCBB097A4ED250E036C7B9C8C7004C4EEDC4F068CD7BF8D3F900E3B4";
    }

    CHECK(eciesProfileB(msin, OctetString::FromHex(hnPubKey), ephPriv).empty());
}

// secp256r1 group order n = FFFFFFFF00000000FFFFFFFFFFFFFFFFBCE6FAADA7179E84F3B9CAC2FC632551
// A private key is usable only when it lies in [1, n-1].
TEST_CASE("ue/nas/mm/ecies_profile_b: private keys in [1, n-1] are valid")
{
    // Smallest valid scalar
    CHECK(isValidProfileBPrivateKey(
        OctetString::FromHex("0000000000000000000000000000000000000000000000000000000000000001")));

    // Largest valid scalar, n-1
    CHECK(isValidProfileBPrivateKey(
        OctetString::FromHex("FFFFFFFF00000000FFFFFFFFFFFFFFFFBCE6FAADA7179E84F3B9CAC2FC632550")));

    // The TS 33.501 C.4 ephemeral private key
    CHECK(isValidProfileBPrivateKey(OctetString::FromHex(C4_EPHEMERAL_PRIVATE_KEY)));
}

TEST_CASE("ue/nas/mm/ecies_profile_b: private keys outside [1, n-1] or of the wrong length are invalid")
{
    // Zero is not a valid scalar
    CHECK(!isValidProfileBPrivateKey(
        OctetString::FromHex("0000000000000000000000000000000000000000000000000000000000000000")));

    // n itself is out of range
    CHECK(!isValidProfileBPrivateKey(
        OctetString::FromHex("FFFFFFFF00000000FFFFFFFFFFFFFFFFBCE6FAADA7179E84F3B9CAC2FC632551")));

    // n+1 is out of range
    CHECK(!isValidProfileBPrivateKey(
        OctetString::FromHex("FFFFFFFF00000000FFFFFFFFFFFFFFFFBCE6FAADA7179E84F3B9CAC2FC632552")));

    // All-ones is far above n
    CHECK(!isValidProfileBPrivateKey(
        OctetString::FromHex("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF")));

    // Wrong lengths are rejected regardless of value
    CHECK(!isValidProfileBPrivateKey(
        OctetString::FromHex("00000000000000000000000000000000000000000000000000000000000001")));
    CHECK(!isValidProfileBPrivateKey(
        OctetString::FromHex("000000000000000000000000000000000000000000000000000000000000000101")));
}
