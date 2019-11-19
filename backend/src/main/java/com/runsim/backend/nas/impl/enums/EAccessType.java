package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EAccessType extends ProtocolEnum {
    public static final EAccessType THREEGPP_ACCESS
            = new EAccessType(0b0, "3GPP access");
    public static final EAccessType NON_THREEGPP_ACCESS
            = new EAccessType(0b1, "Non-3GPP access");

    private EAccessType(int value, String name) {
        super(value, name);
    }

    public static EAccessType fromValue(int value) {
        return fromValueGeneric(EAccessType.class, value);
    }
}
