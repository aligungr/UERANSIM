package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class TypeOfSecurityContext extends ProtocolEnum {

    public static final TypeOfSecurityContext NATIVE_SECURITY_CONTEXT
            = new TypeOfSecurityContext(0b0, "Native security context (for KSI_AMF)");
    public static final TypeOfSecurityContext MAPPED_SECURITY_CONTEXT
            = new TypeOfSecurityContext(0b1, "Mapped security context (for KSI_ASME)");

    private TypeOfSecurityContext(int value, String name) {
        super(value, name);
    }

    public static TypeOfSecurityContext fromValue(int value) {
        return fromValueGeneric(TypeOfSecurityContext.class, value, null);
    }
}
