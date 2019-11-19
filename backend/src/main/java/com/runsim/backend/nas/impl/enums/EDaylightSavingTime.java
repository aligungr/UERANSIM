package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EDaylightSavingTime extends ProtocolEnum {
    public static final EDaylightSavingTime NO_ADJUSTMENT
            = new EDaylightSavingTime(0b00, "No adjustment for Daylight Saving Time");
    public static final EDaylightSavingTime PLUS_ONE
            = new EDaylightSavingTime(0b01, "+1 hour adjustment for Daylight Saving Time");
    public static final EDaylightSavingTime PLUS_TWO
            = new EDaylightSavingTime(0b10, "+2 hours adjustment for Daylight Saving Time");

    private EDaylightSavingTime(int value, String name) {
        super(value, name);
    }

    public static EDaylightSavingTime fromValue(int value) {
        return fromValueGeneric(EDaylightSavingTime.class, value);
    }
}
