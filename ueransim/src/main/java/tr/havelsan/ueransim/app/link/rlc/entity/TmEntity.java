/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.entity;

import tr.havelsan.ueransim.app.link.rlc.IRlcConsumer;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class TmEntity extends RlcEntity {

    public TmEntity(IRlcConsumer consumer) {
        super(consumer);
    }

    @Override
    public void receivePdu(OctetString data) {
    }

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
