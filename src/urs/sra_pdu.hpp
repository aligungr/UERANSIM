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
    RRC
};

struct SraMessage
{
    const EMessageType msgType;
    const uint64_t sti{};

    explicit SraMessage(EMessageType msgType, uint64_t sti) : msgType(msgType), sti(sti)
    {
    }
};

struct SraCellInfoRequest : SraMessage
{
    Vector3 simPos{};

    explicit SraCellInfoRequest(uint64_t sti) : SraMessage(EMessageType::CELL_INFO_REQUEST, sti)
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

    explicit SraCellInfoResponse(uint64_t sti) : SraMessage(EMessageType::CELL_INFO_RESPONSE, sti)
    {
    }
};

struct SraPduDelivery : SraMessage
{
    EPduType pduType{};
    OctetString pdu{};
    OctetString payload{};

    explicit SraPduDelivery(uint64_t sti) : SraMessage(EMessageType::PDU_DELIVERY, sti)
    {
    }
};

void EncodeSraMessage(const SraMessage &msg, OctetString &stream);
std::unique_ptr<SraMessage> DecodeSraMessage(const OctetView &stream);

} // namespace sra