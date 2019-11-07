package com.runsim.backend.utils.bits;

public final class Bit extends BitN {
    public final boolean booleanValue;

    public Bit(int value) {
        super(value, 0b1);
        this.booleanValue = value != 0;
    }
}