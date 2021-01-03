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

public class RRC_FeatureSetDownlink_v1540 extends AsnSequence {
    public RRC_oneFL_DMRS_TwoAdditionalDMRS_DL oneFL_DMRS_TwoAdditionalDMRS_DL; // optional
    public RRC_additionalDMRS_DL_Alt additionalDMRS_DL_Alt; // optional
    public RRC_twoFL_DMRS_TwoAdditionalDMRS_DL twoFL_DMRS_TwoAdditionalDMRS_DL; // optional
    public RRC_oneFL_DMRS_ThreeAdditionalDMRS_DL oneFL_DMRS_ThreeAdditionalDMRS_DL; // optional
    public RRC_pdcch_MonitoringAnyOccasionsWithSpanGap pdcch_MonitoringAnyOccasionsWithSpanGap; // optional
    public RRC_pdsch_SeparationWithGap pdsch_SeparationWithGap; // optional
    public RRC_pdsch_ProcessingType2 pdsch_ProcessingType2; // optional
    public RRC_pdsch_ProcessingType2_Limited pdsch_ProcessingType2_Limited; // optional
    public RRC_dl_MCS_TableAlt_DynamicIndication dl_MCS_TableAlt_DynamicIndication; // optional

    public static class RRC_pdsch_ProcessingType2 extends AsnSequence {
        public RRC_ProcessingParameters scs_15kHz; // optional
        public RRC_ProcessingParameters scs_30kHz; // optional
        public RRC_ProcessingParameters scs_60kHz; // optional
    }

    public static class RRC_oneFL_DMRS_ThreeAdditionalDMRS_DL extends AsnEnumerated {
        public static final RRC_oneFL_DMRS_ThreeAdditionalDMRS_DL SUPPORTED = new RRC_oneFL_DMRS_ThreeAdditionalDMRS_DL(0);
    
        private RRC_oneFL_DMRS_ThreeAdditionalDMRS_DL(long value) {
            super(value);
        }
    }

    public static class RRC_additionalDMRS_DL_Alt extends AsnEnumerated {
        public static final RRC_additionalDMRS_DL_Alt SUPPORTED = new RRC_additionalDMRS_DL_Alt(0);
    
        private RRC_additionalDMRS_DL_Alt(long value) {
            super(value);
        }
    }

    public static class RRC_oneFL_DMRS_TwoAdditionalDMRS_DL extends AsnEnumerated {
        public static final RRC_oneFL_DMRS_TwoAdditionalDMRS_DL SUPPORTED = new RRC_oneFL_DMRS_TwoAdditionalDMRS_DL(0);
    
        private RRC_oneFL_DMRS_TwoAdditionalDMRS_DL(long value) {
            super(value);
        }
    }

    public static class RRC_dl_MCS_TableAlt_DynamicIndication extends AsnEnumerated {
        public static final RRC_dl_MCS_TableAlt_DynamicIndication SUPPORTED = new RRC_dl_MCS_TableAlt_DynamicIndication(0);
    
        private RRC_dl_MCS_TableAlt_DynamicIndication(long value) {
            super(value);
        }
    }

    public static class RRC_pdsch_ProcessingType2_Limited extends AsnSequence {
        public RRC_differentTB_PerSlot_SCS_30kHz differentTB_PerSlot_SCS_30kHz; // mandatory
    
        public static class RRC_differentTB_PerSlot_SCS_30kHz extends AsnEnumerated {
            public static final RRC_differentTB_PerSlot_SCS_30kHz UPTO1 = new RRC_differentTB_PerSlot_SCS_30kHz(0);
            public static final RRC_differentTB_PerSlot_SCS_30kHz UPTO2 = new RRC_differentTB_PerSlot_SCS_30kHz(1);
            public static final RRC_differentTB_PerSlot_SCS_30kHz UPTO4 = new RRC_differentTB_PerSlot_SCS_30kHz(2);
            public static final RRC_differentTB_PerSlot_SCS_30kHz UPTO7 = new RRC_differentTB_PerSlot_SCS_30kHz(3);
        
            private RRC_differentTB_PerSlot_SCS_30kHz(long value) {
                super(value);
            }
        }
    }

    public static class RRC_twoFL_DMRS_TwoAdditionalDMRS_DL extends AsnEnumerated {
        public static final RRC_twoFL_DMRS_TwoAdditionalDMRS_DL SUPPORTED = new RRC_twoFL_DMRS_TwoAdditionalDMRS_DL(0);
    
        private RRC_twoFL_DMRS_TwoAdditionalDMRS_DL(long value) {
            super(value);
        }
    }

    public static class RRC_pdcch_MonitoringAnyOccasionsWithSpanGap extends AsnSequence {
        public RRC_scs_15kHz_5 scs_15kHz; // optional
        public RRC_scs_30kHz_5 scs_30kHz; // optional
        public RRC_scs_60kHz_7 scs_60kHz; // optional
        public RRC_scs_120kHz_5 scs_120kHz; // optional
    
        public static class RRC_scs_120kHz_5 extends AsnEnumerated {
            public static final RRC_scs_120kHz_5 SET1 = new RRC_scs_120kHz_5(0);
            public static final RRC_scs_120kHz_5 SET2 = new RRC_scs_120kHz_5(1);
            public static final RRC_scs_120kHz_5 SET3 = new RRC_scs_120kHz_5(2);
        
            private RRC_scs_120kHz_5(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_15kHz_5 extends AsnEnumerated {
            public static final RRC_scs_15kHz_5 SET1 = new RRC_scs_15kHz_5(0);
            public static final RRC_scs_15kHz_5 SET2 = new RRC_scs_15kHz_5(1);
            public static final RRC_scs_15kHz_5 SET3 = new RRC_scs_15kHz_5(2);
        
            private RRC_scs_15kHz_5(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_60kHz_7 extends AsnEnumerated {
            public static final RRC_scs_60kHz_7 SET1 = new RRC_scs_60kHz_7(0);
            public static final RRC_scs_60kHz_7 SET2 = new RRC_scs_60kHz_7(1);
            public static final RRC_scs_60kHz_7 SET3 = new RRC_scs_60kHz_7(2);
        
            private RRC_scs_60kHz_7(long value) {
                super(value);
            }
        }
    
        public static class RRC_scs_30kHz_5 extends AsnEnumerated {
            public static final RRC_scs_30kHz_5 SET1 = new RRC_scs_30kHz_5(0);
            public static final RRC_scs_30kHz_5 SET2 = new RRC_scs_30kHz_5(1);
            public static final RRC_scs_30kHz_5 SET3 = new RRC_scs_30kHz_5(2);
        
            private RRC_scs_30kHz_5(long value) {
                super(value);
            }
        }
    }

    public static class RRC_pdsch_SeparationWithGap extends AsnEnumerated {
        public static final RRC_pdsch_SeparationWithGap SUPPORTED = new RRC_pdsch_SeparationWithGap(0);
    
        private RRC_pdsch_SeparationWithGap(long value) {
            super(value);
        }
    }
}

