//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "common_types.hpp"

Supi Supi::Parse(const std::string &supi)
{
    if (supi[0] == 'i' && supi[1] == 'm' && supi[2] == 's' && supi[3] == 'i' && supi[4] == '-')
        return Supi{"imsi", supi.substr(5)};
    throw std::runtime_error("invalid supi value");
}
