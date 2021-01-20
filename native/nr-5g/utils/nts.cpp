//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "nts.hpp"

#define WAIT_TIME_IF_NO_TIMER 500

static int64_t currentTimeMs()
{
    auto now = std::chrono::high_resolution_clock::now().time_since_epoch();
    return std::chrono::duration_cast<std::chrono::milliseconds>(now).count();
}

static NtsMessage *timerExpiredMessage(TimerInfo *timerInfo)
{
    return timerInfo ? new NwTimerExpired(timerInfo->timerId) : nullptr;
}

void TimerBase::setTimerAbsolute(int timerId, int64_t timeMs)
{
    auto *timerInfo = new TimerInfo();
    timerInfo->start = currentTimeMs();
    timerInfo->end = timeMs;
    timerInfo->timerId = timerId;

    timerQueue.push(timerInfo);
}

int64_t TimerBase::getNextWaitTime()
{
    if (timerQueue.empty())
        return WAIT_TIME_IF_NO_TIMER;

    auto delta = timerQueue.top()->end - currentTimeMs();
    return delta < 0 ? 0 : delta;
}

TimerInfo *TimerBase::getAndRemoveExpiredTimer()
{
    if (timerQueue.empty())
        return nullptr;

    TimerInfo *timer = timerQueue.top();
    if (timer->end < currentTimeMs())
    {
        timerQueue.pop();
        return timer;
    }

    return nullptr;
}

TimerBase::~TimerBase()
{
    while (!timerQueue.empty())
    {
        delete timerQueue.top();
        timerQueue.pop();
    }
}

bool NtsTask::push(NtsMessage *msg)
{
    std::unique_lock<std::mutex> lock(mutex);
    if (isQuiting)
    {
        delete msg;
        return false;
    }
    msgQueue.push_back(msg);
    cv.notify_one();
    return true;
}

bool NtsTask::pushFront(NtsMessage *msg)
{
    std::unique_lock<std::mutex> lock(mutex);
    if (isQuiting)
    {
        delete msg;
        return false;
    }
    msgQueue.push_front(msg);
    cv.notify_one();
    return true;
}

bool NtsTask::setTimer(int timerId, int64_t delayMs)
{
    return setTimerAbsolute(timerId, currentTimeMs() + delayMs);
}

bool NtsTask::setTimerAbsolute(int timerId, int64_t timeMs)
{
    std::unique_lock<std::mutex> lock(mutex);
    if (isQuiting)
        return false;

    timerBase.setTimerAbsolute(timerId, timeMs);
    cv.notify_one();
    return true;
}

NtsMessage *NtsTask::poll()
{
    std::unique_lock<std::mutex> lock(mutex);
    if (!msgQueue.empty())
    {
        NtsMessage *ret = msgQueue.front();
        msgQueue.pop_front();
        return ret;
    }
    if (isQuiting)
        return nullptr;
    TimerInfo *expiredTimer = timerBase.getAndRemoveExpiredTimer();
    if (expiredTimer != nullptr)
    {
        NtsMessage *msg = timerExpiredMessage(expiredTimer);
        delete expiredTimer;
        return msg;
    }
    return nullptr;
}

NtsMessage *NtsTask::poll(int64_t timeout)
{
    std::unique_lock<std::mutex> lock(mutex);
    if (isQuiting)
        return nullptr;
    if (!msgQueue.empty())
    {
        NtsMessage *ret = msgQueue.front();
        msgQueue.pop_front();
        return ret;
    }
    cv.wait_for(lock, std::chrono::milliseconds(std::min(timerBase.getNextWaitTime(), timeout)));
    if (isQuiting)
        return nullptr;
    if (!msgQueue.empty())
    {
        NtsMessage *ret = msgQueue.front();
        msgQueue.pop_front();
        return ret;
    }
    TimerInfo *expiredTimer = timerBase.getAndRemoveExpiredTimer();
    if (expiredTimer != nullptr)
    {
        NtsMessage *msg = timerExpiredMessage(expiredTimer);
        delete expiredTimer;
        return msg;
    }
    return nullptr;
}

NtsMessage *NtsTask::take()
{
    return poll(WAIT_TIME_IF_NO_TIMER);
}

void NtsTask::start()
{
    std::unique_lock<std::mutex> lock(mutex);

    onStart();

    if (!isQuiting)
    {
        thread = std::thread{[this]() {
            while (true)
            {
                if (this->isQuiting)
                    break;
                this->onLoop();
            }
        }};
    }
}

void NtsTask::quit()
{
    bool expected = false;
    while (!isQuiting.compare_exchange_weak(expected, true, std::memory_order_relaxed, std::memory_order_relaxed))
        return;

    thread.join();

    std::unique_lock<std::mutex> lock(mutex);
    while (!msgQueue.empty())
    {
        NtsMessage *msg = msgQueue.front();
        msgQueue.pop_front();

        // Since we have the ownership at this time, we should delete the messages.
        delete msg;
    }

    onQuit();
}
