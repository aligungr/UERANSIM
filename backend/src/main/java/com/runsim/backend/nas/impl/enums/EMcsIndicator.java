package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EMcsIndicator extends ProtocolEnum {
    public static final EMcsIndicator NOT_VALID
            = new EMcsIndicator(0b0, "Access identity 2 not valid in RPLMN or equivalent PLMN");
    public static final EMcsIndicator VALID
            = new EMcsIndicator(0b1, "Access identity 2 valid in RPLMN or equivalent PLMN");

    private EMcsIndicator(int value, String name) {
        super(value, name);
    }

    public static EMcsIndicator fromValue(int value) {
        return fromValueGeneric(EMcsIndicator.class, value);
    }
}
