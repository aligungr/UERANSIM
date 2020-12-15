/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.l2.rlc.sdu;

public class RlcSduSegment {
    public final RlcSdu sdu;
    public int size;
    public int so;
    public int si;
    public RlcSduSegment nextSegment;

    public RlcSduSegment(RlcSdu sdu) {
        this.sdu = sdu;
    }
}
