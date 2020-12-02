/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_RAT_Type extends RRC_Enumerated {

    public static final RRC_RAT_Type NR = new RRC_RAT_Type("nr");
    public static final RRC_RAT_Type EUTRA_NR = new RRC_RAT_Type("eutra-nr");
    public static final RRC_RAT_Type EUTRA = new RRC_RAT_Type("eutra");
    public static final RRC_RAT_Type SPARE1 = new RRC_RAT_Type("spare1");

    protected RRC_RAT_Type(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "RAT-Type";
    }

    @Override
    public String getXmlTagName() {
        return "RAT-Type";
    }

}
