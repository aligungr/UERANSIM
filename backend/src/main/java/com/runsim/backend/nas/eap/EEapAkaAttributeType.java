package com.runsim.backend.nas.eap;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EEapAkaAttributeType extends ProtocolEnum {
    public static EEapAkaAttributeType AT_RAND = new EEapAkaAttributeType(1, "AT_RAND");
    public static EEapAkaAttributeType AT_AUTN = new EEapAkaAttributeType(2, "AT_AUTN");
    public static EEapAkaAttributeType AT_RES = new EEapAkaAttributeType(3, "AT_RES");
    public static EEapAkaAttributeType AT_AUTS = new EEapAkaAttributeType(4, "AT_AUTS");
    public static EEapAkaAttributeType AT_PADDING = new EEapAkaAttributeType(6, "AT_PADDING");
    public static EEapAkaAttributeType AT_NONCE_MT = new EEapAkaAttributeType(7, "AT_NONCE_MT");
    public static EEapAkaAttributeType AT_PERMANENT_ID_REQ = new EEapAkaAttributeType(10, "AT_PERMANENT_ID_REQ");
    public static EEapAkaAttributeType AT_MAC = new EEapAkaAttributeType(11, "AT_MAC");
    public static EEapAkaAttributeType AT_NOTIFICATION = new EEapAkaAttributeType(12, "AT_NOTIFICATION");
    public static EEapAkaAttributeType AT_ANY_ID_REQ = new EEapAkaAttributeType(13, "AT_ANY_ID_REQ");
    public static EEapAkaAttributeType AT_IDENTITY = new EEapAkaAttributeType(14, "AT_IDENTITY");
    public static EEapAkaAttributeType AT_VERSION_LIST = new EEapAkaAttributeType(15, "AT_VERSION_LIST");
    public static EEapAkaAttributeType AT_SELECTED_VERSION = new EEapAkaAttributeType(16, "AT_SELECTED_VERSION");
    public static EEapAkaAttributeType AT_FULLAUTH_ID_REQ = new EEapAkaAttributeType(17, "AT_FULLAUTH_ID_REQ");
    public static EEapAkaAttributeType AT_COUNTER = new EEapAkaAttributeType(19, "AT_COUNTER");
    public static EEapAkaAttributeType AT_COUNTER_TOO_SMALL = new EEapAkaAttributeType(20, "AT_COUNTER_TOO_SMALL");
    public static EEapAkaAttributeType AT_NONCE_S = new EEapAkaAttributeType(21, "AT_NONCE_S");
    public static EEapAkaAttributeType AT_CLIENT_ERROR_CODE = new EEapAkaAttributeType(22, "AT_CLIENT_ERROR_CODE");
    public static EEapAkaAttributeType AT_KDF_INPUT = new EEapAkaAttributeType(23, "AT_KDF_INPUT");
    public static EEapAkaAttributeType AT_KDF = new EEapAkaAttributeType(24, "AT_KDF");
    public static EEapAkaAttributeType AT_IV = new EEapAkaAttributeType(129, "AT_IV");
    public static EEapAkaAttributeType AT_ENCR_DATA = new EEapAkaAttributeType(130, "AT_ENCR_DATA");
    public static EEapAkaAttributeType AT_NEXT_PSEUDONYM = new EEapAkaAttributeType(132, "AT_NEXT_PSEUDONYM");
    public static EEapAkaAttributeType AT_NEXT_REAUTH_ID = new EEapAkaAttributeType(133, "AT_NEXT_REAUTH_ID");
    public static EEapAkaAttributeType AT_CHECKCODE = new EEapAkaAttributeType(134, "AT_CHECKCODE");
    public static EEapAkaAttributeType AT_RESULT_IND = new EEapAkaAttributeType(135, "AT_RESULT_IND");
    public static EEapAkaAttributeType AT_BIDDING = new EEapAkaAttributeType(136, "AT_BIDDING");
    public static EEapAkaAttributeType AT_IPMS_IND = new EEapAkaAttributeType(137, "AT_IPMS_IND");
    public static EEapAkaAttributeType AT_IPMS_RES = new EEapAkaAttributeType(138, "AT_IPMS_RES");
    public static EEapAkaAttributeType AT_TRUST_IND = new EEapAkaAttributeType(139, "AT_TRUST_IND");

    private EEapAkaAttributeType(int value, String name) {
        super(value, name);
    }

    public static EEapAkaAttributeType fromValue(int value) {
        return fromValueGeneric(EEapAkaAttributeType.class, value, null);
    }
}
