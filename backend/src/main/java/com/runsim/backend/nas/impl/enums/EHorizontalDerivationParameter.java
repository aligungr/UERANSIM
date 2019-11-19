package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EHorizontalDerivationParameter extends ProtocolEnum {
    public static final EHorizontalDerivationParameter NOT_REQUIRED
            = new EHorizontalDerivationParameter(0b0, "K_AMF derivation is not required");
    public static final EHorizontalDerivationParameter REQUIRED
            = new EHorizontalDerivationParameter(0b1, "K_AMF derivation is not required");

    private EHorizontalDerivationParameter(int value, String name) {
        super(value, name);
    }

    public static EHorizontalDerivationParameter fromValue(int value) {
        return fromValueGeneric(EHorizontalDerivationParameter.class, value);
    }
}
