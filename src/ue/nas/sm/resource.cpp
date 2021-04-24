//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sm.hpp"
#include <algorithm>
#include <lib/nas/proto_conf.hpp>
#include <lib/nas/utils.hpp>
#include <ue/app/task.hpp>
#include <ue/nas/mm/mm.hpp>

namespace nr::ue
{

static bool IsEstablished(EPsState state)
{
    switch (state)
    {
    case EPsState::ACTIVE:
    case EPsState::INACTIVE_PENDING:
    case EPsState::MODIFICATION_PENDING:
        return true;
    default:
        return false;
    }
}

void NasSm::localReleaseSession(int psi)
{
    m_logger->debug("Performing local release of PDU session[%d]", psi);

    bool isEstablished = IsEstablished(m_pduSessions[psi]->psState);

    freePduSessionId(psi);

    if (isEstablished)
    {
        auto *statusUpdate = new NwUeStatusUpdate(NwUeStatusUpdate::SESSION_RELEASE);
        statusUpdate->psi = psi;
        m_base->appTask->push(statusUpdate);
    }
}

void NasSm::localReleaseAllSessions()
{
    for (auto &session : m_pduSessions)
        if (IsEstablished(session->psState))
            localReleaseSession(session->psi);
}

bool NasSm::anyEmergencySession()
{
    // ACTIVE_PENDING etc. are also included
    return std::any_of(m_pduSessions.begin(), m_pduSessions.end(),
                       [](auto &ps) { return ps->psState != EPsState::INACTIVE && ps->isEmergency; });
}

void NasSm::handleUplinkStatusChange(int psi, bool isPending)
{
    m_logger->debug("Uplink data status changed PSI[%d] pending[%s]", psi, isPending ? "true" : "false");

    m_pduSessions[psi]->uplinkPending = isPending;

    if (isPending)
        m_mm->serviceNeededForUplinkData();
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