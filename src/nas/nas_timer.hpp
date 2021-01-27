//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "nas_ie4.hpp"

namespace nas
{

class NasTimer
{
  private:
    const int timerCode;
    const bool mmTimer;

    int interval;
    long startMillis;
    bool running;
    long _lastDebugPrintMs;

  public:
    NasTimer(int timerCode, bool isMmTimer, int defaultInterval);

  public:
    void start();
    void start(const IEGprsTimer2 &v);
    void start(const IEGprsTimer3 &v);
    void stop();
    bool performTick();
    [[nodiscard]] bool isRunning() const;
    [[nodiscard]] int getCode() const;
    [[nodiscard]] bool isMmTimer() const;
    [[nodiscard]] int getInterval() const;
    [[nodiscard]] int getRemaining() const;
};

} // namespace nas