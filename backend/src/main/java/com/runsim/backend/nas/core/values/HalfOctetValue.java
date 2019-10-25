package com.runsim.backend.nas.core.values;

import com.runsim.backend.nas.core.BitInputStream;
import com.runsim.backend.nas.core.BitOutputStream;

public class HalfOctetValue extends NASValue {
    private int value;

    public HalfOctetValue() {
    }

    public HalfOctetValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void encode(BitOutputStream stream) {
        stream.writeHalfOctet(value);
    }

    @Override
    public void decode(BitInputStream stream) {
        this.value = stream.readHalfOctet();
    }

    @Override
    public String display() {
        return Integer.toString(value);
    }
}
