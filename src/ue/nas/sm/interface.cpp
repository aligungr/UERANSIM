//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sm.hpp"
#include <algorithm>
#include <nas/proto_conf.hpp>
#include <nas/utils.hpp>
#include <ue/app/task.hpp>
#include <ue/nas/mm/mm.hpp>

namespace nr::ue
{

void NasSm::handleNasEvent(const NwUeNasToNas &msg)
{
    switch (msg.present)
    {
    case NwUeNasToNas::NAS_TIMER_EXPIRE:
        onTimerExpire(*msg.timer);
        break;
    default:
        break;
    }
}

void NasSm::onTimerTick()
{
    int pti = 0;
    for (auto &pt : m_procedureTransactions)
    {
        if (pt.timer && pt.timer->performTick())
            onTransactionTimerExpire(pti);
        pti++;
    }
}

bool NasSm::anyUplinkDataPending()
{
    auto status = getUplinkDataStatus();
    for (int i = 1; i < 16; i++)
        if (status[i])
            return true;
    return false;
}

bool NasSm::anyEmergencyUplinkDataPending()
{
    auto status = getUplinkDataStatus();
    for (int i = 1; i < 16; i++)
        if (status[i] && m_pduSessions[i]->isEmergency)
            return true;
    return false;
}

std::bitset<16> NasSm::getUplinkDataStatus()
{
    std::bitset<16> res{};
    for (int i = 1; i < 16; i++)
        if (m_pduSessions[i]->psState == EPsState::ACTIVE && m_pduSessions[i]->uplinkPending)
            res[i] = true;
    return res;
}

std::bitset<16> NasSm::getPduSessionStatus()
{
    std::bitset<16> res{};
    for (int i = 1; i < 16; i++)
        if (m_pduSessions[i]->psState == EPsState::ACTIVE)
            res[i] = true;
    return res;
}

} // namespace nr::ue
