//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <cstdint>
#include <string>

#include <ext/doctest/doctest.h>
#include <lib/crypt/eea3.hpp>
#include <utils/octet_string.hpp>

// Test sets 1 to 3 of 128-EIA3 in "Specification of the 3GPP Confidentiality and Integrity Algorithms 128-EEA3 &
// 128-EIA3, Document 3: Implementor's Test Data". None of their lengths is a whole number of bytes, so they can only
// be checked here, below the NAS API.
TEST_CASE("lib/crypt/eea3: 128-EIA3 matches the ETSI SAGE reference MAC")
{
    std::string key, message;
    uint32_t count{}, bearer{}, direction{}, bits{}, mac{};

    SUBCASE("test set 1: 1 bit")
    {
        key = "00000000000000000000000000000000";
        count = 0x0;
        bearer = 0x0;
        direction = 0;
        bits = 1;
        message = "00000000";
        mac = 0xc8a9595e;
    }
    SUBCASE("test set 2: 90 bits")
    {
        key = "47054125561eb2dda94059da05097850";
        count = 0x561eb2dd;
        bearer = 0x14;
        direction = 0;
        bits = 90;
        message = "000000000000000000000000";
        mac = 0x6719a088;
    }
    SUBCASE("test set 3: 577 bits")
    {
        key = "c9e6cec4607c72db000aefa88385ab0a";
        count = 0xa94059da;
        bearer = 0xa;
        direction = 1;
        bits = 577;
        message = "983b41d47d780c9e1ad11d7eb70391b1de0b35da2dc62f83e7b78d6306ca0ea07e941b7be91348f9fcb170e2217fecd97f9f"
                  "68adb16e5d7d21e569d280ed775cebde3f4093c5388100000000";
        mac = 0xfae8ff0b;
    }

    OctetString k = OctetString::FromHex(key);
    OctetString m = OctetString::FromHex(message);
    CHECK_EQ(crypto::eea3::EIA3(k.data(), count, direction, bearer, bits, m.data()), mac);
}
