package com.runsim.backend.protocols.nas.impl.enums;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class EProtectionSchemeIdentifier extends ProtocolEnum {

    public static final EProtectionSchemeIdentifier NULL_SCHEMA
            = new EProtectionSchemeIdentifier(0b0000, "Null scheme");
    public static final EProtectionSchemeIdentifier ECIES_SCHEMA_PROFILE_A
            = new EProtectionSchemeIdentifier(0b0001, "ECIES scheme profile A");
    public static final EProtectionSchemeIdentifier ECIES_SCHEMA_PROFILE_B
            = new EProtectionSchemeIdentifier(0b0010, "ECIES scheme profile B");

    public static final EProtectionSchemeIdentifier RESERVED1
            = new EProtectionSchemeIdentifier(0b0011, "Reserved");
    public static final EProtectionSchemeIdentifier RESERVED2
            = new EProtectionSchemeIdentifier(0b0100, "Reserved");
    public static final EProtectionSchemeIdentifier RESERVED3
            = new EProtectionSchemeIdentifier(0b0101, "Reserved");
    public static final EProtectionSchemeIdentifier RESERVED4
            = new EProtectionSchemeIdentifier(0b0110, "Reserved");
    public static final EProtectionSchemeIdentifier RESERVED5
            = new EProtectionSchemeIdentifier(0b0111, "Reserved");
    public static final EProtectionSchemeIdentifier RESERVED6
            = new EProtectionSchemeIdentifier(0b1000, "Reserved");
    public static final EProtectionSchemeIdentifier RESERVED7
            = new EProtectionSchemeIdentifier(0b1001, "Reserved");
    public static final EProtectionSchemeIdentifier RESERVED8
            = new EProtectionSchemeIdentifier(0b1010, "Reserved");
    public static final EProtectionSchemeIdentifier RESERVED9
            = new EProtectionSchemeIdentifier(0b1011, "Reserved");

    public static final EProtectionSchemeIdentifier OPERATOR_SPECIFIC1
            = new EProtectionSchemeIdentifier(0b1100, "Operator-specific protection scheme");
    public static final EProtectionSchemeIdentifier OPERATOR_SPECIFIC2
            = new EProtectionSchemeIdentifier(0b1101, "Operator-specific protection scheme");
    public static final EProtectionSchemeIdentifier OPERATOR_SPECIFIC3
            = new EProtectionSchemeIdentifier(0b1110, "Operator-specific protection scheme");
    public static final EProtectionSchemeIdentifier OPERATOR_SPECIFIC4
            = new EProtectionSchemeIdentifier(0b1111, "Operator-specific protection scheme");

    private EProtectionSchemeIdentifier(int value, String name) {
        super(value, name);
    }

    public static EProtectionSchemeIdentifier fromValue(int value) {
        return fromValueGeneric(EProtectionSchemeIdentifier.class, value);
    }

    public boolean isReserved() {
        return value >= RESERVED1.value && value <= RESERVED9.value;
    }

    public boolean isOperatorSpecific() {
        return value >= OPERATOR_SPECIFIC1.value && value <= OPERATOR_SPECIFIC4.value;
    }
}
