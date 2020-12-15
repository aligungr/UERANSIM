/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.l2.rlc.entity;

import tr.havelsan.ueransim.app.l2.rlc.RlcTransfer;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class TmEntity extends RlcEntity {

    @Override
    public OctetString createPdu(OctetString sdu) {
        return sdu;
    }

    @Override
    public void receivePdu(OctetString data) {
        RlcTransfer.deliverSdu(this, data);
    }

    @Override
    public void receiveSdu(OctetString data) {
        RlcTransfer.sendPdu(this, createPdu(data));
    }

    @Override
    public void timerCycle(long currentTime) {

    }
}
