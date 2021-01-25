//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "urs_rls_ue_entity.hpp"

#include <common.hpp>
#include <constants.hpp>
#include <utility>

static const octet3 AppVersion = octet3{cons::Major, cons::Minor, cons::Patch};

namespace rls
{

RlsUeEntity::RlsUeEntity(std::string nodeName, std::vector<InetAddress> gnbSearchList)
    : nodeName(std::move(nodeName)), gnbSearchList(std::move(gnbSearchList)), state(EUeState::IDLE), nextSearch(0),
      ueToken(0), gnbToken(0), lastGnbHeartbeat(0), lastError(ECause::UNSPECIFIED)
{
}

void RlsUeEntity::onHeartbeat()
{
    if (state != EUeState::CONNECTED)
        return;

    if (utils::CurrentTimeMillis() - lastGnbHeartbeat > Constants::HB_TIMEOUT_GNB_TO_UE)
    {
        state = EUeState::RELEASED;
        nextSearch = 0;
        ueToken = 0;
        gnbToken = 0;
        lastGnbHeartbeat = 0;
        lastError = ECause::UNSPECIFIED;
        onRelease(ECause::HEARTBEAT_TIMEOUT);
        return;
    }

    RlsMessage msg;
    msg.gnbToken = gnbToken;
    msg.ueToken = ueToken;
    msg.msgType = EMessageType::RLS_HEARTBEAT;
    msg.msgCls = EMessageClass::NORMAL_MESSAGE;
    msg.appVersion = AppVersion;
    sendRlsMessage(selected, msg);
}

void RlsUeEntity::onWaitingTimerExpire()
{
    if (state != EUeState::SEARCH)
        return;

    if (nextSearch + 1 >= gnbSearchList.size())
    {
        resetEntity();
        searchFailure(ECause::SETUP_TIMEOUT);
    }
    else
    {
        nextSearch++;
        ueToken = utils::Random64();
        startWaitingTimer(Constants::UE_WAIT_TIMEOUT);
        sendSetupRequest();
    }
}

void RlsUeEntity::onUplinkDelivery(EPayloadType type, OctetString &&payload)
{
    if (state != EUeState::CONNECTED)
    {
        logWarn("RLS uplink delivery request without connection");
        return;
    }

    RlsMessage msg;
    msg.gnbToken = gnbToken;
    msg.ueToken = ueToken;
    msg.msgType = EMessageType::RLS_PAYLOAD_TRANSPORT;
    msg.msgCls = EMessageClass::NORMAL_MESSAGE;
    msg.appVersion = AppVersion;
    msg.payloadType = type;
    msg.payload = std::move(payload);
    sendRlsMessage(selected, msg);
}

void RlsUeEntity::startGnbSearch()
{
    if (state != EUeState::IDLE)
    {
        logWarn("gNB search request while in not IDLE state");
        return;
    }

    if (gnbSearchList.empty())
    {
        searchFailure(ECause::EMPTY_SEARCH_LIST);
        return;
    }

    nextSearch = 0;
    ueToken = utils::Random64();
    startWaitingTimer(Constants::UE_WAIT_TIMEOUT);
    state = EUeState::SEARCH;
    sendSetupRequest();
}

void RlsUeEntity::onReceive(const InetAddress &address, const OctetString &pdu)
{
    RlsMessage msg{};

    auto res = Decode(OctetBuffer{pdu}, msg, AppVersion);
    if (res == DecodeRes::FAILURE)
    {
        logError("PDU decoding failed");
        return;
    }
    if (res == DecodeRes::VERSION_MISMATCH)
    {
        logError("Version mismatch between UE and gNB");
        return;
    }

    if (msg.msgCls == EMessageClass::ERROR_INDICATION)
    {
        logError("RLS error indication received | " + std::to_string((int)msg.cause) + " | " + msg.str);
        return;
    }

    if (msg.ueToken != ueToken)
    {
        logWarn("UE token mismatched message received");
        return;
    }

    if (msg.gnbToken == 0)
    {
        logWarn("Bad gNB token received");
        return;
    }

    if (state != EUeState::CONNECTED && state != EUeState::SEARCH)
    {
        logWarn("RLS received while in not CONNECTED or SEARCH");
        return;
    }

    if (state == EUeState::CONNECTED)
    {
        if (gnbToken != msg.gnbToken)
        {
            logWarn("gNB token mismatched message received");
            return;
        }

        lastGnbHeartbeat = utils::CurrentTimeMillis();

        if (msg.msgType == EMessageType::RLS_PAYLOAD_TRANSPORT)
        {
            deliverPayload(msg.payloadType, std::move(msg.payload));
        }
        else if (msg.msgType == EMessageType::RLS_HEARTBEAT)
        {
            lastGnbHeartbeat = utils::CurrentTimeMillis();
        }
        else if (msg.msgType == EMessageType::RLS_RELEASE_INDICATION)
        {
            state = EUeState::RELEASED;
            nextSearch = 0;
            ueToken = 0;
            gnbToken = 0;
            lastGnbHeartbeat = 0;
            lastError = ECause::UNSPECIFIED;
            onRelease(msg.cause);
        }
        else
        {
            logWarn("RLS receive invalid message type in CONNECTED " + std::to_string((int)msg.msgType));
        }
    }

    if (state == EUeState::SEARCH)
    {
        if (msg.msgType == EMessageType::RLS_SETUP_RESPONSE)
        {
            state = EUeState::CONNECTED;
            gnbToken = msg.gnbToken;
            lastError = ECause::UNSPECIFIED;
            nextSearch = 0;
            lastGnbHeartbeat = utils::CurrentTimeMillis();
            selected = address;
            sendSetupComplete();
            onConnect(msg.str);
        }
        else if (msg.msgType == EMessageType::RLS_SETUP_FAILURE)
        {
            if (msg.cause != ECause::UNSPECIFIED)
                lastError = msg.cause;

            if (nextSearch + 1 >= gnbSearchList.size())
            {
                searchFailure(lastError);
                resetEntity();
            }
            else
            {
                nextSearch++;
                ueToken = utils::Random64();
                startWaitingTimer(Constants::UE_WAIT_TIMEOUT);
                sendSetupRequest();
            }
        }
        else
        {
            logWarn("RLS receive invalid message type in IDLE " + std::to_string((int)msg.msgType));
        }
    }
}

void RlsUeEntity::releaseConnection(ECause cause)
{
    sendReleaseIndication(cause);
    state = EUeState::RELEASED;
    nextSearch = 0;
    ueToken = 0;
    gnbToken = 0;
    lastGnbHeartbeat = 0;
    lastError = ECause::UNSPECIFIED;
    onRelease(cause);
}

void RlsUeEntity::resetEntity()
{
    state = EUeState::IDLE;
    nextSearch = 0;
    ueToken = 0;
    gnbToken = 0;
    lastError = ECause::UNSPECIFIED;
    lastGnbHeartbeat = 0;
    selected = {};
}

void RlsUeEntity::sendSetupRequest()
{
    RlsMessage m;
    m.msgCls = EMessageClass::NORMAL_MESSAGE;
    m.msgType = EMessageType::RLS_SETUP_REQUEST;
    m.appVersion = AppVersion;
    m.ueToken = ueToken;
    m.gnbToken = 0;
    sendRlsMessage(gnbSearchList[nextSearch], m);
}

void RlsUeEntity::sendSetupComplete()
{
    RlsMessage m;
    m.msgCls = EMessageClass::NORMAL_MESSAGE;
    m.msgType = EMessageType::RLS_SETUP_COMPLETE;
    m.appVersion = AppVersion;
    m.ueToken = ueToken;
    m.gnbToken = gnbToken;
    m.str = nodeName;
    sendRlsMessage(selected, m);
}

void RlsUeEntity::sendReleaseIndication(ECause cause)
{
    RlsMessage m;
    m.msgCls = EMessageClass::NORMAL_MESSAGE;
    m.msgType = EMessageType::RLS_RELEASE_INDICATION;
    m.appVersion = AppVersion;
    m.ueToken = ueToken;
    m.gnbToken = gnbToken;
    m.cause = cause;
    sendRlsMessage(selected, m);
}

void RlsUeEntity::sendRlsMessage(const InetAddress &address, const RlsMessage &msg)
{
    OctetString stream{};
    if (!Encode(msg, stream))
    {
        logWarn("PDU encoding failed");
        return;
    }

    sendRlsPdu(address, std::move(stream));
}

} // namespace rls