//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "rls.hpp"

namespace rls
{

DecodeRes Decode(const OctetView &stream, RlsMessage &output, octet3 appVersion)
{
    output.msgCls = static_cast<EMessageClass>(stream.readI());
    if (output.msgCls == EMessageClass::ERROR_INDICATION)
    {
        output.cause = static_cast<ECause>(stream.readI());
        uint16_t errLen = stream.read2US();
        output.str = stream.readUtf8String(errLen);
        return DecodeRes::OK;
    }
    if (output.msgCls == EMessageClass::NORMAL_MESSAGE)
    {
        output.appVersion = stream.read3();
        if ((int)output.appVersion != (int)appVersion)
            return DecodeRes::VERSION_MISMATCH;
        output.msgType = static_cast<EMessageType>(stream.readI());
        if (output.msgType != EMessageType::RLS_SETUP_REQUEST && output.msgType != EMessageType::RLS_SETUP_COMPLETE &&
            output.msgType != EMessageType::RLS_SETUP_FAILURE && output.msgType != EMessageType::RLS_HEARTBEAT &&
            output.msgType != EMessageType::RLS_RELEASE_INDICATION &&
            output.msgType != EMessageType::RLS_PAYLOAD_TRANSPORT && output.msgType != EMessageType::RLS_SETUP_RESPONSE)
            return DecodeRes::FAILURE;
        output.ueToken = stream.read8UL();
        output.gnbToken = stream.read8UL();
        output.payloadType = static_cast<EPayloadType>(stream.readI());
        uint16_t len = stream.read2US();
        output.payload = stream.readOctetString(len);
        output.cause = static_cast<ECause>(stream.readI());
        len = stream.read2US();
        output.str = stream.readUtf8String(len);
        return DecodeRes::OK;
    }

    return DecodeRes::FAILURE;
}

bool Encode(const RlsMessage &msg, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(msg.msgCls));
    if (msg.msgCls == EMessageClass::ERROR_INDICATION)
    {
        stream.appendOctet(static_cast<int>(msg.cause));
        stream.appendOctet2(msg.str.length());
        for (char c : msg.str)
            stream.appendOctet(c);
        return true;
    }
    if (msg.msgCls == EMessageClass::NORMAL_MESSAGE)
    {
        if (msg.msgType != EMessageType::RLS_SETUP_REQUEST && msg.msgType != EMessageType::RLS_SETUP_COMPLETE &&
            msg.msgType != EMessageType::RLS_SETUP_FAILURE && msg.msgType != EMessageType::RLS_HEARTBEAT &&
            msg.msgType != EMessageType::RLS_RELEASE_INDICATION && msg.msgType != EMessageType::RLS_PAYLOAD_TRANSPORT &&
            msg.msgType != EMessageType::RLS_SETUP_RESPONSE)
            return false;

        stream.appendOctet3(msg.appVersion);
        stream.appendOctet(static_cast<int>(msg.msgType));
        stream.appendOctet8(msg.ueToken);
        stream.appendOctet8(msg.gnbToken);
        stream.appendOctet(static_cast<int>(msg.payloadType));
        stream.appendOctet2(msg.payload.length());
        stream.append(msg.payload);
        stream.appendOctet(static_cast<int>(msg.cause));
        stream.appendOctet2(msg.str.length());
        stream.appendUtf8(msg.str);

        return true;
    }
    return false;
}

const char *CauseToString(ECause cause)
{
    switch (cause)
    {
    case ECause::UNSPECIFIED:
        return "RLS-UNSPECIFIED";
    case ECause::TOKEN_CONFLICT:
        return "RLS-TOKEN-CONFLICT";
    case ECause::EMPTY_SEARCH_LIST:
        return "RLS-EMPTY-SEARCH-LIST";
    case ECause::SETUP_TIMEOUT:
        return "RLS-SETUP-TIMEOUT";
    case ECause::HEARTBEAT_TIMEOUT:
        return "RLS-HEARTBEAT-TIMEOUT";
    case ECause::RRC_NORMAL_RELEASE:
        return "RLS-RRC-NORMAL-RELEASE";
    case ECause::RRC_LOCAL_RELEASE:
        return "RLS-RRC-LOCAL-RELEASE";
    default:
        return "?";
    }
}

} // namespace rls
