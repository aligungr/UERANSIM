/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetUplinkPerCC_Id;

public class RRC_FeatureSetUplink__featureSetListPerUplinkCC extends RRC_SequenceOf<RRC_FeatureSetUplinkPerCC_Id> {

    @Override
    public Class<RRC_FeatureSetUplinkPerCC_Id> getItemType() {
        return RRC_FeatureSetUplinkPerCC_Id.class;
    }

}
