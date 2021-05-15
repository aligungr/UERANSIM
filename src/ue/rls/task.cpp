//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <ue/rrc/task.hpp>
#include <utils/common.hpp>
#include <utils/constants.hpp>

namespace nr::ue
{

UeRlsTask::UeRlsTask(TaskBase *base) : m_base{base}
{
    m_logger = m_base->logBase->makeUniqueLogger(m_base->config->getLoggerPrefix() + "rls");

    m_sti = utils::Random64();

    m_udpTask = new RlsUdpTask(base, m_sti, base->config->gnbSearchList);
    m_ctlTask = new RlsControlTask(base, m_sti);

    m_udpTask->initialize(m_ctlTask);
    m_ctlTask->initialize(this, m_udpTask);
}

void UeRlsTask::onStart()
{
    m_udpTask->start();
    m_ctlTask->start();
}

void UeRlsTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_RLS_TO_RLS: {
        auto *w = dynamic_cast<NwUeRlsToRls *>(msg);
        switch (w->present)
        {
        case NwUeRlsToRls::SIGNAL_CHANGED: {
            m_logger->debug("signal changed cellId[%d] dbm[%d]", w->cellId, w->dbm);
            break;
        }
        case NwUeRlsToRls::DOWNLINK_DATA: {
            m_logger->debug("downlink data psi[%d]", w->psi);
            break;
        }
        case NwUeRlsToRls::DOWNLINK_RRC: {
            auto *m = new NwUeRlsToRrc(NwUeRlsToRrc::DOWNLINK_RRC_DELIVERY);
            m->cellId = w->cellId;
            m->channel = w->rrcChannel;
            m->pdu = std::move(w->data);
            m_base->rrcTask->push(m);
            break;
        }
        case NwUeRlsToRls::RADIO_LINK_FAILURE: {
            m_logger->debug("radio link failure [%d]", (int)w->rlfCause);
            break;
        }
        case NwUeRlsToRls::TRANSMISSION_FAILURE: {
            m_logger->debug("transmission failure [%d]", w->pduList.size());
            break;
        }
        default: {
            m_logger->unhandledNts(msg);
            break;
        }
        }
        break;
    }
    default:
        m_logger->unhandledNts(msg);
        break;
    }

    delete msg;
}

void UeRlsTask::onQuit()
{
    m_udpTask->quit();
    m_ctlTask->quit();
    delete m_udpTask;
    delete m_ctlTask;
}

} // namespace nr::ue
