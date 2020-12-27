/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.entity;

import tr.havelsan.ueransim.app.link.rlc.encoding.AmdEncoder;
import tr.havelsan.ueransim.app.link.rlc.encoding.StatusEncoder;
import tr.havelsan.ueransim.app.link.rlc.interfaces.IRlcConsumer;
import tr.havelsan.ueransim.app.link.rlc.pdu.AmdPdu;
import tr.havelsan.ueransim.app.link.rlc.pdu.StatusPdu;
import tr.havelsan.ueransim.app.link.rlc.utils.*;
import tr.havelsan.ueransim.utils.*;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.HashSet;

public class AmEntity extends RlcEntity {

    // Configurations
    private int snLength;
    private int snModulus;
    private int windowSize;
    private int txMaxSize;
    private int rxMaxSize;
    private int pollPdu;
    private int pollByte;
    private int maxRetThreshold;

    // TX state variables
    private int txNext;
    private int txNextAck;
    private int pollSn;
    private int pduWithoutPoll;
    private int byteWithoutPoll;

    // TX buffer
    private int txCurrentSize;
    private LinkedList<RlcSduSegment> txBuffer;

    // RX buffer
    private int rxCurrentSize;
    private LinkedList<AmdPdu> rxBuffer;

    // Other Buffers
    private LinkedList<RlcSduSegment> retBuffer;
    private LinkedList<RlcSduSegment> waitBuffer;
    private LinkedList<RlcSduSegment> ackBuffer;

    // RX state variables
    private int rxNext;
    private int rxNextHighest;
    private int rxHighestStatus;
    private int rxNextStatusTrigger;

    // Custom state variables
    private boolean statusTriggered;
    private boolean forcePoll;

    // Timers
    private long tCurrent;
    private RlcTimer pollRetransmitTimer;
    private RlcTimer reassemblyTimer;
    private RlcTimer statusProhibitTimer;

    //======================================================================================================
    //                                           INITIALIZATION
    //======================================================================================================

    private AmEntity(IRlcConsumer consumer) {
        super(consumer);
    }

    public static AmEntity newInstance(IRlcConsumer consumer, int snLength, int txMaxSize, int rxMaxSize,
                                       int pollPdu, int pollByte, int maxRetThreshold, int pollRetransmitPeriod,
                                       int reassemblyPeriod, int statusProhibitPeriod) {
        if (snLength != 12 && snLength != 18) {
            throw new IncorrectImplementationException();
        }

        var am = new AmEntity(consumer);
        am.snLength = snLength;
        am.rxMaxSize = rxMaxSize;
        am.txMaxSize = txMaxSize;
        am.pollPdu = pollPdu;
        am.pollByte = pollByte;
        am.maxRetThreshold = maxRetThreshold;
        am.snModulus = 1 << snLength;
        am.windowSize = am.snModulus / 2;
        am.pollRetransmitTimer = new RlcTimer(pollRetransmitPeriod);
        am.reassemblyTimer = new RlcTimer(reassemblyPeriod);
        am.statusProhibitTimer = new RlcTimer(statusProhibitPeriod);
        am.rxBuffer = new LinkedList<>();
        am.txBuffer = new LinkedList<>();
        am.retBuffer = new LinkedList<>();
        am.waitBuffer = new LinkedList<>();
        am.ackBuffer = new LinkedList<>();
        am.clearEntity();
        return am;
    }

    private void clearEntity() {
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
    //                                                UTILS
    //======================================================================================================

    private int modulusRx(int a) {
        int r = a - rxNext;
        return r < 0 ? r + snModulus : r;
    }

    private int modulusTx(int a) {
        int r = a - txNextAck;
        return r < 0 ? r + snModulus : r;
    }

    private int snCompareRx(int a, int b) {
        return modulusRx(a) - modulusRx(b);
    }

    private int snCompareTx(int a, int b) {
        return modulusTx(a) - modulusTx(b);
    }

    private boolean isInReceiveWindow(int sn) {
        return modulusRx(sn) < windowSize;
    }

    private boolean pollControlForTransmissionOrRetransmission() {
        // if both the transmission buffer and the retransmission buffer becomes empty (excluding transmitted
        //  RLC SDUs or RLC SDU segments awaiting acknowledgements) after the transmission of the AMD PDU;
        //  or
        //  if no new RLC SDU can be transmitted after the transmission of the AMD PDU (e.g. due to window stalling);
        //  then include a poll in the AMD PDU.

        return (txBuffer.isEmpty() && retBuffer.isEmpty()) || windowStalling();
    }

    //======================================================================================================
    //                                          INTERNAL METHODS
    //======================================================================================================

    private boolean windowStalling() {
        return !(snCompareTx(txNextAck, txNext) <= 0
                && snCompareTx(txNext, (txNextAck + windowSize) % snModulus) < 0);
    }

    private boolean areAllSegmentsAreInAck(int sn) {
        // If transmission, retransmission and wait buffers don't contain such a segment, then
        // we can say that all segments are in acknowledge buffer.
        // TODO: Optimize this method.
        return !RlcFunc.sduListContainsSn(txBuffer, sn) &&
                !RlcFunc.sduListContainsSn(retBuffer, sn) && !RlcFunc.sduListContainsSn(waitBuffer, sn);
    }

    private int sduListCompare(RlcSduSegment a, RlcSduSegment b) {
        if (a.sdu.sn == b.sdu.sn)
            return Integer.compare(a.so, b.so);
        return snCompareTx(a.sdu.sn, b.sdu.sn);
    }

    private void insertToList(LinkedList<RlcSduSegment> list, RlcSduSegment segment) {
        RlcFunc.insertSortedLinkedList(list, segment, this::sduListCompare);
    }

    //======================================================================================================
    //                                          PDU RECEIVE RELATED
    //======================================================================================================

    @Override
    public void receivePdu(OctetString data) {
        if (data.length == 0)
            return;

        // Data PDU
        if (data.get1(0).getBitB(7)) {
            var amdPdu = AmdEncoder.decode(new OctetInputStream(data), snLength == 12);
            receiveAmdPdu(amdPdu);
        }
        // Control PDU
        else {
            // Discard the control PDU if it is not a STATUS PDU
            if (data.get1(0).getBitRangeI(4, 6) != 0) {
                return;
            }

            var statusPdu = StatusEncoder.decode(new BitInputStream(data), snLength == 12);
            receiveStatusPdu(statusPdu);
        }
    }

    private void receiveAmdPdu(AmdPdu pdu) {
        // 5.3.4 ... if the AMD PDU is to be discarded as specified in clause 5.2.3.2.2; or ....
        Runnable triggerControl = () -> {
            if (pdu.p) {
                statusTriggered = true;
            }
        };

        if (pdu.si.requiresSo() && pdu.so == 0) {
            // Bad SO value, discard PDU.
            triggerControl.run();
            return;
        }

        if (pdu.data.length == 0) {
            // No data, discard PDU.
            triggerControl.run();
            return;
        }

        if (rxCurrentSize + pdu.size() > rxMaxSize) {
            // No room in RX buffer, discard PDU.
            triggerControl.run();
            return;
        }

        // Discard if x falls outside of the receiving window
        if (!isInReceiveWindow(pdu.sn)) {
            triggerControl.run();
            return;
        }

        // if byte segment numbers y to z of the RLC SDU with SN = x have been received before:
        //  discard the received AMD PDU
        if (RlcFunc.isAlreadyReceived(rxBuffer, pdu.sn, pdu.so, pdu.data.length)) {
            triggerControl.run();
            return;
        }

        // Place the received AMD PDU in the reception buffer
        rxCurrentSize += RlcFunc.insertToRxBuffer(rxBuffer, pdu, this::snCompareRx);

        // Actions when an AMD PDU is placed in the reception buffer
        actionsOnReception(pdu);

        // Continue 5.3.4
        if (pdu.p) {
            int v = (rxNext + windowSize) % snModulus;

            // if x < RX_Highest_Status or x >= RX_Next + AM_Window_Size:
            if (snCompareRx(pdu.sn, rxHighestStatus) < 0 || snCompareRx(pdu.sn, v) >= 0) {
                // trigger a STATUS report
                statusTriggered = true;
            } else {
                // delay triggering the STATUS report until x < RX_Highest_Status or x >= RX_Next + AM_Window_Size.
                // TODO: instructions are not clear.
                //  I think we need another state variable and keep looking RX_Highest_Status and RX_Next etc.
                //  but for now trigger immediately.
                statusTriggered = true;
            }
        }
    }

    private void receiveStatusPdu(StatusPdu pdu) {
        // If the STATUS report comprises a positive or negative acknowledgement for the RLC SDU
        //  with sequence number equal to POLL_SN:
        //  -    if    t-PollRetransmit is running:
        //  -    stop and reset t-PollRetransmit.
        if (snCompareTx(pollSn, pdu.ackSn) < 0) {
            pollRetransmitTimer.stop();
        }

        ackReceived(pdu.ackSn);

        var alreadyRetIncremented = new HashSet<Integer>();

        for (var nackBlock : pdu.nackBlocks) {

            int nackSn = nackBlock.nackSn;
            int soStart = nackBlock.soStart;
            int soEnd = nackBlock.soEnd;

            if (soStart == -1 && soEnd == -1) {
                soStart = 0;
                soEnd = -1;
            } else {
                if (soEnd == 0xffff)
                    soEnd = -1;
            }

            int range = nackBlock.nackRange == -1 ? 1 : nackBlock.nackRange;

            for (int i = 0; i < range; i++) {
                nackReceived((nackSn + i) % snModulus, i == 0 ? soStart : 0,
                        i == range - 1 ? soEnd : -1, alreadyRetIncremented);
            }

            // Similar as above
            if (snCompareTx(nackSn, pollSn) <= 0 && snCompareTx(pollSn, (nackSn + range) % snModulus) < 0) {
                pollRetransmitTimer.stop();
            }
        }

        checkForSuccessIndication();
    }

    private void actionsOnReception(AmdPdu pdu) {
        int x = pdu.sn;

        // if x >= RX_Next_Highest update RX_Next_Highest to x+ 1.
        if (snCompareRx(x, rxNextHighest) >= 0)
            rxNextHighest = (x + 1) % snModulus;

        if (RlcFunc.isAllSegmentsReceived(rxBuffer, x)) {
            // Reassemble the RLC SDU from AMD PDU(s) with SN = x,
            //  remove RLC headers when doing so and deliver the reassembled RLC SDU to upper layer;
            var reassembleStream = new OctetOutputStream();
            rxCurrentSize -= RlcFunc.reassemble(rxBuffer, pdu.sn, reassembleStream);
            var reassembled = reassembleStream.toOctetString();
            if (reassembled.length > 0) {
                consumer.deliverSdu(this, reassembled);
            }

            // If x = RX_Highest_Status, update RX_Highest_Status to the SN of the first RLC SDU with
            //  SN > current RX_Highest_Status for which not all bytes have been received.
            if (x == rxHighestStatus) {
                int n = rxHighestStatus;
                while (RlcFunc.isDelivered(rxBuffer, n)) {
                    n = (n + 1) % snModulus;
                }
                rxHighestStatus = n;
            }

            // If x = RX_Next: update RX_Next to the SN of the first RLC SDU with SN > current RX_Next
            //  for which not all bytes have been received.
            if (x == rxNext) {
                var rxList = rxBuffer;

                while (!rxList.isEmpty() && rxList.getFirst().value._isProcessed && rxList.getFirst().value.sn == rxNext) {
                    do {
                        rxList.removeFirst();
                    } while (!rxList.isEmpty() && rxList.getFirst().value.sn == rxNext);
                    rxNext = (rxNext + 1) % snModulus;
                }
            }
        }

        // if t-Reassembly is running
        if (reassemblyTimer.isRunning()) {
            if (rxNextStatusTrigger == rxNext // if RX_Next_Status_Trigger = RX_Next; or
                    ||
                    // if RX_Next_Status_Trigger = RX_Next + 1 and there is no missing byte segment of the SDU
                    //  associated with SN = RX_Next before the last byte of all received segments of this SDU; or
                    (rxNextStatusTrigger == (rxNext + 1) % snModulus && !RlcFunc.hasMissingSegment(rxBuffer, rxNext)) ||
                    // if RX_Next_Status_Trigger falls outside of the receiving window and RX_Next_Status_Trigger
                    //  is not equal to RX_Next + AM_Window_Size:
                    (!isInReceiveWindow(rxNextStatusTrigger) && rxNextStatusTrigger != (rxNext + windowSize) % snModulus)) {

                // Stop and reset t-Reassembly.
                reassemblyTimer.stop();
            }
        }

        // if t-Reassembly is not running (includes the case t-Reassembly is stopped due to actions above)
        if (!reassemblyTimer.isRunning()) {
            // if RX_Next_Highest> RX_Next +1; or
            if (snCompareRx(rxNextHighest, (rxNext + 1) % snModulus) > 0
                    // if RX_Next_Highest = RX_Next + 1 and there is at least one missing byte segment of the SDU
                    //  associated with SN = RX_Next before the last byte of all received segments of this SDU:
                    || (rxNextHighest == (rxNext + 1) % snModulus && RlcFunc.hasMissingSegment(rxBuffer, rxNext))) {

                // Start t-Reassembly
                reassemblyTimer.start(tCurrent);
                // Set RX_Next_Status_Trigger to RX_Next_Highest
                rxNextStatusTrigger = rxNextHighest;
            }
        }
    }

    private void ackReceived(int ackSn) {
        boolean[] alreadyDec = new boolean[32768];

        var cursor = waitBuffer.getFirst();
        while (cursor != null) {
            var segment = cursor.value;

            if (snCompareTx(segment.sdu.sn, ackSn) < 0) {
                cursor = waitBuffer.removeAndNext(cursor);
                insertToList(ackBuffer, segment);
            } else {
                cursor = cursor.getNext();
            }
        }

        cursor = retBuffer.getFirst();
        while (cursor != null) {
            var segment = cursor.value;
            if (snCompareTx(segment.sdu.sn, ackSn) < 0) {

                if (!alreadyDec[segment.sdu.sn]) {
                    segment.sdu.retransmissionCount--;
                    alreadyDec[segment.sdu.sn] = true;
                }

                cursor = retBuffer.removeAndNext(cursor);
                insertToList(ackBuffer, segment);
            } else {
                cursor = cursor.getNext();
            }
        }
    }

    private void nackReceived(int nackSn, int soStart, int soEnd, HashSet<Integer> alreadyRetIncremented) {
        if (snCompareTx(txNextAck, nackSn) > 0 || snCompareTx(nackSn, txNext) >= 0)
            return;

        var cursor = waitBuffer.getFirst();

        while (cursor != null) {
            var segment = cursor.value;
            if (segment.sdu.sn == nackSn) {
                if (RlcFunc.soOverlap(soStart, soEnd, segment.so, segment.so + segment.size - 1)) {
                    cursor = waitBuffer.removeAndNext(cursor);
                    considerRetransmission(segment, !alreadyRetIncremented.contains(nackSn));
                    alreadyRetIncremented.add(nackSn);
                } else {
                    cursor = cursor.getNext();
                }
            } else {
                cursor = cursor.getNext();
            }
        }

        cursor = ackBuffer.getFirst();
        while (cursor != null) {
            var segment = cursor.value;

            if (segment.sdu.sn == nackSn) {
                if (RlcFunc.soOverlap(soStart, soEnd, segment.so, segment.so + segment.size - 1)) {
                    cursor = ackBuffer.removeAndNext(cursor);
                    considerRetransmission(segment, !alreadyRetIncremented.contains(nackSn));
                    alreadyRetIncremented.add(nackSn);
                } else {
                    cursor = cursor.getNext();
                }
            } else {
                cursor = cursor.getNext();
            }
        }
    }

    private void considerRetransmission(RlcSduSegment segment, boolean updateRetX) {
        if (updateRetX) {
            segment.sdu.retransmissionCount++;
            if (segment.sdu.retransmissionCount >= maxRetThreshold) {
                consumer.maxRetransmissionReached(this);
            }
        }
        insertToList(retBuffer, segment);
    }

    private void checkForSuccessIndication() {
        var cursor = ackBuffer.getFirst();

        // TODO: Currently sequential succ indication, but not immediate.
        while (cursor != null && cursor.value.sdu.sn == txNextAck && areAllSegmentsAreInAck(cursor.value.sdu.sn)) {
            txCurrentSize -= cursor.value.sdu.data.length;
            int sn = cursor.value.sdu.sn;

            consumer.sduSuccessfulDelivery(this, cursor.value.sdu.sduId);

            while (cursor != null && cursor.value.sdu.sn == sn)
                cursor = ackBuffer.removeAndNext(cursor);
            txNextAck = (txNextAck + 1) % snModulus;
        }
    }

    //======================================================================================================
    //                                      CONSTRUCT PDU RELATED
    //======================================================================================================

    @Override
    public OctetString createPdu(int maxSize) {
        // The transmitting side of an AM RLC entity shall prioritize transmission of RLC control PDUs over
        //  AMD PDUs. The transmitting side of an AM RLC entity shall prioritize transmission of AMD PDUs containing
        //  previously transmitted RLC SDUs or RLC SDU segments over transmission of AMD PDUs containing not
        //  previously transmitted RLC SDUs or RLC SDU segments.

        // When STATUS reporting has been triggered, the receiving side of an AM RLC entity shall:
        //  ... At the first transmission opportunity indicated by lower layer, construct a STATUS PDU and submit
        //  it to lower layer ...
        if (statusTriggered && statusProhibitTimer.stoppedOrExpired(tCurrent)) {
            var res = createStatusPdu(maxSize);
            if (res != null)
                return res;
        }

        if (!retBuffer.isEmpty()) {
            var res = createRetPdu(maxSize);
            if (res != null)
                return res;
        }

        return createTxPdu(maxSize);
    }

    private OctetString createStatusPdu(int maxSize) {
        if (maxSize < 3)
            return null;

        var pdu = new StatusPdu();

        int startSn = rxNext;
        int startSo = 0;

        // Find NACK blocks
        while (true) {
            if (snCompareRx(startSn, rxHighestStatus) >= 0)
                break;

            var missing = RlcFunc.findMissingBlock(rxBuffer, startSn, startSo, (rxHighestStatus - 1 + snModulus) % snModulus, 0xFFFF, snModulus);
            if (missing == null)
                break;

            var block = new NackBlock();
            block.nackSn = missing.snStart;
            block.nackRange = missing.snStart == missing.snEnd ? -1 : (missing.snEnd - missing.snStart + snModulus) % snModulus + 1;

            // Since the NACK range is 8-bit. We must limit to 255, and cut if needed.
            if (block.nackRange > 255) {
                if (missing.soStart == 0 && missing.soEnd == 0xFFFF) {
                    block.soStart = -1;
                    block.soEnd = -1;
                } else {
                    block.soStart = missing.soStart;
                    block.soEnd = 0xFFFF;
                }

                block.nackRange = 255;
                startSn = (missing.snStart + 256) % snModulus;
                startSo = 0;

                pdu.nackBlocks.add(block);

                if (pdu.calculatedSize(snLength == 12) > maxSize) {
                    pdu.nackBlocks.remove(pdu.nackBlocks.size() - 1);
                    break;
                }
            } else {
                if (missing.soStart == 0 && missing.soEnd == 0xFFFF) {
                    block.soStart = -1;
                    block.soEnd = -1;
                } else {
                    block.soStart = missing.soStart;
                    block.soEnd = missing.soEnd;
                }

                pdu.nackBlocks.add(block);

                if (pdu.calculatedSize(snLength == 12) > maxSize) {
                    pdu.nackBlocks.remove(pdu.nackBlocks.size() - 1);
                    break;
                }

                if (missing.soNext == -1 && missing.snNext == -1)
                    break;

                startSn = missing.snNext;
                startSo = missing.soNext;
            }
        }

        // Then find the ACK_SN
        int ackSn = rxNext;
        var cursor = rxBuffer.getFirst();
        while (cursor != null) {
            if (snCompareRx(cursor.value.sn, rxHighestStatus) >= 0)
                break;
            if (snCompareRx(cursor.value.sn, rxNext) >= 0) {
                if (cursor.value._isProcessed) {
                    ackSn = (cursor.value.sn + 1) % snModulus;
                }
            }
            cursor = cursor.getNext();
        }

        pdu.ackSn = ackSn;

        // Reset trigger flag and start prohibit timer.
        statusTriggered = false;
        statusProhibitTimer.start(tCurrent);

        // Finally encode the status PDU
        var stream = new BitOutputStream();
        StatusEncoder.encode(stream, pdu, snLength == 12);
        return stream.toOctetString();
    }

    private OctetString createRetPdu(int maxSize) {
        var segment = retBuffer.getFirstElement();
        if (segment == null) {
            return null;
        }

        int headerSize = RlcFunc.amdPduHeaderSize(snLength, segment.si);

        // Fragmentation is irrelevant since no byte fits the size.
        if (headerSize + 1 > maxSize) {
            return null;
        }

        retBuffer.removeFirst();

        // Perform segmentation if it is needed
        if (headerSize + segment.size > maxSize) {
            var next = RlcFunc.amPerformSegmentation(segment, maxSize, snLength);
            retBuffer.addFirst(next);
        }

        insertToList(waitBuffer, segment);

        boolean includePoll = pollControlForTransmissionOrRetransmission();

        if (forcePoll) {
            includePoll = true;
            forcePoll = false;
        }

        return generateAmdForSdu(segment, includePoll);
    }

    private OctetString createTxPdu(int maxSize) {
        if (windowStalling())
            return null;

        var segment = txBuffer.getFirstElement();
        if (segment == null) {
            return null;
        }

        int headerSize = RlcFunc.amdPduHeaderSize(snLength, segment.si);

        // Fragmentation is irrelevant since no byte fits the size.
        if (headerSize + 1 > maxSize) {
            return null;
        }

        segment.sdu.sn = txNext;
        txBuffer.removeFirst();

        // Perform segmentation if it is needed
        if (headerSize + segment.size > maxSize) {
            var next = RlcFunc.amPerformSegmentation(segment, maxSize, snLength);
            txBuffer.addFirst(next);
        }

        if (segment.si.hasLast()) {
            txNext = (txNext + 1) % snModulus;
        }

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
        byteWithoutPoll += segment.size;

        boolean includePoll = false;

        // if PDU_WITHOUT_POLL >= pollPDU; or if BYTE_WITHOUT_POLL >= pollByte: include a poll in the AMD PDU
        if ((pollPdu != -1 && pduWithoutPoll >= pollPdu) || (pollByte != -1 && byteWithoutPoll >= pollByte))
            includePoll = true;
        else if (pollControlForTransmissionOrRetransmission()) {
            includePoll = true;
        }

        if (forcePoll) {
            includePoll = true;
            forcePoll = false;
        }

        return generateAmdForSdu(segment, includePoll);
    }

    private OctetString generateAmdForSdu(RlcSduSegment segment, boolean includePoll) {
        var pdu = new AmdPdu();
        pdu.p = false;
        pdu.si = segment.si;
        pdu.sn = segment.sdu.sn;
        pdu.so = segment.so;
        pdu.data = segment.sdu.data.substring(segment.so, segment.size);

        if (includePoll) {
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

        var stream = new OctetOutputStream();
        AmdEncoder.encode(stream, pdu, snLength == 12);
        return stream.toOctetString();
    }

    //======================================================================================================
    //                                     TIMER RELATED METHODS
    //======================================================================================================

    @Override
    public void timerCycle(long currentTime) {
        tCurrent = currentTime;

        if (pollRetransmitTimer.cycle(currentTime))
            actionsOnPollRetransmitTimerExpired();
        if (reassemblyTimer.cycle(currentTime))
            actionsOnReassemblyTimerExpired();
    }

    private void actionsOnReassemblyTimerExpired() {
        // When t-Reassembly expires, the receiving side of an AM RLC entity shall:

        // update RX_Highest_Status to the SN of the first RLC SDU with
        //  SN >= RX_Next_Status_Trigger for which not all bytes have been received;
        int sn = rxNextStatusTrigger;
        while (RlcFunc.isDelivered(rxBuffer, sn))
            sn = (sn + 1) % snModulus;
        rxHighestStatus = sn;

        boolean condition = false;

        // if RX_Next_Highest> RX_Highest_Status +1
        if (snCompareRx(rxNextHighest, (rxHighestStatus + 1) % snModulus) > 0) {
            condition = true;
        }
        // or if RX_Next_Highest = RX_Highest_Status + 1 and there is at least one missing byte
        //  segment of the SDU associated with SN = RX_Highest_Status before the last byte
        //  of all received segments of this SDU:
        else if (rxNextHighest == (rxHighestStatus + 1) % snModulus && RlcFunc.hasMissingSegment(rxBuffer, rxHighestStatus)) {
            condition = true;
        }

        if (condition) {
            // start t-Reassembly;
            reassemblyTimer.start(tCurrent);
            // set RX_Next_Status_Trigger to RX_Next_Highest.
            rxNextStatusTrigger = rxNextHighest;
        }
    }

    private void actionsOnPollRetransmitTimerExpired() {
        if (pollControlForTransmissionOrRetransmission()) {
            var first = waitBuffer.getFirst();
            if (first != null) {
                var sn = first.value.sdu.sn;

                int initialRetCount = -1;

                var cursor = waitBuffer.getFirst();
                while (cursor != null) {

                    if (initialRetCount == -1) {
                        initialRetCount = cursor.value.sdu.retransmissionCount;
                    }

                    if (snCompareTx(cursor.value.sdu.sn, sn) > 0) {
                        break;
                    }

                    if (snCompareTx(cursor.value.sdu.sn, sn) == 0) {
                        considerRetransmission(cursor.value, cursor.value.sdu.retransmissionCount == initialRetCount);
                    }

                    cursor = cursor.getNext();
                }
            }
        }

        forcePoll = true;
    }

    //======================================================================================================
    //                                            OTHER METHODS
    //======================================================================================================

    @Override
    public void receiveSdu(OctetString data, int sduId) {
        int size = RlcFunc.insertSduToTransmissionBuffer(data, sduId, txBuffer, txCurrentSize, txMaxSize);
        txCurrentSize += size;
    }

    @Override
    public void discardSdu(int sduId) {
        var segment = RlcFunc.findFirstSduSegmentWithId(txBuffer, sduId);

        // SDU not found, do nothing.
        if (segment == null)
            return;

        // The SDU is already segmented, do nothing.
        if (segment.value.si != ESegmentInfo.FULL)
            return;

        // Remove the segment
        txBuffer.remove(segment);

        // TODO, WARNING: not really sure here because this is not included in the a.i
        txCurrentSize -= segment.value.size;
    }

    @Override
    public void reestablishment() {
        clearEntity();
    }

    @Override
    public void deleteEntity() {
        clearEntity();
    }
}
