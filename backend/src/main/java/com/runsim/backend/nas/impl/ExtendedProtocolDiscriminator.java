package com.runsim.backend.nas.impl;

import com.runsim.backend.nas.core.values.OctetValue;

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
        switch (getValue()) {
            case SESSION_MANAGEMENT_MESSAGES:
                return "5GS session management messages";
            case MOBILITY_MANAGEMENT_MESSAGES:
                return "5GS mobility management messages";
            default:
                return null;
        }
    }
}
