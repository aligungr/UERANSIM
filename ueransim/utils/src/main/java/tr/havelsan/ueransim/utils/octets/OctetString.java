package tr.havelsan.ueransim.utils.octets;

import tr.havelsan.ueransim.utils.Utils;

import java.util.Arrays;
import java.util.Iterator;

public final class OctetString implements Iterable<Octet> {
    public final Octet[] data;
    public final int length;

    public OctetString(Octet[] octets) {
        this.data = octets;
        this.length = octets.length;
    }

    public OctetString(int[] octetInts) {
        var data = new Octet[octetInts.length];
        for (int i = 0; i < octetInts.length; i++)
            data[i] = new Octet(octetInts[i]);

        this.data = data;
        this.length = data.length;
    }

    public OctetString(byte[] octetBytes) {
        var data = new Octet[octetBytes.length];
        for (int i = 0; i < octetBytes.length; i++)
            data[i] = new Octet(octetBytes[i] & 0xFF);

        this.data = data;
        this.length = data.length;
    }

    public OctetString(String hex) {
        this(Utils.hexStringToByteArray(hex));
    }

    public Octet get(int index) {
        return data[index];
    }

    public Octet[] getAsArray() {
        var res = new Octet[length];
        System.arraycopy(data, 0, res, 0, length);
        return res;
    }

    @Override
    public String toString() {
        return toHexString(true);
    }

    public String toHexString() {
        return this.toHexString(false);
    }

    public String toHexString(boolean withSpace) {
        var sb = new StringBuilder();
        forEach(octet -> {
            sb.append(String.format("%02x", octet.intValue()));
            if (withSpace) sb.append(' ');
        });
        return sb.toString().trim();
    }

    @Override
    public Iterator<Octet> iterator() {
        return Arrays.stream(data).iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OctetString))
            return false;
        var os = (OctetString) obj;
        if (os.length != this.length)
            return false;
        for (int i = 0; i < os.length; i++) {
            if (os.data[i].intValue() != this.data[i].intValue())
                return false;
        }
        return true;
    }

    public byte[] toByteArray() {
        var octetArray = getAsArray();
        var byteArray = new byte[octetArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) (octetArray[i].longValue() & 0xFF);
        }
        return byteArray;
    }

    public OctetString substring(int startIndex) {
        var data = new Octet[this.length - startIndex];
        System.arraycopy(this.data, startIndex, data, 0, data.length);
        return new OctetString(data);
    }

    public OctetString substring(int startIndex, int length) {
        var data = new Octet[length];
        System.arraycopy(this.data, startIndex, data, 0, data.length);
        return new OctetString(data);
    }
}
