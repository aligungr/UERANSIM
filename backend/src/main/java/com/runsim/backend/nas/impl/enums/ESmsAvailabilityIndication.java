package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ESmsAvailabilityIndication extends ProtocolEnum {
    public static final ESmsAvailabilityIndication NOT_AVAILABLE
            = new ESmsAvailabilityIndication(0b0, "SMS over NAS not available");
    public static final ESmsAvailabilityIndication AVAILABLE
            = new ESmsAvailabilityIndication(0b1, "SMS over NAS available");

    private ESmsAvailabilityIndication(int value, String name) {
        super(value, name);
    }

    public static ESmsAvailabilityIndication fromValue(int value) {
        return fromValueGeneric(ESmsAvailabilityIndication.class, value);
    }

}
