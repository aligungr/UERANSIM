/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_Q_OffsetRange extends RRC_Enumerated {

    public static final RRC_Q_OffsetRange DB_24 = new RRC_Q_OffsetRange("dB-24");
    public static final RRC_Q_OffsetRange DB_22 = new RRC_Q_OffsetRange("dB-22");
    public static final RRC_Q_OffsetRange DB_20 = new RRC_Q_OffsetRange("dB-20");
    public static final RRC_Q_OffsetRange DB_18 = new RRC_Q_OffsetRange("dB-18");
    public static final RRC_Q_OffsetRange DB_16 = new RRC_Q_OffsetRange("dB-16");
    public static final RRC_Q_OffsetRange DB_14 = new RRC_Q_OffsetRange("dB-14");
    public static final RRC_Q_OffsetRange DB_12 = new RRC_Q_OffsetRange("dB-12");
    public static final RRC_Q_OffsetRange DB_10 = new RRC_Q_OffsetRange("dB-10");
    public static final RRC_Q_OffsetRange DB_8 = new RRC_Q_OffsetRange("dB-8");
    public static final RRC_Q_OffsetRange DB_6 = new RRC_Q_OffsetRange("dB-6");
    public static final RRC_Q_OffsetRange DB_5 = new RRC_Q_OffsetRange("dB-5");
    public static final RRC_Q_OffsetRange DB_4 = new RRC_Q_OffsetRange("dB-4");
    public static final RRC_Q_OffsetRange DB_3 = new RRC_Q_OffsetRange("dB-3");
    public static final RRC_Q_OffsetRange DB_2 = new RRC_Q_OffsetRange("dB-2");
    public static final RRC_Q_OffsetRange DB_1 = new RRC_Q_OffsetRange("dB-1");
    public static final RRC_Q_OffsetRange DB0 = new RRC_Q_OffsetRange("dB0");
    public static final RRC_Q_OffsetRange DB1 = new RRC_Q_OffsetRange("dB1");
    public static final RRC_Q_OffsetRange DB2 = new RRC_Q_OffsetRange("dB2");
    public static final RRC_Q_OffsetRange DB3 = new RRC_Q_OffsetRange("dB3");
    public static final RRC_Q_OffsetRange DB4 = new RRC_Q_OffsetRange("dB4");
    public static final RRC_Q_OffsetRange DB5 = new RRC_Q_OffsetRange("dB5");
    public static final RRC_Q_OffsetRange DB6 = new RRC_Q_OffsetRange("dB6");
    public static final RRC_Q_OffsetRange DB8 = new RRC_Q_OffsetRange("dB8");
    public static final RRC_Q_OffsetRange DB10 = new RRC_Q_OffsetRange("dB10");
    public static final RRC_Q_OffsetRange DB12 = new RRC_Q_OffsetRange("dB12");
    public static final RRC_Q_OffsetRange DB14 = new RRC_Q_OffsetRange("dB14");
    public static final RRC_Q_OffsetRange DB16 = new RRC_Q_OffsetRange("dB16");
    public static final RRC_Q_OffsetRange DB18 = new RRC_Q_OffsetRange("dB18");
    public static final RRC_Q_OffsetRange DB20 = new RRC_Q_OffsetRange("dB20");
    public static final RRC_Q_OffsetRange DB22 = new RRC_Q_OffsetRange("dB22");
    public static final RRC_Q_OffsetRange DB24 = new RRC_Q_OffsetRange("dB24");

    protected RRC_Q_OffsetRange(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "Q-OffsetRange";
    }

    @Override
    public String getXmlTagName() {
        return "Q-OffsetRange";
    }

}
