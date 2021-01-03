#pragma once

#include <atomic>
#include <chrono>
#include <condition_variable>
#include <deque>
#include <mutex>
#include <queue>
#include <scoped_thread.hpp>
#include <thread>
#include <vector>

enum class NtsMessageType
{
    UNDEFINED = 0,
    TIMER_EXPIRED = 1,
    RESERVED_END = 1000,

    // Start of implementation specific types

    SCTP_CONNECTION_REQUEST,
    SCTP_ASSOCIATION_SETUP,
    SCTP_ASSOCIATION_SHUTDOWN,
    SCTP_CLIENT_RECEIVE,
    SCTP_UNHANDLED_NOTIFICATION_RECEIVE,
    SCTP_CONNECTION_CLOSE,
    SCTP_SEND_MESSAGE,
};

struct NtsMessage
{
    const NtsMessageType msgType;

    explicit NtsMessage(NtsMessageType msgType) : msgType(msgType)
    {
    }

    virtual ~NtsMessage() = default;
};

struct NwTimerExpired : NtsMessage
{
    int timerId;

    explicit NwTimerExpired(int timerId) : NtsMessage(NtsMessageType::TIMER_EXPIRED), timerId(timerId)
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
class NtsTask
{
  private:
    std::deque<NtsMessage *> msgQueue{};
    TimerBase timerBase{};
    std::mutex mutex{};
    std::condition_variable cv{};
    std::atomic<bool> isQuiting{};
    std::thread thread;

  public:
    NtsTask() = default;

    virtual ~NtsTask() = default;

    // NtsTask takes the ownership of NtsMessage* after somebody pushes the message.
    bool push(NtsMessage *msg);

    // NtsTask takes the ownership of NtsMessage* after somebody pushes the message.
    bool pushFront(NtsMessage *msg);

    bool setTimer(int timerId, int64_t delayMs);

    bool setTimerAbsolute(int timerId, int64_t timeMs);

  protected:
    // NtsTask gives the ownership of NtsMessage* to the taker (actually almost always it's its itself)
    NtsMessage *poll();

    // NtsTask gives the ownership of NtsMessage* to the taker (actually almost always it's its itself)
    NtsMessage *poll(int64_t timeout);

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
};