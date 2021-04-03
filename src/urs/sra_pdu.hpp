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

namespace sra
{

enum class SraMessageType : uint8_t
{
    RESERVED = 0,
    CELL_INFO_REQUEST,
    CELL_INFO_RESPONSE,
};

struct SraMessage
{
    const SraMessageType msgType;

    explicit SraMessage(SraMessageType msgType) : msgType(msgType)
    {
    }
};

struct SraCellInfoRequest : SraMessage
{
    Vector3 simPos{};

    SraCellInfoRequest() : SraMessage(SraMessageType::CELL_INFO_REQUEST)
    {
    }
};

struct SraCellInfoResponse : SraMessage
{
    GlobalNci cellId{};
    int tac{};
    int dbm{};
    std::string gnbName{};
    std::string linkIp{};

    SraCellInfoResponse() : SraMessage(SraMessageType::CELL_INFO_RESPONSE)
    {
    }
};

void EncodeSraMessage(const SraMessage &msg, OctetString &stream);
std::unique_ptr<SraMessage> DecodeSraMessage(const OctetView &stream);

} // namespace sra