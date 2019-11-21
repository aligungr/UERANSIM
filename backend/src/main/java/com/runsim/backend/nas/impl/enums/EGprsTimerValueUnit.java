package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EGprsTimerValueUnit extends ProtocolEnum {
    public static final EGprsTimerValueUnit MULTIPLES_OF_2_SECONDS
            = new EGprsTimerValueUnit(0b000, "value is incremented in multiples of 2 seconds");
    public static final EGprsTimerValueUnit MULTIPLES_OF_1_MINUTE
            = new EGprsTimerValueUnit(0b001, "value is incremented in multiples of 1 minute ");
    public static final EGprsTimerValueUnit MULTIPLES_OF_DECIHOURS
            = new EGprsTimerValueUnit(0b010, "value is incremented in multiples of decihours");
    public static final EGprsTimerValueUnit TIMER_IS_DEACTIVATED
            = new EGprsTimerValueUnit(0b111, "value indicates that the timer is deactivated.");

    private EGprsTimerValueUnit(int value, String name) {
        super(value, name);
    }

    public static EGprsTimerValueUnit fromValue(int value) {
        return fromValueGeneric(EGprsTimerValueUnit.class, value);
    }
}
