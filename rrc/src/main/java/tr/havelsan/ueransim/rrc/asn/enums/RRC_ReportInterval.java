/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_ReportInterval extends AsnEnumerated {
    public static final RRC_ReportInterval MS120 = new RRC_ReportInterval(0);
    public static final RRC_ReportInterval MS240 = new RRC_ReportInterval(1);
    public static final RRC_ReportInterval MS480 = new RRC_ReportInterval(2);
    public static final RRC_ReportInterval MS640 = new RRC_ReportInterval(3);
    public static final RRC_ReportInterval MS1024 = new RRC_ReportInterval(4);
    public static final RRC_ReportInterval MS2048 = new RRC_ReportInterval(5);
    public static final RRC_ReportInterval MS5120 = new RRC_ReportInterval(6);
    public static final RRC_ReportInterval MS10240 = new RRC_ReportInterval(7);
    public static final RRC_ReportInterval MS20480 = new RRC_ReportInterval(8);
    public static final RRC_ReportInterval MS40960 = new RRC_ReportInterval(9);
    public static final RRC_ReportInterval MIN1 = new RRC_ReportInterval(10);
    public static final RRC_ReportInterval MIN6 = new RRC_ReportInterval(11);
    public static final RRC_ReportInterval MIN12 = new RRC_ReportInterval(12);
    public static final RRC_ReportInterval MIN30 = new RRC_ReportInterval(13);

    private RRC_ReportInterval(long value) {
        super(value);
    }
}

