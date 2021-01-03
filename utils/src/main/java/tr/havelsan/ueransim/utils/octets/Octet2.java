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
 * Represents 2-octet or 16-bit unsigned integer
 */
public final class Octet2 extends OctetN {

    public Octet2() {
        this(0);
    }

    public Octet2(long value) {
        super(value, 2);
    }

    public Octet2(int msb, int lsb) {
        this(((msb & 0xFF) << 8) | (lsb & 0xFF));
    }

    public Octet2(Octet msb, Octet lsb) {
        this(msb.intValue(), lsb.intValue());
    }

    public Octet2(String hex) {
        this(Utils.toLong(hex));
    }

    @Override
    public final Octet2 setBit(int index, int bit) {
        return new Octet2(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet2 setBit(int index, Bit bit) {
        return new Octet2(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet2 setBitRange(int start, int end, long value) {
        return new Octet2(super.setBitRange(start, end, value).longValue());
    }
}
