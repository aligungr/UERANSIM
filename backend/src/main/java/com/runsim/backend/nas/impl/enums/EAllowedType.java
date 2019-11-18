package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EAllowedType extends ProtocolEnum {
    public static final EAllowedType IN_THE_ALLOWED_AREA
            = new EAllowedType(0b0, "TAIs in the list are in the allowed area");
    public static final EAllowedType IN_THE_NON_ALLOWED_AREA
            = new EAllowedType(0b1, "TAIs in the list are in the non-allowed area");

    private EAllowedType(int value, String name) {
        super(value, name);
    }

    public static EAllowedType fromValue(int value) {
        return fromValueGeneric(EAllowedType.class, value);
    }
}
