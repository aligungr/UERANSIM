//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <memory>
#include <stdexcept>
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

// What DecodeNasMessage() throws for a message that must not decode.
static nas::NasDecodeError DecodeFailure(const std::string &hex)
{
    OctetString pdu = OctetString::FromHex(hex);
    try
    {
        nas::DecodeNasMessage(OctetView{pdu});
    }
    catch (const nas::NasDecodeError &e)
    {
        return e;
    }
    throw std::logic_error("the message decoded");
}

// TS 24.501 §7.2.1, and for a security protected message its MAC and sequence number.
TEST_CASE("lib/nas/encode: a message too short to hold its message type does not decode, as too short")
{
    CHECK_EQ(DecodeFailure("").failure, nas::EDecodeFailure::TOO_SHORT);
    CHECK_EQ(DecodeFailure("7e00").failure, nas::EDecodeFailure::TOO_SHORT);
    CHECK_EQ(DecodeFailure("2e0507").failure, nas::EDecodeFailure::TOO_SHORT);
    CHECK_EQ(DecodeFailure("7e0201020304").failure, nas::EDecodeFailure::TOO_SHORT);
}

TEST_CASE("lib/nas/encode: a message of neither 5GMM nor 5GSM does not decode, as of an unknown protocol")
{
    CHECK_EQ(DecodeFailure("0f0040").failure, nas::EDecodeFailure::UNKNOWN_PROTOCOL);
}

TEST_CASE("lib/nas/encode: a message of an undefined type does not decode, and says what its header holds")
{
    auto mm = DecodeFailure("7e0040");
    CHECK_EQ(mm.failure, nas::EDecodeFailure::UNKNOWN_MESSAGE_TYPE);
    CHECK_EQ(mm.epd, nas::EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES);
    CHECK_EQ(static_cast<int>(mm.messageType), 0x40);

    auto sm = DecodeFailure("2e0507c0");
    CHECK_EQ(sm.failure, nas::EDecodeFailure::UNKNOWN_MESSAGE_TYPE);
    CHECK_EQ(sm.epd, nas::EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES);
    CHECK_EQ(sm.pduSessionId, 5);
    CHECK_EQ(sm.pti, 7);
    CHECK_EQ(static_cast<int>(sm.messageType), 0xc0);
}

// The mandatory 5GS registration result claims one octet of contents and the message ends there.
TEST_CASE("lib/nas/encode: a message cut short in its mandatory part does not decode, as invalid mandatory information")
{
    auto error = DecodeFailure("7e004201");
    CHECK_EQ(error.failure, nas::EDecodeFailure::INVALID_MANDATORY_INFORMATION);
    CHECK_EQ(error.messageType, nas::EMessageType::REGISTRATION_ACCEPT);
}

// TS 24.501 §7.5.1: an IE the message does not define, encoded as comprehension required (IEI 0000xxxx).
TEST_CASE("lib/nas/encode: an unknown IE encoded as comprehension required does not decode, as invalid mandatory "
          "information")
{
    CHECK_EQ(DecodeFailure("7e00420101050100").failure, nas::EDecodeFailure::INVALID_MANDATORY_INFORMATION);
}
