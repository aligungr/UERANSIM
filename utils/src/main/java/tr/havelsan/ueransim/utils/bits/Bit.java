/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils.bits;

public final class Bit extends BitN {
    public static final Bit ONE = new Bit(1);
    public static final Bit ZERO = new Bit(0);

    public Bit(int value) {
        super(value, 1);
    }

    public Bit(Bit value) {
        super(value);
    }
}