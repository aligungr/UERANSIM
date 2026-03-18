//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <sstream>
#include <string>
#include <utility>
#include <vector>

namespace nr::ue::tun
{

int AllocateTun(const char *ifPrefix, char **allocatedName);
void ConfigureTun(const char *tunName, const char *ipAddr, const char *netmask, int mtu, bool configureRoute);
void ConfigureFramedRoutes(const char *tunName, const std::vector<std::string> &routes, bool configureRoute);

} // namespace nr::ue::tun
