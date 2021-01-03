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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BandCombinationIndex;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetEntryIndex;

public class RRC_BandCombinationInfo extends AsnSequence {
    public RRC_BandCombinationIndex bandCombinationIndex; // mandatory
    public RRC_allowedFeatureSetsList allowedFeatureSetsList; // mandatory, SIZE(1..128)

    // SIZE(1..128)
    public static class RRC_allowedFeatureSetsList extends AsnSequenceOf<RRC_FeatureSetEntryIndex> {
    }
}

