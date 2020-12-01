/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_PCI_Range extends RRC_Sequence {

    public RRC_PhysCellId start;
    public RRC_Integer range;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "start","range" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "start","range" };
    }

    @Override
    public String getAsnName() {
        return "PCI-Range";
    }

    @Override
    public String getXmlTagName() {
        return "PCI-Range";
    }

}
