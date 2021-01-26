//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <sstream>
#include <string>
#include <utility>

namespace nr::ue::tun
{

int AllocateTun(const char *ifPrefix, char **allocatedName);
void ConfigureTun(const char *tunName, const char *ipAddr, bool configureRoute);

} // namespace nr::ue::tun
