package tr.havelsan.ueransim.utils.bits;

import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.BitSet;
import java.util.Iterator;

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

    public Iterator<Bit> bitIterator() {
        return bitSet.stream().mapToObj(Bit::new).iterator();
    }

    public Iterator<Boolean> booleanIterator() {
        return bitSet.stream().mapToObj(i -> i != 0).iterator();
    }

    public Iterator<Integer> integerIterator() {
        return bitSet.stream().iterator();
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

    public byte[] toByteArray() {
        return bitSet.toByteArray();
    }

    public int[] toIntArray() {
        byte[] arr = toByteArray();
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i] & 0xFF;
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitLength(); i++) {
            sb.append(getI(i));
        }
        return sb.toString();
    }

    public String toHexString() {
        return toOctetString().toHexString();
    }

    @Override
    public String toString() {
        return toHexString();
    }
}
