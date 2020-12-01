/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultServFreqListEUTRA_SCG;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultServFreqListNR_SCG;

public class RRC_MeasResults__ext1 extends RRC_Sequence {

    public RRC_MeasResultServFreqListEUTRA_SCG measResultServFreqListEUTRA_SCG;
    public RRC_MeasResultServFreqListNR_SCG measResultServFreqListNR_SCG;
    public RRC_MeasResultSFTD_EUTRA measResultSFTD_EUTRA;
    public RRC_MeasResultCellSFTD_NR measResultSFTD_NR;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measResultServFreqListEUTRA-SCG","measResultServFreqListNR-SCG","measResultSFTD-EUTRA","measResultSFTD-NR" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measResultServFreqListEUTRA_SCG","measResultServFreqListNR_SCG","measResultSFTD_EUTRA","measResultSFTD_NR" };
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
