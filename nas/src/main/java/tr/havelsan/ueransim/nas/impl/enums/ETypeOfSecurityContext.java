/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class ETypeOfSecurityContext extends ProtocolEnum {
    public static final ETypeOfSecurityContext NATIVE_SECURITY_CONTEXT
            = new ETypeOfSecurityContext(0b0, "Native security context (for KSI_AMF)");
    public static final ETypeOfSecurityContext MAPPED_SECURITY_CONTEXT
            = new ETypeOfSecurityContext(0b1, "Mapped security context (for KSI_ASME)");

    private ETypeOfSecurityContext(int value, String name) {
        super(value, name);
    }

    public static ETypeOfSecurityContext fromValue(int value) {
        return fromValueGeneric(ETypeOfSecurityContext.class, value, null);
    }
}