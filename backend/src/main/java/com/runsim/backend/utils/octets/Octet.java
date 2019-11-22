package com.runsim.backend.utils.octets;

import com.runsim.backend.utils.bits.Bit;

/**
 * Represents 1-octet or 8-bit unsigned integer
 */
public final class Octet extends OctetN {

    public Octet() {
        this(0);
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
