/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FeatureSetDownlinkPerCC;

public class RRC_FeatureSets__featureSetsDownlinkPerCC extends RRC_SequenceOf<RRC_FeatureSetDownlinkPerCC> {

    @Override
    public Class<RRC_FeatureSetDownlinkPerCC> getItemType() {
        return RRC_FeatureSetDownlinkPerCC.class;
    }

}
