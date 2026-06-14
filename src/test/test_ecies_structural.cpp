//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "test_util.hpp"
#include <cstdlib>
#include <ctime>
#include <ue/nas/mm/ecies_profile_b.hpp>

void run_ecies_profile_b_structural_test()
{
    // Use the same HN public key from the C.4 vector (uncompressed, 65 bytes)
    const std::string hnPubKeyHex = "0472DA71976234CE833A6907425867B82E074D44EF907DFB4B3E21C1C2256EBCD1"
                                    "5A7DED52FCBB097A4ED250E036C7B9C8C7004C4EEDC4F068CD7BF8D3F900E3B4";

    // Generate a pseudo-random 32-byte ephemeral private key (not crypto-secure, just for test)
    std::srand(static_cast<unsigned>(std::time(nullptr)));
    std::string ephPrivHex;
    for (int i = 0; i < 32; i++)
    {
        char buf[3];
        std::snprintf(buf, sizeof(buf), "%02x", std::rand() % 256);
        ephPrivHex += buf;
    }

    // Plaintext MSIN (BCD-encoded, 5 bytes) — same length as C.4 vector
    const std::string plaintextMsinHex = "0000000010";
    int msinLen = 5;

    OctetString hnPubKey = OctetString::FromHex(hnPubKeyHex);
    OctetString ephPrivKey = OctetString::FromHex(ephPrivHex);
    OctetString plaintextMsin = OctetString::FromHex(plaintextMsinHex);

    std::string result = eciesProfileB(plaintextMsin, hnPubKey, ephPrivKey);

    // Structural assertions: output must be non-empty
    TEST_ASSERT(!result.empty(), "structural: eciesProfileB with random key produces output");

    // Expected hex length: (33 + msinLen + 8) * 2 = (33 + 5 + 8) * 2 = 92
    int expectedHexLen = (33 + msinLen + 8) * 2;
    TEST_ASSERT_EQ(static_cast<int>(result.size()), expectedHexLen);

    // First byte (ephemeral pubkey prefix) must be 0x02 or 0x03
    std::string prefix = result.substr(0, 2);
    TEST_ASSERT(prefix == "02" || prefix == "03", "structural: ephemeral pubkey prefix is 02 or 03");
}
