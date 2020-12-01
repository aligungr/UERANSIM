/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_MIMO_LayersDL extends RRC_Enumerated {

    public static final RRC_MIMO_LayersDL TWOLAYERS = new RRC_MIMO_LayersDL("twoLayers");
    public static final RRC_MIMO_LayersDL FOURLAYERS = new RRC_MIMO_LayersDL("fourLayers");
    public static final RRC_MIMO_LayersDL EIGHTLAYERS = new RRC_MIMO_LayersDL("eightLayers");

    protected RRC_MIMO_LayersDL(String sValue) {
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
