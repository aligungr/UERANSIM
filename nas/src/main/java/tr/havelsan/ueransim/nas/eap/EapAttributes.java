/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class EapAttributes {
    private final LinkedHashMap<EAttributeType, OctetString> attributes;

    public EapAttributes() {
        this.attributes = new LinkedHashMap<>();
    }

    //======================================================================================================
    //                                        GET METHODS
    //======================================================================================================

    public OctetString getRand() {
        return attributes.get(EAttributeType.AT_RAND).substring(2);
    }

    public OctetString getMac() {
        return attributes.get(EAttributeType.AT_MAC).substring(2);
    }

    public OctetString getAutn() {
        return attributes.get(EAttributeType.AT_AUTN).substring(2);
    }

    public int getClientErrorCode() {
        return attributes.get(EAttributeType.AT_CLIENT_ERROR_CODE).get2(0).intValue();
    }

    public int getKdf() {
        return attributes.get(EAttributeType.AT_KDF).get2(0).intValue();
    }

    public OctetString getAuts() {
        return attributes.get(EAttributeType.AT_AUTS);
    }

    //======================================================================================================
    //                                          PUT METHODS
    //======================================================================================================

    public void putRes(OctetString value) {
        attributes.put(EAttributeType.AT_RES, Utils.insertLeadingLength2(value, true));
    }

    public void putMac(OctetString value) {
        attributes.put(EAttributeType.AT_MAC, OctetString.concat(new OctetString("0000"), value));
    }

    public void putAutn(OctetString value) {
        attributes.put(EAttributeType.AT_AUTN, OctetString.concat(new OctetString("0000"), value));
    }

    public void putKdf(int value) {
        attributes.put(EAttributeType.AT_KDF, new Octet2(value).toOctetString());
    }

    public void putClientErrorCode(int code) {
        attributes.put(EAttributeType.AT_CLIENT_ERROR_CODE, new Octet2(code).toOctetString());
    }

    public void putAuts(OctetString auts) {
        attributes.put(EAttributeType.AT_AUTS, auts);
    }

    //======================================================================================================
    //                                          OTHERS
    //======================================================================================================


    public void putRawAttribute(EAttributeType key, OctetString value) {
        attributes.put(key, value);
    }

    public Set<Map.Entry<EAttributeType, OctetString>> entrySet() {
        return attributes.entrySet();
    }
}