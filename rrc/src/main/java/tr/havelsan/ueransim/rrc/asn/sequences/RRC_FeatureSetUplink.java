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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetUplinkPerCC_Id;

public class RRC_FeatureSetUplink extends AsnSequence {
    public RRC_featureSetListPerUplinkCC featureSetListPerUplinkCC; // mandatory, SIZE(1..32)
    public RRC_scalingFactor_1 scalingFactor; // optional
    public RRC_crossCarrierScheduling_OtherSCS_1 crossCarrierScheduling_OtherSCS; // optional
    public RRC_FreqSeparationClass intraBandFreqSeparationUL; // optional
    public RRC_searchSpaceSharingCA_UL searchSpaceSharingCA_UL; // optional
    public RRC_DummyI dummy1; // optional
    public RRC_SRS_Resources supportedSRS_Resources; // optional
    public RRC_twoPUCCH_Group twoPUCCH_Group; // optional
    public RRC_dynamicSwitchSUL dynamicSwitchSUL; // optional
    public RRC_simultaneousTxSUL_NonSUL simultaneousTxSUL_NonSUL; // optional
    public RRC_pusch_ProcessingType1_DifferentTB_PerSlot pusch_ProcessingType1_DifferentTB_PerSlot; // optional
    public RRC_DummyF dummy2; // optional

    public static class RRC_simultaneousTxSUL_NonSUL extends AsnEnumerated {
        public static final RRC_simultaneousTxSUL_NonSUL SUPPORTED = new RRC_simultaneousTxSUL_NonSUL(0);
    
        private RRC_simultaneousTxSUL_NonSUL(long value) {
            super(value);
        }
    }

    public static class RRC_dynamicSwitchSUL extends AsnEnumerated {
        public static final RRC_dynamicSwitchSUL SUPPORTED = new RRC_dynamicSwitchSUL(0);
    
        private RRC_dynamicSwitchSUL(long value) {
            super(value);
        }
    }

    public static class RRC_twoPUCCH_Group extends AsnEnumerated {
        public static final RRC_twoPUCCH_Group SUPPORTED = new RRC_twoPUCCH_Group(0);
    
        private RRC_twoPUCCH_Group(long value) {
            super(value);
        }
    }

    public static class RRC_searchSpaceSharingCA_UL extends AsnEnumerated {
        public static final RRC_searchSpaceSharingCA_UL SUPPORTED = new RRC_searchSpaceSharingCA_UL(0);
    
        private RRC_searchSpaceSharingCA_UL(long value) {
            super(value);
        }
    }

    public static class RRC_crossCarrierScheduling_OtherSCS_1 extends AsnEnumerated {
        public static final RRC_crossCarrierScheduling_OtherSCS_1 SUPPORTED = new RRC_crossCarrierScheduling_OtherSCS_1(0);
    
        private RRC_crossCarrierScheduling_OtherSCS_1(long value) {
            super(value);
        }
    }

    public static class RRC_scalingFactor_1 extends AsnEnumerated {
        public static final RRC_scalingFactor_1 F0P4 = new RRC_scalingFactor_1(0);
        public static final RRC_scalingFactor_1 F0P75 = new RRC_scalingFactor_1(1);
        public static final RRC_scalingFactor_1 F0P8 = new RRC_scalingFactor_1(2);
    
        private RRC_scalingFactor_1(long value) {
            super(value);
        }
    }

    public static class RRC_pusch_ProcessingType1_DifferentTB_PerSlot extends AsnSequence {
        public RRC_scs_15kHz_2 scs_15kHz; // optional
        public RRC_scs_30kHz_4 scs_30kHz; // optional
        public RRC_scs_60kHz_3 scs_60kHz; // optional
        public RRC_scs_120kHz_7 scs_120kHz; // optional
    
        public static class RRC_scs_15kHz_2 extends AsnEnumerated {
            public static final RRC_scs_15kHz_2 UPTO2 = new RRC_scs_15kHz_2(0);
            public static final RRC_scs_15kHz_2 UPTO4 = new RRC_scs_15kHz_2(1);
            public static final RRC_scs_15kHz_2 UPTO7 = new RRC_scs_15kHz_2(2);
        
            private RRC_scs_15kHz_2(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_120kHz_7 extends AsnEnumerated {
            public static final RRC_scs_120kHz_7 UPTO2 = new RRC_scs_120kHz_7(0);
            public static final RRC_scs_120kHz_7 UPTO4 = new RRC_scs_120kHz_7(1);
            public static final RRC_scs_120kHz_7 UPTO7 = new RRC_scs_120kHz_7(2);
        
            private RRC_scs_120kHz_7(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_60kHz_3 extends AsnEnumerated {
            public static final RRC_scs_60kHz_3 UPTO2 = new RRC_scs_60kHz_3(0);
            public static final RRC_scs_60kHz_3 UPTO4 = new RRC_scs_60kHz_3(1);
            public static final RRC_scs_60kHz_3 UPTO7 = new RRC_scs_60kHz_3(2);
        
            private RRC_scs_60kHz_3(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_30kHz_4 extends AsnEnumerated {
            public static final RRC_scs_30kHz_4 UPTO2 = new RRC_scs_30kHz_4(0);
            public static final RRC_scs_30kHz_4 UPTO4 = new RRC_scs_30kHz_4(1);
            public static final RRC_scs_30kHz_4 UPTO7 = new RRC_scs_30kHz_4(2);
        
            private RRC_scs_30kHz_4(long value) {
                super(value);
            }
        }
    }

    // SIZE(1..32)
    public static class RRC_featureSetListPerUplinkCC extends AsnSequenceOf<RRC_FeatureSetUplinkPerCC_Id> {
    }
}

