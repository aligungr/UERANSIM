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

namespace nr::ue::tap
{

int AllocateTap(const char *ifPrefix, char **allocatedName);
void ConfigureTap(const char *tapName, int mtu);

} // namespace nr::ue::tap
