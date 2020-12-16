/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils.bits;

import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO: Optimize this
public final class BitString {
    private final List<Boolean> bits;

    public BitString() {
        this.bits = new ArrayList<>();
    }

    public static void copy(BitString source, int sourceOffset, BitString destination, int destinationOffset, int bitLength) {
        for (int i = 0; i < bitLength; i++) {
            destination.set(destinationOffset + i, source.getB(sourceOffset + i));
        }
    }

    public static BitString from(OctetString octetString, int bitLength) {
        return from(octetString.getAsOctetArray(), bitLength);
    }

    public static BitString from(OctetString octetString) {
        return from(octetString, octetString.length * 8);
    }

    public static BitString from(Octet[] octets, int bitLength) {
        var res = new BitString();
        for (int i = 0; i < octets.length; i++) {
            if (res.bitLength() >= bitLength) {
                break;
            }
            var octet = octets[i];
            for (int j = 0; j < 8; j++) {
                boolean bit = octet.getBitB(j);
                res.set(i * 8 + (7 - j), bit);
            }
        }
        if (bitLength != res.bitLength()) {
            return res.substring(0, bitLength);
        }
        return res;
    }

    public static BitString from(Octet[] octets) {
        return from(octets, octets.length * 8);
    }

    public static BitString from(byte[] octets, int bitLength) {
        return from(new OctetString(octets), bitLength);
    }

    public static BitString from(byte[] octets) {
        return from(octets, octets.length * 8);
    }

    public static BitString fromHex(String hex, int bitLength) {
        return from(new OctetString(hex), bitLength);
    }

    public static BitString fromHex(String hex) {
        return from(new OctetString(hex));
    }

    public static BitString fromBits(String s) {
        var res = new BitString();
        for (int i = 0; i < s.length(); i++) {
            boolean b;
            char c = s.charAt(i);
            if (c == '0') b = false;
            else if (c == '1') b = true;
            else throw new IllegalArgumentException();
            res.set(i, b);
        }
        return res;
    }

    public static BitString xor(BitString a, BitString b) {
        if (a.bitLength() != b.bitLength()) {
            throw new IllegalArgumentException("bit lengths must be the same");
        }
        int length = a.bitLength();
        BitString res = new BitString();
        for (int i = 0; i < length; i++) {
            res.set(i, a.getB(i) ^ b.getB(i));
        }
        return res;
    }

    public static BitString reverse(BitString bitString) {
        var res = new BitString();
        for (int i = 0; i < bitString.bitLength(); i++) {
            res.set(bitString.bitLength() - i - 1, bitString.getB(i));
        }
        return res;
    }

    public BitString substring(int startIndex, int bitLength) {
        var res = new BitString();
        for (int i = 0; i < bitLength; i++) {
            res.set(i, this.getB(startIndex + i));
        }
        return res;
    }

    public BitString substring(int startIndex) {
        return substring(startIndex, this.bitLength() - startIndex);
    }

    public void set(int index) {
        set(index, true);
    }

    private void expand(int requiredIndex) {
        if (bits.size() <= requiredIndex) {
            int delta = requiredIndex - bits.size() + 1;
            for (int i = 0; i < delta; i++) {
                bits.add(false);
            }
        }
    }

    public void set(int index, boolean value) {
        expand(index);
        bits.set(index, value);
    }

    public void set(int fromIndex, int toIndex) {
        set(fromIndex, toIndex, true);
    }

    public void set(int fromIndex, int toIndex, boolean value) {
        for (int i = fromIndex; i <= toIndex; i++) {
            set(i, value);
        }
    }

    public void set(int fromIndex, int toIndex, Bit value) {
        set(fromIndex, toIndex, value.boolValue());
    }

    public void set(int index, Bit value) {
        set(index, value.boolValue());
    }

    public void clear(int index) {
        set(index, false);
    }

    public void clear(int fromIndex, int toIndex) {
        for (int i = fromIndex; i <= toIndex; i++) {
            clear(i);
        }
    }

    public void clear() {
        clear(0, bitLength() - 1);
    }

    public Bit get(int index) {
        return new Bit(getI(index));
    }

    public boolean getB(int index) {
        return bits.get(index);
    }

    public int getI(int index) {
        return getB(index) ? 1 : 0;
    }

    public int bitLength() {
        return bits.size();
    }

    public int octetLength() {
        return (int) Math.ceil(bitLength() / 8.0);
    }

    public int[] toIntArray() {
        int bitLength = bitLength();
        int octetLength = octetLength();
        if (octetLength == 0) {
            return new int[0];
        }

        int[] res = new int[octetLength];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < 8; j++) {
                int index = i * 8 + j;
                boolean bit = index < bitLength && getB(index);
                if (bit) {
                    res[i] |= 0b1 << (7 - j);
                }
            }
        }
        return res;
    }

    public byte[] toByteArray() {
        int[] arr = toIntArray();
        byte[] res = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = (byte) (arr[i] & 0xFF);
        }
        return res;
    }

    public Octet[] toOctetArray() {
        int[] arr = toIntArray();
        Octet[] res = new Octet[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = new Octet(arr[i]);
        }
        return res;
    }

    public OctetString toOctetString() {
        return new OctetString(toOctetArray());
    }

    public String toBinaryString() {
        return toBinaryString(false);
    }

    public String toBinaryString(boolean withSpace) {
        var sb = new StringBuilder();
        int length = bitLength();
        for (int i = 0; i < length; i++) {
            sb.append(getI(i));

            if (withSpace) {
                if ((i + 1) % 8 == 0) {
                    sb.append(' ');
                }
            }
        }
        return sb.toString();
    }

    public String toHexString(boolean withSpace) {
        return toOctetString().toHexString(withSpace);
    }

    public String toHexString() {
        return toHexString(false);
    }

    @Override
    public String toString() {
        return toBinaryString(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitString bitString = (BitString) o;
        return Objects.equals(bits, bitString.bits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bits);
    }

    public int intValue() {
        // TODO: Ya 31 ile kontrol edilsin veya unsigned olarak parse edilsin
        if (bitLength() > 32) {
            throw new IllegalStateException("BitString has more than 31 bits");
        }
        return Integer.parseInt(toBinaryString(false), 2);
    }

    public long longValue() {
        // TODO: Ya 63 ile kontrol edilsin veya unsigned olarak parse edilsin
        if (bitLength() > 64) {
            throw new IllegalStateException("BitString has more than 63 bits");
        }
        return Long.parseLong(toBinaryString(false), 2);
    }
}
