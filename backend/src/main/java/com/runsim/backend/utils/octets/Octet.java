package com.runsim.backend.utils.octets;

import com.runsim.backend.utils.bits.Bit;

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

    public boolean getBitB(int index) {
        return getBitI(index) != 0;
    }

    public Bit getBit(int index) {
        return new Bit(getBitI(index));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Octet)
            return ((Octet) obj).intValue == this.intValue;
        if (obj instanceof Octet2)
            return ((Octet2) obj).intValue == this.intValue;
        if (obj instanceof Integer)
            return this.intValue == (Integer) obj;
        return false;
    }
}
