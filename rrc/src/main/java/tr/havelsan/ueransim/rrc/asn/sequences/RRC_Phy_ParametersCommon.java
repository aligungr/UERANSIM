/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_Phy_ParametersCommon extends RRC_Sequence {

    public RRC_Integer csi_RS_CFRA_ForHO;
    public RRC_Integer dynamicPRB_BundlingDL;
    public RRC_Integer sp_CSI_ReportPUCCH;
    public RRC_Integer sp_CSI_ReportPUSCH;
    public RRC_Integer nzp_CSI_RS_IntefMgmt;
    public RRC_Integer type2_SP_CSI_Feedback_LongPUCCH;
    public RRC_Integer precoderGranularityCORESET;
    public RRC_Integer dynamicHARQ_ACK_Codebook;
    public RRC_Integer semiStaticHARQ_ACK_Codebook;
    public RRC_Integer spatialBundlingHARQ_ACK;
    public RRC_Integer dynamicBetaOffsetInd_HARQ_ACK_CSI;
    public RRC_Integer pucch_Repetition_F1_3_4;
    public RRC_Integer ra_Type0_PUSCH;
    public RRC_Integer dynamicSwitchRA_Type0_1_PDSCH;
    public RRC_Integer dynamicSwitchRA_Type0_1_PUSCH;
    public RRC_Integer pdsch_MappingTypeA;
    public RRC_Integer pdsch_MappingTypeB;
    public RRC_Integer interleavingVRB_ToPRB_PDSCH;
    public RRC_Integer interSlotFreqHopping_PUSCH;
    public RRC_Integer type1_PUSCH_RepetitionMultiSlots;
    public RRC_Integer type2_PUSCH_RepetitionMultiSlots;
    public RRC_Integer pusch_RepetitionMultiSlots;
    public RRC_Integer pdsch_RepetitionMultiSlots;
    public RRC_Integer downlinkSPS;
    public RRC_Integer configuredUL_GrantType1;
    public RRC_Integer configuredUL_GrantType2;
    public RRC_Integer pre_EmptIndication_DL;
    public RRC_Integer cbg_TransIndication_DL;
    public RRC_Integer cbg_TransIndication_UL;
    public RRC_Integer cbg_FlushIndication_DL;
    public RRC_Integer dynamicHARQ_ACK_CodeB_CBG_Retx_DL;
    public RRC_Integer rateMatchingResrcSetSemi_Static;
    public RRC_Integer rateMatchingResrcSetDynamic;
    public RRC_Integer bwp_SwitchingDelay;
    public RRC_Phy_ParametersCommon__ext1 ext1;
    public RRC_Phy_ParametersCommon__ext2 ext2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "csi-RS-CFRA-ForHO","dynamicPRB-BundlingDL","sp-CSI-ReportPUCCH","sp-CSI-ReportPUSCH","nzp-CSI-RS-IntefMgmt","type2-SP-CSI-Feedback-LongPUCCH","precoderGranularityCORESET","dynamicHARQ-ACK-Codebook","semiStaticHARQ-ACK-Codebook","spatialBundlingHARQ-ACK","dynamicBetaOffsetInd-HARQ-ACK-CSI","pucch-Repetition-F1-3-4","ra-Type0-PUSCH","dynamicSwitchRA-Type0-1-PDSCH","dynamicSwitchRA-Type0-1-PUSCH","pdsch-MappingTypeA","pdsch-MappingTypeB","interleavingVRB-ToPRB-PDSCH","interSlotFreqHopping-PUSCH","type1-PUSCH-RepetitionMultiSlots","type2-PUSCH-RepetitionMultiSlots","pusch-RepetitionMultiSlots","pdsch-RepetitionMultiSlots","downlinkSPS","configuredUL-GrantType1","configuredUL-GrantType2","pre-EmptIndication-DL","cbg-TransIndication-DL","cbg-TransIndication-UL","cbg-FlushIndication-DL","dynamicHARQ-ACK-CodeB-CBG-Retx-DL","rateMatchingResrcSetSemi-Static","rateMatchingResrcSetDynamic","bwp-SwitchingDelay","ext1","ext2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "csi_RS_CFRA_ForHO","dynamicPRB_BundlingDL","sp_CSI_ReportPUCCH","sp_CSI_ReportPUSCH","nzp_CSI_RS_IntefMgmt","type2_SP_CSI_Feedback_LongPUCCH","precoderGranularityCORESET","dynamicHARQ_ACK_Codebook","semiStaticHARQ_ACK_Codebook","spatialBundlingHARQ_ACK","dynamicBetaOffsetInd_HARQ_ACK_CSI","pucch_Repetition_F1_3_4","ra_Type0_PUSCH","dynamicSwitchRA_Type0_1_PDSCH","dynamicSwitchRA_Type0_1_PUSCH","pdsch_MappingTypeA","pdsch_MappingTypeB","interleavingVRB_ToPRB_PDSCH","interSlotFreqHopping_PUSCH","type1_PUSCH_RepetitionMultiSlots","type2_PUSCH_RepetitionMultiSlots","pusch_RepetitionMultiSlots","pdsch_RepetitionMultiSlots","downlinkSPS","configuredUL_GrantType1","configuredUL_GrantType2","pre_EmptIndication_DL","cbg_TransIndication_DL","cbg_TransIndication_UL","cbg_FlushIndication_DL","dynamicHARQ_ACK_CodeB_CBG_Retx_DL","rateMatchingResrcSetSemi_Static","rateMatchingResrcSetDynamic","bwp_SwitchingDelay","ext1","ext2" };
    }

    @Override
    public String getAsnName() {
        return "Phy-ParametersCommon";
    }

    @Override
    public String getXmlTagName() {
        return "Phy-ParametersCommon";
    }

}
