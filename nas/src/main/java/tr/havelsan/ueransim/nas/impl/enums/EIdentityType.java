/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EIdentityType extends ProtocolEnum {
    public static final EIdentityType NO_IDENTITY = new EIdentityType(0b000, "No identity");
    public static final EIdentityType SUCI = new EIdentityType(0b001, "SUCI");
    public static final EIdentityType GUTI = new EIdentityType(0b010, "5G-GUTI");
    public static final EIdentityType IMEI = new EIdentityType(0b011, "IMEI");
    public static final EIdentityType TMSI = new EIdentityType(0b100, "5G-S-TMSI");
    public static final EIdentityType IMEISV = new EIdentityType(0b101, "IMEISV");

    private EIdentityType(int value, String name) {
        super(value, name);
    }

    public static EIdentityType fromValue(int value) {
        // 3GPP 24501 15.2.0, 9.11.3.3:
        // "All other values are unused and shall be interpreted as "SUCI", if received by the UE."
        return fromValueGeneric(EIdentityType.class, value, SUCI);
    }
}
