package com.runsim.backend.protocols.core;

import com.runsim.backend.protocols.octets.Octet;
import com.runsim.backend.protocols.octets.OctetString;

public class OctetInputStream {
    private final byte[] data;
    private final int length;
    private int index;

    public OctetInputStream(byte[] data) {
        this.data = data;
        this.length = data.length;
        this.index = 0;
    }

    public int readOctet() {
        return data[index++];
    }

    public int peekOctet() {
        return data[index];
    }

    public int peekOctet(int offset) {
        return data[index + offset];
    }

    public int readOctet2() {
        var res = (data[index] << 8) | data[index + 1];
        index += 2;
        return res;
    }

    public int peekOctet2() {
        return (data[index] << 8) | data[index + 1];
    }

    public int readOctet3() {
        var res = (data[index] << 16) | (data[index + 1] << 8) | data[index + 2];
        index += 3;
        return res;
    }

    public int peekOctet3() {
        return (data[index] << 16) | (data[index + 1] << 8) | data[index + 2];
    }

    public boolean hasNext() {
        return index < length;
    }

    public Octet[] readOctets(int length) {
        Octet[] res = new Octet[length];
        for (int i = 0; i < res.length; i++)
            res[i] = new Octet(data[index + i]);
        index += length;
        return res;
    }

    public OctetString readOctetString(int length) {
        return new OctetString(readOctets(length));
    }

    public Octet[] peekOctets(int length) {
        Octet[] res = new Octet[length];
        for (int i = 0; i < res.length; i++)
            res[i] = new Octet(data[index + i]);
        return res;
    }

    public OctetString peekOctetString(int length) {
        return new OctetString(peekOctets(length));
    }
}
