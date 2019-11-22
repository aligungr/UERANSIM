package com.runsim.backend.utils.octets;

/**
 * Represents 4-octet or 32-bit unsigned integer
 */
public class Octet4 extends OctetN {
    public Octet4(long longValue) {
        super(longValue, 4);
    }

    public Octet4(Octet octet3, Octet octet2, Octet octet1, Octet octet0) {
        this(Integer.toUnsignedLong(octet0.intValue() | octet1.intValue() << 8 | octet2.intValue() << 16 | octet3.intValue() << 24));
    }
}
