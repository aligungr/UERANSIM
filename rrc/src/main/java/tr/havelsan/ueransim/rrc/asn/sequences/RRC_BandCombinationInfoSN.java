/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BandCombinationIndex;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetEntryIndex;

public class RRC_BandCombinationInfoSN extends RRC_Sequence {

    public RRC_BandCombinationIndex bandCombinationIndex;
    public RRC_FeatureSetEntryIndex requestedFeatureSets;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "bandCombinationIndex","requestedFeatureSets" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "bandCombinationIndex","requestedFeatureSets" };
    }

    @Override
    public String getAsnName() {
        return "BandCombinationInfoSN";
    }

    @Override
    public String getXmlTagName() {
        return "BandCombinationInfoSN";
    }

}
