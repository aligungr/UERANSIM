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
    private List<UmdPdu> rxBuffer; // TODO: auto sorting as first SN then SO

    // RX state variables
    private int rxNextReassembly; // Earliest SN that is still considered for reassembly
    private int rxNextHighest; //

    //======================================================================================================
    //                                          INTERNAL METHODS
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
    //                                             BASE METHODS
    //======================================================================================================

    @Override
    public OctetString createPdu(OctetString sdu) {
        return null;
    }

    @Override
    public void receivePdu(OctetString data) {
        var pdu = UmdPdu.decode(new OctetInputStream(data), snLength == 6);

        // If it is a full sdu, deliver directly.
        if (pdu.si == RlcConstants.SI_FULL) {
            RlcTransfer.deliverSdu(this, pdu.data);
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

        rxCurrentSize += pdu.data.length;
        rxBuffer.add(pdu);
    }

    @Override
    public void receiveSdu(OctetString data) {

    }
}
