/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_Alpha extends RRC_Enumerated {

    public static final RRC_Alpha ALPHA0 = new RRC_Alpha("alpha0");
    public static final RRC_Alpha ALPHA04 = new RRC_Alpha("alpha04");
    public static final RRC_Alpha ALPHA05 = new RRC_Alpha("alpha05");
    public static final RRC_Alpha ALPHA06 = new RRC_Alpha("alpha06");
    public static final RRC_Alpha ALPHA07 = new RRC_Alpha("alpha07");
    public static final RRC_Alpha ALPHA08 = new RRC_Alpha("alpha08");
    public static final RRC_Alpha ALPHA09 = new RRC_Alpha("alpha09");
    public static final RRC_Alpha ALPHA1 = new RRC_Alpha("alpha1");

    protected RRC_Alpha(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "Alpha";
    }

    @Override
    public String getXmlTagName() {
        return "Alpha";
    }

}
