/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_FeatureSets extends AsnSequence {
    public RRC_featureSetsDownlink featureSetsDownlink; // optional, SIZE(1..1024)
    public RRC_featureSetsDownlinkPerCC featureSetsDownlinkPerCC; // optional, SIZE(1..1024)
    public RRC_featureSetsUplink featureSetsUplink; // optional, SIZE(1..1024)
    public RRC_featureSetsUplinkPerCC featureSetsUplinkPerCC; // optional, SIZE(1..1024)
    public RRC_ext1_13 ext1; // optional

    public static class RRC_ext1_13 extends AsnSequence {
        public RRC_featureSetsDownlink_v1540 featureSetsDownlink_v1540; // optional, SIZE(1..1024)
        public RRC_featureSetsUplink_v1540 featureSetsUplink_v1540; // optional, SIZE(1..1024)
        public RRC_featureSetsUplinkPerCC_v1540 featureSetsUplinkPerCC_v1540; // optional, SIZE(1..1024)
    
        // SIZE(1..1024)
        public static class RRC_featureSetsUplinkPerCC_v1540 extends AsnSequenceOf<RRC_FeatureSetUplinkPerCC_v1540> {
        }
    
        // SIZE(1..1024)
        public static class RRC_featureSetsUplink_v1540 extends AsnSequenceOf<RRC_FeatureSetUplink_v1540> {
        }
    
        // SIZE(1..1024)
        public static class RRC_featureSetsDownlink_v1540 extends AsnSequenceOf<RRC_FeatureSetDownlink_v1540> {
        }
    }

    // SIZE(1..1024)
    public static class RRC_featureSetsUplinkPerCC extends AsnSequenceOf<RRC_FeatureSetUplinkPerCC> {
    }

    // SIZE(1..1024)
    public static class RRC_featureSetsDownlink extends AsnSequenceOf<RRC_FeatureSetDownlink> {
    }

    // SIZE(1..1024)
    public static class RRC_featureSetsDownlinkPerCC extends AsnSequenceOf<RRC_FeatureSetDownlinkPerCC> {
    }

    // SIZE(1..1024)
    public static class RRC_featureSetsUplink extends AsnSequenceOf<RRC_FeatureSetUplink> {
    }
}

