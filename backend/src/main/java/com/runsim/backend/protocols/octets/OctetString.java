package com.runsim.backend.protocols.octets;

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
        return toHexString();
    }

    public String toHexString() {
        var sb = new StringBuilder();
        forEach(octet -> sb.append(String.format("%02x", octet.intValue)));
        return sb.toString();
    }

    @Override
    public Iterator<Octet> iterator() {
        return Arrays.stream(data).iterator();
    }
}
