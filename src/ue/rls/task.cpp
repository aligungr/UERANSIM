//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <ue/app/task.hpp>
#include <ue/l3/task.hpp>
#include <utils/common.hpp>
#include <utils/random.hpp>

static constexpr const int TIMER_ID_ACK_CONTROL = 1;
static constexpr const int TIMER_ID_ACK_SEND = 2;
static constexpr const int TIMER_PERIOD_ACK_CONTROL = 1500;
static constexpr const int TIMER_PERIOD_ACK_SEND = 2250;

namespace nr::ue
{

UeRlsTask::UeRlsTask(TaskBase *base) : m_base{base}
{
    m_logger = m_base->logBase->makeUniqueLogger(m_base->config->getLoggerPrefix() + "rls");

    m_shCtx = new RlsSharedContext();
    m_shCtx->sti = Random::Mixed(base->config->getNodeName()).nextL();

    m_udpTask = new RlsUdpTask(base, m_shCtx);
    m_ctlLayer = std::make_unique<RlsCtlLayer>(base, m_shCtx);
}

void UeRlsTask::onStart()
{
    m_udpTask->start();
    m_ctlLayer->onStart(this, m_udpTask);

    setTimer(TIMER_ID_ACK_CONTROL, TIMER_PERIOD_ACK_CONTROL);
    setTimer(TIMER_ID_ACK_SEND, TIMER_PERIOD_ACK_SEND);
}

void UeRlsTask::onLoop()
{
    auto msg = take();
    if (!msg)
        return;

    if (msg->msgType == NtsMessageType::TIMER_EXPIRED)
    {
        auto &w = dynamic_cast<NmTimerExpired &>(*msg);
        if (w.timerId == TIMER_ID_ACK_CONTROL)
        {
            setTimer(TIMER_ID_ACK_CONTROL, TIMER_PERIOD_ACK_CONTROL);
            m_ctlLayer->onAckControlTimerExpired();
        }
        else if (w.timerId == TIMER_ID_ACK_SEND)
        {
            setTimer(TIMER_ID_ACK_SEND, TIMER_PERIOD_ACK_SEND);
            m_ctlLayer->onAckSendTimerExpired();
        }
        return;
    }

    switch (msg->msgType)
    {
    case NtsMessageType::UE_RLS_TO_RLS: {
        auto &w = dynamic_cast<NmUeRlsToRls &>(*msg);
        switch (w.present)
        {
        case NmUeRlsToRls::RECEIVE_RLS_MESSAGE: {
            m_ctlLayer->handleRlsMessage(w.cellId, *w.msg);
            break;
        }
        case NmUeRlsToRls::SIGNAL_CHANGED: {
            auto m = std::make_unique<NmUeRlsToRrc>(NmUeRlsToRrc::SIGNAL_CHANGED);
            m->cellId = w.cellId;
            m->dbm = w.dbm;
            m_base->l3Task->push(std::move(m));
            break;
        }
        case NmUeRlsToRls::DOWNLINK_DATA: {
            auto m = std::make_unique<NmUeRlsToNas>(NmUeRlsToNas::DATA_PDU_DELIVERY);
            m->psi = w.psi;
            m->pdu = std::move(w.data);
            m_base->l3Task->push(std::move(m));
            break;
        }
        case NmUeRlsToRls::DOWNLINK_RRC: {
            auto m = std::make_unique<NmUeRlsToRrc>(NmUeRlsToRrc::DOWNLINK_RRC_DELIVERY);
            m->cellId = w.cellId;
            m->channel = w.rrcChannel;
            m->pdu = std::move(w.data);
            m_base->l3Task->push(std::move(m));
            break;
        }
        case NmUeRlsToRls::RADIO_LINK_FAILURE: {
            auto m = std::make_unique<NmUeRlsToRrc>(NmUeRlsToRrc::RADIO_LINK_FAILURE);
            m->rlfCause = w.rlfCause;
            m_base->l3Task->push(std::move(m));
            break;
        }
        case NmUeRlsToRls::TRANSMISSION_FAILURE: {
            m_logger->debug("transmission failure [%d]", w.pduList.size());
            break;
        }
        default: {
            m_logger->unhandledNts(*msg);
            break;
        }
        }
        break;
    }
    case NtsMessageType::UE_RRC_TO_RLS: {
        auto &w = dynamic_cast<NmUeRrcToRls &>(*msg);
        switch (w.present)
        {
        case NmUeRrcToRls::ASSIGN_CURRENT_CELL: {
            m_ctlLayer->assignCurrentCell(w.cellId);
            break;
        }
        case NmUeRrcToRls::RRC_PDU_DELIVERY: {
            m_ctlLayer->handleUplinkRrcDelivery(w.cellId, w.pduId, w.channel, std::move(w.pdu));
            break;
        }
        case NmUeRrcToRls::RESET_STI: {
            m_shCtx->sti = Random::Mixed(m_base->config->getNodeName()).nextL();
            break;
        }
        }
        break;
    }
    case NtsMessageType::UE_NAS_TO_RLS: {
        auto &w = dynamic_cast<NmUeNasToRls &>(*msg);
        switch (w.present)
        {
        case NmUeNasToRls::DATA_PDU_DELIVERY: {
            m_ctlLayer->handleUplinkDataDelivery(w.psi, std::move(w.pdu));
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

void UeRlsTask::onQuit()
{
    m_udpTask->quit();
    m_ctlLayer->onQuit();

    delete m_udpTask;
    delete m_shCtx;
}

} // namespace nr::ue
