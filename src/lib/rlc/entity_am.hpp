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

#include <bitset>

#include <utils/linked_list.hpp>

namespace rlc
{

class AmEntity : public IRlcEntity
{
    using Snset = std::bitset<262144>;

    // Configurations
    int snLength;
    int snModulus;
    int windowSize;
    int txMaxSize;
    int rxMaxSize;
    int pollPdu;  // -1 means inf
    int pollByte; // -1 means inf
    int maxRetThreshold;

    // TX state variables
    int txNext;
    int txNextAck;
    int pollSn;
    int pduWithoutPoll;
    int byteWithoutPoll;

    // TX buffer
    int txCurrentSize;
    LinkedList<RlcSduSegment> txBuffer;

    // RX buffer
    int rxCurrentSize;
    LinkedList<AmdPdu> rxBuffer;

    // Other Buffers
    LinkedList<RlcSduSegment> retBuffer;
    LinkedList<RlcSduSegment> waitBuffer;
    LinkedList<RlcSduSegment> ackBuffer;

    // RX state variables
    int rxNext;
    int rxNextHighest;
    int rxHighestStatus;
    int rxNextStatusTrigger;

    // Custom state variables
    bool statusTriggered;
    bool forcePoll;

    // Timers
    int64_t tCurrent;
    RlcTimer pollRetransmitTimer;
    RlcTimer reassemblyTimer;
    RlcTimer statusProhibitTimer;

  public:
    AmEntity(IRlcConsumer *consumer, int snLength, int txMaxSize, int rxMaxSize, int pollPdu, int pollByte,
             int maxRetThreshold, int pollRetransmitPeriod, int reassemblyPeriod, int statusProhibitPeriod);
    ~AmEntity() override;

  private:
    /* Initialization related */
    void clearEntity();

    /* Utils */
    [[nodiscard]] int modulusRx(int a) const;
    [[nodiscard]] int modulusTx(int a) const;
    [[nodiscard]] int snCompareRx(int a, int b) const;
    [[nodiscard]] int snCompareTx(int a, int b) const;
    bool isInReceiveWindow(int sn);
    bool windowStalling();
    bool pollControlForTransmissionOrRetransmission();

    /* Internal */
    bool areAllSegmentsInAck(int sn);
    int sduListCompare(const RlcSduSegment &a, const RlcSduSegment &b);
    void insertToList(LinkedList<RlcSduSegment> &list, RlcSduSegment *segment);

    /* PDU receive related */
    void receiveAmdPdu(AmdPdu *pdu);
    void actionsOnReception(AmdPdu &pdu);
    void receiveStatusPdu(StatusPdu *pdu);
    void ackReceived(int ackSn);
    void nackReceived(int nackSn, int soStart, int soEnd, Snset &alreadyRetIncremented);
    void considerRetransmission(RlcSduSegment *segment, bool updateRetX);
    void checkForSuccessIndication();

    /* PDU construct related */
    int createStatusPdu(uint8_t *buffer, int maxSize, bool noSideEffect);
    int createRetPdu(uint8_t *buffer, int maxSize);
    int createTxPdu(uint8_t *buffer, int maxSize);
    int generateAmdForSdu(const RlcSduSegment &segment, bool includePoll, uint8_t *buffer);

    /* Timer related */
    void actionsOnReassemblyTimerExpired();
    void actionsOnPollRetransmitTimerExpired();

    /* Data volume related */
    int estimateStatusSize();

  public:
    void receivePdu(uint8_t *data, int size) override;
    int createPdu(uint8_t *buffer, int maxSize) override;
    void timerCycle(int64_t currentTime) override;
    void receiveSdu(uint8_t *data, int size, int sduId) override;
    void discardSdu(int sduId) override;
    void reestablishment() override;
    void calculateDataVolume(RlcDataVolume &volume) override;

    int64_t debug_getCurrentTime() override
    {
        return tCurrent;
    }
};

} // namespace rlc