/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_PUCCH_MaxCodeRate extends RRC_Enumerated {

    public static final RRC_PUCCH_MaxCodeRate ZERODOT08 = new RRC_PUCCH_MaxCodeRate("zeroDot08");
    public static final RRC_PUCCH_MaxCodeRate ZERODOT15 = new RRC_PUCCH_MaxCodeRate("zeroDot15");
    public static final RRC_PUCCH_MaxCodeRate ZERODOT25 = new RRC_PUCCH_MaxCodeRate("zeroDot25");
    public static final RRC_PUCCH_MaxCodeRate ZERODOT35 = new RRC_PUCCH_MaxCodeRate("zeroDot35");
    public static final RRC_PUCCH_MaxCodeRate ZERODOT45 = new RRC_PUCCH_MaxCodeRate("zeroDot45");
    public static final RRC_PUCCH_MaxCodeRate ZERODOT60 = new RRC_PUCCH_MaxCodeRate("zeroDot60");
    public static final RRC_PUCCH_MaxCodeRate ZERODOT80 = new RRC_PUCCH_MaxCodeRate("zeroDot80");

    protected RRC_PUCCH_MaxCodeRate(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "PUCCH-MaxCodeRate";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-MaxCodeRate";
    }

}
