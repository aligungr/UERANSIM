package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class ExtendedProtocolDiscriminator extends ProtocolEnum {
    public static final ExtendedProtocolDiscriminator MOBILITY_MANAGEMENT_MESSAGES
            = new ExtendedProtocolDiscriminator(0b01111110, "5GS mobility management messages");
    public static final ExtendedProtocolDiscriminator SESSION_MANAGEMENT_MESSAGES
            = new ExtendedProtocolDiscriminator(0b00101110, "5GS session management messages");

    private ExtendedProtocolDiscriminator(int value, String name) {
        super(value, name);
    }

    public static ExtendedProtocolDiscriminator fromValue(int value) {
        return fromValueGeneric(ExtendedProtocolDiscriminator.class, value, null);
    }
}
