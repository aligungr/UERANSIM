/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_DMRS_UplinkConfig;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PUSCH_TimeDomainResourceAllocationList;

public class RRC_PUSCH_Config extends AsnSequence {
    public AsnInteger dataScramblingIdentityPUSCH; // optional, VALUE(0..1023)
    public RRC_txConfig txConfig; // optional
    public RRC_SetupRelease_DMRS_UplinkConfig dmrs_UplinkForPUSCH_MappingTypeA; // optional
    public RRC_SetupRelease_DMRS_UplinkConfig dmrs_UplinkForPUSCH_MappingTypeB; // optional
    public RRC_PUSCH_PowerControl pusch_PowerControl; // optional
    public RRC_frequencyHopping_1 frequencyHopping; // optional
    public RRC_frequencyHoppingOffsetLists frequencyHoppingOffsetLists; // optional, SIZE(1..4)
    public RRC_resourceAllocation_2 resourceAllocation; // mandatory
    public RRC_SetupRelease_PUSCH_TimeDomainResourceAllocationList pusch_TimeDomainAllocationList; // optional
    public RRC_pusch_AggregationFactor pusch_AggregationFactor; // optional
    public RRC_mcs_Table_1 mcs_Table; // optional
    public RRC_mcs_TableTransformPrecoder_1 mcs_TableTransformPrecoder; // optional
    public RRC_transformPrecoder_1 transformPrecoder; // optional
    public RRC_codebookSubset codebookSubset; // optional
    public AsnInteger maxRank; // optional, VALUE(1..4)
    public RRC_rbg_Size_1 rbg_Size; // optional
    public RRC_SetupRelease_UCI_OnPUSCH uci_OnPUSCH; // optional
    public RRC_tp_pi2BPSK tp_pi2BPSK; // optional

    public static class RRC_rbg_Size_1 extends AsnEnumerated {
        public static final RRC_rbg_Size_1 CONFIG2 = new RRC_rbg_Size_1(0);
    
        private RRC_rbg_Size_1(long value) {
            super(value);
        }
    }

    public static class RRC_mcs_Table_1 extends AsnEnumerated {
        public static final RRC_mcs_Table_1 QAM256 = new RRC_mcs_Table_1(0);
        public static final RRC_mcs_Table_1 QAM64LOWSE = new RRC_mcs_Table_1(1);
    
        private RRC_mcs_Table_1(long value) {
            super(value);
        }
    }

    // SIZE(1..4)
    public static class RRC_frequencyHoppingOffsetLists extends AsnSequenceOf<AsnInteger> {
    }

    public static class RRC_codebookSubset extends AsnEnumerated {
        public static final RRC_codebookSubset FULLYANDPARTIALANDNONCOHERENT = new RRC_codebookSubset(0);
        public static final RRC_codebookSubset PARTIALANDNONCOHERENT = new RRC_codebookSubset(1);
        public static final RRC_codebookSubset NONCOHERENT = new RRC_codebookSubset(2);
    
        private RRC_codebookSubset(long value) {
            super(value);
        }
    }

    public static class RRC_transformPrecoder_1 extends AsnEnumerated {
        public static final RRC_transformPrecoder_1 ENABLED = new RRC_transformPrecoder_1(0);
        public static final RRC_transformPrecoder_1 DISABLED = new RRC_transformPrecoder_1(1);
    
        private RRC_transformPrecoder_1(long value) {
            super(value);
        }
    }

    public static class RRC_mcs_TableTransformPrecoder_1 extends AsnEnumerated {
        public static final RRC_mcs_TableTransformPrecoder_1 QAM256 = new RRC_mcs_TableTransformPrecoder_1(0);
        public static final RRC_mcs_TableTransformPrecoder_1 QAM64LOWSE = new RRC_mcs_TableTransformPrecoder_1(1);
    
        private RRC_mcs_TableTransformPrecoder_1(long value) {
            super(value);
        }
    }

    public static class RRC_pusch_AggregationFactor extends AsnEnumerated {
        public static final RRC_pusch_AggregationFactor N2 = new RRC_pusch_AggregationFactor(0);
        public static final RRC_pusch_AggregationFactor N4 = new RRC_pusch_AggregationFactor(1);
        public static final RRC_pusch_AggregationFactor N8 = new RRC_pusch_AggregationFactor(2);
    
        private RRC_pusch_AggregationFactor(long value) {
            super(value);
        }
    }

    public static class RRC_resourceAllocation_2 extends AsnEnumerated {
        public static final RRC_resourceAllocation_2 RESOURCEALLOCATIONTYPE0 = new RRC_resourceAllocation_2(0);
        public static final RRC_resourceAllocation_2 RESOURCEALLOCATIONTYPE1 = new RRC_resourceAllocation_2(1);
        public static final RRC_resourceAllocation_2 DYNAMICSWITCH = new RRC_resourceAllocation_2(2);
    
        private RRC_resourceAllocation_2(long value) {
            super(value);
        }
    }

    public static class RRC_SetupRelease_PUSCH_TimeDomainResourceAllocationList extends AsnChoice {
        public AsnNull release;
        public RRC_PUSCH_TimeDomainResourceAllocationList setup;
    }

    public static class RRC_txConfig extends AsnEnumerated {
        public static final RRC_txConfig CODEBOOK = new RRC_txConfig(0);
        public static final RRC_txConfig NONCODEBOOK = new RRC_txConfig(1);
    
        private RRC_txConfig(long value) {
            super(value);
        }
    }

    public static class RRC_frequencyHopping_1 extends AsnEnumerated {
        public static final RRC_frequencyHopping_1 INTRASLOT = new RRC_frequencyHopping_1(0);
        public static final RRC_frequencyHopping_1 INTERSLOT = new RRC_frequencyHopping_1(1);
    
        private RRC_frequencyHopping_1(long value) {
            super(value);
        }
    }

    public static class RRC_SetupRelease_UCI_OnPUSCH extends AsnChoice {
        public AsnNull release;
        public RRC_UCI_OnPUSCH setup;
    }

    public static class RRC_tp_pi2BPSK extends AsnEnumerated {
        public static final RRC_tp_pi2BPSK ENABLED = new RRC_tp_pi2BPSK(0);
    
        private RRC_tp_pi2BPSK(long value) {
            super(value);
        }
    }
}

