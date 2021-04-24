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

namespace rls
{

enum class EMessageType : uint8_t
{
    RESERVED = 0,
    CELL_INFO_REQUEST,
    CELL_INFO_RESPONSE,
    PDU_DELIVERY
};

enum class EPduType : uint8_t
{
    RESERVED = 0,
    RRC,
    DATA
};

struct RlsMessage
{
    const EMessageType msgType;
    const uint64_t sti{};

    explicit RlsMessage(EMessageType msgType, uint64_t sti) : msgType(msgType), sti(sti)
    {
    }
};

struct RlsCellInfoRequest : RlsMessage
{
    Vector3 simPos{};

    explicit RlsCellInfoRequest(uint64_t sti) : RlsMessage(EMessageType::CELL_INFO_REQUEST, sti)
    {
    }
};

struct RlsCellInfoResponse : RlsMessage
{
    GlobalNci cellId{};
    int tac{};
    int dbm{};
    std::string gnbName{};
    std::string linkIp{};

    explicit RlsCellInfoResponse(uint64_t sti) : RlsMessage(EMessageType::CELL_INFO_RESPONSE, sti)
    {
    }
};

struct RlsPduDelivery : RlsMessage
{
    EPduType pduType{};
    OctetString pdu{};
    OctetString payload{};

    explicit RlsPduDelivery(uint64_t sti) : RlsMessage(EMessageType::PDU_DELIVERY, sti)
    {
    }
};

void EncodeRlsMessage(const RlsMessage &msg, OctetString &stream);
std::unique_ptr<RlsMessage> DecodeRlsMessage(const OctetView &stream);

} // namespace rls