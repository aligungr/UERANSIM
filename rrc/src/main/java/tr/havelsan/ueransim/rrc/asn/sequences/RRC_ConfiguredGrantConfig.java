/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_CG_UCI_OnPUSCH;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P0_PUSCH_AlphaSetId;

public class RRC_ConfiguredGrantConfig extends AsnSequence {
    public RRC_frequencyHopping_2 frequencyHopping; // optional
    public RRC_DMRS_UplinkConfig cg_DMRS_Configuration; // mandatory
    public RRC_mcs_Table_4 mcs_Table; // optional
    public RRC_mcs_TableTransformPrecoder_2 mcs_TableTransformPrecoder; // optional
    public RRC_SetupRelease_CG_UCI_OnPUSCH uci_OnPUSCH; // optional
    public RRC_resourceAllocation_1 resourceAllocation; // mandatory
    public RRC_rbg_Size_3 rbg_Size; // optional
    public RRC_powerControlLoopToUse powerControlLoopToUse; // mandatory
    public RRC_P0_PUSCH_AlphaSetId p0_PUSCH_Alpha; // mandatory
    public RRC_transformPrecoder_2 transformPrecoder; // optional
    public AsnInteger nrofHARQ_Processes; // mandatory, VALUE(1..16)
    public RRC_repK repK; // mandatory
    public RRC_repK_RV repK_RV; // optional
    public RRC_periodicity_3 periodicity; // mandatory
    public AsnInteger configuredGrantTimer; // optional, VALUE(1..64)
    public RRC_rrc_ConfiguredUplinkGrant rrc_ConfiguredUplinkGrant; // optional

    public static class RRC_resourceAllocation_1 extends AsnEnumerated {
        public static final RRC_resourceAllocation_1 RESOURCEALLOCATIONTYPE0 = new RRC_resourceAllocation_1(0);
        public static final RRC_resourceAllocation_1 RESOURCEALLOCATIONTYPE1 = new RRC_resourceAllocation_1(1);
        public static final RRC_resourceAllocation_1 DYNAMICSWITCH = new RRC_resourceAllocation_1(2);
    
        private RRC_resourceAllocation_1(long value) {
            super(value);
        }
    }

    public static class RRC_rbg_Size_3 extends AsnEnumerated {
        public static final RRC_rbg_Size_3 CONFIG2 = new RRC_rbg_Size_3(0);
    
        private RRC_rbg_Size_3(long value) {
            super(value);
        }
    }

    public static class RRC_frequencyHopping_2 extends AsnEnumerated {
        public static final RRC_frequencyHopping_2 INTRASLOT = new RRC_frequencyHopping_2(0);
        public static final RRC_frequencyHopping_2 INTERSLOT = new RRC_frequencyHopping_2(1);
    
        private RRC_frequencyHopping_2(long value) {
            super(value);
        }
    }

    public static class RRC_mcs_Table_4 extends AsnEnumerated {
        public static final RRC_mcs_Table_4 QAM256 = new RRC_mcs_Table_4(0);
        public static final RRC_mcs_Table_4 QAM64LOWSE = new RRC_mcs_Table_4(1);
    
        private RRC_mcs_Table_4(long value) {
            super(value);
        }
    }

    public static class RRC_mcs_TableTransformPrecoder_2 extends AsnEnumerated {
        public static final RRC_mcs_TableTransformPrecoder_2 QAM256 = new RRC_mcs_TableTransformPrecoder_2(0);
        public static final RRC_mcs_TableTransformPrecoder_2 QAM64LOWSE = new RRC_mcs_TableTransformPrecoder_2(1);
    
        private RRC_mcs_TableTransformPrecoder_2(long value) {
            super(value);
        }
    }

    public static class RRC_SetupRelease_CG_UCI_OnPUSCH extends AsnChoice {
        public AsnNull release;
        public RRC_CG_UCI_OnPUSCH setup;
    }

    public static class RRC_powerControlLoopToUse extends AsnEnumerated {
        public static final RRC_powerControlLoopToUse N0 = new RRC_powerControlLoopToUse(0);
        public static final RRC_powerControlLoopToUse N1 = new RRC_powerControlLoopToUse(1);
    
        private RRC_powerControlLoopToUse(long value) {
            super(value);
        }
    }

    public static class RRC_repK extends AsnEnumerated {
        public static final RRC_repK N1 = new RRC_repK(0);
        public static final RRC_repK N2 = new RRC_repK(1);
        public static final RRC_repK N4 = new RRC_repK(2);
        public static final RRC_repK N8 = new RRC_repK(3);
    
        private RRC_repK(long value) {
            super(value);
        }
    }

    public static class RRC_repK_RV extends AsnEnumerated {
        public static final RRC_repK_RV S1_0231 = new RRC_repK_RV(0);
        public static final RRC_repK_RV S2_0303 = new RRC_repK_RV(1);
        public static final RRC_repK_RV S3_0000 = new RRC_repK_RV(2);
    
        private RRC_repK_RV(long value) {
            super(value);
        }
    }

    public static class RRC_periodicity_3 extends AsnEnumerated {
        public static final RRC_periodicity_3 SYM2 = new RRC_periodicity_3(0);
        public static final RRC_periodicity_3 SYM7 = new RRC_periodicity_3(1);
        public static final RRC_periodicity_3 SYM1X14 = new RRC_periodicity_3(2);
        public static final RRC_periodicity_3 SYM2X14 = new RRC_periodicity_3(3);
        public static final RRC_periodicity_3 SYM4X14 = new RRC_periodicity_3(4);
        public static final RRC_periodicity_3 SYM5X14 = new RRC_periodicity_3(5);
        public static final RRC_periodicity_3 SYM8X14 = new RRC_periodicity_3(6);
        public static final RRC_periodicity_3 SYM10X14 = new RRC_periodicity_3(7);
        public static final RRC_periodicity_3 SYM16X14 = new RRC_periodicity_3(8);
        public static final RRC_periodicity_3 SYM20X14 = new RRC_periodicity_3(9);
        public static final RRC_periodicity_3 SYM32X14 = new RRC_periodicity_3(10);
        public static final RRC_periodicity_3 SYM40X14 = new RRC_periodicity_3(11);
        public static final RRC_periodicity_3 SYM64X14 = new RRC_periodicity_3(12);
        public static final RRC_periodicity_3 SYM80X14 = new RRC_periodicity_3(13);
        public static final RRC_periodicity_3 SYM128X14 = new RRC_periodicity_3(14);
        public static final RRC_periodicity_3 SYM160X14 = new RRC_periodicity_3(15);
        public static final RRC_periodicity_3 SYM256X14 = new RRC_periodicity_3(16);
        public static final RRC_periodicity_3 SYM320X14 = new RRC_periodicity_3(17);
        public static final RRC_periodicity_3 SYM512X14 = new RRC_periodicity_3(18);
        public static final RRC_periodicity_3 SYM640X14 = new RRC_periodicity_3(19);
        public static final RRC_periodicity_3 SYM1024X14 = new RRC_periodicity_3(20);
        public static final RRC_periodicity_3 SYM1280X14 = new RRC_periodicity_3(21);
        public static final RRC_periodicity_3 SYM2560X14 = new RRC_periodicity_3(22);
        public static final RRC_periodicity_3 SYM5120X14 = new RRC_periodicity_3(23);
        public static final RRC_periodicity_3 SYM6 = new RRC_periodicity_3(24);
        public static final RRC_periodicity_3 SYM1X12 = new RRC_periodicity_3(25);
        public static final RRC_periodicity_3 SYM2X12 = new RRC_periodicity_3(26);
        public static final RRC_periodicity_3 SYM4X12 = new RRC_periodicity_3(27);
        public static final RRC_periodicity_3 SYM5X12 = new RRC_periodicity_3(28);
        public static final RRC_periodicity_3 SYM8X12 = new RRC_periodicity_3(29);
        public static final RRC_periodicity_3 SYM10X12 = new RRC_periodicity_3(30);
        public static final RRC_periodicity_3 SYM16X12 = new RRC_periodicity_3(31);
        public static final RRC_periodicity_3 SYM20X12 = new RRC_periodicity_3(32);
        public static final RRC_periodicity_3 SYM32X12 = new RRC_periodicity_3(33);
        public static final RRC_periodicity_3 SYM40X12 = new RRC_periodicity_3(34);
        public static final RRC_periodicity_3 SYM64X12 = new RRC_periodicity_3(35);
        public static final RRC_periodicity_3 SYM80X12 = new RRC_periodicity_3(36);
        public static final RRC_periodicity_3 SYM128X12 = new RRC_periodicity_3(37);
        public static final RRC_periodicity_3 SYM160X12 = new RRC_periodicity_3(38);
        public static final RRC_periodicity_3 SYM256X12 = new RRC_periodicity_3(39);
        public static final RRC_periodicity_3 SYM320X12 = new RRC_periodicity_3(40);
        public static final RRC_periodicity_3 SYM512X12 = new RRC_periodicity_3(41);
        public static final RRC_periodicity_3 SYM640X12 = new RRC_periodicity_3(42);
        public static final RRC_periodicity_3 SYM1280X12 = new RRC_periodicity_3(43);
        public static final RRC_periodicity_3 SYM2560X12 = new RRC_periodicity_3(44);
    
        private RRC_periodicity_3(long value) {
            super(value);
        }
    }

    public static class RRC_rrc_ConfiguredUplinkGrant extends AsnSequence {
        public AsnInteger timeDomainOffset; // mandatory, VALUE(0..5119)
        public AsnInteger timeDomainAllocation; // mandatory, VALUE(0..15)
        public AsnBitString frequencyDomainAllocation; // mandatory, SIZE(18)
        public AsnInteger antennaPort; // mandatory, VALUE(0..31)
        public AsnInteger dmrs_SeqInitialization; // optional, VALUE(0..1)
        public AsnInteger precodingAndNumberOfLayers; // mandatory, VALUE(0..63)
        public AsnInteger srs_ResourceIndicator; // optional, VALUE(0..15)
        public AsnInteger mcsAndTBS; // mandatory, VALUE(0..31)
        public AsnInteger frequencyHoppingOffset; // optional, VALUE(1..274)
        public AsnInteger pathlossReferenceIndex; // mandatory, VALUE(0..3)
    }

    public static class RRC_transformPrecoder_2 extends AsnEnumerated {
        public static final RRC_transformPrecoder_2 ENABLED = new RRC_transformPrecoder_2(0);
        public static final RRC_transformPrecoder_2 DISABLED = new RRC_transformPrecoder_2(1);
    
        private RRC_transformPrecoder_2(long value) {
            super(value);
        }
    }
}

