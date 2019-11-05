package com.runsim.backend.protocols.bits;

public final class Bit extends BitN {
    public boolean booleanValue;

    public Bit(int value) {
        super(value, 0b1);
        this.booleanValue = value != 0;
    }
}