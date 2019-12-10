package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EEapAkaSubType extends ProtocolEnum {
    public static EEapAkaSubType AKA_CHALLENGE
            = new EEapAkaSubType(1, "AKA-Challenge");
    public static EEapAkaSubType AKA_AUTHENTICATION_REJECT
            = new EEapAkaSubType(2, "AKA-Authentication-Reject");
    public static EEapAkaSubType AKA_SYNCHRONIZATION_FAILURE
            = new EEapAkaSubType(4, "AKA-Synchronization-Failure");
    public static EEapAkaSubType AKA_IDENTITY
            = new EEapAkaSubType(5, "AKA-Identity");
    public static EEapAkaSubType AKA_NOTIFICATION
            = new EEapAkaSubType(12, "Notification");
    public static EEapAkaSubType AKA_REAUTHENTICATION
            = new EEapAkaSubType(13, "Re-authentication");
    public static EEapAkaSubType AKA_CLIENT_ERROR
            = new EEapAkaSubType(14, "Client-Error");

    private EEapAkaSubType(int value, String name) {
        super(value, name);
    }

    public static EEapAkaSubType fromValue(int value) {
        return fromValueGeneric(EEapAkaSubType.class, value, null);
    }
}
