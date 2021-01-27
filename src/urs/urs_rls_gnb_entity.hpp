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
#include <set>
#include <unordered_map>

namespace rls
{

class RlsGnbEntity
{
  private:
    std::string nodeName;
    uint64_t token;
    std::unordered_map<uint64_t, int> ueIdMap;         // UE token to gNB-internal UE id.
    std::unordered_map<int, uint64_t> idUeMap;         // gNB-internal UE id to UE token.
    std::unordered_map<int, InetAddress> ueAddressMap; // UE token to address
    std::unordered_map<int, uint64_t> heartbeatMap;    // UE token to last heartbeat time
    std::set<int> setupCompleteWaiting;

  public:
    explicit RlsGnbEntity(std::string nodeName);
    virtual ~RlsGnbEntity() = default;

  protected:
    virtual void logWarn(const std::string &msg) = 0;
    virtual void logError(const std::string &msg) = 0;

    virtual bool isInReadyState() = 0;

    virtual void onUeConnected(int ue, std::string name) = 0;
    virtual void onUeReleased(int ue, ECause cause) = 0;

    virtual void sendRlsPdu(const InetAddress &address, OctetString &&pdu) = 0;
    virtual void deliverUplinkPayload(int ue, EPayloadType type, OctetString &&payload) = 0;

  public:
    void onHeartbeat();
    void onReceive(const InetAddress &address, const OctetString &pdu);
    void downlinkPayloadDelivery(int ue, EPayloadType type, OctetString &&payload);
    void releaseConnection(int ue, ECause cause);

  private:
    void sendReleaseIndication(int ue, ECause cause);
    void sendHeartbeat(int ue);
    void removeUe(int ue, ECause cause);
    void sendRlsMessage(int ue, const RlsMessage &msg);
    void sendSetupFailure(const InetAddress &address, uint64_t ueToken, ECause cause);
    void sendSetupResponse(int ue);
};

} // namespace rls