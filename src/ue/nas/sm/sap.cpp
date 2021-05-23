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
#include <ue/rls/task.hpp>

namespace nr::ue
{

void NasSm::handleNasEvent(const NmUeNasToNas &msg)
{
    switch (msg.present)
    {
    case NmUeNasToNas::NAS_TIMER_EXPIRE:
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

void NasSm::handleUplinkDataRequest(int psi, OctetString &&data)
{
    if (m_pduSessions[psi]->psState != EPsState::ACTIVE)
        return;

    if (m_mm->m_cmState == ECmState::CM_CONNECTED)
    {
        // TODO: We should also check if radio resources are established by RRC.
        //  Checking CM state is not sufficient

        if (m_pduSessions[psi]->uplinkPending)
        {
            m_pduSessions[psi]->uplinkPending = false;
            handleUplinkStatusChange(psi, false);
        }

        auto *m = new NmUeNasToRls(NmUeNasToRls::DATA_PDU_DELIVERY);
        m->psi = psi;
        m->pdu = std::move(data);
        m_base->rlsTask->push(m);
    }
    else
    {
        if (!m_pduSessions[psi]->uplinkPending)
        {
            m_pduSessions[psi]->uplinkPending = true;
            handleUplinkStatusChange(psi, true);
        }
    }
}

} // namespace nr::ue
