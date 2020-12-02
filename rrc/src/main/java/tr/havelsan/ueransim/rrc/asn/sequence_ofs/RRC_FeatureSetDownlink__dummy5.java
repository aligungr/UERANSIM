/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DummyC;

public class RRC_FeatureSetDownlink__dummy5 extends RRC_SequenceOf<RRC_DummyC> {

    @Override
    public Class<RRC_DummyC> getItemType() {
        return RRC_DummyC.class;
    }

}
