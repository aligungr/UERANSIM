/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_FeatureSetDownlink_v1540__pdsch_ProcessingType2_Limited extends RRC_Sequence {

    public RRC_Integer differentTB_PerSlot_SCS_30kHz;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "differentTB-PerSlot-SCS-30kHz" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "differentTB_PerSlot_SCS_30kHz" };
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
