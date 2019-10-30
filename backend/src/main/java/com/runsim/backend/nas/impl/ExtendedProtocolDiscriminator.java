package com.runsim.backend.nas.impl;

import com.runsim.backend.nas.core.OctetValue;

public class ExtendedProtocolDiscriminator extends OctetValue {

    public static final int SESSION_MANAGEMENT_MESSAGES = 0b00101110;
    public static final int MOBILITY_MANAGEMENT_MESSAGES = 0b01111110;

    public ExtendedProtocolDiscriminator() {
    }

    public ExtendedProtocolDiscriminator(int value) {
        super(value);
    }

    @Override
    public String display() {
        String string;
        switch (getValue()) {
            case SESSION_MANAGEMENT_MESSAGES:
                string = "5GS session management messages";
                break;
            case MOBILITY_MANAGEMENT_MESSAGES:
                string = "5GS mobility management messages";
                break;
            default:
                string = INVALID_DATA;
                break;
        }
        return "Extended protocol discriminator: " + string;
    }
}
