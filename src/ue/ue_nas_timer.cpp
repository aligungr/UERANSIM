//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_nas_task.hpp"

namespace nr::ue
{

void NasTask::onTimerExpire(nas::NasTimer &timer)
{
    switch (timer.getCode())
    {
    case 3512: {
        if (emulationMode && mmCtx.mmState == EMmState::MM_REGISTERED)
        {
            sendRegistration(nas::ERegistrationType::PERIODIC_REGISTRATION_UPDATING,
                             nas::EFollowOnRequest::FOR_PENDING);
        }
        break;
    }
    case 3346: {
        if (emulationMode && mmCtx.mmSubState == EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE)
        {
            sendRegistration(nas::ERegistrationType::INITIAL_REGISTRATION, nas::EFollowOnRequest::FOR_PENDING);
        }
        break;
    }
    }
}

void NasTask::performTick()
{
    auto sendExpireMsg = [this](nas::NasTimer *timer) { push(new NwNasTimerExpire(timer)); };

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
    if (timers.t3580.performTick())
        sendExpireMsg(&timers.t3580);
    if (timers.t3581.performTick())
        sendExpireMsg(&timers.t3581);
    if (timers.t3582.performTick())
        sendExpireMsg(&timers.t3582);
    if (timers.t3583.performTick())
        sendExpireMsg(&timers.t3583);
    if (timers.t3584.performTick())
        sendExpireMsg(&timers.t3584);
    if (timers.t3585.performTick())
        sendExpireMsg(&timers.t3585);
}

} // namespace nr::ue