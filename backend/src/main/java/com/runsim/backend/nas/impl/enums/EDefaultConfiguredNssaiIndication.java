package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EDefaultConfiguredNssaiIndication extends ProtocolEnum {
    public static final EDefaultConfiguredNssaiIndication NOT_CREATED_FROM_DEFAULT_CONFIGURED_NSSAI
            = new EDefaultConfiguredNssaiIndication(0b0, "Requested NSSAI not created from default configured NSSAI");
    public static final EDefaultConfiguredNssaiIndication CREATED_FROM_DEFAULT_CONFIGURED_NSSAI
            = new EDefaultConfiguredNssaiIndication(0b1, "Requested NSSAI created from default configured NSSAI");

    private EDefaultConfiguredNssaiIndication(int value, String name) {
        super(value, name);
    }

    public static EDefaultConfiguredNssaiIndication fromValue(int value) {
        return fromValueGeneric(EDefaultConfiguredNssaiIndication.class, value);
    }
}
