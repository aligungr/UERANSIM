// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#pragma once

#include <mutex>
#include <condition_variable>
#include <deque>

template <typename T>
class blocking_queue
{
private:
    std::mutex m_mutex;
    std::condition_variable m_condition;
    std::deque<T> m_queue;

public:
    void push(T const &value)
    {
        {
            std::unique_lock<std::mutex> lock(this->m_mutex);
            m_queue.push_front(value);
        }
        this->m_condition.notify_one();
    }
    T pop()
    {
        std::unique_lock<std::mutex> lock(this->m_mutex);
        this->m_condition.wait(lock, [=] { return !this->m_queue.empty(); });
        T rc(std::move(this->m_queue.back()));
        this->m_queue.pop_back();
        return rc;
    }
};