package com.runsim.backend.utils.bits;

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
}
