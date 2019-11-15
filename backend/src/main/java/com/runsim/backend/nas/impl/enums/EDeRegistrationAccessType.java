package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EDeRegistrationAccessType extends ProtocolEnum {
    public static final EDeRegistrationAccessType THREEGPP_ACCESS
            = new EDeRegistrationAccessType(0b01, "3GPP access");
    public static final EDeRegistrationAccessType NON_THREEGPP_ACCESS
            = new EDeRegistrationAccessType(0b10, "Non-3GPP access");
    public static final EDeRegistrationAccessType THREEGPP_ACCESS_AND_NON_THREEGPP_ACCESS
            = new EDeRegistrationAccessType(0b11, "3GPP access and non-3GPP access");

    private EDeRegistrationAccessType(int value, String name) {
        super(value, name);
    }

    public static EDeRegistrationAccessType fromValue(int value) {
        return fromValueGeneric(EDeRegistrationAccessType.class, value);
    }
}
