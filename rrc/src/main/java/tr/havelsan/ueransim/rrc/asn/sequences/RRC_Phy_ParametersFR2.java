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

public class RRC_Phy_ParametersFR2 extends AsnSequence {
    public RRC_dummy_5 dummy; // optional
    public RRC_pdsch_RE_MappingFR2_PerSymbol pdsch_RE_MappingFR2_PerSymbol; // optional
    public RRC_ext1_5 ext1; // optional

    public static class RRC_ext1_5 extends AsnSequence {
        public RRC_pCell_FR2 pCell_FR2; // optional
        public RRC_pdsch_RE_MappingFR2_PerSlot pdsch_RE_MappingFR2_PerSlot; // optional
    
        public static class RRC_pdsch_RE_MappingFR2_PerSlot extends AsnEnumerated {
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N16 = new RRC_pdsch_RE_MappingFR2_PerSlot(0);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N32 = new RRC_pdsch_RE_MappingFR2_PerSlot(1);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N48 = new RRC_pdsch_RE_MappingFR2_PerSlot(2);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N64 = new RRC_pdsch_RE_MappingFR2_PerSlot(3);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N80 = new RRC_pdsch_RE_MappingFR2_PerSlot(4);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N96 = new RRC_pdsch_RE_MappingFR2_PerSlot(5);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N112 = new RRC_pdsch_RE_MappingFR2_PerSlot(6);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N128 = new RRC_pdsch_RE_MappingFR2_PerSlot(7);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N144 = new RRC_pdsch_RE_MappingFR2_PerSlot(8);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N160 = new RRC_pdsch_RE_MappingFR2_PerSlot(9);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N176 = new RRC_pdsch_RE_MappingFR2_PerSlot(10);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N192 = new RRC_pdsch_RE_MappingFR2_PerSlot(11);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N208 = new RRC_pdsch_RE_MappingFR2_PerSlot(12);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N224 = new RRC_pdsch_RE_MappingFR2_PerSlot(13);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N240 = new RRC_pdsch_RE_MappingFR2_PerSlot(14);
            public static final RRC_pdsch_RE_MappingFR2_PerSlot N256 = new RRC_pdsch_RE_MappingFR2_PerSlot(15);
        
            private RRC_pdsch_RE_MappingFR2_PerSlot(long value) {
                super(value);
            }
        }
    
        public static class RRC_pCell_FR2 extends AsnEnumerated {
            public static final RRC_pCell_FR2 SUPPORTED = new RRC_pCell_FR2(0);
        
            private RRC_pCell_FR2(long value) {
                super(value);
            }
        }
    }

    public static class RRC_dummy_5 extends AsnEnumerated {
        public static final RRC_dummy_5 SUPPORTED = new RRC_dummy_5(0);
    
        private RRC_dummy_5(long value) {
            super(value);
        }
    }

    public static class RRC_pdsch_RE_MappingFR2_PerSymbol extends AsnEnumerated {
        public static final RRC_pdsch_RE_MappingFR2_PerSymbol N6 = new RRC_pdsch_RE_MappingFR2_PerSymbol(0);
        public static final RRC_pdsch_RE_MappingFR2_PerSymbol N20 = new RRC_pdsch_RE_MappingFR2_PerSymbol(1);
    
        private RRC_pdsch_RE_MappingFR2_PerSymbol(long value) {
            super(value);
        }
    }
}

