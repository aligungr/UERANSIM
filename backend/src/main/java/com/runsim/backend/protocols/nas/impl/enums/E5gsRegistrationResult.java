package com.runsim.backend.protocols.nas.impl.enums;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class E5gsRegistrationResult extends ProtocolEnum {
    public static final E5gsRegistrationResult THREEGPP_ACCESS
            = new E5gsRegistrationResult(0b001, "3GPP access");
    public static final E5gsRegistrationResult NON_THREEGPP_ACCESS
            = new E5gsRegistrationResult(0b010, "Non-3GPP access");
    public static final E5gsRegistrationResult THREEGPP_ACCESS_AND_NON_THREEGPP_ACCESS
            = new E5gsRegistrationResult(0b011, "3GPP access and non-3GPP access");

    private E5gsRegistrationResult(int value, String name) {
        super(value, name);
    }

    public static E5gsRegistrationResult fromValue(int value) {
        return fromValueGeneric(E5gsRegistrationResult.class, value);
    }
}
