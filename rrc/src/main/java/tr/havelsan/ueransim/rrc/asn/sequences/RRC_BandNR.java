/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorNR;

public class RRC_BandNR extends AsnSequence {
    public RRC_FreqBandIndicatorNR bandNR; // mandatory
    public AsnBitString modifiedMPR_Behaviour; // optional, SIZE(8)
    public RRC_MIMO_ParametersPerBand mimo_ParametersPerBand; // optional
    public RRC_extendedCP extendedCP; // optional
    public RRC_multipleTCI multipleTCI; // optional
    public RRC_bwp_WithoutRestriction bwp_WithoutRestriction; // optional
    public RRC_bwp_SameNumerology bwp_SameNumerology; // optional
    public RRC_bwp_DiffNumerology bwp_DiffNumerology; // optional
    public RRC_crossCarrierScheduling_SameSCS crossCarrierScheduling_SameSCS; // optional
    public RRC_pdsch_256QAM_FR2 pdsch_256QAM_FR2; // optional
    public RRC_pusch_256QAM pusch_256QAM; // optional
    public RRC_ue_PowerClass ue_PowerClass; // optional
    public RRC_rateMatchingLTE_CRS rateMatchingLTE_CRS; // optional
    public RRC_channelBWs_DL_v1530 channelBWs_DL_v1530; // optional
    public RRC_channelBWs_UL_v1530 channelBWs_UL_v1530; // optional
    public RRC_ext1_42 ext1; // optional
    public RRC_ext2_9 ext2; // optional
    public RRC_ext3_4 ext3; // optional

    public static class RRC_pdsch_256QAM_FR2 extends AsnEnumerated {
        public static final RRC_pdsch_256QAM_FR2 SUPPORTED = new RRC_pdsch_256QAM_FR2(0);
    
        private RRC_pdsch_256QAM_FR2(long value) {
            super(value);
        }
    }

    public static class RRC_ue_PowerClass extends AsnEnumerated {
        public static final RRC_ue_PowerClass PC1 = new RRC_ue_PowerClass(0);
        public static final RRC_ue_PowerClass PC2 = new RRC_ue_PowerClass(1);
        public static final RRC_ue_PowerClass PC3 = new RRC_ue_PowerClass(2);
        public static final RRC_ue_PowerClass PC4 = new RRC_ue_PowerClass(3);
    
        private RRC_ue_PowerClass(long value) {
            super(value);
        }
    }

    public static class RRC_multipleTCI extends AsnEnumerated {
        public static final RRC_multipleTCI SUPPORTED = new RRC_multipleTCI(0);
    
        private RRC_multipleTCI(long value) {
            super(value);
        }
    }

    public static class RRC_rateMatchingLTE_CRS extends AsnEnumerated {
        public static final RRC_rateMatchingLTE_CRS SUPPORTED = new RRC_rateMatchingLTE_CRS(0);
    
        private RRC_rateMatchingLTE_CRS(long value) {
            super(value);
        }
    }

    public static class RRC_pusch_256QAM extends AsnEnumerated {
        public static final RRC_pusch_256QAM SUPPORTED = new RRC_pusch_256QAM(0);
    
        private RRC_pusch_256QAM(long value) {
            super(value);
        }
    }

    public static class RRC_extendedCP extends AsnEnumerated {
        public static final RRC_extendedCP SUPPORTED = new RRC_extendedCP(0);
    
        private RRC_extendedCP(long value) {
            super(value);
        }
    }

    public static class RRC_ext1_42 extends AsnSequence {
        public RRC_maxUplinkDutyCycle_PC2_FR1 maxUplinkDutyCycle_PC2_FR1; // optional
    
        public static class RRC_maxUplinkDutyCycle_PC2_FR1 extends AsnEnumerated {
            public static final RRC_maxUplinkDutyCycle_PC2_FR1 N60 = new RRC_maxUplinkDutyCycle_PC2_FR1(0);
            public static final RRC_maxUplinkDutyCycle_PC2_FR1 N70 = new RRC_maxUplinkDutyCycle_PC2_FR1(1);
            public static final RRC_maxUplinkDutyCycle_PC2_FR1 N80 = new RRC_maxUplinkDutyCycle_PC2_FR1(2);
            public static final RRC_maxUplinkDutyCycle_PC2_FR1 N90 = new RRC_maxUplinkDutyCycle_PC2_FR1(3);
            public static final RRC_maxUplinkDutyCycle_PC2_FR1 N100 = new RRC_maxUplinkDutyCycle_PC2_FR1(4);
        
            private RRC_maxUplinkDutyCycle_PC2_FR1(long value) {
                super(value);
            }
        }
    }

    public static class RRC_ext3_4 extends AsnSequence {
        public RRC_maxUplinkDutyCycle_FR2 maxUplinkDutyCycle_FR2; // optional
    
        public static class RRC_maxUplinkDutyCycle_FR2 extends AsnEnumerated {
            public static final RRC_maxUplinkDutyCycle_FR2 N15 = new RRC_maxUplinkDutyCycle_FR2(0);
            public static final RRC_maxUplinkDutyCycle_FR2 N20 = new RRC_maxUplinkDutyCycle_FR2(1);
            public static final RRC_maxUplinkDutyCycle_FR2 N25 = new RRC_maxUplinkDutyCycle_FR2(2);
            public static final RRC_maxUplinkDutyCycle_FR2 N30 = new RRC_maxUplinkDutyCycle_FR2(3);
            public static final RRC_maxUplinkDutyCycle_FR2 N40 = new RRC_maxUplinkDutyCycle_FR2(4);
            public static final RRC_maxUplinkDutyCycle_FR2 N50 = new RRC_maxUplinkDutyCycle_FR2(5);
            public static final RRC_maxUplinkDutyCycle_FR2 N60 = new RRC_maxUplinkDutyCycle_FR2(6);
            public static final RRC_maxUplinkDutyCycle_FR2 N70 = new RRC_maxUplinkDutyCycle_FR2(7);
            public static final RRC_maxUplinkDutyCycle_FR2 N80 = new RRC_maxUplinkDutyCycle_FR2(8);
            public static final RRC_maxUplinkDutyCycle_FR2 N90 = new RRC_maxUplinkDutyCycle_FR2(9);
            public static final RRC_maxUplinkDutyCycle_FR2 N100 = new RRC_maxUplinkDutyCycle_FR2(10);
        
            private RRC_maxUplinkDutyCycle_FR2(long value) {
                super(value);
            }
        }
    }

    public static class RRC_channelBWs_UL_v1530 extends AsnChoice {
        public RRC_fr1_3 fr1;
        public RRC_fr2_3 fr2;
    
        public static class RRC_fr1_3 extends AsnSequence {
            public AsnBitString scs_15kHz; // optional, SIZE(10)
            public AsnBitString scs_30kHz; // optional, SIZE(10)
            public AsnBitString scs_60kHz; // optional, SIZE(10)
        }
    
        public static class RRC_fr2_3 extends AsnSequence {
            public AsnBitString scs_60kHz; // optional, SIZE(3)
            public AsnBitString scs_120kHz; // optional, SIZE(3)
        }
    }

    public static class RRC_bwp_DiffNumerology extends AsnEnumerated {
        public static final RRC_bwp_DiffNumerology UPTO4 = new RRC_bwp_DiffNumerology(0);
    
        private RRC_bwp_DiffNumerology(long value) {
            super(value);
        }
    }

    public static class RRC_crossCarrierScheduling_SameSCS extends AsnEnumerated {
        public static final RRC_crossCarrierScheduling_SameSCS SUPPORTED = new RRC_crossCarrierScheduling_SameSCS(0);
    
        private RRC_crossCarrierScheduling_SameSCS(long value) {
            super(value);
        }
    }

    public static class RRC_channelBWs_DL_v1530 extends AsnChoice {
        public RRC_fr1_1 fr1;
        public RRC_fr2_2 fr2;
    
        public static class RRC_fr1_1 extends AsnSequence {
            public AsnBitString scs_15kHz; // optional, SIZE(10)
            public AsnBitString scs_30kHz; // optional, SIZE(10)
            public AsnBitString scs_60kHz; // optional, SIZE(10)
        }
    
        public static class RRC_fr2_2 extends AsnSequence {
            public AsnBitString scs_60kHz; // optional, SIZE(3)
            public AsnBitString scs_120kHz; // optional, SIZE(3)
        }
    }

    public static class RRC_ext2_9 extends AsnSequence {
        public RRC_pucch_SpatialRelInfoMAC_CE pucch_SpatialRelInfoMAC_CE; // optional
        public RRC_powerBoosting_pi2BPSK powerBoosting_pi2BPSK; // optional
    
        public static class RRC_pucch_SpatialRelInfoMAC_CE extends AsnEnumerated {
            public static final RRC_pucch_SpatialRelInfoMAC_CE SUPPORTED = new RRC_pucch_SpatialRelInfoMAC_CE(0);
        
            private RRC_pucch_SpatialRelInfoMAC_CE(long value) {
                super(value);
            }
        }
    
        public static class RRC_powerBoosting_pi2BPSK extends AsnEnumerated {
            public static final RRC_powerBoosting_pi2BPSK SUPPORTED = new RRC_powerBoosting_pi2BPSK(0);
        
            private RRC_powerBoosting_pi2BPSK(long value) {
                super(value);
            }
        }
    }

    public static class RRC_bwp_WithoutRestriction extends AsnEnumerated {
        public static final RRC_bwp_WithoutRestriction SUPPORTED = new RRC_bwp_WithoutRestriction(0);
    
        private RRC_bwp_WithoutRestriction(long value) {
            super(value);
        }
    }

    public static class RRC_bwp_SameNumerology extends AsnEnumerated {
        public static final RRC_bwp_SameNumerology UPTO2 = new RRC_bwp_SameNumerology(0);
        public static final RRC_bwp_SameNumerology UPTO4 = new RRC_bwp_SameNumerology(1);
    
        private RRC_bwp_SameNumerology(long value) {
            super(value);
        }
    }
}

