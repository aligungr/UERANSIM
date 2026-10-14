//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <ext/doctest/doctest.h>
#include <test/support/ue_nas_harness.hpp>
#include <ue/types.hpp>

using namespace nr::ue;

TEST_CASE("ue/nas/mm/base: the node listener hears the MM state and substate under their own names")
{
    UeNasHarness ue;
    ue.selectCell();

    CHECK_EQ(ue.mmState(), ToJson(EMmState::MM_REGISTERED_INITIATED).str());
    CHECK_EQ(ue.mmSubState(), ToJson(EMmSubState::MM_REGISTERED_INITIATED_PS).str());

    // Unanswered, the registration attempt ends with T3510.
    ue.advance(16'000);

    CHECK_EQ(ue.mmState(), ToJson(EMmState::MM_DEREGISTERED).str());
    CHECK_EQ(ue.mmSubState(), ToJson(EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION).str());
}
