package tr.havelsan.ueransim.utils.bits;

import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetN;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class BitString {
    private final List<Boolean> bits;

    public BitString() {
        this.bits = new ArrayList<>();
    }

    public static void copy(BitString source, int sourceOffset, BitString destination, int destinationOffset, int length) {
        for (int i = 0; i < length; i++) {
            destination.set(destinationOffset + i, source.getB(sourceOffset + i));
        }
    }

    public static BitString from(OctetN octets, boolean useBigEndian) {
        return from(octets.toOctetArray(useBigEndian));
    }

    public static BitString from(OctetString octetString) {
        return from(octetString.getAsArray());
    }

    public static BitString from(Octet[] octets) {
        var res = new BitString();
        for (int i = 0; i < octets.length; i++) {
            var octet = octets[i];
            for (int j = 0; j < 8; j++) {
                boolean bit = octet.getBitB(j);
                res.set(i * 8 + (7 - j), bit);
            }
        }
        return res;
    }

    public static BitString from(byte[] octets) {
        return from(new OctetString(octets));
    }

    public static BitString fromHex(String hex) {
        return from(new OctetString(hex));
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
        expand(index);
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
        int[] res = new int[octetLength()];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < 8; j++) {
                boolean bit = getB(i * 8 + j);
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

    public String toHexString() {
        return toOctetString().toHexString();
    }

    @Override
    public String toString() {
        return toBinaryString(true);
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
}
