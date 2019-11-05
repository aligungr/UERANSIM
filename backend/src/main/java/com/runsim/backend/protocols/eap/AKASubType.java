package com.runsim.backend.protocols.eap;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class AKASubType extends ProtocolEnum {
    public static AKASubType AKA_CHALLENGE
            = new AKASubType(1, "AKA-Challenge");
    public static AKASubType AKA_AUTHENTICATION_REJECT
            = new AKASubType(2, "AKA-Authentication-Reject");
    public static AKASubType AKA_SYNCHRONIZATION_FAILURE
            = new AKASubType(4, "AKA-Synchronization-Failure");
    public static AKASubType AKA_IDENTITY
            = new AKASubType(5, "AKA-Identity");
    public static AKASubType AKA_NOTIFICATION
            = new AKASubType(12, "Notification");
    public static AKASubType AKA_REAUTHENTICATION
            = new AKASubType(13, "Re-authentication");
    public static AKASubType AKA_CLIENT_ERROR
            = new AKASubType(14, "Client-Error");

    private AKASubType(int value, String name) {
        super(value, name);
    }

    public static AKASubType fromValue(int value) {
        return fromValueGeneric(AKASubType.class, value, null);
    }
}
