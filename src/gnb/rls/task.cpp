//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "task.hpp"

#include <gnb/gtp/task.hpp>
#include <gnb/rrc/task.hpp>
#include <utils/common.hpp>
#include <utils/random.hpp>

namespace nr::gnb
{

GnbRlsTask::GnbRlsTask(TaskBase *base) : m_base{base}
{
    m_logger = m_base->logBase->makeUniqueLogger("rls");
    m_sti = Random::Mixed(base->config->name).nextUL();

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
    auto msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::GNB_RLS_TO_RLS: {
        auto &w = dynamic_cast<NmGnbRlsToRls &>(*msg);
        switch (w.present)
        {
        case NmGnbRlsToRls::SIGNAL_DETECTED: {
            auto m = std::make_unique<NmGnbRlsToRrc>(NmGnbRlsToRrc::SIGNAL_DETECTED);
            m->ueId = w.ueId;
            m_base->rrcTask->push(std::move(m));
            break;
        }
        case NmGnbRlsToRls::SIGNAL_LOST: {
            m_logger->debug("UE[%d] signal lost", w.ueId);
            break;
        }
        case NmGnbRlsToRls::UPLINK_DATA: {
            auto m = std::make_unique<NmGnbRlsToGtp>(NmGnbRlsToGtp::DATA_PDU_DELIVERY);
            m->ueId = w.ueId;
            m->psi = w.psi;
            m->pdu = std::move(w.data);
            m_base->gtpTask->push(std::move(m));
            break;
        }
        case NmGnbRlsToRls::UPLINK_RRC: {
            auto m = std::make_unique<NmGnbRlsToRrc>(NmGnbRlsToRrc::UPLINK_RRC);
            m->ueId = w.ueId;
            m->rrcChannel = w.rrcChannel;
            m->data = std::move(w.data);
            m_base->rrcTask->push(std::move(m));
            break;
        }
        case NmGnbRlsToRls::RADIO_LINK_FAILURE: {
            m_logger->debug("radio link failure [%d]", (int)w.rlfCause);
            break;
        }
        case NmGnbRlsToRls::TRANSMISSION_FAILURE: {
            m_logger->debug("transmission failure [%s]", "");
            break;
        }
        default: {
            m_logger->unhandledNts(*msg);
            break;
        }
        }
        break;
    }
    case NtsMessageType::GNB_RRC_TO_RLS: {
        auto &w = dynamic_cast<NmGnbRrcToRls &>(*msg);
        switch (w.present)
        {
        case NmGnbRrcToRls::RRC_PDU_DELIVERY: {
            auto m = std::make_unique<NmGnbRlsToRls>(NmGnbRlsToRls::DOWNLINK_RRC);
            m->ueId = w.ueId;
            m->rrcChannel = w.channel;
            m->pduId = 0;
            m->data = std::move(w.pdu);
            m_ctlTask->push(std::move(m));
            break;
        }
        }
        break;
    }
    case NtsMessageType::GNB_GTP_TO_RLS: {
        auto &w = dynamic_cast<NmGnbGtpToRls &>(*msg);
        switch (w.present)
        {
        case NmGnbGtpToRls::DATA_PDU_DELIVERY: {
            auto m = std::make_unique<NmGnbRlsToRls>(NmGnbRlsToRls::DOWNLINK_DATA);
            m->ueId = w.ueId;
            m->psi = w.psi;
            m->data = std::move(w.pdu);
            m_ctlTask->push(std::move(m));
            break;
        }
        }
        break;
    }
    default:
        m_logger->unhandledNts(*msg);
        break;
    }
}

void GnbRlsTask::onQuit()
{
    m_udpTask->quit();
    m_ctlTask->quit();
    delete m_udpTask;
    delete m_ctlTask;
}

} // namespace nr::gnb
