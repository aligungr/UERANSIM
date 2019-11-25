package com.runsim.backend.nas.eap;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EAkaSubType extends ProtocolEnum {
    public static EAkaSubType AKA_CHALLENGE
            = new EAkaSubType(1, "AKA-Challenge");
    public static EAkaSubType AKA_AUTHENTICATION_REJECT
            = new EAkaSubType(2, "AKA-Authentication-Reject");
    public static EAkaSubType AKA_SYNCHRONIZATION_FAILURE
            = new EAkaSubType(4, "AKA-Synchronization-Failure");
    public static EAkaSubType AKA_IDENTITY
            = new EAkaSubType(5, "AKA-Identity");
    public static EAkaSubType AKA_NOTIFICATION
            = new EAkaSubType(12, "Notification");
    public static EAkaSubType AKA_REAUTHENTICATION
            = new EAkaSubType(13, "Re-authentication");
    public static EAkaSubType AKA_CLIENT_ERROR
            = new EAkaSubType(14, "Client-Error");

    private EAkaSubType(int value, String name) {
        super(value, name);
    }

    public static EAkaSubType fromValue(int value) {
        return fromValueGeneric(EAkaSubType.class, value, null);
    }
}
