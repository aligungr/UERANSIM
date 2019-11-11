package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EEmergencyServiceSupport3gppIndicator extends ProtocolEnum {

    public static final EEmergencyServiceSupport3gppIndicator NOT_SUPPORTED
            = new EEmergencyServiceSupport3gppIndicator(0b00, "Emergency services not supported");
    public static final EEmergencyServiceSupport3gppIndicator SUPPORTED_IN_NR_CONNECTED_TO_5GCN_ONLY
            = new EEmergencyServiceSupport3gppIndicator(0b01, "Emergency services supported in NR connected to 5GCN only");
    public static final EEmergencyServiceSupport3gppIndicator SUPPORTED_IN_EUTRA_CONNTECTED_TO_5GCN_ONLY
            = new EEmergencyServiceSupport3gppIndicator(0b10, "Emergency services supported in E-UTRA connected to 5GCN only");
    public static final EEmergencyServiceSupport3gppIndicator SUPPORTED_IN_NR_CONNTECTED_TO_5GCN_AND_EUTRA_CONNECTED_TO_5GCN
            = new EEmergencyServiceSupport3gppIndicator(0b11, "Emergency services supported in NR connected to 5GCN and E-UTRA connected to 5GCN");

    private EEmergencyServiceSupport3gppIndicator(int value, String name) {
        super(value, name);
    }

    public static EEmergencyServiceSupport3gppIndicator fromValue(int value) {
        return fromValueGeneric(EEmergencyServiceSupport3gppIndicator.class, value);
    }
}
