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
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;

public class RRC_RACH_ConfigCommon extends AsnSequence {
    public RRC_RACH_ConfigGeneric rach_ConfigGeneric; // mandatory
    public AsnInteger totalNumberOfRA_Preambles; // optional, VALUE(1..63)
    public RRC_ssb_perRACH_OccasionAndCB_PreamblesPerSSB ssb_perRACH_OccasionAndCB_PreamblesPerSSB; // optional
    public RRC_groupBconfigured groupBconfigured; // optional
    public RRC_ra_ContentionResolutionTimer ra_ContentionResolutionTimer; // mandatory
    public RRC_RSRP_Range rsrp_ThresholdSSB; // optional
    public RRC_RSRP_Range rsrp_ThresholdSSB_SUL; // optional
    public RRC_prach_RootSequenceIndex prach_RootSequenceIndex; // mandatory
    public RRC_SubcarrierSpacing msg1_SubcarrierSpacing; // optional
    public RRC_restrictedSetConfig restrictedSetConfig; // mandatory
    public RRC_msg3_transformPrecoder msg3_transformPrecoder; // optional

    public static class RRC_msg3_transformPrecoder extends AsnEnumerated {
        public static final RRC_msg3_transformPrecoder ENABLED = new RRC_msg3_transformPrecoder(0);
    
        private RRC_msg3_transformPrecoder(long value) {
            super(value);
        }
    }

    public static class RRC_prach_RootSequenceIndex extends AsnChoice {
        public AsnInteger l839; // VALUE(0..837)
        public AsnInteger l139; // VALUE(0..137)
    }

    public static class RRC_groupBconfigured extends AsnSequence {
        public RRC_ra_Msg3SizeGroupA ra_Msg3SizeGroupA; // mandatory
        public RRC_messagePowerOffsetGroupB messagePowerOffsetGroupB; // mandatory
        public AsnInteger numberOfRA_PreamblesGroupA; // mandatory, VALUE(1..64)
    
        public static class RRC_messagePowerOffsetGroupB extends AsnEnumerated {
            public static final RRC_messagePowerOffsetGroupB MINUSINFINITY = new RRC_messagePowerOffsetGroupB(0);
            public static final RRC_messagePowerOffsetGroupB DB0 = new RRC_messagePowerOffsetGroupB(1);
            public static final RRC_messagePowerOffsetGroupB DB5 = new RRC_messagePowerOffsetGroupB(2);
            public static final RRC_messagePowerOffsetGroupB DB8 = new RRC_messagePowerOffsetGroupB(3);
            public static final RRC_messagePowerOffsetGroupB DB10 = new RRC_messagePowerOffsetGroupB(4);
            public static final RRC_messagePowerOffsetGroupB DB12 = new RRC_messagePowerOffsetGroupB(5);
            public static final RRC_messagePowerOffsetGroupB DB15 = new RRC_messagePowerOffsetGroupB(6);
            public static final RRC_messagePowerOffsetGroupB DB18 = new RRC_messagePowerOffsetGroupB(7);
        
            private RRC_messagePowerOffsetGroupB(long value) {
                super(value);
            }
        }
    
        public static class RRC_ra_Msg3SizeGroupA extends AsnEnumerated {
            public static final RRC_ra_Msg3SizeGroupA B56 = new RRC_ra_Msg3SizeGroupA(0);
            public static final RRC_ra_Msg3SizeGroupA B144 = new RRC_ra_Msg3SizeGroupA(1);
            public static final RRC_ra_Msg3SizeGroupA B208 = new RRC_ra_Msg3SizeGroupA(2);
            public static final RRC_ra_Msg3SizeGroupA B256 = new RRC_ra_Msg3SizeGroupA(3);
            public static final RRC_ra_Msg3SizeGroupA B282 = new RRC_ra_Msg3SizeGroupA(4);
            public static final RRC_ra_Msg3SizeGroupA B480 = new RRC_ra_Msg3SizeGroupA(5);
            public static final RRC_ra_Msg3SizeGroupA B640 = new RRC_ra_Msg3SizeGroupA(6);
            public static final RRC_ra_Msg3SizeGroupA B800 = new RRC_ra_Msg3SizeGroupA(7);
            public static final RRC_ra_Msg3SizeGroupA B1000 = new RRC_ra_Msg3SizeGroupA(8);
            public static final RRC_ra_Msg3SizeGroupA B72 = new RRC_ra_Msg3SizeGroupA(9);
            public static final RRC_ra_Msg3SizeGroupA SPARE6 = new RRC_ra_Msg3SizeGroupA(10);
            public static final RRC_ra_Msg3SizeGroupA SPARE5 = new RRC_ra_Msg3SizeGroupA(11);
            public static final RRC_ra_Msg3SizeGroupA SPARE4 = new RRC_ra_Msg3SizeGroupA(12);
            public static final RRC_ra_Msg3SizeGroupA SPARE3 = new RRC_ra_Msg3SizeGroupA(13);
            public static final RRC_ra_Msg3SizeGroupA SPARE2 = new RRC_ra_Msg3SizeGroupA(14);
            public static final RRC_ra_Msg3SizeGroupA SPARE1 = new RRC_ra_Msg3SizeGroupA(15);
        
            private RRC_ra_Msg3SizeGroupA(long value) {
                super(value);
            }
        }
    }

    public static class RRC_restrictedSetConfig extends AsnEnumerated {
        public static final RRC_restrictedSetConfig UNRESTRICTEDSET = new RRC_restrictedSetConfig(0);
        public static final RRC_restrictedSetConfig RESTRICTEDSETTYPEA = new RRC_restrictedSetConfig(1);
        public static final RRC_restrictedSetConfig RESTRICTEDSETTYPEB = new RRC_restrictedSetConfig(2);
    
        private RRC_restrictedSetConfig(long value) {
            super(value);
        }
    }

    public static class RRC_ra_ContentionResolutionTimer extends AsnEnumerated {
        public static final RRC_ra_ContentionResolutionTimer SF8 = new RRC_ra_ContentionResolutionTimer(0);
        public static final RRC_ra_ContentionResolutionTimer SF16 = new RRC_ra_ContentionResolutionTimer(1);
        public static final RRC_ra_ContentionResolutionTimer SF24 = new RRC_ra_ContentionResolutionTimer(2);
        public static final RRC_ra_ContentionResolutionTimer SF32 = new RRC_ra_ContentionResolutionTimer(3);
        public static final RRC_ra_ContentionResolutionTimer SF40 = new RRC_ra_ContentionResolutionTimer(4);
        public static final RRC_ra_ContentionResolutionTimer SF48 = new RRC_ra_ContentionResolutionTimer(5);
        public static final RRC_ra_ContentionResolutionTimer SF56 = new RRC_ra_ContentionResolutionTimer(6);
        public static final RRC_ra_ContentionResolutionTimer SF64 = new RRC_ra_ContentionResolutionTimer(7);
    
        private RRC_ra_ContentionResolutionTimer(long value) {
            super(value);
        }
    }

    public static class RRC_ssb_perRACH_OccasionAndCB_PreamblesPerSSB extends AsnChoice {
        public RRC_oneEighth oneEighth;
        public RRC_oneFourth oneFourth;
        public RRC_oneHalf oneHalf;
        public RRC_one one;
        public RRC_two_1 two;
        public AsnInteger four; // VALUE(1..16)
        public AsnInteger eight; // VALUE(1..8)
        public AsnInteger sixteen; // VALUE(1..4)
    
        public static class RRC_oneFourth extends AsnEnumerated {
            public static final RRC_oneFourth N4 = new RRC_oneFourth(0);
            public static final RRC_oneFourth N8 = new RRC_oneFourth(1);
            public static final RRC_oneFourth N12 = new RRC_oneFourth(2);
            public static final RRC_oneFourth N16 = new RRC_oneFourth(3);
            public static final RRC_oneFourth N20 = new RRC_oneFourth(4);
            public static final RRC_oneFourth N24 = new RRC_oneFourth(5);
            public static final RRC_oneFourth N28 = new RRC_oneFourth(6);
            public static final RRC_oneFourth N32 = new RRC_oneFourth(7);
            public static final RRC_oneFourth N36 = new RRC_oneFourth(8);
            public static final RRC_oneFourth N40 = new RRC_oneFourth(9);
            public static final RRC_oneFourth N44 = new RRC_oneFourth(10);
            public static final RRC_oneFourth N48 = new RRC_oneFourth(11);
            public static final RRC_oneFourth N52 = new RRC_oneFourth(12);
            public static final RRC_oneFourth N56 = new RRC_oneFourth(13);
            public static final RRC_oneFourth N60 = new RRC_oneFourth(14);
            public static final RRC_oneFourth N64 = new RRC_oneFourth(15);
        
            private RRC_oneFourth(long value) {
                super(value);
            }
        }
    
        public static class RRC_oneHalf extends AsnEnumerated {
            public static final RRC_oneHalf N4 = new RRC_oneHalf(0);
            public static final RRC_oneHalf N8 = new RRC_oneHalf(1);
            public static final RRC_oneHalf N12 = new RRC_oneHalf(2);
            public static final RRC_oneHalf N16 = new RRC_oneHalf(3);
            public static final RRC_oneHalf N20 = new RRC_oneHalf(4);
            public static final RRC_oneHalf N24 = new RRC_oneHalf(5);
            public static final RRC_oneHalf N28 = new RRC_oneHalf(6);
            public static final RRC_oneHalf N32 = new RRC_oneHalf(7);
            public static final RRC_oneHalf N36 = new RRC_oneHalf(8);
            public static final RRC_oneHalf N40 = new RRC_oneHalf(9);
            public static final RRC_oneHalf N44 = new RRC_oneHalf(10);
            public static final RRC_oneHalf N48 = new RRC_oneHalf(11);
            public static final RRC_oneHalf N52 = new RRC_oneHalf(12);
            public static final RRC_oneHalf N56 = new RRC_oneHalf(13);
            public static final RRC_oneHalf N60 = new RRC_oneHalf(14);
            public static final RRC_oneHalf N64 = new RRC_oneHalf(15);
        
            private RRC_oneHalf(long value) {
                super(value);
            }
        }
    
        public static class RRC_oneEighth extends AsnEnumerated {
            public static final RRC_oneEighth N4 = new RRC_oneEighth(0);
            public static final RRC_oneEighth N8 = new RRC_oneEighth(1);
            public static final RRC_oneEighth N12 = new RRC_oneEighth(2);
            public static final RRC_oneEighth N16 = new RRC_oneEighth(3);
            public static final RRC_oneEighth N20 = new RRC_oneEighth(4);
            public static final RRC_oneEighth N24 = new RRC_oneEighth(5);
            public static final RRC_oneEighth N28 = new RRC_oneEighth(6);
            public static final RRC_oneEighth N32 = new RRC_oneEighth(7);
            public static final RRC_oneEighth N36 = new RRC_oneEighth(8);
            public static final RRC_oneEighth N40 = new RRC_oneEighth(9);
            public static final RRC_oneEighth N44 = new RRC_oneEighth(10);
            public static final RRC_oneEighth N48 = new RRC_oneEighth(11);
            public static final RRC_oneEighth N52 = new RRC_oneEighth(12);
            public static final RRC_oneEighth N56 = new RRC_oneEighth(13);
            public static final RRC_oneEighth N60 = new RRC_oneEighth(14);
            public static final RRC_oneEighth N64 = new RRC_oneEighth(15);
        
            private RRC_oneEighth(long value) {
                super(value);
            }
        }
    
        public static class RRC_two_1 extends AsnEnumerated {
            public static final RRC_two_1 N4 = new RRC_two_1(0);
            public static final RRC_two_1 N8 = new RRC_two_1(1);
            public static final RRC_two_1 N12 = new RRC_two_1(2);
            public static final RRC_two_1 N16 = new RRC_two_1(3);
            public static final RRC_two_1 N20 = new RRC_two_1(4);
            public static final RRC_two_1 N24 = new RRC_two_1(5);
            public static final RRC_two_1 N28 = new RRC_two_1(6);
            public static final RRC_two_1 N32 = new RRC_two_1(7);
        
            private RRC_two_1(long value) {
                super(value);
            }
        }
    
        public static class RRC_one extends AsnEnumerated {
            public static final RRC_one N4 = new RRC_one(0);
            public static final RRC_one N8 = new RRC_one(1);
            public static final RRC_one N12 = new RRC_one(2);
            public static final RRC_one N16 = new RRC_one(3);
            public static final RRC_one N20 = new RRC_one(4);
            public static final RRC_one N24 = new RRC_one(5);
            public static final RRC_one N28 = new RRC_one(6);
            public static final RRC_one N32 = new RRC_one(7);
            public static final RRC_one N36 = new RRC_one(8);
            public static final RRC_one N40 = new RRC_one(9);
            public static final RRC_one N44 = new RRC_one(10);
            public static final RRC_one N48 = new RRC_one(11);
            public static final RRC_one N52 = new RRC_one(12);
            public static final RRC_one N56 = new RRC_one(13);
            public static final RRC_one N60 = new RRC_one(14);
            public static final RRC_one N64 = new RRC_one(15);
        
            private RRC_one(long value) {
                super(value);
            }
        }
    }
}

