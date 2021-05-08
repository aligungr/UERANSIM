//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "timer.hpp"

#include <sstream>

#include <utils/common.hpp>

namespace nas
{

NasTimer::NasTimer(int timerCode, bool isMmTimer, int defaultInterval)
    : m_code(timerCode), m_isMm(isMmTimer), m_interval(defaultInterval), m_startMillis(0), m_isRunning(false),
      m_expiryCount(0), m_lastDebugPrintMs(0)
{
}

bool NasTimer::isRunning() const
{
    return m_isRunning;
}

int NasTimer::getCode() const
{
    return m_code;
}

bool NasTimer::isMmTimer() const
{
    return m_isMm;
}

void NasTimer::start(bool clearExpiryCount)
{
    if (clearExpiryCount)
        resetExpiryCount();
    m_startMillis = utils::CurrentTimeMillis();
    m_isRunning = true;
}

void NasTimer::start(const nas::IEGprsTimer2 &v, bool clearExpiryCount)
{
    if (clearExpiryCount)
        resetExpiryCount();
    m_interval = v.value;
    m_startMillis = utils::CurrentTimeMillis();
    m_isRunning = true;
}

void NasTimer::start(const nas::IEGprsTimer3 &v, bool clearExpiryCount)
{
    if (clearExpiryCount)
        resetExpiryCount();

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

    m_interval = secs;
    m_startMillis = utils::CurrentTimeMillis();
    m_isRunning = true;
}

void NasTimer::stop(bool clearExpiryCount)
{
    if (clearExpiryCount)
        resetExpiryCount();

    if (m_isRunning)
    {
        m_startMillis = utils::CurrentTimeMillis();
        m_isRunning = false;
    }
}

bool NasTimer::performTick()
{
    if (m_isRunning)
    {
        long currentMs = utils::CurrentTimeMillis();
        long deltaSec = (currentMs - m_startMillis) / 1000LL;
        long remainingSec = m_interval - deltaSec;

        if (currentMs - m_lastDebugPrintMs > 10LL * 1000LL)
            m_lastDebugPrintMs = currentMs;

        if (remainingSec <= 0LL)
        {
            stop(false);
            m_expiryCount++;
            return true;
        }
    }
    return false;
}

int NasTimer::getInterval() const
{
    return m_interval;
}

int NasTimer::getRemaining() const
{
    if (!m_isRunning)
        return 0;

    int elapsed = static_cast<int>((utils::CurrentTimeMillis() - m_startMillis) / 1000LL);
    return std::max(m_interval - elapsed, 0);
}

void NasTimer::resetExpiryCount()
{
    m_expiryCount = 0;
}

int NasTimer::getExpiryCount() const
{
    return m_expiryCount;
}

Json ToJson(const NasTimer &v)
{
    std::stringstream ss{};
    if (v.isRunning())
        ss << "rem[" << v.getRemaining() << "] int[" << v.getInterval() << "]";
    else
        ss << ".";

    return ss.str();
}

} // namespace nas
