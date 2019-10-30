package com.runsim.backend.nas.core;

import java.util.ArrayList;
import java.util.List;

public class BitOutputStream {
    private final List<Integer> halfOctets;

    public BitOutputStream() {
        this.halfOctets = new ArrayList<>();
    }

    public void writeOctet(int value) {
        writeHalfOctet((value >> 4) & 0xF);
        writeHalfOctet(value & 0xF);
    }

    public void writeHalfOctet(int value) {
        halfOctets.add(value);
    }

    public byte[] toByteArray() {
        var halfOctets = this.halfOctets;

        int length = halfOctets.size();
        if (length % 2 != 0)
            throw new RuntimeException("odd number of half octets");

        var res = new byte[length / 2];
        for (int i = 0; i < res.length; i++) {
            int ms = halfOctets.get(2 * i);
            int ls = halfOctets.get(2 * i + 1);
            res[i] = (byte) ((ms << 4) | ls);
        }

        return res;
    }
}
