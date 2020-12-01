/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_IntraFreqNeighCellInfo;

public class RRC_IntraFreqNeighCellList extends RRC_SequenceOf<RRC_IntraFreqNeighCellInfo> {

    @Override
    public String getAsnName() {
        return "IntraFreqNeighCellList";
    }

    @Override
    public String getXmlTagName() {
        return "IntraFreqNeighCellList";
    }

    @Override
    public Class<RRC_IntraFreqNeighCellInfo> getItemType() {
        return RRC_IntraFreqNeighCellInfo.class;
    }

}
