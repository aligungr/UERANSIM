package com.runsim.backend.protocols.eap;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class AKAAttributeType extends ProtocolEnum {
    public static AKAAttributeType AT_RAND = new AKAAttributeType(1, "AT_RAND");
    public static AKAAttributeType AT_AUTN = new AKAAttributeType(2, "AT_AUTN");
    public static AKAAttributeType AT_RES = new AKAAttributeType(3, "AT_RES");
    public static AKAAttributeType AT_AUTS = new AKAAttributeType(4, "AT_AUTS");
    public static AKAAttributeType AT_PADDING = new AKAAttributeType(6, "AT_PADDING");
    public static AKAAttributeType AT_NONCE_MT = new AKAAttributeType(7, "AT_NONCE_MT");
    public static AKAAttributeType AT_PERMANENT_ID_REQ = new AKAAttributeType(10, "AT_PERMANENT_ID_REQ");
    public static AKAAttributeType AT_MAC = new AKAAttributeType(11, "AT_MAC");
    public static AKAAttributeType AT_NOTIFICATION = new AKAAttributeType(12, "AT_NOTIFICATION");
    public static AKAAttributeType AT_ANY_ID_REQ = new AKAAttributeType(13, "AT_ANY_ID_REQ");
    public static AKAAttributeType AT_IDENTITY = new AKAAttributeType(14, "AT_IDENTITY");
    public static AKAAttributeType AT_VERSION_LIST = new AKAAttributeType(15, "AT_VERSION_LIST");
    public static AKAAttributeType AT_SELECTED_VERSION = new AKAAttributeType(16, "AT_SELECTED_VERSION");
    public static AKAAttributeType AT_FULLAUTH_ID_REQ = new AKAAttributeType(17, "AT_FULLAUTH_ID_REQ");
    public static AKAAttributeType AT_COUNTER = new AKAAttributeType(19, "AT_COUNTER");
    public static AKAAttributeType AT_COUNTER_TOO_SMALL = new AKAAttributeType(20, "AT_COUNTER_TOO_SMALL");
    public static AKAAttributeType AT_NONCE_S = new AKAAttributeType(21, "AT_NONCE_S");
    public static AKAAttributeType AT_CLIENT_ERROR_CODE = new AKAAttributeType(22, "AT_CLIENT_ERROR_CODE");
    public static AKAAttributeType AT_KDF_INPUT = new AKAAttributeType(23, "AT_KDF_INPUT");
    public static AKAAttributeType AT_KDF = new AKAAttributeType(24, "AT_KDF");
    public static AKAAttributeType AT_IV = new AKAAttributeType(129, "AT_IV");
    public static AKAAttributeType AT_ENCR_DATA = new AKAAttributeType(130, "AT_ENCR_DATA");
    public static AKAAttributeType AT_NEXT_PSEUDONYM = new AKAAttributeType(132, "AT_NEXT_PSEUDONYM");
    public static AKAAttributeType AT_NEXT_REAUTH_ID = new AKAAttributeType(133, "AT_NEXT_REAUTH_ID");
    public static AKAAttributeType AT_CHECKCODE = new AKAAttributeType(134, "AT_CHECKCODE");
    public static AKAAttributeType AT_RESULT_IND = new AKAAttributeType(135, "AT_RESULT_IND");
    public static AKAAttributeType AT_BIDDING = new AKAAttributeType(136, "AT_BIDDING");
    public static AKAAttributeType AT_IPMS_IND = new AKAAttributeType(137, "AT_IPMS_IND");
    public static AKAAttributeType AT_IPMS_RES = new AKAAttributeType(138, "AT_IPMS_RES");
    public static AKAAttributeType AT_TRUST_IND = new AKAAttributeType(139, "AT_TRUST_IND");

    private AKAAttributeType(int value, String name) {
        super(value, name);
    }

    public static AKAAttributeType fromValue(int value) {
        return fromValueGeneric(AKAAttributeType.class, value);
    }
}
