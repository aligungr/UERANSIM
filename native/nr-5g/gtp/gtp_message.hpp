//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>
#include <memory>
#include <optional>
#include <vector>

#include "gtp_ext_header.hpp"

namespace gtp
{

struct GtpMessage
{
    // GTP Message Types. (Only GTP-U included)
    static constexpr const uint8_t MT_ECHO_REQUEST = 1;
    static constexpr const uint8_t MT_ECHO_RESPONSE = 2;
    static constexpr const uint8_t MT_ERROR_INDICATION = 26;
    static constexpr const uint8_t MT_SUPPORTED_EXT_HEADERS_NOTIFICATION = 31;
    static constexpr const uint8_t MT_END_MARKER = 254;
    static constexpr const uint8_t MT_G_PDU = 255;

    uint8_t msgType;
    uint32_t teid;
    std::optional<uint16_t> seq;
    std::optional<uint8_t> nPduNum;
    std::vector<std::unique_ptr<GtpExtHeader>> extHeaders;
    OctetString payload;
};

} // namespace gtp