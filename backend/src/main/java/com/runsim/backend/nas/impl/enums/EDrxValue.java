package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EDrxValue extends ProtocolEnum {

    public static final EDrxValue NOT_SPECIFIED
            = new EDrxValue(0b0000, "DRX value not specified");
    public static final EDrxValue T32
            = new EDrxValue(0b0001, "DRX cycle parameter T = 32");
    public static final EDrxValue T64
            = new EDrxValue(0b0010, "DRX cycle parameter T = 64");
    public static final EDrxValue T128
            = new EDrxValue(0b0011, "DRX cycle parameter T = 128");
    public static final EDrxValue T256
            = new EDrxValue(0b0100, "DRX cycle parameter T = 256");

    private EDrxValue(int value, String name) {
        super(value, name);
    }

    public static EDrxValue fromValue(int value) {
        return fromValueGeneric(EDrxValue.class, value);
    }
}
