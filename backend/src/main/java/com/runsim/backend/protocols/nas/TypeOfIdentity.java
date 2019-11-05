package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class TypeOfIdentity extends ProtocolEnum {
    public static TypeOfIdentity NO_IDENTITY = new TypeOfIdentity(0b000, "No identity");
    public static TypeOfIdentity SUCI = new TypeOfIdentity(0b001, "SUCI");
    public static TypeOfIdentity GUTI = new TypeOfIdentity(0b010, "5G-GUTI");
    public static TypeOfIdentity IMEI = new TypeOfIdentity(0b011, "IMEI");
    public static TypeOfIdentity TMSI = new TypeOfIdentity(0b100, "5G-S-TMSI");
    public static TypeOfIdentity IMEISV = new TypeOfIdentity(0b101, "IMEISV");

    private TypeOfIdentity(int value, String name) {
        super(value, name);
    }

    public static TypeOfIdentity fromValue(int value) {
        // 3GPP 24501 15.2.0, 9.11.3.3:
        // "All other values are unused and shall be interpreted as "SUCI", if received by the UE."
        return fromValueGeneric(TypeOfIdentity.class, value, SUCI);
    }
}
