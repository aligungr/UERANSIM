package com.runsim.backend.utils.octets;

import com.runsim.backend.utils.bits.Bit;

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
        this((big << 16) | (middle << 8) | little);
    }

    public Octet3(Octet big, Octet middle, Octet little) {
        this(big.intValue(), middle.intValue(), little.intValue());
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
