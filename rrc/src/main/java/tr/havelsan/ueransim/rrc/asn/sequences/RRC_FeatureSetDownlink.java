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
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_FreqSeparationClass;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetDownlinkPerCC_Id;

public class RRC_FeatureSetDownlink extends AsnSequence {
    public RRC_featureSetListPerDownlinkCC featureSetListPerDownlinkCC; // mandatory, SIZE(1..32)
    public RRC_FreqSeparationClass intraBandFreqSeparationDL; // optional
    public RRC_scalingFactor_2 scalingFactor; // optional
    public RRC_crossCarrierScheduling_OtherSCS_2 crossCarrierScheduling_OtherSCS; // optional
    public RRC_scellWithoutSSB scellWithoutSSB; // optional
    public RRC_csi_RS_MeasSCellWithoutSSB csi_RS_MeasSCellWithoutSSB; // optional
    public RRC_dummy1_2 dummy1; // optional
    public RRC_type1_3_CSS type1_3_CSS; // optional
    public RRC_pdcch_MonitoringAnyOccasions pdcch_MonitoringAnyOccasions; // optional
    public RRC_dummy2_1 dummy2; // optional
    public RRC_ue_SpecificUL_DL_Assignment ue_SpecificUL_DL_Assignment; // optional
    public RRC_searchSpaceSharingCA_DL searchSpaceSharingCA_DL; // optional
    public RRC_timeDurationForQCL timeDurationForQCL; // optional
    public RRC_pdsch_ProcessingType1_DifferentTB_PerSlot pdsch_ProcessingType1_DifferentTB_PerSlot; // optional
    public RRC_DummyA dummy3; // optional
    public RRC_dummy4 dummy4; // optional, SIZE(1..16)
    public RRC_dummy5 dummy5; // optional, SIZE(1..16)
    public RRC_dummy6_1 dummy6; // optional, SIZE(1..16)
    public RRC_dummy7 dummy7; // optional, SIZE(1..16)

    // SIZE(1..16)
    public static class RRC_dummy7 extends AsnSequenceOf<RRC_DummyE> {
    }

    public static class RRC_searchSpaceSharingCA_DL extends AsnEnumerated {
        public static final RRC_searchSpaceSharingCA_DL SUPPORTED = new RRC_searchSpaceSharingCA_DL(0);
    
        private RRC_searchSpaceSharingCA_DL(long value) {
            super(value);
        }
    }

    public static class RRC_ue_SpecificUL_DL_Assignment extends AsnEnumerated {
        public static final RRC_ue_SpecificUL_DL_Assignment SUPPORTED = new RRC_ue_SpecificUL_DL_Assignment(0);
    
        private RRC_ue_SpecificUL_DL_Assignment(long value) {
            super(value);
        }
    }

    public static class RRC_type1_3_CSS extends AsnEnumerated {
        public static final RRC_type1_3_CSS SUPPORTED = new RRC_type1_3_CSS(0);
    
        private RRC_type1_3_CSS(long value) {
            super(value);
        }
    }

    public static class RRC_csi_RS_MeasSCellWithoutSSB extends AsnEnumerated {
        public static final RRC_csi_RS_MeasSCellWithoutSSB SUPPORTED = new RRC_csi_RS_MeasSCellWithoutSSB(0);
    
        private RRC_csi_RS_MeasSCellWithoutSSB(long value) {
            super(value);
        }
    }

    public static class RRC_scellWithoutSSB extends AsnEnumerated {
        public static final RRC_scellWithoutSSB SUPPORTED = new RRC_scellWithoutSSB(0);
    
        private RRC_scellWithoutSSB(long value) {
            super(value);
        }
    }

    public static class RRC_crossCarrierScheduling_OtherSCS_2 extends AsnEnumerated {
        public static final RRC_crossCarrierScheduling_OtherSCS_2 SUPPORTED = new RRC_crossCarrierScheduling_OtherSCS_2(0);
    
        private RRC_crossCarrierScheduling_OtherSCS_2(long value) {
            super(value);
        }
    }

    public static class RRC_scalingFactor_2 extends AsnEnumerated {
        public static final RRC_scalingFactor_2 F0P4 = new RRC_scalingFactor_2(0);
        public static final RRC_scalingFactor_2 F0P75 = new RRC_scalingFactor_2(1);
        public static final RRC_scalingFactor_2 F0P8 = new RRC_scalingFactor_2(2);
    
        private RRC_scalingFactor_2(long value) {
            super(value);
        }
    }

    public static class RRC_dummy2_1 extends AsnEnumerated {
        public static final RRC_dummy2_1 SUPPORTED = new RRC_dummy2_1(0);
    
        private RRC_dummy2_1(long value) {
            super(value);
        }
    }

    // SIZE(1..16)
    public static class RRC_dummy6_1 extends AsnSequenceOf<RRC_DummyD> {
    }

    public static class RRC_pdsch_ProcessingType1_DifferentTB_PerSlot extends AsnSequence {
        public RRC_scs_15kHz_1 scs_15kHz; // optional
        public RRC_scs_30kHz_1 scs_30kHz; // optional
        public RRC_scs_60kHz_8 scs_60kHz; // optional
        public RRC_scs_120kHz_1 scs_120kHz; // optional
    
        public static class RRC_scs_120kHz_1 extends AsnEnumerated {
            public static final RRC_scs_120kHz_1 UPTO2 = new RRC_scs_120kHz_1(0);
            public static final RRC_scs_120kHz_1 UPTO4 = new RRC_scs_120kHz_1(1);
            public static final RRC_scs_120kHz_1 UPTO7 = new RRC_scs_120kHz_1(2);
        
            private RRC_scs_120kHz_1(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_30kHz_1 extends AsnEnumerated {
            public static final RRC_scs_30kHz_1 UPTO2 = new RRC_scs_30kHz_1(0);
            public static final RRC_scs_30kHz_1 UPTO4 = new RRC_scs_30kHz_1(1);
            public static final RRC_scs_30kHz_1 UPTO7 = new RRC_scs_30kHz_1(2);
        
            private RRC_scs_30kHz_1(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_15kHz_1 extends AsnEnumerated {
            public static final RRC_scs_15kHz_1 UPTO2 = new RRC_scs_15kHz_1(0);
            public static final RRC_scs_15kHz_1 UPTO4 = new RRC_scs_15kHz_1(1);
            public static final RRC_scs_15kHz_1 UPTO7 = new RRC_scs_15kHz_1(2);
        
            private RRC_scs_15kHz_1(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_60kHz_8 extends AsnEnumerated {
            public static final RRC_scs_60kHz_8 UPTO2 = new RRC_scs_60kHz_8(0);
            public static final RRC_scs_60kHz_8 UPTO4 = new RRC_scs_60kHz_8(1);
            public static final RRC_scs_60kHz_8 UPTO7 = new RRC_scs_60kHz_8(2);
        
            private RRC_scs_60kHz_8(long value) {
                super(value);
            }
        }
    }

    public static class RRC_timeDurationForQCL extends AsnSequence {
        public RRC_scs_60kHz_1 scs_60kHz; // optional
        public RRC_scs_120kHz_2 scs_120kHz; // optional
    
        public static class RRC_scs_120kHz_2 extends AsnEnumerated {
            public static final RRC_scs_120kHz_2 S14 = new RRC_scs_120kHz_2(0);
            public static final RRC_scs_120kHz_2 S28 = new RRC_scs_120kHz_2(1);
        
            private RRC_scs_120kHz_2(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_60kHz_1 extends AsnEnumerated {
            public static final RRC_scs_60kHz_1 S7 = new RRC_scs_60kHz_1(0);
            public static final RRC_scs_60kHz_1 S14 = new RRC_scs_60kHz_1(1);
            public static final RRC_scs_60kHz_1 S28 = new RRC_scs_60kHz_1(2);
        
            private RRC_scs_60kHz_1(long value) {
                super(value);
            }
        }
    }

    // SIZE(1..16)
    public static class RRC_dummy5 extends AsnSequenceOf<RRC_DummyC> {
    }

    // SIZE(1..32)
    public static class RRC_featureSetListPerDownlinkCC extends AsnSequenceOf<RRC_FeatureSetDownlinkPerCC_Id> {
    }

    // SIZE(1..16)
    public static class RRC_dummy4 extends AsnSequenceOf<RRC_DummyB> {
    }

    public static class RRC_dummy1_2 extends AsnEnumerated {
        public static final RRC_dummy1_2 SUPPORTED = new RRC_dummy1_2(0);
    
        private RRC_dummy1_2(long value) {
            super(value);
        }
    }

    public static class RRC_pdcch_MonitoringAnyOccasions extends AsnEnumerated {
        public static final RRC_pdcch_MonitoringAnyOccasions WITHOUTDCI_GAP = new RRC_pdcch_MonitoringAnyOccasions(0);
        public static final RRC_pdcch_MonitoringAnyOccasions WITHDCI_GAP = new RRC_pdcch_MonitoringAnyOccasions(1);
    
        private RRC_pdcch_MonitoringAnyOccasions(long value) {
            super(value);
        }
    }
}

