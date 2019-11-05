package com.runsim.backend.protocols.octets;

import com.runsim.backend.protocols.bits.Bit;

public class Octet {
    public final int intValue;

    public Octet(int intValue) {
        this.intValue = intValue & 0xFF;
    }

    @Override
    public String toString() {
        return Integer.toString(intValue);
    }

    public int getBitI(int index) {
        index %= 8;
        return (intValue >> index) & 0b1;
    }

    public Bit getBit(int index) {
        return new Bit(getBitI(index));
    }
}
