/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CrossCarrierSchedulingConfig__schedulingCellInfo__own extends RRC_Sequence {

    public RRC_Boolean cif_Presence;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cif-Presence" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cif_Presence" };
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
