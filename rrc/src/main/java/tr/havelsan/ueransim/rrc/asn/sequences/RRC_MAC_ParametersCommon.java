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

public class RRC_MAC_ParametersCommon extends AsnSequence {
    public RRC_lcp_Restriction lcp_Restriction; // optional
    public RRC_dummy_2 dummy; // optional
    public RRC_lch_ToSCellRestriction lch_ToSCellRestriction; // optional
    public RRC_ext1_10 ext1; // optional

    public static class RRC_ext1_10 extends AsnSequence {
        public RRC_recommendedBitRate recommendedBitRate; // optional
        public RRC_recommendedBitRateQuery recommendedBitRateQuery; // optional
    
        public static class RRC_recommendedBitRate extends AsnEnumerated {
            public static final RRC_recommendedBitRate SUPPORTED = new RRC_recommendedBitRate(0);
        
            private RRC_recommendedBitRate(long value) {
                super(value);
            }
        }
    
        public static class RRC_recommendedBitRateQuery extends AsnEnumerated {
            public static final RRC_recommendedBitRateQuery SUPPORTED = new RRC_recommendedBitRateQuery(0);
        
            private RRC_recommendedBitRateQuery(long value) {
                super(value);
            }
        }
    }

    public static class RRC_lch_ToSCellRestriction extends AsnEnumerated {
        public static final RRC_lch_ToSCellRestriction SUPPORTED = new RRC_lch_ToSCellRestriction(0);
    
        private RRC_lch_ToSCellRestriction(long value) {
            super(value);
        }
    }

    public static class RRC_lcp_Restriction extends AsnEnumerated {
        public static final RRC_lcp_Restriction SUPPORTED = new RRC_lcp_Restriction(0);
    
        private RRC_lcp_Restriction(long value) {
            super(value);
        }
    }

    public static class RRC_dummy_2 extends AsnEnumerated {
        public static final RRC_dummy_2 SUPPORTED = new RRC_dummy_2(0);
    
        private RRC_dummy_2(long value) {
            super(value);
        }
    }
}

