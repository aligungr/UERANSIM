/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;

public class RRC_UE_NR_Capability__featureSetCombinations extends RRC_SequenceOf<RRC_FeatureSetCombination> {

    @Override
    public Class<RRC_FeatureSetCombination> getItemType() {
        return RRC_FeatureSetCombination.class;
    }

}
