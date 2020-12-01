/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_InterFreqNeighCellInfo;

public class RRC_InterFreqNeighCellList extends RRC_SequenceOf<RRC_InterFreqNeighCellInfo> {

    @Override
    public String getAsnName() {
        return "InterFreqNeighCellList";
    }

    @Override
    public String getXmlTagName() {
        return "InterFreqNeighCellList";
    }

    @Override
    public Class<RRC_InterFreqNeighCellInfo> getItemType() {
        return RRC_InterFreqNeighCellInfo.class;
    }

}
