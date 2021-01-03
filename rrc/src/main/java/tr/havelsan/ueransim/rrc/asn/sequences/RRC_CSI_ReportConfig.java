/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_ReportPeriodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_PortIndexFor8Ranks;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_ReportConfigId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_ResourceConfigId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P0_PUSCH_AlphaSetId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_CSI_ReportConfig extends AsnSequence {
    public RRC_CSI_ReportConfigId reportConfigId; // mandatory
    public RRC_ServCellIndex carrier; // optional
    public RRC_CSI_ResourceConfigId resourcesForChannelMeasurement; // mandatory
    public RRC_CSI_ResourceConfigId csi_IM_ResourcesForInterference; // optional
    public RRC_CSI_ResourceConfigId nzp_CSI_RS_ResourcesForInterference; // optional
    public RRC_reportConfigType reportConfigType; // mandatory
    public RRC_reportQuantity reportQuantity; // mandatory
    public RRC_reportFreqConfiguration reportFreqConfiguration; // optional
    public RRC_timeRestrictionForChannelMeasurements timeRestrictionForChannelMeasurements; // mandatory
    public RRC_timeRestrictionForInterferenceMeasurements timeRestrictionForInterferenceMeasurements; // mandatory
    public RRC_CodebookConfig codebookConfig; // optional
    public RRC_dummy_4 dummy; // optional
    public RRC_groupBasedBeamReporting groupBasedBeamReporting; // mandatory
    public RRC_cqi_Table cqi_Table; // optional
    public RRC_subbandSize subbandSize; // mandatory
    public RRC_non_PMI_PortIndication non_PMI_PortIndication; // optional, SIZE(1..128)
    public RRC_ext1_50 ext1; // optional

    public static class RRC_dummy_4 extends AsnEnumerated {
        public static final RRC_dummy_4 N1 = new RRC_dummy_4(0);
        public static final RRC_dummy_4 N2 = new RRC_dummy_4(1);
    
        private RRC_dummy_4(long value) {
            super(value);
        }
    }

    public static class RRC_reportConfigType extends AsnChoice {
        public RRC_periodic_3 periodic;
        public RRC_semiPersistentOnPUCCH semiPersistentOnPUCCH;
        public RRC_semiPersistentOnPUSCH semiPersistentOnPUSCH;
        public RRC_aperiodic_3 aperiodic;
    
        public static class RRC_semiPersistentOnPUCCH extends AsnSequence {
            public RRC_CSI_ReportPeriodicityAndOffset reportSlotConfig; // mandatory
            public RRC_pucch_CSI_ResourceList_1 pucch_CSI_ResourceList; // mandatory, SIZE(1..4)
        
            // SIZE(1..4)
            public static class RRC_pucch_CSI_ResourceList_1 extends AsnSequenceOf<RRC_PUCCH_CSI_Resource> {
            }
        }
    
        public static class RRC_periodic_3 extends AsnSequence {
            public RRC_CSI_ReportPeriodicityAndOffset reportSlotConfig; // mandatory
            public RRC_pucch_CSI_ResourceList_2 pucch_CSI_ResourceList; // mandatory, SIZE(1..4)
        
            // SIZE(1..4)
            public static class RRC_pucch_CSI_ResourceList_2 extends AsnSequenceOf<RRC_PUCCH_CSI_Resource> {
            }
        }
    
        public static class RRC_semiPersistentOnPUSCH extends AsnSequence {
            public RRC_reportSlotConfig reportSlotConfig; // mandatory
            public RRC_reportSlotOffsetList_1 reportSlotOffsetList; // mandatory, SIZE(1..16)
            public RRC_P0_PUSCH_AlphaSetId p0alpha; // mandatory
        
            // SIZE(1..16)
            public static class RRC_reportSlotOffsetList_1 extends AsnSequenceOf<AsnInteger> {
            }
        
            public static class RRC_reportSlotConfig extends AsnEnumerated {
                public static final RRC_reportSlotConfig SL5 = new RRC_reportSlotConfig(0);
                public static final RRC_reportSlotConfig SL10 = new RRC_reportSlotConfig(1);
                public static final RRC_reportSlotConfig SL20 = new RRC_reportSlotConfig(2);
                public static final RRC_reportSlotConfig SL40 = new RRC_reportSlotConfig(3);
                public static final RRC_reportSlotConfig SL80 = new RRC_reportSlotConfig(4);
                public static final RRC_reportSlotConfig SL160 = new RRC_reportSlotConfig(5);
                public static final RRC_reportSlotConfig SL320 = new RRC_reportSlotConfig(6);
            
                private RRC_reportSlotConfig(long value) {
                    super(value);
                }
            }
        }
    
        public static class RRC_aperiodic_3 extends AsnSequence {
            public RRC_reportSlotOffsetList_2 reportSlotOffsetList; // mandatory, SIZE(1..16)
        
            // SIZE(1..16)
            public static class RRC_reportSlotOffsetList_2 extends AsnSequenceOf<AsnInteger> {
            }
        }
    }

    public static class RRC_cqi_Table extends AsnEnumerated {
        public static final RRC_cqi_Table TABLE1 = new RRC_cqi_Table(0);
        public static final RRC_cqi_Table TABLE2 = new RRC_cqi_Table(1);
        public static final RRC_cqi_Table TABLE3 = new RRC_cqi_Table(2);
        public static final RRC_cqi_Table SPARE1 = new RRC_cqi_Table(3);
    
        private RRC_cqi_Table(long value) {
            super(value);
        }
    }

    public static class RRC_timeRestrictionForInterferenceMeasurements extends AsnEnumerated {
        public static final RRC_timeRestrictionForInterferenceMeasurements CONFIGURED = new RRC_timeRestrictionForInterferenceMeasurements(0);
        public static final RRC_timeRestrictionForInterferenceMeasurements NOTCONFIGURED = new RRC_timeRestrictionForInterferenceMeasurements(1);
    
        private RRC_timeRestrictionForInterferenceMeasurements(long value) {
            super(value);
        }
    }

    // SIZE(1..128)
    public static class RRC_non_PMI_PortIndication extends AsnSequenceOf<RRC_PortIndexFor8Ranks> {
    }

    public static class RRC_subbandSize extends AsnEnumerated {
        public static final RRC_subbandSize VALUE1 = new RRC_subbandSize(0);
        public static final RRC_subbandSize VALUE2 = new RRC_subbandSize(1);
    
        private RRC_subbandSize(long value) {
            super(value);
        }
    }

    public static class RRC_ext1_50 extends AsnSequence {
        public RRC_semiPersistentOnPUSCH_v1530 semiPersistentOnPUSCH_v1530; // optional
    
        public static class RRC_semiPersistentOnPUSCH_v1530 extends AsnSequence {
            public RRC_reportSlotConfig_v1530 reportSlotConfig_v1530; // mandatory
        
            public static class RRC_reportSlotConfig_v1530 extends AsnEnumerated {
                public static final RRC_reportSlotConfig_v1530 SL4 = new RRC_reportSlotConfig_v1530(0);
                public static final RRC_reportSlotConfig_v1530 SL8 = new RRC_reportSlotConfig_v1530(1);
                public static final RRC_reportSlotConfig_v1530 SL16 = new RRC_reportSlotConfig_v1530(2);
            
                private RRC_reportSlotConfig_v1530(long value) {
                    super(value);
                }
            }
        }
    }

    public static class RRC_reportQuantity extends AsnChoice {
        public AsnNull none;
        public AsnNull cri_RI_PMI_CQI;
        public AsnNull cri_RI_i1;
        public RRC_cri_RI_i1_CQI cri_RI_i1_CQI;
        public AsnNull cri_RI_CQI;
        public AsnNull cri_RSRP;
        public AsnNull ssb_Index_RSRP;
        public AsnNull cri_RI_LI_PMI_CQI;
    
        public static class RRC_cri_RI_i1_CQI extends AsnSequence {
            public RRC_pdsch_BundleSizeForCSI pdsch_BundleSizeForCSI; // optional
        
            public static class RRC_pdsch_BundleSizeForCSI extends AsnEnumerated {
                public static final RRC_pdsch_BundleSizeForCSI N2 = new RRC_pdsch_BundleSizeForCSI(0);
                public static final RRC_pdsch_BundleSizeForCSI N4 = new RRC_pdsch_BundleSizeForCSI(1);
            
                private RRC_pdsch_BundleSizeForCSI(long value) {
                    super(value);
                }
            }
        }
    }

    public static class RRC_reportFreqConfiguration extends AsnSequence {
        public RRC_cqi_FormatIndicator cqi_FormatIndicator; // optional
        public RRC_pmi_FormatIndicator pmi_FormatIndicator; // optional
        public RRC_csi_ReportingBand csi_ReportingBand; // optional
    
        public static class RRC_csi_ReportingBand extends AsnChoice {
            public AsnBitString subbands3; // SIZE(3)
            public AsnBitString subbands4; // SIZE(4)
            public AsnBitString subbands5; // SIZE(5)
            public AsnBitString subbands6; // SIZE(6)
            public AsnBitString subbands7; // SIZE(7)
            public AsnBitString subbands8; // SIZE(8)
            public AsnBitString subbands9; // SIZE(9)
            public AsnBitString subbands10; // SIZE(10)
            public AsnBitString subbands11; // SIZE(11)
            public AsnBitString subbands12; // SIZE(12)
            public AsnBitString subbands13; // SIZE(13)
            public AsnBitString subbands14; // SIZE(14)
            public AsnBitString subbands15; // SIZE(15)
            public AsnBitString subbands16; // SIZE(16)
            public AsnBitString subbands17; // SIZE(17)
            public AsnBitString subbands18; // SIZE(18)
            public AsnBitString subbands19_v1530; // SIZE(19)
        }
    
        public static class RRC_cqi_FormatIndicator extends AsnEnumerated {
            public static final RRC_cqi_FormatIndicator WIDEBANDCQI = new RRC_cqi_FormatIndicator(0);
            public static final RRC_cqi_FormatIndicator SUBBANDCQI = new RRC_cqi_FormatIndicator(1);
        
            private RRC_cqi_FormatIndicator(long value) {
                super(value);
            }
        }
    
        public static class RRC_pmi_FormatIndicator extends AsnEnumerated {
            public static final RRC_pmi_FormatIndicator WIDEBANDPMI = new RRC_pmi_FormatIndicator(0);
            public static final RRC_pmi_FormatIndicator SUBBANDPMI = new RRC_pmi_FormatIndicator(1);
        
            private RRC_pmi_FormatIndicator(long value) {
                super(value);
            }
        }
    }

    public static class RRC_timeRestrictionForChannelMeasurements extends AsnEnumerated {
        public static final RRC_timeRestrictionForChannelMeasurements CONFIGURED = new RRC_timeRestrictionForChannelMeasurements(0);
        public static final RRC_timeRestrictionForChannelMeasurements NOTCONFIGURED = new RRC_timeRestrictionForChannelMeasurements(1);
    
        private RRC_timeRestrictionForChannelMeasurements(long value) {
            super(value);
        }
    }

    public static class RRC_groupBasedBeamReporting extends AsnChoice {
        public AsnNull enabled;
        public RRC_disabled disabled;
    
        public static class RRC_disabled extends AsnSequence {
            public RRC_nrofReportedRS nrofReportedRS; // optional
        
            public static class RRC_nrofReportedRS extends AsnEnumerated {
                public static final RRC_nrofReportedRS N1 = new RRC_nrofReportedRS(0);
                public static final RRC_nrofReportedRS N2 = new RRC_nrofReportedRS(1);
                public static final RRC_nrofReportedRS N3 = new RRC_nrofReportedRS(2);
                public static final RRC_nrofReportedRS N4 = new RRC_nrofReportedRS(3);
            
                private RRC_nrofReportedRS(long value) {
                    super(value);
                }
            }
        }
    }
}

