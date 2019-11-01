package com.runsim.backend.protocols.octets;

public class Octet2 {
    public final int intValue;

    public Octet2(int intValue) {
        this.intValue = intValue & 0xFFFF;
    }

    public Octet2(int msb, int lsb) {
        this.intValue = ((msb << 8) & 0xFF) | (lsb & 0xFF);
    }

    public Octet2(Octet msb, Octet lsb) {
        this(msb.intValue, lsb.intValue);
    }
}
