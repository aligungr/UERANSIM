/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CFRA__resources__csirs__csirs_ResourceList;

public class RRC_CFRA__resources__csirs extends RRC_Sequence {

    public RRC_CFRA__resources__csirs__csirs_ResourceList csirs_ResourceList;
    public RRC_RSRP_Range rsrp_ThresholdCSI_RS;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "csirs-ResourceList","rsrp-ThresholdCSI-RS" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "csirs_ResourceList","rsrp_ThresholdCSI_RS" };
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
