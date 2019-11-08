package com.runsim.backend.utils.bits;

public final class Bit extends BitN {
    public Bit(int value) {
        super(value, 1);
    }

    public final boolean booleanValue() {
        return intValue() != 0;
    }
}