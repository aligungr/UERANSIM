package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ERejectedSNssaCause extends ProtocolEnum {
    public static final ERejectedSNssaCause NA_IN_PLMN
            = new ERejectedSNssaCause(0b0, "S-NSSAI not available in the current PLMN");
    public static final ERejectedSNssaCause NA_IN_RA
            = new ERejectedSNssaCause(0b1, "S-NSSAI not available in the current registration area");

    private ERejectedSNssaCause(int value, String name) {
        super(value, name);
    }

    public static ERejectedSNssaCause fromValue(int value) {
        return fromValueGeneric(ERejectedSNssaCause.class, value);
    }
}
