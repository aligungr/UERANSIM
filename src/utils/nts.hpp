//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "scoped_thread.hpp"

#include <atomic>
#include <chrono>
#include <condition_variable>
#include <deque>
#include <memory>
#include <mutex>
#include <queue>
#include <thread>
#include <vector>

enum class NtsMessageType
{
    UNDEFINED = 0,
    TIMER_EXPIRED = 1,
    RESERVED_END = 1000,

    // Start of implementation specific types
    GNB_STATUS_UPDATE,
    GNB_CLI_COMMAND,
    UE_STATUS_UPDATE,
    UE_CLI_COMMAND,
    UE_CTL_COMMAND,

    UDP_SERVER_RECEIVE,
    CLI_SEND_RESPONSE,

    GNB_RLS_TO_RRC,
    GNB_RLS_TO_GTP,
    GNB_GTP_TO_RLS,
    GNB_RRC_TO_RLS,
    GNB_RLS_TO_RLS,
    GNB_NGAP_TO_RRC,
    GNB_RRC_TO_NGAP,
    GNB_NGAP_TO_GTP,
    GNB_SCTP,

    UE_APP_TO_TUN,
    UE_APP_TO_NAS,
    UE_TUN_TO_APP,
    UE_RRC_TO_NAS,
    UE_NAS_TO_RRC,
    UE_RRC_TO_RLS,
    UE_RRC_TO_RRC,
    UE_NAS_TO_NAS,
    UE_RLS_TO_RRC,
    UE_RLS_TO_NAS,
    UE_RLS_TO_RLS,
    UE_NAS_TO_APP,
    UE_NAS_TO_RLS,
};

struct NtsMessage
{
    const NtsMessageType msgType;

    explicit NtsMessage(NtsMessageType msgType) : msgType(msgType)
    {
    }

    virtual ~NtsMessage() = default;
};

struct NmTimerExpired : NtsMessage
{
    int timerId;

    explicit NmTimerExpired(int timerId) : NtsMessage(NtsMessageType::TIMER_EXPIRED), timerId(timerId)
    {
    }
};

struct TimerInfo
{
    int timerId{};
    int64_t start{};
    int64_t end{};
};

class TimerBase
{
    struct TimerInfoComparator
    {
        bool operator()(TimerInfo *a, TimerInfo *b)
        {
            return a->end >= b->end;
        }
    };

    std::priority_queue<TimerInfo *, std::vector<TimerInfo *>, TimerInfoComparator> timerQueue{};

  public:
    ~TimerBase();

    void setTimerAbsolute(int timerId, int64_t timeMs);

    TimerInfo *getAndRemoveExpiredTimer();

    int64_t getNextWaitTime();
};

// TODO: Limit queue size?
// todo: message priority, especially control plane messages should have more priorty in appTask etc
class NtsTask
{
  private:
    std::deque<std::unique_ptr<NtsMessage>> msgQueue{};
    TimerBase timerBase{};
    std::mutex mutex{};
    std::condition_variable cv{};
    std::atomic_bool isQuiting{};
    std::atomic_int pauseReqCount{};
    std::atomic_bool pauseConfirmed{};
    std::thread thread;

  public:
    NtsTask() = default;

    virtual ~NtsTask() = default;

    bool push(std::unique_ptr<NtsMessage> &&msg);
    bool pushFront(std::unique_ptr<NtsMessage> &&msg);
    bool setTimer(int timerId, int64_t delayMs);
    bool setTimerAbsolute(int timerId, int64_t timeMs);

  protected:
    std::unique_ptr<NtsMessage> poll();
    std::unique_ptr<NtsMessage> poll(int64_t timeout);
    std::unique_ptr<NtsMessage> take();

  protected:
    // Called exactly once after start() called and before onLoop() callbacks.
    virtual void onStart() = 0;

    // Called in (almost) endless onLoop until quit.
    virtual void onLoop() = 0;

    // Called exactly once after quit() called. It is guaranteed that onLoop() is never be called after onQuit()
    virtual void onQuit() = 0;

  public:
    // - NTS task starts with this function.
    // - Calling start() multiple times is undefined behaviour.
    // - This function is executed by the caller as blocking.
    void start();

    // - NTS task begins to be stopped after called this function. The task may stop after some delay. (usually
    // WAIT_TIME_IF_NO_TIMER).
    // - Caller always blocked until the thread completely exit. Therefore if onLoop function does not terminate, then
    // this function never returns.
    // - Always call this function before destroying the task.
    // - Calling quit() before calling start() is undefined behaviour.
    void quit();

    // - NTS task begin to be paused. The pause request is confirmed after the next loop() call.
    // - If the task quits immediately and loop() never called again, this pause request will never be confirmed.
    // - This should not be used if the task is quiting or quited.
    void requestPause();

    // - NTS task begin to be un-paused. The un-pause request is confirmed after the next loop() call.
    // - This should not be used if the task is quiting or quited.
    void requestUnpause();

    // - Returns true iff pause was requested and now is confirmed.
    bool isPauseConfirmed();
};