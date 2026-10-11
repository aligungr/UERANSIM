//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <memory>
#include <string>

#include <ext/doctest/doctest.h>
#include <lib/nas/eap.hpp>
#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>

// An EAP-AKA' Challenge request (RFC 5448): code 1, identifier 5, type 50, subtype 1 and two reserved octets, then the
// given attributes. The EAP length covers them all, give or take `lengthAdjustment`.
static std::unique_ptr<eap::Eap> DecodeChallenge(const std::string &attributes, int lengthAdjustment = 0)
{
    OctetString body = OctetString::FromHex("32010000" + attributes);
    OctetString pdu;
    pdu.appendOctet(1);
    pdu.appendOctet(5);
    pdu.appendOctet2(4 + body.length() + lengthAdjustment);
    pdu.append(body);
    return eap::DecodeEapPdu(OctetView{pdu});
}

// AT_RAND: type 1, five 4-octet units, two reserved octets and the 16 octet RAND.
static const std::string AT_RAND = "01050000"
                                   "00112233445566778899aabbccddeeff";

// AT_KDF: type 24, one 4-octet unit, KDF 1.
static const std::string AT_KDF = "18010001";

// RFC 5448 requires AT_KDF, which is one unit long, in every EAP-AKA' challenge.
TEST_CASE("lib/nas/eap: an attribute one unit long decodes")
{
    auto decoded = DecodeChallenge(AT_RAND + AT_KDF);
    REQUIRE(decoded != nullptr);
    REQUIRE(decoded->eapType == eap::EEapType::EAP_AKA_PRIME);

    auto &akaPrime = static_cast<const eap::EapAkaPrime &>(*decoded);
    CHECK_EQ(akaPrime.attributes.getKdf(), 1);
    CHECK_EQ(akaPrime.attributes.getRand().toHexString(), "00112233445566778899AABBCCDDEEFF");
}

// RFC 4187 §8.1: attributes 128 to 255 are skippable, and a peer ignores one it does not know.
TEST_CASE("lib/nas/eap: an unknown skippable attribute is ignored")
{
    auto decoded = DecodeChallenge(AT_RAND + "c801abcd" + AT_KDF);
    REQUIRE(decoded != nullptr);
    REQUIRE(decoded->eapType == eap::EEapType::EAP_AKA_PRIME);

    auto &akaPrime = static_cast<const eap::EapAkaPrime &>(*decoded);
    CHECK_EQ(akaPrime.attributes.getKdf(), 1);
    CHECK_EQ(akaPrime.attributes.getRand().toHexString(), "00112233445566778899AABBCCDDEEFF");
}

// The EAP length leaves 4 octets fewer than AT_RAND takes.
TEST_CASE("lib/nas/eap: attributes that overrun the EAP length do not decode")
{
    auto decoded = DecodeChallenge(AT_RAND, -4);
    CHECK(decoded.get() == nullptr);
}

// RFC 4187 §8.1: attribute 0 is neither known nor skippable.
TEST_CASE("lib/nas/eap: an attribute of type 0 does not decode")
{
    auto decoded = DecodeChallenge(AT_RAND + "00010000");
    CHECK(decoded.get() == nullptr);
}
