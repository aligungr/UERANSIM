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

    // Buffers
    private RlcRxBuffer<AmdPdu> rxBuffer;
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

    public static AmEntity newInstance(IRlcConsumer consumer) {
        // TODO
        return null;
    }

    private void clearEntity() {
        // TODO
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

    //======================================================================================================
    //                                          INTERNAL METHODS
    //======================================================================================================

    private boolean windowStalling() {
        return !(snCompareTx(txNextAck, txNext) <= 0
                && snCompareTx(txNext, (txNextAck + windowSize) % snModulus) < 0);
    }

    private int amdPduHeaderSize(ESegmentInfo si) {
        int res = 2;
        if (snLength == 18) res++;
        if (!si.hasFirst()) res += 2;
        return res;
    }

    private RlcSduSegment performSegmentation(RlcSduSegment sdu, int maxSize) {
        int headerSize = amdPduHeaderSize(sdu.si);
        if (headerSize + 1 > maxSize)
            return null;

        int overflowed = headerSize + sdu.size - maxSize;

        sdu.si = sdu.si.asNotLast();
        sdu.size -= overflowed;

        var next = new RlcSduSegment(sdu.sdu);
        next.si = sdu.si.asNotFirst();
        next.size = overflowed;
        next.so = sdu.so + sdu.size;

        return next;
    }

    private boolean soOverlaps(int start1, int end1, int start2, int end2) {
        return start1 < start2 ? (end1 == -1 || end1 >= start2) : (end2 == -1 || start1 <= end2);
    }

    private boolean areAllSiblingSegmentsAreInAck(RlcSduSegment segment) {
        // TODO: recheck this method
        int sn = segment.sdu.sn;

        for (var s : txBuffer.elements()) {
            if (snCompareTx(s.sdu.sn, sn) > 0)
                break;
            if (s.sdu.sn == sn)
                return false;
        }
        for (var s : retBuffer.elements()) {
            if (snCompareTx(s.sdu.sn, sn) > 0)
                break;
            if (s.sdu.sn == sn)
                return false;
        }
        for (var s : waitBuffer.elements()) {
            if (snCompareTx(s.sdu.sn, sn) > 0)
                break;
            if (s.sdu.sn == sn)
                return false;
        }

        return true;
    }

    private void insertToList(LinkedList<RlcSduSegment> list, RlcSduSegment segment) {
        RlcUtils.insertSortedLinkedList(list, segment, (a, b) -> {
            if (a.sdu.sn == b.sdu.sn)
                return Integer.compare(a.so, b.so);
            return snCompareTx(a.sdu.sn, b.sdu.sn);
        });
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
            if (data.get1(0).getBitRangeI(4, 6) != 0) {
                // Discard the control PDU if it is not a STATUS PDU
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

        if (!rxBuffer.hasRoomFor(pdu)) {
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
        if (rxBuffer.isAlreadyReceived(pdu.sn, pdu.so, pdu.data.length)) {
            triggerControl.run();
            return;
        }

        // Place the received AMD PDU in the reception buffer
        rxBuffer.add(pdu);

        // Actions when an AMD PDU is placed in the reception buffer
        actionReception(pdu);

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

    private void actionReception(AmdPdu pdu) {
        int x = pdu.sn;

        // if x >= RX_Next_Highest update RX_Next_Highest to x+ 1.
        if (snCompareRx(x, rxNextHighest) >= 0)
            rxNextHighest = (x + 1) % snModulus;

        if (rxBuffer.isAllSegmentsReceived(x)) {
            // Reassemble the RLC SDU from AMD PDU(s) with SN = x,
            //  remove RLC headers when doing so and deliver the reassembled RLC SDU to upper layer;
            var reassembled = rxBuffer.reassemble(pdu.sn);
            if (reassembled != null) {
                consumer.deliverSdu(this, reassembled);
            }

            // If x = RX_Highest_Status, update RX_Highest_Status to the SN of the first RLC SDU with
            //  SN > current RX_Highest_Status for which not all bytes have been received.
            if (x == rxHighestStatus) {
                int n = rxHighestStatus;
                while (rxBuffer.isDelivered(n)) {
                    n = (n + 1) % snModulus;
                }
                rxHighestStatus = n;
            }

            // If x = RX_Next: update RX_Next to the SN of the first RLC SDU with SN > current RX_Next
            //  for which not all bytes have been received.
            if (x == rxNext) {
                var rxList = rxBuffer.getList();

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
                    (rxNextStatusTrigger == (rxNext + 1) % snModulus && !rxBuffer.hasMissingSegment(rxNext)) ||
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
                    || (rxNextHighest == (rxNext + 1) % snModulus && rxBuffer.hasMissingSegment(rxNext))) {

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
                if (soOverlaps(soStart, soEnd, segment.so, segment.so + segment.size - 1)) {
                    cursor = waitBuffer.removeAndNext(cursor);
                    considerRetransmission(segment, !alreadyRetIncremented.contains(nackSn));
                    alreadyRetIncremented.add(nackSn);
                } else {
                    cursor = cursor.getNext();
                }
            }
        }

        cursor = ackBuffer.getFirst();
        while (cursor != null) {
            var segment = cursor.value;

            if (segment.sdu.sn == nackSn) {
                if (soOverlaps(soStart, soEnd, segment.so, segment.so + segment.size - 1)) {
                    cursor = ackBuffer.removeAndNext(cursor);
                    considerRetransmission(segment, !alreadyRetIncremented.contains(nackSn));
                    alreadyRetIncremented.add(nackSn);
                } else {
                    cursor = cursor.getNext();
                }
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
        while (cursor != null && cursor.value.sdu.sn == txNextAck && areAllSiblingSegmentsAreInAck(cursor.value)) {
            txCurrentSize -= cursor.value.sdu.data.length;
            int sn = cursor.value.sdu.sn;

            consumer.sduSuccessfulDelivery(this, cursor.value.sdu.sduId);

            while (cursor != null && cursor.value.sdu.sn == sn)
                cursor = ackBuffer.removeAndNext(cursor);
            txNextAck = (txNextAck + 1) % snModulus;
        }
    }

    //======================================================================================================
    //                                          SDU RECEIVE RELATED
    //======================================================================================================

    @Override
    public void receiveSdu(OctetString data, int sduId) {
        if (data.length == 0)
            return;

        if (txCurrentSize + data.length > txMaxSize)
            return;

        var sdu = new RlcSdu(sduId, data);
        sdu.sn = -1;
        sdu.retransmissionCount = -1;

        var segment = new RlcSduSegment(sdu);
        segment.size = data.length;
        segment.so = 0;
        segment.si = ESegmentInfo.FULL;

        txCurrentSize += segment.size;
        insertToList(txBuffer, segment);
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
            var missing = findMissingBlock(startSn, startSo, (rxHighestStatus - 1 + snModulus) % snModulus, 0xFFFF);
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
            }
            else {
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

                if (missing.nextSo == -1 || missing.nextSn == -1)
                    break;

                startSn = missing.nextSn;
                startSo = missing.nextSo;
            }
        }

        // Then find the ACK_SN
        int ackSn = rxNext;
        var cursor = rxBuffer.getList().getFirst();
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

        // Finally encode the status PDU
        var stream = new BitOutputStream();
        StatusEncoder.encode(stream, pdu, snLength == 12);
        return stream.toOctetString();
    }

    private MissingBlock findMissingBlock(int startSn, int startSo, int endSn, int endSo) {
        // Start line >= end line ise missin part yok demektir.
        // Aksi halde bakmaya devam edilir:
        // Start line ile kesişen bir segment var mı yok mu?
        // Eğer varsa,
        //    kesişen segment eğer endlinedan önce (<= değil <) bitiyorsa ve bitmiyorsa durum değişir.
        //    Bitiyorsa:
        //        yeni start line söz konusu segmentin bitiş noktası olacak şekilde recursion çağrılır.
        //    Bitmiyorsa:
        //        missin part yok demektir.
        // Eğer yoksa
        //     missing partın başlangıcı start line olur
        //     end noktasından önce ve start linedan sonra bir segment veya segment parçası başlangıcı var mı diye bakılır.
        //     Varsa:
        //        missin partın end noktası start-end line arasındaki starta en yakın ilk segment parçasının in başlangıcı olır
        //     Yoksa:
        //        end line olur
        // Son

        if (snCompareRx(startSn, endSn) > 0 || (snCompareRx(startSn, endSn) == 0 && startSo >= endSo))
            return null;

        var segment = rxBuffer.firstItemIntersecting(startSn, startSn);
        if (segment != null) {
            var endPointSn = segment.value.sn;
            var endPointSo = segment.value.si.requiresSo() ? segment.value.so + segment.value.size() : segment.value.size();

            // An assertion just in case (checking for infinite recursion)
            if (snCompareRx(startSn, endPointSn) == 0 && endPointSo == startSo) {
                throw new IncorrectImplementationException(); // bug found
            }

            return findMissingBlock(endPointSn, endPointSo, endSn, endSo);
        }

        var res = new MissingBlock();
        res.snStart = startSn;
        res.soStart = startSo;

        var cursor = rxBuffer.getList().getFirst();

        while (cursor != null) {
            var val = cursor.value;
            var so = val.si.requiresSo() ? val.so : 0;

            if (snCompareRx(val.sn, startSn) < 0 || (snCompareRx(val.sn, startSn) == 0 && so < startSo)) {
                cursor = cursor.getNext();
            } else {
                break;
            }
        }

        if (cursor == null) {
            res.snEnd = endSn;
            res.soEnd = endSo;

            // There is no next
            res.nextSn = -1;
            res.nextSo = -1;

            return res;
        }

        var startPointSn = cursor.value.sn;
        var startPointSo = cursor.value.si.requiresSo() ? cursor.value.so : 0;

        if (snCompareRx(startPointSn, endSn) > 0 || (snCompareRx(startPointSn, endSn) == 0 && startPointSo > endSo)) {
            res.snEnd = endSn;
            res.soEnd = endSo;

            // There is no next
            res.nextSn = -1;
            res.nextSo = -1;

            return res;
        }

        if (startPointSo != 0) {
            res.snEnd = startPointSn;
            res.soEnd = startPointSo - 1;

            if (cursor.value.si.hasLast()) {
                res.nextSn = (startPointSn + 1) % snModulus;
                res.nextSo = 0;
            } else {
                res.nextSn = startPointSn;
                res.nextSo = (cursor.value.si.requiresSo() ? cursor.value.so : 0) + cursor.value.size();
            }
        } else {
            res.snEnd = (startPointSn - 1 + snModulus) % snModulus;
            res.soEnd = 0xFFFF;

            res.nextSn = startPointSn;
            res.nextSo = 0;
        }
        return res;
    }

    private OctetString createRetPdu(int maxSize) {
        var segment = retBuffer.getFirstElement();
        if (segment == null) {
            return null;
        }

        int headerSize = amdPduHeaderSize(segment.si);

        // Fragmentation is irrelevant since no byte fits the size.
        if (headerSize + 1 > maxSize) {
            return null;
        }

        retBuffer.removeFirst();

        // Perform segmentation if it is needed
        if (headerSize + segment.size > maxSize) {
            var next = performSegmentation(segment, maxSize);
            retBuffer.addFirst(next);
        }

        insertToList(waitBuffer, segment);

        boolean includePoll = pollCheckForTransmission();

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

        int headerSize = amdPduHeaderSize(segment.si);

        // Fragmentation is irrelevant since no byte fits the size.
        if (headerSize + 1 > maxSize) {
            return null;
        }

        segment.sdu.sn = txNext;
        txBuffer.removeFirst();

        // Perform segmentation if it is needed
        if (headerSize + segment.size > maxSize) {
            var next = performSegmentation(segment, maxSize);
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
        else if (pollCheckForTransmission()) {
            includePoll = true;
        }

        if (forcePoll) {
            includePoll = true;
            forcePoll = false;
        }

        return generateAmdForSdu(segment, includePoll);
    }

    private boolean pollCheckForTransmission() {
        // if both the transmission buffer and the retransmission buffer becomes empty (excluding transmitted
        //  RLC SDUs or RLC SDU segments awaiting acknowledgements) after the transmission of the AMD PDU;
        //  or
        //  if no new RLC SDU can be transmitted after the transmission of the AMD PDU (e.g. due to window stalling);
        //  then include a poll in the AMD PDU.

        return (txBuffer.isEmpty() && retBuffer.isEmpty()) || windowStalling();
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

            // (re)start  t-PollRetransmit
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

        if (pollRetransmitTimer.cycle(currentTime)) {
            actionPollRetransmitTimerExpired();
        }
        if (reassemblyTimer.cycle(currentTime)) {
            actionReassemblyTimerExpired();
        }
    }

    private void actionReassemblyTimerExpired() {
        // When t-Reassembly expires, the receiving side of an AM RLC entity shall:

        // update RX_Highest_Status to the SN of the first RLC SDU with
        //  SN >= RX_Next_Status_Trigger for which not all bytes have been received;
        int sn = rxNextStatusTrigger;
        while (rxBuffer.isDelivered(sn))
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
        else if (rxNextHighest == (rxHighestStatus + 1) % snModulus && rxBuffer.hasMissingSegment(rxHighestStatus)) {
            condition = true;
        }

        if (condition) {
            // start t-Reassembly;
            reassemblyTimer.start(tCurrent);
            // set RX_Next_Status_Trigger to RX_Next_Highest.
            rxNextStatusTrigger = rxNextHighest;
        }
    }

    private void actionPollRetransmitTimerExpired() {
        // TODO
    }

    //======================================================================================================
    //                                            OTHER METHODS
    //======================================================================================================

    @Override
    public void discardSdu(int sduId) {
        var cursor = txBuffer.getFirst();
        while (cursor != null && cursor.value.sdu.sduId != sduId) {
            cursor = cursor.getNext();
        }

        // SDU not found, do nothing.
        if (cursor == null)
            return;

        // The SDU is already segmented, do nothing.
        if (cursor.value.si != ESegmentInfo.FULL)
            return;

        // Remove the segment
        txBuffer.remove(cursor);

        // TODO, WARNING: not really sure here because this is not included in the a.i
        txCurrentSize -= cursor.value.size;
    }

    @Override
    public void reestablishment() {
        // TODO
    }

    @Override
    public void deleteEntity() {
        // TODO
    }
}
