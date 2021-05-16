//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include <mutex>

template <typename T>
class Locked
{
  private:
    T m_value;
    std::recursive_mutex m_mutex;

  public:
    Locked() : m_value{}, m_mutex{}
    {
    }

    explicit Locked(T value) : m_value{value}, m_mutex{}
    {
    }

    static_assert(!std::is_reference<T>::value);

    Locked(const T &) = delete;
    Locked(T &&) = delete;

    Locked &operator=(const Locked &) = delete;
    Locked &operator=(Locked &&) = delete;

    template <typename Func>
    inline void access(Func fun)
    {
        // Şimdilik access ve mutate aynı, optimizasyon adına read-write lock kullanılabilir
        mutate(fun);
    }

    template <typename Func>
    inline void mutate(Func fun)
    {
        std::lock_guard lk(m_mutex);
        fun(m_value);
    }

    inline T get()
    {
        T copy{};
        access([&copy](auto &value) { copy = value; });
        return copy;
    }

    inline void set(const T &value)
    {
        mutate([&value](auto &v) { v = value; });
    }
};