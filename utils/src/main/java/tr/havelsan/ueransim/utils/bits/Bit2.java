/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils.bits;

public final class Bit2 extends BitN {

    public Bit2(int value) {
        super(value, 2);
    }

    public Bit2(Bit bit1, Bit bit0) {
        super(bit1, bit0);
    }

    public Bit2(int bit1, int bit0) {
        super(bit1, bit0);
    }
}