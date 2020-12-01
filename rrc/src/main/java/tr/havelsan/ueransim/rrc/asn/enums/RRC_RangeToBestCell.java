/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_RangeToBestCell extends RRC_Enumerated {

    public static final RRC_RangeToBestCell DB_24 = new RRC_RangeToBestCell("dB-24");
    public static final RRC_RangeToBestCell DB_22 = new RRC_RangeToBestCell("dB-22");
    public static final RRC_RangeToBestCell DB_20 = new RRC_RangeToBestCell("dB-20");
    public static final RRC_RangeToBestCell DB_18 = new RRC_RangeToBestCell("dB-18");
    public static final RRC_RangeToBestCell DB_16 = new RRC_RangeToBestCell("dB-16");
    public static final RRC_RangeToBestCell DB_14 = new RRC_RangeToBestCell("dB-14");
    public static final RRC_RangeToBestCell DB_12 = new RRC_RangeToBestCell("dB-12");
    public static final RRC_RangeToBestCell DB_10 = new RRC_RangeToBestCell("dB-10");
    public static final RRC_RangeToBestCell DB_8 = new RRC_RangeToBestCell("dB-8");
    public static final RRC_RangeToBestCell DB_6 = new RRC_RangeToBestCell("dB-6");
    public static final RRC_RangeToBestCell DB_5 = new RRC_RangeToBestCell("dB-5");
    public static final RRC_RangeToBestCell DB_4 = new RRC_RangeToBestCell("dB-4");
    public static final RRC_RangeToBestCell DB_3 = new RRC_RangeToBestCell("dB-3");
    public static final RRC_RangeToBestCell DB_2 = new RRC_RangeToBestCell("dB-2");
    public static final RRC_RangeToBestCell DB_1 = new RRC_RangeToBestCell("dB-1");
    public static final RRC_RangeToBestCell DB0 = new RRC_RangeToBestCell("dB0");
    public static final RRC_RangeToBestCell DB1 = new RRC_RangeToBestCell("dB1");
    public static final RRC_RangeToBestCell DB2 = new RRC_RangeToBestCell("dB2");
    public static final RRC_RangeToBestCell DB3 = new RRC_RangeToBestCell("dB3");
    public static final RRC_RangeToBestCell DB4 = new RRC_RangeToBestCell("dB4");
    public static final RRC_RangeToBestCell DB5 = new RRC_RangeToBestCell("dB5");
    public static final RRC_RangeToBestCell DB6 = new RRC_RangeToBestCell("dB6");
    public static final RRC_RangeToBestCell DB8 = new RRC_RangeToBestCell("dB8");
    public static final RRC_RangeToBestCell DB10 = new RRC_RangeToBestCell("dB10");
    public static final RRC_RangeToBestCell DB12 = new RRC_RangeToBestCell("dB12");
    public static final RRC_RangeToBestCell DB14 = new RRC_RangeToBestCell("dB14");
    public static final RRC_RangeToBestCell DB16 = new RRC_RangeToBestCell("dB16");
    public static final RRC_RangeToBestCell DB18 = new RRC_RangeToBestCell("dB18");
    public static final RRC_RangeToBestCell DB20 = new RRC_RangeToBestCell("dB20");
    public static final RRC_RangeToBestCell DB22 = new RRC_RangeToBestCell("dB22");
    public static final RRC_RangeToBestCell DB24 = new RRC_RangeToBestCell("dB24");

    protected RRC_RangeToBestCell(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "RangeToBestCell";
    }

    @Override
    public String getXmlTagName() {
        return "RangeToBestCell";
    }
}
