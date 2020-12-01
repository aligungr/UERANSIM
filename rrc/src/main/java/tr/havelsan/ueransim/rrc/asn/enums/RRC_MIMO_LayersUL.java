/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_MIMO_LayersUL extends RRC_Enumerated {

    public static final RRC_MIMO_LayersUL ONELAYER = new RRC_MIMO_LayersUL("oneLayer");
    public static final RRC_MIMO_LayersUL TWOLAYERS = new RRC_MIMO_LayersUL("twoLayers");
    public static final RRC_MIMO_LayersUL FOURLAYERS = new RRC_MIMO_LayersUL("fourLayers");

    protected RRC_MIMO_LayersUL(String sValue) {
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
