/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PCI_RangeIndex;

public class RRC_PCI_RangeIndexList extends RRC_SequenceOf<RRC_PCI_RangeIndex> {

    @Override
    public String getAsnName() {
        return "PCI-RangeIndexList";
    }

    @Override
    public String getXmlTagName() {
        return "PCI-RangeIndexList";
    }

    @Override
    public Class<RRC_PCI_RangeIndex> getItemType() {
        return RRC_PCI_RangeIndex.class;
    }

}
