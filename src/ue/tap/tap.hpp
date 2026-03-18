//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <string>

namespace nr::ue::tap
{

int TapAllocate(const char *namePrefix, std::string &allocatedName, std::string &error);
bool TapConfigure(const std::string &tapName, int mtu, std::string &error);

} // namespace nr::ue::tap
