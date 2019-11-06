package com.runsim.backend.nas.eap;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EAkaAttributeType extends ProtocolEnum {
    public static EAkaAttributeType AT_RAND = new EAkaAttributeType(1, "AT_RAND");
    public static EAkaAttributeType AT_AUTN = new EAkaAttributeType(2, "AT_AUTN");
    public static EAkaAttributeType AT_RES = new EAkaAttributeType(3, "AT_RES");
    public static EAkaAttributeType AT_AUTS = new EAkaAttributeType(4, "AT_AUTS");
    public static EAkaAttributeType AT_PADDING = new EAkaAttributeType(6, "AT_PADDING");
    public static EAkaAttributeType AT_NONCE_MT = new EAkaAttributeType(7, "AT_NONCE_MT");
    public static EAkaAttributeType AT_PERMANENT_ID_REQ = new EAkaAttributeType(10, "AT_PERMANENT_ID_REQ");
    public static EAkaAttributeType AT_MAC = new EAkaAttributeType(11, "AT_MAC");
    public static EAkaAttributeType AT_NOTIFICATION = new EAkaAttributeType(12, "AT_NOTIFICATION");
    public static EAkaAttributeType AT_ANY_ID_REQ = new EAkaAttributeType(13, "AT_ANY_ID_REQ");
    public static EAkaAttributeType AT_IDENTITY = new EAkaAttributeType(14, "AT_IDENTITY");
    public static EAkaAttributeType AT_VERSION_LIST = new EAkaAttributeType(15, "AT_VERSION_LIST");
    public static EAkaAttributeType AT_SELECTED_VERSION = new EAkaAttributeType(16, "AT_SELECTED_VERSION");
    public static EAkaAttributeType AT_FULLAUTH_ID_REQ = new EAkaAttributeType(17, "AT_FULLAUTH_ID_REQ");
    public static EAkaAttributeType AT_COUNTER = new EAkaAttributeType(19, "AT_COUNTER");
    public static EAkaAttributeType AT_COUNTER_TOO_SMALL = new EAkaAttributeType(20, "AT_COUNTER_TOO_SMALL");
    public static EAkaAttributeType AT_NONCE_S = new EAkaAttributeType(21, "AT_NONCE_S");
    public static EAkaAttributeType AT_CLIENT_ERROR_CODE = new EAkaAttributeType(22, "AT_CLIENT_ERROR_CODE");
    public static EAkaAttributeType AT_KDF_INPUT = new EAkaAttributeType(23, "AT_KDF_INPUT");
    public static EAkaAttributeType AT_KDF = new EAkaAttributeType(24, "AT_KDF");
    public static EAkaAttributeType AT_IV = new EAkaAttributeType(129, "AT_IV");
    public static EAkaAttributeType AT_ENCR_DATA = new EAkaAttributeType(130, "AT_ENCR_DATA");
    public static EAkaAttributeType AT_NEXT_PSEUDONYM = new EAkaAttributeType(132, "AT_NEXT_PSEUDONYM");
    public static EAkaAttributeType AT_NEXT_REAUTH_ID = new EAkaAttributeType(133, "AT_NEXT_REAUTH_ID");
    public static EAkaAttributeType AT_CHECKCODE = new EAkaAttributeType(134, "AT_CHECKCODE");
    public static EAkaAttributeType AT_RESULT_IND = new EAkaAttributeType(135, "AT_RESULT_IND");
    public static EAkaAttributeType AT_BIDDING = new EAkaAttributeType(136, "AT_BIDDING");
    public static EAkaAttributeType AT_IPMS_IND = new EAkaAttributeType(137, "AT_IPMS_IND");
    public static EAkaAttributeType AT_IPMS_RES = new EAkaAttributeType(138, "AT_IPMS_RES");
    public static EAkaAttributeType AT_TRUST_IND = new EAkaAttributeType(139, "AT_TRUST_IND");

    private EAkaAttributeType(int value, String name) {
        super(value, name);
    }

    public static EAkaAttributeType fromValue(int value) {
        return fromValueGeneric(EAkaAttributeType.class, value);
    }
}
