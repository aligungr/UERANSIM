/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils.bits;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.octets.Octet;

import java.util.Arrays;

public class BitN {
    private final int _intValue;
    private final byte _bitCount;

    /**
     * Constructs a BitN object with given int value and bit count.
     * Integer value is masked with ((2 ^ [bit count]) - 1)
     *
     * @param intValue is the integer value. This value can be 0 at minimum and (2^[bit count] - 1) at maximum.
     * @param bitCount is the total number of bits to be used of integer value
     */
    BitN(int intValue, int bitCount) {
        // maximum 30 bit, since implementation uses int32.
        // if you want to keep more than 30 bits, the value can be changed from int to long.
        if (bitCount <= 0 || bitCount > 30)
            throw new IllegalArgumentException("invalid bit count");
        if (intValue < 0)
            throw new IllegalArgumentException("negative int value");

        this._intValue = intValue & ((1 << bitCount) - 1);
        this._bitCount = (byte) bitCount;
    }

    /**
     * Constructs a BitN object with given Bit array.
     * bits[0] is the least significant bit, and bits[n-1] is the most significant bit,
     * where n is the length of the array.
     */
    BitN(Bit... bits) {
        this(Arrays.stream(bits).mapToInt(BitN::intValue).toArray());
    }

    /**
     * Constructs a BitN object with given integer array.
     * bits[0] is the least significant bit, and bits[n-1] is the most significant bit,
     * where n is the length of the array.
     */
    BitN(int... bits) {
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

    /*
     * Returns bool value of the underlying bit representation.
     * */
    public final boolean boolValue() {
        return intValue() != 0;
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

    /**
     * Returns a proper hash code for the object
     */
    @Override
    public final int hashCode() {
        return _intValue;
    }

    /**
     * Performs compare operation with given object using {@link Utils#unsignedEqual(Object, Object)}
     */
    @Override
    public final boolean equals(Object obj) {
        return Utils.unsignedEqual(this, obj);
    }

    /**
     * Returns string representation of the object
     */
    @Override
    public String toString() {
        return Integer.toString(_intValue);
    }

    /**
     * Returns binary string representation of the object, prefixed with "0b"
     */
    public final String toBinaryString() {
        return "0b" + Utils.padLeft(Integer.toBinaryString(_intValue), _bitCount, '0');
    }

    /**
     * Converts this object to {@link Octet[]} according to given endianness.
     *
     * @param useBigEndian true for Big Endian, false for Little Endian
     * @param useMsb       In case of total number of bits is not divisible by eight, last octet will be padded right
     *                     if this parameter is true, or will be padded left.
     */
    public Octet[] toOctetArray(boolean useBigEndian, boolean useMsb) {
        int intValue = intValue();
        int bitCount = bitCount();

        int odd = bitCount % 8;

        if (odd != 0) {
            int spare = 8 - odd;
            if (useMsb) {
                bitCount += spare;
                intValue <<= spare;
            } else {
                int overflow = intValue & ((1 << odd) - 1);
                intValue = ((intValue << spare) & ~0xFF) | overflow;
                bitCount += spare;
            }
        }

        int octetCount = bitCount / 8;
        var octets = new Octet[octetCount];

        for (int i = 0; i < octetCount; i++) {
            octets[useBigEndian ? octetCount - i - 1 : i] = new Octet(intValue & 0xFF);
            intValue >>= 8;
        }

        return octets;
    }

    /**
     * Returns the bit at given index as an integer.
     * index 0 is the least significant and index k is the most significant.
     */
    public final int getBitI(int index) {
        if (index < 0 || index > 29)
            throw new IllegalArgumentException("invalid index");
        return (_intValue >> index) & 0b1;
    }

    /**
     * Returns the bit at given index a {@link Bit}.
     * index 0 is the least significant and index k is the most significant.
     */
    public final Bit getBit(int index) {
        return new Bit(getBitI(index));
    }

    /**
     * Returns the bit at given index a boolean.
     * index 0 is the least significant and index k is the most significant.
     */
    public final boolean getBitB(int index) {
        return getBitI(index) != 0;
    }

    /**
     * Returns the bits in range [start, end] as long.
     * If end index is smaller than start index, then start and end indexes are swapped.
     *
     * @param start is the start index of the bit in binary representation.
     *              [0] is the least significant bit, while [n-1] is the most significant bit
     * @param end   is the end index of the bit in binary representation.
     *              [0] is the least significant bit, while [n-1] is the most significant bit
     */
    public final long getBitRangeL(int start, int end) {
        if (end < start) {
            int temp = end;
            end = start;
            start = temp;
        }

        long val = 0;
        for (int i = end; i >= start; i--) {
            int bit = getBitI(i);
            val |= bit;

            if (i != start)
                val <<= 1L;
        }
        return val;
    }

    /**
     * Returns the bits in range [start, end] as integer.
     * If end index is smaller than start index, then start and end indexes are swapped.
     *
     * @param start is the start index of the bit in binary representation.
     *              [0] is the least significant bit, while [n-1] is the most significant bit
     * @param end   is the end index of the bit in binary representation.
     *              [0] is the least significant bit, while [n-1] is the most significant bit
     * @throws ArithmeticException is thrown if given range cannot fit into 32-bit signed integer.
     */
    public final int getBitRangeI(int start, int end) {
        int i = StrictMath.toIntExact(getBitRangeL(start, end));
        if (i < 0) throw new ArithmeticException();
        return i;
    }

    /**
     * Converts this object to {@link BitString}.
     */
    public BitString toBitString() {
        var bs = new BitString();
        for (int i = 0; i < bitCount(); i++) {
            bs.set(i, this.getBitB(i));
        }
        return bs;
    }
}
