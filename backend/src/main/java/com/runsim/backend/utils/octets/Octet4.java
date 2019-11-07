package com.runsim.backend.utils.octets;

import com.runsim.backend.utils.Utils;

public class Octet4 {
    public final long longValue;

    public Octet4(long longValue) {
        this.longValue = longValue & 0xFFFFFFFFL;
    }

    public Octet4(Octet octet3, Octet octet2, Octet octet1, Octet octet0) {
        int signed32 = 0;
        signed32 |= octet0.intValue;
        signed32 |= octet1.intValue << 8;
        signed32 |= octet2.intValue << 16;
        signed32 |= octet3.intValue << 24;
        this.longValue = Integer.toUnsignedLong(signed32);
    }

    @Override
    public String toString() {
        return "0x" + Utils.padLeft(Long.toBinaryString(longValue), 4 * 2, '0');
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Octet)
            return ((Octet) obj).intValue == this.longValue;
        if (obj instanceof Octet2)
            return ((Octet2) obj).intValue == this.longValue;
        if (obj instanceof Octet4)
            return ((Octet4) obj).longValue == this.longValue;
        if (obj instanceof Integer)
            return this.longValue == (Integer) obj;
        return false;
    }
}
