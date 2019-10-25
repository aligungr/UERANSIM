package com.runsim.backend.nas.impl;

import com.runsim.backend.nas.core.values.HalfOctetValue;

public class SecurityHeaderType extends HalfOctetValue {

    public static final int NOT_PROTECTED = 0b0000;
    public static final int INTEGRITY_PROTECTED = 0b0001;
    public static final int INTEGRITY_PROTECTED_AND_CIPHERED = 0b0010;
    public static final int INTEGRITY_PROTECTED_WITH_SECURITY_CONTEXT = 0b0011;
    public static final int INTEGRITY_PROTECTED_AND_CIPHERED_WITH_SECURITY_CONTEXT = 0b0100;

    public SecurityHeaderType() {
    }

    public SecurityHeaderType(int value) {
        super(value);
    }

    @Override
    public String display() {
        switch (getValue()) {
            case NOT_PROTECTED:
                return "Plain 5GS NAS message, not security protected";
            case INTEGRITY_PROTECTED:
                return "Integrity protected";
            case INTEGRITY_PROTECTED_AND_CIPHERED:
                return "Integrity protected and ciphered";
            case INTEGRITY_PROTECTED_WITH_SECURITY_CONTEXT:
                return "Integrity protected with new 5G NAS security context";
            case INTEGRITY_PROTECTED_AND_CIPHERED_WITH_SECURITY_CONTEXT:
                return "Integrity protected and ciphered with new 5G NAS security context";
            default:
                return null;
        }
    }
}
