//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <string>

namespace nr::ue::tun
{

int TunAllocate(const char *namePrefix, std::string &allocatedName, std::string &error);
bool TunConfigure(const std::string &tunName, const std::string &ipAddress, const std::string &netmask, int mtu, bool configureRouting, std::string &error);

} // namespace nr::ue::tun
