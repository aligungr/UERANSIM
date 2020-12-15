/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.entity;

import tr.havelsan.ueransim.app.link.rlc.IRlcConsumer;
import tr.havelsan.ueransim.app.link.rlc.RlcConstants;
import tr.havelsan.ueransim.app.link.rlc.pdu.AmdPdu;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedList;

public class AmEntity extends RlcEntity {

    // Configurations
    private int snLength;
    private int snModulus;
    private int windowSize;
    private int rxMaxSize;

    /* TX state variables */
    private int txNextAck;

    // RX state variables
    private int rxNext;

    // RX buffer
    private int rxCurrentSize;
    private LinkedList<AmdPdu> rxBuffer;

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
    //                                               UTILS
    //======================================================================================================

    private int modulusRx(int a) {
        int r = a - rxNext;
        return r < 0 ? r + snModulus : r;
    }

    private int modulusTx(int a) {
        int r = a - txNextAck;
        if (r < 0) r += snModulus;
        return r;
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
    //                                           INTERNAL METHODS
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

    //======================================================================================================
    //                                              ACTIONS
    //======================================================================================================

    private void actionReception(AmdPdu pdu) {
        // TODO
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
        if (pdu.si == RlcConstants.SI_MIDDLE || pdu.si == RlcConstants.SI_LAST) {
            if (pdu.so == 0) {
                // Bad SO value, discard PDU.
                return;
            }
        }

        if (pdu.data.length == 0) {
            // No data, discard PDU.
            return;
        }

        if (rxCurrentSize + pdu.data.length > rxMaxSize) {
            // No room in RX buffer, discard PDU.
            return;
        }

        // Discard if x falls outside of the receiving window
        if (!isInReceiveWindow(pdu.sn)) {
            return;
        }

        // if byte segment numbers y to z of the RLC SDU with SN = x have been received before:
        //  discard the received AMD PDU
        if (isAlreadyReceived(pdu.sn, pdu.so, pdu.data.length)) {
            return;
        }

        // Place the received AMD PDU in the reception buffer
        rxCurrentSize += pdu.data.length;
        insertReception(pdu);

        // Actions when an AMD PDU is placed in the reception buffer
        actionReception(pdu);

        // TODO
        if (pdu.p) {

        }
    }

    //======================================================================================================
    //                                            BASE METHODS
    //======================================================================================================

    @Override
    public void receiveSdu(OctetString data, int sduId) {

    }

    @Override
    public OctetString createPdu(int maxSize) {
        return null;
    }

    @Override
    public void timerCycle(long currentTime) {

    }

    @Override
    public void discardSdu(int sduId) {

    }

    @Override
    public void reestablishment() {

    }

    @Override
    public void deleteEntity() {

    }
}
