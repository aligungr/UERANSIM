package com.runsim.backend.nas;

import com.runsim.backend.nas.exceptions.NotImplementedException;

public class NasInputStream {
    private final byte[] data;
    private final int length;

    public NasInputStream(byte[] data) {
        this.data = data;
        this.length = data.length;
    }

    public int readOctet() {
        throw new NotImplementedException("");
    }
}
