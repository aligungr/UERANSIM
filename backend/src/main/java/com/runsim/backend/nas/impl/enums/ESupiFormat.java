package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ESupiFormat extends ProtocolEnum {
    public static final ESupiFormat IMSI
            = new ESupiFormat(0b000, "IMSI");
    public static final ESupiFormat NETWORK_SPECIFIC_IDENTIFIER
            = new ESupiFormat(0b001, "Network Specific Identifier");

    private ESupiFormat(int value, String name) {
        super(value, name);
    }

    public static ESupiFormat fromValue(int value) {
        return fromValueGeneric(ESupiFormat.class, value, ESupiFormat.IMSI);
    }
}
