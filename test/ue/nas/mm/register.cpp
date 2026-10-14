//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <memory>
#include <string>
#include <vector>

#include <ext/doctest/doctest.h>
#include <lib/nas/msg.hpp>
#include <test/support/ue_nas_harness.hpp>
#include <ue/nts.hpp>
#include <ue/types.hpp>

using namespace nr::ue;

static std::string NameOf(EMmSubState state)
{
    return ToJson(state).str();
}

// The registration request among the uplink NAS messages, which must be the only one.
static std::unique_ptr<nas::RegistrationRequest> TakeRegistrationRequest(UeNasHarness &ue)
{
    auto uplink = ue.takeUplinkNas();
    REQUIRE_EQ(uplink.size(), 1u);
    auto *request = dynamic_cast<nas::RegistrationRequest *>(uplink[0].get());
    REQUIRE(request != nullptr);
    uplink[0].release();
    return std::unique_ptr<nas::RegistrationRequest>{request};
}

static bool AskedForLocalRelease(UeNasHarness &ue)
{
    for (auto &msg : ue.takeToRrc())
        if (msg->present == NmUeNasToRrc::LOCAL_RELEASE_CONNECTION)
            return true;
    return false;
}

TEST_CASE("ue/nas/mm/register: on a suitable cell, the UE asks for initial registration with its SUCI")
{
    UeNasHarness ue;
    ue.selectCell();

    auto request = TakeRegistrationRequest(ue);
    CHECK_EQ(request->registrationType.registrationType, nas::ERegistrationType::INITIAL_REGISTRATION);
    CHECK_EQ(request->mobileIdentity.type, nas::EIdentityType::SUCI);
    CHECK_EQ(ue.mmSubState(), NameOf(EMmSubState::MM_REGISTERED_INITIATED_PS));
}

TEST_CASE("ue/nas/mm/register: unanswered, the attempt ends when T3510 expires and starts again when T3511 does")
{
    UeNasHarness ue;
    ue.selectCell();
    TakeRegistrationRequest(ue);
    ue.connectionSetup();

    // T3510 runs for 15 s (TS 24.501 §10.2).
    ue.advance(14'000);
    CHECK_EQ(ue.mmSubState(), NameOf(EMmSubState::MM_REGISTERED_INITIATED_PS));

    ue.advance(1'500);
    CHECK_EQ(ue.mmSubState(), NameOf(EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION));
    CHECK(AskedForLocalRelease(ue));
    ue.connectionRelease();

    // Then T3511, for 10 s.
    ue.advance(9'000);
    CHECK(ue.takeUplinkNas().empty());

    ue.advance(1'500);
    auto retry = TakeRegistrationRequest(ue);
    CHECK_EQ(retry->registrationType.registrationType, nas::ERegistrationType::INITIAL_REGISTRATION);
    CHECK_EQ(ue.mmSubState(), NameOf(EMmSubState::MM_REGISTERED_INITIATED_PS));
}
