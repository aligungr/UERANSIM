package com.runsim.backend.protocols.bits;

public class BitN {
    public final int intValue;

    public BitN(int intValue, int mask) {
        this.intValue = intValue & mask;
    }

    @Override
    public final int hashCode() {
        return intValue;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Integer && this.intValue == (Integer) obj;
    }

    @Override
    public String toString() {
        return Integer.toString(intValue);
    }
}
