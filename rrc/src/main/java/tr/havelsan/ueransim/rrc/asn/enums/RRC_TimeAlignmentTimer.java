/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_TimeAlignmentTimer extends RRC_Enumerated {

    public static final RRC_TimeAlignmentTimer MS500 = new RRC_TimeAlignmentTimer("ms500");
    public static final RRC_TimeAlignmentTimer MS750 = new RRC_TimeAlignmentTimer("ms750");
    public static final RRC_TimeAlignmentTimer MS1280 = new RRC_TimeAlignmentTimer("ms1280");
    public static final RRC_TimeAlignmentTimer MS1920 = new RRC_TimeAlignmentTimer("ms1920");
    public static final RRC_TimeAlignmentTimer MS2560 = new RRC_TimeAlignmentTimer("ms2560");
    public static final RRC_TimeAlignmentTimer MS5120 = new RRC_TimeAlignmentTimer("ms5120");
    public static final RRC_TimeAlignmentTimer MS10240 = new RRC_TimeAlignmentTimer("ms10240");
    public static final RRC_TimeAlignmentTimer INFINITY = new RRC_TimeAlignmentTimer("infinity");

    protected RRC_TimeAlignmentTimer(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "TimeAlignmentTimer";
    }

    @Override
    public String getXmlTagName() {
        return "TimeAlignmentTimer";
    }

}
