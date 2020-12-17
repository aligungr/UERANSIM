/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.entity;

import tr.havelsan.ueransim.app.link.rlc.interfaces.IRlcConsumer;
import tr.havelsan.ueransim.app.link.rlc.utils.RlcSdu;
import tr.havelsan.ueransim.app.link.rlc.utils.RlcSduSegment;
import tr.havelsan.ueransim.app.link.rlc.utils.RlcTimer;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedList;

public class TmEntity extends RlcEntity {

    // Configurations
    private int txMaxSize;

    // TX buffer
    private int txCurrentSize;
    private LinkedList<RlcSduSegment> txBuffer;

    // Timers
    private long tCurrent;         // Not a timer, but holds the current time in ms.

    //======================================================================================================
    //                                           INITIALIZATION
    //======================================================================================================

    private TmEntity(IRlcConsumer consumer) {
        super(consumer);
    }

    public static TmEntity newInstance(IRlcConsumer consumer, int txMaxSize) {
        var tm = new TmEntity(consumer);
        tm.txMaxSize = txMaxSize;
        tm.txBuffer = new LinkedList<>();
        tm.clearEntity();
        return tm;
    }

    private void clearEntity() {
        txCurrentSize = 0;
        txBuffer.clear();
    }

    //======================================================================================================
    //                                             BASE METHODS
    //======================================================================================================

    @Override
    public void receivePdu(OctetString data) {
        consumer.deliverSdu(this, data);
    }

    @Override
    public void receiveSdu(OctetString data, int sduId) {
        if (txCurrentSize + data.length > txMaxSize)
            return;

        var sdu = new RlcSdu(sduId, data);

        var segment = new RlcSduSegment(sdu);
        segment.size = data.length;

        txCurrentSize += segment.size;
        txBuffer.addLast(segment);
    }

    @Override
    public OctetString createPdu(int maxSize) {
        var segment = txBuffer.peekFirst();
        if (segment == null) {
            return null;
        }

        if (segment.size > maxSize)
            return null;

        txBuffer.removeFirst();
        txCurrentSize -= segment.size;

        return segment.sdu.data;
    }

    @Override
    public void timerCycle(long currentTime) {
        tCurrent = currentTime;
        // do nothing
    }

    @Override
    public void discardSdu(int sduId) {
        // do nothing
    }

    @Override
    public void reestablishment() {
        clearEntity();
    }

    @Override
    public void deleteEntity() {
        clearEntity();
    }
}
