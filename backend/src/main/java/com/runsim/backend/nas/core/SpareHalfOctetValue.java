package com.runsim.backend.nas.core;

public final class SpareHalfOctetValue extends HalfOctetValue {

    @Override
    public void decode(BitInputStream stream) {
        stream.readHalfOctet();
    }

    @Override
    public String display() {
        return "Spare Half Octet";
    }
}
