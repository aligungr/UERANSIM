//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <string>

#include <ext/doctest/doctest.h>
#include <lib/nas/encode.hpp>
#include <lib/nas/msg.hpp>
#include <test/support/ue_nas_harness.hpp>
#include <utils/octet_string.hpp>

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

TEST_CASE("ue/nas/sm/transport: a 5GSM message for a reserved PDU session identity is ignored")
{
    UeNasHarness ue;
    Register(ue);

    // §7.3.2: a PDU SESSION RELEASE COMMAND for PDU session 200, "regular deactivation".
    ue.receive(DlNasTransport("2ec800d324"));
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
