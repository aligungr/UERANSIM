/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PDCCH_BlindDetection;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P_Max;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RNTI_Value;

public class RRC_PhysicalCellGroupConfig extends AsnSequence {
    public RRC_harq_ACK_SpatialBundlingPUCCH harq_ACK_SpatialBundlingPUCCH; // optional
    public RRC_harq_ACK_SpatialBundlingPUSCH harq_ACK_SpatialBundlingPUSCH; // optional
    public RRC_P_Max p_NR_FR1; // optional
    public RRC_pdsch_HARQ_ACK_Codebook pdsch_HARQ_ACK_Codebook; // mandatory
    public RRC_RNTI_Value tpc_SRS_RNTI; // optional
    public RRC_RNTI_Value tpc_PUCCH_RNTI; // optional
    public RRC_RNTI_Value tpc_PUSCH_RNTI; // optional
    public RRC_RNTI_Value sp_CSI_RNTI; // optional
    public RRC_SetupRelease_RNTI_Value cs_RNTI; // optional
    public RRC_ext1_44 ext1; // optional
    public RRC_ext2_7 ext2; // optional
    public RRC_ext3_7 ext3; // optional

    public static class RRC_SetupRelease_RNTI_Value extends AsnChoice {
        public AsnNull release;
        public RRC_RNTI_Value setup;
    }

    public static class RRC_harq_ACK_SpatialBundlingPUSCH extends AsnEnumerated {
        public static final RRC_harq_ACK_SpatialBundlingPUSCH TRUE = new RRC_harq_ACK_SpatialBundlingPUSCH(0);
    
        private RRC_harq_ACK_SpatialBundlingPUSCH(long value) {
            super(value);
        }
    }

    public static class RRC_ext2_7 extends AsnSequence {
        public RRC_xScale xScale; // optional
    
        public static class RRC_xScale extends AsnEnumerated {
            public static final RRC_xScale DB0 = new RRC_xScale(0);
            public static final RRC_xScale DB6 = new RRC_xScale(1);
            public static final RRC_xScale SPARE2 = new RRC_xScale(2);
            public static final RRC_xScale SPARE1 = new RRC_xScale(3);
        
            private RRC_xScale(long value) {
                super(value);
            }
        }
    }

    public static class RRC_ext1_44 extends AsnSequence {
        public RRC_RNTI_Value mcs_C_RNTI; // optional
        public RRC_P_Max p_UE_FR1; // optional
    }

    public static class RRC_harq_ACK_SpatialBundlingPUCCH extends AsnEnumerated {
        public static final RRC_harq_ACK_SpatialBundlingPUCCH TRUE = new RRC_harq_ACK_SpatialBundlingPUCCH(0);
    
        private RRC_harq_ACK_SpatialBundlingPUCCH(long value) {
            super(value);
        }
    }

    public static class RRC_pdsch_HARQ_ACK_Codebook extends AsnEnumerated {
        public static final RRC_pdsch_HARQ_ACK_Codebook SEMISTATIC = new RRC_pdsch_HARQ_ACK_Codebook(0);
        public static final RRC_pdsch_HARQ_ACK_Codebook DYNAMIC = new RRC_pdsch_HARQ_ACK_Codebook(1);
    
        private RRC_pdsch_HARQ_ACK_Codebook(long value) {
            super(value);
        }
    }

    public static class RRC_ext3_7 extends AsnSequence {
        public RRC_SetupRelease_PDCCH_BlindDetection pdcch_BlindDetection; // optional
    
        public static class RRC_SetupRelease_PDCCH_BlindDetection extends AsnChoice {
            public AsnNull release;
            public RRC_PDCCH_BlindDetection setup;
        }
    }
}

