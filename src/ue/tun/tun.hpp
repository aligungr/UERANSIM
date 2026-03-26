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

int TunAllocate(const char *namePrefix, std::string &allocatedName, const std::string &nsName, bool useNamespace,
				std::string &error);
bool TunConfigure(const std::string &tunName, const std::string &ipAddress, const std::string &netmask, int mtu,
				  const std::string &nsName, bool useNamespace, bool configureRouting, std::string &error);
bool TunCleanup(const std::string &tunName, const std::string &nsName, bool useNamespace, std::string &error);

} // namespace nr::ue::tun
