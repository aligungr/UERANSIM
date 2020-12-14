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

public class UmEntity extends RlcEntity {

    private int snLength = 6;

    private int snModulus;
    private int windowSize;

    private int rxNextReassembly; // Earliest SN that is still considered for reassembly
    private int rxNextHighest; //

    private int modulusRx(int num) {
        int r = num - (rxNextHighest - windowSize);
        if (r < 0) r += snModulus;
        return r % snModulus;
    }

    private int snCompareRx(int a, int b) {
        return modulusRx(a) - modulusRx(b);
    }

    @Override
    public OctetString createPdu(OctetString sdu) {
        return null;
    }

    @Override
    public void receivePdu(OctetString data) {
        var pdu = UmdPdu.decode(new OctetInputStream(data), snLength == 6);

        if (pdu.si == RlcConstants.SI_FULL) {
            RlcTransfer.deliverSdu(this, pdu.data);
            return;
        }

        if (pdu.data.length == 0) {
            return;
        }

        if (snCompareRx(pdu.sn, rxNextReassembly) < 0) {
            return;
        }

        // TODO place the received UMD PDU in the reception buffer
    }

    @Override
    public void receiveSdu(OctetString data) {

    }
}
