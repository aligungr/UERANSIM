//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "task.hpp"
#include <ue/nts.hpp>

static const int NTS_TIMER_ID_NAS_TIMER_CYCLE = 1;
static const int NTS_TIMER_ID_MM_CYCLE = 2;
static const int NTS_TIMER_INTERVAL_NAS_TIMER_CYCLE = 1000;
static const int NTS_TIMER_INTERVAL_MM_CYCLE = 1100;

namespace nr::ue
{

NasTask::NasTask(TaskBase *base) : base{base}, timers{}
{
    logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "nas");

    mm = new NasMm(base, &timers);
    sm = new NasSm(base, &timers);
    usim = new Usim();
}

void NasTask::onStart()
{
    usim->initialize(base->config->supi.has_value());

    sm->onStart(mm);
    mm->onStart(sm, usim);

    setTimer(NTS_TIMER_ID_NAS_TIMER_CYCLE, NTS_TIMER_INTERVAL_NAS_TIMER_CYCLE);
    setTimer(NTS_TIMER_ID_MM_CYCLE, NTS_TIMER_INTERVAL_MM_CYCLE);
}

void NasTask::onQuit()
{
    mm->onQuit();
    sm->onQuit();

    delete mm;
    delete sm;

    delete usim;
}

void NasTask::onLoop()
{
    auto msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_RRC_TO_NAS: {
        mm->handleRrcEvent(dynamic_cast<NmUeRrcToNas &>(*msg));
        break;
    }
    case NtsMessageType::UE_NAS_TO_NAS: {
        auto &w = dynamic_cast<NmUeNasToNas &>(*msg);
        switch (w.present)
        {
        case NmUeNasToNas::PERFORM_MM_CYCLE: {
            mm->handleNasEvent(w);
            break;
        }
        case NmUeNasToNas::NAS_TIMER_EXPIRE: {
            if (w.timer->isMmTimer())
                mm->handleNasEvent(w);
            else
                sm->handleNasEvent(w);
            break;
        }
        default:
            break;
        }
        break;
    }
    case NtsMessageType::UE_APP_TO_NAS: {
        auto &w = dynamic_cast<NmUeAppToNas &>(*msg);
        switch (w.present)
        {
        case NmUeAppToNas::UPLINK_DATA_DELIVERY: {
            sm->handleUplinkDataRequest(w.psi, std::move(w.data));
            break;
        }
        default:
            break;
        }
        break;
    }
    case NtsMessageType::UE_RLS_TO_NAS: {
        auto &w = dynamic_cast<NmUeRlsToNas &>(*msg);
        switch (w.present)
        {
        case NmUeRlsToNas::DATA_PDU_DELIVERY: {
            sm->handleDownlinkDataRequest(w.psi, std::move(w.pdu));
            break;
        }
        }

        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto &w = dynamic_cast<NmTimerExpired &>(*msg);
        int timerId = w.timerId;
        if (timerId == NTS_TIMER_ID_NAS_TIMER_CYCLE)
        {
            setTimer(NTS_TIMER_ID_NAS_TIMER_CYCLE, NTS_TIMER_INTERVAL_NAS_TIMER_CYCLE);
            performTick();
        }
        if (timerId == NTS_TIMER_ID_MM_CYCLE)
        {
            setTimer(NTS_TIMER_ID_MM_CYCLE, NTS_TIMER_INTERVAL_MM_CYCLE);
            mm->handleNasEvent(NmUeNasToNas{NmUeNasToNas::PERFORM_MM_CYCLE});
        }
        break;
    }
    default:
        logger->unhandledNts(*msg);
        break;
    }
}

void NasTask::performTick()
{
    auto sendExpireMsg = [this](UeTimer *timer) {
        auto m = std::make_unique<NmUeNasToNas>(NmUeNasToNas::NAS_TIMER_EXPIRE);
        m->timer = timer;
        push(std::move(m));
    };

    if (timers.t3346.performTick())
        sendExpireMsg(&timers.t3346);
    if (timers.t3396.performTick())
        sendExpireMsg(&timers.t3396);
    if (timers.t3444.performTick())
        sendExpireMsg(&timers.t3444);
    if (timers.t3445.performTick())
        sendExpireMsg(&timers.t3445);
    if (timers.t3502.performTick())
        sendExpireMsg(&timers.t3502);
    if (timers.t3510.performTick())
        sendExpireMsg(&timers.t3510);
    if (timers.t3511.performTick())
        sendExpireMsg(&timers.t3511);
    if (timers.t3512.performTick())
        sendExpireMsg(&timers.t3512);
    if (timers.t3516.performTick())
        sendExpireMsg(&timers.t3516);
    if (timers.t3517.performTick())
        sendExpireMsg(&timers.t3517);
    if (timers.t3519.performTick())
        sendExpireMsg(&timers.t3519);
    if (timers.t3520.performTick())
        sendExpireMsg(&timers.t3520);
    if (timers.t3521.performTick())
        sendExpireMsg(&timers.t3521);
    if (timers.t3525.performTick())
        sendExpireMsg(&timers.t3525);
    if (timers.t3540.performTick())
        sendExpireMsg(&timers.t3540);
    if (timers.t3584.performTick())
        sendExpireMsg(&timers.t3584);
    if (timers.t3585.performTick())
        sendExpireMsg(&timers.t3585);

    sm->onTimerTick();
}

} // namespace nr::ue