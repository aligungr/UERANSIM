/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.asn.core;

import tr.havelsan.ueransim.utils.bits.BitString;

public class AsnBitString extends AsnValue {
    public BitString value;

    public AsnBitString() {
    }

    public AsnBitString(BitString value) {
        this.value = value;
    }

    public static AsnBitString spare(int length) {
        if (length == 0)
            return new AsnBitString(new BitString());
        var val = new BitString();
        val.clear(length - 1);
        return new AsnBitString(val);
    }
}
