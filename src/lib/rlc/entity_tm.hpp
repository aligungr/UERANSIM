//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "rlc.hpp"
#include "utils.hpp"

#include <utils/linked_list.hpp>

namespace rlc
{

class TmEntity : public IRlcEntity
{
    // Configurations
    int txMaxSize;

    // TX buffer
    int txCurrentSize;
    LinkedList<RlcSduSegment> txBuffer;

    // Timers
    int64_t tCurrent; // Not a timer, but holds the current time in ms.

  public:
    TmEntity(IRlcConsumer *pConsumer, int txMaxSize);
    ~TmEntity() override;

  private:
    void clearEntity();

  public:
    void receivePdu(uint8_t *data, int size) override;
    void receiveSdu(uint8_t *data, int size, int sduId) override;
    int createPdu(uint8_t *buffer, int maxSize) override;
    void timerCycle(int64_t currentTime) override;
    void discardSdu(int sduId) override;
    void reestablishment() override;
    void calculateDataVolume(RlcDataVolume &volume) override;

    int64_t debug_getCurrentTime() override
    {
        return tCurrent;
    }
};

} // namespace rlc
