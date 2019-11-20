package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EReflectiveQoS extends ProtocolEnum {
    public static final EReflectiveQoS NOT_SUPPORTED
            = new EReflectiveQoS(0b0, "Reflective QoS not supported");
    public static final EReflectiveQoS SUPPORTED
            = new EReflectiveQoS(0b1, "Reflective QoS supported");

    private EReflectiveQoS(int value, String name) {
        super(value, name);
    }

    public static EReflectiveQoS fromValue(int value) {
        return fromValueGeneric(EReflectiveQoS.class, value);
    }
}
