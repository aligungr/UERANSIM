//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>

struct cons
{
    static constexpr const uint8_t Major = 3;
    static constexpr const uint8_t Minor = 0;
    static constexpr const uint8_t Patch = 0;

    static constexpr const uint16_t NdpPort = 4997;
    static constexpr const char *NdpLoopback = "127.0.0.1";


};
