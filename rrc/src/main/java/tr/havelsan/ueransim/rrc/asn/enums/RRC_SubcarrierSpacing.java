/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_SubcarrierSpacing extends RRC_Enumerated {

    public static final RRC_SubcarrierSpacing KHZ15 = new RRC_SubcarrierSpacing("kHz15");
    public static final RRC_SubcarrierSpacing KHZ30 = new RRC_SubcarrierSpacing("kHz30");
    public static final RRC_SubcarrierSpacing KHZ60 = new RRC_SubcarrierSpacing("kHz60");
    public static final RRC_SubcarrierSpacing KHZ120 = new RRC_SubcarrierSpacing("kHz120");
    public static final RRC_SubcarrierSpacing KHZ240 = new RRC_SubcarrierSpacing("kHz240");
    public static final RRC_SubcarrierSpacing SPARE3 = new RRC_SubcarrierSpacing("spare3");
    public static final RRC_SubcarrierSpacing SPARE2 = new RRC_SubcarrierSpacing("spare2");
    public static final RRC_SubcarrierSpacing SPARE1 = new RRC_SubcarrierSpacing("spare1");

    protected RRC_SubcarrierSpacing(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "SubcarrierSpacing";
    }

    @Override
    public String getXmlTagName() {
        return "SubcarrierSpacing";
    }

}
