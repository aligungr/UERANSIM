package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EEpcNasSupported extends ProtocolEnum {
    public static final EEpcNasSupported NOT_SUPPORTED
            = new EEpcNasSupported(0b1, "S1 mode not supported");
    public static final EEpcNasSupported SUPPORTED
            = new EEpcNasSupported(0b0, "S1 mode supported");

    private EEpcNasSupported(int value, String name) {
        super(value, name);
    }

    public static EEpcNasSupported fromValue(int value) {
        return fromValueGeneric(EEpcNasSupported.class, value);
    }
}
