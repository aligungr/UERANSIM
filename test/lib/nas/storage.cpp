//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <optional>
#include <vector>

#include <ext/doctest/doctest.h>
#include <lib/nas/storage.hpp>

static std::vector<int> ItemsOf(nas::NasList<int> &list)
{
    std::vector<int> items;
    list.forEach([&items](const int &item) { items.push_back(item); });
    return items;
}

// The UE clears its list of equivalent PLMNs and adds the registered PLMN on every registration accept.
TEST_CASE("lib/nas/storage: a list takes items again after it is cleared")
{
    nas::NasList<int> list{4, 0, std::nullopt};
    list.add(1);
    list.add(2);

    list.clear();
    CHECK_EQ(list.size(), 0u);

    list.add(3);
    CHECK_EQ(ItemsOf(list), std::vector<int>{3});
    CHECK_EQ(list.size(), 1u);
}
