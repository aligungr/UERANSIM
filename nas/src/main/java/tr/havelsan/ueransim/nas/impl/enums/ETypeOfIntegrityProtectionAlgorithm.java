/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class ETypeOfIntegrityProtectionAlgorithm extends ProtocolEnum {
    public static final ETypeOfIntegrityProtectionAlgorithm IA0
            = new ETypeOfIntegrityProtectionAlgorithm(0b0000, "5G integrity algorithm 5G-IA0 (null integrity protection algorithm)");
    public static final ETypeOfIntegrityProtectionAlgorithm IA1_128
            = new ETypeOfIntegrityProtectionAlgorithm(0b0001, "5G integrity algorithm 128-5G-IA1");
    public static final ETypeOfIntegrityProtectionAlgorithm IA2_128
            = new ETypeOfIntegrityProtectionAlgorithm(0b0010, "5G integrity algorithm 128-5G-IA2");
    public static final ETypeOfIntegrityProtectionAlgorithm IA3_128
            = new ETypeOfIntegrityProtectionAlgorithm(0b0011, "5G integrity algorithm 128-5G-IA3");
    public static final ETypeOfIntegrityProtectionAlgorithm IA4
            = new ETypeOfIntegrityProtectionAlgorithm(0b0100, "5G integrity algorithm 5G-IA4");
    public static final ETypeOfIntegrityProtectionAlgorithm IA5
            = new ETypeOfIntegrityProtectionAlgorithm(0b0101, "5G integrity algorithm 5G-IA5");
    public static final ETypeOfIntegrityProtectionAlgorithm IA6
            = new ETypeOfIntegrityProtectionAlgorithm(0b0110, "5G integrity algorithm 5G-IA6");
    public static final ETypeOfIntegrityProtectionAlgorithm IA7
            = new ETypeOfIntegrityProtectionAlgorithm(0b0111, "5G integrity algorithm 5G-IA7");

    private ETypeOfIntegrityProtectionAlgorithm(int value, String name) {
        super(value, name);
    }

    public static ETypeOfIntegrityProtectionAlgorithm fromValue(int value) {
        return fromValueGeneric(ETypeOfIntegrityProtectionAlgorithm.class, value, null);
    }
}