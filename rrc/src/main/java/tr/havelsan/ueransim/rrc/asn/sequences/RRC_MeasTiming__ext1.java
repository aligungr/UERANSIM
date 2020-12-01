/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SSB_ToMeasure;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_MeasTiming__ext1 extends RRC_Sequence {

    public RRC_SSB_ToMeasure ssb_ToMeasure_v1540;
    public RRC_PhysCellId physCellId;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssb-ToMeasure-v1540","physCellId" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssb_ToMeasure_v1540","physCellId" };
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
