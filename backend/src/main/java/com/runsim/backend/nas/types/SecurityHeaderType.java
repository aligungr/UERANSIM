package com.runsim.backend.nas.types;

import com.runsim.backend.nas.core.NasEnum;

public class SecurityHeaderType extends NasEnum {
    public static final SecurityHeaderType NOT_PROTECTED
            = new SecurityHeaderType(0b0000, "Plain 5GS NAS message, not security protected");
    public static final SecurityHeaderType INTEGRITY_PROTECTED
            = new SecurityHeaderType(0b0001, "Integrity protected");
    public static final SecurityHeaderType INTEGRITY_PROTECTED_AND_CIPHERED
            = new SecurityHeaderType(0b0010, "Integrity protected and ciphered");
    public static final SecurityHeaderType INTEGRITY_PROTECTED_WITH_SECURITY_CONTEXT
            = new SecurityHeaderType(0b0011, "Integrity protected with new 5G NAS security context");
    public static final SecurityHeaderType INTEGRITY_PROTECTED_AND_CIPHERED_WITH_SECURITY_CONTEXT
            = new SecurityHeaderType(0b0100, "Integrity protected and ciphered with new 5G NAS security context");

    private SecurityHeaderType(int value, String name) {
        super(value, name);
    }

    public static SecurityHeaderType fromValue(int value) {
        if (value == NOT_PROTECTED.getValue())
            return NOT_PROTECTED;
        if (value == INTEGRITY_PROTECTED.getValue())
            return INTEGRITY_PROTECTED;
        if (value == INTEGRITY_PROTECTED_AND_CIPHERED.getValue())
            return INTEGRITY_PROTECTED_AND_CIPHERED;
        if (value == INTEGRITY_PROTECTED_WITH_SECURITY_CONTEXT.getValue())
            return INTEGRITY_PROTECTED_WITH_SECURITY_CONTEXT;
        if (value == INTEGRITY_PROTECTED_AND_CIPHERED_WITH_SECURITY_CONTEXT.getValue())
            return INTEGRITY_PROTECTED_AND_CIPHERED_WITH_SECURITY_CONTEXT;
        return null;
    }
}
