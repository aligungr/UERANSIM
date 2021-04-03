//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>
#include <memory>
#include <utils/common_types.hpp>
#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>

namespace sas
{

enum class SasMessageType : uint8_t
{
    RESERVED = 0,
    CELL_INFO_REQUEST,
    CELL_INFO_RESPONSE,
};

struct SasMessage
{
    const SasMessageType msgType;

    explicit SasMessage(SasMessageType msgType) : msgType(msgType)
    {
    }
};

struct SasCellInfoRequest : SasMessage
{
    Vector3 simPos{};

    SasCellInfoRequest() : SasMessage(SasMessageType::CELL_INFO_REQUEST)
    {
    }
};

struct SasCellInfoResponse : SasMessage
{
    GlobalNci cellId{};
    int tac{};
    int dbm{};

    SasCellInfoResponse() : SasMessage(SasMessageType::CELL_INFO_RESPONSE)
    {
    }
};

void EncodeSasMessage(const SasMessage &msg, OctetString &stream);
std::unique_ptr<SasMessage> DecodeSasMessage(const OctetView &stream);

} // namespace sas