package com.runsim.backend.utils.octets;

public class Octet3 {
    public final int intValue;

    public Octet3(int intValue) {
        this.intValue = intValue & 0xFFFFFF;
    }

    public Octet3(int big, int middle, int little) {
        this((big << 16) | (middle << 8) | little);
    }

    public Octet3(Octet big, Octet middle, Octet little) {
        this(big.intValue, middle.intValue, little.intValue);
    }

    @Override
    public String toString() {
        return Integer.toString(intValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Octet)
            return ((Octet) obj).intValue == this.intValue;
        if (obj instanceof Octet2)
            return ((Octet2) obj).intValue == this.intValue;
        if (obj instanceof Octet3)
            return ((Octet3) obj).intValue == this.intValue;
        if (obj instanceof Integer)
            return this.intValue == (Integer) obj;
        return false;
    }
}
