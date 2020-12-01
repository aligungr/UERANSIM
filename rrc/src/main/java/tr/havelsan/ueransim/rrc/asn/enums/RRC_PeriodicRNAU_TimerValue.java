/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_PeriodicRNAU_TimerValue extends RRC_Enumerated {

    public static final RRC_PeriodicRNAU_TimerValue MIN5 = new RRC_PeriodicRNAU_TimerValue("min5");
    public static final RRC_PeriodicRNAU_TimerValue MIN10 = new RRC_PeriodicRNAU_TimerValue("min10");
    public static final RRC_PeriodicRNAU_TimerValue MIN20 = new RRC_PeriodicRNAU_TimerValue("min20");
    public static final RRC_PeriodicRNAU_TimerValue MIN30 = new RRC_PeriodicRNAU_TimerValue("min30");
    public static final RRC_PeriodicRNAU_TimerValue MIN60 = new RRC_PeriodicRNAU_TimerValue("min60");
    public static final RRC_PeriodicRNAU_TimerValue MIN120 = new RRC_PeriodicRNAU_TimerValue("min120");
    public static final RRC_PeriodicRNAU_TimerValue MIN360 = new RRC_PeriodicRNAU_TimerValue("min360");
    public static final RRC_PeriodicRNAU_TimerValue MIN720 = new RRC_PeriodicRNAU_TimerValue("min720");

    protected RRC_PeriodicRNAU_TimerValue(String sValue) {
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
