//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "common.hpp"

#include <atomic>
#include <memory>
#include <stdexcept>

template <typename TInput, typename TOutput>
class LightSync
{
  private:
    const int64_t m_validUntilForProducer;
    const int64_t m_validUntilForConsumer;
    const std::unique_ptr<TInput> m_input;
    std::atomic_bool m_processed;
    std::unique_ptr<TOutput> m_output;

  public:
    LightSync(int validForMs, int estimatedProcessMs, std::unique_ptr<TInput> &&input)
        : m_validUntilForProducer{utils::CurrentTimeMillis() + static_cast<int64_t>(validForMs)},
          m_validUntilForConsumer{utils::CurrentTimeMillis() + static_cast<int64_t>(validForMs + estimatedProcessMs)},
          m_input{std::move(input)}, m_processed{}, m_output{}
    {
        if (validForMs >= 2500)
            throw std::runtime_error("LightSync timeout is too large");
        if (estimatedProcessMs >= 50)
            throw std::runtime_error("LightSync estimated process time is too large");
    }

  private:
    bool isExpiredForConsumer()
    {
        return utils::CurrentTimeMillis() > m_validUntilForConsumer;
    }

  public:
    const TInput &input()
    {
        return *m_input;
    }

    bool isExpiredForProducer()
    {
        return utils::CurrentTimeMillis() > m_validUntilForProducer;
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
    std::unique_ptr<TOutput> waitForProcess()
    {
        while (true)
        {
            if (isExpiredForConsumer())
                return nullptr;

            if (m_processed)
            {
                std::unique_ptr<TOutput> output = std::move(m_output);
                m_output = nullptr;
                return output;
            }
        }
    }

    static std::shared_ptr<LightSync<TInput, TOutput>> MakeShared(int validForMs, int estimatedProcessMs,
                                                                  std::unique_ptr<TInput> &&input)
    {
        return std::make_shared<LightSync<TInput, TOutput>>(validForMs, estimatedProcessMs, std::move(input));
    }
};