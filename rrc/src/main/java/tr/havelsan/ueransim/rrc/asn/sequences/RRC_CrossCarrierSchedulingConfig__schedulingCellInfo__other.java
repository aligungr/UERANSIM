/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_CrossCarrierSchedulingConfig__schedulingCellInfo__other extends RRC_Sequence {

    public RRC_ServCellIndex schedulingCellId;
    public RRC_Integer cif_InSchedulingCell;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "schedulingCellId","cif-InSchedulingCell" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "schedulingCellId","cif_InSchedulingCell" };
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
