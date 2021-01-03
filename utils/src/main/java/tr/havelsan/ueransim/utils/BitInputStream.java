/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils;

import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.OctetString;

// TODO: Optimize this
public class BitInputStream {

    private final BitString data;
    private int index;

    public BitInputStream(OctetString data) {
        this.data = BitString.from(data);
    }

    public boolean peek() {
        return data.getB(index);
    }

    public boolean read() {
        return data.getB(index++);
    }

    public int readRange(int count) {
        if (count < 0 || count > 31)
            throw new IllegalArgumentException();

        var sub = data.substring(index, count);
        index += count;
        return sub.intValue();
    }

    public int peekRange(int count) {
        if (count < 0 || count > 31)
            throw new IllegalArgumentException();

        return data.substring(index, count).intValue();
    }
}
