/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PCI_Range;

public class RRC_IntraFreqBlackCellList extends RRC_SequenceOf<RRC_PCI_Range> {

    @Override
    public String getAsnName() {
        return "IntraFreqBlackCellList";
    }

    @Override
    public String getXmlTagName() {
        return "IntraFreqBlackCellList";
    }

    @Override
    public Class<RRC_PCI_Range> getItemType() {
        return RRC_PCI_Range.class;
    }

}
