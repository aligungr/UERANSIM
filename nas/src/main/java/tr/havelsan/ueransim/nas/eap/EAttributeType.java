/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EAttributeType extends ProtocolEnum {
    public static EAttributeType AT_RAND = new EAttributeType(1, "AT_RAND");
    public static EAttributeType AT_AUTN = new EAttributeType(2, "AT_AUTN");
    public static EAttributeType AT_RES = new EAttributeType(3, "AT_RES");
    public static EAttributeType AT_AUTS = new EAttributeType(4, "AT_AUTS");
    public static EAttributeType AT_PADDING = new EAttributeType(6, "AT_PADDING");
    public static EAttributeType AT_NONCE_MT = new EAttributeType(7, "AT_NONCE_MT");
    public static EAttributeType AT_PERMANENT_ID_REQ = new EAttributeType(10, "AT_PERMANENT_ID_REQ");
    public static EAttributeType AT_MAC = new EAttributeType(11, "AT_MAC");
    public static EAttributeType AT_NOTIFICATION = new EAttributeType(12, "AT_NOTIFICATION");
    public static EAttributeType AT_ANY_ID_REQ = new EAttributeType(13, "AT_ANY_ID_REQ");
    public static EAttributeType AT_IDENTITY = new EAttributeType(14, "AT_IDENTITY");
    public static EAttributeType AT_VERSION_LIST = new EAttributeType(15, "AT_VERSION_LIST");
    public static EAttributeType AT_SELECTED_VERSION = new EAttributeType(16, "AT_SELECTED_VERSION");
    public static EAttributeType AT_FULLAUTH_ID_REQ = new EAttributeType(17, "AT_FULLAUTH_ID_REQ");
    public static EAttributeType AT_COUNTER = new EAttributeType(19, "AT_COUNTER");
    public static EAttributeType AT_COUNTER_TOO_SMALL = new EAttributeType(20, "AT_COUNTER_TOO_SMALL");
    public static EAttributeType AT_NONCE_S = new EAttributeType(21, "AT_NONCE_S");
    public static EAttributeType AT_CLIENT_ERROR_CODE = new EAttributeType(22, "AT_CLIENT_ERROR_CODE");
    public static EAttributeType AT_KDF_INPUT = new EAttributeType(23, "AT_KDF_INPUT");
    public static EAttributeType AT_KDF = new EAttributeType(24, "AT_KDF");
    public static EAttributeType AT_IV = new EAttributeType(129, "AT_IV");
    public static EAttributeType AT_ENCR_DATA = new EAttributeType(130, "AT_ENCR_DATA");
    public static EAttributeType AT_NEXT_PSEUDONYM = new EAttributeType(132, "AT_NEXT_PSEUDONYM");
    public static EAttributeType AT_NEXT_REAUTH_ID = new EAttributeType(133, "AT_NEXT_REAUTH_ID");
    public static EAttributeType AT_CHECKCODE = new EAttributeType(134, "AT_CHECKCODE");
    public static EAttributeType AT_RESULT_IND = new EAttributeType(135, "AT_RESULT_IND");
    public static EAttributeType AT_BIDDING = new EAttributeType(136, "AT_BIDDING");
    public static EAttributeType AT_IPMS_IND = new EAttributeType(137, "AT_IPMS_IND");
    public static EAttributeType AT_IPMS_RES = new EAttributeType(138, "AT_IPMS_RES");
    public static EAttributeType AT_TRUST_IND = new EAttributeType(139, "AT_TRUST_IND");

    private EAttributeType(int value, String name) {
        super(value, name);
    }

    public static EAttributeType fromValue(int value) {
        return fromValueGeneric(EAttributeType.class, value, null);
    }
}
