/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.utils;

public class RlcSduSegment {
    public final RlcSdu sdu;
    public int size;
    public int so;
    public ESegmentInfo si;

    public RlcSduSegment(RlcSdu sdu) {
        this.sdu = sdu;
    }
}
