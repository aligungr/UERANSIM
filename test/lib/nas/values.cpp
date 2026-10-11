//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <ext/doctest/doctest.h>
#include <lib/nas/values.hpp>
#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>

// TS 24.501 §9.11.3.9 defines the types of list 00, 01 and 10; 11 is reserved, so there is no telling how long such a
// partial list is. Decoding one without consuming anything sent the list decoder of the 5GS TAI list into an endless
// loop.
TEST_CASE("lib/nas/values: a partial tracking area identity list of the reserved type does not decode")
{
    OctetString octets = OctetString::FromHex("6000f110000001");
    CHECK_THROWS(nas::VPartialTrackingAreaIdentityList::Decode(OctetView{octets}));
}
