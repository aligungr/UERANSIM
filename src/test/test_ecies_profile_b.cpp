//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "test_util.hpp"
#include <ue/nas/mm/ecies_profile_b.hpp>

void run_ecies_profile_b_tests()
{
    // Throwaway inputs to prove linkage
    OctetString msin = OctetString::FromHex("1234567890");
    OctetString hnKey = OctetString::FromHex("aabbccdd");
    OctetString ephPriv = OctetString::FromHex("00112233445566778899aabbccddeeff00112233445566778899aabbccddeeff");

    std::string result = eciesProfileB(msin, hnKey, ephPriv);
    TEST_ASSERT(result.empty(), "eciesProfileB stub returns empty string");
}
