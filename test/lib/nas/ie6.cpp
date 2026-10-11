//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <string>

#include <ext/doctest/doctest.h>
#include <lib/nas/ie6.hpp>
#include <utils/octet_string.hpp>

// A SUCI IE with Profile B (scheme 2) and the given scheme output
static nas::IE5gsMobileIdentity ProfileBSuci(const std::string &schemeOutput)
{
    nas::IE5gsMobileIdentity ie{};
    ie.type = nas::EIdentityType::SUCI;
    ie.supiFormat = nas::ESupiFormat::IMSI;
    ie.imsi.plmn = Plmn{001, 01, false};
    ie.imsi.routingIndicator = "1234";
    ie.imsi.protectionSchemaId = 2; // Profile B
    ie.imsi.homeNetworkPublicKeyIdentifier = 27;
    ie.imsi.schemeOutput = schemeOutput;
    return ie;
}

// The §C.4 expected schemeOutput = compressedEphPub(33) || ciphertext(5) || macTag(8) = 46 bytes = 92 hex chars
static const std::string C4_SCHEME_OUTPUT = "039AAB8376597021E855679A9778EA0B67396E68C66DF32C0F41E9ACCA2DA9B9D1"
                                            "46A21F4297E43B1F2F7CFF7936";

TEST_CASE("lib/nas/ie6: SUCI with a Profile B scheme output")
{
    nas::IE5gsMobileIdentity ie = ProfileBSuci(C4_SCHEME_OUTPUT);

    OctetString encoded;
    nas::IE5gsMobileIdentity::Encode(ie, encoded);

    // Expected length: 1 (type) + 3 (PLMN) + 2 (routing indicator) + 1 (schemeId) + 1 (keyId)
    //                  + 46 (schemeOutput bytes from 92 hex chars) = 54
    int schemeOutputBytes = static_cast<int>(ie.imsi.schemeOutput.size()) / 2;
    int expectedLen = 1 + 3 + 2 + 1 + 1 + schemeOutputBytes;
    CHECK_EQ(encoded.length(), expectedLen);
}

// The scheme output is hex, and OctetString::FromHex takes either case, so the case the
// generator happens to emit must not reach the wire.
TEST_CASE("lib/nas/ie6: SUCI scheme output hex case does not change the encoded bytes")
{
    OctetString encoded;
    nas::IE5gsMobileIdentity::Encode(ProfileBSuci(C4_SCHEME_OUTPUT), encoded);

    OctetString lowerEncoded;
    nas::IE5gsMobileIdentity::Encode(
        ProfileBSuci("039aab8376597021e855679a9778ea0b67396e68c66df32c0f41e9acca2da9b9d1"
                     "46a21f4297e43b1f2f7cff7936"),
        lowerEncoded);

    CHECK_EQ(lowerEncoded.toHexString(), encoded.toHexString());
}

// Profile A (scheme 1): schemeOutput = uePublicKey(32) || cipher(msinLen) || macTag(8)
// For a 10-digit MSIN → BCD = 5 bytes → output = (32 + 5 + 8) * 2 = 90 hex chars
TEST_CASE("lib/nas/ie6: SUCI with a Profile A scheme output")
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
    REQUIRE_EQ(encoded.length(), 1 + 3 + 2 + 1 + 1 + 45);

    // Verify scheme id is encoded at correct offset (byte index 6, after type+PLMN+routingInd)
    CHECK_EQ(encoded.getI(6), 1);
}

// For Null scheme: schemeOutput is the decimal MSIN string, BCD-encoded in the IE.
TEST_CASE("lib/nas/ie6: SUCI with the null scheme BCD-encodes the MSIN")
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
    REQUIRE_EQ(encoded.length(), 1 + 3 + 2 + 1 + 1 + 5);

    // protectionSchemaId at byte 6 must be 0
    CHECK_EQ(encoded.getI(6), 0);

    // Bytes 8..12 are the BCD-encoded MSIN (5 bytes for "0000000001")
    // BCD of "0000000001" => 0x00, 0x00, 0x00, 0x00, 0x10
    CHECK_EQ(encoded.getI(8), 0x00);
    CHECK_EQ(encoded.getI(9), 0x00);
    CHECK_EQ(encoded.getI(10), 0x00);
    CHECK_EQ(encoded.getI(11), 0x00);
    CHECK_EQ(encoded.getI(12), 0x10);
}
