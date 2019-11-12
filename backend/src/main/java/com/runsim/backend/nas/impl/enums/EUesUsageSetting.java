package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EUesUsageSetting extends ProtocolEnum {
    public static final EUesUsageSetting NOT_SUPPORTED
            = new EUesUsageSetting(0b0, "voice centric");
    public static final EUesUsageSetting SUPPORTED
            = new EUesUsageSetting(0b1, "data centric");

    private EUesUsageSetting(int value, String name) {
        super(value, name);
    }

    public static EUesUsageSetting fromValue(int value) {
        return fromValueGeneric(EUesUsageSetting.class, value);
    }
}
