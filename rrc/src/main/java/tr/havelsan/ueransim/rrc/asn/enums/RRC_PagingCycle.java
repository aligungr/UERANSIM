/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_PagingCycle extends RRC_Enumerated {

    public static final RRC_PagingCycle RF32 = new RRC_PagingCycle("rf32");
    public static final RRC_PagingCycle RF64 = new RRC_PagingCycle("rf64");
    public static final RRC_PagingCycle RF128 = new RRC_PagingCycle("rf128");
    public static final RRC_PagingCycle RF256 = new RRC_PagingCycle("rf256");

    protected RRC_PagingCycle(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "PagingCycle";
    }

    @Override
    public String getXmlTagName() {
        return "PagingCycle";
    }

}
