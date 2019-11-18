package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ERejectedSNssaiCause extends ProtocolEnum {
    public static final ERejectedSNssaiCause NA_IN_PLMN
            = new ERejectedSNssaiCause(0b0, "S-NSSAI not available in the current PLMN");
    public static final ERejectedSNssaiCause NA_IN_RA
            = new ERejectedSNssaiCause(0b1, "S-NSSAI not available in the current registration area");

    private ERejectedSNssaiCause(int value, String name) {
        super(value, name);
    }

    public static ERejectedSNssaiCause fromValue(int value) {
        return fromValueGeneric(ERejectedSNssaiCause.class, value);
    }
}
