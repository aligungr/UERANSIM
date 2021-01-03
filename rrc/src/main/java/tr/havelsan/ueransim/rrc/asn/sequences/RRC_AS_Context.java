/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_RAN_NotificationAreaInfo;

public class RRC_AS_Context extends AsnSequence {
    public RRC_ReestablishmentInfo reestablishmentInfo; // optional
    public RRC_ConfigRestrictInfoSCG configRestrictInfo; // optional
    public RRC_ext1_45 ext1; // optional
    public RRC_ext2_8 ext2; // optional
    public RRC_ext3_3 ext3; // optional

    public static class RRC_ext3_3 extends AsnSequence {
        public RRC_BandCombinationInfoSN selectedBandCombinationSN; // optional
    }

    public static class RRC_ext2_8 extends AsnSequence {
        public AsnOctetString ueAssistanceInformation; // optional, SIZE(0..MAX)
    }

    public static class RRC_ext1_45 extends AsnSequence {
        public RRC_RAN_NotificationAreaInfo ran_NotificationAreaInfo; // optional
    }
}

