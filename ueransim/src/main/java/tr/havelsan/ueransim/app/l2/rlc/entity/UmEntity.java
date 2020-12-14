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

    // Configurations
    private int snLength;
    private int snModulus;
    private int windowSize;
    private int tReassemblyPeriod;

    // RX management
    private int rxMaxSize;
    private int rxCurrentSize;
    private List<UmdPdu> rxBuffer;

    // RX state variables
    private int rxNextReassembly;  // Earliest SN that is still considered for reassembly
    private int rxNextHighest;     // SN of the UMD PDU with the highest SN among received UMD PDUs
    private int rxTimerTrigger;    // The SN which triggered t-Reassembly

    // Timers
    private long tCurrent;         // Not a timer, but holds the current time in ms.
    private long tReassemblyStart; // Reassembling timer

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

        // WARNING: Check if it is already reassembled and delivered. Returning false if it is
        //  already processes. Because no need to reprocess it. We can consider this method as
        //  "ready to reassembly and deliver" instead of "is all segments received?"
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

    private boolean hasMissingSegment(int sn) {
        int index = -1;

        for (int i = 0; i < rxBuffer.size(); i++) {
            var pdu = rxBuffer.get(i);
            if (pdu.sn == sn) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            // No such a PDU, therefore we don't have a missing segment.
            return false;
        }

        if (rxBuffer.get(index)._isDelivered) {
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
                int n = rxNextReassembly;
                while (isDelivered(n)) {
                    n = (n + 1) % snModulus;
                }
                rxNextReassembly = n;
            }
        } else if (snCompareRx(x, rxNextHighest) >= 0) {
            // Update RX_Next_Highest to x + 1;
            rxNextHighest = (x + 1) % snModulus;

            // Discard any UMD PDUs with SN that falls outside of the reassembly window
            var it = rxBuffer.listIterator();
            while (it.hasNext()) {
                var value = it.next();

                if (snCompareRx(value.sn, rxNextHighest) >= 0) {
                    rxCurrentSize -= value.data.length;
                    it.remove();
                }
            }

            // If RX_Next_Reassembly falls outside of the reassembly window
            if (snCompareRx(rxNextReassembly, rxNextHighest) >= 0) {
                // Set RX_Next_Reassembly to the SN of the first SN >= (RX_Next_Highest – UM_Window_Size)
                //  that has not been reassembled and delivered to upper layer.
                int n = (rxNextHighest - windowSize + snModulus) % snModulus;
                while (isDelivered(n))
                    n = (n + 1) % snModulus;
                rxNextReassembly = n;
            }
        }

        // If t-Reassembly is running
        if (tReassemblyStart != 0) {
            boolean condition = false;

            // if RX_Timer_Trigger <= RX_Next_Reassembly; or
            if (snCompareRx(rxTimerTrigger, rxNextReassembly) <= 0) {
                condition = true;
            }
            // if RX_Timer_Trigger falls outside of the reassembly window and
            //  RX_Timer_Trigger is not equal to RX_Next_Highest; or
            else if (snCompareRx(rxTimerTrigger, rxNextHighest) > 0) {
                condition = true;
            }
            // if RX_Next_Highest = RX_Next_Reassembly + 1 and there is no missing byte segment
            //  of the RLC SDU associated with SN = RX_Next_Reassembly before the last byte of
            //  all received segments of this RLC SDU:
            else if (rxNextHighest == (rxNextReassembly + 1) % snModulus && !hasMissingSegment(rxNextReassembly)) {
                condition = true;
            }

            // ... stop and reset t-Reassembly.
            if (condition) {
                tReassemblyStart = 0;
            }
        }

        // If t-Reassembly is not running (includes the case when t-Reassembly is stopped due to actions above)
        if (tReassemblyStart == 0) {
            boolean condition = false;

            // if RX_Next_Highest > RX_Next_Reassembly + 1; or
            if (snCompareRx(rxNextHighest, (rxNextReassembly + 1) % snModulus) > 0) {
                condition = true;
            }
            // if RX_Next_Highest = RX_Next_Reassembly + 1 and there is at least one missing byte segment
            //  of the RLC SDU associated with SN = RX_Next_Reassembly before the last byte of all received
            //  segments of this RLC SDU:
            else if (rxNextHighest == (rxNextReassembly + 1) % snModulus && hasMissingSegment(rxNextReassembly)) {
                condition = true;
            }

            if (condition) {
                // start t-Reassembly;
                tReassemblyStart = tCurrent;
                // set RX_Timer_Trigger to RX_Next_Highest.
                rxTimerTrigger = rxNextHighest;
            }
        }
    }

    private void actionReassemblyTimerExpired() {
        // TODO
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

    @Override
    public void timerCycle(long currentTime) {
        tCurrent = currentTime;

        // If t-Reassembly is running and expired
        if (tReassemblyStart != 0 && tCurrent > tReassemblyStart + tReassemblyPeriod) {
            actionReassemblyTimerExpired();
        }
    }
}
