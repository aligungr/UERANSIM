/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_PCI_List extends RRC_SequenceOf<RRC_PhysCellId> {

    @Override
    public String getAsnName() {
        return "PCI-List";
    }

    @Override
    public String getXmlTagName() {
        return "PCI-List";
    }

    @Override
    public Class<RRC_PhysCellId> getItemType() {
        return RRC_PhysCellId.class;
    }

}
