package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EEmergencyServiceFallback3gppIndicator extends ProtocolEnum {

    public static final EEmergencyServiceFallback3gppIndicator NOT_SUPPORTED
            = new EEmergencyServiceFallback3gppIndicator(0b00, "Emergency services fallback not supported");
    public static final EEmergencyServiceFallback3gppIndicator SUPPORTED_IN_NR_CONNECTED_TO_5GCN_ONLY
            = new EEmergencyServiceFallback3gppIndicator(0b01, "Emergency services fallback supported in NR connected to 5GCN only");
    public static final EEmergencyServiceFallback3gppIndicator SUPPORTED_IN_EUTRA_CONNTECTED_TO_5GCN_ONLY
            = new EEmergencyServiceFallback3gppIndicator(0b10, "Emergency services fallback supported in E-UTRA connected to 5GCN only");
    public static final EEmergencyServiceFallback3gppIndicator SUPPORTED_IN_NR_CONNTECTED_TO_5GCN_AND_EUTRA_CONNECTED_TO_5GCN
            = new EEmergencyServiceFallback3gppIndicator(0b11, "Emergency services fallback supported in NR connected to 5GCN and E-UTRA connected to 5GCN");

    private EEmergencyServiceFallback3gppIndicator(int value, String name) {
        super(value, name);
    }

    public static EEmergencyServiceFallback3gppIndicator fromValue(int value) {
        return fromValueGeneric(EEmergencyServiceFallback3gppIndicator.class, value);
    }
}
