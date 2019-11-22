package com.runsim.backend.utils.octets;

/**
 * Represents 3-octet or 24-bit value
 */
public class Octet3 extends OctetN {
    public Octet3(int intValue) {
        super(intValue & 0xFFFFFF, 3);
    }

    public Octet3(int big, int middle, int little) {
        this((big << 16) | (middle << 8) | little);
    }

    public Octet3(Octet big, Octet middle, Octet little) {
        this(big.intValue(), middle.intValue(), little.intValue());
    }
}
