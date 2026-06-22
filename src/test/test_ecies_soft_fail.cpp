//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "test_util.hpp"
#include <ue/nas/mm/ecies_profile_b.hpp>
#include <utils/octet_string.hpp>

void run_ecies_soft_fail_tests()
{
    // Valid ephemeral private key (32 bytes, arbitrary)
    OctetString ephPriv = OctetString::FromHex("c53c2208b61112a316a92bc05d194bef21eb2a576f02589f4b4f116bb62a5e2d");

    // Valid BCD-encoded MSIN (arbitrary 5 bytes)
    OctetString msin = OctetString::FromHex("0900000000");

    // (a) HN key of invalid length (10 bytes)
    {
        OctetString badLenKey = OctetString::FromHex("0102030405060708090a");
        std::string result = eciesProfileB(msin, badLenKey, ephPriv);
        TEST_ASSERT(result.empty(), "soft-fail: invalid HN key length (10 bytes) returns empty");
    }

    // (b) 33-byte key with invalid prefix (0x05 instead of 0x02/0x03)
    {
        // 33 bytes: prefix 0x05 + 32 bytes of zeros
        OctetString badPrefixKey =
            OctetString::FromHex("050000000000000000000000000000000000000000000000000000000000000000");
        std::string result = eciesProfileB(msin, badPrefixKey, ephPriv);
        TEST_ASSERT(result.empty(), "soft-fail: 33-byte key with invalid prefix 0x05 returns empty");
    }

    // (c) 33-byte compressed key with valid prefix but NOT a valid curve point
    //     (all zeros x-coordinate is not on secp256r1)
    {
        OctetString invalidPointKey =
            OctetString::FromHex("020000000000000000000000000000000000000000000000000000000000000000");
        std::string result = eciesProfileB(msin, invalidPointKey, ephPriv);
        TEST_ASSERT(result.empty(), "soft-fail: invalid curve point returns empty");
    }

    // (d) 65-byte key with invalid prefix (0x03 instead of 0x04)
    {
        OctetString badUncompressedKey =
            OctetString::FromHex("03"
                                 "0000000000000000000000000000000000000000000000000000000000000000"
                                 "0000000000000000000000000000000000000000000000000000000000000000");
        std::string result = eciesProfileB(msin, badUncompressedKey, ephPriv);
        TEST_ASSERT(result.empty(), "soft-fail: 65-byte key with invalid prefix 0x03 returns empty");
    }
}
