/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DummyD;

public class RRC_FeatureSetDownlink__dummy6 extends RRC_SequenceOf<RRC_DummyD> {

    @Override
    public Class<RRC_DummyD> getItemType() {
        return RRC_DummyD.class;
    }

}
