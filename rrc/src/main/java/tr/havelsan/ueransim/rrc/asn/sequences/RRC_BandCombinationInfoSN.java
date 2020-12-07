/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BandCombinationIndex;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetEntryIndex;

public class RRC_BandCombinationInfoSN extends AsnSequence {
    public RRC_BandCombinationIndex bandCombinationIndex; // mandatory
    public RRC_FeatureSetEntryIndex requestedFeatureSets; // mandatory
}

