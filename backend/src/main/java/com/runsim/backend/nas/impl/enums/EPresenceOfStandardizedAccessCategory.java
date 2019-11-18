package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EPresenceOfStandardizedAccessCategory extends ProtocolEnum {
    public static final EPresenceOfStandardizedAccessCategory NOT_INCLUDED
            = new EPresenceOfStandardizedAccessCategory(0b0, "Standardized access category field is not included");
    public static final EPresenceOfStandardizedAccessCategory INCLUDED
            = new EPresenceOfStandardizedAccessCategory(0b1, "Standardized access category field is included");

    private EPresenceOfStandardizedAccessCategory(int value, String name) {
        super(value, name);
    }

    public static EPresenceOfStandardizedAccessCategory fromValue(int value) {
        return fromValueGeneric(EPresenceOfStandardizedAccessCategory.class, value);
    }
}
