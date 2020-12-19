/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.utils;

public enum ESegmentInfo {
    // 00, Data field contains all bytes of an RLC SDU
    FULL(0b00),
    // 01, Data field contains the first segment of an RLC SDU
    FIRST(0b01),
    // 10, Data field contains the last segment of an RLC SDU
    LAST(0b10),
    // 11, Data field contains neither the first nor last segment of an RLC SDU
    MIDDLE(0b11);

    private final int si;

    ESegmentInfo(int si) {
        this.si = si;
    }

    public static ESegmentInfo fromSi(int si) {
        switch (si) {
            case 0b00:
                return FULL;
            case 0b01:
                return FIRST;
            case 0b10:
                return LAST;
            case 0b11:
                return MIDDLE;
            default:
                throw new RuntimeException();
        }
    }

    public int intValue() {
        return si;
    }

    public boolean hasFirst() {
        return this == FULL || this == FIRST;
    }

    public boolean hasLast() {
        return this == FULL || this == LAST;
    }

    public boolean requiresSo() {
        return this == LAST || this == MIDDLE;
    }

    public ESegmentInfo asNotLast() {
        if (this == LAST) return MIDDLE;
        if (this == FULL) return FIRST;
        return this;
    }

    public ESegmentInfo asNotFirst() {
        if (this == FIRST) return MIDDLE;
        if (this == FULL) return LAST;
        return this;
    }
}
