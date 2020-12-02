/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FeatureSetUplinkPerCC;

public class RRC_FeatureSets__featureSetsUplinkPerCC extends RRC_SequenceOf<RRC_FeatureSetUplinkPerCC> {

    @Override
    public Class<RRC_FeatureSetUplinkPerCC> getItemType() {
        return RRC_FeatureSetUplinkPerCC.class;
    }

}
