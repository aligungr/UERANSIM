package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class ProtectionSchemeIdentifier extends ProtocolEnum {

    public static final ProtectionSchemeIdentifier NULL_SCHEMA
            = new ProtectionSchemeIdentifier(0b0000, "Null scheme");
    public static final ProtectionSchemeIdentifier ECIES_SCHEMA_PROFILE_A
            = new ProtectionSchemeIdentifier(0b0001, "ECIES scheme profile A");
    public static final ProtectionSchemeIdentifier ECIES_SCHEMA_PROFILE_B
            = new ProtectionSchemeIdentifier(0b0010, "ECIES scheme profile B");

    public static final ProtectionSchemeIdentifier RESERVED1
            = new ProtectionSchemeIdentifier(0b0011, "Reserved");
    public static final ProtectionSchemeIdentifier RESERVED2
            = new ProtectionSchemeIdentifier(0b0100, "Reserved");
    public static final ProtectionSchemeIdentifier RESERVED3
            = new ProtectionSchemeIdentifier(0b0101, "Reserved");
    public static final ProtectionSchemeIdentifier RESERVED4
            = new ProtectionSchemeIdentifier(0b0110, "Reserved");
    public static final ProtectionSchemeIdentifier RESERVED5
            = new ProtectionSchemeIdentifier(0b0111, "Reserved");
    public static final ProtectionSchemeIdentifier RESERVED6
            = new ProtectionSchemeIdentifier(0b1000, "Reserved");
    public static final ProtectionSchemeIdentifier RESERVED7
            = new ProtectionSchemeIdentifier(0b1001, "Reserved");
    public static final ProtectionSchemeIdentifier RESERVED8
            = new ProtectionSchemeIdentifier(0b1010, "Reserved");
    public static final ProtectionSchemeIdentifier RESERVED9
            = new ProtectionSchemeIdentifier(0b1011, "Reserved");

    public static final ProtectionSchemeIdentifier OPERATOR_SPECIFIC1
            = new ProtectionSchemeIdentifier(0b1100, "Operator-specific protection scheme");
    public static final ProtectionSchemeIdentifier OPERATOR_SPECIFIC2
            = new ProtectionSchemeIdentifier(0b1101, "Operator-specific protection scheme");
    public static final ProtectionSchemeIdentifier OPERATOR_SPECIFIC3
            = new ProtectionSchemeIdentifier(0b1110, "Operator-specific protection scheme");
    public static final ProtectionSchemeIdentifier OPERATOR_SPECIFIC4
            = new ProtectionSchemeIdentifier(0b1111, "Operator-specific protection scheme");

    private ProtectionSchemeIdentifier(int value, String name) {
        super(value, name);
    }

    public static ProtectionSchemeIdentifier fromValue(int value) {
        return fromValueGeneric(ProtectionSchemeIdentifier.class, value);
    }

    public boolean isReserved() {
        return value >= RESERVED1.value && value <= RESERVED9.value;
    }

    public boolean isOperatorSpecific() {
        return value >= OPERATOR_SPECIFIC1.value && value <= OPERATOR_SPECIFIC4.value;
    }
}
