package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EImsVoPs3gpp extends ProtocolEnum {
    public static final EImsVoPs3gpp NOT_SUPPORTED
            = new EImsVoPs3gpp(0b0, "IMS voice over PS session not supported over 3GPP access");
    public static final EImsVoPs3gpp SUPPORTED
            = new EImsVoPs3gpp(0b1, "IMS voice over PS session supported over 3GPP access");

    private EImsVoPs3gpp(int value, String name) {
        super(value, name);
    }

    public static EImsVoPs3gpp fromValue(int value) {
        return fromValueGeneric(EImsVoPs3gpp.class, value);
    }
}
