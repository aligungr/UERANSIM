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
#include <lib/nas/encode.hpp>
#include <lib/nas/msg.hpp>
#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>

// A plain Registration Accept whose mandatory 5GS registration result says "3GPP access", followed by `optionalIes`.
static std::unique_ptr<nas::NasMessage> DecodeRegistrationAccept(const std::string &optionalIes)
{
    OctetString pdu = OctetString::FromHex("7e00420101" + optionalIes);
    return nas::DecodeNasMessage(OctetView{pdu});
}

static const nas::RegistrationAccept &AsRegistrationAccept(const std::unique_ptr<nas::NasMessage> &message)
{
    auto *registrationAccept = dynamic_cast<const nas::RegistrationAccept *>(message.get());
    REQUIRE(registrationAccept != nullptr);
    return *registrationAccept;
}

// The T3512 value IE (GPRS timer 3, IEI 0x5E): 6 units of 10 minutes. Placed after the IE under test, it shows that
// decoding carried on past that IE in step.
static const std::string T3512 = "5e0106";

// TS 24.501 §7.6.1: IEs the message does not define are ignored. Their format follows from the IEI alone (TS 24.007
// §11.2.4); here a TLV IE, a TLV-E IE (IEI 0111xxxx) and a type 1 IE (bit 8 set).
TEST_CASE("lib/nas/encode: optional IEs the message does not define are skipped")
{
    auto message = DecodeRegistrationAccept("6a0105"
                                            "740002aabb"
                                            "e1" +
                                            T3512);
    auto &registrationAccept = AsRegistrationAccept(message);

    REQUIRE(registrationAccept.t3512Value.has_value());
    CHECK_EQ(registrationAccept.t3512Value->timerValue, 6);
}

// TS 24.501 §9.11.3.44 allows the PDU session status IE 2 to 32 octets of contents; the ones after the second are
// spare. This one has four.
TEST_CASE("lib/nas/encode: the octets of an IE past the ones its decoder reads are skipped")
{
    auto message = DecodeRegistrationAccept("500420000000" + T3512);
    auto &registrationAccept = AsRegistrationAccept(message);

    REQUIRE(registrationAccept.pduSessionStatus.has_value());
    CHECK(registrationAccept.pduSessionStatus->psi[5]);
    REQUIRE(registrationAccept.t3512Value.has_value());
    CHECK_EQ(registrationAccept.t3512Value->timerValue, 6);
}

// TS 24.501 §7.6.3: of an IE that is not defined as repeatable, only the first occurrence is handled.
TEST_CASE("lib/nas/encode: only the first occurrence of a repeated optional IE counts")
{
    auto message = DecodeRegistrationAccept(T3512 + "5e0107");
    auto &registrationAccept = AsRegistrationAccept(message);

    REQUIRE(registrationAccept.t3512Value.has_value());
    CHECK_EQ(registrationAccept.t3512Value->timerValue, 6);
}

// TS 24.501 §7.7.1: a syntactically incorrect optional IE is treated as not present. This 5G-GUTI has three octets of
// contents, far too few for a GUTI.
TEST_CASE("lib/nas/encode: an optional IE whose contents do not decode is treated as absent")
{
    auto message = DecodeRegistrationAccept("770003f21300" + T3512);
    auto &registrationAccept = AsRegistrationAccept(message);

    CHECK_FALSE(registrationAccept.mobileIdentity.has_value());
    REQUIRE(registrationAccept.t3512Value.has_value());
    CHECK_EQ(registrationAccept.t3512Value->timerValue, 6);
}

// TS 24.501 §9.11.3.9 reserves the type of list 11. A TAI list holding one used to send its decoder into an endless
// loop.
TEST_CASE("lib/nas/encode: a TAI list with a partial list of the reserved type is treated as absent")
{
    auto message = DecodeRegistrationAccept("54046000f110" + T3512);
    auto &registrationAccept = AsRegistrationAccept(message);

    CHECK_FALSE(registrationAccept.taiList.has_value());
    REQUIRE(registrationAccept.t3512Value.has_value());
    CHECK_EQ(registrationAccept.t3512Value->timerValue, 6);
}

// The T3512 value IE claims five octets of contents and the message ends after one.
TEST_CASE("lib/nas/encode: an optional IE cut short by the end of the message is treated as absent")
{
    auto message = DecodeRegistrationAccept("5e0506");
    auto &registrationAccept = AsRegistrationAccept(message);

    CHECK_FALSE(registrationAccept.t3512Value.has_value());
}

// The mandatory 5GS registration result claims one octet of contents and the message ends there.
TEST_CASE("lib/nas/encode: a message cut short in its mandatory part does not decode")
{
    OctetString pdu = OctetString::FromHex("7e004201");
    CHECK_THROWS(nas::DecodeNasMessage(OctetView{pdu}));
}
