/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_DataInactivityTimer extends RRC_Enumerated {

    public static final RRC_DataInactivityTimer S1 = new RRC_DataInactivityTimer("s1");
    public static final RRC_DataInactivityTimer S2 = new RRC_DataInactivityTimer("s2");
    public static final RRC_DataInactivityTimer S3 = new RRC_DataInactivityTimer("s3");
    public static final RRC_DataInactivityTimer S5 = new RRC_DataInactivityTimer("s5");
    public static final RRC_DataInactivityTimer S7 = new RRC_DataInactivityTimer("s7");
    public static final RRC_DataInactivityTimer S10 = new RRC_DataInactivityTimer("s10");
    public static final RRC_DataInactivityTimer S15 = new RRC_DataInactivityTimer("s15");
    public static final RRC_DataInactivityTimer S20 = new RRC_DataInactivityTimer("s20");
    public static final RRC_DataInactivityTimer S40 = new RRC_DataInactivityTimer("s40");
    public static final RRC_DataInactivityTimer S50 = new RRC_DataInactivityTimer("s50");
    public static final RRC_DataInactivityTimer S60 = new RRC_DataInactivityTimer("s60");
    public static final RRC_DataInactivityTimer S80 = new RRC_DataInactivityTimer("s80");
    public static final RRC_DataInactivityTimer S100 = new RRC_DataInactivityTimer("s100");
    public static final RRC_DataInactivityTimer S120 = new RRC_DataInactivityTimer("s120");
    public static final RRC_DataInactivityTimer S150 = new RRC_DataInactivityTimer("s150");
    public static final RRC_DataInactivityTimer S180 = new RRC_DataInactivityTimer("s180");

    protected RRC_DataInactivityTimer(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "DataInactivityTimer";
    }

    @Override
    public String getXmlTagName() {
        return "DataInactivityTimer";
    }

}
