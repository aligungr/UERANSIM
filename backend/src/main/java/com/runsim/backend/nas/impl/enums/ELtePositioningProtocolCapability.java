package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ELtePositioningProtocolCapability extends ProtocolEnum {
    public static final ELtePositioningProtocolCapability NOT_SUPPORTED
            = new ELtePositioningProtocolCapability(0b1, "LPP in N1 mode not supported");
    public static final ELtePositioningProtocolCapability SUPPORTED
            = new ELtePositioningProtocolCapability(0b0, "LPP in N1 mode supported");

    private ELtePositioningProtocolCapability(int value, String name) {
        super(value, name);
    }

    public static ELtePositioningProtocolCapability fromValue(int value) {
        return fromValueGeneric(ELtePositioningProtocolCapability.class, value);
    }
}
