//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <utils/random.hpp>

struct TimerId
{
    static constexpr const int L3_MACHINE_CYCLE = 1;
    static constexpr const int L3_TIMER = 2;
    static constexpr const int RLS_ACK_CONTROL = 3;
    static constexpr const int RLS_ACK_SEND = 4;
};

struct TimerPeriod
{
    static constexpr const int L3_MACHINE_CYCLE = 2500;
    static constexpr const int L3_TIMER = 1000;
    static constexpr const int RLS_ACK_CONTROL = 1500;
    static constexpr const int RLS_ACK_SEND = 2250;
};

namespace nr::ue
{

ue::UeL23Task::UeL23Task(TaskBase *base) : m_base{base}
{
    m_logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "l23");
    base->shCtx.sti = Random::Mixed(base->config->getNodeName()).nextL();

    m_rlsUdp = std::make_unique<RlsUdpLayer>(base, this);
    m_rlsCtl = std::make_unique<RlsCtlLayer>(base);
    m_rrc = std::make_unique<UeRrcLayer>(base);
    m_nas = std::make_unique<NasLayer>(base);
    m_tun = std::make_unique<TunLayer>(base);
}

UeL23Task::~UeL23Task() = default;

void UeL23Task::onStart()
{
    m_rrc->onStart();
    m_nas->onStart();

    setTimer(TimerId::L3_MACHINE_CYCLE, TimerPeriod::L3_MACHINE_CYCLE);
    setTimer(TimerId::L3_TIMER, TimerPeriod::L3_TIMER);
    setTimer(TimerId::RLS_ACK_CONTROL, TimerPeriod::RLS_ACK_CONTROL);
    setTimer(TimerId::RLS_ACK_SEND, TimerPeriod::RLS_ACK_SEND);
}

void UeL23Task::onLoop()
{
    m_rlsUdp->checkHeartbeat();

    auto msg = take();
    if (!msg)
        return;

    if (msg->msgType == NtsMessageType::UE_CYCLE_REQUIRED)
    {
        m_rrc->performCycle();
        m_nas->performCycle();
    }
    else if (msg->msgType == NtsMessageType::TIMER_EXPIRED)
    {
        auto &w = dynamic_cast<NmTimerExpired &>(*msg);
        if (w.timerId == TimerId::L3_MACHINE_CYCLE)
        {
            setTimer(TimerId::L3_MACHINE_CYCLE, TimerPeriod::L3_MACHINE_CYCLE);
            m_rrc->performCycle();
            m_nas->performCycle();
        }
        else if (w.timerId == TimerId::L3_TIMER)
        {
            setTimer(TimerId::L3_TIMER, TimerPeriod::L3_TIMER);
            m_rrc->performCycle();
            m_nas->performCycle();
        }
        else if (w.timerId == TimerId::RLS_ACK_CONTROL)
        {
            setTimer(TimerId::RLS_ACK_CONTROL, TimerPeriod::RLS_ACK_CONTROL);
            m_rlsCtl->onAckControlTimerExpired();
        }
        else if (w.timerId == TimerId::RLS_ACK_SEND)
        {
            setTimer(TimerId::RLS_ACK_SEND, TimerPeriod::RLS_ACK_SEND);
            m_rlsCtl->onAckSendTimerExpired();
        }
    }
    else if (msg->msgType == NtsMessageType::UE_TUN_TO_APP || msg->msgType == NtsMessageType::UE_RLS_TO_NAS)
    {
        m_nas->handleSapMessage(std::move(msg));
    }
    else if (msg->msgType == NtsMessageType::UDP_SERVER_RECEIVE)
    {
        auto &w = dynamic_cast<udp::NwUdpServerReceive &>(*msg);
        auto rlsMsg = rls::DecodeRlsMessage(OctetView{w.packet});
        if (rlsMsg == nullptr)
            m_logger->err("Unable to decode RLS message");
        else
            m_rlsUdp->receiveRlsPdu(w.fromAddress, std::move(rlsMsg));
    }
    else
    {
        m_logger->unhandledNts(*msg);
    }
}

void UeL23Task::onQuit()
{
    m_rrc->onQuit();
    m_nas->onQuit();
}

} // namespace nr::ue