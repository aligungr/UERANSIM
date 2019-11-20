package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

/**
 * The name seems a little long... but not so long, but a little long..
 */
public class EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink extends ProtocolEnum {
    public static final EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink SIXTY_FOR_KBPS
            = new EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink(0b0, "64 kbps");
    public static final EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink FULL_DATA_RATE
            = new EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink(0b1, "Full data rate");

    private EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink(int value, String name) {
        super(value, name);
    }

    public static EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink fromValue(int value) {
        return fromValueGeneric(EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink.class, value);
    }
}
