/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_FeatureSet;
import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;

public class RRC_FeatureSetsPerBand extends RRC_SequenceOf<RRC_FeatureSet> {

    @Override
    public String getAsnName() {
        return "FeatureSetsPerBand";
    }

    @Override
    public String getXmlTagName() {
        return "FeatureSetsPerBand";
    }

    @Override
    public Class<RRC_FeatureSet> getItemType() {
        return RRC_FeatureSet.class;
    }

}
