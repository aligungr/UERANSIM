//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <gnb/gtp/task.hpp>
#include <gnb/rrc/task.hpp>
#include <utils/common.hpp>

namespace nr::gnb
{

GnbRlsTask::GnbRlsTask(TaskBase *base) : m_base{base}
{
    m_logger = m_base->logBase->makeUniqueLogger("rls");
    m_sti = utils::Random64();

    m_udpTask = new RlsUdpTask(base, m_sti, base->config->phyLocation);
    m_ctlTask = new RlsControlTask(base, m_sti);

    m_udpTask->initialize(m_ctlTask);
    m_ctlTask->initialize(this, m_udpTask);
}

void GnbRlsTask::onStart()
{
    m_udpTask->start();
    m_ctlTask->start();
}

void GnbRlsTask::onLoop()
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
        case NwGnbRlsToRls::SIGNAL_DETECTED: {
            auto *m = new NwGnbRlsToRrc(NwGnbRlsToRrc::SIGNAL_DETECTED);
            m->ueId = w->ueId;
            m_base->rrcTask->push(m);
            break;
        }
        case NwGnbRlsToRls::SIGNAL_LOST: {
            m_logger->debug("UE[%d] signal lost", w->ueId);
            break;
        }
        case NwGnbRlsToRls::UPLINK_DATA: {
            auto *m = new NwGnbRlsToGtp(NwGnbRlsToGtp::DATA_PDU_DELIVERY);
            m->ueId = w->ueId;
            m->psi = w->psi;
            m->pdu = std::move(w->data);
            m_base->gtpTask->push(m);
            break;
        }
        case NwGnbRlsToRls::UPLINK_RRC: {
            auto *m = new NwGnbRlsToRrc(NwGnbRlsToRrc::UPLINK_RRC);
            m->ueId = w->ueId;
            m->rrcChannel = w->rrcChannel;
            m->data = std::move(w->data);
            m_base->rrcTask->push(m);
            break;
        }
        case NwGnbRlsToRls::RADIO_LINK_FAILURE: {
            m_logger->debug("radio link failure [%d]", (int)w->rlfCause);
            break;
        }
        case NwGnbRlsToRls::TRANSMISSION_FAILURE: {
            m_logger->debug("transmission failure [%s]", "");
            break;
        }
        default: {
            m_logger->unhandledNts(msg);
            break;
        }
        }
        break;
    }
    case NtsMessageType::GNB_RRC_TO_RLS: {
        auto *w = dynamic_cast<NwGnbRrcToRls *>(msg);
        switch (w->present)
        {
        case NwGnbRrcToRls::RRC_PDU_DELIVERY: {
            auto *m = new NwGnbRlsToRls(NwGnbRlsToRls::DOWNLINK_RRC);
            m->ueId = w->ueId;
            m->rrcChannel = w->channel;
            m->pduId = 0;
            m->data = std::move(w->pdu);
            m_ctlTask->push(m);
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

void GnbRlsTask::onQuit()
{
    m_udpTask->quit();
    m_ctlTask->quit();
    delete m_udpTask;
    delete m_ctlTask;
}

} // namespace nr::gnb
