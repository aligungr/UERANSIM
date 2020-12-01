/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_UL_DataSplitThreshold extends RRC_Enumerated {

    public static final RRC_UL_DataSplitThreshold B0 = new RRC_UL_DataSplitThreshold("b0");
    public static final RRC_UL_DataSplitThreshold B100 = new RRC_UL_DataSplitThreshold("b100");
    public static final RRC_UL_DataSplitThreshold B200 = new RRC_UL_DataSplitThreshold("b200");
    public static final RRC_UL_DataSplitThreshold B400 = new RRC_UL_DataSplitThreshold("b400");
    public static final RRC_UL_DataSplitThreshold B800 = new RRC_UL_DataSplitThreshold("b800");
    public static final RRC_UL_DataSplitThreshold B1600 = new RRC_UL_DataSplitThreshold("b1600");
    public static final RRC_UL_DataSplitThreshold B3200 = new RRC_UL_DataSplitThreshold("b3200");
    public static final RRC_UL_DataSplitThreshold B6400 = new RRC_UL_DataSplitThreshold("b6400");
    public static final RRC_UL_DataSplitThreshold B12800 = new RRC_UL_DataSplitThreshold("b12800");
    public static final RRC_UL_DataSplitThreshold B25600 = new RRC_UL_DataSplitThreshold("b25600");
    public static final RRC_UL_DataSplitThreshold B51200 = new RRC_UL_DataSplitThreshold("b51200");
    public static final RRC_UL_DataSplitThreshold B102400 = new RRC_UL_DataSplitThreshold("b102400");
    public static final RRC_UL_DataSplitThreshold B204800 = new RRC_UL_DataSplitThreshold("b204800");
    public static final RRC_UL_DataSplitThreshold B409600 = new RRC_UL_DataSplitThreshold("b409600");
    public static final RRC_UL_DataSplitThreshold B819200 = new RRC_UL_DataSplitThreshold("b819200");
    public static final RRC_UL_DataSplitThreshold B1228800 = new RRC_UL_DataSplitThreshold("b1228800");
    public static final RRC_UL_DataSplitThreshold B1638400 = new RRC_UL_DataSplitThreshold("b1638400");
    public static final RRC_UL_DataSplitThreshold B2457600 = new RRC_UL_DataSplitThreshold("b2457600");
    public static final RRC_UL_DataSplitThreshold B3276800 = new RRC_UL_DataSplitThreshold("b3276800");
    public static final RRC_UL_DataSplitThreshold B4096000 = new RRC_UL_DataSplitThreshold("b4096000");
    public static final RRC_UL_DataSplitThreshold B4915200 = new RRC_UL_DataSplitThreshold("b4915200");
    public static final RRC_UL_DataSplitThreshold B5734400 = new RRC_UL_DataSplitThreshold("b5734400");
    public static final RRC_UL_DataSplitThreshold B6553600 = new RRC_UL_DataSplitThreshold("b6553600");
    public static final RRC_UL_DataSplitThreshold INFINITY = new RRC_UL_DataSplitThreshold("infinity");
    public static final RRC_UL_DataSplitThreshold SPARE8 = new RRC_UL_DataSplitThreshold("spare8");
    public static final RRC_UL_DataSplitThreshold SPARE7 = new RRC_UL_DataSplitThreshold("spare7");
    public static final RRC_UL_DataSplitThreshold SPARE6 = new RRC_UL_DataSplitThreshold("spare6");
    public static final RRC_UL_DataSplitThreshold SPARE5 = new RRC_UL_DataSplitThreshold("spare5");
    public static final RRC_UL_DataSplitThreshold SPARE4 = new RRC_UL_DataSplitThreshold("spare4");
    public static final RRC_UL_DataSplitThreshold SPARE3 = new RRC_UL_DataSplitThreshold("spare3");
    public static final RRC_UL_DataSplitThreshold SPARE2 = new RRC_UL_DataSplitThreshold("spare2");
    public static final RRC_UL_DataSplitThreshold SPARE1 = new RRC_UL_DataSplitThreshold("spare1");

    protected RRC_UL_DataSplitThreshold(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
