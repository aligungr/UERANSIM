package com.runsim.backend.protocols;

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
}
