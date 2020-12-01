/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_TimeToTrigger extends RRC_Enumerated {

    public static final RRC_TimeToTrigger MS0 = new RRC_TimeToTrigger("ms0");
    public static final RRC_TimeToTrigger MS40 = new RRC_TimeToTrigger("ms40");
    public static final RRC_TimeToTrigger MS64 = new RRC_TimeToTrigger("ms64");
    public static final RRC_TimeToTrigger MS80 = new RRC_TimeToTrigger("ms80");
    public static final RRC_TimeToTrigger MS100 = new RRC_TimeToTrigger("ms100");
    public static final RRC_TimeToTrigger MS128 = new RRC_TimeToTrigger("ms128");
    public static final RRC_TimeToTrigger MS160 = new RRC_TimeToTrigger("ms160");
    public static final RRC_TimeToTrigger MS256 = new RRC_TimeToTrigger("ms256");
    public static final RRC_TimeToTrigger MS320 = new RRC_TimeToTrigger("ms320");
    public static final RRC_TimeToTrigger MS480 = new RRC_TimeToTrigger("ms480");
    public static final RRC_TimeToTrigger MS512 = new RRC_TimeToTrigger("ms512");
    public static final RRC_TimeToTrigger MS640 = new RRC_TimeToTrigger("ms640");
    public static final RRC_TimeToTrigger MS1024 = new RRC_TimeToTrigger("ms1024");
    public static final RRC_TimeToTrigger MS1280 = new RRC_TimeToTrigger("ms1280");
    public static final RRC_TimeToTrigger MS2560 = new RRC_TimeToTrigger("ms2560");
    public static final RRC_TimeToTrigger MS5120 = new RRC_TimeToTrigger("ms5120");

    protected RRC_TimeToTrigger(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "TimeToTrigger";
    }

    @Override
    public String getXmlTagName() {
        return "TimeToTrigger";
    }

}
