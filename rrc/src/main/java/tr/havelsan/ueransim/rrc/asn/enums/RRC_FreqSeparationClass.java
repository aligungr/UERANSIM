/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_FreqSeparationClass extends RRC_Enumerated {

    public static final RRC_FreqSeparationClass C1 = new RRC_FreqSeparationClass("c1");
    public static final RRC_FreqSeparationClass C2 = new RRC_FreqSeparationClass("c2");
    public static final RRC_FreqSeparationClass C3 = new RRC_FreqSeparationClass("c3");

    protected RRC_FreqSeparationClass(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "FreqSeparationClass";
    }

    @Override
    public String getXmlTagName() {
        return "FreqSeparationClass";
    }

}
