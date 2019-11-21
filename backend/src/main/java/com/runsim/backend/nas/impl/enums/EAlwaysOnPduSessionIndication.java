package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EAlwaysOnPduSessionIndication extends ProtocolEnum {
    public static final EAlwaysOnPduSessionIndication NOT_ALLOWED
            = new EAlwaysOnPduSessionIndication(0b0, "Always-on PDU session not allowed");
    public static final EAlwaysOnPduSessionIndication REQUIRED
            = new EAlwaysOnPduSessionIndication(0b1, "Always-on PDU session required");

    private EAlwaysOnPduSessionIndication(int value, String name) {
        super(value, name);
    }

    public static EAlwaysOnPduSessionIndication fromValue(int value) {
        return fromValueGeneric(EAlwaysOnPduSessionIndication.class, value);
    }
}
