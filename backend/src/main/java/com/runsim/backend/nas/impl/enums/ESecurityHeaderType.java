package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ESecurityHeaderType extends ProtocolEnum {
    public static final ESecurityHeaderType NOT_PROTECTED
            = new ESecurityHeaderType(0b0000, "Plain 5GS NAS message, not security protected");
    public static final ESecurityHeaderType INTEGRITY_PROTECTED
            = new ESecurityHeaderType(0b0001, "Integrity protected");
    public static final ESecurityHeaderType INTEGRITY_PROTECTED_AND_CIPHERED
            = new ESecurityHeaderType(0b0010, "Integrity protected and ciphered");
    public static final ESecurityHeaderType INTEGRITY_PROTECTED_WITH_SECURITY_CONTEXT
            = new ESecurityHeaderType(0b0011, "Integrity protected with new 5G NAS security context");
    public static final ESecurityHeaderType INTEGRITY_PROTECTED_AND_CIPHERED_WITH_SECURITY_CONTEXT
            = new ESecurityHeaderType(0b0100, "Integrity protected and ciphered with new 5G NAS security context");

    private ESecurityHeaderType(int value, String name) {
        super(value, name);
    }

    public static ESecurityHeaderType fromValue(int value) {
        return fromValueGeneric(ESecurityHeaderType.class, value);
    }
}
