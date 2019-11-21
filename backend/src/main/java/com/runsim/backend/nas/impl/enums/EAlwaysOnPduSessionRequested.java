package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EAlwaysOnPduSessionRequested extends ProtocolEnum {
    public static final EAlwaysOnPduSessionRequested NOT_REQUESTED
            = new EAlwaysOnPduSessionRequested(0b0, "Always-on PDU session not requested");
    public static final EAlwaysOnPduSessionRequested REQUESTED
            = new EAlwaysOnPduSessionRequested(0b1, "Always-on PDU session requested");

    private EAlwaysOnPduSessionRequested(int value, String name) {
        super(value, name);
    }

    public static EAlwaysOnPduSessionRequested fromValue(int value) {
        return fromValueGeneric(EAlwaysOnPduSessionRequested.class, value);
    }
}
