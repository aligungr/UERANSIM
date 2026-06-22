//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "test_util.hpp"
#include <lib/nas/ie6.hpp>
#include <utils/octet_string.hpp>

void run_regression_tests()
{
    // --- Test 1: Encoder length for Profile B SUCI IE ---
    // Construct a SUCI IE with Profile B (scheme 2) using the §C.4 expected schemeOutput.
    // schemeOutput = compressedEphPub(33) || ciphertext(5) || macTag(8) = 46 bytes = 92 hex chars
    {
        nas::IE5gsMobileIdentity ie{};
        ie.type = nas::EIdentityType::SUCI;
        ie.supiFormat = nas::ESupiFormat::IMSI;
        ie.imsi.plmn = Plmn{001, 01, false};
        ie.imsi.routingIndicator = "1234";
        ie.imsi.protectionSchemaId = 2; // Profile B
        ie.imsi.homeNetworkPublicKeyIdentifier = 27;
        ie.imsi.schemeOutput = "039aab8376597021e855679a9778ea0b67396e68c66df32c0f41e9acca2da9b9d1"
                               "46a21f4297e43b1f2f7cff7936";

        OctetString encoded;
        nas::IE5gsMobileIdentity::Encode(ie, encoded);

        // Expected length: 1 (type) + 3 (PLMN) + 2 (routing indicator) + 1 (schemeId) + 1 (keyId)
        //                  + 46 (schemeOutput bytes from 92 hex chars) = 54
        int schemeOutputBytes = static_cast<int>(ie.imsi.schemeOutput.size()) / 2;
        int expectedLen = 1 + 3 + 2 + 1 + 1 + schemeOutputBytes;
        TEST_ASSERT_EQ(encoded.length(), expectedLen);
    }

    // --- Test 2: Profile A structural — output hex length and scheme id ---
    // Profile A (scheme 1): schemeOutput = uePublicKey(32) || cipher(msinLen) || macTag(8)
    // For a 10-digit MSIN → BCD = 5 bytes → output = (32 + 5 + 8) * 2 = 90 hex chars
    // Construct IE with a 90-char hex scheme output and verify encoder length.
    {
        nas::IE5gsMobileIdentity ie{};
        ie.type = nas::EIdentityType::SUCI;
        ie.supiFormat = nas::ESupiFormat::IMSI;
        ie.imsi.plmn = Plmn{310, 260, true};
        ie.imsi.routingIndicator = "17";
        ie.imsi.protectionSchemaId = 1; // Profile A
        ie.imsi.homeNetworkPublicKeyIdentifier = 1;
        // Fake 45-byte (90 hex char) scheme output (pubkey32 + cipher5 + mac8)
        ie.imsi.schemeOutput = std::string(90, 'a');

        OctetString encoded;
        nas::IE5gsMobileIdentity::Encode(ie, encoded);

        // Expected: 1 + 3 + 2 + 1 + 1 + 45 = 53
        int expectedLen = 1 + 3 + 2 + 1 + 1 + 45;
        TEST_ASSERT_EQ(encoded.length(), expectedLen);

        // Verify scheme id is encoded at correct offset (byte index 6, after type+PLMN+routingInd)
        TEST_ASSERT_EQ(static_cast<int>(encoded.getI(6)), 1);
    }

    // --- Test 3: Null scheme unchanged — scheme 0 produces BCD-encoded MSIN ---
    // For Null scheme: schemeOutput is the decimal MSIN string, BCD-encoded in the IE.
    {
        nas::IE5gsMobileIdentity ie{};
        ie.type = nas::EIdentityType::SUCI;
        ie.supiFormat = nas::ESupiFormat::IMSI;
        ie.imsi.plmn = Plmn{208, 93, false};
        ie.imsi.routingIndicator = "1";
        ie.imsi.protectionSchemaId = 0; // Null scheme
        ie.imsi.homeNetworkPublicKeyIdentifier = 0;
        ie.imsi.schemeOutput = "0000000001"; // 10-digit MSIN

        OctetString encoded;
        nas::IE5gsMobileIdentity::Encode(ie, encoded);

        // Null scheme: BCD encodes 10 digits into 5 bytes
        // Expected: 1 + 3 + 2 (routing indicator) + 1 + 1 + 5 = 13
        int expectedLen = 1 + 3 + 2 + 1 + 1 + 5;
        TEST_ASSERT_EQ(encoded.length(), expectedLen);

        // protectionSchemaId at byte 6 must be 0
        TEST_ASSERT_EQ(static_cast<int>(encoded.getI(6)), 0);

        // Decode the BCD portion and verify it matches the original MSIN
        // Bytes 8..12 are the BCD-encoded MSIN (5 bytes for "0000000001")
        // BCD of "0000000001" => 0x00, 0x00, 0x00, 0x00, 0x10
        TEST_ASSERT_EQ(static_cast<int>(encoded.getI(8)), 0x00);
        TEST_ASSERT_EQ(static_cast<int>(encoded.getI(9)), 0x00);
        TEST_ASSERT_EQ(static_cast<int>(encoded.getI(10)), 0x00);
        TEST_ASSERT_EQ(static_cast<int>(encoded.getI(11)), 0x00);
        TEST_ASSERT_EQ(static_cast<int>(encoded.getI(12)), 0x10);
    }
}
