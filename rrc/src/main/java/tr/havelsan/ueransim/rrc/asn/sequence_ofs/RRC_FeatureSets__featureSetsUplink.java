/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FeatureSetUplink;

public class RRC_FeatureSets__featureSetsUplink extends RRC_SequenceOf<RRC_FeatureSetUplink> {

    @Override
    public Class<RRC_FeatureSetUplink> getItemType() {
        return RRC_FeatureSetUplink.class;
    }

}
