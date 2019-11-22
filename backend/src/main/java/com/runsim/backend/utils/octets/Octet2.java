package com.runsim.backend.utils.octets;

/**
 * Represents 2-octet or 16-bit value
 */
public class Octet2 extends OctetN {

    public Octet2(int intValue) {
        super(intValue, 2);
    }

    public Octet2(int msb, int lsb) {
        this(((msb << 8) & 0xFF) | (lsb & 0xFF));
    }

    public Octet2(Octet msb, Octet lsb) {
        this(msb.intValue(), lsb.intValue());
    }
}
