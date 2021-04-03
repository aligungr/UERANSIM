//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <cmath>

static int MIN_ALLOWED_DBM = -120;

static int EstimateSimulatedDbm(const Vector3 &myPos, const Vector3 &uePos)
{
    int deltaX = myPos.x - uePos.x;
    int deltaY = myPos.y - uePos.y;
    int deltaZ = myPos.z - uePos.z;

    int distance = static_cast<int>(std::sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ));
    if (distance == 0)
        return -1; // 0 may be confusing for people
    return -distance;
}

namespace nr::gnb
{

void GnbSasTask::handleCellInfoRequest(const InetAddress &addr, const sas::SasCellInfoRequest &msg)
{
    int dbm = EstimateSimulatedDbm(m_base->config->phyLocation, msg.simPos);
    if (dbm < MIN_ALLOWED_DBM)
    {
        // if the simulated signal strength is such low, then do not send a response to this message
        return;
    }

    sas::SasCellInfoResponse resp{};
    resp.cellId.nci = m_base->config->nci;
    resp.cellId.plmn = m_base->config->plmn;
    resp.tac = m_base->config->tac;
    resp.dbm = dbm;

    sendSasMessage(addr, resp);
}

} // namespace nr::gnb
