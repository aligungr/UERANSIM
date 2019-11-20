package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EMultiHomedIPv6PduSession extends ProtocolEnum {
    public static final EMultiHomedIPv6PduSession NOT_SUPPORTED
            = new EMultiHomedIPv6PduSession(0b0, "Multi-homed IPv6 PDU session not supported");
    public static final EMultiHomedIPv6PduSession SUPPORTED
            = new EMultiHomedIPv6PduSession(0b1, "Multi-homed IPv6 PDU session supported");

    private EMultiHomedIPv6PduSession(int value, String name) {
        super(value, name);
    }

    public static EMultiHomedIPv6PduSession fromValue(int value) {
        return fromValueGeneric(EMultiHomedIPv6PduSession.class, value);
    }
}
