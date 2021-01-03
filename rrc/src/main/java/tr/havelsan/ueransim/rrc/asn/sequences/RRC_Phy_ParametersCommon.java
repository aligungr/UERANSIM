/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_Phy_ParametersCommon extends AsnSequence {
    public RRC_csi_RS_CFRA_ForHO csi_RS_CFRA_ForHO; // optional
    public RRC_dynamicPRB_BundlingDL dynamicPRB_BundlingDL; // optional
    public RRC_sp_CSI_ReportPUCCH sp_CSI_ReportPUCCH; // optional
    public RRC_sp_CSI_ReportPUSCH sp_CSI_ReportPUSCH; // optional
    public RRC_nzp_CSI_RS_IntefMgmt nzp_CSI_RS_IntefMgmt; // optional
    public RRC_type2_SP_CSI_Feedback_LongPUCCH type2_SP_CSI_Feedback_LongPUCCH; // optional
    public RRC_precoderGranularityCORESET precoderGranularityCORESET; // optional
    public RRC_dynamicHARQ_ACK_Codebook dynamicHARQ_ACK_Codebook; // optional
    public RRC_semiStaticHARQ_ACK_Codebook semiStaticHARQ_ACK_Codebook; // optional
    public RRC_spatialBundlingHARQ_ACK spatialBundlingHARQ_ACK; // optional
    public RRC_dynamicBetaOffsetInd_HARQ_ACK_CSI dynamicBetaOffsetInd_HARQ_ACK_CSI; // optional
    public RRC_pucch_Repetition_F1_3_4 pucch_Repetition_F1_3_4; // optional
    public RRC_ra_Type0_PUSCH ra_Type0_PUSCH; // optional
    public RRC_dynamicSwitchRA_Type0_1_PDSCH dynamicSwitchRA_Type0_1_PDSCH; // optional
    public RRC_dynamicSwitchRA_Type0_1_PUSCH dynamicSwitchRA_Type0_1_PUSCH; // optional
    public RRC_pdsch_MappingTypeA pdsch_MappingTypeA; // optional
    public RRC_pdsch_MappingTypeB pdsch_MappingTypeB; // optional
    public RRC_interleavingVRB_ToPRB_PDSCH interleavingVRB_ToPRB_PDSCH; // optional
    public RRC_interSlotFreqHopping_PUSCH interSlotFreqHopping_PUSCH; // optional
    public RRC_type1_PUSCH_RepetitionMultiSlots type1_PUSCH_RepetitionMultiSlots; // optional
    public RRC_type2_PUSCH_RepetitionMultiSlots type2_PUSCH_RepetitionMultiSlots; // optional
    public RRC_pusch_RepetitionMultiSlots pusch_RepetitionMultiSlots; // optional
    public RRC_pdsch_RepetitionMultiSlots pdsch_RepetitionMultiSlots; // optional
    public RRC_downlinkSPS downlinkSPS; // optional
    public RRC_configuredUL_GrantType1 configuredUL_GrantType1; // optional
    public RRC_configuredUL_GrantType2 configuredUL_GrantType2; // optional
    public RRC_pre_EmptIndication_DL pre_EmptIndication_DL; // optional
    public RRC_cbg_TransIndication_DL cbg_TransIndication_DL; // optional
    public RRC_cbg_TransIndication_UL cbg_TransIndication_UL; // optional
    public RRC_cbg_FlushIndication_DL cbg_FlushIndication_DL; // optional
    public RRC_dynamicHARQ_ACK_CodeB_CBG_Retx_DL dynamicHARQ_ACK_CodeB_CBG_Retx_DL; // optional
    public RRC_rateMatchingResrcSetSemi_Static rateMatchingResrcSetSemi_Static; // optional
    public RRC_rateMatchingResrcSetDynamic rateMatchingResrcSetDynamic; // optional
    public RRC_bwp_SwitchingDelay bwp_SwitchingDelay; // optional
    public RRC_ext1_6 ext1; // optional
    public RRC_ext2_5 ext2; // optional

    public static class RRC_ext1_6 extends AsnSequence {
        public RRC_dummy_3 dummy; // optional
    
        public static class RRC_dummy_3 extends AsnEnumerated {
            public static final RRC_dummy_3 SUPPORTED = new RRC_dummy_3(0);
        
            private RRC_dummy_3(long value) {
                super(value);
            }
        }
    }

    public static class RRC_rateMatchingResrcSetSemi_Static extends AsnEnumerated {
        public static final RRC_rateMatchingResrcSetSemi_Static SUPPORTED = new RRC_rateMatchingResrcSetSemi_Static(0);
    
        private RRC_rateMatchingResrcSetSemi_Static(long value) {
            super(value);
        }
    }

    public static class RRC_dynamicHARQ_ACK_CodeB_CBG_Retx_DL extends AsnEnumerated {
        public static final RRC_dynamicHARQ_ACK_CodeB_CBG_Retx_DL SUPPORTED = new RRC_dynamicHARQ_ACK_CodeB_CBG_Retx_DL(0);
    
        private RRC_dynamicHARQ_ACK_CodeB_CBG_Retx_DL(long value) {
            super(value);
        }
    }

    public static class RRC_cbg_TransIndication_DL extends AsnEnumerated {
        public static final RRC_cbg_TransIndication_DL SUPPORTED = new RRC_cbg_TransIndication_DL(0);
    
        private RRC_cbg_TransIndication_DL(long value) {
            super(value);
        }
    }

    public static class RRC_type2_PUSCH_RepetitionMultiSlots extends AsnEnumerated {
        public static final RRC_type2_PUSCH_RepetitionMultiSlots SUPPORTED = new RRC_type2_PUSCH_RepetitionMultiSlots(0);
    
        private RRC_type2_PUSCH_RepetitionMultiSlots(long value) {
            super(value);
        }
    }

    public static class RRC_interSlotFreqHopping_PUSCH extends AsnEnumerated {
        public static final RRC_interSlotFreqHopping_PUSCH SUPPORTED = new RRC_interSlotFreqHopping_PUSCH(0);
    
        private RRC_interSlotFreqHopping_PUSCH(long value) {
            super(value);
        }
    }

    public static class RRC_interleavingVRB_ToPRB_PDSCH extends AsnEnumerated {
        public static final RRC_interleavingVRB_ToPRB_PDSCH SUPPORTED = new RRC_interleavingVRB_ToPRB_PDSCH(0);
    
        private RRC_interleavingVRB_ToPRB_PDSCH(long value) {
            super(value);
        }
    }

    public static class RRC_pdsch_MappingTypeB extends AsnEnumerated {
        public static final RRC_pdsch_MappingTypeB SUPPORTED = new RRC_pdsch_MappingTypeB(0);
    
        private RRC_pdsch_MappingTypeB(long value) {
            super(value);
        }
    }

    public static class RRC_pdsch_MappingTypeA extends AsnEnumerated {
        public static final RRC_pdsch_MappingTypeA SUPPORTED = new RRC_pdsch_MappingTypeA(0);
    
        private RRC_pdsch_MappingTypeA(long value) {
            super(value);
        }
    }

    public static class RRC_dynamicSwitchRA_Type0_1_PDSCH extends AsnEnumerated {
        public static final RRC_dynamicSwitchRA_Type0_1_PDSCH SUPPORTED = new RRC_dynamicSwitchRA_Type0_1_PDSCH(0);
    
        private RRC_dynamicSwitchRA_Type0_1_PDSCH(long value) {
            super(value);
        }
    }

    public static class RRC_ra_Type0_PUSCH extends AsnEnumerated {
        public static final RRC_ra_Type0_PUSCH SUPPORTED = new RRC_ra_Type0_PUSCH(0);
    
        private RRC_ra_Type0_PUSCH(long value) {
            super(value);
        }
    }

    public static class RRC_semiStaticHARQ_ACK_Codebook extends AsnEnumerated {
        public static final RRC_semiStaticHARQ_ACK_Codebook SUPPORTED = new RRC_semiStaticHARQ_ACK_Codebook(0);
    
        private RRC_semiStaticHARQ_ACK_Codebook(long value) {
            super(value);
        }
    }

    public static class RRC_precoderGranularityCORESET extends AsnEnumerated {
        public static final RRC_precoderGranularityCORESET SUPPORTED = new RRC_precoderGranularityCORESET(0);
    
        private RRC_precoderGranularityCORESET(long value) {
            super(value);
        }
    }

    public static class RRC_sp_CSI_ReportPUSCH extends AsnEnumerated {
        public static final RRC_sp_CSI_ReportPUSCH SUPPORTED = new RRC_sp_CSI_ReportPUSCH(0);
    
        private RRC_sp_CSI_ReportPUSCH(long value) {
            super(value);
        }
    }

    public static class RRC_sp_CSI_ReportPUCCH extends AsnEnumerated {
        public static final RRC_sp_CSI_ReportPUCCH SUPPORTED = new RRC_sp_CSI_ReportPUCCH(0);
    
        private RRC_sp_CSI_ReportPUCCH(long value) {
            super(value);
        }
    }

    public static class RRC_csi_RS_CFRA_ForHO extends AsnEnumerated {
        public static final RRC_csi_RS_CFRA_ForHO SUPPORTED = new RRC_csi_RS_CFRA_ForHO(0);
    
        private RRC_csi_RS_CFRA_ForHO(long value) {
            super(value);
        }
    }

    public static class RRC_spatialBundlingHARQ_ACK extends AsnEnumerated {
        public static final RRC_spatialBundlingHARQ_ACK SUPPORTED = new RRC_spatialBundlingHARQ_ACK(0);
    
        private RRC_spatialBundlingHARQ_ACK(long value) {
            super(value);
        }
    }

    public static class RRC_ext2_5 extends AsnSequence {
        public RRC_maxNumberSearchSpaces maxNumberSearchSpaces; // optional
        public RRC_rateMatchingCtrlResrcSetDynamic rateMatchingCtrlResrcSetDynamic; // optional
        public RRC_maxLayersMIMO_Indication maxLayersMIMO_Indication; // optional
    
        public static class RRC_rateMatchingCtrlResrcSetDynamic extends AsnEnumerated {
            public static final RRC_rateMatchingCtrlResrcSetDynamic SUPPORTED = new RRC_rateMatchingCtrlResrcSetDynamic(0);
        
            private RRC_rateMatchingCtrlResrcSetDynamic(long value) {
                super(value);
            }
        }
    
        public static class RRC_maxNumberSearchSpaces extends AsnEnumerated {
            public static final RRC_maxNumberSearchSpaces N10 = new RRC_maxNumberSearchSpaces(0);
        
            private RRC_maxNumberSearchSpaces(long value) {
                super(value);
            }
        }
    
        public static class RRC_maxLayersMIMO_Indication extends AsnEnumerated {
            public static final RRC_maxLayersMIMO_Indication SUPPORTED = new RRC_maxLayersMIMO_Indication(0);
        
            private RRC_maxLayersMIMO_Indication(long value) {
                super(value);
            }
        }
    }

    public static class RRC_configuredUL_GrantType2 extends AsnEnumerated {
        public static final RRC_configuredUL_GrantType2 SUPPORTED = new RRC_configuredUL_GrantType2(0);
    
        private RRC_configuredUL_GrantType2(long value) {
            super(value);
        }
    }

    public static class RRC_downlinkSPS extends AsnEnumerated {
        public static final RRC_downlinkSPS SUPPORTED = new RRC_downlinkSPS(0);
    
        private RRC_downlinkSPS(long value) {
            super(value);
        }
    }

    public static class RRC_cbg_FlushIndication_DL extends AsnEnumerated {
        public static final RRC_cbg_FlushIndication_DL SUPPORTED = new RRC_cbg_FlushIndication_DL(0);
    
        private RRC_cbg_FlushIndication_DL(long value) {
            super(value);
        }
    }

    public static class RRC_dynamicPRB_BundlingDL extends AsnEnumerated {
        public static final RRC_dynamicPRB_BundlingDL SUPPORTED = new RRC_dynamicPRB_BundlingDL(0);
    
        private RRC_dynamicPRB_BundlingDL(long value) {
            super(value);
        }
    }

    public static class RRC_type2_SP_CSI_Feedback_LongPUCCH extends AsnEnumerated {
        public static final RRC_type2_SP_CSI_Feedback_LongPUCCH SUPPORTED = new RRC_type2_SP_CSI_Feedback_LongPUCCH(0);
    
        private RRC_type2_SP_CSI_Feedback_LongPUCCH(long value) {
            super(value);
        }
    }

    public static class RRC_cbg_TransIndication_UL extends AsnEnumerated {
        public static final RRC_cbg_TransIndication_UL SUPPORTED = new RRC_cbg_TransIndication_UL(0);
    
        private RRC_cbg_TransIndication_UL(long value) {
            super(value);
        }
    }

    public static class RRC_type1_PUSCH_RepetitionMultiSlots extends AsnEnumerated {
        public static final RRC_type1_PUSCH_RepetitionMultiSlots SUPPORTED = new RRC_type1_PUSCH_RepetitionMultiSlots(0);
    
        private RRC_type1_PUSCH_RepetitionMultiSlots(long value) {
            super(value);
        }
    }

    public static class RRC_pucch_Repetition_F1_3_4 extends AsnEnumerated {
        public static final RRC_pucch_Repetition_F1_3_4 SUPPORTED = new RRC_pucch_Repetition_F1_3_4(0);
    
        private RRC_pucch_Repetition_F1_3_4(long value) {
            super(value);
        }
    }

    public static class RRC_nzp_CSI_RS_IntefMgmt extends AsnEnumerated {
        public static final RRC_nzp_CSI_RS_IntefMgmt SUPPORTED = new RRC_nzp_CSI_RS_IntefMgmt(0);
    
        private RRC_nzp_CSI_RS_IntefMgmt(long value) {
            super(value);
        }
    }

    public static class RRC_rateMatchingResrcSetDynamic extends AsnEnumerated {
        public static final RRC_rateMatchingResrcSetDynamic SUPPORTED = new RRC_rateMatchingResrcSetDynamic(0);
    
        private RRC_rateMatchingResrcSetDynamic(long value) {
            super(value);
        }
    }

    public static class RRC_dynamicBetaOffsetInd_HARQ_ACK_CSI extends AsnEnumerated {
        public static final RRC_dynamicBetaOffsetInd_HARQ_ACK_CSI SUPPORTED = new RRC_dynamicBetaOffsetInd_HARQ_ACK_CSI(0);
    
        private RRC_dynamicBetaOffsetInd_HARQ_ACK_CSI(long value) {
            super(value);
        }
    }

    public static class RRC_configuredUL_GrantType1 extends AsnEnumerated {
        public static final RRC_configuredUL_GrantType1 SUPPORTED = new RRC_configuredUL_GrantType1(0);
    
        private RRC_configuredUL_GrantType1(long value) {
            super(value);
        }
    }

    public static class RRC_pusch_RepetitionMultiSlots extends AsnEnumerated {
        public static final RRC_pusch_RepetitionMultiSlots SUPPORTED = new RRC_pusch_RepetitionMultiSlots(0);
    
        private RRC_pusch_RepetitionMultiSlots(long value) {
            super(value);
        }
    }

    public static class RRC_dynamicHARQ_ACK_Codebook extends AsnEnumerated {
        public static final RRC_dynamicHARQ_ACK_Codebook SUPPORTED = new RRC_dynamicHARQ_ACK_Codebook(0);
    
        private RRC_dynamicHARQ_ACK_Codebook(long value) {
            super(value);
        }
    }

    public static class RRC_pre_EmptIndication_DL extends AsnEnumerated {
        public static final RRC_pre_EmptIndication_DL SUPPORTED = new RRC_pre_EmptIndication_DL(0);
    
        private RRC_pre_EmptIndication_DL(long value) {
            super(value);
        }
    }

    public static class RRC_bwp_SwitchingDelay extends AsnEnumerated {
        public static final RRC_bwp_SwitchingDelay TYPE1 = new RRC_bwp_SwitchingDelay(0);
        public static final RRC_bwp_SwitchingDelay TYPE2 = new RRC_bwp_SwitchingDelay(1);
    
        private RRC_bwp_SwitchingDelay(long value) {
            super(value);
        }
    }

    public static class RRC_pdsch_RepetitionMultiSlots extends AsnEnumerated {
        public static final RRC_pdsch_RepetitionMultiSlots SUPPORTED = new RRC_pdsch_RepetitionMultiSlots(0);
    
        private RRC_pdsch_RepetitionMultiSlots(long value) {
            super(value);
        }
    }

    public static class RRC_dynamicSwitchRA_Type0_1_PUSCH extends AsnEnumerated {
        public static final RRC_dynamicSwitchRA_Type0_1_PUSCH SUPPORTED = new RRC_dynamicSwitchRA_Type0_1_PUSCH(0);
    
        private RRC_dynamicSwitchRA_Type0_1_PUSCH(long value) {
            super(value);
        }
    }
}

