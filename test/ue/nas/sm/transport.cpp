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
#include <test/support/ue_nas_harness.hpp>
#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>

// TS 24.501 §7: what the UE does with a 5GSM message in a DL NAS TRANSPORT that it cannot take as it is. Each test
// starts from a UE that has sent its initial registration request over an RRC connection, and so takes downlink NAS.

static void Register(UeNasHarness &ue)
{
    ue.selectCell();
    ue.connectionSetup();
    ue.takeUplinkNas();
}

static OctetString DlNasTransport(const std::string &smMessage)
{
    nas::DlNasTransport transport;
    transport.payloadContainerType.payloadContainerType = nas::EPayloadContainerType::N1_SM_INFORMATION;
    transport.payloadContainer.data = OctetString::FromHex(smMessage);

    OctetString pdu;
    nas::EncodeNasMessage(transport, pdu);
    return pdu;
}

// The 5GSM message carried by the single uplink NAS message, which has to be a UL NAS TRANSPORT.
static std::unique_ptr<nas::SmMessage> TakeUplinkSm(UeNasHarness &ue)
{
    auto uplink = ue.takeUplinkNas();
    REQUIRE_EQ(uplink.size(), 1u);
    auto *transport = dynamic_cast<nas::UlNasTransport *>(uplink[0].get());
    REQUIRE(transport != nullptr);

    auto decoded = nas::DecodeNasMessage(OctetView{transport->payloadContainer.data});
    auto *sm = dynamic_cast<nas::SmMessage *>(decoded.get());
    REQUIRE(sm != nullptr);
    decoded.release();
    return std::unique_ptr<nas::SmMessage>{sm};
}

TEST_CASE("ue/nas/sm/transport: a 5GSM message of an unknown type is answered with 5GSM STATUS #97")
{
    UeNasHarness ue;
    Register(ue);

    // §7.4: PDU session 5, PTI 7, message type 0xC0, which is not defined.
    ue.receive(DlNasTransport("2e0507c0"));

    auto sm = TakeUplinkSm(ue);
    auto *status = dynamic_cast<nas::FiveGSmStatus *>(sm.get());
    REQUIRE(status != nullptr);
    CHECK_EQ(status->pduSessionId, 5);
    CHECK_EQ(status->pti, 7);
    CHECK_EQ(status->smCause.value, nas::ESmCause::MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED);
}

TEST_CASE("ue/nas/sm/transport: a 5GSM message without a mandatory IE is answered with 5GSM STATUS #96")
{
    UeNasHarness ue;
    Register(ue);

    // §7.5.1: a PDU SESSION ESTABLISHMENT REJECT that ends before its 5GSM cause.
    ue.receive(DlNasTransport("2e0507c3"));

    auto sm = TakeUplinkSm(ue);
    auto *status = dynamic_cast<nas::FiveGSmStatus *>(sm.get());
    REQUIRE(status != nullptr);
    CHECK_EQ(status->pduSessionId, 5);
    CHECK_EQ(status->pti, 7);
    CHECK_EQ(status->smCause.value, nas::ESmCause::INVALID_MANDATORY_INFORMATION);
}

TEST_CASE("ue/nas/sm/transport: a 5GSM message of a type the UE does not take is answered with 5GSM STATUS #97")
{
    UeNasHarness ue;
    Register(ue);

    // §7.4: a PDU SESSION ESTABLISHMENT REQUEST, which only the UE sends.
    ue.receive(DlNasTransport("2e0507c1ffff"));

    auto sm = TakeUplinkSm(ue);
    auto *status = dynamic_cast<nas::FiveGSmStatus *>(sm.get());
    REQUIRE(status != nullptr);
    CHECK_EQ(status->smCause.value, nas::ESmCause::MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED);
}

TEST_CASE("ue/nas/sm/transport: a 5GSM message for a reserved PDU session identity is ignored")
{
    UeNasHarness ue;
    Register(ue);

    // §7.3.2: a PDU SESSION RELEASE COMMAND for PDU session 200, "regular deactivation".
    ue.receive(DlNasTransport("2ec800d324"));
    CHECK(ue.takeUplinkNas().empty());

    // The same, not decoding.
    ue.receive(DlNasTransport("2ec800d3"));
    CHECK(ue.takeUplinkNas().empty());
}

TEST_CASE("ue/nas/sm/transport: a 5GSM STATUS for a reserved procedure transaction identity aborts nothing")
{
    UeNasHarness ue;
    Register(ue);

    // Cause #81 "invalid PTI value", for PTI 255.
    ue.receive(DlNasTransport("2e05ffd651"));
    CHECK(ue.takeUplinkNas().empty());
}

TEST_CASE("ue/nas/sm/transport: a PDU SESSION ESTABLISHMENT ACCEPT without a mandatory IE has the session released "
          "with cause #96")
{
    auto config = UeNasHarness::DefaultConfig();
    nr::ue::SessionConfig session{};
    session.type = nas::EPduSessionType::IPV4;
    session.apn = "internet";
    config.defaultSessions.push_back(session);

    UeNasHarness ue{std::move(config)};
    Register(ue);

    // A REGISTRATION ACCEPT ("3GPP access"), on which the UE asks for its PDU session.
    ue.receive(OctetString::FromHex("7e00420101"));
    auto request = TakeUplinkSm(ue);
    REQUIRE_EQ(request->messageType, nas::EMessageType::PDU_SESSION_ESTABLISHMENT_REQUEST);
    REQUIRE_EQ(request->pduSessionId, 1);
    REQUIRE_EQ(request->pti, 1);

    // §7.5.3 a): the accept for it, ending before its selected PDU session type.
    ue.receive(DlNasTransport("2e0101c2"));

    auto sm = TakeUplinkSm(ue);
    auto *release = dynamic_cast<nas::PduSessionReleaseRequest *>(sm.get());
    REQUIRE(release != nullptr);
    CHECK_EQ(release->pduSessionId, 1);
    REQUIRE(release->smCause.has_value());
    CHECK_EQ(release->smCause->value, nas::ESmCause::INVALID_MANDATORY_INFORMATION);
}

TEST_CASE("ue/nas/sm/transport: a PDU SESSION RELEASE COMMAND without a mandatory IE is answered with a release "
          "complete with cause #96")
{
    UeNasHarness ue;
    Register(ue);

    // §7.5.3 c): a PDU SESSION RELEASE COMMAND that ends before its 5GSM cause.
    ue.receive(DlNasTransport("2e0507d3"));

    auto sm = TakeUplinkSm(ue);
    auto *complete = dynamic_cast<nas::PduSessionReleaseComplete *>(sm.get());
    REQUIRE(complete != nullptr);
    CHECK_EQ(complete->pduSessionId, 5);
    CHECK_EQ(complete->pti, 7);
    REQUIRE(complete->smCause.has_value());
    CHECK_EQ(complete->smCause->value, nas::ESmCause::INVALID_MANDATORY_INFORMATION);
}
