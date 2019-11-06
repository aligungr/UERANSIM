package com.runsim.backend.protocols.nas.impl.enums;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class EFollowOnRequest extends ProtocolEnum {
    public static final EFollowOnRequest NO_FOR_PENDING
            = new EFollowOnRequest(0b0, "No follow-on request pending");
    public static final EFollowOnRequest FOR_PENDING
            = new EFollowOnRequest(0b1, "Follow-on request pending");

    private EFollowOnRequest(int value, String name) {
        super(value, name);
    }

    public static EFollowOnRequest fromValue(int value) {
        return fromValueGeneric(EFollowOnRequest.class, value);
    }
}
