package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ESwitchOff extends ProtocolEnum {
    public static final ESwitchOff NORMAL_DE_REGISTRATION
            = new ESwitchOff(0b0, "Normal de-registration");
    public static final ESwitchOff SWITCH_OFF
            = new ESwitchOff(0b1, "Switch off");

    private ESwitchOff(int value, String name) {
        super(value, name);
    }

    public static ESwitchOff fromValue(int value) {
        return fromValueGeneric(ESwitchOff.class, value);
    }
}
