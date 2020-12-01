/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_SN_FieldLengthAM extends RRC_Enumerated {

    public static final RRC_SN_FieldLengthAM SIZE12 = new RRC_SN_FieldLengthAM("size12");
    public static final RRC_SN_FieldLengthAM SIZE18 = new RRC_SN_FieldLengthAM("size18");

    protected RRC_SN_FieldLengthAM(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
