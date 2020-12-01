/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ReducedAggregatedBandwidth;

public class RRC_OverheatingAssistance__reducedMaxBW_FR1 extends RRC_Sequence {

    public RRC_ReducedAggregatedBandwidth reducedBW_FR1_DL;
    public RRC_ReducedAggregatedBandwidth reducedBW_FR1_UL;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reducedBW-FR1-DL","reducedBW-FR1-UL" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reducedBW_FR1_DL","reducedBW_FR1_UL" };
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
