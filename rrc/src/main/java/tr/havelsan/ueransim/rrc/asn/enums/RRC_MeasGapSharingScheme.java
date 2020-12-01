/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_MeasGapSharingScheme extends RRC_Enumerated {

    public static final RRC_MeasGapSharingScheme SCHEME00 = new RRC_MeasGapSharingScheme("scheme00");
    public static final RRC_MeasGapSharingScheme SCHEME01 = new RRC_MeasGapSharingScheme("scheme01");
    public static final RRC_MeasGapSharingScheme SCHEME10 = new RRC_MeasGapSharingScheme("scheme10");
    public static final RRC_MeasGapSharingScheme SCHEME11 = new RRC_MeasGapSharingScheme("scheme11");

    protected RRC_MeasGapSharingScheme(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "MeasGapSharingScheme";
    }

    @Override
    public String getXmlTagName() {
        return "MeasGapSharingScheme";
    }

}
