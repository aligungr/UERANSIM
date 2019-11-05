package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class IdentityType extends ProtocolEnum {
    public static IdentityType NO_IDENTITY = new IdentityType(0b000, "No identity");
    public static IdentityType SUCI = new IdentityType(0b001, "SUCI");
    public static IdentityType GUTI = new IdentityType(0b010, "5G-GUTI");
    public static IdentityType IMEI = new IdentityType(0b011, "IMEI");
    public static IdentityType TMSI = new IdentityType(0b100, "5G-S-TMSI");
    public static IdentityType IMEISV = new IdentityType(0b101, "IMEISV");

    private IdentityType(int value, String name) {
        super(value, name);
    }

    public static IdentityType fromValue(int value) {
        // 3GPP 24501 15.2.0, 9.11.3.3:
        // "All other values are unused and shall be interpreted as "SUCI", if received by the UE."
        return fromValueGeneric(IdentityType.class, value, SUCI);
    }
}
