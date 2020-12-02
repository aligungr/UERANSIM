/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DummyB;

public class RRC_FeatureSetDownlink__dummy4 extends RRC_SequenceOf<RRC_DummyB> {

    @Override
    public Class<RRC_DummyB> getItemType() {
        return RRC_DummyB.class;
    }

}
