package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EEmergencyServiceSupportNon3gppIndicator extends ProtocolEnum {
    public static final EEmergencyServiceSupportNon3gppIndicator NOT_SUPPORTED
            = new EEmergencyServiceSupportNon3gppIndicator(0b1, "Emergency services not supported over non-3GPP access");
    public static final EEmergencyServiceSupportNon3gppIndicator SUPPORTED
            = new EEmergencyServiceSupportNon3gppIndicator(0b0, "Emergency services supported over non-3GPP access");

    private EEmergencyServiceSupportNon3gppIndicator(int value, String name) {
        super(value, name);
    }

    public static EEmergencyServiceSupportNon3gppIndicator fromValue(int value) {
        return fromValueGeneric(EEmergencyServiceSupportNon3gppIndicator.class, value);
    }
}
