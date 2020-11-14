/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class ESubType extends ProtocolEnum {
    public static ESubType AKA_CHALLENGE
            = new ESubType(1, "AKA-Challenge");
    public static ESubType AKA_AUTHENTICATION_REJECT
            = new ESubType(2, "AKA-Authentication-Reject");
    public static ESubType AKA_SYNCHRONIZATION_FAILURE
            = new ESubType(4, "AKA-Synchronization-Failure");
    public static ESubType AKA_IDENTITY
            = new ESubType(5, "AKA-Identity");
    public static ESubType AKA_NOTIFICATION
            = new ESubType(12, "Notification");
    public static ESubType AKA_REAUTHENTICATION
            = new ESubType(13, "Re-authentication");
    public static ESubType AKA_CLIENT_ERROR
            = new ESubType(14, "Client-Error");

    private ESubType(int value, String name) {
        super(value, name);
    }

    public static ESubType fromValue(int value) {
        return fromValueGeneric(ESubType.class, value, null);
    }
}