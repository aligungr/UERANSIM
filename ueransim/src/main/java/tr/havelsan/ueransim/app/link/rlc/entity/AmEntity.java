/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.entity;

import tr.havelsan.ueransim.app.link.rlc.IRlcConsumer;
import tr.havelsan.ueransim.app.link.rlc.enums.ESegmentInfo;
import tr.havelsan.ueransim.app.link.rlc.pdu.AmdPdu;
import tr.havelsan.ueransim.app.link.rlc.sdu.RlcSdu;
import tr.havelsan.ueransim.app.link.rlc.sdu.RlcSduSegment;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedList;

public class AmEntity extends RlcEntity {

    // Configurations
    private int snLength;
    private int snModulus;
    private int windowSize;
    private int rxMaxSize;
    private int txMaxSize;
    private int tReassemblyPeriod;
    private int tPollRetransmitPeriod;
    private int tStatusProhibitPeriod;

    // TX state variables
    private int txNextAck;

    // TX buffer
    private int txCurrentSize;
    private LinkedList<RlcSduSegment> txBuffer;

    // Retransmission buffer
    private LinkedList<RlcSduSegment> retBuffer;

    // RX state variables
    private int rxNext;
    private int rxNextHighest;
    private int rxHighestStatus;
    private int rxNextStatusTrigger;

    // RX buffer
    private int rxCurrentSize;
    private LinkedList<AmdPdu> rxBuffer;

    // Custom state variables
    private boolean statusTriggered;

    // Timers
    private long tCurrent;              // Not a timer, but holds the current time in ms.
    private long tPollRetransmitStart;  // Used by the transmitting side of an AM RLC entity in order to retransmit a poll
    private long tReassemblyStart;      // Reassembling timer
    private long tStatusProhibitStart;  // Used by the entity in order to prohibit transmission of a STATUS PDU

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

    private boolean isAlreadyReceived(int sn, int so, int size) {
        if (rxBuffer.isEmpty())
            return false;

        var it = rxBuffer.listIterator();
        var pdu = it.next();

        int n;
        while (pdu != null && size > 0) {
            if (pdu.sn == sn) {
                if (pdu.so <= so && so < pdu.so + pdu.data.length) {
                    n = pdu.data.length - (so - pdu.so);
                    size -= n;
                    so += n;
                } else if (pdu.so <= so + size - 1 && so + size - 1 < pdu.so + pdu.data.length) {
                    n = size - (pdu.so - so);
                    size -= n;
                }
            }
            pdu = it.hasNext() ? it.next() : null;
        }
        return size <= 0;
    }

    private void insertReception(AmdPdu pdu) {
        int index = -1;

        for (int i = 0; i < rxBuffer.size(); i++) {
            var p = rxBuffer.get(i);
            if (p.sn == pdu.sn) {
                if (p.so > pdu.so) {
                    break;
                }
            } else if (snCompareRx(p.sn, pdu.sn) > 0) {
                break;
            }

            index = i;
        }

        rxBuffer.add(index + 1, pdu);
    }

    private int firstIndexOfSn(int sn) {
        int index = -1;

        for (int i = 0; i < rxBuffer.size(); i++) {
            var pdu = rxBuffer.get(i);
            if (pdu.sn == sn) {
                index = i;
                break;
            }
        }

        return index;
    }

    private boolean isAllSegmentsReceived(int sn) {
        int index = firstIndexOfSn(sn);
        if (index == -1)
            return false;

        // WARNING: Check if it is already reassembled and delivered. Returning false if it is
        //  already processes. Because no need to reprocess it. We can consider this method as
        //  "ready to reassemble and deliver" instead of "is all segments received?"
        if (rxBuffer.get(index)._isProcessed)
            return false;

        int maxOffset = -1;

        for (int i = index; i < rxBuffer.size(); i++) {
            var pdu = rxBuffer.get(i);

            if (pdu.so > maxOffset + 1)
                return false;
            if (pdu.si.hasLast())
                return true;

            var endOffset = pdu.so + pdu.data.length - 1;
            if (endOffset > maxOffset)
                maxOffset = endOffset;
        }

        return false;
    }

    private void reassembleAndDeliver(int sn) {
        int startIndex = firstIndexOfSn(sn);

        if (startIndex == -1)
            return;

        int endIndex = startIndex;
        while (endIndex + 1 < rxBuffer.size() && rxBuffer.get(endIndex + 1).sn == sn) {
            endIndex++;
        }

        var output = new OctetOutputStream();

        for (int i = startIndex; i <= endIndex; i++) {
            var pdu = rxBuffer.get(i);
            output.writeOctetString(pdu.data);
            pdu._isProcessed = true;
            rxCurrentSize -= pdu.data.length;
        }

        consumer.deliverSdu(this, output.toOctetString());
    }

    private boolean hasMissingSegment(int sn) {
        int index = firstIndexOfSn(sn);

        if (index == -1) {
            // No such a PDU, therefore we don't have a missing segment.
            return false;
        }

        if (rxBuffer.get(index)._isProcessed) {
            // The related SN is already processed, therefore we don't have a missing segment.
            return false;
        }

        int endOffset = -1;
        while (true) {
            if (index >= rxBuffer.size())
                break;

            var pdu = rxBuffer.get(index);
            if (pdu.sn != sn) {
                break;
            }

            if (pdu.so > endOffset + 1)
                return true;

            int newOffset = pdu.so + pdu.data.length - 1;
            if (newOffset > endOffset)
                endOffset = newOffset;

            index++;
        }

        return false;
    }

    private boolean isDelivered(int sn) {
        for (var pdu : rxBuffer) {
            if (pdu.sn == sn && pdu._isProcessed)
                return true;
        }
        return false;
    }

    private boolean pendingStatusToSend() {
        return statusTriggered &&
                (tStatusProhibitStart == 0 || tCurrent - tStatusProhibitStart > tStatusProhibitPeriod);
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
            var amdPdu = AmdPdu.decode(new OctetInputStream(data), snLength == 12);
            receiveAmdPdu(amdPdu);
        }
        // Control PDU
        else {
            throw new RuntimeException("not implemented yet"); // TODO
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

        if (rxCurrentSize + pdu.data.length > rxMaxSize) {
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
        if (isAlreadyReceived(pdu.sn, pdu.so, pdu.data.length)) {
            triggerControl.run();
            return;
        }

        // Place the received AMD PDU in the reception buffer
        rxCurrentSize += pdu.data.length;
        insertReception(pdu);

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

    private void actionReception(AmdPdu pdu) {
        int x = pdu.sn;

        // if x >= RX_Next_Highest update RX_Next_Highest to x+ 1.
        if (snCompareRx(x, rxNextHighest) >= 0)
            rxNextHighest = (x + 1) % snModulus;

        if (isAllSegmentsReceived(x)) {
            // Reassemble the RLC SDU from AMD PDU(s) with SN = x,
            //  remove RLC headers when doing so and deliver the reassembled RLC SDU to upper layer;
            reassembleAndDeliver(pdu.sn);

            // If x = RX_Highest_Status, update RX_Highest_Status to the SN of the first RLC SDU with
            //  SN > current RX_Highest_Status for which not all bytes have been received.
            if (x == rxHighestStatus) {
                int n = rxHighestStatus;
                while (isDelivered(n)) {
                    n = (n + 1) % snModulus;
                }
                rxHighestStatus = n;
            }

            // If x = RX_Next: update RX_Next to the SN of the first RLC SDU with SN > current RX_Next
            //  for which not all bytes have been received.
            if (x == rxNext) {

                // TODO, WARNING: Not completely sure
                while (!rxBuffer.isEmpty() && rxBuffer.peekFirst()._isProcessed && rxBuffer.peekFirst().sn == rxNext) {
                    do {
                        rxBuffer.removeFirst();
                    } while (!rxBuffer.isEmpty() && rxBuffer.peekFirst().sn == rxNext);
                    rxNext = (rxNext + 1) % snModulus;
                }
            }
        }

        // if t-Reassembly is running
        if (tReassemblyStart > 0) {
            if (rxNextStatusTrigger == rxNext // if RX_Next_Status_Trigger = RX_Next; or
                    ||
                    // if RX_Next_Status_Trigger = RX_Next + 1 and there is no missing byte segment of the SDU
                    //  associated with SN = RX_Next before the last byte of all received segments of this SDU; or
                    (rxNextStatusTrigger == (rxNext + 1) % snModulus && !hasMissingSegment(rxNext)) ||
                    // if RX_Next_Status_Trigger falls outside of the receiving window and RX_Next_Status_Trigger
                    //  is not equal to RX_Next + AM_Window_Size:
                    (!isInReceiveWindow(rxNextStatusTrigger) && rxNextStatusTrigger != (rxNext + windowSize) % snModulus)) {

                // Stop and reset t-Reassembly.
                tReassemblyStart = 0;
            }
        }

        // if t-Reassembly is not running (includes the case t-Reassembly is stopped due to actions above)
        if (tReassemblyStart == 0) {
            // if RX_Next_Highest> RX_Next +1; or
            if (snCompareRx(rxNextHighest, (rxNext + 1) % snModulus) > 0
                    // if RX_Next_Highest = RX_Next + 1 and there is at least one missing byte segment of the SDU
                    //  associated with SN = RX_Next before the last byte of all received segments of this SDU:
                    || (rxNextHighest == (rxNext + 1) % snModulus && hasMissingSegment(rxNext))) {

                // Start t-Reassembly
                tReassemblyStart = tCurrent;
                // Set RX_Next_Status_Trigger to RX_Next_Highest
                rxNextStatusTrigger = rxNextHighest;
            }
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
        txBuffer.addLast(segment);
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

        // At the first transmission opportunity indicated by lower layer,
        //  construct a STATUS PDU and submit it to lower layer
        if (pendingStatusToSend()) {
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
        // TODO
        return null;
    }

    private OctetString createRetPdu(int maxSize) {
        // TODO
        return null;
    }

    private OctetString createTxPdu(int maxSize) {
        // TODO
        return null;
    }

    //======================================================================================================
    //                                     TIMER RELATED METHODS
    //======================================================================================================

    @Override
    public void timerCycle(long currentTime) {
        tCurrent = currentTime;

        // If PollRetransmit is running and expired
        if (tPollRetransmitStart != 0 && tCurrent > tPollRetransmitStart + tPollRetransmitPeriod) {
            // Stop timer and Handle expire actions
            tPollRetransmitStart = 0;
            actionPollRetransmitTimerExpired();
        }

        // If t-Reassembly is running and expired
        if (tReassemblyStart != 0 && tCurrent > tReassemblyStart + tReassemblyPeriod) {
            // Stop timer and Handle expire actions
            tReassemblyStart = 0;
            actionReassemblyTimerExpired();
        }
    }

    private void actionReassemblyTimerExpired() {
        // When t-Reassembly expires, the receiving side of an AM RLC entity shall:

        // update RX_Highest_Status to the SN of the first RLC SDU with
        //  SN >= RX_Next_Status_Trigger for which not all bytes have been received;
        int sn = rxNextStatusTrigger;
        while (isDelivered(sn))
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
        else if (rxNextHighest == (rxHighestStatus + 1) % snModulus && hasMissingSegment(rxHighestStatus)) {
            condition = true;
        }

        if (condition) {
            // start t-Reassembly;
            tReassemblyStart = tCurrent;
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
        RlcSduSegment p = null;

        for (var segment : txBuffer) {
            if (segment.sdu.sduId == sduId) {
                p = segment;
                break;
            }
        }

        // SDU not found, do nothing.
        if (p == null)
            return;

        // The SDU is already segmented, do nothing.
        if (p.si != ESegmentInfo.FULL)
            return;

        // Remove the segment
        if (!txBuffer.remove(p)) {
            throw new RuntimeException();
        }

        // TODO, WARNING: not really sure here because this is not included in the a.i
        txCurrentSize -= p.size;
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
