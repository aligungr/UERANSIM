package com.runsim.backend.protocols.nas.impl.enums;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class ESmsOverNasTransportAllowed extends ProtocolEnum {

    public static final ESmsOverNasTransportAllowed NOT_ALLOWED
            = new ESmsOverNasTransportAllowed(0b0, "SMS over NAS not allowed");
    public static final ESmsOverNasTransportAllowed ALLOWED
            = new ESmsOverNasTransportAllowed(0b1, "SMS over NAS allowed");

    private ESmsOverNasTransportAllowed(int value, String name) {
        super(value, name);
    }

    public static ESmsOverNasTransportAllowed fromValue(int value) {
        return fromValueGeneric(ESmsOverNasTransportAllowed.class, value);
    }
}
