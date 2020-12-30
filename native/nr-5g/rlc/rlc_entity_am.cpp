#include "rlc_entity_am.hpp"
#include "rlc_encoder.hpp"
#include "rlc_func.hpp"

#include <cassert>

#define REASSEMBLE_BUFFER_LEN 32768

namespace nr::rlc
{

//======================================================================================================
//                                     INITIALIZATION RELATED
//======================================================================================================

AmEntity::AmEntity(IRlcConsumer *consumer, int snLength, int txMaxSize, int rxMaxSize, int pollPdu, int pollByte,
                   int maxRetThreshold, int pollRetransmitPeriod, int reassemblyPeriod, int statusProhibitPeriod)
    : IRlcEntity(consumer), snLength(snLength), snModulus(1 << snLength), windowSize((1 << snLength) / 2),
      txMaxSize(txMaxSize), rxMaxSize(rxMaxSize), pollPdu(pollPdu), pollByte(pollByte),
      maxRetThreshold(maxRetThreshold), txNext(0), txNextAck(0), pollSn(0), pduWithoutPoll(0), byteWithoutPoll(0),
      txCurrentSize(0), txBuffer{}, rxCurrentSize(0), rxBuffer{}, retBuffer{}, waitBuffer{}, ackBuffer{}, rxNext(0),
      rxNextHighest(0), rxHighestStatus(0), rxNextStatusTrigger(0), statusTriggered(false), forcePoll(false),
      tCurrent(0), pollRetransmitTimer(pollRetransmitPeriod), reassemblyTimer(reassemblyPeriod),
      statusProhibitTimer(statusProhibitPeriod)
{
    assert(snLength == 12 || snLength == 18);
    clearEntity();
}

AmEntity::~AmEntity()
{
    clearEntity();
}

void AmEntity::clearEntity()
{
    txNext = 0;
    txNextAck = 0;
    pollSn = 0;
    pduWithoutPoll = 0;
    byteWithoutPoll = 0;

    txCurrentSize = 0;
    txBuffer.clear();

    rxCurrentSize = 0;
    rxBuffer.clear();

    retBuffer.clear();
    waitBuffer.clear();
    ackBuffer.clear();

    rxNext = 0;
    rxNextHighest = 0;
    rxHighestStatus = 0;
    rxNextStatusTrigger = 0;

    statusTriggered = false;
    forcePoll = false;

    tCurrent = 0;
    pollRetransmitTimer.stop();
    reassemblyTimer.stop();
    statusProhibitTimer.stop();
}

//======================================================================================================
//                                              UTILS
//======================================================================================================

int AmEntity::modulusRx(int a) const
{
    int r = a - rxNext;
    return r < 0 ? r + snModulus : r;
}

int AmEntity::modulusTx(int a) const
{
    int r = a - txNextAck;
    return r < 0 ? r + snModulus : r;
}

int AmEntity::snCompareRx(int a, int b) const
{
    return modulusRx(a) - modulusRx(b);
}

int AmEntity::snCompareTx(int a, int b) const
{
    return modulusTx(a) - modulusTx(b);
}

bool AmEntity::isInReceiveWindow(int sn)
{
    return modulusRx(sn) < windowSize;
}

bool AmEntity::windowStalling()
{
    return !(snCompareTx(txNextAck, txNext) <= 0 && snCompareTx(txNext, (txNextAck + windowSize) % snModulus) < 0);
}

bool AmEntity::pollControlForTransmissionOrRetransmission()
{
    return (txBuffer.isEmpty() && retBuffer.isEmpty()) || windowStalling();
}

//======================================================================================================
//                                              INTERNAL
//======================================================================================================

bool AmEntity::areAllSegmentsAreInAck(int sn)
{
    // If transmission, retransmission and wait buffers don't contain such a segment, then
    // we can say that all segments are in acknowledge buffer.
    // TODO: Optimize this method.
    return !func::SduListContainsSn(txBuffer, sn) && !func::SduListContainsSn(retBuffer, sn) &&
           !func::SduListContainsSn(waitBuffer, sn);
}

int AmEntity::sduListCompare(const RlcSduSegment &a, const RlcSduSegment &b)
{
    if (a.sdu->sn == b.sdu->sn)
        return a.so - b.so;
    return snCompareTx(a.sdu->sn, b.sdu->sn);
}

void AmEntity::insertToList(LinkedList<RlcSduSegment> &list, RlcSduSegment *segment)
{
    auto comp = [this](const RlcSduSegment &a, const RlcSduSegment &b) { return sduListCompare(a, b); };
    func::InsertSortedLinkedList(list, segment, ILambdaComparator<RlcSduSegment, decltype(comp)>{comp});
}

//======================================================================================================
//                                          PDU RECEIVE RELATED
//======================================================================================================

void AmEntity::receivePdu(uint8_t *data, int size)
{
    if (size == 0)
        return;

    if (data[0] >> 7)
    {
        auto *pdu = RlcEncoder::DecodeAmd(data, size, snLength == 12);
        if (pdu)
            receiveAmdPdu(pdu);
    }
    else
    {
        if ((data[0] >> 4) & 0b111)
        {
            // Discard the control PDU if it is not a STATUS PDU
            return;
        }

        auto *pdu = RlcEncoder::DecodeStatus(data, size, snLength == 12);
        if (pdu)
            receiveStatusPdu(pdu);
    }
}

void AmEntity::receiveAmdPdu(AmdPdu *pdu)
{
    // 5.3.4 ... if the AMD PDU is to be discarded as specified in clause 5.2.3.2.2; or ....
    auto triggerControl = [this, pdu]() {
        if (pdu->p)
            statusTriggered = true;
    };

    if (si::requiresSo(pdu->si) && pdu->so == 0)
    {
        // Bad SO value, discard PDU.
        triggerControl();
        return;
    }

    if (pdu->size == 0)
    {
        // No data, discard PDU.
        triggerControl();
        return;
    }

    if (rxCurrentSize + pdu->size > rxMaxSize)
    {
        // No room in RX buffer, discard PDU.
        triggerControl();
        return;
    }

    // Discard if x falls outside of the receiving window
    if (!isInReceiveWindow(pdu->sn))
    {
        triggerControl();
        return;
    }

    // if byte segment numbers y to z of the RLC SDU with SN = x have been received before:
    //  discard the received AMD PDU
    if (func::IsAlreadyReceived(rxBuffer, pdu->sn, pdu->so, pdu->size))
    {
        triggerControl();
        return;
    }

    // Place the received AMD PDU in the reception buffer
    rxCurrentSize +=
        func::InsertToRxBuffer(rxBuffer, pdu, ISnCompare([this](int a, int b) { return snCompareRx(a, b); }));

    // Actions when an AMD PDU is placed in the reception buffer
    actionsOnReception(*pdu);

    // Continue 5.3.4
    if (pdu->p)
    {
        int v = (rxNext + windowSize) % snModulus;

        // if x < RX_Highest_Status or x >= RX_Next + AM_Window_Size:
        if (snCompareRx(pdu->sn, rxHighestStatus) < 0 || snCompareRx(pdu->sn, v) >= 0)
        {
            // trigger a STATUS report
            statusTriggered = true;
        }
        else
        {
            // delay triggering the STATUS report until x < RX_Highest_Status or x >= RX_Next + AM_Window_Size.
            // TODO: instructions are not clear.
            //  I think we need another state variable and keep looking RX_Highest_Status and RX_Next etc.
            //  but for now trigger immediately.
            statusTriggered = true;
        }
    }
}

void AmEntity::actionsOnReception(AmdPdu &pdu)
{
    int x = pdu.sn;

    // if x >= RX_Next_Highest update RX_Next_Highest to x+ 1.
    if (snCompareRx(x, rxNextHighest) >= 0)
        rxNextHighest = (x + 1) % snModulus;

    if (func::IsAllSegmentsReceived(rxBuffer, x))
    {
        // Reassemble the RLC SDU from all byte segments with SN = x, remove RLC headers and deliver
        //  the reassembled RLC SDU to upper layer.
        uint8_t reassembleBuffer[REASSEMBLE_BUFFER_LEN];
        int reassembled = func::Reassemble(rxBuffer, pdu.sn, reassembleBuffer);
        rxCurrentSize -= reassembled;
        if (reassembled > 0)
            consumer->deliverSdu(this, reassembleBuffer, reassembled);

        // If x = RX_Highest_Status, update RX_Highest_Status to the SN of the first RLC SDU with
        //  SN > current RX_Highest_Status for which not all bytes have been received.
        if (x == rxHighestStatus)
        {
            int n = rxHighestStatus;
            while (func::IsDelivered(rxBuffer, n))
            {
                n = (n + 1) % snModulus;
            }
            rxHighestStatus = n;
        }

        // If x = RX_Next: update RX_Next to the SN of the first RLC SDU with SN > current RX_Next
        //  for which not all bytes have been received.
        if (x == rxNext)
        {
            while (!rxBuffer.isEmpty() && rxBuffer.getFirst()->value->isProcessed &&
                   rxBuffer.getFirst()->value->sn == rxNext)
            {
                do
                {
                    rxBuffer.removeFirst();
                } while (!rxBuffer.isEmpty() && rxBuffer.getFirst()->value->sn == rxNext);
                rxNext = (rxNext + 1) % snModulus;
            }
        }
    }

    // if t-Reassembly is running
    if (reassemblyTimer.isRunning())
    {
        if (rxNextStatusTrigger == rxNext // if RX_Next_Status_Trigger = RX_Next; or
            ||
            // if RX_Next_Status_Trigger = RX_Next + 1 and there is no missing byte segment of the SDU
            //  associated with SN = RX_Next before the last byte of all received segments of this SDU; or
            (rxNextStatusTrigger == (rxNext + 1) % snModulus && !func::HasMissingSegment(rxBuffer, rxNext)) ||
            // if RX_Next_Status_Trigger falls outside of the receiving window and RX_Next_Status_Trigger
            //  is not equal to RX_Next + AM_Window_Size:
            (!isInReceiveWindow(rxNextStatusTrigger) && rxNextStatusTrigger != (rxNext + windowSize) % snModulus))
        {

            // Stop and reset t-Reassembly.
            reassemblyTimer.stop();
        }
    }

    // if t-Reassembly is not running (includes the case t-Reassembly is stopped due to actions above)
    if (!reassemblyTimer.isRunning())
    {
        // if RX_Next_Highest> RX_Next +1; or
        if (snCompareRx(rxNextHighest, (rxNext + 1) % snModulus) > 0
            // if RX_Next_Highest = RX_Next + 1 and there is at least one missing byte segment of the SDU
            //  associated with SN = RX_Next before the last byte of all received segments of this SDU:
            || (rxNextHighest == (rxNext + 1) % snModulus && func::HasMissingSegment(rxBuffer, rxNext)))
        {

            // Start t-Reassembly
            reassemblyTimer.start(tCurrent);
            // Set RX_Next_Status_Trigger to RX_Next_Highest
            rxNextStatusTrigger = rxNextHighest;
        }
    }
}

void AmEntity::receiveStatusPdu(StatusPdu *pdu)
{
    // If the STATUS report comprises a positive or negative acknowledgement for the RLC SDU
    //  with sequence number equal to POLL_SN:
    //  -    if    t-PollRetransmit is running:
    //  -    stop and reset t-PollRetransmit.
    if (snCompareTx(pollSn, pdu->ackSn) < 0)
        pollRetransmitTimer.stop();

    ackReceived(pdu->ackSn);

    Snset alreadyRetIncremented;

    for (auto &nackBlock : pdu->nackBlocks)
    {
        int nackSn = nackBlock.nackSn;
        int soStart = nackBlock.soStart;
        int soEnd = nackBlock.soEnd;

        if (soStart == -1 && soEnd == -1)
        {
            soStart = 0;
            soEnd = -1;
        }
        else
        {
            if (soEnd == 0xffff)
                soEnd = -1;
        }

        int range = nackBlock.nackRange == -1 ? 1 : nackBlock.nackRange;

        for (int i = 0; i < range; i++)
        {
            nackReceived((nackSn + i) % snModulus, i == 0 ? soStart : 0, i == range - 1 ? soEnd : -1,
                         alreadyRetIncremented);
        }

        // Similar as above
        if (snCompareTx(nackSn, pollSn) <= 0 && snCompareTx(pollSn, (nackSn + range) % snModulus) < 0)
            pollRetransmitTimer.stop();
    }

    checkForSuccessIndication();
}

void AmEntity::ackReceived(int ackSn)
{
    Snset alreadyDec;

    auto cursor = waitBuffer.getFirst();
    while (cursor != nullptr)
    {
        auto segment = cursor->value;
        if (snCompareTx(segment->sdu->sn, ackSn) < 0)
        {
            cursor = waitBuffer.removeAndNext(cursor);
            insertToList(ackBuffer, segment);
        }
        else
        {
            cursor = cursor->getNext();
        }
    }

    cursor = retBuffer.getFirst();
    while (cursor != nullptr)
    {
        auto segment = cursor->value;
        if (snCompareTx(segment->sdu->sn, ackSn) < 0)
        {
            if (!alreadyDec[segment->sdu->sn])
            {
                segment->sdu->retransmissionCount--;
                alreadyDec[segment->sdu->sn] = true;
            }
            cursor = retBuffer.removeAndNext(cursor);
            insertToList(ackBuffer, segment);
        }
        else
        {
            cursor = cursor->getNext();
        }
    }
}

void AmEntity::nackReceived(int nackSn, int soStart, int soEnd, AmEntity::Snset &alreadyRetIncremented)
{
    if (snCompareTx(txNextAck, nackSn) > 0 || snCompareTx(nackSn, txNext) >= 0)
        return;

    auto cursor = waitBuffer.getFirst();

    while (cursor != nullptr)
    {
        auto segment = cursor->value;
        if (segment->sdu->sn == nackSn)
        {
            if (func::SoOverlap(soStart, soEnd, segment->so, segment->so + segment->size - 1))
            {
                cursor = waitBuffer.removeAndNext(cursor);
                considerRetransmission(segment, !alreadyRetIncremented[nackSn]);
                alreadyRetIncremented[nackSn] = true;
            }
            else
            {
                cursor = cursor->getNext();
            }
        }
        else
        {
            cursor = cursor->getNext();
        }
    }

    cursor = ackBuffer.getFirst();
    while (cursor != nullptr)
    {
        auto segment = cursor->value;

        if (segment->sdu->sn == nackSn)
        {
            if (func::SoOverlap(soStart, soEnd, segment->so, segment->so + segment->size - 1))
            {
                cursor = ackBuffer.removeAndNext(cursor);
                considerRetransmission(segment, !alreadyRetIncremented[nackSn]);
                alreadyRetIncremented[nackSn] = true;
            }
            else
            {
                cursor = cursor->getNext();
            }
        }
        else
        {
            cursor = cursor->getNext();
        }
    }
}

void AmEntity::considerRetransmission(RlcSduSegment *segment, bool updateRetX)
{
    if (updateRetX)
    {
        segment->sdu->retransmissionCount++;
        if (segment->sdu->retransmissionCount >= maxRetThreshold)
            consumer->maxRetransmissionReached(this);
    }
    insertToList(retBuffer, segment);
}

void AmEntity::checkForSuccessIndication()
{
    auto cursor = ackBuffer.getFirst();

    // TODO: Currently sequential succ indication, but not immediate.
    while (cursor != nullptr && cursor->value->sdu->sn == txNextAck && areAllSegmentsAreInAck(cursor->value->sdu->sn))
    {
        txCurrentSize -= cursor->value->sdu->size;
        int sn = cursor->value->sdu->sn;

        consumer->sduSuccessfulDelivery(this, cursor->value->sdu->sduId);

        while (cursor != nullptr && cursor->value->sdu->sn == sn)
            cursor = ackBuffer.removeAndNext(cursor);
        txNextAck = (txNextAck + 1) % snModulus;
    }
}

//======================================================================================================
//                                         PDU CONSTRUCT RELATED
//======================================================================================================

int AmEntity::createPdu(uint8_t *buffer, int maxSize)
{
    // The transmitting side of an AM RLC entity shall prioritize transmission of RLC control PDUs over
    //  AMD PDUs. The transmitting side of an AM RLC entity shall prioritize transmission of AMD PDUs containing
    //  previously transmitted RLC SDUs or RLC SDU segments over transmission of AMD PDUs containing not
    //  previously transmitted RLC SDUs or RLC SDU segments.

    // When STATUS reporting has been triggered, the receiving side of an AM RLC entity shall:
    //  ... At the first transmission opportunity indicated by lower layer, construct a STATUS PDU and submit
    //  it to lower layer ...
    if (statusTriggered && statusProhibitTimer.stoppedOrExpired(tCurrent))
    {
        int res = createStatusPdu(buffer, maxSize);
        if (res != 0)
            return res;
    }

    if (!retBuffer.isEmpty())
    {
        int res = createRetPdu(buffer, maxSize);
        if (res != 0)
            return res;
    }

    return createTxPdu(buffer, maxSize);
}

int AmEntity::createStatusPdu(uint8_t *buffer, int maxSize)
{
    if (maxSize < 3)
        return 0;

    StatusPdu pdu;

    int startSn = rxNext;
    int startSo = 0;

    // Find NACK blocks
    while (true)
    {
        if (snCompareRx(startSn, rxHighestStatus) >= 0)
            break;

        auto missing = func::FindMissingBlock(rxBuffer, startSn, startSo, (rxHighestStatus - 1 + snModulus) % snModulus,
                                              0xFFFF, snModulus);
        if (missing == nullptr)
            break;

        NackBlock block{};
        block.nackSn = missing->snStart;
        block.nackRange =
            missing->snStart == missing->snEnd ? -1 : (missing->snEnd - missing->snStart + snModulus) % snModulus + 1;

        // Since the NACK range is 8-bit. We must limit to 255, and cut if needed.
        if (block.nackRange > 255)
        {
            if (missing->soStart == 0 && missing->soEnd == 0xFFFF)
            {
                block.soStart = -1;
                block.soEnd = -1;
            }
            else
            {
                block.soStart = missing->soStart;
                block.soEnd = 0xFFFF;
            }

            block.nackRange = 255;
            startSn = (missing->snStart + 256) % snModulus;
            startSo = 0;

            pdu.nackBlocks.push_back(block);

            if (pdu.calculatedSize(snLength == 12) > maxSize)
            {
                pdu.nackBlocks.pop_back();
                delete missing;
                break;
            }
        }
        else
        {
            if (missing->soStart == 0 && missing->soEnd == 0xFFFF)
            {
                block.soStart = -1;
                block.soEnd = -1;
            }
            else
            {
                block.soStart = missing->soStart;
                block.soEnd = missing->soEnd;
            }

            pdu.nackBlocks.push_back(block);

            if (pdu.calculatedSize(snLength == 12) > maxSize)
            {
                pdu.nackBlocks.pop_back();
                delete missing;
                break;
            }

            if (missing->soNext == -1 && missing->snNext == -1)
            {
                delete missing;
                break;
            }

            startSn = missing->snNext;
            startSo = missing->soNext;
        }

        delete missing;
    }

    // Then find the ACK_SN
    int ackSn = rxNext;
    auto cursor = rxBuffer.getFirst();
    while (cursor != nullptr)
    {
        if (snCompareRx(cursor->value->sn, rxHighestStatus) >= 0)
            break;
        if (snCompareRx(cursor->value->sn, rxNext) >= 0)
        {
            if (cursor->value->isProcessed)
                ackSn = (cursor->value->sn + 1) % snModulus;
        }
        cursor = cursor->getNext();
    }
    pdu.ackSn = ackSn;

    // Reset trigger flag and start prohibit timer.
    statusTriggered = false;
    statusProhibitTimer.start(tCurrent);

    // Finally encode the status PDU
    return RlcEncoder::EncodeStatus(buffer, pdu, snLength == 12);
}

int AmEntity::createRetPdu(uint8_t *buffer, int maxSize)
{
    auto segment = retBuffer.getFirstElement();
    if (segment == nullptr)
        return 0;

    int headerSize = func::AmdPduHeaderSize(snLength, segment->si);

    // Fragmentation is irrelevant since no byte fits the size.
    if (headerSize + 1 > maxSize)
        return 0;

    retBuffer.removeFirst();

    // Perform segmentation if it is needed
    if (headerSize + segment->size > maxSize)
    {
        auto next = func::AmPerformSegmentation(segment, maxSize, snLength);
        if (next == nullptr)
            return 0;
        retBuffer.addFirst(next);
    }

    insertToList(waitBuffer, segment);

    bool includePoll = pollControlForTransmissionOrRetransmission();

    if (forcePoll)
    {
        includePoll = true;
        forcePoll = false;
    }

    return generateAmdForSdu(*segment, includePoll, buffer);
}

int AmEntity::createTxPdu(uint8_t *buffer, int maxSize)
{
    if (windowStalling())
        return 0;

    auto segment = txBuffer.getFirstElement();
    if (segment == nullptr)
        return 0;

    int headerSize = func::AmdPduHeaderSize(snLength, segment->si);

    // Fragmentation is irrelevant since no byte fits the size.
    if (headerSize + 1 > maxSize)
        return 0;

    segment->sdu->sn = txNext;
    txBuffer.removeFirst();

    // Perform segmentation if it is needed
    if (headerSize + segment->size > maxSize)
    {
        auto next = func::AmPerformSegmentation(segment, maxSize, snLength);
        if (next == nullptr)
            return 0;
        txBuffer.addFirst(next);
    }

    if (si::hasLast(segment->si))
        txNext = (txNext + 1) % snModulus;

    insertToList(waitBuffer, segment);

    // 5.3.3.2	Transmission of a AMD PDU
    //  Upon notification of a transmission opportunity by lower layer, for each AMD PDU submitted for
    //   transmission such that the AMD PDU contains either a not previously transmitted RLC SDU or an
    //   RLC SDU segment containing not previously transmitted byte segment, the transmitting side of
    //   an AM RLC entity shall:

    // increment PDU_WITHOUT_POLL by one
    pduWithoutPoll++;

    // increment BYTE_WITHOUT_POLL by every new byte of Data field element
    //  that it maps to the Data field of the AMD PDU;
    byteWithoutPoll += segment->size;

    bool includePoll = false;

    // if PDU_WITHOUT_POLL >= pollPDU; or if BYTE_WITHOUT_POLL >= pollByte: include a poll in the AMD PDU
    if ((pollPdu != -1 && pduWithoutPoll >= pollPdu) || (pollByte != -1 && byteWithoutPoll >= pollByte) ||
        pollControlForTransmissionOrRetransmission()) // or the other control
    {
        includePoll = true;
    }

    if (forcePoll)
    {
        includePoll = true;
        forcePoll = false;
    }

    return generateAmdForSdu(*segment, includePoll, buffer);
}

int AmEntity::generateAmdForSdu(const RlcSduSegment &segment, bool includePoll, uint8_t *buffer)
{
    AmdPdu pdu{};
    pdu.p = false;
    pdu.si = segment.si;
    pdu.sn = segment.sdu->sn;
    pdu.so = segment.so;
    pdu.data = new uint8_t[segment.size];
    pdu.size = segment.size;
    std::memcpy(pdu.data, segment.sdu->data + segment.so, segment.size);

    if (includePoll)
    {
        // To include a poll in an AMD PDU, the transmitting side of an AM RLC entity shall
        // set the P field of the AMD PDU to "1";
        pdu.p = true;

        // set PDU_WITHOUT_POLL to 0;
        pduWithoutPoll = 0;
        // set BYTE_WITHOUT_POLL to 0.
        byteWithoutPoll = 0;

        // set POLL_SN to the highest SN of the AMD PDU among the AMD PDUs submitted to lower layer
        //  TODO: check this later
        pollSn = (txNext - 1 + snModulus) % snModulus;

        // (re)start t-PollRetransmit
        pollRetransmitTimer.start(tCurrent);
    }

    return RlcEncoder::EncodeAmd(buffer, pdu, snLength == 12);
}

//======================================================================================================
//                                          TIMER RELATED
//======================================================================================================

void AmEntity::timerCycle(int64_t currentTime)
{
    tCurrent = currentTime;

    if (pollRetransmitTimer.cycle(currentTime))
        actionsOnPollRetransmitTimerExpired();
    if (reassemblyTimer.cycle(currentTime))
        actionsOnReassemblyTimerExpired();
}

void AmEntity::actionsOnReassemblyTimerExpired()
{
    // When t-Reassembly expires, the receiving side of an AM RLC entity shall:

    // update RX_Highest_Status to the SN of the first RLC SDU with
    //  SN >= RX_Next_Status_Trigger for which not all bytes have been received;
    int sn = rxNextStatusTrigger;
    while (func::IsDelivered(rxBuffer, sn))
        sn = (sn + 1) % snModulus;
    rxHighestStatus = sn;

    bool condition = false;

    // if RX_Next_Highest> RX_Highest_Status +1
    if (snCompareRx(rxNextHighest, (rxHighestStatus + 1) % snModulus) > 0)
    {
        condition = true;
    }
    else if (rxNextHighest == (rxHighestStatus + 1) % snModulus && func::HasMissingSegment(rxBuffer, rxHighestStatus))
    {
        // or if RX_Next_Highest = RX_Highest_Status + 1 and there is at least one missing byte
        //  segment of the SDU associated with SN = RX_Highest_Status before the last byte
        //  of all received segments of this SDU:
        condition = true;
    }

    if (condition)
    {
        // start t-Reassembly;
        reassemblyTimer.start(tCurrent);
        // set RX_Next_Status_Trigger to RX_Next_Highest.
        rxNextStatusTrigger = rxNextHighest;
    }
}

void AmEntity::actionsOnPollRetransmitTimerExpired()
{
    if (pollControlForTransmissionOrRetransmission())
    {
        // 5.3.3.4: Consider the RLC SDU with the highest SN among the RLC SDUs submitted to lower layer for
        // retransmission
        auto item = func::FirstItemWithSn(waitBuffer, (txNext - 1 + snModulus) % snModulus);

        // 5.3.3.4: ... or consider any RLC SDU which has not been positively acknowledged for retransmission.
        if (item == nullptr)
        {
            // The spec says 'any', here we take first one.
            item = waitBuffer.getFirst();
        }

        if (item != nullptr)
        {
            auto sn = item->value->sdu->sn;
            bool alreadyRetIncremented = false;
            auto cursor = waitBuffer.getFirst();

            // Spec says SDU, not segment. Therefore take all segments.
            while (cursor != nullptr)
            {
                if (snCompareTx(cursor->value->sdu->sn, sn) > 0)
                    break;
                if (snCompareTx(cursor->value->sdu->sn, sn) == 0)
                {
                    considerRetransmission(cursor->value, !alreadyRetIncremented);
                    alreadyRetIncremented = true;
                    cursor = waitBuffer.removeAndNext(cursor);
                }
                else
                {
                    cursor = cursor->getNext();
                }
            }
        }
    }

    forcePoll = true;
}

//======================================================================================================
//                                        DATA VOLUME CALCULATION
//======================================================================================================

void AmEntity::calculateDataVolume(RlcDataVolume &volume)
{
    auto txCalc = [this](const RlcSduSegment *v) { return v->size + func::AmdPduHeaderSize(snLength, v->si); };

    // Calculate TX
    volume.transmissionSize = func::ListSum<RlcSduSegment>(txBuffer, txCalc);

    // Calculate RX
    volume.receptionSize = rxCurrentSize; // An estimation.

    // Calculate RETX
    volume.retransmissionSize = func::ListSum<RlcSduSegment>(retBuffer, txCalc);

    // Calculate STATUS
    volume.statusSize = estimateStatusSize();
}

int AmEntity::estimateStatusSize()
{
    // TODO
    return 0;
}

//======================================================================================================
//                                         OTHER FUNCTIONS
//======================================================================================================

void AmEntity::receiveSdu(uint8_t *data, int size, int sduId)
{
    txCurrentSize += func::InsertSduToTransmissionBuffer(data, size, sduId, txBuffer, txCurrentSize, txMaxSize);
}

void AmEntity::discardSdu(int sduId)
{
    auto segment = func::FindFirstSduSegmentWithId(txBuffer, sduId);

    // SDU not found, do nothing.
    if (segment == nullptr)
        return;

    // The SDU is already segmented, do nothing.
    if (segment->value->si != ESegmentInfo::FULL)
        return;

    // Remove the segment
    txBuffer.remove(segment);

    // TODO, WARNING: not really sure here because this is not included in the a.i
    txCurrentSize -= segment->value->size;
}

void AmEntity::reestablishment()
{
    clearEntity();
}

} // namespace nr::rlc
