/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BandCombination;

public class RRC_BandCombinationList extends RRC_SequenceOf<RRC_BandCombination> {

    @Override
    public String getAsnName() {
        return "BandCombinationList";
    }

    @Override
    public String getXmlTagName() {
        return "BandCombinationList";
    }

    @Override
    public Class<RRC_BandCombination> getItemType() {
        return RRC_BandCombination.class;
    }

}
