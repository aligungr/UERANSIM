package com.runsim.backend.utils.octets;

import com.runsim.backend.utils.Utils;
import com.runsim.backend.utils.bits.Bit;

public class OctetN {
    private final long _longValue;
    private final byte _octetCount;

    public OctetN(long longValue, int octetCount) {
        // maximum 7 octet, since implementation uses int64.
        if (octetCount < 0 || octetCount > 7)
            throw new IllegalArgumentException("invalid octet count");
        if (longValue < 0)
            throw new IllegalArgumentException("negative value");

        long octetMaximum = (1L << (octetCount * 8)) - 1;
        if (longValue > octetMaximum)
            throw new IllegalArgumentException("longValue exceeds the octetMaximum");

        this._longValue = longValue & ((1L << (8 * octetCount)) - 1);
        this._octetCount = (byte) octetCount;
    }

    public final long longValue() {
        return _longValue;
    }

    public final int intValue() {
        int i = Math.toIntExact(_longValue);
        if (i < 0) throw new ArithmeticException();
        return i;
    }

    public final int octetCount() {
        return _octetCount;
    }

    @Override
    public final String toString() {
        return toHexString();
    }

    public final String toHexString() {
        return "0x" + Utils.padLeft(Long.toHexString(_longValue), _octetCount, '0');
    }

    public final String toBinaryString() {
        return "0b" + Utils.padLeft(Long.toBinaryString(_longValue), _octetCount * 8, '0');
    }

    public final int getBitI(int index) {
        return (int) ((_longValue >> index) & 0b1);
    }

    public final boolean getBitB(int index) {
        return getBitI(index) != 0;
    }

    public final Bit getBit(int index) {
        return new Bit(getBitI(index));
    }

    public final OctetN setBit(int index, int bit) {
        return new OctetN(bit == 0 ? _longValue & ~(1L << index) : _longValue | (1L << index), _octetCount);
    }

    public final OctetN setBit(int index, Bit bit) {
        return setBit(index, bit.intValue());
    }

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

    public final int getBitRangeI(int start, int end) {
        return StrictMath.toIntExact(getBitRangeL(start, end));
    }

    public final OctetN setBitRange(int start, int end, long value) {
        OctetN octetN = new OctetN(_longValue, _octetCount);
        OctetN val = new OctetN(value, _octetCount);

        int length = end - start + 1;
        for (int i = 0; i < length; i++) {
            octetN = octetN.setBit(start + i, val.getBitI(i));
        }

        return octetN;
    }

    @Override
    public final boolean equals(Object obj) {
        return Utils.unsignedEqual(this, obj);
    }

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

    public final Octet[] toOctetArray() {
        return toOctetArray(true);
    }
}
