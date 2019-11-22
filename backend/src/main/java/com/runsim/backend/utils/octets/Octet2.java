package com.runsim.backend.utils.octets;

import com.runsim.backend.utils.bits.Bit;

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
        this(((msb << 8) & 0xFF) | (lsb & 0xFF));
    }

    public Octet2(Octet msb, Octet lsb) {
        this(msb.intValue(), lsb.intValue());
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
