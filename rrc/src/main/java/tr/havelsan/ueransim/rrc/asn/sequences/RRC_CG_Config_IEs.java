/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CandidateServingFreqListNR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FR_InfoList;

public class RRC_CG_Config_IEs extends RRC_Sequence {

    public RRC_OctetString scg_CellGroupConfig;
    public RRC_OctetString scg_RB_Config;
    public RRC_ConfigRestrictModReqSCG configRestrictModReq;
    public RRC_DRX_Info drx_InfoSCG;
    public RRC_OctetString candidateCellInfoListSN;
    public RRC_MeasConfigSN measConfigSN;
    public RRC_BandCombinationInfoSN selectedBandCombination;
    public RRC_FR_InfoList fr_InfoListSCG;
    public RRC_CandidateServingFreqListNR candidateServingFreqListNR;
    public RRC_CG_Config_v1540_IEs nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "scg-CellGroupConfig","scg-RB-Config","configRestrictModReq","drx-InfoSCG","candidateCellInfoListSN","measConfigSN","selectedBandCombination","fr-InfoListSCG","candidateServingFreqListNR","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "scg_CellGroupConfig","scg_RB_Config","configRestrictModReq","drx_InfoSCG","candidateCellInfoListSN","measConfigSN","selectedBandCombination","fr_InfoListSCG","candidateServingFreqListNR","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "CG-Config-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "CG-Config-IEs";
    }

}
