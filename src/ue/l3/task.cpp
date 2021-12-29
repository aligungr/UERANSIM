#include "task.hpp"

struct TimerId
{
    static constexpr const int MACHINE_CYCLE = 1;
    static constexpr const int TIMER = 2;
};

struct TimerPeriod
{
    static constexpr const int MACHINE_CYCLE = 2500;
    static constexpr const int TIMER = 1000;
};

namespace nr::ue
{

UeL3Task::UeL3Task(TaskBase *base)
{
    m_rrc = std::make_unique<UeRrcLayer>(base);
    m_nas = std::make_unique<NasLayer>(base);
}

void UeL3Task::onStart()
{
    m_rrc->onStart();
    m_nas->onStart();

    setTimer(TimerId::MACHINE_CYCLE, TimerPeriod::MACHINE_CYCLE);
    setTimer(TimerId::TIMER, TimerPeriod::TIMER);
}

void UeL3Task::onQuit()
{
    m_rrc->onQuit();
    m_nas->onQuit();
}

void UeL3Task::onLoop()
{
    auto msg = take();
    if (!msg)
        return;

    if (msg->msgType == NtsMessageType::UE_CYCLE_REQUIRED)
    {
        m_rrc->performCycle();
        m_nas->performCycle();
    }
    else if (msg->msgType == NtsMessageType::TIMER_EXPIRED)
    {
        auto &w = dynamic_cast<NmTimerExpired &>(*msg);
        if (w.timerId == TimerId::MACHINE_CYCLE)
        {
            setTimer(TimerId::MACHINE_CYCLE, TimerPeriod::MACHINE_CYCLE);
            m_rrc->performCycle();
            m_nas->performCycle();
        }
        else if (w.timerId == TimerId::TIMER)
        {
            setTimer(TimerId::TIMER, TimerPeriod::TIMER);
            m_rrc->performCycle();
            m_nas->performCycle();
        }
    }
    else if (msg->msgType == NtsMessageType::UE_RLS_TO_RRC)
    {
        m_rrc->handleRlsSapMessage(dynamic_cast<NmUeRlsToRrc &>(*msg));
    }
    else if (msg->msgType == NtsMessageType::UE_RRC_TO_NAS)
    {
        m_nas->handleSapMessage(std::move(msg));
    }
    else if (msg->msgType == NtsMessageType::UE_TUN_TO_APP || msg->msgType == NtsMessageType::UE_RLS_TO_NAS)
    {
        m_nas->handleSapMessage(std::move(msg));
    }
}

UeRrcLayer &UeL3Task::rrc()
{
    return *m_rrc;
}

NasLayer &UeL3Task::nas()
{
    return *m_nas;
}

} // namespace nr::ue