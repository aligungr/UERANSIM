/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils;

import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.OctetString;

// TODO: Optimize this
public class BitOutputStream {
    private final BitString data;

    public BitOutputStream() {
        this.data = new BitString();
    }

    public void write(boolean b) {
        data.set(data.bitLength(), b);
    }

    public void writeBits(int value, int length) {
        if (length < 0 || length > 31)
            throw new IllegalArgumentException();

        for (int i = length - 1; i >= 0; i--) {
            write(((value >> i) & 1) != 0);
        }
    }

    public OctetString toOctetString() {
        return data.toOctetString();
    }
}
