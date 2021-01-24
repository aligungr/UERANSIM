//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "nas_timer.hpp"

#include <common.hpp>

namespace nas
{

NasTimer::NasTimer(int timerCode, bool isMmTimer, int defaultInterval)
    : timerCode(timerCode), isMmTimer(isMmTimer), interval(defaultInterval), startMillis(0), isRunning(false),
      _lastDebugPrintMs(0)
{
}

bool NasTimer::running() const
{
    return isRunning;
}

int NasTimer::code() const
{
    return timerCode;
}

bool NasTimer::mmTimer() const
{
    return isMmTimer;
}

void NasTimer::start()
{
    startMillis = utils::CurrentTimeMillis();
    isRunning = true;
}

void NasTimer::start(const nas::IEGprsTimer2 &v)
{
    interval = v.value;
    startMillis = utils::CurrentTimeMillis();
    isRunning = true;
}

void NasTimer::start(const nas::IEGprsTimer3 &v)
{
    int secs = 0;
    int val = v.timerValue;

    if (v.unit == nas::EGprsTimerValueUnit3::MULTIPLES_OF_2SEC)
        secs = val * 2;
    else if (v.unit == nas::EGprsTimerValueUnit3::MULTIPLES_OF_1MIN)
        secs = val * 60;
    else if (v.unit == nas::EGprsTimerValueUnit3::MULTIPLES_OF_10MIN)
        secs = val * 60 * 10;
    else if (v.unit == nas::EGprsTimerValueUnit3::MULTIPLES_OF_1HOUR)
        secs = val * 60 * 60;
    else if (v.unit == nas::EGprsTimerValueUnit3::MULTIPLES_OF_10HOUR)
        secs = val * 60 * 60 * 10;
    else if (v.unit == nas::EGprsTimerValueUnit3::MULTIPLES_OF_30SEC)
        secs = val * 30;
    else if (v.unit == nas::EGprsTimerValueUnit3::MULTIPLES_OF_320HOUR)
        secs = val * 60 * 60 * 320;

    interval = secs;
    startMillis = utils::CurrentTimeMillis();
    isRunning = true;
}

void NasTimer::stop()
{
    if (isRunning)
    {
        startMillis = utils::CurrentTimeMillis();
        isRunning = false;
    }
}

bool NasTimer::performTick()
{
    if (isRunning)
    {
        long currentMs = utils::CurrentTimeMillis();
        long deltaSec = (currentMs - startMillis) / 1000;
        long remainingSec = interval - deltaSec;

        if (currentMs - _lastDebugPrintMs > 10 * 1000)
        {
            _lastDebugPrintMs = currentMs;
            // Log.debug(Tag.TIMER, "NAS Timer %s int:%ss rem:%ss", timerCode, interval, remainingSec);
        }

        if (remainingSec < 0)
        {
            stop();
            return true;
        }
    }
    return false;
}

} // namespace nas