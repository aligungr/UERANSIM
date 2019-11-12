package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EHandoverAttachSupported extends ProtocolEnum {
    public static final EHandoverAttachSupported NOT_SUPPORTED
            = new EHandoverAttachSupported(0b0, "not supported");
    public static final EHandoverAttachSupported SUPPORTED
            = new EHandoverAttachSupported(0b1, "supported");

    private EHandoverAttachSupported(int value, String name) {
        super(value, name);
    }

    public static EHandoverAttachSupported fromValue(int value) {
        return fromValueGeneric(EHandoverAttachSupported.class, value);
    }
}
