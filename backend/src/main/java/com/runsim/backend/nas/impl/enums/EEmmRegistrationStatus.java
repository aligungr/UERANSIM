package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EEmmRegistrationStatus extends ProtocolEnum {
    public static final EEmmRegistrationStatus NOT_REGISTERED
            = new EEmmRegistrationStatus(0b0, "UE is not in EMM-REGISTERED state");
    public static final EEmmRegistrationStatus REGISTERED
            = new EEmmRegistrationStatus(0b1, "UE is in EMM-REGISTERED state");

    private EEmmRegistrationStatus(int value, String name) {
        super(value, name);
    }

    public static EEmmRegistrationStatus fromValue(int value) {
        return fromValueGeneric(EEmmRegistrationStatus.class, value);
    }
}
