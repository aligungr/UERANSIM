/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_UAC_AccessCategory1_SelectionAssistanceInfo extends RRC_Enumerated {

    public static final RRC_UAC_AccessCategory1_SelectionAssistanceInfo A = new RRC_UAC_AccessCategory1_SelectionAssistanceInfo("a");
    public static final RRC_UAC_AccessCategory1_SelectionAssistanceInfo B = new RRC_UAC_AccessCategory1_SelectionAssistanceInfo("b");
    public static final RRC_UAC_AccessCategory1_SelectionAssistanceInfo C = new RRC_UAC_AccessCategory1_SelectionAssistanceInfo("c");

    protected RRC_UAC_AccessCategory1_SelectionAssistanceInfo(String sValue) {
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
