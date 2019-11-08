package com.runsim.backend.utils.octets;

import com.runsim.backend.utils.Utils;

import java.util.Arrays;
import java.util.Iterator;

public class OctetString implements Iterable<Octet> {
    public final Octet[] data;
    public final int length;

    public OctetString(Octet[] octets) {
        this.data = octets;
        this.length = octets.length;
    }

    public OctetString(int[] octets) {
        var data = new Octet[octets.length];
        for (int i = 0; i < octets.length; i++)
            data[i] = new Octet(octets[i]);

        this.data = data;
        this.length = data.length;
    }

    public OctetString(byte[] octets) {
        var data = new Octet[octets.length];
        for (int i = 0; i < octets.length; i++)
            data[i] = new Octet(octets[i] & 0xFF);

        this.data = data;
        this.length = data.length;
    }

    public OctetString(String base16) {
        this(Utils.hexStringToByteArray(base16));
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
}
