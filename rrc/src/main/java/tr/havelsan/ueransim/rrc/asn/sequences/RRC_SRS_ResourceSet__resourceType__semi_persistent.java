/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceId;

public class RRC_SRS_ResourceSet__resourceType__semi_persistent extends RRC_Sequence {

    public RRC_NZP_CSI_RS_ResourceId associatedCSI_RS;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "associatedCSI-RS" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "associatedCSI_RS" };
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
