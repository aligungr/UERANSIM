//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "sm.hpp"
#include <algorithm>
#include <lib/nas/proto_conf.hpp>
#include <lib/nas/utils.hpp>
#include <ue/app/task.hpp>
#include <ue/nas/mm/mm.hpp>

namespace nr::ue
{

std::unique_ptr<UeTimer> NasSm::newTransactionTimer(int code)
{
    std::unique_ptr<UeTimer> timer;

    switch (code)
    {
    case 3580:
        timer = std::make_unique<UeTimer>(3580, false, 16);
        break;
    case 3581:
        timer = std::make_unique<UeTimer>(3581, false, 16);
        break;
    case 3582:
        timer = std::make_unique<UeTimer>(3582, false, 16);
        break;
    default:
        m_logger->err("Bad SM transaction timer code");
        return nullptr;
    }

    timer->start();
    return timer;
}

void NasSm::onTimerExpire(UeTimer &timer)
{
}

void NasSm::onTransactionTimerExpire(int pti)
{
    auto &pt = m_procedureTransactions[pti];
    if (pt.state == EPtState::INACTIVE)
        return;

    switch (pt.timer->getCode())
    {
    case 3580: {
        if (pt.timer->getExpiryCount() < 5)
        {
            m_logger->warn("Retransmitting PDU Session Establishment Request due to T3580 expiry");
            sendSmMessage(pt.psi, *pt.message);

            pt.timer->start(false);
        }
        else
        {
            m_logger->err("PDU Session Establishment procedure failure, no response from the network after 5 attempts");
            abortProcedureByPti(pti);
        }
        break;
    }
    case 3582: {
        if (pt.timer->getExpiryCount() < 5)
        {
            m_logger->warn("Retransmitting PDU Session Release Request due to T3582 expiry");
            sendSmMessage(pt.psi, *pt.message);

            pt.timer->start(false);
        }
        else
        {
            m_logger->err("PDU Session Release procedure failure, no response from the network after 5 attempts");
            abortProcedureByPti(pti);
            m_mm->mobilityUpdatingRequired(ERegUpdateCause::PS_STATUS_INFORM);
        }
        break;
    }
    }
}

} // namespace nr::ue