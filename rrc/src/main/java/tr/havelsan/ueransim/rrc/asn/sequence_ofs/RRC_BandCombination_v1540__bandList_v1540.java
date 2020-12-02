/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BandParameters_v1540;

public class RRC_BandCombination_v1540__bandList_v1540 extends RRC_SequenceOf<RRC_BandParameters_v1540> {

    @Override
    public Class<RRC_BandParameters_v1540> getItemType() {
        return RRC_BandParameters_v1540.class;
    }

}