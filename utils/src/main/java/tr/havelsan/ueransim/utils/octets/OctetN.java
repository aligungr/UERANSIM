/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils.octets;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.BitString;

/**
 * Represents 1 to 7 octet unsigned integer.
 */
public class OctetN {
    private final long _longValue;
    private final byte _octetCount;

    /**
     * Constructs new OctetN instance with given parameters.
     *
     * @param longValue  is the underlying unsigned integral value. This value cannot be negative.
     * @param octetCount is the total number of octets to represent this value.
     *                   Given value is masked with ((2 ^ (8 * octetCount)) - 1).
     *                   Octet count cannot exceeds 7 since implementation uses 64-bit signed integer.
     */
    /*package-private*/ OctetN(long longValue, int octetCount) {
        // maximum 7 octet, since implementation uses 64-bit signed integer.
        if (octetCount < 0 || octetCount > 7)
            throw new IllegalArgumentException("invalid octet count");
        if (longValue < 0)
            throw new IllegalArgumentException("negative value");

        this._longValue = longValue & ((1L << (8 * octetCount)) - 1);
        this._octetCount = (byte) octetCount;
    }

    /**
     * Converts underlying value as 64-bit signed integer.
     * (Underlying unsigned octet-n value always fits into 64-bit signed integer.)
     */
    public final long longValue() {
        return _longValue;
    }

    /**
     * Converts underlying value as 32-bit signed integer.
     *
     * @throws ArithmeticException is thrown if the value cannot fit into 32-bit signed integer.
     */
    public final int intValue() {
        int i = Math.toIntExact(_longValue);
        if (i < 0) throw new ArithmeticException();
        return i;
    }

    /**
     * Returns the octet count of this object.
     * This value is <b>not</b> the minimum number of octets to represent integral value.
     * Octet count is given at construction either explicitly, or implicitly by subtypes.
     */
    public final int octetCount() {
        return _octetCount;
    }

    /**
     * Returns string representation of the value.
     */
    @Override
    public String toString() {
        return toHexString();
    }

    /**
     * Returns hexadecimal string representation of the value.
     */
    public final String toHexString() {
        return "0x" + Utils.padLeft(Long.toHexString(_longValue), _octetCount * 2, '0');
    }

    /**
     * Returns binary string representation of the value.
     */
    public final String toBinaryString() {
        return "0b" + Utils.padLeft(Long.toBinaryString(_longValue), _octetCount * 8, '0');
    }

    /**
     * Returns the bit as an integer at given index.
     *
     * @param index is the index of the bit in binary representation.
     *              [0] is the least significant bit, while [n-1] is the most significant bit
     */
    public final int getBitI(int index) {
        return (int) ((_longValue >> index) & 0b1);
    }

    /**
     * Returns the bit as a boolean at given index.
     *
     * @param index is the index of the bit in binary representation.
     *              [0] is the least significant bit, while [n-1] is the most significant bit
     */
    public final boolean getBitB(int index) {
        return getBitI(index) != 0;
    }

    /**
     * Returns the bit as {@link Bit} at given index.
     *
     * @param index is the index of the bit in binary representation.
     *              [0] is the least significant bit, while [n-1] is the most significant bit
     */
    public final Bit getBit(int index) {
        return new Bit(getBitI(index));
    }

    /**
     * Assigns the bit at given index and returns a new instance.
     *
     * @param index is the index of the bit in binary representation.
     *              [0] is the least significant bit, while [n-1] is the most significant bit.
     * @param bit   new bit value to be assigned
     */
    public OctetN setBit(int index, int bit) {
        return new OctetN((bit & 1) == 0 ? _longValue & ~(1L << index) : _longValue | (1L << index), _octetCount);
    }

    /**
     * Assigns the bit at given index and returns a new instance.
     *
     * @param index is the index of the bit in binary representation.
     *              [0] is the least significant bit, while [n-1] is the most significant bit.
     * @param bit   new bit value to be assigned
     */
    public OctetN setBit(int index, Bit bit) {
        return setBit(index, bit.intValue());
    }

    /**
     * Assigns the bit at given index and returns a new instance.
     *
     * @param index is the index of the bit in binary representation.
     *              [0] is the least significant bit, while [n-1] is the most significant bit.
     * @param bit   new bit value to be assigned
     */
    public OctetN setBit(int index, boolean bit) {
        return setBit(index, bit ? 1 : 0);
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
     * Returns the bits in range [start, end] as integer.
     * If end index is smaller than start index, then start and end indexes are swapped.
     *
     * @param start is the start index of the bit in binary representation.
     *              [0] is the least significant bit, while [n-1] is the most significant bit
     * @param end   is the end index of the bit in binary representation.
     *              [0] is the least significant bit, while [n-1] is the most significant bit
     * @param value is the value to be assigned.
     */
    public OctetN setBitRange(int start, int end, long value) {
        OctetN octetN = new OctetN(_longValue, _octetCount);
        OctetN val = new OctetN(value, _octetCount);
        int length = end - start + 1;
        for (int i = 0; i < length; i++)
            octetN = octetN.setBit(start + i, val.getBitI(i));
        return octetN;
    }

    /**
     * Returns true iff the underlying value is semantically assumed to be equal to given object.
     * see {@link Utils#unsignedEqual(Object, Object)}
     */
    @Override
    public final boolean equals(Object obj) {
        return Utils.unsignedEqual(this, obj);
    }

    /**
     * Returns the underlying value as an array of {@link Octet}s according to given endianness.
     * Size of the returning array is equal to {@link #octetCount()}.
     *
     * @param useBigEndian should be set true to use Big Endian, or false for Little Endian
     */
    public final Octet[] toOctetArray(boolean useBigEndian) {
        var octets = new Octet[octetCount()];
        if (!useBigEndian) {
            for (int i = 0; i < octets.length; i++) {
                octets[i] = new Octet((int) ((_longValue >> (8 * i)) & 0xFF));
            }
        } else {
            for (int i = 0; i < octets.length; i++) {
                octets[octets.length - i - 1] = new Octet((int) ((_longValue >> (8 * i)) & 0xFF));
            }
        }
        return octets;
    }

    /**
     * Returns the underlying value as an array of {@link Octet}s using <b>Big Endian</b>.
     * see {@link #toOctetArray(boolean)}.
     */
    public final Octet[] toOctetArray() {
        return toOctetArray(true);
    }

    /**
     * Returns the underlying value as an array of bytes according to given endianness.
     * Size of the returning array is equal to {@link #octetCount()}.
     *
     * @param useBigEndian should be set true to use Big Endian, or false for Little Endian
     */
    public final byte[] toByteArray(boolean useBigEndian) {
        var octets = new byte[octetCount()];
        if (!useBigEndian) {
            for (int i = 0; i < octets.length; i++) {
                octets[i] = (byte) ((int) ((_longValue >> (8 * i)) & 0xFF));
            }
        } else {
            for (int i = 0; i < octets.length; i++) {
                octets[octets.length - i - 1] = (byte) ((int) ((_longValue >> (8 * i)) & 0xFF));
            }
        }
        return octets;
    }

    /**
     * Returns the underlying value as an array of bytes using <b>Big Endian</b>.
     * see {@link #toByteArray(boolean)}.
     */
    public final byte[] toByteArray() {
        return toByteArray(true);
    }

    /**
     * Returns the underlying value as an {@link OctetString} according to given endianness.
     * Size of the returning string is equal to {@link #octetCount()}.
     *
     * @param useBigEndian should be set true to use Big Endian, or false for Little Endian
     */
    public final OctetString toOctetString(boolean useBigEndian) {
        return new OctetString(toByteArray(useBigEndian));
    }

    /**
     * Returns the underlying value as an {@link OctetString} using <b>Big Endian</b>.
     * see {@link #toOctetString(boolean)}.
     */
    public final OctetString toOctetString() {
        return toOctetString(true);
    }

    /**
     * Returns the underlying value as an {@link BitString} according to given endianness.
     *
     * @param useBigEndian should be set true to use Big Endian, or false for Little Endian
     */
    public final BitString toBitString(boolean useBigEndian) {
        return BitString.from(toByteArray(useBigEndian));
    }

    /**
     * Returns the underlying value as an {@link BitString} using <b>Big Endian</b>.
     * see {@link #toBitString(boolean)}.
     */
    public final BitString toBitString() {
        return toBitString(true);
    }
}
