/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils.bits;

public final class Bit3 extends BitN {

    public Bit3(int value) {
        super(value, 3);
    }

    public Bit3(Bit bit2, Bit bit1, Bit bit0) {
        super(bit2, bit1, bit0);
    }

    public Bit3(int bit2, int bit1, int bit0) {
        super(bit2, bit1, bit0);
    }
}