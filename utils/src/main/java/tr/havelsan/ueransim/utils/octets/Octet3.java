/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils.octets;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;

/**
 * Represents 3-octet or 24-bit unsigned integer
 */
public final class Octet3 extends OctetN {

    public Octet3() {
        this(0);
    }

    public Octet3(long value) {
        super(value, 3);
    }

    public Octet3(int big, int middle, int little) {
        this(((big & 0xFF) << 16) | ((middle & 0xFF) << 8) | (little & 0xFF));
    }

    public Octet3(Octet big, Octet middle, Octet little) {
        this(big.intValue(), middle.intValue(), little.intValue());
    }

    public Octet3(String hex) {
        this(Utils.toLong(hex));
    }

    @Override
    public final Octet3 setBit(int index, int bit) {
        return new Octet3(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet3 setBit(int index, Bit bit) {
        return new Octet3(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet3 setBitRange(int start, int end, long value) {
        return new Octet3(super.setBitRange(start, end, value).longValue());
    }
}
