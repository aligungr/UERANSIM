/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_MIMO_ParametersPerBand extends AsnSequence {
    public RRC_tci_StatePDSCH tci_StatePDSCH; // optional
    public RRC_additionalActiveTCI_StatePDCCH additionalActiveTCI_StatePDCCH; // optional
    public RRC_pusch_TransCoherence pusch_TransCoherence; // optional
    public RRC_beamCorrespondenceWithoutUL_BeamSweeping beamCorrespondenceWithoutUL_BeamSweeping; // optional
    public RRC_periodicBeamReport periodicBeamReport; // optional
    public RRC_aperiodicBeamReport aperiodicBeamReport; // optional
    public RRC_sp_BeamReportPUCCH sp_BeamReportPUCCH; // optional
    public RRC_sp_BeamReportPUSCH sp_BeamReportPUSCH; // optional
    public RRC_DummyG dummy1; // optional
    public AsnInteger maxNumberRxBeam; // optional, VALUE(2..8)
    public RRC_maxNumberRxTxBeamSwitchDL maxNumberRxTxBeamSwitchDL; // optional
    public RRC_maxNumberNonGroupBeamReporting maxNumberNonGroupBeamReporting; // optional
    public RRC_groupBeamReporting groupBeamReporting; // optional
    public RRC_uplinkBeamManagement uplinkBeamManagement; // optional
    public AsnInteger maxNumberCSI_RS_BFD; // optional, VALUE(1..64)
    public AsnInteger maxNumberSSB_BFD; // optional, VALUE(1..64)
    public AsnInteger maxNumberCSI_RS_SSB_CBD; // optional, VALUE(1..256)
    public RRC_dummy2_3 dummy2; // optional
    public RRC_twoPortsPTRS_UL twoPortsPTRS_UL; // optional
    public RRC_SRS_Resources dummy5; // optional
    public AsnInteger dummy3; // optional, VALUE(1..4)
    public RRC_beamReportTiming beamReportTiming; // optional
    public RRC_ptrs_DensityRecommendationSetDL ptrs_DensityRecommendationSetDL; // optional
    public RRC_ptrs_DensityRecommendationSetUL ptrs_DensityRecommendationSetUL; // optional
    public RRC_DummyH dummy4; // optional
    public RRC_aperiodicTRS aperiodicTRS; // optional
    public RRC_ext1_41 ext1; // optional

    public static class RRC_uplinkBeamManagement extends AsnSequence {
        public RRC_maxNumberSRS_ResourcePerSet_BM maxNumberSRS_ResourcePerSet_BM; // mandatory
        public AsnInteger maxNumberSRS_ResourceSet; // mandatory, VALUE(1..8)
    
        public static class RRC_maxNumberSRS_ResourcePerSet_BM extends AsnEnumerated {
            public static final RRC_maxNumberSRS_ResourcePerSet_BM N2 = new RRC_maxNumberSRS_ResourcePerSet_BM(0);
            public static final RRC_maxNumberSRS_ResourcePerSet_BM N4 = new RRC_maxNumberSRS_ResourcePerSet_BM(1);
            public static final RRC_maxNumberSRS_ResourcePerSet_BM N8 = new RRC_maxNumberSRS_ResourcePerSet_BM(2);
            public static final RRC_maxNumberSRS_ResourcePerSet_BM N16 = new RRC_maxNumberSRS_ResourcePerSet_BM(3);
        
            private RRC_maxNumberSRS_ResourcePerSet_BM(long value) {
                super(value);
            }
        }
    }

    public static class RRC_additionalActiveTCI_StatePDCCH extends AsnEnumerated {
        public static final RRC_additionalActiveTCI_StatePDCCH SUPPORTED = new RRC_additionalActiveTCI_StatePDCCH(0);
    
        private RRC_additionalActiveTCI_StatePDCCH(long value) {
            super(value);
        }
    }

    public static class RRC_beamReportTiming extends AsnSequence {
        public RRC_scs_15kHz_3 scs_15kHz; // optional
        public RRC_scs_30kHz_2 scs_30kHz; // optional
        public RRC_scs_60kHz_5 scs_60kHz; // optional
        public RRC_scs_120kHz_4 scs_120kHz; // optional
    
        public static class RRC_scs_120kHz_4 extends AsnEnumerated {
            public static final RRC_scs_120kHz_4 SYM14 = new RRC_scs_120kHz_4(0);
            public static final RRC_scs_120kHz_4 SYM28 = new RRC_scs_120kHz_4(1);
            public static final RRC_scs_120kHz_4 SYM56 = new RRC_scs_120kHz_4(2);
        
            private RRC_scs_120kHz_4(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_30kHz_2 extends AsnEnumerated {
            public static final RRC_scs_30kHz_2 SYM4 = new RRC_scs_30kHz_2(0);
            public static final RRC_scs_30kHz_2 SYM8 = new RRC_scs_30kHz_2(1);
            public static final RRC_scs_30kHz_2 SYM14 = new RRC_scs_30kHz_2(2);
            public static final RRC_scs_30kHz_2 SYM28 = new RRC_scs_30kHz_2(3);
        
            private RRC_scs_30kHz_2(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_15kHz_3 extends AsnEnumerated {
            public static final RRC_scs_15kHz_3 SYM2 = new RRC_scs_15kHz_3(0);
            public static final RRC_scs_15kHz_3 SYM4 = new RRC_scs_15kHz_3(1);
            public static final RRC_scs_15kHz_3 SYM8 = new RRC_scs_15kHz_3(2);
        
            private RRC_scs_15kHz_3(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_60kHz_5 extends AsnEnumerated {
            public static final RRC_scs_60kHz_5 SYM8 = new RRC_scs_60kHz_5(0);
            public static final RRC_scs_60kHz_5 SYM14 = new RRC_scs_60kHz_5(1);
            public static final RRC_scs_60kHz_5 SYM28 = new RRC_scs_60kHz_5(2);
        
            private RRC_scs_60kHz_5(long value) {
                super(value);
            }
        }
    }

    public static class RRC_pusch_TransCoherence extends AsnEnumerated {
        public static final RRC_pusch_TransCoherence NONCOHERENT = new RRC_pusch_TransCoherence(0);
        public static final RRC_pusch_TransCoherence PARTIALCOHERENT = new RRC_pusch_TransCoherence(1);
        public static final RRC_pusch_TransCoherence FULLCOHERENT = new RRC_pusch_TransCoherence(2);
    
        private RRC_pusch_TransCoherence(long value) {
            super(value);
        }
    }

    public static class RRC_ext1_41 extends AsnSequence {
        public RRC_dummy6_2 dummy6; // optional
        public RRC_BeamManagementSSB_CSI_RS beamManagementSSB_CSI_RS; // optional
        public RRC_beamSwitchTiming beamSwitchTiming; // optional
        public RRC_CodebookParameters codebookParameters; // optional
        public RRC_CSI_RS_IM_ReceptionForFeedback csi_RS_IM_ReceptionForFeedback; // optional
        public RRC_CSI_RS_ProcFrameworkForSRS csi_RS_ProcFrameworkForSRS; // optional
        public RRC_CSI_ReportFramework csi_ReportFramework; // optional
        public RRC_CSI_RS_ForTracking csi_RS_ForTracking; // optional
        public RRC_srs_AssocCSI_RS srs_AssocCSI_RS; // optional, SIZE(1..7)
        public RRC_SpatialRelations spatialRelations; // optional
    
        public static class RRC_beamSwitchTiming extends AsnSequence {
            public RRC_scs_60kHz_2 scs_60kHz; // optional
            public RRC_scs_120kHz_6 scs_120kHz; // optional
        
            public static class RRC_scs_120kHz_6 extends AsnEnumerated {
                public static final RRC_scs_120kHz_6 SYM14 = new RRC_scs_120kHz_6(0);
                public static final RRC_scs_120kHz_6 SYM28 = new RRC_scs_120kHz_6(1);
                public static final RRC_scs_120kHz_6 SYM48 = new RRC_scs_120kHz_6(2);
                public static final RRC_scs_120kHz_6 SYM224 = new RRC_scs_120kHz_6(3);
                public static final RRC_scs_120kHz_6 SYM336 = new RRC_scs_120kHz_6(4);
            
                private RRC_scs_120kHz_6(long value) {
                    super(value);
                }
            }
        
            public static class RRC_scs_60kHz_2 extends AsnEnumerated {
                public static final RRC_scs_60kHz_2 SYM14 = new RRC_scs_60kHz_2(0);
                public static final RRC_scs_60kHz_2 SYM28 = new RRC_scs_60kHz_2(1);
                public static final RRC_scs_60kHz_2 SYM48 = new RRC_scs_60kHz_2(2);
                public static final RRC_scs_60kHz_2 SYM224 = new RRC_scs_60kHz_2(3);
                public static final RRC_scs_60kHz_2 SYM336 = new RRC_scs_60kHz_2(4);
            
                private RRC_scs_60kHz_2(long value) {
                    super(value);
                }
            }
        }
    
        public static class RRC_dummy6_2 extends AsnEnumerated {
            public static final RRC_dummy6_2 TRUE = new RRC_dummy6_2(0);
        
            private RRC_dummy6_2(long value) {
                super(value);
            }
        }
    
        // SIZE(1..7)
        public static class RRC_srs_AssocCSI_RS extends AsnSequenceOf<RRC_SupportedCSI_RS_Resource> {
        }
    }

    public static class RRC_twoPortsPTRS_UL extends AsnEnumerated {
        public static final RRC_twoPortsPTRS_UL SUPPORTED = new RRC_twoPortsPTRS_UL(0);
    
        private RRC_twoPortsPTRS_UL(long value) {
            super(value);
        }
    }

    public static class RRC_tci_StatePDSCH extends AsnSequence {
        public RRC_maxNumberConfiguredTCIstatesPerCC maxNumberConfiguredTCIstatesPerCC; // optional
        public RRC_maxNumberActiveTCI_PerBWP maxNumberActiveTCI_PerBWP; // optional
    
        public static class RRC_maxNumberConfiguredTCIstatesPerCC extends AsnEnumerated {
            public static final RRC_maxNumberConfiguredTCIstatesPerCC N4 = new RRC_maxNumberConfiguredTCIstatesPerCC(0);
            public static final RRC_maxNumberConfiguredTCIstatesPerCC N8 = new RRC_maxNumberConfiguredTCIstatesPerCC(1);
            public static final RRC_maxNumberConfiguredTCIstatesPerCC N16 = new RRC_maxNumberConfiguredTCIstatesPerCC(2);
            public static final RRC_maxNumberConfiguredTCIstatesPerCC N32 = new RRC_maxNumberConfiguredTCIstatesPerCC(3);
            public static final RRC_maxNumberConfiguredTCIstatesPerCC N64 = new RRC_maxNumberConfiguredTCIstatesPerCC(4);
            public static final RRC_maxNumberConfiguredTCIstatesPerCC N128 = new RRC_maxNumberConfiguredTCIstatesPerCC(5);
        
            private RRC_maxNumberConfiguredTCIstatesPerCC(long value) {
                super(value);
            }
        }
    
        public static class RRC_maxNumberActiveTCI_PerBWP extends AsnEnumerated {
            public static final RRC_maxNumberActiveTCI_PerBWP N1 = new RRC_maxNumberActiveTCI_PerBWP(0);
            public static final RRC_maxNumberActiveTCI_PerBWP N2 = new RRC_maxNumberActiveTCI_PerBWP(1);
            public static final RRC_maxNumberActiveTCI_PerBWP N4 = new RRC_maxNumberActiveTCI_PerBWP(2);
            public static final RRC_maxNumberActiveTCI_PerBWP N8 = new RRC_maxNumberActiveTCI_PerBWP(3);
        
            private RRC_maxNumberActiveTCI_PerBWP(long value) {
                super(value);
            }
        }
    }

    public static class RRC_sp_BeamReportPUCCH extends AsnEnumerated {
        public static final RRC_sp_BeamReportPUCCH SUPPORTED = new RRC_sp_BeamReportPUCCH(0);
    
        private RRC_sp_BeamReportPUCCH(long value) {
            super(value);
        }
    }

    public static class RRC_beamCorrespondenceWithoutUL_BeamSweeping extends AsnEnumerated {
        public static final RRC_beamCorrespondenceWithoutUL_BeamSweeping SUPPORTED = new RRC_beamCorrespondenceWithoutUL_BeamSweeping(0);
    
        private RRC_beamCorrespondenceWithoutUL_BeamSweeping(long value) {
            super(value);
        }
    }

    public static class RRC_dummy2_3 extends AsnEnumerated {
        public static final RRC_dummy2_3 SUPPORTED = new RRC_dummy2_3(0);
    
        private RRC_dummy2_3(long value) {
            super(value);
        }
    }

    public static class RRC_periodicBeamReport extends AsnEnumerated {
        public static final RRC_periodicBeamReport SUPPORTED = new RRC_periodicBeamReport(0);
    
        private RRC_periodicBeamReport(long value) {
            super(value);
        }
    }

    public static class RRC_sp_BeamReportPUSCH extends AsnEnumerated {
        public static final RRC_sp_BeamReportPUSCH SUPPORTED = new RRC_sp_BeamReportPUSCH(0);
    
        private RRC_sp_BeamReportPUSCH(long value) {
            super(value);
        }
    }

    public static class RRC_ptrs_DensityRecommendationSetUL extends AsnSequence {
        public RRC_PTRS_DensityRecommendationUL scs_15kHz; // optional
        public RRC_PTRS_DensityRecommendationUL scs_30kHz; // optional
        public RRC_PTRS_DensityRecommendationUL scs_60kHz; // optional
        public RRC_PTRS_DensityRecommendationUL scs_120kHz; // optional
    }

    public static class RRC_aperiodicTRS extends AsnEnumerated {
        public static final RRC_aperiodicTRS SUPPORTED = new RRC_aperiodicTRS(0);
    
        private RRC_aperiodicTRS(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberRxTxBeamSwitchDL extends AsnSequence {
        public RRC_scs_15kHz_4 scs_15kHz; // optional
        public RRC_scs_30kHz_3 scs_30kHz; // optional
        public RRC_scs_60kHz_6 scs_60kHz; // optional
        public RRC_scs_120kHz_3 scs_120kHz; // optional
        public RRC_scs_240kHz scs_240kHz; // optional
    
        public static class RRC_scs_120kHz_3 extends AsnEnumerated {
            public static final RRC_scs_120kHz_3 N4 = new RRC_scs_120kHz_3(0);
            public static final RRC_scs_120kHz_3 N7 = new RRC_scs_120kHz_3(1);
            public static final RRC_scs_120kHz_3 N14 = new RRC_scs_120kHz_3(2);
        
            private RRC_scs_120kHz_3(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_30kHz_3 extends AsnEnumerated {
            public static final RRC_scs_30kHz_3 N4 = new RRC_scs_30kHz_3(0);
            public static final RRC_scs_30kHz_3 N7 = new RRC_scs_30kHz_3(1);
            public static final RRC_scs_30kHz_3 N14 = new RRC_scs_30kHz_3(2);
        
            private RRC_scs_30kHz_3(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_15kHz_4 extends AsnEnumerated {
            public static final RRC_scs_15kHz_4 N4 = new RRC_scs_15kHz_4(0);
            public static final RRC_scs_15kHz_4 N7 = new RRC_scs_15kHz_4(1);
            public static final RRC_scs_15kHz_4 N14 = new RRC_scs_15kHz_4(2);
        
            private RRC_scs_15kHz_4(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_240kHz extends AsnEnumerated {
            public static final RRC_scs_240kHz N4 = new RRC_scs_240kHz(0);
            public static final RRC_scs_240kHz N7 = new RRC_scs_240kHz(1);
            public static final RRC_scs_240kHz N14 = new RRC_scs_240kHz(2);
        
            private RRC_scs_240kHz(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_60kHz_6 extends AsnEnumerated {
            public static final RRC_scs_60kHz_6 N4 = new RRC_scs_60kHz_6(0);
            public static final RRC_scs_60kHz_6 N7 = new RRC_scs_60kHz_6(1);
            public static final RRC_scs_60kHz_6 N14 = new RRC_scs_60kHz_6(2);
        
            private RRC_scs_60kHz_6(long value) {
                super(value);
            }
        }
    }

    public static class RRC_ptrs_DensityRecommendationSetDL extends AsnSequence {
        public RRC_PTRS_DensityRecommendationDL scs_15kHz; // optional
        public RRC_PTRS_DensityRecommendationDL scs_30kHz; // optional
        public RRC_PTRS_DensityRecommendationDL scs_60kHz; // optional
        public RRC_PTRS_DensityRecommendationDL scs_120kHz; // optional
    }

    public static class RRC_aperiodicBeamReport extends AsnEnumerated {
        public static final RRC_aperiodicBeamReport SUPPORTED = new RRC_aperiodicBeamReport(0);
    
        private RRC_aperiodicBeamReport(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberNonGroupBeamReporting extends AsnEnumerated {
        public static final RRC_maxNumberNonGroupBeamReporting N1 = new RRC_maxNumberNonGroupBeamReporting(0);
        public static final RRC_maxNumberNonGroupBeamReporting N2 = new RRC_maxNumberNonGroupBeamReporting(1);
        public static final RRC_maxNumberNonGroupBeamReporting N4 = new RRC_maxNumberNonGroupBeamReporting(2);
    
        private RRC_maxNumberNonGroupBeamReporting(long value) {
            super(value);
        }
    }

    public static class RRC_groupBeamReporting extends AsnEnumerated {
        public static final RRC_groupBeamReporting SUPPORTED = new RRC_groupBeamReporting(0);
    
        private RRC_groupBeamReporting(long value) {
            super(value);
        }
    }
}

