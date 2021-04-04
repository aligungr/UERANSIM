//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <utils/common.hpp>

namespace nr::gnb
{

void GnbSraTask::updateUeInfo(const InetAddress &addr, uint64_t sti)
{
    if (m_stiToUeId.count(sti))
    {
        int ueId = m_stiToUeId[sti];
        auto &ctx = m_ueCtx[ueId];
        ctx->addr = addr;
        ctx->lastSeen = utils::CurrentTimeMillis();
    }
    else
    {
        int ueId = utils::NextId();
        m_stiToUeId[sti] = ueId;
        auto ctx = std::make_unique<SraUeContext>(ueId);
        ctx->sti = sti;
        ctx->addr = addr;
        ctx->lastSeen = utils::CurrentTimeMillis();
        m_ueCtx[ueId] = std::move(ctx);

        m_logger->debug("New UE signal detected, UE[%d]", ueId);
    }
}

void GnbSraTask::onPeriodicLostControl()
{

}

} // namespace nr::gnb
