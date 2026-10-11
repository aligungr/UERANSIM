//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <cstdint>
#include <string>
#include <vector>

#include <ext/doctest/doctest.h>
#include <lib/crypt/crypt.hpp>
#include <lib/crypt/eea3.hpp>
#include <utils/octet_string.hpp>

// The message in a buffer that holds exactly its bytes. The NAS layer ciphers such copies (src/ue/nas/enc.cpp), and
// with no spare capacity behind them a read or a write past the end of the message lands outside the allocation,
// where ASan sees it.
static OctetString Exact(const std::string &hex)
{
    return OctetString::FromHex(hex).copy();
}

static OctetString Pattern(size_t length)
{
    std::vector<uint8_t> bytes(length);
    for (size_t i = 0; i < length; i++)
        bytes[i] = static_cast<uint8_t>(0xA5 ^ (i * 29));
    return OctetString(std::move(bytes));
}

// The first `bits` bits of `data`, in hex. The reference ciphertexts only define the first LENGTH bits, while the
// NAS API always ciphers whole bytes.
static std::string FirstBits(const OctetString &data, uint32_t bits)
{
    OctetString prefix = data.subCopy(0, static_cast<int>((bits + 7) / 8));
    if (bits % 8 != 0)
        prefix.data()[prefix.length() - 1] &= static_cast<uint8_t>(0xFF << (8 - bits % 8));
    return prefix.toHexString();
}

// Test set 3 of 128-EEA1 in TS 33.401 Annex C: 310 bits, so 39 bytes through the NAS API.
TEST_CASE("lib/crypt/crypt: 128-NEA1 matches the TS 33.401 reference ciphertext")
{
    OctetString key = OctetString::FromHex("0a8b6bd8d9b08b08d64e32d1817777fb");
    OctetString message = Exact("fd40a41d370a1f65745095687d47ba1d36d2349e23f644392c8ea9c49d40c13271aff264d0f248");
    OctetString expected =
        OctetString::FromHex("48148e5452a210c05f46bc80dc6f73495b02048c1b958b026102ca97280279a4c18d2ee308921c");

    crypto::EncryptEea1(0x544d49cd, 0x04, 0, message, key);
    CHECK_EQ(FirstBits(message, 310), FirstBits(expected, 310));
}

// Test sets 1 and 2 of "Specification of the 3GPP Confidentiality and Integrity Algorithms 128-EEA3 & 128-EIA3,
// Document 3: Implementor's Test Data".
TEST_CASE("lib/crypt/crypt: 128-NEA3 matches the ETSI SAGE reference ciphertext")
{
    std::string key, plaintext, ciphertext;
    uint32_t count{}, bits{};
    int bearer{}, direction{};

    SUBCASE("test set 1: 193 bits, so 25 bytes through the NAS API")
    {
        key = "173d14ba5003731d7a60049470f00a29";
        count = 0x66035492;
        bearer = 0xf;
        direction = 0;
        bits = 193;
        plaintext = "6cf65340735552ab0c9752fa6f9025fe0bd675d9005875b200";
        ciphertext = "a6c85fc66afb8533aafc2518dfe784940ee1e4b030238cc800";
    }
    SUBCASE("test set 2: 800 bits")
    {
        key = "e5bd3ea0eb55ade866c6ac58bd54302a";
        count = 0x56823;
        bearer = 0x18;
        direction = 1;
        bits = 800;
        plaintext = "14a8ef693d678507bbe7270a7f67ff5006c3525b9807e467c4e56000ba338f5d429559036751822246c80d3b38f07f4be2d8"
                    "ff5805f5132229bde93bbbdcaf382bf1ee972fbf9977bada8945847a2a6c9ad34a667554e04d1f7fa2c33241bd8f01ba220d";
        ciphertext = "131d43e0dea1be5c5a1bfd971d852cbf712d7b4f57961fea3208afa8bca433f456ad09c7417e58bc69cf8866d1353f74865e"
                     "80781d202dfb3ecff7fcbc3b190fe82a204ed0e350fc0f6f2613b2f2bca6df5a473a57a4a00d985ebad880d6f23864a07b01";
    }

    OctetString message = Exact(plaintext);
    crypto::EncryptEea3(count, bearer, direction, message, OctetString::FromHex(key));
    CHECK_EQ(FirstBits(message, bits), FirstBits(OctetString::FromHex(ciphertext), bits));

    crypto::DecryptEea3(count, bearer, direction, message, OctetString::FromHex(key));
    CHECK_EQ(FirstBits(message, bits), FirstBits(OctetString::FromHex(plaintext), bits));
}

// Both algorithms produce their keystream in 32-bit words, while NAS messages can have any length.
TEST_CASE("lib/crypt/crypt: 128-NEA1 decrypts what it encrypted, for every message length up to 12 bytes")
{
    OctetString key = OctetString::FromHex("d3c5d592327fb11c4035c6680af8c6d1");
    for (size_t length = 1; length <= 12; length++)
    {
        CAPTURE(length);
        OctetString message = Pattern(length);
        crypto::EncryptEea1(0x398a59b4, 0x15, 1, message, key);
        crypto::DecryptEea1(0x398a59b4, 0x15, 1, message, key);
        CHECK_EQ(message.toHexString(), Pattern(length).toHexString());
    }
}

TEST_CASE("lib/crypt/crypt: 128-NEA3 decrypts what it encrypted, for every message length up to 12 bytes")
{
    OctetString key = OctetString::FromHex("173d14ba5003731d7a60049470f00a29");
    for (size_t length = 1; length <= 12; length++)
    {
        CAPTURE(length);
        OctetString message = Pattern(length);
        crypto::EncryptEea3(0x66035492, 0xf, 0, message, key);
        crypto::DecryptEea3(0x66035492, 0xf, 0, message, key);
        CHECK_EQ(message.toHexString(), Pattern(length).toHexString());
    }
}

// The published 128-EIA3 lengths are not whole bytes, so the algorithm is checked against them in eea3.cpp; this checks
// that the NAS API hands it the message and the parameters in the right order.
TEST_CASE("lib/crypt/crypt: 128-NIA3 is EIA3 over the whole message")
{
    OctetString key = OctetString::FromHex("c9e6cec4607c72db000aefa88385ab0a");
    OctetString message = OctetString::FromHex("983b41d47d780c9e1ad11d7eb70391b1de0b35da2dc62f83");

    CHECK_EQ(crypto::ComputeMacEia3(0xa94059da, 0xa, 1, message, key),
             crypto::eea3::EIA3(key.data(), 0xa94059da, 1, 0xa, message.length() * 8, message.data()));
}

// The MAC must not depend on anything past the message, so the same bytes in two separate buffers give the same MAC.
TEST_CASE("lib/crypt/crypt: the 128-NIA3 MAC covers only the message, for every length up to 12 bytes")
{
    OctetString key = OctetString::FromHex("c9e6cec4607c72db000aefa88385ab0a");
    for (size_t length = 1; length <= 12; length++)
    {
        CAPTURE(length);
        CHECK_EQ(crypto::ComputeMacEia3(0xa94059da, 0xa, 1, Pattern(length), key),
                 crypto::ComputeMacEia3(0xa94059da, 0xa, 1, Pattern(length), key));
    }
}
