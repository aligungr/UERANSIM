/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils.octets;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;

/**
 * Represents 4-octet or 32-bit unsigned integer
 */
public final class Octet4 extends OctetN {

    public Octet4() {
        this(0);
    }

    public Octet4(long value) {
        super(value, 4);
    }

    public Octet4(Octet octet3, Octet octet2, Octet octet1, Octet octet0) {
        this(Integer.toUnsignedLong(octet0.intValue() | octet1.intValue() << 8 | octet2.intValue() << 16 | octet3.intValue() << 24));
    }

    public Octet4(int octet3, int octet2, int octet1, int octet0) {
        this(new Octet(octet3), new Octet(octet2), new Octet(octet1), new Octet(octet0));
    }

    public Octet4(String hex) {
        this(Utils.toLong(hex));
    }

    @Override
    public final Octet4 setBit(int index, int bit) {
        return new Octet4(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet4 setBit(int index, Bit bit) {
        return new Octet4(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet4 setBitRange(int start, int end, long value) {
        return new Octet4(super.setBitRange(start, end, value).longValue());
    }
}
