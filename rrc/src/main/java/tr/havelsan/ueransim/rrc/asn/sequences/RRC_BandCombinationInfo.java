/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BandCombinationIndex;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BandCombinationInfo__allowedFeatureSetsList;

public class RRC_BandCombinationInfo extends RRC_Sequence {

    public RRC_BandCombinationIndex bandCombinationIndex;
    public RRC_BandCombinationInfo__allowedFeatureSetsList allowedFeatureSetsList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "bandCombinationIndex","allowedFeatureSetsList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "bandCombinationIndex","allowedFeatureSetsList" };
    }

    @Override
    public String getAsnName() {
        return "BandCombinationInfo";
    }

    @Override
    public String getXmlTagName() {
        return "BandCombinationInfo";
    }

}
