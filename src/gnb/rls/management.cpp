//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <set>

#include <gnb/rrc/task.hpp>
#include <utils/common.hpp>

static const int64_t LAST_SEEN_THRESHOLD = 3000;

namespace nr::gnb
{

int GnbRlsTask::updateUeInfo(const InetAddress &addr, uint64_t sti)
{
    if (m_stiToUeId.count(sti))
    {
        int ueId = m_stiToUeId[sti];
        auto &ctx = m_ueCtx[ueId];
        ctx->addr = addr;
        ctx->lastSeen = utils::CurrentTimeMillis();
        return ueId;
    }
    else
    {
        int ueId = ++m_ueIdCounter;
        m_stiToUeId[sti] = ueId;
        auto ctx = std::make_unique<RlsUeContext>(ueId);
        ctx->sti = sti;
        ctx->addr = addr;
        ctx->lastSeen = utils::CurrentTimeMillis();
        m_ueCtx[ueId] = std::move(ctx);

        m_logger->debug("New UE signal detected, total [%d] UEs in coverage", static_cast<int>(m_stiToUeId.size()));
        return ueId;
    }
}

void GnbRlsTask::onPeriodicLostControl()
{
    int64_t current = utils::CurrentTimeMillis();

    std::set<int> lostUeId{};
    std::set<uint64_t> lostSti{};

    for (auto &item : m_ueCtx)
    {
        if (current - item.second->lastSeen > LAST_SEEN_THRESHOLD)
        {
            lostUeId.insert(item.second->ueId);
            lostSti.insert(item.second->sti);
        }
    }

    for (uint64_t sti : lostSti)
        m_stiToUeId.erase(sti);
    for (int ueId : lostUeId)
    {
        m_ueCtx.erase(ueId);
        m_logger->debug("Signal lost detected for UE[%d]", ueId);

        auto *w = new NwGnbRlsToRrc(NwGnbRlsToRrc::SIGNAL_LOST);
        w->ueId = ueId;
        m_base->rrcTask->push(w);
    }
}

} // namespace nr::gnb
