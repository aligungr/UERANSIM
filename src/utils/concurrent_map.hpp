//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <mutex>
#include <unordered_map>

#pragma once

template <typename TKey, typename TValue>
class ConcurrentMap
{
  private:
    std::unordered_map<TKey, TValue> m_map{};
    mutable std::recursive_mutex m_mutex{};

  public:
    ConcurrentMap() = default;

  public:
    TValue getOrDefault(const TKey &key)
    {
        std::lock_guard lk(m_mutex);
        if (m_map.count(key) != 0)
            return m_map[key];
        return TValue{};
    }

    void put(const TKey &key, const TValue &value)
    {
        std::lock_guard lk(m_mutex);
        m_map[key] = value;
    }

    template <typename Fun>
    void invokeForeach(const Fun &fun) const
    {
        std::lock_guard lk(m_mutex);
        for (auto i : m_map)
            fun(i);
    }

    void remove(const TKey &key)
    {
        std::lock_guard lk(m_mutex);
        m_map.erase(key);
    }

    size_t removeAndGetSize(const TKey &key)
    {
        std::lock_guard lk(m_mutex);
        m_map.erase(key);
        return m_map.size();
    }
};