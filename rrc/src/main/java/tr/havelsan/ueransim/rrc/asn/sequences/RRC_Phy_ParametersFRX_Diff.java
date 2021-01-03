/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_Phy_ParametersFRX_Diff extends AsnSequence {
    public RRC_dynamicSFI_1 dynamicSFI; // optional
    public AsnBitString dummy1; // optional, SIZE(2)
    public AsnBitString twoFL_DMRS; // optional, SIZE(2)
    public AsnBitString dummy2; // optional, SIZE(2)
    public AsnBitString dummy3; // optional, SIZE(2)
    public RRC_supportedDMRS_TypeDL supportedDMRS_TypeDL; // optional
    public RRC_supportedDMRS_TypeUL supportedDMRS_TypeUL; // optional
    public RRC_semiOpenLoopCSI semiOpenLoopCSI; // optional
    public RRC_csi_ReportWithoutPMI csi_ReportWithoutPMI; // optional
    public RRC_csi_ReportWithoutCQI csi_ReportWithoutCQI; // optional
    public AsnBitString onePortsPTRS; // optional, SIZE(2)
    public RRC_twoPUCCH_F0_2_ConsecSymbols_1 twoPUCCH_F0_2_ConsecSymbols; // optional
    public RRC_pucch_F2_WithFH pucch_F2_WithFH; // optional
    public RRC_pucch_F3_WithFH pucch_F3_WithFH; // optional
    public RRC_pucch_F4_WithFH pucch_F4_WithFH; // optional
    public RRC_freqHoppingPUCCH_F0_2 freqHoppingPUCCH_F0_2; // optional
    public RRC_freqHoppingPUCCH_F1_3_4 freqHoppingPUCCH_F1_3_4; // optional
    public RRC_mux_SR_HARQ_ACK_CSI_PUCCH_MultiPerSlot mux_SR_HARQ_ACK_CSI_PUCCH_MultiPerSlot; // optional
    public RRC_uci_CodeBlockSegmentation uci_CodeBlockSegmentation; // optional
    public RRC_onePUCCH_LongAndShortFormat onePUCCH_LongAndShortFormat; // optional
    public RRC_twoPUCCH_AnyOthersInSlot twoPUCCH_AnyOthersInSlot; // optional
    public RRC_intraSlotFreqHopping_PUSCH intraSlotFreqHopping_PUSCH; // optional
    public RRC_pusch_LBRM pusch_LBRM; // optional
    public AsnInteger pdcch_BlindDetectionCA; // optional, VALUE(4..16)
    public RRC_tpc_PUSCH_RNTI tpc_PUSCH_RNTI; // optional
    public RRC_tpc_PUCCH_RNTI tpc_PUCCH_RNTI; // optional
    public RRC_tpc_SRS_RNTI tpc_SRS_RNTI; // optional
    public RRC_absoluteTPC_Command absoluteTPC_Command; // optional
    public RRC_twoDifferentTPC_Loop_PUSCH_1 twoDifferentTPC_Loop_PUSCH; // optional
    public RRC_twoDifferentTPC_Loop_PUCCH_2 twoDifferentTPC_Loop_PUCCH; // optional
    public RRC_pusch_HalfPi_BPSK pusch_HalfPi_BPSK; // optional
    public RRC_pucch_F3_4_HalfPi_BPSK pucch_F3_4_HalfPi_BPSK; // optional
    public RRC_almostContiguousCP_OFDM_UL almostContiguousCP_OFDM_UL; // optional
    public RRC_sp_CSI_RS sp_CSI_RS; // optional
    public RRC_sp_CSI_IM sp_CSI_IM; // optional
    public RRC_tdd_MultiDL_UL_SwitchPerSlot tdd_MultiDL_UL_SwitchPerSlot; // optional
    public RRC_multipleCORESET multipleCORESET; // optional
    public RRC_ext1_4 ext1; // optional
    public RRC_ext2_2 ext2; // optional

    public static class RRC_ext2_2 extends AsnSequence {
        public RRC_pdcch_BlindDetectionNRDC pdcch_BlindDetectionNRDC; // optional
        public RRC_mux_HARQ_ACK_PUSCH_DiffSymbol mux_HARQ_ACK_PUSCH_DiffSymbol; // optional
    
        public static class RRC_pdcch_BlindDetectionNRDC extends AsnSequence {
            public AsnInteger pdcch_BlindDetectionMCG_UE; // mandatory, VALUE(1..15)
            public AsnInteger pdcch_BlindDetectionSCG_UE; // mandatory, VALUE(1..15)
        }
    
        public static class RRC_mux_HARQ_ACK_PUSCH_DiffSymbol extends AsnEnumerated {
            public static final RRC_mux_HARQ_ACK_PUSCH_DiffSymbol SUPPORTED = new RRC_mux_HARQ_ACK_PUSCH_DiffSymbol(0);
        
            private RRC_mux_HARQ_ACK_PUSCH_DiffSymbol(long value) {
                super(value);
            }
        }
    }

    public static class RRC_ext1_4 extends AsnSequence {
        public RRC_CSI_RS_IM_ReceptionForFeedback csi_RS_IM_ReceptionForFeedback; // optional
        public RRC_CSI_RS_ProcFrameworkForSRS csi_RS_ProcFrameworkForSRS; // optional
        public RRC_CSI_ReportFramework csi_ReportFramework; // optional
        public RRC_mux_SR_HARQ_ACK_CSI_PUCCH_OncePerSlot mux_SR_HARQ_ACK_CSI_PUCCH_OncePerSlot; // optional
        public RRC_mux_SR_HARQ_ACK_PUCCH mux_SR_HARQ_ACK_PUCCH; // optional
        public RRC_mux_MultipleGroupCtrlCH_Overlap mux_MultipleGroupCtrlCH_Overlap; // optional
        public RRC_dl_SchedulingOffset_PDSCH_TypeA_1 dl_SchedulingOffset_PDSCH_TypeA; // optional
        public RRC_dl_SchedulingOffset_PDSCH_TypeB_2 dl_SchedulingOffset_PDSCH_TypeB; // optional
        public RRC_ul_SchedulingOffset_2 ul_SchedulingOffset; // optional
        public RRC_dl_64QAM_MCS_TableAlt dl_64QAM_MCS_TableAlt; // optional
        public RRC_ul_64QAM_MCS_TableAlt ul_64QAM_MCS_TableAlt; // optional
        public RRC_cqi_TableAlt cqi_TableAlt; // optional
        public RRC_oneFL_DMRS_TwoAdditionalDMRS_UL oneFL_DMRS_TwoAdditionalDMRS_UL; // optional
        public RRC_twoFL_DMRS_TwoAdditionalDMRS_UL twoFL_DMRS_TwoAdditionalDMRS_UL; // optional
        public RRC_oneFL_DMRS_ThreeAdditionalDMRS_UL oneFL_DMRS_ThreeAdditionalDMRS_UL; // optional
    
        public static class RRC_oneFL_DMRS_ThreeAdditionalDMRS_UL extends AsnEnumerated {
            public static final RRC_oneFL_DMRS_ThreeAdditionalDMRS_UL SUPPORTED = new RRC_oneFL_DMRS_ThreeAdditionalDMRS_UL(0);
        
            private RRC_oneFL_DMRS_ThreeAdditionalDMRS_UL(long value) {
                super(value);
            }
        }
    
        public static class RRC_ul_64QAM_MCS_TableAlt extends AsnEnumerated {
            public static final RRC_ul_64QAM_MCS_TableAlt SUPPORTED = new RRC_ul_64QAM_MCS_TableAlt(0);
        
            private RRC_ul_64QAM_MCS_TableAlt(long value) {
                super(value);
            }
        }
    
        public static class RRC_ul_SchedulingOffset_2 extends AsnEnumerated {
            public static final RRC_ul_SchedulingOffset_2 SUPPORTED = new RRC_ul_SchedulingOffset_2(0);
        
            private RRC_ul_SchedulingOffset_2(long value) {
                super(value);
            }
        }
    
        public static class RRC_dl_SchedulingOffset_PDSCH_TypeB_2 extends AsnEnumerated {
            public static final RRC_dl_SchedulingOffset_PDSCH_TypeB_2 SUPPORTED = new RRC_dl_SchedulingOffset_PDSCH_TypeB_2(0);
        
            private RRC_dl_SchedulingOffset_PDSCH_TypeB_2(long value) {
                super(value);
            }
        }
    
        public static class RRC_dl_SchedulingOffset_PDSCH_TypeA_1 extends AsnEnumerated {
            public static final RRC_dl_SchedulingOffset_PDSCH_TypeA_1 SUPPORTED = new RRC_dl_SchedulingOffset_PDSCH_TypeA_1(0);
        
            private RRC_dl_SchedulingOffset_PDSCH_TypeA_1(long value) {
                super(value);
            }
        }
    
        public static class RRC_mux_SR_HARQ_ACK_PUCCH extends AsnEnumerated {
            public static final RRC_mux_SR_HARQ_ACK_PUCCH SUPPORTED = new RRC_mux_SR_HARQ_ACK_PUCCH(0);
        
            private RRC_mux_SR_HARQ_ACK_PUCCH(long value) {
                super(value);
            }
        }
    
        public static class RRC_twoFL_DMRS_TwoAdditionalDMRS_UL extends AsnEnumerated {
            public static final RRC_twoFL_DMRS_TwoAdditionalDMRS_UL SUPPORTED = new RRC_twoFL_DMRS_TwoAdditionalDMRS_UL(0);
        
            private RRC_twoFL_DMRS_TwoAdditionalDMRS_UL(long value) {
                super(value);
            }
        }
    
        public static class RRC_dl_64QAM_MCS_TableAlt extends AsnEnumerated {
            public static final RRC_dl_64QAM_MCS_TableAlt SUPPORTED = new RRC_dl_64QAM_MCS_TableAlt(0);
        
            private RRC_dl_64QAM_MCS_TableAlt(long value) {
                super(value);
            }
        }
    
        public static class RRC_mux_MultipleGroupCtrlCH_Overlap extends AsnEnumerated {
            public static final RRC_mux_MultipleGroupCtrlCH_Overlap SUPPORTED = new RRC_mux_MultipleGroupCtrlCH_Overlap(0);
        
            private RRC_mux_MultipleGroupCtrlCH_Overlap(long value) {
                super(value);
            }
        }
    
        public static class RRC_mux_SR_HARQ_ACK_CSI_PUCCH_OncePerSlot extends AsnSequence {
            public RRC_sameSymbol sameSymbol; // optional
            public RRC_diffSymbol diffSymbol; // optional
        
            public static class RRC_sameSymbol extends AsnEnumerated {
                public static final RRC_sameSymbol SUPPORTED = new RRC_sameSymbol(0);
            
                private RRC_sameSymbol(long value) {
                    super(value);
                }
            }
        
            public static class RRC_diffSymbol extends AsnEnumerated {
                public static final RRC_diffSymbol SUPPORTED = new RRC_diffSymbol(0);
            
                private RRC_diffSymbol(long value) {
                    super(value);
                }
            }
        }
    
        public static class RRC_cqi_TableAlt extends AsnEnumerated {
            public static final RRC_cqi_TableAlt SUPPORTED = new RRC_cqi_TableAlt(0);
        
            private RRC_cqi_TableAlt(long value) {
                super(value);
            }
        }
    
        public static class RRC_oneFL_DMRS_TwoAdditionalDMRS_UL extends AsnEnumerated {
            public static final RRC_oneFL_DMRS_TwoAdditionalDMRS_UL SUPPORTED = new RRC_oneFL_DMRS_TwoAdditionalDMRS_UL(0);
        
            private RRC_oneFL_DMRS_TwoAdditionalDMRS_UL(long value) {
                super(value);
            }
        }
    }

    public static class RRC_pusch_HalfPi_BPSK extends AsnEnumerated {
        public static final RRC_pusch_HalfPi_BPSK SUPPORTED = new RRC_pusch_HalfPi_BPSK(0);
    
        private RRC_pusch_HalfPi_BPSK(long value) {
            super(value);
        }
    }

    public static class RRC_twoDifferentTPC_Loop_PUCCH_2 extends AsnEnumerated {
        public static final RRC_twoDifferentTPC_Loop_PUCCH_2 SUPPORTED = new RRC_twoDifferentTPC_Loop_PUCCH_2(0);
    
        private RRC_twoDifferentTPC_Loop_PUCCH_2(long value) {
            super(value);
        }
    }

    public static class RRC_intraSlotFreqHopping_PUSCH extends AsnEnumerated {
        public static final RRC_intraSlotFreqHopping_PUSCH SUPPORTED = new RRC_intraSlotFreqHopping_PUSCH(0);
    
        private RRC_intraSlotFreqHopping_PUSCH(long value) {
            super(value);
        }
    }

    public static class RRC_onePUCCH_LongAndShortFormat extends AsnEnumerated {
        public static final RRC_onePUCCH_LongAndShortFormat SUPPORTED = new RRC_onePUCCH_LongAndShortFormat(0);
    
        private RRC_onePUCCH_LongAndShortFormat(long value) {
            super(value);
        }
    }

    public static class RRC_uci_CodeBlockSegmentation extends AsnEnumerated {
        public static final RRC_uci_CodeBlockSegmentation SUPPORTED = new RRC_uci_CodeBlockSegmentation(0);
    
        private RRC_uci_CodeBlockSegmentation(long value) {
            super(value);
        }
    }

    public static class RRC_mux_SR_HARQ_ACK_CSI_PUCCH_MultiPerSlot extends AsnEnumerated {
        public static final RRC_mux_SR_HARQ_ACK_CSI_PUCCH_MultiPerSlot SUPPORTED = new RRC_mux_SR_HARQ_ACK_CSI_PUCCH_MultiPerSlot(0);
    
        private RRC_mux_SR_HARQ_ACK_CSI_PUCCH_MultiPerSlot(long value) {
            super(value);
        }
    }

    public static class RRC_freqHoppingPUCCH_F0_2 extends AsnEnumerated {
        public static final RRC_freqHoppingPUCCH_F0_2 NOTSUPPORTED = new RRC_freqHoppingPUCCH_F0_2(0);
    
        private RRC_freqHoppingPUCCH_F0_2(long value) {
            super(value);
        }
    }

    public static class RRC_pucch_F4_WithFH extends AsnEnumerated {
        public static final RRC_pucch_F4_WithFH SUPPORTED = new RRC_pucch_F4_WithFH(0);
    
        private RRC_pucch_F4_WithFH(long value) {
            super(value);
        }
    }

    public static class RRC_pucch_F3_WithFH extends AsnEnumerated {
        public static final RRC_pucch_F3_WithFH SUPPORTED = new RRC_pucch_F3_WithFH(0);
    
        private RRC_pucch_F3_WithFH(long value) {
            super(value);
        }
    }

    public static class RRC_pucch_F2_WithFH extends AsnEnumerated {
        public static final RRC_pucch_F2_WithFH SUPPORTED = new RRC_pucch_F2_WithFH(0);
    
        private RRC_pucch_F2_WithFH(long value) {
            super(value);
        }
    }

    public static class RRC_twoPUCCH_F0_2_ConsecSymbols_1 extends AsnEnumerated {
        public static final RRC_twoPUCCH_F0_2_ConsecSymbols_1 SUPPORTED = new RRC_twoPUCCH_F0_2_ConsecSymbols_1(0);
    
        private RRC_twoPUCCH_F0_2_ConsecSymbols_1(long value) {
            super(value);
        }
    }

    public static class RRC_csi_ReportWithoutCQI extends AsnEnumerated {
        public static final RRC_csi_ReportWithoutCQI SUPPORTED = new RRC_csi_ReportWithoutCQI(0);
    
        private RRC_csi_ReportWithoutCQI(long value) {
            super(value);
        }
    }

    public static class RRC_csi_ReportWithoutPMI extends AsnEnumerated {
        public static final RRC_csi_ReportWithoutPMI SUPPORTED = new RRC_csi_ReportWithoutPMI(0);
    
        private RRC_csi_ReportWithoutPMI(long value) {
            super(value);
        }
    }

    public static class RRC_semiOpenLoopCSI extends AsnEnumerated {
        public static final RRC_semiOpenLoopCSI SUPPORTED = new RRC_semiOpenLoopCSI(0);
    
        private RRC_semiOpenLoopCSI(long value) {
            super(value);
        }
    }

    public static class RRC_supportedDMRS_TypeUL extends AsnEnumerated {
        public static final RRC_supportedDMRS_TypeUL TYPE1 = new RRC_supportedDMRS_TypeUL(0);
        public static final RRC_supportedDMRS_TypeUL TYPE1AND2 = new RRC_supportedDMRS_TypeUL(1);
    
        private RRC_supportedDMRS_TypeUL(long value) {
            super(value);
        }
    }

    public static class RRC_supportedDMRS_TypeDL extends AsnEnumerated {
        public static final RRC_supportedDMRS_TypeDL TYPE1 = new RRC_supportedDMRS_TypeDL(0);
        public static final RRC_supportedDMRS_TypeDL TYPE1AND2 = new RRC_supportedDMRS_TypeDL(1);
    
        private RRC_supportedDMRS_TypeDL(long value) {
            super(value);
        }
    }

    public static class RRC_dynamicSFI_1 extends AsnEnumerated {
        public static final RRC_dynamicSFI_1 SUPPORTED = new RRC_dynamicSFI_1(0);
    
        private RRC_dynamicSFI_1(long value) {
            super(value);
        }
    }

    public static class RRC_twoPUCCH_AnyOthersInSlot extends AsnEnumerated {
        public static final RRC_twoPUCCH_AnyOthersInSlot SUPPORTED = new RRC_twoPUCCH_AnyOthersInSlot(0);
    
        private RRC_twoPUCCH_AnyOthersInSlot(long value) {
            super(value);
        }
    }

    public static class RRC_absoluteTPC_Command extends AsnEnumerated {
        public static final RRC_absoluteTPC_Command SUPPORTED = new RRC_absoluteTPC_Command(0);
    
        private RRC_absoluteTPC_Command(long value) {
            super(value);
        }
    }

    public static class RRC_twoDifferentTPC_Loop_PUSCH_1 extends AsnEnumerated {
        public static final RRC_twoDifferentTPC_Loop_PUSCH_1 SUPPORTED = new RRC_twoDifferentTPC_Loop_PUSCH_1(0);
    
        private RRC_twoDifferentTPC_Loop_PUSCH_1(long value) {
            super(value);
        }
    }

    public static class RRC_tdd_MultiDL_UL_SwitchPerSlot extends AsnEnumerated {
        public static final RRC_tdd_MultiDL_UL_SwitchPerSlot SUPPORTED = new RRC_tdd_MultiDL_UL_SwitchPerSlot(0);
    
        private RRC_tdd_MultiDL_UL_SwitchPerSlot(long value) {
            super(value);
        }
    }

    public static class RRC_sp_CSI_IM extends AsnEnumerated {
        public static final RRC_sp_CSI_IM SUPPORTED = new RRC_sp_CSI_IM(0);
    
        private RRC_sp_CSI_IM(long value) {
            super(value);
        }
    }

    public static class RRC_almostContiguousCP_OFDM_UL extends AsnEnumerated {
        public static final RRC_almostContiguousCP_OFDM_UL SUPPORTED = new RRC_almostContiguousCP_OFDM_UL(0);
    
        private RRC_almostContiguousCP_OFDM_UL(long value) {
            super(value);
        }
    }

    public static class RRC_sp_CSI_RS extends AsnEnumerated {
        public static final RRC_sp_CSI_RS SUPPORTED = new RRC_sp_CSI_RS(0);
    
        private RRC_sp_CSI_RS(long value) {
            super(value);
        }
    }

    public static class RRC_freqHoppingPUCCH_F1_3_4 extends AsnEnumerated {
        public static final RRC_freqHoppingPUCCH_F1_3_4 NOTSUPPORTED = new RRC_freqHoppingPUCCH_F1_3_4(0);
    
        private RRC_freqHoppingPUCCH_F1_3_4(long value) {
            super(value);
        }
    }

    public static class RRC_pucch_F3_4_HalfPi_BPSK extends AsnEnumerated {
        public static final RRC_pucch_F3_4_HalfPi_BPSK SUPPORTED = new RRC_pucch_F3_4_HalfPi_BPSK(0);
    
        private RRC_pucch_F3_4_HalfPi_BPSK(long value) {
            super(value);
        }
    }

    public static class RRC_multipleCORESET extends AsnEnumerated {
        public static final RRC_multipleCORESET SUPPORTED = new RRC_multipleCORESET(0);
    
        private RRC_multipleCORESET(long value) {
            super(value);
        }
    }

    public static class RRC_tpc_PUCCH_RNTI extends AsnEnumerated {
        public static final RRC_tpc_PUCCH_RNTI SUPPORTED = new RRC_tpc_PUCCH_RNTI(0);
    
        private RRC_tpc_PUCCH_RNTI(long value) {
            super(value);
        }
    }

    public static class RRC_tpc_SRS_RNTI extends AsnEnumerated {
        public static final RRC_tpc_SRS_RNTI SUPPORTED = new RRC_tpc_SRS_RNTI(0);
    
        private RRC_tpc_SRS_RNTI(long value) {
            super(value);
        }
    }

    public static class RRC_pusch_LBRM extends AsnEnumerated {
        public static final RRC_pusch_LBRM SUPPORTED = new RRC_pusch_LBRM(0);
    
        private RRC_pusch_LBRM(long value) {
            super(value);
        }
    }

    public static class RRC_tpc_PUSCH_RNTI extends AsnEnumerated {
        public static final RRC_tpc_PUSCH_RNTI SUPPORTED = new RRC_tpc_PUSCH_RNTI(0);
    
        private RRC_tpc_PUSCH_RNTI(long value) {
            super(value);
        }
    }
}

