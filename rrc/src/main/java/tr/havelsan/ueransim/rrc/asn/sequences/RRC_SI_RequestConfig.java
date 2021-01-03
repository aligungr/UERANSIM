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
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_SI_RequestConfig extends AsnSequence {
    public RRC_rach_OccasionsSI rach_OccasionsSI; // optional
    public RRC_si_RequestPeriod si_RequestPeriod; // optional
    public RRC_si_RequestResources si_RequestResources; // mandatory, SIZE(1..32)

    public static class RRC_rach_OccasionsSI extends AsnSequence {
        public RRC_RACH_ConfigGeneric rach_ConfigSI; // mandatory
        public RRC_ssb_perRACH_Occasion_1 ssb_perRACH_Occasion; // mandatory
    
        public static class RRC_ssb_perRACH_Occasion_1 extends AsnEnumerated {
            public static final RRC_ssb_perRACH_Occasion_1 ONEEIGHTH = new RRC_ssb_perRACH_Occasion_1(0);
            public static final RRC_ssb_perRACH_Occasion_1 ONEFOURTH = new RRC_ssb_perRACH_Occasion_1(1);
            public static final RRC_ssb_perRACH_Occasion_1 ONEHALF = new RRC_ssb_perRACH_Occasion_1(2);
            public static final RRC_ssb_perRACH_Occasion_1 ONE = new RRC_ssb_perRACH_Occasion_1(3);
            public static final RRC_ssb_perRACH_Occasion_1 TWO = new RRC_ssb_perRACH_Occasion_1(4);
            public static final RRC_ssb_perRACH_Occasion_1 FOUR = new RRC_ssb_perRACH_Occasion_1(5);
            public static final RRC_ssb_perRACH_Occasion_1 EIGHT = new RRC_ssb_perRACH_Occasion_1(6);
            public static final RRC_ssb_perRACH_Occasion_1 SIXTEEN = new RRC_ssb_perRACH_Occasion_1(7);
        
            private RRC_ssb_perRACH_Occasion_1(long value) {
                super(value);
            }
        }
    }

    public static class RRC_si_RequestPeriod extends AsnEnumerated {
        public static final RRC_si_RequestPeriod ONE = new RRC_si_RequestPeriod(0);
        public static final RRC_si_RequestPeriod TWO = new RRC_si_RequestPeriod(1);
        public static final RRC_si_RequestPeriod FOUR = new RRC_si_RequestPeriod(2);
        public static final RRC_si_RequestPeriod SIX = new RRC_si_RequestPeriod(3);
        public static final RRC_si_RequestPeriod EIGHT = new RRC_si_RequestPeriod(4);
        public static final RRC_si_RequestPeriod TEN = new RRC_si_RequestPeriod(5);
        public static final RRC_si_RequestPeriod TWELVE = new RRC_si_RequestPeriod(6);
        public static final RRC_si_RequestPeriod SIXTEEN = new RRC_si_RequestPeriod(7);
    
        private RRC_si_RequestPeriod(long value) {
            super(value);
        }
    }

    // SIZE(1..32)
    public static class RRC_si_RequestResources extends AsnSequenceOf<RRC_SI_RequestResources> {
    }
}

