/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_NR_RS_Type extends RRC_Enumerated {

    public static final RRC_NR_RS_Type SSB = new RRC_NR_RS_Type("ssb");
    public static final RRC_NR_RS_Type CSI_RS = new RRC_NR_RS_Type("csi-rs");

    protected RRC_NR_RS_Type(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "NR-RS-Type";
    }

    @Override
    public String getXmlTagName() {
        return "NR-RS-Type";
    }

}
