package com.runsim.backend.utils.bits;

public final class Bit extends BitN {
    public Bit(int value) {
        super(value, 1);
    }

    public Bit(Bit value) {
        super(value);
    }
}