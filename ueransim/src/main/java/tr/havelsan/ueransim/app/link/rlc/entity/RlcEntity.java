/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.entity;

import tr.havelsan.ueransim.app.link.rlc.interfaces.IRlcConsumer;
import tr.havelsan.ueransim.utils.octets.OctetString;

public abstract class RlcEntity {

    protected final IRlcConsumer consumer;

    public RlcEntity(IRlcConsumer consumer) {
        this.consumer = consumer;
    }

    public abstract void receivePdu(OctetString data);

    public abstract void receiveSdu(OctetString data, int sduId);

    public abstract OctetString createPdu(int maxSize);

    public abstract void timerCycle(long currentTime);

    public abstract void discardSdu(int sduId);

    public abstract void reestablishment();

    public abstract void deleteEntity();
}
