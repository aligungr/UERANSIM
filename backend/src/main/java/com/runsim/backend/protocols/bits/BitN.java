package com.runsim.backend.protocols.bits;

public class BitN {
    private final int value;

    public BitN(int value, int mask) {
        this.value = value & mask;
    }

    public int getValue() {
        return value;
    }

    @Override
    public final int hashCode() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Integer && this.value == (Integer) obj;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
