package com.runsim.backend.protocols.octets;

public class Octet {
    public final int intValue;

    public Octet(int intValue) {
        this.intValue = intValue & 0xFF;
    }

    @Override
    public String toString() {
        return Integer.toString(intValue);
    }
}
