/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.l2.rlc.entity;

import tr.havelsan.ueransim.app.l2.rlc.RlcConstants;
import tr.havelsan.ueransim.app.l2.rlc.RlcTransfer;
import tr.havelsan.ueransim.app.l2.rlc.pdu.UmdPdu;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.List;

public class UmEntity extends RlcEntity {

    private int snLength = 6;

    private int snModulus;
    private int windowSize;

    // RX management
    private int rxMaxSize;
    private int rxCurrentSize;
    private List<UmdPdu> rxBuffer;

    // RX state variables
    private int rxNextReassembly; // Earliest SN that is still considered for reassembly
    private int rxNextHighest; //

    //======================================================================================================
    //                                                  UTILS
    //======================================================================================================

    private int modulusRx(int num) {
        int r = num - (rxNextHighest - windowSize);
        if (r < 0) r += snModulus;
        return r % snModulus;
    }

    private int snCompareRx(int a, int b) {
        return modulusRx(a) - modulusRx(b);
    }

    //======================================================================================================
    //                                              INTERNAL METHODS
    //======================================================================================================

    private void insertReception(UmdPdu pdu) {
        int index = -1;

        for (int i = 0; i < rxBuffer.size(); i++) {
            var p = rxBuffer.get(i);
            if (p.sn == pdu.sn) {
                if (p.so > pdu.so) {
                    break;
                }
            } else if (p.sn > pdu.sn) {
                break;
            }

            index = i;
        }

        rxBuffer.add(index + 1, pdu);
    }

    private boolean isAllSegmentsReceived(int sn) {
        int index = -1;

        for (int i = 0; i < rxBuffer.size(); i++) {
            var pdu = rxBuffer.get(i);
            if (pdu.sn == sn) {
                index = i;
                break;
            }
        }

        if (index == -1)
            return false;

        // Check if it is already reassembled and delivered. Returning false if it is
        //  already processes.
        if (rxBuffer.get(index)._isDelivered)
            return false;

        int maxOffset = -1;

        for (int i = index; i < rxBuffer.size(); i++) {
            var pdu = rxBuffer.get(i);

            if (pdu.so > maxOffset + 1)
                return false;
            if (pdu.si == RlcConstants.SI_LAST)
                return true;

            var endOffset = pdu.so + pdu.data.length - 1;
            if (endOffset > maxOffset)
                maxOffset = endOffset;
        }

        return false;
    }

    private boolean isDelivered(int sn) {
        for (var pdu : rxBuffer) {
            if (pdu.sn == sn && pdu._isDelivered)
                return true;
        }
        return false;
    }

    private void deliverReception(UmdPdu pdu) {
        // TODO
    }

    //======================================================================================================
    //                                             ACTIONS
    //======================================================================================================

    private void actionReception(UmdPdu pdu) {
        int x = pdu.sn;

        // If all byte segments with SN = x are received
        if (isAllSegmentsReceived(x)) {
            // Reassemble the RLC SDU from all byte segments with SN = x, remove RLC headers and deliver
            //  the reassembled RLC SDU to upper layer.
            deliverReception(pdu);

            // if x = RX_Next_Reassembly, update RX_Next_Reassembly to the SN of the first
            //  SN > current RX_Next_Reassembly that has not been reassembled and delivered to upper layer.
            if (x == rxNextReassembly) {
                int i = rxNextReassembly;
                while (isDelivered(i)) {
                    i = (i + 1) % snModulus;
                }
                rxNextReassembly = i;
            }
        }
    }

    //======================================================================================================
    //                                             BASE METHODS
    //======================================================================================================

    @Override
    public OctetString createPdu(OctetString sdu) {
        return null;
    }

    @Override
    public void receivePdu(OctetString data) {
        var pdu = UmdPdu.decode(new OctetInputStream(data), snLength == 6);
        pdu._isDelivered = false;

        // If it is a full sdu, deliver directly.
        if (pdu.si == RlcConstants.SI_FULL) {
            RlcTransfer.deliverSdu(this, pdu.data);
            return;
        }

        // If SO is invalid, then discard.
        if (pdu.si != RlcConstants.SI_FIRST && pdu.so == 0) {
            return;
        }

        // If data length == 0, then discard.
        if (pdu.data.length == 0) {
            return;
        }

        // If (RX_Next_Highest – UM_Window_Size) <= SN < RX_Next_Reassembly, then discard.
        if (snCompareRx(pdu.sn, rxNextReassembly) < 0) {
            return;
        }

        // If no room, then discard.
        if (rxCurrentSize + pdu.data.length > rxMaxSize) {
            return;
        }

        // Place the received UMD PDU in the reception buffer
        rxCurrentSize += pdu.data.length;
        insertReception(pdu); // TODO: may be incorrect, see a.i.

        // Actions when an UMD PDU is placed in the reception buffer (5.2.2.2.3)
        actionReception(pdu);
    }

    @Override
    public void receiveSdu(OctetString data) {

    }
}
