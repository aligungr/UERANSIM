package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class SUPIFormat extends ProtocolEnum {
    public static final SUPIFormat IMSI
            = new SUPIFormat(0b000, "IMSI");
    public static final SUPIFormat NETWORK_SPECIFIC_IDENTIFIER
            = new SUPIFormat(0b001, "Network Specific Identifier");

    private SUPIFormat(int value, String name) {
        super(value, name);
    }

    public static SUPIFormat fromValue(int value) {
        return fromValueGeneric(SUPIFormat.class, value, null);
    }
}
