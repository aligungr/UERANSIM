//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <initializer_list>
#include <unordered_map>
#include <utility>
#include <vector>

template <typename TKey, typename TValue>
class OrderedMap
{
  private:
    std::unordered_map<TKey, TValue> m_map{};
    std::vector<TKey> m_keyOrder{};

  public:
    OrderedMap() = default;

    OrderedMap(std::initializer_list<std::pair<TKey, TValue>> initList)
    {
        for (auto &pair : initList)
        {
            if (!m_map.count(pair.first))
                m_keyOrder.push_back(pair.first);
            m_map.insert(pair);
        }
    }

    inline auto count(const TKey &key) const
    {
        return m_map.count(key);
    }

    inline TValue &operator[](const TKey &key)
    {
        return m_map.at(key);
    }

    inline const TValue &operator[](const TKey &key) const
    {
        return m_map.at(key);
    }

    inline typename std::vector<TKey>::iterator begin()
    {
        return m_keyOrder.begin();
    }

    inline typename std::vector<TKey>::const_iterator begin() const
    {
        return m_keyOrder.begin();
    }

    inline typename std::vector<TKey>::iterator end()
    {
        return m_keyOrder.end();
    }

    inline typename std::vector<TKey>::const_iterator end() const
    {
        return m_keyOrder.end();
    }
};