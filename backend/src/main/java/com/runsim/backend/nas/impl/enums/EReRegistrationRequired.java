package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EReRegistrationRequired extends ProtocolEnum {
    public static final EReRegistrationRequired NOT_REQUIRED
            = new EReRegistrationRequired(0b0, "re-registration not required");
    public static final EReRegistrationRequired REQUIRED
            = new EReRegistrationRequired(0b1, "re-registration required");

    private EReRegistrationRequired(int value, String name) {
        super(value, name);
    }

    public static EReRegistrationRequired fromValue(int value) {
        return fromValueGeneric(EReRegistrationRequired.class, value);
    }
}
