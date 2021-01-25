//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "urs_rls.hpp"

namespace rls
{

DecodeRes Decode(const OctetBuffer &stream, RlsMessage &output, octet3 appVersion)
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
        if (output.msgType != EMessageType::RLS_SETUP_REQUEST)
            output.gnbToken = stream.read8UL();
        if (output.msgType == EMessageType::RLS_PAYLOAD_TRANSPORT)
        {
            output.payloadType = static_cast<EPayloadType>(stream.readI());
            uint16_t len = stream.read2US();
            output.payload = stream.readOctetString(len);
        }
        if (output.msgType == EMessageType::RLS_SETUP_FAILURE)
            output.cause = static_cast<ECause>(stream.readI());
        if (output.msgType == EMessageType::RLS_SETUP_RESPONSE || output.msgType == EMessageType::RLS_SETUP_COMPLETE)
        {
            uint16_t len = stream.read2US();
            output.str = stream.readUtf8String(len);
        }
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
        stream.appendOctet3(msg.appVersion);
		stream.appendOctet(static_cast<int>(msg.msgType));
		stream.appendOctet8(msg.ueToken);
        if (msg.msgType != EMessageType::RLS_SETUP_REQUEST && msg.msgType != EMessageType::RLS_SETUP_COMPLETE &&
            msg.msgType != EMessageType::RLS_SETUP_FAILURE && msg.msgType != EMessageType::RLS_HEARTBEAT &&
            msg.msgType != EMessageType::RLS_RELEASE_INDICATION && msg.msgType != EMessageType::RLS_PAYLOAD_TRANSPORT &&
            msg.msgType != EMessageType::RLS_SETUP_RESPONSE)
            return false;
        if (msg.msgType != EMessageType::RLS_SETUP_REQUEST)
            stream.appendOctet8(msg.gnbToken);
        if (msg.msgType == EMessageType::RLS_SETUP_FAILURE)
            stream.appendOctet(static_cast<int>(msg.cause));
        if (msg.msgType == EMessageType::RLS_PAYLOAD_TRANSPORT)
        {
            stream.appendOctet(static_cast<int>(msg.payloadType));
            stream.appendOctet2(msg.payload.length());
            stream.append(msg.payload);
        }
        if (msg.msgType == EMessageType::RLS_SETUP_RESPONSE || msg.msgType == EMessageType::RLS_SETUP_COMPLETE)
        {
            stream.appendOctet2(msg.str.length());
            for (char c : msg.str)
                stream.appendOctet(c);
        }
        return true;
    }
    return false;
}

const char *CauseToString(ECause cause)
{
    switch (cause)
    {
    case ECause::UNSPECIFIED:
        return "UNSPECIFIED";
    case ECause::TOKEN_CONFLICT:
        return "TOKEN_CONFLICT";
    case ECause::GNB_IS_NOT_READY_FOR_N1:
        return "GNB_IS_NOT_READY_FOR_N1";
    case ECause::EMPTY_SEARCH_LIST:
        return "EMPTY_SEARCH_LIST";
    case ECause::SETUP_TIMEOUT:
        return "SETUP_TIMEOUT";
    case ECause::HEARTBEAT_TIMEOUT:
        return "HEARTBEAT_TIMEOUT";
    default:
        return "?";
    }
}

} // namespace rls
