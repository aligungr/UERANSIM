//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>
#include <cstdlib>

namespace rlc
{

struct IRlcConsumer;
struct RlcDataVolume;

class IRlcEntity
{
  protected:
    IRlcConsumer *consumer;

  public:
    explicit IRlcEntity(IRlcConsumer *pConsumer) : consumer(pConsumer)
    {
    }

    virtual ~IRlcEntity() = default;
    virtual void receivePdu(uint8_t *data, int size) = 0;
    virtual void receiveSdu(uint8_t *data, int size, int sduId) = 0;
    virtual int createPdu(uint8_t *buffer, int maxSize) = 0;
    virtual void timerCycle(int64_t currentTime) = 0;
    virtual void discardSdu(int sduId) = 0;
    virtual void reestablishment() = 0;
    virtual void calculateDataVolume(RlcDataVolume &volume) = 0;

    virtual int64_t debug_getCurrentTime() = 0;
};

struct IRlcConsumer
{
    virtual void deliverSdu(IRlcEntity *entity, uint8_t *data, int size) = 0;
    virtual void maxRetransmissionReached(IRlcEntity *entity) = 0;
    virtual void sduSuccessfulDelivery(IRlcEntity *entity, int sduId) = 0;
};

struct RlcDataVolume
{
    int receptionSize;
    int transmissionSize;
    int retransmissionSize;
    int statusSize;
};

IRlcEntity *NewTmEntity(IRlcConsumer *consumer, int txMaxSize);
IRlcEntity *NewUmEntity(IRlcConsumer *consumer, int snLength, int tReassemblyPeriod, int txMaxSize, int rxMaxSize);
IRlcEntity *NewAmEntity(IRlcConsumer *consumer, int snLength, int txMaxSize, int rxMaxSize, int pollPdu, int pollByte,
                        int maxRetThreshold, int pollRetransmitPeriod, int reassemblyPeriod, int statusProhibitPeriod);

} // namespace rlc
