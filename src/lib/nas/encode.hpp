//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "msg.hpp"

#include <stdexcept>
#include <string>

namespace nas
{

// Why a NAS message did not decode, which decides what its receiver does with it (TS 24.501 §7).
enum class EDecodeFailure
{
    // Too short to hold its message type, or for a security protected message its security header: it is ignored
    // (§7.2.1).
    TOO_SHORT,
    // Neither a 5GMM nor a 5GSM message: it is ignored.
    UNKNOWN_PROTOCOL,
    // Of a message type that is not defined: it is answered with cause #97 (§7.4).
    UNKNOWN_MESSAGE_TYPE,
    // A mandatory IE is missing or does not decode, or an IE the message does not define is encoded as comprehension
    // required: it is answered with cause #96 (§7.5.1).
    INVALID_MANDATORY_INFORMATION,
};

// What DecodeNasMessage() throws for a message that does not decode, with the header of the message as far as it did.
struct NasDecodeError : std::runtime_error
{
    EDecodeFailure failure;
    EExtendedProtocolDiscriminator epd{};
    // Unless the message is too short or of an unknown protocol.
    EMessageType messageType{};
    // Of a 5GSM message.
    int pduSessionId{};
    int pti{};

    NasDecodeError(EDecodeFailure failure, const std::string &what);
};

void EncodeNasMessage(const NasMessage &msg, OctetString &stream);
std::unique_ptr<NasMessage> DecodeNasMessage(const OctetView &stream);

} // namespace nas
