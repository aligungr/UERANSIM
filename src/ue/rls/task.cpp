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

    base->shCtx.sti = Random::Mixed(base->config->getNodeName()).nextL();

    m_udpLayer = std::make_unique<RlsUdpLayer>(base);
    m_ctlLayer = std::make_unique<RlsCtlLayer>(base);
}

void UeRlsTask::onStart()
{
    m_udpLayer->onStart();
    m_ctlLayer->onStart();

    setTimer(TIMER_ID_ACK_CONTROL, TIMER_PERIOD_ACK_CONTROL);
    setTimer(TIMER_ID_ACK_SEND, TIMER_PERIOD_ACK_SEND);
}

void UeRlsTask::onLoop()
{
    m_udpLayer->checkHeartbeat();

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
            m_base->shCtx.sti = Random::Mixed(m_base->config->getNodeName()).nextL();
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
    case NtsMessageType::UDP_SERVER_RECEIVE: {
        auto &w = dynamic_cast<udp::NwUdpServerReceive &>(*msg);
        auto rlsMsg = rls::DecodeRlsMessage(OctetView{w.packet});
        if (rlsMsg == nullptr)
            m_logger->err("Unable to decode RLS message");
        else
            m_udpLayer->receiveRlsPdu(w.fromAddress, std::move(rlsMsg));
        break;
    }
    default:
        m_logger->unhandledNts(*msg);
        break;
    }
}

void UeRlsTask::onQuit()
{
    m_udpLayer->onQuit();
    m_ctlLayer->onQuit();
}

RlsCtlLayer &UeRlsTask::ctl()
{
    return *m_ctlLayer;
}

RlsUdpLayer &UeRlsTask::udp()
{
    return *m_udpLayer;
}

} // namespace nr::ue
