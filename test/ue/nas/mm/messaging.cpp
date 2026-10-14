//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <ext/doctest/doctest.h>
#include <lib/nas/msg.hpp>
#include <test/support/ue_nas_harness.hpp>
#include <utils/octet_string.hpp>

// TS 24.501 §7: what the UE does with a downlink 5GMM message it cannot decode. Each test starts from a UE that has
// sent its initial registration request over an RRC connection, and so takes downlink NAS.

static void Register(UeNasHarness &ue)
{
    ue.selectCell();
    ue.connectionSetup();
    ue.takeUplinkNas();
}

// The cause of the single uplink NAS message, which has to be a 5GMM STATUS.
static nas::EMmCause TakeMmStatusCause(UeNasHarness &ue)
{
    auto uplink = ue.takeUplinkNas();
    REQUIRE_EQ(uplink.size(), 1u);
    auto *status = dynamic_cast<nas::FiveGMmStatus *>(uplink[0].get());
    REQUIRE(status != nullptr);
    return status->mmCause.value;
}

TEST_CASE("ue/nas/mm/messaging: a downlink message too short to hold its message type is ignored")
{
    UeNasHarness ue;
    Register(ue);

    // §7.2.1
    ue.receive(OctetString::FromHex("7e00"));
    CHECK(ue.takeUplinkNas().empty());
}

TEST_CASE("ue/nas/mm/messaging: a downlink message of neither 5GMM nor 5GSM is ignored")
{
    UeNasHarness ue;
    Register(ue);

    ue.receive(OctetString::FromHex("0f0040"));
    CHECK(ue.takeUplinkNas().empty());
}

TEST_CASE("ue/nas/mm/messaging: a downlink 5GMM message of an unknown type is answered with cause #97")
{
    UeNasHarness ue;
    Register(ue);

    // §7.4: message type 0x40 is not defined.
    ue.receive(OctetString::FromHex("7e0040"));
    CHECK_EQ(TakeMmStatusCause(ue), nas::EMmCause::MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED);
}

TEST_CASE("ue/nas/mm/messaging: a downlink 5GMM message of a type the UE does not take is answered with cause #97")
{
    UeNasHarness ue;
    Register(ue);

    // §7.4: a REGISTRATION COMPLETE, which only the UE sends.
    ue.receive(OctetString::FromHex("7e0043"));
    CHECK_EQ(TakeMmStatusCause(ue), nas::EMmCause::MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED);
}

TEST_CASE("ue/nas/mm/messaging: a downlink 5GMM message without a mandatory IE is answered with cause #96")
{
    UeNasHarness ue;
    Register(ue);

    // §7.5.1: an IDENTITY REQUEST that ends before its identity type.
    ue.receive(OctetString::FromHex("7e005b"));
    CHECK_EQ(TakeMmStatusCause(ue), nas::EMmCause::INVALID_MANDATORY_INFORMATION);
}

TEST_CASE("ue/nas/mm/messaging: a downlink 5GMM message with an unknown comprehension required IE is answered with "
          "cause #96")
{
    UeNasHarness ue;
    Register(ue);

    // §7.5.1: an IDENTITY REQUEST for the SUCI, then an IE with IEI 0x05, which the 0000 in its upper bits marks as
    // comprehension required (TS 24.007 §11.2.4).
    ue.receive(OctetString::FromHex("7e005b01"
                                    "050100"));
    CHECK_EQ(TakeMmStatusCause(ue), nas::EMmCause::INVALID_MANDATORY_INFORMATION);
}
