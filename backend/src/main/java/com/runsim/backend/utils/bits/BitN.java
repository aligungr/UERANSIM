package com.runsim.backend.utils.bits;

import com.runsim.backend.utils.Utils;
import com.runsim.backend.utils.octets.Octet;

public class BitN {
    private final int _intValue;
    private final byte _bitCount;

    public BitN(int intValue, int bitCount) {
        // maximum 30 bit, since implementation uses int32.
        // if you want to keep more than 30 bits, the value can be changed from int to long.
        if (bitCount < 0 || bitCount > 30)
            throw new IllegalArgumentException("invalid bit count");
        if (intValue < 0)
            throw new IllegalArgumentException("negative int value");

        this._intValue = intValue & ((1 << bitCount) - 1);
        this._bitCount = (byte) bitCount;
    }

    public final int intValue() {
        return _intValue;
    }

    public final int bitCount() {
        return _bitCount;
    }

    public final int octetCount() {
        return (int) Math.ceil(bitCount() / 8.0);
    }

    @Override
    public final int hashCode() {
        return _intValue;
    }

    @Override
    public final boolean equals(Object obj) {
        return Utils.unsignedEqual(this, obj);
    }

    @Override
    public String toString() {
        return Integer.toString(_intValue);
    }

    public final String toBinaryString() {
        return "0b" + Utils.padLeft(Integer.toBinaryString(_intValue), _bitCount, '0');
    }

    public Octet[] toOctetArray(boolean useBigEndian) {
        var octets = new Octet[octetCount()];
        int intValue = intValue();
        int bitCount = bitCount();
        for (int i = 0; i < octets.length; i++) {
            if (bitCount < 8)
                bitCount = 8;

            int msbOctet = ((intValue >> (bitCount - 8)) & 0xFF);
            intValue &= ((1 << (bitCount - 8)) - 1);
            bitCount -= 8;

            octets[i] = new Octet(msbOctet);
        }
        return octets;
    }

    public Octet[] toOctetArray() {
        return toOctetArray(true);
    }
}
