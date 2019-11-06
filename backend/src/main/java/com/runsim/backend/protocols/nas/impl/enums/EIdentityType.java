package com.runsim.backend.protocols.nas.impl.enums;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class EIdentityType extends ProtocolEnum {
    public static EIdentityType NO_IDENTITY = new EIdentityType(0b000, "No identity");
    public static EIdentityType SUCI = new EIdentityType(0b001, "SUCI");
    public static EIdentityType GUTI = new EIdentityType(0b010, "5G-GUTI");
    public static EIdentityType IMEI = new EIdentityType(0b011, "IMEI");
    public static EIdentityType TMSI = new EIdentityType(0b100, "5G-S-TMSI");
    public static EIdentityType IMEISV = new EIdentityType(0b101, "IMEISV");

    private EIdentityType(int value, String name) {
        super(value, name);
    }

    public static EIdentityType fromValue(int value) {
        // 3GPP 24501 15.2.0, 9.11.3.3:
        // "All other values are unused and shall be interpreted as "SUCI", if received by the UE."
        return fromValueGeneric(EIdentityType.class, value, SUCI);
    }
}
