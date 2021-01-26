//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_nas_task.hpp"
#include "ue_nts.hpp"

static const int NTS_TIMER_ID_NAS_TIMER_CYCLE = 1;
static const int NTS_TIMER_ID_MM_CYCLE = 2;
static const int NTS_TIMER_INTERVAL_NAS_TIMER_CYCLE = 1000;
static const int NTS_TIMER_INTERVAL_MM_CYCLE = 400;

namespace nr::ue
{

NasTask::NasTask(TaskBase *base)
    : base{base}, timers{}, mmCtx{}, smCtx{}, currentNsCtx{}, nonCurrentNsCtx{},
      emulationMode(base->config->emulationMode)
{
    logger = base->logBase->makeUniqueLogger("nas");
}

void NasTask::onStart()
{
    logger->debug("NAS layer started");

    auto *statusUpdate = new NwUeStatusUpdate(NwUeStatusUpdate::MM_STATE);
    statusUpdate->mmState = MmStateName(mmCtx.mmState);
    statusUpdate->mmSubState = MmSubStateName(mmCtx.mmSubState);
    base->appTask->push(statusUpdate);

    statusUpdate = new NwUeStatusUpdate(NwUeStatusUpdate::RM_STATE);
    statusUpdate->rmState = RmStateName(mmCtx.rmState);
    base->appTask->push(statusUpdate);

    setTimer(NTS_TIMER_ID_NAS_TIMER_CYCLE, NTS_TIMER_INTERVAL_NAS_TIMER_CYCLE);
    setTimer(NTS_TIMER_ID_MM_CYCLE, NTS_TIMER_INTERVAL_MM_CYCLE);
    triggerMmCycle();
}

void NasTask::onQuit()
{
    // TODO
}

void NasTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_DOWNLINK_NAS_DELIVERY: {
        auto *w = dynamic_cast<NwDownlinkNasDelivery *>(msg);
        OctetString nasPdu = std::move(w->nasPdu);
        delete w;
        OctetBuffer buffer{nasPdu};
        std::unique_ptr<nas::NasMessage> nasMessage = nas::DecodeNasMessage(buffer);
        receiveNasMessage(*nasMessage);
        break;
    }
    case NtsMessageType::NAS_TIMER_EXPIRE: {
        auto *w = dynamic_cast<NwNasTimerExpire *>(msg);
        nas::NasTimer *timer = w->timer;
        delete w;
        onTimerExpire(*timer);
        break;
    }
    case NtsMessageType::UE_EXTERNAL_COMMAND: {
        // TODO
        delete msg;
        break;
    }
    case NtsMessageType::UE_MR_PLMN_SEARCH_RESPONSE: {
        auto *w = dynamic_cast<NwPlmnSearchResponse *>(msg);
        receivePlmnSearchResponse(*w);
        delete w;
        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto *w = dynamic_cast<NwTimerExpired *>(msg);
        int timerId = w->timerId;
        delete w;
        if (timerId == NTS_TIMER_ID_NAS_TIMER_CYCLE)
        {
            setTimer(NTS_TIMER_ID_NAS_TIMER_CYCLE, NTS_TIMER_INTERVAL_NAS_TIMER_CYCLE);
            performTick();
        }
        if (timerId == NTS_TIMER_ID_MM_CYCLE)
        {
            setTimer(NTS_TIMER_ID_MM_CYCLE, NTS_TIMER_INTERVAL_MM_CYCLE);
            performMmCycle();
        }
        break;
    }
    case NtsMessageType::NAS_PERFORM_MM_CYCLE: {
        delete msg;
        performMmCycle();
        break;
    }
    case NtsMessageType::UE_TRIGGER_INITIAL_SESSION_CREATE: {
        establishInitialSessions();
        delete msg;
        break;
    }
    default:
        logger->err("Unhandled NTS message received with type %d", (int)msg->msgType);
        delete msg;
        break;
    }
}

} // namespace nr::ue