package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

/**
 * The name seems a little long... but not so long, but a little long..
 */
public class EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink extends ProtocolEnum {
    public static final EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink SIXTY_FOR_KBPS
            = new EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink(0b0, "64 kbps");
    public static final EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink FULL_DATA_RATE
            = new EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink(0b1, "Full data rate");

    private EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink(int value, String name) {
        super(value, name);
    }

    public static EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink fromValue(int value) {
        return fromValueGeneric(EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink.class, value);
    }
}
