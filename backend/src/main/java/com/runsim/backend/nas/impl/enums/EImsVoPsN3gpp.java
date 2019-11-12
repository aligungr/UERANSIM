package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EImsVoPsN3gpp extends ProtocolEnum {
    public static final EImsVoPsN3gpp NOT_SUPPORTED
            = new EImsVoPsN3gpp(0b0, "IMS voice over PS session not supported over non-3GPP access");
    public static final EImsVoPsN3gpp SUPPORTED
            = new EImsVoPsN3gpp(0b1, "IMS voice over PS session supported over non-3GPP access");

    private EImsVoPsN3gpp(int value, String name) {
        super(value, name);
    }

    public static EImsVoPsN3gpp fromValue(int value) {
        return fromValueGeneric(EImsVoPsN3gpp.class, value);
    }
}
