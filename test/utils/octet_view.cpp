//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <stdexcept>

#include <ext/doctest/doctest.h>
#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>

TEST_CASE("utils/octet_view: a sub-view covers the next octets, which the view steps over")
{
    OctetString octets = OctetString::FromHex("0102030405");
    OctetView view{octets};
    view.read();

    OctetView sub = view.subView(2);
    CHECK_EQ(sub.remainingSize(), 2u);
    CHECK_EQ(sub.readI(), 2);
    CHECK_EQ(sub.readI(), 3);
    CHECK_THROWS_AS(sub.read(), std::out_of_range);

    CHECK_EQ(view.readI(), 4);
}

TEST_CASE("utils/octet_view: a sub-view longer than what is left throws and consumes nothing")
{
    OctetString octets = OctetString::FromHex("010203");
    OctetView view{octets};
    view.read();

    CHECK_THROWS_AS(view.subView(3), std::out_of_range);
    CHECK_EQ(view.readI(), 2);
}
