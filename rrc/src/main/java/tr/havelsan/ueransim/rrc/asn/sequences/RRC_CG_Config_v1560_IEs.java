/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CandidateServingFreqListEUTRA;

public class RRC_CG_Config_v1560_IEs extends RRC_Sequence {

    public RRC_ARFCN_ValueEUTRA pSCellFrequencyEUTRA;
    public RRC_OctetString scg_CellGroupConfigEUTRA;
    public RRC_OctetString candidateCellInfoListSN_EUTRA;
    public RRC_CandidateServingFreqListEUTRA candidateServingFreqListEUTRA;
    public RRC_Integer needForGaps;
    public RRC_DRX_Config drx_ConfigSCG;
    public RRC_CG_Config_v1560_IEs__reportCGI_RequestEUTRA reportCGI_RequestEUTRA;
    public RRC_CG_Config_v1560_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pSCellFrequencyEUTRA","scg-CellGroupConfigEUTRA","candidateCellInfoListSN-EUTRA","candidateServingFreqListEUTRA","needForGaps","drx-ConfigSCG","reportCGI-RequestEUTRA","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pSCellFrequencyEUTRA","scg_CellGroupConfigEUTRA","candidateCellInfoListSN_EUTRA","candidateServingFreqListEUTRA","needForGaps","drx_ConfigSCG","reportCGI_RequestEUTRA","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "CG-Config-v1560-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "CG-Config-v1560-IEs";
    }

}
