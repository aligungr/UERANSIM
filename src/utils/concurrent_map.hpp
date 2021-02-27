//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
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