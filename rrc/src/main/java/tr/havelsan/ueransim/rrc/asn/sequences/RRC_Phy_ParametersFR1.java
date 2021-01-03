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

public class RRC_Phy_ParametersFR1 extends AsnSequence {
    public RRC_pdcch_MonitoringSingleOccasion pdcch_MonitoringSingleOccasion; // optional
    public RRC_scs_60kHz_4 scs_60kHz; // optional
    public RRC_pdsch_256QAM_FR1 pdsch_256QAM_FR1; // optional
    public RRC_pdsch_RE_MappingFR1_PerSymbol pdsch_RE_MappingFR1_PerSymbol; // optional
    public RRC_ext1_34 ext1; // optional

    public static class RRC_pdsch_RE_MappingFR1_PerSymbol extends AsnEnumerated {
        public static final RRC_pdsch_RE_MappingFR1_PerSymbol N10 = new RRC_pdsch_RE_MappingFR1_PerSymbol(0);
        public static final RRC_pdsch_RE_MappingFR1_PerSymbol N20 = new RRC_pdsch_RE_MappingFR1_PerSymbol(1);
    
        private RRC_pdsch_RE_MappingFR1_PerSymbol(long value) {
            super(value);
        }
    }

    public static class RRC_pdcch_MonitoringSingleOccasion extends AsnEnumerated {
        public static final RRC_pdcch_MonitoringSingleOccasion SUPPORTED = new RRC_pdcch_MonitoringSingleOccasion(0);
    
        private RRC_pdcch_MonitoringSingleOccasion(long value) {
            super(value);
        }
    }

    public static class RRC_pdsch_256QAM_FR1 extends AsnEnumerated {
        public static final RRC_pdsch_256QAM_FR1 SUPPORTED = new RRC_pdsch_256QAM_FR1(0);
    
        private RRC_pdsch_256QAM_FR1(long value) {
            super(value);
        }
    }

    public static class RRC_ext1_34 extends AsnSequence {
        public RRC_pdsch_RE_MappingFR1_PerSlot pdsch_RE_MappingFR1_PerSlot; // optional
    
        public static class RRC_pdsch_RE_MappingFR1_PerSlot extends AsnEnumerated {
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N16 = new RRC_pdsch_RE_MappingFR1_PerSlot(0);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N32 = new RRC_pdsch_RE_MappingFR1_PerSlot(1);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N48 = new RRC_pdsch_RE_MappingFR1_PerSlot(2);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N64 = new RRC_pdsch_RE_MappingFR1_PerSlot(3);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N80 = new RRC_pdsch_RE_MappingFR1_PerSlot(4);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N96 = new RRC_pdsch_RE_MappingFR1_PerSlot(5);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N112 = new RRC_pdsch_RE_MappingFR1_PerSlot(6);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N128 = new RRC_pdsch_RE_MappingFR1_PerSlot(7);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N144 = new RRC_pdsch_RE_MappingFR1_PerSlot(8);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N160 = new RRC_pdsch_RE_MappingFR1_PerSlot(9);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N176 = new RRC_pdsch_RE_MappingFR1_PerSlot(10);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N192 = new RRC_pdsch_RE_MappingFR1_PerSlot(11);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N208 = new RRC_pdsch_RE_MappingFR1_PerSlot(12);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N224 = new RRC_pdsch_RE_MappingFR1_PerSlot(13);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N240 = new RRC_pdsch_RE_MappingFR1_PerSlot(14);
            public static final RRC_pdsch_RE_MappingFR1_PerSlot N256 = new RRC_pdsch_RE_MappingFR1_PerSlot(15);
        
            private RRC_pdsch_RE_MappingFR1_PerSlot(long value) {
                super(value);
            }
        }
    }

    public static class RRC_scs_60kHz_4 extends AsnEnumerated {
        public static final RRC_scs_60kHz_4 SUPPORTED = new RRC_scs_60kHz_4(0);
    
        private RRC_scs_60kHz_4(long value) {
            super(value);
        }
    }
}

