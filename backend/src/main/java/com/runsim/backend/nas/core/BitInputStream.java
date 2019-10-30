package com.runsim.backend.nas.core;

import com.runsim.backend.utils.Utils;

public class BitInputStream {
    private final int[] halfOctets;
    private final int length;
    private int index;

    public BitInputStream(String base16) {
        this(Utils.hexStringToByteArray(base16));
    }

    public BitInputStream(byte[] data) {
        this.length = data.length * 2;
        this.halfOctets = new int[this.length];
        for (int i = 0; i < data.length; i++) {
            byte value = data[i];
            this.halfOctets[2 * i] = (value >> 4) & 0xF;
            this.halfOctets[2 * i + 1] = value & 0xF;
        }
        this.index = 0;
    }

    public int readOctet() {
        return (readHalfOctet() << 4) | readHalfOctet();
    }

    public int readHalfOctet() {
        if (!hasNext())
            throw new RuntimeException("unexpected end of file");
        return halfOctets[index++];
    }

    public boolean hasNext() {
        return index < length;
    }
}
