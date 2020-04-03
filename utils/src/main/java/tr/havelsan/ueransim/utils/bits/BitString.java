package tr.havelsan.ueransim.utils.bits;

import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetN;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.BitSet;

public final class BitString {
    private final BitSet bitSet;

    public BitString() {
        this.bitSet = new BitSet();
    }

    public static void copy(BitString source, int sourceOffset, BitString destination, int destinationOffset, int length) {
        for (int i = 0; i < length; i++) {
            destination.set(destinationOffset + i, source.getB(sourceOffset + i));
        }
    }

    public static BitString from(OctetN octets, boolean useBigEndian, boolean firstMsb) {
        return from(octets.toOctetArray(useBigEndian), firstMsb);
    }

    public static BitString from(OctetString octetString, boolean firstMsb) {
        return from(octetString.getAsArray(), firstMsb);
    }

    public static BitString from(Octet[] octets, boolean firstMsb) {
        var res = new BitString();
        for (int i = 0; i < octets.length; i++) {
            var octet = octets[i];
            for (int j = 0; j < 8; j++) {
                boolean bit = octet.getBitB(j);
                int k = firstMsb ? 7 - j : j;
                res.set(i * 8 + k, bit);
            }
        }
        return res;
    }

    public void set(int index) {
        set(index, true);
    }

    public void set(int index, boolean value) {
        bitSet.set(index, value);
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
        return bitSet.get(index);
    }

    public int getI(int index) {
        return getB(index) ? 1 : 0;
    }

    public int bitLength() {
        return bitSet.cardinality();
    }

    public int octetLength() {
        return (int) Math.ceil(bitLength() / 8.0);
    }

    public int[] toIntArray(boolean firstMsb) {
        int[] res = new int[octetLength()];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < 8; j++) {
                boolean bit = getB(i * 8 + j);
                if (bit) {
                    int k = firstMsb ? 7 - j : j;
                    res[i] |= 0b1 << k;
                }
            }
        }
        return res;
    }

    public int[] toIntArray() {
        return toIntArray(true);
    }

    public byte[] toByteArray(boolean firstMsb) {
        int[] arr = toIntArray(firstMsb);
        byte[] res = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = (byte) (arr[i] & 0xFF);
        }
        return res;
    }

    public byte[] toByteArray() {
        return toByteArray(true);
    }

    public Octet[] toOctetArray(boolean firstMsb) {
        int[] arr = toIntArray(firstMsb);
        Octet[] res = new Octet[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = new Octet(arr[i]);
        }
        return res;
    }

    public Octet[] toOctetArray() {
        return toOctetArray(true);
    }

    public OctetString toOctetString(boolean firstMsb) {
        return new OctetString(toOctetArray(firstMsb));
    }

    public OctetString toOctetString() {
        return toOctetString(true);
    }

    public String toBinaryString() {
        var sb = new StringBuilder();
        for (int i = 0; i < bitLength(); i++) {
            sb.append(getI(i));
        }
        return sb.toString();
    }

    public String toHexString() {
        return toHexString(true);
    }

    public String toHexString(boolean firstMsb) {
        return toOctetString(firstMsb).toHexString();
    }

    @Override
    public String toString() {
        return toBinaryString();
    }
}
