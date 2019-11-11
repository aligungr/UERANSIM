package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EMpsIndicator extends ProtocolEnum {
    public static final EMpsIndicator NOT_SUPPORTED
            = new EMpsIndicator(0b0, "Access identity 1 not valid in RPLMN or equivalent PLMN");
    public static final EMpsIndicator SUPPORTED
            = new EMpsIndicator(0b1, "Access identity 1 valid in RPLMN or equivalent PLMN");

    private EMpsIndicator(int value, String name) {
        super(value, name);
    }

    public static EMpsIndicator fromValue(int value) {
        return fromValueGeneric(EMpsIndicator.class, value);
    }
}
