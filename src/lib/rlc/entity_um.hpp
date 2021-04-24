//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "rlc.hpp"
#include "utils.hpp"

#include <utils/linked_list.hpp>

namespace rlc
{

class UmEntity : public IRlcEntity
{
    // Configurations
    int snLength;
    int snModulus;
    int windowSize;
    int txMaxSize;
    int rxMaxSize;

    // TX buffer
    int txCurrentSize;
    LinkedList<RlcSduSegment> txBuffer;

    // TX state variables
    int txNext; // SN to be assigned for the next newly generated UMD PDU with segment

    // RX buffer
    int rxCurrentSize;
    LinkedList<UmdPdu> rxBuffer;

    // RX state variables
    int rxNextReassembly; // Earliest SN that is still considered for reassembly
    int rxNextHighest;    // SN of the UMD PDU with the highest SN among received UMD PDUs
    int rxTimerTrigger;   // The SN which triggered t-Reassembly

    // Timers
    int64_t tCurrent; // Not a timer, but holds the current time in ms.
    RlcTimer reassemblyTimer;

  public:
    UmEntity(IRlcConsumer *consumer, int snLength, int tReassemblyPeriod, int txMaxSize, int rxMaxSize);
    ~UmEntity() override;

  private:
    /* Initialization related */
    void clearEntity();

    /* Utils */
    [[nodiscard]] int modulusRx(int num) const;
    [[nodiscard]] int snCompareRx(int a, int b) const;

    /* Actions */
    void actionsOnReception(UmdPdu &pdu);
    void actionsOnReassemblyTimerExpired();

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
