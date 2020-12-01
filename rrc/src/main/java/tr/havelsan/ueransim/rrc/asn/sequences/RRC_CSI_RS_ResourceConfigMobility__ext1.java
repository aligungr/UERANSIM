/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_CSI_RS_ResourceConfigMobility__ext1 extends RRC_Sequence {

    public RRC_ServCellIndex refServCellIndex_v1530;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "refServCellIndex-v1530" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "refServCellIndex_v1530" };
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
