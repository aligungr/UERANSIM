package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ENssaiInclusionMode extends ProtocolEnum {
    public static final ENssaiInclusionMode A
            = new ENssaiInclusionMode(0b00, "NSSAI inclusion mode A");
    public static final ENssaiInclusionMode B
            = new ENssaiInclusionMode(0b01, "NSSAI inclusion mode B");
    public static final ENssaiInclusionMode C
            = new ENssaiInclusionMode(0b10, "NSSAI inclusion mode C");
    public static final ENssaiInclusionMode D
            = new ENssaiInclusionMode(0b11, "NSSAI inclusion mode D");


    private ENssaiInclusionMode(int value, String name) {
        super(value, name);
    }

    public static ENssaiInclusionMode fromValue(int value) {
        return fromValueGeneric(ENssaiInclusionMode.class, value);
    }
}
