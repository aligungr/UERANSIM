//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "gnb_types.hpp"

#include <memory>
#include <unordered_map>

namespace nr::gnb
{

inline uint64_t MakeSessionResInd(int ueId, int psi)
{
    return (static_cast<int64_t>(ueId) << 32LL) | static_cast<int64_t>(psi);
}

inline int GetUeId(int64_t sessionResInd)
{
    return static_cast<int>((sessionResInd >> 32LL) & 0xFFFFFFFFLL);
}

inline int GetPsi(int64_t sessionResInd)
{
    return static_cast<int>(sessionResInd & 0xFFFFFFFFLL);
}

class IRateLimiter
{
  public:
    virtual bool allowDownlinkPacket(uint64_t pduSession, int64_t packetSize) = 0;
    virtual bool allowUplinkPacket(uint64_t pduSession, int64_t packetSize) = 0;
    virtual void updateUeUplinkLimit(int ueId, int64_t limit) = 0;
    virtual void updateUeDownlinkLimit(int ueId, int64_t limit) = 0;
    virtual void updateSessionUplinkLimit(uint64_t pduSession, int64_t limit) = 0;
    virtual void updateSessionDownlinkLimit(uint64_t pduSession, int64_t limit) = 0;
};

class PduSessionTree
{
    std::unordered_map<uint32_t, uint64_t> mapByDownTeid;
    std::unordered_map<int, std::unordered_map<int, uint64_t>> mapByUeId;

  public:
    PduSessionTree();
    void insert(uint64_t session, uint32_t downTeid);
    uint64_t findByDownTeid(uint32_t teid);
    uint64_t findBySessionId(int ue, int psi);
    void remove(uint64_t session, uint32_t downTeid);
};

class TokenBucket
{
    static constexpr const int64_t REFILL_PERIOD = 1000L;

    int64_t byteCapacity;
    double refillTokensPerOneMillis;
    double availableTokens;
    int64_t lastRefillTimestamp;

  public:
    explicit TokenBucket(long byteCapacity);
    bool tryConsume(int64_t numberTokens);
    void updateCapacity(int64_t newByteCapacity);

  private:
    void refill();
};

class RateLimiter : public IRateLimiter
{
    std::unordered_map<int, std::unique_ptr<TokenBucket>> downlinkByUe;
    std::unordered_map<int, std::unique_ptr<TokenBucket>> uplinkByUe;
    std::unordered_map<uint64_t, std::unique_ptr<TokenBucket>> downlinkBySession;
    std::unordered_map<uint64_t, std::unique_ptr<TokenBucket>> uplinkBySession;

  public:
    bool allowDownlinkPacket(uint64_t pduSession, int64_t packetSize) override;
    bool allowUplinkPacket(uint64_t pduSession, int64_t packetSize) override;
    void updateUeUplinkLimit(int ueId, int64_t limit) override;
    void updateUeDownlinkLimit(int ueId, int64_t limit) override;
    void updateSessionUplinkLimit(uint64_t pduSession, int64_t limit) override;
    void updateSessionDownlinkLimit(uint64_t pduSession, int64_t limit) override;
};

} // namespace nr::gtp
