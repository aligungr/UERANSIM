package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EPayloadContainerType extends ProtocolEnum {
    public static EPayloadContainerType N1_SM_INFORMATION
            = new EPayloadContainerType(0b0001, "N1 SM information");
    public static EPayloadContainerType SMS
            = new EPayloadContainerType(0b0010, "SMS");
    public static EPayloadContainerType LPP_MESSAGE
            = new EPayloadContainerType(0b0011, "LTE Positioning Protocol (LPP) message container");
    public static EPayloadContainerType SOR_TRANSPARENT_CONTAINER
            = new EPayloadContainerType(0b0100, "SOR transparent container");
    public static EPayloadContainerType UE_POLICY_CONTAINER
            = new EPayloadContainerType(0b0101, "UE policy container");
    public static EPayloadContainerType UE_PARAMETERS_UPDATE_TRANSPARENT_CONTAINER
            = new EPayloadContainerType(0b0110, "UE parameters update transparent container");
    public static EPayloadContainerType MULTIPLE_PAYLOADS
            = new EPayloadContainerType(0b1111, "Multiple payloads");

    private EPayloadContainerType(int value, String name) {
        super(value, name);
    }

    public static EPayloadContainerType fromValue(int value) {
        return fromValueGeneric(EPayloadContainerType.class, value);
    }
}
