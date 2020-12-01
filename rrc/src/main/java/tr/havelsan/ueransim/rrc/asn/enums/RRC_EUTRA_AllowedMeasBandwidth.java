/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_EUTRA_AllowedMeasBandwidth extends RRC_Enumerated {

    public static final RRC_EUTRA_AllowedMeasBandwidth MBW6 = new RRC_EUTRA_AllowedMeasBandwidth("mbw6");
    public static final RRC_EUTRA_AllowedMeasBandwidth MBW15 = new RRC_EUTRA_AllowedMeasBandwidth("mbw15");
    public static final RRC_EUTRA_AllowedMeasBandwidth MBW25 = new RRC_EUTRA_AllowedMeasBandwidth("mbw25");
    public static final RRC_EUTRA_AllowedMeasBandwidth MBW50 = new RRC_EUTRA_AllowedMeasBandwidth("mbw50");
    public static final RRC_EUTRA_AllowedMeasBandwidth MBW75 = new RRC_EUTRA_AllowedMeasBandwidth("mbw75");
    public static final RRC_EUTRA_AllowedMeasBandwidth MBW100 = new RRC_EUTRA_AllowedMeasBandwidth("mbw100");

    protected RRC_EUTRA_AllowedMeasBandwidth(String sValue) {
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
