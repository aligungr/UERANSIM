package com.runsim.backend.utils.bits;

import com.runsim.backend.utils.Utils;
import com.runsim.backend.utils.octets.Octet;

import java.util.Arrays;

public class BitN {
    private final int _intValue;
    private final byte _bitCount;

    /**
     *
     */
    public BitN(int intValue, int bitCount) {
        // maximum 30 bit, since implementation uses int32.
        // if you want to keep more than 30 bits, the value can be changed from int to long.
        if (bitCount <= 0 || bitCount > 30)
            throw new IllegalArgumentException("invalid bit count");
        if (intValue < 0)
            throw new IllegalArgumentException("negative int value");

        // TODO: Add max bound check accourding to bit count

        this._intValue = intValue & ((1 << bitCount) - 1);
        this._bitCount = (byte) bitCount;
    }

    /**
     * Constructs a BitN object with given Bit array.
     * bits[0] is the least significant bit, and bits[n-1] is the most significant bit,
     * where n is the length of the array.
     */
    public BitN(Bit... bits) {
        this(Arrays.stream(bits).mapToInt(BitN::intValue).toArray());
    }

    /**
     * Constructs a BitN object with given integer array.
     * bits[0] is the least significant bit, and bits[n-1] is the most significant bit,
     * where n is the length of the array.
     */
    public BitN(int... bits) {
        this(bitsToInt(bits), bits.length);
    }

    /**
     * Returns int32 representation of given bits.
     * bits[0] is the least significant bit, and bits[n-1] is the most significant bit,
     * where n is the length of the array.
     */
    private static int bitsToInt(int... bits) {
        int val = 0;
        for (int i = bits.length - 1; i >= 0; i--) {
            val |= bits[i] & 0b1;
            if (i != 0)
                val <<= 1;
        }
        return val;
    }

    /**
     * Returns int value of the underlying bit representation.
     */
    public final int intValue() {
        return _intValue;
    }

    /**
     * Returns bit count of the underlying bit representation.
     */
    public final int bitCount() {
        return _bitCount;
    }

    /**
     * Returns the minimum number of octet counts required to represent the BitN object.
     */
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

    public final int getBitI(int index) {
        if (index < 0 || index > 29)
            throw new IllegalArgumentException("invalid index");
        return (int) ((_intValue >> index) & 0b1);
    }

    public final Bit getBit(int index) {
        return new Bit(getBitI(index));
    }

    public final boolean getBitB(int index) {
        return getBitI(index) != 0;
    }

    public Octet[] toOctetArray() {
        return toOctetArray(true);
    }
}
