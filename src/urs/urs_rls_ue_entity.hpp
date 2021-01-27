//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "urs_rls.hpp"

#include <network.hpp>
#include <optional>

namespace rls
{

class RlsUeEntity
{
  private:
    std::string nodeName;
    std::vector<InetAddress> gnbSearchList;

    EUeState state;
    size_t nextSearch;
    uint64_t ueToken;
    uint64_t gnbToken;
    uint64_t lastGnbHeartbeat;
    ECause lastError;
    InetAddress selected;

  public:
    explicit RlsUeEntity(std::string nodeName, std::vector<InetAddress> gnbSearchList);
    virtual ~RlsUeEntity() = default;

  protected:
    virtual void logWarn(const std::string &msg) = 0;
    virtual void logError(const std::string &msg) = 0;
    virtual void startWaitingTimer(int period) = 0;
    virtual void searchFailure(ECause cause) = 0;
    virtual void onRelease(ECause cause) = 0;
    virtual void onConnect(const std::string &gnbName) = 0;
    virtual void sendRlsPdu(const InetAddress &address, OctetString &&pdu) = 0;
    virtual void deliverPayload(EPayloadType type, OctetString &&payload) = 0;

  public:
    void onHeartbeat();
    void onWaitingTimerExpire();
    void onReceive(const InetAddress &address, const OctetString &pdu);
    void onUplinkDelivery(EPayloadType type, OctetString &&payload);
    void startGnbSearch();
    void releaseConnection(ECause cause);
    void resetEntity();

  private:
    void sendSetupRequest();
    void sendSetupComplete();
    void sendReleaseIndication(ECause cause);
    void sendRlsMessage(const InetAddress &address, const RlsMessage &msg);
};

} // namespace rls