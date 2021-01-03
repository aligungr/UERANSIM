/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ControlResourceSetZero;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SearchSpaceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SearchSpaceZero;

public class RRC_PDCCH_ConfigCommon extends AsnSequence {
    public RRC_ControlResourceSetZero controlResourceSetZero; // optional
    public RRC_ControlResourceSet commonControlResourceSet; // optional
    public RRC_SearchSpaceZero searchSpaceZero; // optional
    public RRC_commonSearchSpaceList commonSearchSpaceList; // optional, SIZE(1..4)
    public RRC_SearchSpaceId searchSpaceSIB1; // optional
    public RRC_SearchSpaceId searchSpaceOtherSystemInformation; // optional
    public RRC_SearchSpaceId pagingSearchSpace; // optional
    public RRC_SearchSpaceId ra_SearchSpace; // optional
    public RRC_ext1_31 ext1; // optional

    public static class RRC_ext1_31 extends AsnSequence {
        public RRC_firstPDCCH_MonitoringOccasionOfPO_1 firstPDCCH_MonitoringOccasionOfPO; // optional
    
        public static class RRC_firstPDCCH_MonitoringOccasionOfPO_1 extends AsnChoice {
            public RRC_sCS15KHZoneT_2 sCS15KHZoneT; // SIZE(1..4)
            public RRC_sCS30KHZoneT_SCS15KHZhalfT_2 sCS30KHZoneT_SCS15KHZhalfT; // SIZE(1..4)
            public RRC_sCS60KHZoneT_SCS30KHZhalfT_SCS15KHZquarterT_1 sCS60KHZoneT_SCS30KHZhalfT_SCS15KHZquarterT; // SIZE(1..4)
            public RRC_sCS120KHZoneT_SCS60KHZhalfT_SCS30KHZquarterT_SCS15KHZoneEighthT_2 sCS120KHZoneT_SCS60KHZhalfT_SCS30KHZquarterT_SCS15KHZoneEighthT; // SIZE(1..4)
            public RRC_sCS120KHZhalfT_SCS60KHZquarterT_SCS30KHZoneEighthT_SCS15KHZoneSixteenthT_2 sCS120KHZhalfT_SCS60KHZquarterT_SCS30KHZoneEighthT_SCS15KHZoneSixteenthT; // SIZE(1..4)
            public RRC_sCS120KHZquarterT_SCS60KHZoneEighthT_SCS30KHZoneSixteenthT_2 sCS120KHZquarterT_SCS60KHZoneEighthT_SCS30KHZoneSixteenthT; // SIZE(1..4)
            public RRC_sCS120KHZoneEighthT_SCS60KHZoneSixteenthT_2 sCS120KHZoneEighthT_SCS60KHZoneSixteenthT; // SIZE(1..4)
            public RRC_sCS120KHZoneSixteenthT_2 sCS120KHZoneSixteenthT; // SIZE(1..4)
        
            // SIZE(1..4)
            public static class RRC_sCS120KHZoneT_SCS60KHZhalfT_SCS30KHZquarterT_SCS15KHZoneEighthT_2 extends AsnSequenceOf<AsnInteger> {
            }
        
            // SIZE(1..4)
            public static class RRC_sCS120KHZhalfT_SCS60KHZquarterT_SCS30KHZoneEighthT_SCS15KHZoneSixteenthT_2 extends AsnSequenceOf<AsnInteger> {
            }
        
            // SIZE(1..4)
            public static class RRC_sCS60KHZoneT_SCS30KHZhalfT_SCS15KHZquarterT_1 extends AsnSequenceOf<AsnInteger> {
            }
        
            // SIZE(1..4)
            public static class RRC_sCS120KHZoneSixteenthT_2 extends AsnSequenceOf<AsnInteger> {
            }
        
            // SIZE(1..4)
            public static class RRC_sCS15KHZoneT_2 extends AsnSequenceOf<AsnInteger> {
            }
        
            // SIZE(1..4)
            public static class RRC_sCS120KHZquarterT_SCS60KHZoneEighthT_SCS30KHZoneSixteenthT_2 extends AsnSequenceOf<AsnInteger> {
            }
        
            // SIZE(1..4)
            public static class RRC_sCS30KHZoneT_SCS15KHZhalfT_2 extends AsnSequenceOf<AsnInteger> {
            }
        
            // SIZE(1..4)
            public static class RRC_sCS120KHZoneEighthT_SCS60KHZoneSixteenthT_2 extends AsnSequenceOf<AsnInteger> {
            }
        }
    }

    // SIZE(1..4)
    public static class RRC_commonSearchSpaceList extends AsnSequenceOf<RRC_SearchSpace> {
    }
}

