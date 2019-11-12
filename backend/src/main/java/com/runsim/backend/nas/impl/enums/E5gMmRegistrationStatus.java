package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class E5gMmRegistrationStatus extends ProtocolEnum {
    public static final E5gMmRegistrationStatus NOT_REGISTERED
            = new E5gMmRegistrationStatus(0b0, "UE is not in 5GMM-REGISTERED state");
    public static final E5gMmRegistrationStatus REGISTERED
            = new E5gMmRegistrationStatus(0b1, "UE is in 5GMM-REGISTERED state");

    private E5gMmRegistrationStatus(int value, String name) {
        super(value, name);
    }

    public static E5gMmRegistrationStatus fromValue(int value) {
        return fromValueGeneric(E5gMmRegistrationStatus.class, value);
    }
}
