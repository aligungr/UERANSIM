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

public class RRC_MRDC_Parameters extends AsnSequence {
    public RRC_singleUL_Transmission singleUL_Transmission; // optional
    public RRC_dynamicPowerSharing dynamicPowerSharing; // optional
    public RRC_tdm_Pattern tdm_Pattern; // optional
    public RRC_ul_SharingEUTRA_NR ul_SharingEUTRA_NR; // optional
    public RRC_ul_SwitchingTimeEUTRA_NR ul_SwitchingTimeEUTRA_NR; // optional
    public RRC_simultaneousRxTxInterBandENDC simultaneousRxTxInterBandENDC; // optional
    public RRC_asyncIntraBandENDC asyncIntraBandENDC; // optional
    public RRC_ext1_37 ext1; // optional

    public static class RRC_ul_SwitchingTimeEUTRA_NR extends AsnEnumerated {
        public static final RRC_ul_SwitchingTimeEUTRA_NR TYPE1 = new RRC_ul_SwitchingTimeEUTRA_NR(0);
        public static final RRC_ul_SwitchingTimeEUTRA_NR TYPE2 = new RRC_ul_SwitchingTimeEUTRA_NR(1);
    
        private RRC_ul_SwitchingTimeEUTRA_NR(long value) {
            super(value);
        }
    }

    public static class RRC_ext1_37 extends AsnSequence {
        public RRC_dualPA_Architecture_1 dualPA_Architecture; // optional
        public RRC_intraBandENDC_Support_v1540 intraBandENDC_Support_v1540; // optional
        public RRC_ul_TimingAlignmentEUTRA_NR ul_TimingAlignmentEUTRA_NR; // optional
    
        public static class RRC_dualPA_Architecture_1 extends AsnEnumerated {
            public static final RRC_dualPA_Architecture_1 SUPPORTED = new RRC_dualPA_Architecture_1(0);
        
            private RRC_dualPA_Architecture_1(long value) {
                super(value);
            }
        }
    
        public static class RRC_ul_TimingAlignmentEUTRA_NR extends AsnEnumerated {
            public static final RRC_ul_TimingAlignmentEUTRA_NR REQUIRED = new RRC_ul_TimingAlignmentEUTRA_NR(0);
        
            private RRC_ul_TimingAlignmentEUTRA_NR(long value) {
                super(value);
            }
        }
    
        public static class RRC_intraBandENDC_Support_v1540 extends AsnEnumerated {
            public static final RRC_intraBandENDC_Support_v1540 NON_CONTIGUOUS = new RRC_intraBandENDC_Support_v1540(0);
            public static final RRC_intraBandENDC_Support_v1540 BOTH = new RRC_intraBandENDC_Support_v1540(1);
        
            private RRC_intraBandENDC_Support_v1540(long value) {
                super(value);
            }
        }
    }

    public static class RRC_simultaneousRxTxInterBandENDC extends AsnEnumerated {
        public static final RRC_simultaneousRxTxInterBandENDC SUPPORTED = new RRC_simultaneousRxTxInterBandENDC(0);
    
        private RRC_simultaneousRxTxInterBandENDC(long value) {
            super(value);
        }
    }

    public static class RRC_ul_SharingEUTRA_NR extends AsnEnumerated {
        public static final RRC_ul_SharingEUTRA_NR TDM = new RRC_ul_SharingEUTRA_NR(0);
        public static final RRC_ul_SharingEUTRA_NR FDM = new RRC_ul_SharingEUTRA_NR(1);
        public static final RRC_ul_SharingEUTRA_NR BOTH = new RRC_ul_SharingEUTRA_NR(2);
    
        private RRC_ul_SharingEUTRA_NR(long value) {
            super(value);
        }
    }

    public static class RRC_tdm_Pattern extends AsnEnumerated {
        public static final RRC_tdm_Pattern SUPPORTED = new RRC_tdm_Pattern(0);
    
        private RRC_tdm_Pattern(long value) {
            super(value);
        }
    }

    public static class RRC_dynamicPowerSharing extends AsnEnumerated {
        public static final RRC_dynamicPowerSharing SUPPORTED = new RRC_dynamicPowerSharing(0);
    
        private RRC_dynamicPowerSharing(long value) {
            super(value);
        }
    }

    public static class RRC_singleUL_Transmission extends AsnEnumerated {
        public static final RRC_singleUL_Transmission SUPPORTED = new RRC_singleUL_Transmission(0);
    
        private RRC_singleUL_Transmission(long value) {
            super(value);
        }
    }

    public static class RRC_asyncIntraBandENDC extends AsnEnumerated {
        public static final RRC_asyncIntraBandENDC SUPPORTED = new RRC_asyncIntraBandENDC(0);
    
        private RRC_asyncIntraBandENDC(long value) {
            super(value);
        }
    }
}

