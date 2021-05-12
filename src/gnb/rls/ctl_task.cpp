//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ctl_task.hpp"

namespace nr::gnb
{

RlsControlTask::RlsControlTask(TaskBase *base) : m_udpTask{}
{
    m_logger = base->logBase->makeUniqueLogger("rls-ctl");
}

void RlsControlTask::initialize(RlsUdpTask *udpTask)
{
    m_udpTask = udpTask;
}

void RlsControlTask::onStart()
{
}

void RlsControlTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::GNB_RLS_TO_RLS: {
        auto *w = dynamic_cast<NwGnbRlsToRls *>(msg);
        switch (w->present)
        {
        case NwGnbRlsToRls::SIGNAL_DETECTED:
            handleSignalDetected(w->ueId);
            break;
        case NwGnbRlsToRls::SIGNAL_LOST:
            handleSignalLost(w->ueId);
            break;
        case NwGnbRlsToRls::RECEIVE_RLS_MESSAGE:
            handleRlsMessage(w->ueId, *w->msg);
            break;
        case NwGnbRlsToRls::DOWNLINK_DATA:
            handleDownlinkDataDelivery(w->ueId, w->psi, std::move(w->data));
            break;
        case NwGnbRlsToRls::DOWNLINK_RRC:
            handleDownlinkRrcDelivery(w->ueId, w->pduId, w->rrcChannel, std::move(w->data));
            break;
        default:
            m_logger->unhandledNts(msg);
            break;
        }
        break;
    }
    default:
        m_logger->unhandledNts(msg);
        break;
    }

    delete msg;
}

void RlsControlTask::onQuit()
{
}

} // namespace nr::gnb
