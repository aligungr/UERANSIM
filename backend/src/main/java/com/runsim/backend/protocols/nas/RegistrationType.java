package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class RegistrationType extends ProtocolEnum {
    public static final RegistrationType INITIAL_REGISTRATION
            = new RegistrationType(0b001, "initial registration");
    public static final RegistrationType MOBILITY_REGISTRATION_UPDATING
            = new RegistrationType(0b010, "mobility registration updating");
    public static final RegistrationType PERIODIC_REGISTRATION_UPDATING
            = new RegistrationType(0b011, "periodic registration updating");
    public static final RegistrationType EMERGENCY_REGISTRATION
            = new RegistrationType(0b100, "emergency registration");
    public static final RegistrationType RESERVED
            = new RegistrationType(0b111, "reserved");

    private RegistrationType(int value, String name) {
        super(value, name);
    }

    public static RegistrationType fromValue(int value) {
        return fromValueGeneric(RegistrationType.class, value, null);
    }
}
