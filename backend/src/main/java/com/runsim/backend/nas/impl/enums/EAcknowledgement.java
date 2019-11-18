package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EAcknowledgement extends ProtocolEnum {
    public static final EAcknowledgement NOT_REQUESTED
            = new EAcknowledgement(0b0, "acknowledgement not requested");
    public static final EAcknowledgement REQUESTED
            = new EAcknowledgement(0b1, "acknowledgement requested");

    private EAcknowledgement(int value, String name) {
        super(value, name);
    }

    public static EAcknowledgement fromValue(int value) {
        return fromValueGeneric(EAcknowledgement.class, value);
    }
}
