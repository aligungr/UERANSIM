//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <cmath>

#include <gnb/gtp/task.hpp>
#include <gnb/rrc/task.hpp>

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

void GnbRlsTask::handleCellInfoRequest(int ueId, const rls::RlsCellInfoRequest &msg)
{
    int dbm = EstimateSimulatedDbm(m_base->config->phyLocation, msg.simPos);
    if (dbm < MIN_ALLOWED_DBM)
    {
        // if the simulated signal strength is such low, then do not send a response to this message
        return;
    }

    rls::RlsCellInfoResponse resp{m_sti};
    resp.cellId.nci = m_base->config->nci;
    resp.cellId.plmn = m_base->config->plmn;
    resp.tac = m_base->config->tac;
    resp.dbm = dbm;
    resp.gnbName = m_base->config->name;
    resp.linkIp = m_base->config->portalIp;

    sendRlsMessage(ueId, resp);
}

void GnbRlsTask::handleUplinkPduDelivery(int ueId, rls::RlsPduDelivery &msg)
{
    if (msg.pduType == rls::EPduType::RRC)
    {
        auto *nw = new NwGnbRlsToRrc(NwGnbRlsToRrc::RRC_PDU_DELIVERY);
        nw->ueId = ueId;
        nw->channel = static_cast<rrc::RrcChannel>(msg.payload.get4I(0));
        nw->pdu = std::move(msg.pdu);
        m_base->rrcTask->push(nw);
    }
    else if (msg.pduType == rls::EPduType::DATA)
    {
        auto *nw = new NwGnbRlsToGtp(NwGnbRlsToGtp::DATA_PDU_DELIVERY);
        nw->ueId = ueId;
        nw->psi = msg.payload.get4I(0);
        nw->pdu = std::move(msg.pdu);
        m_base->gtpTask->push(nw);
    }
}

void GnbRlsTask::handleDownlinkDelivery(int ueId, rls::EPduType pduType, OctetString &&pdu, OctetString &&payload)
{
    rls::RlsPduDelivery resp{m_sti};
    resp.pduType = pduType;
    resp.pdu = std::move(pdu);
    resp.payload = std::move(payload);

    if (ueId != 0)
    {
        sendRlsMessage(ueId, resp);
    }
    else
    {
        for (auto &ue : m_ueCtx)
            sendRlsMessage(ue.first, resp);
    }
}

} // namespace nr::gnb
