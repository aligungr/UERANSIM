/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;

public class RRC_CFRA extends AsnSequence {
    public RRC_occasions occasions; // optional
    public RRC_resources resources; // mandatory
    public RRC_ext1_49 ext1; // optional

    public static class RRC_resources extends AsnChoice {
        public RRC_ssb ssb;
        public RRC_csirs csirs;
    
        public static class RRC_csirs extends AsnSequence {
            public RRC_csirs_ResourceList csirs_ResourceList; // mandatory, SIZE(1..96)
            public RRC_RSRP_Range rsrp_ThresholdCSI_RS; // mandatory
        
            // SIZE(1..96)
            public static class RRC_csirs_ResourceList extends AsnSequenceOf<RRC_CFRA_CSIRS_Resource> {
            }
        }
    
        public static class RRC_ssb extends AsnSequence {
            public RRC_ssb_ResourceList ssb_ResourceList; // mandatory, SIZE(1..64)
            public AsnInteger ra_ssb_OccasionMaskIndex; // mandatory, VALUE(0..15)
        
            // SIZE(1..64)
            public static class RRC_ssb_ResourceList extends AsnSequenceOf<RRC_CFRA_SSB_Resource> {
            }
        }
    }

    public static class RRC_occasions extends AsnSequence {
        public RRC_RACH_ConfigGeneric rach_ConfigGeneric; // mandatory
        public RRC_ssb_perRACH_Occasion_2 ssb_perRACH_Occasion; // optional
    
        public static class RRC_ssb_perRACH_Occasion_2 extends AsnEnumerated {
            public static final RRC_ssb_perRACH_Occasion_2 ONEEIGHTH = new RRC_ssb_perRACH_Occasion_2(0);
            public static final RRC_ssb_perRACH_Occasion_2 ONEFOURTH = new RRC_ssb_perRACH_Occasion_2(1);
            public static final RRC_ssb_perRACH_Occasion_2 ONEHALF = new RRC_ssb_perRACH_Occasion_2(2);
            public static final RRC_ssb_perRACH_Occasion_2 ONE = new RRC_ssb_perRACH_Occasion_2(3);
            public static final RRC_ssb_perRACH_Occasion_2 TWO = new RRC_ssb_perRACH_Occasion_2(4);
            public static final RRC_ssb_perRACH_Occasion_2 FOUR = new RRC_ssb_perRACH_Occasion_2(5);
            public static final RRC_ssb_perRACH_Occasion_2 EIGHT = new RRC_ssb_perRACH_Occasion_2(6);
            public static final RRC_ssb_perRACH_Occasion_2 SIXTEEN = new RRC_ssb_perRACH_Occasion_2(7);
        
            private RRC_ssb_perRACH_Occasion_2(long value) {
                super(value);
            }
        }
    }

    public static class RRC_ext1_49 extends AsnSequence {
        public AsnInteger totalNumberOfRA_Preambles_v1530; // optional, VALUE(1..63)
    }
}

