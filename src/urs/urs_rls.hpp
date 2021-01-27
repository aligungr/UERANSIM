//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <memory>
#include <octet_buffer.hpp>
#include <octet_string.hpp>
#include <utility>

namespace rls
{

enum class EUeState
{
    IDLE,
    SEARCH,
    CONNECTED,
    RELEASED,
};

enum class EMessageClass : uint8_t
{
    RESERVED = 0,
    ERROR_INDICATION,
    NORMAL_MESSAGE
};

enum class EMessageType : uint8_t
{
    RESERVED = 0,
    RLS_SETUP_REQUEST,
    RLS_SETUP_RESPONSE,
    RLS_SETUP_FAILURE,
    RLS_SETUP_COMPLETE,
    RLS_HEARTBEAT,
    RLS_RELEASE_INDICATION,
    RLS_PAYLOAD_TRANSPORT
};

enum class ECause : uint8_t
{
    UNSPECIFIED = 0,
    TOKEN_CONFLICT,
    GNB_IS_NOT_READY_FOR_N1,
    EMPTY_SEARCH_LIST,
    SETUP_TIMEOUT,
    HEARTBEAT_TIMEOUT
};

enum class EPayloadType : uint8_t
{
    RRC,
    DATA,
};

struct Constants
{
    static constexpr const int UE_WAIT_TIMEOUT = 500;
    static constexpr const int HB_TIMEOUT_GNB_TO_UE = 3000;
    static constexpr const int HB_TIMEOUT_UE_TO_GNB = 5000;
    static constexpr const int HB_PERIOD_GNB_TO_UE = 1500;
    static constexpr const int HB_PERIOD_UE_TO_GNB = 2000;
};

struct RlsMessage
{
    EMessageClass msgCls{};

    ECause cause{};    // only for RLS_SETUP_FAILURE and error indication messages
    std::string str{}; // only for error indication, RLS_SETUP_RESPONSE, RLS_SETUP_COMPLETE, RLS_SETUP_FAILURE

    octet3 appVersion{};    // only for normal messages
    EMessageType msgType{}; // only for normal messages
    uint64_t ueToken{};     // only for normal messages
    uint64_t gnbToken{};    // only for normal messages except RLS_SETUP_REQUEST

    EPayloadType payloadType{}; // only for RLC_PAYLOAD_TRANSPORT
    OctetString payload{};      // only for RLC_PAYLOAD_TRANSPORT
};

enum class DecodeRes
{
    OK,
    VERSION_MISMATCH,
    FAILURE,
};

DecodeRes Decode(const OctetBuffer &stream, RlsMessage &output, octet3 appVersion);
bool Encode(const RlsMessage &msg, OctetString &stream);

const char *CauseToString(ECause cause);

} // namespace rls
