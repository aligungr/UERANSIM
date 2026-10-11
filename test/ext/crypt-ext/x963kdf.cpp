//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <cstdint>
#include <string>
#include <vector>

#include <ext/doctest/doctest.h>
#include <utils/common.hpp>
#include <utils/octet_string.hpp>

extern "C"
{
#include <ext/crypt-ext/x963kdf.h>
}

static std::string toHex(const uint8_t *data, size_t length)
{
    return utils::VectorToHexString(std::vector<uint8_t>(data, data + length));
}

// The 32 byte shared secret and shared info of the Profile A path
struct KdfInputs
{
    uint8_t sharedSecret[32]{};
    uint8_t sharedInfo[32]{};

    KdfInputs()
    {
        for (int i = 0; i < 32; i++)
        {
            sharedSecret[i] = static_cast<uint8_t>(0x11 * (i + 1));
            sharedInfo[i] = static_cast<uint8_t>(i * 7 + 3);
        }
    }
};

// 64 byte output. Cross checked against an independent ANSI-X9.63 KDF implementation, so this pins the behaviour
// Profile A relies on.
TEST_CASE_FIXTURE(KdfInputs, "ext/crypt-ext/x963kdf: Profile A output matches an independent implementation")
{
    uint8_t out[64];
    x963kdf(out, sharedSecret, sharedInfo, sizeof(out));
    CHECK_EQ(toHex(out, sizeof(out)), std::string("7808D062A5D71EC45ADA90A0BBFFA1E84EF6AE6C5B2504E84A2F3DC9951A06BB"
                                                  "A2445E51506B1858FAD48F614D68E786A99AB1FCC481B98DEFC4399BEFF66DFF"));
}

TEST_CASE_FIXTURE(KdfInputs, "ext/crypt-ext/x963kdf: x963kdf is x963kdf_ex with both lengths set to 32")
{
    uint8_t viaWrapper[64];
    uint8_t viaEx[64];
    x963kdf(viaWrapper, sharedSecret, sharedInfo, sizeof(viaWrapper));
    x963kdf_ex(viaEx, sharedSecret, 32, sharedInfo, 32, sizeof(viaEx));
    CHECK_EQ(toHex(viaWrapper, sizeof(viaWrapper)), toHex(viaEx, sizeof(viaEx)));
}

// A key size that is not a multiple of the digest size must still be filled completely, and must be a prefix of the
// longer output. The previous implementation computed the block count as ceil(keySize / 32) in integer arithmetic, so
// it produced one block too few and copied uninitialised bytes into the tail.
TEST_CASE_FIXTURE(KdfInputs, "ext/crypt-ext/x963kdf: a key size that is not a multiple of 32 is filled completely")
{
    uint8_t out48[48];
    x963kdf(out48, sharedSecret, sharedInfo, sizeof(out48));
    CHECK_EQ(toHex(out48, sizeof(out48)), std::string("7808D062A5D71EC45ADA90A0BBFFA1E84EF6AE6C5B2504E84A2F3DC9951A06BB"
                                                      "A2445E51506B1858FAD48F614D68E786"));
}

// Profile B path: 33 byte shared info. These are the intermediate keys of the TS 33.501 C.4 vector, so a change here
// would break the SUCI before the vector test could catch it.
TEST_CASE("ext/crypt-ext/x963kdf: TS 33.501 C.4 intermediate keys")
{
    OctetString z = OctetString::FromHex("6C7E6518980025B982FBB2FF746E3C2E85A196D252099A7AD23EA7B4C0959CAE");
    OctetString ephPub = OctetString::FromHex("039AAB8376597021E855679A9778EA0B67396E68C66DF32C0F41E9ACCA2DA9B9D1");

    uint8_t derived[64];
    x963kdf_ex(derived, z.data(), 32, ephPub.data(), 33, sizeof(derived));

    CHECK_EQ(toHex(derived, 16), std::string("8A65C3AED80295C12BD55087E965702A"));      // enc key
    CHECK_EQ(toHex(derived + 16, 16), std::string("EF285B4061C3BAEE858AB6EC68487DAE")); // ICB
    CHECK_EQ(toHex(derived + 32, 32),
             std::string("A5EBAC0BC48D9CF7AE5CE39CD840AC6C761AEC04078FAB954D634F923E901C64")); // mac key
}
