package com.runsim.backend.utils.octets;

import com.runsim.backend.utils.Utils;
import com.runsim.backend.utils.bits.Bit;

/**
 * Represents 6-octet or 48-bit unsigned integer
 */
public final class Octet6 extends OctetN {

    public Octet6() {
        this(0);
    }

    public Octet6(long value) {
        super(value, 6);
    }

    public Octet6(String hex) {
        this(Utils.toLong(hex));
    }

    @Override
    public final Octet6 setBit(int index, int bit) {
        return new Octet6(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet6 setBit(int index, Bit bit) {
        return new Octet6(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet6 setBitRange(int start, int end, long value) {
        return new Octet6(super.setBitRange(start, end, value).longValue());
    }
}
