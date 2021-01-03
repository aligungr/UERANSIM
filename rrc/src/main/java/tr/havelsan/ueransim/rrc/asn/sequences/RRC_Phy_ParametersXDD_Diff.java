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

public class RRC_Phy_ParametersXDD_Diff extends AsnSequence {
    public RRC_dynamicSFI_2 dynamicSFI; // optional
    public RRC_twoPUCCH_F0_2_ConsecSymbols_2 twoPUCCH_F0_2_ConsecSymbols; // optional
    public RRC_twoDifferentTPC_Loop_PUSCH_2 twoDifferentTPC_Loop_PUSCH; // optional
    public RRC_twoDifferentTPC_Loop_PUCCH_1 twoDifferentTPC_Loop_PUCCH; // optional
    public RRC_ext1_15 ext1; // optional

    public static class RRC_twoDifferentTPC_Loop_PUCCH_1 extends AsnEnumerated {
        public static final RRC_twoDifferentTPC_Loop_PUCCH_1 SUPPORTED = new RRC_twoDifferentTPC_Loop_PUCCH_1(0);
    
        private RRC_twoDifferentTPC_Loop_PUCCH_1(long value) {
            super(value);
        }
    }

    public static class RRC_ext1_15 extends AsnSequence {
        public RRC_dl_SchedulingOffset_PDSCH_TypeA_2 dl_SchedulingOffset_PDSCH_TypeA; // optional
        public RRC_dl_SchedulingOffset_PDSCH_TypeB_1 dl_SchedulingOffset_PDSCH_TypeB; // optional
        public RRC_ul_SchedulingOffset_1 ul_SchedulingOffset; // optional
    
        public static class RRC_ul_SchedulingOffset_1 extends AsnEnumerated {
            public static final RRC_ul_SchedulingOffset_1 SUPPORTED = new RRC_ul_SchedulingOffset_1(0);
        
            private RRC_ul_SchedulingOffset_1(long value) {
                super(value);
            }
        }
    
        public static class RRC_dl_SchedulingOffset_PDSCH_TypeB_1 extends AsnEnumerated {
            public static final RRC_dl_SchedulingOffset_PDSCH_TypeB_1 SUPPORTED = new RRC_dl_SchedulingOffset_PDSCH_TypeB_1(0);
        
            private RRC_dl_SchedulingOffset_PDSCH_TypeB_1(long value) {
                super(value);
            }
        }
    
        public static class RRC_dl_SchedulingOffset_PDSCH_TypeA_2 extends AsnEnumerated {
            public static final RRC_dl_SchedulingOffset_PDSCH_TypeA_2 SUPPORTED = new RRC_dl_SchedulingOffset_PDSCH_TypeA_2(0);
        
            private RRC_dl_SchedulingOffset_PDSCH_TypeA_2(long value) {
                super(value);
            }
        }
    }

    public static class RRC_twoPUCCH_F0_2_ConsecSymbols_2 extends AsnEnumerated {
        public static final RRC_twoPUCCH_F0_2_ConsecSymbols_2 SUPPORTED = new RRC_twoPUCCH_F0_2_ConsecSymbols_2(0);
    
        private RRC_twoPUCCH_F0_2_ConsecSymbols_2(long value) {
            super(value);
        }
    }

    public static class RRC_dynamicSFI_2 extends AsnEnumerated {
        public static final RRC_dynamicSFI_2 SUPPORTED = new RRC_dynamicSFI_2(0);
    
        private RRC_dynamicSFI_2(long value) {
            super(value);
        }
    }

    public static class RRC_twoDifferentTPC_Loop_PUSCH_2 extends AsnEnumerated {
        public static final RRC_twoDifferentTPC_Loop_PUSCH_2 SUPPORTED = new RRC_twoDifferentTPC_Loop_PUSCH_2(0);
    
        private RRC_twoDifferentTPC_Loop_PUSCH_2(long value) {
            super(value);
        }
    }
}

