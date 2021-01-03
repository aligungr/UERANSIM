/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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