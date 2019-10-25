package com.runsim.backend.nas.core.values;

import com.runsim.backend.nas.core.BitInputStream;
import com.runsim.backend.nas.core.BitOutputStream;

public class OctetValue extends NASValue {
    private int value;

    public OctetValue() {
    }

    public OctetValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void encode(BitOutputStream stream) {
        stream.writeOctet(value);
    }

    @Override
    public void decode(BitInputStream stream) {
        this.value = stream.readOctet();
    }

    @Override
    public String display() {
        return Integer.toString(value);
    }
}
