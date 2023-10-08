//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "timer.hpp"

#include <sstream>

#include <utils/common.hpp>

UeTimer::UeTimer(int timerCode, bool isMmTimer, int defaultInterval)
    : m_code(timerCode), m_isMm(isMmTimer), m_interval(defaultInterval), m_startMillis(0), m_isRunning(false),
      m_expiryCount(0), m_lastDebugPrintMs(0)
{
}

bool UeTimer::isRunning() const
{
    return m_isRunning;
}

int UeTimer::getCode() const
{
    return m_code;
}

bool UeTimer::isMmTimer() const
{
    return m_isMm;
}

void UeTimer::start(bool clearExpiryCount)
{
    if (clearExpiryCount)
        resetExpiryCount();
    m_startMillis = utils::CurrentTimeMillis();
    m_isRunning = true;
}

void UeTimer::start(const nas::IEGprsTimer2 &v, bool clearExpiryCount)
{
    if (clearExpiryCount)
        resetExpiryCount();
    m_interval = v.value;
    m_startMillis = utils::CurrentTimeMillis();
    m_isRunning = true;
}

void UeTimer::start(const nas::IEGprsTimer3 &v, bool clearExpiryCount)
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

void UeTimer::stop(bool clearExpiryCount)
{
    if (clearExpiryCount)
        resetExpiryCount();

    if (m_isRunning)
    {
        m_startMillis = utils::CurrentTimeMillis();
        m_isRunning = false;
    }
}

bool UeTimer::performTick()
{
    if (m_isRunning)
    {
        int64_t currentMs = utils::CurrentTimeMillis();
        int64_t deltaSec = (currentMs - m_startMillis) / 1000LL;
        int64_t remainingSec = m_interval - deltaSec;

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

int UeTimer::getInterval() const
{
    return m_interval;
}

int UeTimer::getRemaining() const
{
    if (!m_isRunning)
        return 0;

    int elapsed = static_cast<int>((utils::CurrentTimeMillis() - m_startMillis) / 1000LL);
    return std::max(m_interval - elapsed, 0);
}

void UeTimer::resetExpiryCount()
{
    m_expiryCount = 0;
}

int UeTimer::getExpiryCount() const
{
    return m_expiryCount;
}

Json ToJson(const UeTimer &v)
{
    std::stringstream ss{};
    if (v.isRunning())
        ss << "rem[" << v.getRemaining() << "] int[" << v.getInterval() << "]";
    else
        ss << ".";

    return ss.str();
}
