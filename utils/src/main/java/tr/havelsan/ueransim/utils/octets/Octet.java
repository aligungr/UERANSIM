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
 * Represents 1-octet or 8-bit unsigned integer
 */
public final class Octet extends OctetN {

    public Octet() {
        this(0);
    }

    public Octet(byte value) {
        this(value & 0xFF);
    }

    public Octet(int value) {
        this((long) value);
    }

    public Octet(String hex) {
        this(Utils.toLong(hex));
    }

    public Octet(long value) {
        super(value, 1);
    }

    @Override
    public final Octet setBit(int index, int bit) {
        return new Octet(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet setBit(int index, Bit bit) {
        return new Octet(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet setBitRange(int start, int end, long value) {
        return new Octet(super.setBitRange(start, end, value).longValue());
    }
}
