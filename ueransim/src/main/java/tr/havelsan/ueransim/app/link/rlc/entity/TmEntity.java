/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.entity;

import tr.havelsan.ueransim.app.link.rlc.IRlcConsumer;
import tr.havelsan.ueransim.app.link.rlc.sdu.RlcSduSegment;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedList;

public class TmEntity extends RlcEntity {

    // Configurations
    private int txMaxSize;

    // TX buffer
    private int txCurrentSize;
    private LinkedList<RlcSduSegment> txBuffer;

    //======================================================================================================
    //                                           INITIALIZATION
    //======================================================================================================

    private TmEntity(IRlcConsumer consumer) {
        super(consumer);
    }

    public static TmEntity newInstance(IRlcConsumer consumer, int txMaxSize) {
        var tm = new TmEntity(consumer);
        tm.clearEntity();
        tm.txMaxSize = txMaxSize;
        tm.txBuffer = new LinkedList<>();
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
