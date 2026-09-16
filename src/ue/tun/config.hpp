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

namespace nr::ue::tun
{

int AllocateTun(const char *ifPrefix, char **allocatedName, const char *nsName, bool useNamespace);
void ConfigureTun(const char *tunName, const char *ipv4Addr, const char *netmask, const char *ipv6Addr,
				  int ipv6PrefixLength, int mtu, const char *nsName, bool useNamespace, bool configureRoute);
void CleanupTun(const char *tunName, const char *nsName, bool useNamespace);

} // namespace nr::ue::tun
