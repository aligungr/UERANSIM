/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;

public class RRC_FeatureSetCombination extends RRC_SequenceOf<RRC_FeatureSetsPerBand> {

    @Override
    public String getAsnName() {
        return "FeatureSetCombination";
    }

    @Override
    public String getXmlTagName() {
        return "FeatureSetCombination";
    }

    @Override
    public Class<RRC_FeatureSetsPerBand> getItemType() {
        return RRC_FeatureSetsPerBand.class;
    }

}
