//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "utils.hpp"

#include <utils/common.hpp>

namespace nr::gnb
{

PduSessionTree::PduSessionTree() : mapByDownTeid{}, mapByUeId{}
{
}

void PduSessionTree::insert(uint64_t session, uint32_t downTeid)
{
    mapByDownTeid[downTeid] = session;
}

uint64_t PduSessionTree::findByDownTeid(uint32_t teid)
{
    if (mapByDownTeid.count(teid))
        return mapByDownTeid[teid];
    return {};
}

uint64_t PduSessionTree::findBySessionId(int ue, int psi)
{
    if (!mapByUeId.count(ue))
        return {};

    auto &map = mapByUeId[ue];
    if (!map.count(psi))
        return {};

    return map[psi];
}

void PduSessionTree::remove(uint64_t session, uint32_t downTeid)
{
    int ueId = GetUeId(session);
    int psi = GetPsi(session);

    mapByDownTeid.erase(downTeid);

    if (mapByUeId.count(ueId))
    {
        auto &map = mapByUeId[ueId];
        if (map.count(psi))
            map.erase(psi);
        if (map.empty())
            mapByUeId.erase(ueId);
    }
}

void PduSessionTree::enumerateByUe(int ue, std::vector<uint64_t> &output)
{
    if (mapByUeId.count(ue) == 0)
        return;
    auto &map = mapByUeId[ue];

    for (auto &item : map)
        output.push_back(item.second);
}

TokenBucket::TokenBucket(int64_t byteCapacity) : byteCapacity(byteCapacity)
{
    if (byteCapacity > 0)
    {
        this->refillTokensPerOneMillis = (double)byteCapacity / (double)REFILL_PERIOD;
        this->availableTokens = static_cast<double>(byteCapacity);
        this->lastRefillTimestamp = utils::CurrentTimeMillis();
    }
}

bool TokenBucket::tryConsume(uint64_t numberOfTokens)
{
    if (byteCapacity > 0)
    {
        refill();
        if (availableTokens < static_cast<double>(numberOfTokens))
            return false;
        else
        {
            availableTokens -= static_cast<double>(numberOfTokens);
            return true;
        }
    }
    else
        return true;
}

void TokenBucket::updateCapacity(uint64_t newByteCapacity)
{
    byteCapacity = newByteCapacity;
    if (newByteCapacity > 0)
        refillTokensPerOneMillis = (double)byteCapacity / (double)REFILL_PERIOD;
}

void TokenBucket::refill()
{
    int64_t currentTimeMillis = utils::CurrentTimeMillis();
    if (currentTimeMillis > lastRefillTimestamp)
    {
        int64_t millisSinceLastRefill = currentTimeMillis - lastRefillTimestamp;
        double refill = static_cast<double>(millisSinceLastRefill) * refillTokensPerOneMillis;
        availableTokens = std::min(static_cast<double>(byteCapacity), availableTokens + refill);
        lastRefillTimestamp = currentTimeMillis;
    }
}

bool RateLimiter::allowDownlinkPacket(uint64_t pduSession, uint64_t packetSize)
{
    int ueId = GetUeId(pduSession);

    if (downlinkByUe.count(ueId))
    {
        auto &bucket = downlinkByUe[ueId];
        if (!bucket->tryConsume(packetSize))
            return false;
    }

    if (downlinkBySession.count(pduSession))
    {
        auto &bucket = downlinkBySession[pduSession];
        if (!bucket->tryConsume(packetSize))
            return false;
    }

    return true;
}

bool RateLimiter::allowUplinkPacket(uint64_t pduSession, uint64_t packetSize)
{
    int ueId = GetUeId(pduSession);

    if (uplinkByUe.count(ueId))
    {
        auto &bucket = uplinkByUe[ueId];
        if (!bucket->tryConsume(packetSize))
            return false;
    }

    if (uplinkBySession.count(pduSession))
    {
        auto &bucket = uplinkBySession[pduSession];
        if (!bucket->tryConsume(packetSize))
            return false;
    }

    return true;
}

void RateLimiter::updateUeUplinkLimit(int ueId, uint64_t limit)
{
    if (limit <= 0)
    {
        uplinkByUe.erase(ueId);
        return;
    }

    if (uplinkByUe.count(ueId))
    {
        auto &bucket = uplinkByUe[ueId];
        bucket->updateCapacity(limit);
    }
    else
    {
        uplinkByUe[ueId] = std::make_unique<TokenBucket>(limit);
    }
}

void RateLimiter::updateUeDownlinkLimit(int ueId, uint64_t limit)
{
    if (limit <= 0)
    {
        downlinkByUe.erase(ueId);
        return;
    }

    if (downlinkByUe.count(ueId))
    {
        auto &bucket = downlinkByUe[ueId];
        bucket->updateCapacity(limit);
    }
    else
    {
        downlinkByUe[ueId] = std::make_unique<TokenBucket>(limit);
    }
}

void RateLimiter::updateSessionUplinkLimit(uint64_t pduSession, uint64_t limit)
{
    if (limit <= 0)
    {
        uplinkBySession.erase(pduSession);
        return;
    }

    if (uplinkBySession.count(pduSession))
    {
        auto &bucket = uplinkBySession[pduSession];
        bucket->updateCapacity(limit);
    }
    else
    {
        uplinkBySession[pduSession] = std::make_unique<TokenBucket>(limit);
    }
}

void RateLimiter::updateSessionDownlinkLimit(uint64_t pduSession, uint64_t limit)
{
    if (limit <= 0)
    {
        downlinkBySession.erase(pduSession);
        return;
    }

    if (downlinkBySession.count(pduSession))
    {
        auto &bucket = downlinkBySession[pduSession];
        bucket->updateCapacity(limit);
    }
    else
    {
        downlinkBySession[pduSession] = std::make_unique<TokenBucket>(limit);
    }
}

} // namespace nr::gnb
