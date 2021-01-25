//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "urs_rls_gnb_entity.hpp"

#include <common.hpp>
#include <constants.hpp>
#include <random>

static uint64_t TokenGen()
{
    while (true)
    {
        std::random_device rd;
        std::mt19937_64 eng(rd());
        std::uniform_int_distribution<uint64_t> distribution;
        uint64_t r = distribution(eng);
        if (r != 0)
            return r;
    }
}

static const octet3 AppVersion = octet3{cons::Major, cons::Minor, cons::Patch};

namespace rls
{

RlsGnbEntity::RlsGnbEntity(std::string nodeName)
    : nodeName(std::move(nodeName)), token(TokenGen()), ueIdMap(), idUeMap(), ueAddressMap(), heartbeatMap(),
      setupCompleteWaiting()
{
}

void RlsGnbEntity::onHeartbeat()
{
    uint64_t current = utils::CurrentTimeMillis();

    std::vector<int> uesToRemove{};

    for (auto &v : heartbeatMap)
    {
        if (current - v.second > Constants::HB_TIMEOUT_UE_TO_GNB)
            uesToRemove.push_back(ueIdMap[v.first]);
        else
            sendHeartbeat(ueIdMap[v.first]);
    }

    for (int ue : uesToRemove)
        removeUe(ue, ECause::HEARTBEAT_TIMEOUT);
}

void RlsGnbEntity::downlinkPayloadDelivery(int ue, EPayloadType type, OctetString &&payload)
{
    if (!idUeMap.count(ue))
    {
        logWarn("UE connection released or not established");
        return;
    }

    RlsMessage m;
    m.msgCls = EMessageClass::NORMAL_MESSAGE;
    m.msgType = EMessageType::RLS_PAYLOAD_TRANSPORT;
    m.appVersion = AppVersion;
    m.ueToken = ueIdMap[ue];
    m.gnbToken = token;
    m.payloadType = type;
    m.payload = std::move(payload);
    sendRlsMessage(ue, m);
}

void RlsGnbEntity::releaseConnection(int ue, ECause cause)
{
    sendReleaseIndication(ue, cause);
    removeUe(ue, cause);
}

void RlsGnbEntity::sendRlsMessage(int ue, const RlsMessage &msg)
{
    OctetString buf{};
    if (!Encode(msg, buf))
    {
        logWarn("PDU encoding failed");
        return;
    }

    sendRlsPdu(ueAddressMap[ue], buf);
}

void RlsGnbEntity::sendHeartbeat(int ue)
{
    RlsMessage m;
    m.msgCls = EMessageClass::NORMAL_MESSAGE;
    m.msgType = EMessageType::RLS_HEARTBEAT;
    m.appVersion = AppVersion;
    m.ueToken = ueIdMap[ue];
    m.gnbToken = token;
    sendRlsMessage(ue, m);
}

void RlsGnbEntity::sendReleaseIndication(int ue, ECause cause)
{
    RlsMessage m;
    m.msgCls = EMessageClass::NORMAL_MESSAGE;
    m.msgType = EMessageType::RLS_RELEASE_INDICATION;
    m.appVersion = AppVersion;
    m.ueToken = ueIdMap[ue];
    m.gnbToken = token;
    m.cause = cause;
    sendRlsMessage(ue, m);
}

void RlsGnbEntity::removeUe(int ue, ECause cause)
{
    uint64_t ueToken = idUeMap[ue];
    ueIdMap.erase(ueToken);
    idUeMap.erase(ue);
    ueAddressMap.erase(ue);
    heartbeatMap.erase(ue);
    if (!setupCompleteWaiting.count(ue))
        onUeReleased(ue, cause);
    setupCompleteWaiting.erase(ue);
}

void RlsGnbEntity::onReceive(const InetAddress &address, const OctetString &pdu)
{
    RlsMessage msg{};

    auto res = Decode(OctetBuffer{pdu}, msg, AppVersion);
    if (res == DecodeRes::FAILURE)
    {
        logWarn("PDU decoding failed");
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

    if (msg.ueToken == 0)
    {
        logWarn("UE gNB token received");
        return;
    }

    if (msg.msgType == EMessageType::RLS_SETUP_REQUEST)
    {
        if (ueIdMap.count(msg.ueToken))
        {
            sendSetupFailure(address, msg.ueToken, ECause::TOKEN_CONFLICT);
            return;
        }

        int ueId = utils::NextId();
        ueIdMap[msg.ueToken] = ueId;
        ueAddressMap[ueId] = address;
        heartbeatMap[ueId] = utils::CurrentTimeMillis();
        setupCompleteWaiting.insert(ueId);

        sendSetupResponse(ueId);
        return;
    }

    if (!ueIdMap.count(msg.ueToken))
    {
        logWarn("Unknown UE token");
        return;
    }

    int ue = ueIdMap[msg.ueToken];
    heartbeatMap[ue] = utils::CurrentTimeMillis();

    if (msg.msgType == EMessageType::RLS_SETUP_COMPLETE)
    {
        setupCompleteWaiting.erase(ue);
        onUeConnected(ue, msg.str);
    }
    else if (msg.msgType == EMessageType::RLS_HEARTBEAT)
    {
        heartbeatMap[ue] = utils::CurrentTimeMillis();
    }
    else if (msg.msgType == EMessageType::RLS_RELEASE_INDICATION)
    {
        removeUe(ue, msg.cause);
    }
    else if (msg.msgType == EMessageType::RLS_PAYLOAD_TRANSPORT)
    {
        deliverUplinkPayload(msg.payloadType, std::move(msg.payload));
    }
    else
    {
        logWarn("Bad message type received from UE");
    }
}

void RlsGnbEntity::sendSetupResponse(int ue)
{
    RlsMessage m;
    m.msgCls = EMessageClass::NORMAL_MESSAGE;
    m.msgType = EMessageType::RLS_RELEASE_INDICATION;
    m.appVersion = AppVersion;
    m.ueToken = ueIdMap[ue];
    m.gnbToken = token;
    m.str = nodeName;
    sendRlsMessage(ue, m);
}

void RlsGnbEntity::sendSetupFailure(const InetAddress &address, uint64_t ueToken, ECause cause)
{
    RlsMessage m;
    m.msgCls = EMessageClass::NORMAL_MESSAGE;
    m.msgType = EMessageType::RLS_SETUP_FAILURE;
    m.appVersion = AppVersion;
    m.ueToken = ueToken;
    m.gnbToken = token;
    m.cause = cause;

    OctetString buf{};
    if (!Encode(m, buf))
    {
        logWarn("PDU encoding failed");
        return;
    }

    sendRlsPdu(address, buf);
}

} // namespace rls