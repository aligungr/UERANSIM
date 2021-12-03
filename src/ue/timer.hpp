//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <lib/nas/ie4.hpp>
#include <utils/json.hpp>

class UeTimer
{
  private:
    const int m_code;
    const bool m_isMm;

    int m_interval;
    int64_t m_startMillis;
    bool m_isRunning;
    int m_expiryCount;

    int64_t m_lastDebugPrintMs;

  public:
    UeTimer(int timerCode, bool isMmTimer, int defaultInterval);

  public:
    void start(bool clearExpiryCount = true);
    void start(const nas::IEGprsTimer2 &v, bool clearExpiryCount = true);
    void start(const nas::IEGprsTimer3 &v, bool clearExpiryCount = true);
    void stop(bool clearExpiryCount = true);
    void resetExpiryCount();
    bool performTick();
    [[nodiscard]] bool isRunning() const;
    [[nodiscard]] int getCode() const;
    [[nodiscard]] bool isMmTimer() const;
    [[nodiscard]] int getInterval() const;
    [[nodiscard]] int getRemaining() const;
    [[nodiscard]] int getExpiryCount() const;
};

Json ToJson(const UeTimer &v);
