package com.runsim.backend.nas.types;

import com.runsim.backend.nas.core.NasEnum;

public class ExtendedProtocolDiscriminator extends NasEnum {
    public static final ExtendedProtocolDiscriminator MOBILITY_MANAGEMENT_MESSAGES
            = new ExtendedProtocolDiscriminator(0b01111110, "5GS mobility management messages");
    public static final ExtendedProtocolDiscriminator SESSION_MANAGEMENT_MESSAGES
            = new ExtendedProtocolDiscriminator(0b00101110, "5GS session management messages");

    private ExtendedProtocolDiscriminator(int value, String name) {
        super(value, name);
    }

    public static ExtendedProtocolDiscriminator fromValue(int value) {
        if (value == MOBILITY_MANAGEMENT_MESSAGES.getValue())
            return MOBILITY_MANAGEMENT_MESSAGES;
        else if (value == SESSION_MANAGEMENT_MESSAGES.getValue())
            return SESSION_MANAGEMENT_MESSAGES;
        return null;
    }
}
