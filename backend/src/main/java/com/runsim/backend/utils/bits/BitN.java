package com.runsim.backend.utils.bits;

import com.runsim.backend.utils.Utils;

public class BitN {
    public final int intValue;
    public final byte bitCount;

    public BitN(int intValue, int bitCount) {
        // warning: maximum 30 bit, since implementation uses int32.
        // if you want to keep more than 30 bits, the value can be changed from int to long.
        if (bitCount < 0 || bitCount > 30)
            throw new IllegalArgumentException("invalid bit count");
        if (intValue < 0)
            throw new IllegalArgumentException("negative int value");

        this.intValue = intValue & ((1 << bitCount) - 1);
        this.bitCount = (byte) bitCount;
    }

    @Override
    public final int hashCode() {
        return intValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BitN)
            return ((BitN) obj).intValue == this.intValue;
        if (obj instanceof Integer)
            return this.intValue == (Integer) obj;
        return false;
    }

    @Override
    public String toString() {
        return Integer.toString(intValue);
    }

    public String toBinaryString() {
        return Utils.padLeft(Integer.toBinaryString(intValue), bitCount, '0');
    }
}
