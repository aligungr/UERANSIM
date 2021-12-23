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
        auto statusUpdate = std::make_unique<NmUeStatusUpdate>(NmUeStatusUpdate::SESSION_RELEASE);
        statusUpdate->psi = psi;
        m_base->appTask->push(std::move(statusUpdate));
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
        m_mm->serviceRequestRequiredForData();

    m_mm->triggerMmCycle();
}

bool NasSm::anyUplinkDataPending()
{
    // TODO: look for only resources are already established etc

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

void NasSm::establishRequiredSessions()
{
    if (m_mm->hasEmergency())
    {
        if (!anyEmergencySession())
        {
            SessionConfig config;
            config.type = nas::EPduSessionType::IPV4;
            config.apn = std::nullopt;
            config.sNssai = std::nullopt;
            config.isEmergency = true;
            sendEstablishmentRequest(config);
        }

        return;
    }

    for (auto &config : m_base->config->defaultSessions)
    {
        if (!anySessionMatches(config))
            sendEstablishmentRequest(config);
    }
}

bool NasSm::anySessionMatches(const SessionConfig &config)
{
    // ACTIVE_PENDING etc. are also included
    return std::any_of(m_pduSessions.begin(), m_pduSessions.end(), [&config](auto &ps) {
        if (ps->psState == EPsState::INACTIVE)
            return false;

        if (config.isEmergency)
            return ps->isEmergency;

        if (config.isEmergency != ps->isEmergency)
            return false;
        if (config.type != ps->sessionType)
            return false;
        if (config.apn != ps->apn)
            return false;
        if (config.sNssai != ps->sNssai)
            return false;

        return true;
    });
}

} // namespace nr::ue