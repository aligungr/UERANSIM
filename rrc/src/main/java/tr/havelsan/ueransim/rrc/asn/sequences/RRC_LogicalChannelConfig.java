/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SchedulingRequestId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_LogicalChannelConfig extends AsnSequence {
    public RRC_ul_SpecificParameters ul_SpecificParameters; // optional

    public static class RRC_ul_SpecificParameters extends AsnSequence {
        public AsnInteger priority; // mandatory, VALUE(1..16)
        public RRC_prioritisedBitRate prioritisedBitRate; // mandatory
        public RRC_bucketSizeDuration bucketSizeDuration; // mandatory
        public RRC_allowedServingCells allowedServingCells; // optional, SIZE(1..31)
        public RRC_allowedSCS_List allowedSCS_List; // optional, SIZE(1..5)
        public RRC_maxPUSCH_Duration maxPUSCH_Duration; // optional
        public RRC_configuredGrantType1Allowed configuredGrantType1Allowed; // optional
        public AsnInteger logicalChannelGroup; // optional, VALUE(0..7)
        public RRC_SchedulingRequestId schedulingRequestID; // optional
        public AsnBoolean logicalChannelSR_Mask; // mandatory
        public AsnBoolean logicalChannelSR_DelayTimerApplied; // mandatory
        public RRC_bitRateQueryProhibitTimer bitRateQueryProhibitTimer; // optional
    
        public static class RRC_bitRateQueryProhibitTimer extends AsnEnumerated {
            public static final RRC_bitRateQueryProhibitTimer S0 = new RRC_bitRateQueryProhibitTimer(0);
            public static final RRC_bitRateQueryProhibitTimer S0DOT4 = new RRC_bitRateQueryProhibitTimer(1);
            public static final RRC_bitRateQueryProhibitTimer S0DOT8 = new RRC_bitRateQueryProhibitTimer(2);
            public static final RRC_bitRateQueryProhibitTimer S1DOT6 = new RRC_bitRateQueryProhibitTimer(3);
            public static final RRC_bitRateQueryProhibitTimer S3 = new RRC_bitRateQueryProhibitTimer(4);
            public static final RRC_bitRateQueryProhibitTimer S6 = new RRC_bitRateQueryProhibitTimer(5);
            public static final RRC_bitRateQueryProhibitTimer S12 = new RRC_bitRateQueryProhibitTimer(6);
            public static final RRC_bitRateQueryProhibitTimer S30 = new RRC_bitRateQueryProhibitTimer(7);
        
            private RRC_bitRateQueryProhibitTimer(long value) {
                super(value);
            }
        }
    
        public static class RRC_prioritisedBitRate extends AsnEnumerated {
            public static final RRC_prioritisedBitRate KBPS0 = new RRC_prioritisedBitRate(0);
            public static final RRC_prioritisedBitRate KBPS8 = new RRC_prioritisedBitRate(1);
            public static final RRC_prioritisedBitRate KBPS16 = new RRC_prioritisedBitRate(2);
            public static final RRC_prioritisedBitRate KBPS32 = new RRC_prioritisedBitRate(3);
            public static final RRC_prioritisedBitRate KBPS64 = new RRC_prioritisedBitRate(4);
            public static final RRC_prioritisedBitRate KBPS128 = new RRC_prioritisedBitRate(5);
            public static final RRC_prioritisedBitRate KBPS256 = new RRC_prioritisedBitRate(6);
            public static final RRC_prioritisedBitRate KBPS512 = new RRC_prioritisedBitRate(7);
            public static final RRC_prioritisedBitRate KBPS1024 = new RRC_prioritisedBitRate(8);
            public static final RRC_prioritisedBitRate KBPS2048 = new RRC_prioritisedBitRate(9);
            public static final RRC_prioritisedBitRate KBPS4096 = new RRC_prioritisedBitRate(10);
            public static final RRC_prioritisedBitRate KBPS8192 = new RRC_prioritisedBitRate(11);
            public static final RRC_prioritisedBitRate KBPS16384 = new RRC_prioritisedBitRate(12);
            public static final RRC_prioritisedBitRate KBPS32768 = new RRC_prioritisedBitRate(13);
            public static final RRC_prioritisedBitRate KBPS65536 = new RRC_prioritisedBitRate(14);
            public static final RRC_prioritisedBitRate INFINITY = new RRC_prioritisedBitRate(15);
        
            private RRC_prioritisedBitRate(long value) {
                super(value);
            }
        }
    
        // SIZE(1..5)
        public static class RRC_allowedSCS_List extends AsnSequenceOf<RRC_SubcarrierSpacing> {
        }
    
        public static class RRC_bucketSizeDuration extends AsnEnumerated {
            public static final RRC_bucketSizeDuration MS5 = new RRC_bucketSizeDuration(0);
            public static final RRC_bucketSizeDuration MS10 = new RRC_bucketSizeDuration(1);
            public static final RRC_bucketSizeDuration MS20 = new RRC_bucketSizeDuration(2);
            public static final RRC_bucketSizeDuration MS50 = new RRC_bucketSizeDuration(3);
            public static final RRC_bucketSizeDuration MS100 = new RRC_bucketSizeDuration(4);
            public static final RRC_bucketSizeDuration MS150 = new RRC_bucketSizeDuration(5);
            public static final RRC_bucketSizeDuration MS300 = new RRC_bucketSizeDuration(6);
            public static final RRC_bucketSizeDuration MS500 = new RRC_bucketSizeDuration(7);
            public static final RRC_bucketSizeDuration MS1000 = new RRC_bucketSizeDuration(8);
            public static final RRC_bucketSizeDuration SPARE7 = new RRC_bucketSizeDuration(9);
            public static final RRC_bucketSizeDuration SPARE6 = new RRC_bucketSizeDuration(10);
            public static final RRC_bucketSizeDuration SPARE5 = new RRC_bucketSizeDuration(11);
            public static final RRC_bucketSizeDuration SPARE4 = new RRC_bucketSizeDuration(12);
            public static final RRC_bucketSizeDuration SPARE3 = new RRC_bucketSizeDuration(13);
            public static final RRC_bucketSizeDuration SPARE2 = new RRC_bucketSizeDuration(14);
            public static final RRC_bucketSizeDuration SPARE1 = new RRC_bucketSizeDuration(15);
        
            private RRC_bucketSizeDuration(long value) {
                super(value);
            }
        }
    
        // SIZE(1..31)
        public static class RRC_allowedServingCells extends AsnSequenceOf<RRC_ServCellIndex> {
        }
    
        public static class RRC_maxPUSCH_Duration extends AsnEnumerated {
            public static final RRC_maxPUSCH_Duration MS0P02 = new RRC_maxPUSCH_Duration(0);
            public static final RRC_maxPUSCH_Duration MS0P04 = new RRC_maxPUSCH_Duration(1);
            public static final RRC_maxPUSCH_Duration MS0P0625 = new RRC_maxPUSCH_Duration(2);
            public static final RRC_maxPUSCH_Duration MS0P125 = new RRC_maxPUSCH_Duration(3);
            public static final RRC_maxPUSCH_Duration MS0P25 = new RRC_maxPUSCH_Duration(4);
            public static final RRC_maxPUSCH_Duration MS0P5 = new RRC_maxPUSCH_Duration(5);
            public static final RRC_maxPUSCH_Duration SPARE2 = new RRC_maxPUSCH_Duration(6);
            public static final RRC_maxPUSCH_Duration SPARE1 = new RRC_maxPUSCH_Duration(7);
        
            private RRC_maxPUSCH_Duration(long value) {
                super(value);
            }
        }
    
        public static class RRC_configuredGrantType1Allowed extends AsnEnumerated {
            public static final RRC_configuredGrantType1Allowed TRUE = new RRC_configuredGrantType1Allowed(0);
        
            private RRC_configuredGrantType1Allowed(long value) {
                super(value);
            }
        }
    }
}

