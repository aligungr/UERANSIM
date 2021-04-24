//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <gnb/gtp/task.hpp>
#include <gnb/nts.hpp>
#include <gnb/rrc/task.hpp>
#include <utils/common.hpp>
#include <utils/constants.hpp>
#include <utils/libc_error.hpp>

static const int TIMER_ID_LOST_CONTROL = 1;
static const int TIMER_PERIOD_LOST_CONTROL = 2000;

namespace nr::gnb
{

GnbRlsTask::GnbRlsTask(TaskBase *base)
    : m_base{base}, m_udpTask{}, m_powerOn{}, m_ueCtx{}, m_stiToUeId{}, m_ueIdCounter{}
{
    m_logger = m_base->logBase->makeUniqueLogger("rls");
    m_sti = utils::Random64();
}

void GnbRlsTask::onStart()
{
    try
    {
        m_udpTask = new udp::UdpServerTask(m_base->config->portalIp, cons::PortalPort, this);
        m_udpTask->start();
    }
    catch (const LibError &e)
    {
        m_logger->err("RLS failure [%s]", e.what());
        quit();
        return;
    }

    setTimer(TIMER_ID_LOST_CONTROL, TIMER_PERIOD_LOST_CONTROL);
}

void GnbRlsTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::GNB_RRC_TO_RLS: {
        auto *w = dynamic_cast<NwGnbRrcToRls *>(msg);
        switch (w->present)
        {
        case NwGnbRrcToRls::RRC_PDU_DELIVERY: {
            handleDownlinkDelivery(w->ueId, rls::EPduType::RRC, std::move(w->pdu),
                                   OctetString::FromOctet4(static_cast<int>(w->channel)));
            break;
        }
        case NwGnbRrcToRls::RADIO_POWER_ON: {
            m_powerOn = true;
            break;
        }
        }
        break;
    }
    case NtsMessageType::GNB_GTP_TO_RLS: {
        auto *w = dynamic_cast<NwGnbGtpToRls *>(msg);
        switch (w->present)
        {
        case NwGnbGtpToRls::DATA_PDU_DELIVERY: {
            handleDownlinkDelivery(w->ueId, rls::EPduType::DATA, std::move(w->pdu),
                                   OctetString::FromOctet4(static_cast<int>(w->psi)));
            break;
        }
        }
        break;
    }
    case NtsMessageType::UDP_SERVER_RECEIVE: {
        auto *w = dynamic_cast<udp::NwUdpServerReceive *>(msg);
        auto rlsMsg = rls::DecodeRlsMessage(OctetView{w->packet});
        if (rlsMsg == nullptr)
        {
            m_logger->err("Unable to decode RLS message");
            break;
        }
        receiveRlsMessage(w->fromAddress, *rlsMsg);
        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto *w = dynamic_cast<NwTimerExpired *>(msg);
        if (w->timerId == TIMER_ID_LOST_CONTROL)
        {
            setTimer(TIMER_ID_LOST_CONTROL, TIMER_PERIOD_LOST_CONTROL);
            onPeriodicLostControl();
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
    if (m_udpTask != nullptr)
        m_udpTask->quit();
    delete m_udpTask;
}

} // namespace nr::gnb
