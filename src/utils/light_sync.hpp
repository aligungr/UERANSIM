//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "common.hpp"

#include <atomic>
#include <memory>
#include <stdexcept>

template <typename TInput, typename TOutput>
class LightSync
{
    const int64_t m_validUntil;
    const std::unique_ptr<TInput> m_input;
    std::atomic_bool m_processed;
    std::shared_ptr<TOutput> m_output;

  public:
    LightSync(int validForMs, std::unique_ptr<TInput> &&input)
        : m_validUntil{utils::CurrentTimeMillis() + static_cast<int64_t>(validForMs)}, m_input{std::move(input)},
          m_processed{}, m_output{}
    {
        if (validForMs >= 2500)
            throw std::runtime_error("LightSync timeout is too large");
    }

    const TInput &getInput()
    {
        return *m_input;
    }

    bool isValid()
    {
        return utils::CurrentTimeMillis() <= m_validUntil;
    }

    // Only producer can call at most once
    void notifyProcessed(std::unique_ptr<TOutput> &&output)
    {
        if (m_processed)
            throw std::runtime_error("LightSync processed more than once");
        m_output = std::move(output);
        m_processed = true;
    }

    // Only consumer can call at most once
    std::shared_ptr<TOutput> waitForProcess()
    {
        while (true)
        {
            if (!isValid())
                return nullptr;

            if (m_processed)
                return m_output;
        }
    }
};