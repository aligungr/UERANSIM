package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ESmsRequested extends ProtocolEnum {
    public static final ESmsRequested NOT_SUPPORTED
            = new ESmsRequested(0b0, "SMS over NAS not supported");
    public static final ESmsRequested SUPPORTED
            = new ESmsRequested(0b1, "SMS over NAS supported");

    private ESmsRequested(int value, String name) {
        super(value, name);
    }

    public static ESmsRequested fromValue(int value) {
        return fromValueGeneric(ESmsRequested.class, value);
    }
}
