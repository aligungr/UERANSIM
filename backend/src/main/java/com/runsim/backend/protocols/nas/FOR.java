package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class FOR extends ProtocolEnum {
    public static final FOR NO_FOR_PENDING
            = new FOR(0b0, "No follow-on request pending");
    public static final FOR FOR_PENDING
            = new FOR(0b1, "Follow-on request pending");

    private FOR(int value, String name) {
        super(value, name);
    }

    public static FOR fromValue(int value) {
        return fromValueGeneric(FOR.class, value, null);
    }
}
