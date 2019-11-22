package com.runsim.backend.utils.octets;

import com.runsim.backend.utils.bits.Bit;

/**
 * Represents 7-octet or 56-bit unsigned integer
 */
public final class Octet7 extends OctetN {

    public Octet7() {
        this(0);
    }

    public Octet7(long value) {
        super(value, 7);
    }

    @Override
    public final Octet7 setBit(int index, int bit) {
        return new Octet7(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet7 setBit(int index, Bit bit) {
        return new Octet7(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet7 setBitRange(int start, int end, long value) {
        return new Octet7(super.setBitRange(start, end, value).longValue());
    }
}
