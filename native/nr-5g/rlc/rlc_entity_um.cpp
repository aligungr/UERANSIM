//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "rlc_entity_um.hpp"
#include "rlc_encoder.hpp"
#include "rlc_func.hpp"

#include <cassert>

#define REASSEMBLE_BUFFER_LEN 32768

//======================================================================================================
//                                      INITIALIZATION RELATED
//======================================================================================================

rlc::UmEntity::UmEntity(rlc::IRlcConsumer *consumer, int snLength, int tReassemblyPeriod, int txMaxSize, int rxMaxSize)
    : IRlcEntity(consumer), snLength(snLength), snModulus(1 << snLength), windowSize((1 << snLength) / 2),
      txMaxSize(txMaxSize), rxMaxSize(rxMaxSize), txCurrentSize(0), txNext(0), rxCurrentSize(0), rxNextReassembly(0),
      rxNextHighest(0), rxTimerTrigger(0), tCurrent(0), reassemblyTimer(tReassemblyPeriod)
{
    assert(snLength == 6 || snLength == 12);

    clearEntity();
}

rlc::UmEntity::~UmEntity()
{
    clearEntity();
}

void rlc::UmEntity::clearEntity()
{
    // discard all RLC SDUs, RLC SDU segments, and RLC PDUs, if any
    txCurrentSize = 0;
    txBuffer.clearAndDelete();
    rxCurrentSize = 0;
    rxBuffer.clearAndDelete();

    // reset all state variables to their initial values.
    txNext = 0;
    rxNextReassembly = 0;
    rxNextHighest = 0;
    rxTimerTrigger = 0;

    // stop and reset all timers;
    tCurrent = 0;
    reassemblyTimer.stop();
}

//======================================================================================================
//                                                UTILS
//======================================================================================================

int rlc::UmEntity::modulusRx(int num) const
{
    int r = num - (rxNextHighest - windowSize);
    if (r < 0)
        r += snModulus;
    return r % snModulus;
}

int rlc::UmEntity::snCompareRx(int a, int b) const
{
    return modulusRx(a) - modulusRx(b);
}

//======================================================================================================
//                                               ACTIONS
//======================================================================================================

void rlc::UmEntity::actionsOnReception(rlc::UmdPdu &pdu)
{
    int x = pdu.sn;

    // If all byte segments with SN = x are received
    if (func::IsAllSegmentsReceived(rxBuffer, x))
    {
        // Reassemble the RLC SDU from all byte segments with SN = x, remove RLC headers and deliver
        //  the reassembled RLC SDU to upper layer.
        uint8_t reassembleBuffer[REASSEMBLE_BUFFER_LEN];
        int reassembled = func::Reassemble(rxBuffer, pdu.sn, reassembleBuffer);
        rxCurrentSize -= reassembled;
        if (reassembled > 0)
            consumer->deliverSdu(this, reassembleBuffer, reassembled);

        // if x = RX_Next_Reassembly, update RX_Next_Reassembly to the SN of the first
        //  SN > current RX_Next_Reassembly that has not been reassembled and delivered to upper layer.
        if (x == rxNextReassembly)
        {
            int n = rxNextReassembly;
            while (func::IsDelivered(rxBuffer, n))
            {
                n = (n + 1) % snModulus;
            }
            rxNextReassembly = n;
        }
    }
    else if (snCompareRx(x, rxNextHighest) >= 0)
    {
        // Update RX_Next_Highest to x + 1;
        rxNextHighest = (x + 1) % snModulus;

        // Discard any UMD PDUs with SN that falls outside of the reassembly window
        rxCurrentSize -= func::DiscardRxPduIf(rxBuffer, [this](int sn) { return snCompareRx(sn, rxNextHighest) >= 0; });

        // If RX_Next_Reassembly falls outside of the reassembly window
        if (snCompareRx(rxNextReassembly, rxNextHighest) >= 0)
        {
            // Set RX_Next_Reassembly to the SN of the first SN >= (RX_Next_Highest – UM_Window_Size)
            //  that has not been reassembled and delivered to upper layer.
            int n = (rxNextHighest - windowSize + snModulus) % snModulus;
            while (func::IsDelivered(rxBuffer, n))
            {
                n = (n + 1) % snModulus;
            }
            rxNextReassembly = n;
        }
    }

    // If t-Reassembly is running
    if (reassemblyTimer.isRunning())
    {
        bool condition = false;

        // if RX_Timer_Trigger <= RX_Next_Reassembly; or
        if (snCompareRx(rxTimerTrigger, rxNextReassembly) <= 0)
        {
            condition = true;
        }
        else if (snCompareRx(rxTimerTrigger, rxNextHighest) > 0)
        {
            // if RX_Timer_Trigger falls outside of the reassembly window and
            //  RX_Timer_Trigger is not equal to RX_Next_Highest; or

            condition = true;
        }
        else if (rxNextHighest == (rxNextReassembly + 1) % snModulus &&
                 !func::HasMissingSegment(rxBuffer, rxNextReassembly))
        {
            // if RX_Next_Highest = RX_Next_Reassembly + 1 and there is no missing byte segment
            //  of the RLC SDU associated with SN = RX_Next_Reassembly before the last byte of
            //  all received segments of this RLC SDU:

            condition = true;
        }

        // ... stop and reset t-Reassembly.
        if (condition)
            reassemblyTimer.stop();
    }

    // If t-Reassembly is not running (includes the case when t-Reassembly is stopped due to actions above)
    if (!reassemblyTimer.isRunning())
    {
        bool condition = false;

        // if RX_Next_Highest > RX_Next_Reassembly + 1; or
        if (snCompareRx(rxNextHighest, (rxNextReassembly + 1) % snModulus) > 0)
        {
            condition = true;
        }
        else if (rxNextHighest == (rxNextReassembly + 1) % snModulus &&
                 func::HasMissingSegment(rxBuffer, rxNextReassembly))
        {
            // if RX_Next_Highest = RX_Next_Reassembly + 1 and there is at least one missing byte segment
            //  of the RLC SDU associated with SN = RX_Next_Reassembly before the last byte of all received
            //  segments of this RLC SDU:

            condition = true;
        }

        if (condition)
        {
            // start t-Reassembly;
            reassemblyTimer.start(tCurrent);
            // set RX_Timer_Trigger to RX_Next_Highest.
            rxTimerTrigger = rxNextHighest;
        }
    }
}

void rlc::UmEntity::actionsOnReassemblyTimerExpired()
{
    // Update RX_Next_Reassembly to the SN of the first SN >= RX_Timer_Trigger that has not been reassembled
    rxNextReassembly = rxTimerTrigger;
    while (func::IsDelivered(rxBuffer, rxNextReassembly))
        rxNextReassembly = (rxNextReassembly + 1) % snModulus;

    // Discard all segments with SN < updated RX_Next_Reassembly
    rxCurrentSize -= func::DiscardRxPduIf(rxBuffer, [this](int sn) { return snCompareRx(sn, rxNextReassembly) < 0; });

    // if RX_Next_Highest > RX_Next_Reassembly + 1;
    if ((snCompareRx(rxNextHighest, (rxNextReassembly + 1) % snModulus) > 0) ||
        // or if RX_Next_Highest = RX_Next_Reassembly + 1 and there is at
        //  least one missing byte segment of the RLC SDU associated with SN = RX_Next_Reassembly
        //  before the last byte of all received segments of this RLC SDU
        (rxNextHighest == rxNextReassembly + 1 && func::HasMissingSegment(rxBuffer, rxNextReassembly)))
    {
        // start t-Reassembly
        reassemblyTimer.start(tCurrent);
        // set RX_Timer_Trigger to RX_Next_Highest
        rxTimerTrigger = rxNextHighest;
    }
}

//======================================================================================================
//                                             BASE FUNCTIONS
//======================================================================================================

void rlc::UmEntity::receivePdu(uint8_t *data, int size)
{
    auto pdu = RlcEncoder::DecodeUmd(data, size, snLength == 6);
    if (pdu == nullptr)
        return;

    pdu->isProcessed = false;

    // If data length == 0, then discard.
    if (pdu->size == 0)
        return;

    // If it is a full SDU, deliver directly.
    if (pdu->si == ESegmentInfo::FULL)
    {
        consumer->deliverSdu(this, pdu->data, pdu->size);
        return;
    }

    // If SO is invalid, then discard.
    if (si::requiresSo(pdu->si) && pdu->so == 0)
        return;

    // If (RX_Next_Highest – UM_Window_Size) <= SN < RX_Next_Reassembly, then discard.
    if (snCompareRx(pdu->sn, rxNextReassembly) < 0)
        return;

    // If no room, then discard.
    if (rxCurrentSize + pdu->size > rxMaxSize)
        return;

    // Place the received UMD PDU in the reception buffer
    rxCurrentSize +=
        func::InsertToRxBuffer(rxBuffer, pdu, ISnCompare([this](int a, int b) { return snCompareRx(a, b); }));

    // Actions when an UMD PDU is placed in the reception buffer (5.2.2.2.3)
    actionsOnReception(*pdu);
}

void rlc::UmEntity::receiveSdu(uint8_t *data, int size, int sduId)
{
    txCurrentSize += func::InsertSduToTransmissionBuffer(data, size, sduId, txBuffer, txCurrentSize, txMaxSize);
}

int rlc::UmEntity::createPdu(uint8_t *buffer, int maxSize)
{
    auto segment = txBuffer.getFirstElement();
    if (segment == nullptr)
        return 0;

    int headerSize = func::UmdPduHeaderSize(snLength, segment->si);

    // Fragmentation is irrelevant since no byte fits the size.
    if (headerSize + 1 > maxSize)
        return 0;

    segment->sdu->sn = txNext;
    txBuffer.removeFirst();

    // Perform segmentation if it is needed
    if (headerSize + segment->size > maxSize)
    {
        auto next = func::UmPerformSegmentation(segment, maxSize, snLength);
        if (next == nullptr)
            return 0;
        txBuffer.addFirst(next);
    }

    if (segment->si == ESegmentInfo::LAST)
        txNext = (txNext + 1) % snModulus;

    txCurrentSize -= segment->size;

    return RlcEncoder::EncodeUmd(buffer, snLength == 6, segment->si, segment->so, segment->sdu->sn,
                                 segment->sdu->data + segment->so, segment->size);
}

void rlc::UmEntity::timerCycle(int64_t currentTime)
{
    tCurrent = currentTime;

    if (reassemblyTimer.cycle(currentTime))
        actionsOnReassemblyTimerExpired();
}

void rlc::UmEntity::discardSdu(int sduId)
{
    auto segment = func::FindFirstSduSegmentWithId(txBuffer, sduId);

    // SDU not found, do nothing.
    if (segment == nullptr)
        return;

    // The SDU is already segmented, do nothing.
    if (segment->value->si != ESegmentInfo::FULL)
        return;

    // Remove the segment
    delete txBuffer.remove(segment);

    // TODO, WARNING: not really sure here because this is not included in the a.i
    txCurrentSize -= segment->value->size;
}

void rlc::UmEntity::reestablishment()
{
    clearEntity();
}

void rlc::UmEntity::calculateDataVolume(rlc::RlcDataVolume &volume)
{
    auto txCalc = [this](const RlcSduSegment *v) { return v->size + func::UmdPduHeaderSize(snLength, v->si); };

    // Calculate TX
    volume.transmissionSize = func::ListSum<RlcSduSegment>(txBuffer, txCalc);

    // Calculate RX
    volume.receptionSize = rxCurrentSize; // An estimation.

    // Others
    volume.retransmissionSize = 0;
    volume.statusSize = 0;
}
