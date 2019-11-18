package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ERegistrationRequested extends ProtocolEnum {
    public static final ERegistrationRequested NOT_REQUESTED
            = new ERegistrationRequested(0b0, "registration not requested");
    public static final ERegistrationRequested REQUESTED
            = new ERegistrationRequested(0b1, "registration requested");

    private ERegistrationRequested(int value, String name) {
        super(value, name);
    }

    public static ERegistrationRequested fromValue(int value) {
        return fromValueGeneric(ERegistrationRequested.class, value);
    }
}
