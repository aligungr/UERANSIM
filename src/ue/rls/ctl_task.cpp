//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ctl_task.hpp"

#include <utils/common.hpp>

static constexpr const int TIMER_ID_ACK_CONTROL = 1;
static constexpr const int TIMER_ID_ACK_SEND = 2;

static constexpr const int TIMER_PERIOD_ACK_CONTROL = 1500;
static constexpr const int TIMER_PERIOD_ACK_SEND = 2250;

namespace nr::ue
{

RlsControlTask::RlsControlTask(TaskBase *base, RlsSharedContext *shCtx)
{
    layer = std::make_unique<RlsCtlLayer>(base, shCtx);
    mainTask = {};
    udpTask = {};
}

void RlsControlTask::onStart()
{
    layer->onStart(mainTask, udpTask);

    setTimer(TIMER_ID_ACK_CONTROL, TIMER_PERIOD_ACK_CONTROL);
    setTimer(TIMER_ID_ACK_SEND, TIMER_PERIOD_ACK_SEND);
}

void RlsControlTask::onQuit()
{
    layer->onQuit();
}

void RlsControlTask::onLoop()
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
            layer->onAckControlTimerExpired();
        }
        else if (w.timerId == TIMER_ID_ACK_SEND)
        {
            setTimer(TIMER_ID_ACK_SEND, TIMER_PERIOD_ACK_SEND);
            layer->onAckSendTimerExpired();
        }
    }
    else if (msg->msgType == NtsMessageType::UE_RLS_TO_RLS)
    {
        layer->handleSapMessage(dynamic_cast<NmUeRlsToRls &>(*msg));
    }
}

void RlsControlTask::initialize(NtsTask *mainTask, RlsUdpTask *udpTask)
{
    this->mainTask = mainTask;
    this->udpTask = udpTask;
}

} // namespace nr::ue
