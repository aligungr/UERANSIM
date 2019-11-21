package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EGprsTimerValueUnit3 extends ProtocolEnum {

    public static final EGprsTimerValueUnit3 MULTIPLES_OF_10MIN
            = new EGprsTimerValueUnit3(0b000, "value is incremented in multiples of 10 minutes");
    public static final EGprsTimerValueUnit3 MULTIPLES_OF_1HOUR
            = new EGprsTimerValueUnit3(0b001, "value is incremented in multiples of 1 hour");
    public static final EGprsTimerValueUnit3 MULTIPLES_OF_10HOUR
            = new EGprsTimerValueUnit3(0b010, "value is incremented in multiples of 10 hours");
    public static final EGprsTimerValueUnit3 MULTIPLES_OF_2SEC
            = new EGprsTimerValueUnit3(0b011, "value is incremented in multiples of 2 seconds");
    public static final EGprsTimerValueUnit3 MULTIPLES_OF_30HOUR
            = new EGprsTimerValueUnit3(0b100, "value is incremented in multiples of 30 seconds");
    public static final EGprsTimerValueUnit3 MULTIPLES_OF_1MIN
            = new EGprsTimerValueUnit3(0b101, "value is incremented in multiples of 1 minute");
    public static final EGprsTimerValueUnit3 MULTIPLES_OF_320HOUR
            = new EGprsTimerValueUnit3(0b110, "value is incremented in multiples of 320 hours");
    public static final EGprsTimerValueUnit3 DEACTIVATED
            = new EGprsTimerValueUnit3(0b111, "value indicates that the timer is deactivated");

    private EGprsTimerValueUnit3(int value, String name) {
        super(value, name);
    }

    public static EGprsTimerValueUnit3 fromValue(int value) {
        return fromValueGeneric(EGprsTimerValueUnit3.class, value);
    }
}
