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
    : timerCode(timerCode), mmTimer(isMmTimer), interval(defaultInterval), startMillis(0), running(false),
      _lastDebugPrintMs(0)
{
}

bool NasTimer::isRunning() const
{
    return running;
}

int NasTimer::getCode() const
{
    return timerCode;
}

bool NasTimer::isMmTimer() const
{
    return mmTimer;
}

void NasTimer::start()
{
    startMillis = utils::CurrentTimeMillis();
    running = true;
}

void NasTimer::start(const nas::IEGprsTimer2 &v)
{
    interval = v.value;
    startMillis = utils::CurrentTimeMillis();
    running = true;
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
    running = true;
}

void NasTimer::stop()
{
    if (running)
    {
        startMillis = utils::CurrentTimeMillis();
        running = false;
    }
}

bool NasTimer::performTick()
{
    if (running)
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

int NasTimer::getInterval() const
{
    return interval;
}

int NasTimer::getRemaining() const
{
    if (!running)
        return 0;

    long elapsed = utils::CurrentTimeMillis() - startMillis;
    return static_cast<int>(std::max(interval - elapsed, 0L));
}

} // namespace nas
