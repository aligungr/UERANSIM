/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_ReportInterval extends RRC_Enumerated {

    public static final RRC_ReportInterval MS120 = new RRC_ReportInterval("ms120");
    public static final RRC_ReportInterval MS240 = new RRC_ReportInterval("ms240");
    public static final RRC_ReportInterval MS480 = new RRC_ReportInterval("ms480");
    public static final RRC_ReportInterval MS640 = new RRC_ReportInterval("ms640");
    public static final RRC_ReportInterval MS1024 = new RRC_ReportInterval("ms1024");
    public static final RRC_ReportInterval MS2048 = new RRC_ReportInterval("ms2048");
    public static final RRC_ReportInterval MS5120 = new RRC_ReportInterval("ms5120");
    public static final RRC_ReportInterval MS10240 = new RRC_ReportInterval("ms10240");
    public static final RRC_ReportInterval MS20480 = new RRC_ReportInterval("ms20480");
    public static final RRC_ReportInterval MS40960 = new RRC_ReportInterval("ms40960");
    public static final RRC_ReportInterval MIN1 = new RRC_ReportInterval("min1");
    public static final RRC_ReportInterval MIN6 = new RRC_ReportInterval("min6");
    public static final RRC_ReportInterval MIN12 = new RRC_ReportInterval("min12");
    public static final RRC_ReportInterval MIN30 = new RRC_ReportInterval("min30");

    protected RRC_ReportInterval(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ReportInterval";
    }

    @Override
    public String getXmlTagName() {
        return "ReportInterval";
    }

}
