#pragma once

#include <atomic>
#include <chrono>
#include <condition_variable>
#include <deque>
#include <mutex>
#include <queue>
#include <thread>
#include <vector>

enum NtsMessageType : int
{
    UNDEFINED = 0,
    TIMER_EXPIRED = 1,

    RESERVED_END = 1000,
    // The enums are extensible
};

struct NtsMessage
{
    const int msgType;

    explicit NtsMessage(NtsMessageType msgType) : msgType((int)msgType)
    {
    }

    explicit NtsMessage(int msgType) : msgType(msgType)
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
    std::deque<NtsMessage *> msgQueue{};
    TimerBase timerBase{};
    std::mutex mutex{};
    std::condition_variable cv{};
    std::thread thread{};
    std::atomic<bool> isQuiting{};

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
    NtsMessage *take();

    // NtsTask gives the ownership of NtsMessage* to the taker (actually almost always it's its itself)
    NtsMessage *poll();

    // NtsTask gives the ownership of NtsMessage* to the taker (actually almost always it's its itself)
    NtsMessage *poll(int64_t timeout);

  protected:
    // Called exactly once after start() called and before onLoop() callbacks.
    virtual void onStart() = 0;

    // Called in (almost) endless onLoop until quit.
    virtual void onLoop() = 0;

  public:
    // NTS task starts with this function.
    // Calling start() multiple times is undefined behaviour.
    void start();

    // NTS task begins to be quited with this function.
    // - The task is completely quited after an indeterminate time.
    // - The task becomes unusable immediately after calling quit(). It's better to clear all references of this task
    // from all the places before calling quit().
    // - The task 'delete' itself in 'indeterminate' time after calling quit. Therefore all tasks must be allocated
    // with new(). And the pointer must be considered as a dangling pointer immediately after calling quit().
    // - Calling quit() multiple times or calling it before start() is undefined behaviour.
    void quit();
};