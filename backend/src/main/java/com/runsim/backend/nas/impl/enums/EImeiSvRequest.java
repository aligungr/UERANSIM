package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EImeiSvRequest extends ProtocolEnum {
    public static final EImeiSvRequest NOT_REQUESTED
            = new EImeiSvRequest(0b000, "IMEISV not requested");
    public static final EImeiSvRequest REQUESTED
            = new EImeiSvRequest(0b001, "IMEISV requested");

    private EImeiSvRequest(int value, String name) {
        super(value, name);
    }

    public static EImeiSvRequest fromValue(int value) {
        return fromValueGeneric(EImeiSvRequest.class, value);
    }
}
