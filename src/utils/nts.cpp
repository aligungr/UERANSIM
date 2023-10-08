//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "nts.hpp"
#include "common.hpp"

#include <stdexcept>

#define WAIT_TIME_IF_NO_TIMER 500
#define PAUSE_POLLING_PERIOD 20

static std::unique_ptr<NtsMessage> TimerExpiredMessage(TimerInfo *timerInfo)
{
    return timerInfo ? std::make_unique<NmTimerExpired>(timerInfo->timerId) : nullptr;
}

void TimerBase::setTimerAbsolute(int timerId, int64_t timeMs)
{
    auto *timerInfo = new TimerInfo();
    timerInfo->start = utils::CurrentTimeMillis();
    timerInfo->end = timeMs;
    timerInfo->timerId = timerId;

    timerQueue.push(timerInfo);
}

int64_t TimerBase::getNextWaitTime()
{
    if (timerQueue.empty())
        return WAIT_TIME_IF_NO_TIMER;

    auto delta = timerQueue.top()->end - utils::CurrentTimeMillis();
    return delta < 0 ? 0 : delta;
}

TimerInfo *TimerBase::getAndRemoveExpiredTimer()
{
    if (timerQueue.empty())
        return nullptr;

    TimerInfo *timer = timerQueue.top();
    if (timer->end < utils::CurrentTimeMillis())
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

bool NtsTask::push(std::unique_ptr<NtsMessage> &&msg)
{
    if (isQuiting)
        return false;

    {
        std::unique_lock<std::mutex> lock(mutex);
        msgQueue.push_back(std::move(msg));
    }

    cv.notify_one();
    return true;
}

bool NtsTask::pushFront(std::unique_ptr<NtsMessage> &&msg)
{
    if (isQuiting)
        return false;

    {
        std::unique_lock<std::mutex> lock(mutex);
        msgQueue.push_front(std::move(msg));
    }

    cv.notify_one();
    return true;
}

bool NtsTask::setTimer(int timerId, int64_t delayMs)
{
    return setTimerAbsolute(timerId, utils::CurrentTimeMillis() + delayMs);
}

bool NtsTask::setTimerAbsolute(int timerId, int64_t timeMs)
{
    if (isQuiting)
        return false;

    {
        std::unique_lock<std::mutex> lock(mutex);
        timerBase.setTimerAbsolute(timerId, timeMs);
    }

    cv.notify_one();
    return true;
}

std::unique_ptr<NtsMessage> NtsTask::poll()
{
    {
        std::unique_lock<std::mutex> lock(mutex);
        if (!msgQueue.empty())
        {
            auto ret = std::move(msgQueue.front());
            msgQueue.pop_front();
            return ret;
        }
    }

    if (isQuiting)
        return nullptr;
    TimerInfo *expiredTimer;

    {
        std::unique_lock<std::mutex> lock(mutex);
        expiredTimer = timerBase.getAndRemoveExpiredTimer();
    }

    if (expiredTimer != nullptr)
    {
        auto msg = TimerExpiredMessage(expiredTimer);
        delete expiredTimer;
        return msg;
    }
    return nullptr;
}

std::unique_ptr<NtsMessage> NtsTask::poll(int64_t timeout)
{
    timeout = std::min(timeout, (int64_t)WAIT_TIME_IF_NO_TIMER);

    if (isQuiting)
        return nullptr;

    {
        std::unique_lock<std::mutex> lock(mutex);
        if (!msgQueue.empty())
        {
            auto ret = std::move(msgQueue.front());
            msgQueue.pop_front();
            return ret;
        }
        cv.wait_for(lock, std::chrono::milliseconds(std::min(timerBase.getNextWaitTime(), timeout)));
    }

    if (isQuiting)
        return nullptr;

    {
        std::unique_lock<std::mutex> lock(mutex);
        if (!msgQueue.empty())
        {
            auto ret = std::move(msgQueue.front());
            msgQueue.pop_front();
            return ret;
        }
    }

    TimerInfo *expiredTimer;
    {
        std::unique_lock<std::mutex> lock(mutex);
        expiredTimer = timerBase.getAndRemoveExpiredTimer();
    }

    if (expiredTimer != nullptr)
    {
        auto msg = TimerExpiredMessage(expiredTimer);
        delete expiredTimer;
        return msg;
    }
    return nullptr;
}

std::unique_ptr<NtsMessage> NtsTask::take()
{
    return poll(WAIT_TIME_IF_NO_TIMER);
}

void NtsTask::start()
{
    onStart();

    if (!isQuiting)
    {
        thread = std::thread{[this]() {
            while (true)
            {
                if (this->isQuiting)
                    break;

                if (pauseReqCount > 0)
                {
                    pauseConfirmed = true;
                    utils::Sleep(PAUSE_POLLING_PERIOD);
                }
                else
                {
                    pauseConfirmed = false;
                    this->onLoop();
                }
            }
        }};
    }
}

void NtsTask::quit()
{
    bool expected = false;
    while (!isQuiting.compare_exchange_weak(expected, true, std::memory_order_relaxed, std::memory_order_relaxed))
        return;

    cv.notify_one();

    if (thread.joinable())
        thread.join();

    {
        std::unique_lock<std::mutex> lock(mutex);
        while (!msgQueue.empty())
            msgQueue.pop_front();
    }

    onQuit();
}

void NtsTask::requestPause()
{
    if (++pauseReqCount < 0)
        throw std::runtime_error("NTS pause overflow");

    if (!isQuiting)
        cv.notify_one();
}

void NtsTask::requestUnpause()
{
    if (--pauseReqCount < 0)
        throw std::runtime_error("NTS un-pause underflow");
}

bool NtsTask::isPauseConfirmed()
{
    return pauseConfirmed;
}
