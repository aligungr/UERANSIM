//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include "cmd.hpp"

#include <utils/random.hpp>

struct TimerPeriod
{
    static constexpr const int L3_MACHINE_CYCLE = 2500;
    static constexpr const int L3_TIMER = 1000;
    static constexpr const int RLS_ACK_CONTROL = 1500;
    static constexpr const int RLS_ACK_SEND = 2250;
    static constexpr const int SWITCH_OFF = 500;
};

namespace nr::ue
{

ue::UeTask::UeTask(std::unique_ptr<UeConfig> &&config, app::IUeController *ueController,
                   app::INodeListener *nodeListener, NtsTask *cliCallbackTask)
{
    this->logBase = std::make_unique<LogBase>("logs/ue-" + config->getNodeName() + ".log");
    this->config = std::move(config);
    this->ueController = ueController;
    this->nodeListener = nodeListener;
    this->cliCallbackTask = cliCallbackTask;

    this->m_logger = logBase->makeUniqueLogger(this->config->getLoggerPrefix() + "main");
    this->shCtx.sti = Random::Mixed(this->config->getNodeName()).nextL();

    this->rlsUdp = std::make_unique<RlsUdpLayer>(this);
    this->rlsCtl = std::make_unique<RlsCtlLayer>(this);
    this->rrc = std::make_unique<UeRrcLayer>(this);
    this->nas = std::make_unique<NasLayer>(this);
    this->tun = std::make_unique<TunLayer>(this);

    this->m_timerL3MachineCycle = -1;
    this->m_timerL3Timer = -1;
    this->m_timerRlsAckControl = -1;
    this->m_timerRlsAckSend = -1;
    this->m_timerSwitchOff = -1;

    this->m_immediateCycle = true;
}

UeTask::~UeTask() = default;

void UeTask::onStart()
{
    rlsUdp->onStart();
    rrc->onStart();
    nas->onStart();

    auto current = utils::CurrentTimeMillis();
    m_timerL3MachineCycle = current + TimerPeriod::L3_MACHINE_CYCLE;
    m_timerL3Timer = current + TimerPeriod::L3_TIMER;
    m_timerRlsAckControl = current + TimerPeriod::RLS_ACK_CONTROL;
    m_timerRlsAckSend = current + TimerPeriod::RLS_ACK_SEND;
}

void UeTask::onLoop()
{
    rlsUdp->checkHeartbeat();

    checkTimers();

    if (m_immediateCycle)
    {
        m_immediateCycle = false;
        rrc->performCycle();
        nas->performCycle();
        return;
    }

    auto msg = take();
    if (!msg)
        return;

    if (msg->msgType == NtsMessageType::UE_TUN_TO_APP)
    {
        auto &w = dynamic_cast<NmUeTunToApp &>(*msg);
        nas->handleUplinkDataRequest(w.psi, std::move(w.data));
    }
    else if (msg->msgType == NtsMessageType::UDP_SERVER_RECEIVE)
    {
        auto &w = dynamic_cast<udp::NwUdpServerReceive &>(*msg);
        rlsUdp->receiveRlsPdu(w.fromAddress, w.packet);
    }
    else if (msg->msgType == NtsMessageType::UE_CLI_COMMAND)
    {
        auto &w = dynamic_cast<NmUeCliCommand &>(*msg);
        UeCmdHandler handler{this};
        handler.handleCmd(w);
    }
    else
    {
        m_logger->unhandledNts(*msg);
    }
}

void UeTask::onQuit()
{
    rlsUdp->onQuit();
    rrc->onQuit();
    nas->onQuit();
}

void UeTask::checkTimers()
{
    auto current = utils::CurrentTimeMillis();

    if (m_timerL3MachineCycle != -1 && m_timerL3MachineCycle <= current)
    {
        m_timerL3MachineCycle = current + TimerPeriod::L3_MACHINE_CYCLE;
        rrc->performCycle();
        nas->performCycle();
    }
    else if (m_timerL3Timer != -1 && m_timerL3Timer <= current)
    {
        m_timerL3Timer = current + TimerPeriod::L3_TIMER;
        rrc->performCycle();
        nas->performCycle();
    }
    else if (m_timerRlsAckControl != -1 && m_timerRlsAckControl <= current)
    {
        m_timerRlsAckControl = current + TimerPeriod::RLS_ACK_CONTROL;
        rlsCtl->onAckControlTimerExpired();
    }
    else if (m_timerRlsAckSend != -1 && m_timerRlsAckSend <= current)
    {
        m_timerRlsAckSend = current + TimerPeriod::RLS_ACK_SEND;
        rlsCtl->onAckSendTimerExpired();
    }
    else if (m_timerSwitchOff != -1 && m_timerSwitchOff <= current)
    {
        ueController->performSwitchOff(this);
    }
}

void UeTask::triggerCycle()
{
    m_immediateCycle = true;
}

void UeTask::triggerSwitchOff()
{
    m_timerSwitchOff = utils::CurrentTimeMillis() + TimerPeriod::SWITCH_OFF;
}

} // namespace nr::ue