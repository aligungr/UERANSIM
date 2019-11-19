package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EEpsTypeOfIntegrityProtectionAlgorithm extends ProtocolEnum {
    public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA0
            = new EEpsTypeOfIntegrityProtectionAlgorithm(0b000, "EPS integrity algorithm EIA0 (null integrity protection algorithm)");
    public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA1_128
            = new EEpsTypeOfIntegrityProtectionAlgorithm(0b001, "EPS integrity algorithm 128-EIA1");
    public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA2_128
            = new EEpsTypeOfIntegrityProtectionAlgorithm(0b010, "EPS integrity algorithm 128-EIA2");
    public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA3_128
            = new EEpsTypeOfIntegrityProtectionAlgorithm(0b011, "EPS integrity algorithm 128-EIA3");
    public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA4
            = new EEpsTypeOfIntegrityProtectionAlgorithm(0b100, "EPS integrity algorithm EIA4");
    public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA5
            = new EEpsTypeOfIntegrityProtectionAlgorithm(0b101, "EPS integrity algorithm EIA5");
    public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA6
            = new EEpsTypeOfIntegrityProtectionAlgorithm(0b110, "EPS integrity algorithm EIA6");
    public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA7
            = new EEpsTypeOfIntegrityProtectionAlgorithm(0b111, "EPS integrity algorithm EIA7");

    private EEpsTypeOfIntegrityProtectionAlgorithm(int value, String name) {
        super(value, name);
    }

    public static EEpsTypeOfIntegrityProtectionAlgorithm fromValue(int value) {
        return fromValueGeneric(EEpsTypeOfIntegrityProtectionAlgorithm.class, value);
    }
}
