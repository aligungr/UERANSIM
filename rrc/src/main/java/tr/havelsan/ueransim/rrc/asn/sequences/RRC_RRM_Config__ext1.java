/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultServFreqListEUTRA_SCG;

public class RRC_RRM_Config__ext1 extends RRC_Sequence {

    public RRC_MeasResultServFreqListEUTRA_SCG candidateCellInfoListSN_EUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "candidateCellInfoListSN-EUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "candidateCellInfoListSN_EUTRA" };
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
