/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_PagingCycle;

public class RRC_PCCH_Config extends AsnSequence {
    public RRC_PagingCycle defaultPagingCycle; // mandatory
    public RRC_nAndPagingFrameOffset nAndPagingFrameOffset; // mandatory
    public RRC_ns ns; // mandatory
    public RRC_firstPDCCH_MonitoringOccasionOfPO_2 firstPDCCH_MonitoringOccasionOfPO; // optional

    public static class RRC_firstPDCCH_MonitoringOccasionOfPO_2 extends AsnChoice {
        public RRC_sCS15KHZoneT_1 sCS15KHZoneT; // SIZE(1..4)
        public RRC_sCS30KHZoneT_SCS15KHZhalfT_1 sCS30KHZoneT_SCS15KHZhalfT; // SIZE(1..4)
        public RRC_sCS60KHZoneT_SCS30KHZhalfT_SCS15KHZquarterT_2 sCS60KHZoneT_SCS30KHZhalfT_SCS15KHZquarterT; // SIZE(1..4)
        public RRC_sCS120KHZoneT_SCS60KHZhalfT_SCS30KHZquarterT_SCS15KHZoneEighthT_1 sCS120KHZoneT_SCS60KHZhalfT_SCS30KHZquarterT_SCS15KHZoneEighthT; // SIZE(1..4)
        public RRC_sCS120KHZhalfT_SCS60KHZquarterT_SCS30KHZoneEighthT_SCS15KHZoneSixteenthT_1 sCS120KHZhalfT_SCS60KHZquarterT_SCS30KHZoneEighthT_SCS15KHZoneSixteenthT; // SIZE(1..4)
        public RRC_sCS120KHZquarterT_SCS60KHZoneEighthT_SCS30KHZoneSixteenthT_1 sCS120KHZquarterT_SCS60KHZoneEighthT_SCS30KHZoneSixteenthT; // SIZE(1..4)
        public RRC_sCS120KHZoneEighthT_SCS60KHZoneSixteenthT_1 sCS120KHZoneEighthT_SCS60KHZoneSixteenthT; // SIZE(1..4)
        public RRC_sCS120KHZoneSixteenthT_1 sCS120KHZoneSixteenthT; // SIZE(1..4)
    
        // SIZE(1..4)
        public static class RRC_sCS120KHZoneT_SCS60KHZhalfT_SCS30KHZquarterT_SCS15KHZoneEighthT_1 extends AsnSequenceOf<AsnInteger> {
        }
    
        // SIZE(1..4)
        public static class RRC_sCS120KHZhalfT_SCS60KHZquarterT_SCS30KHZoneEighthT_SCS15KHZoneSixteenthT_1 extends AsnSequenceOf<AsnInteger> {
        }
    
        // SIZE(1..4)
        public static class RRC_sCS120KHZquarterT_SCS60KHZoneEighthT_SCS30KHZoneSixteenthT_1 extends AsnSequenceOf<AsnInteger> {
        }
    
        // SIZE(1..4)
        public static class RRC_sCS120KHZoneSixteenthT_1 extends AsnSequenceOf<AsnInteger> {
        }
    
        // SIZE(1..4)
        public static class RRC_sCS15KHZoneT_1 extends AsnSequenceOf<AsnInteger> {
        }
    
        // SIZE(1..4)
        public static class RRC_sCS60KHZoneT_SCS30KHZhalfT_SCS15KHZquarterT_2 extends AsnSequenceOf<AsnInteger> {
        }
    
        // SIZE(1..4)
        public static class RRC_sCS30KHZoneT_SCS15KHZhalfT_1 extends AsnSequenceOf<AsnInteger> {
        }
    
        // SIZE(1..4)
        public static class RRC_sCS120KHZoneEighthT_SCS60KHZoneSixteenthT_1 extends AsnSequenceOf<AsnInteger> {
        }
    }

    public static class RRC_ns extends AsnEnumerated {
        public static final RRC_ns FOUR = new RRC_ns(0);
        public static final RRC_ns TWO = new RRC_ns(1);
        public static final RRC_ns ONE = new RRC_ns(2);
    
        private RRC_ns(long value) {
            super(value);
        }
    }

    public static class RRC_nAndPagingFrameOffset extends AsnChoice {
        public AsnNull oneT;
        public AsnInteger halfT; // VALUE(0..1)
        public AsnInteger quarterT; // VALUE(0..3)
        public AsnInteger oneEighthT; // VALUE(0..7)
        public AsnInteger oneSixteenthT; // VALUE(0..15)
    }
}

