/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SSB_MTC2__pci_List;

public class RRC_SSB_MTC2 extends RRC_Sequence {

    public RRC_SSB_MTC2__pci_List pci_List;
    public RRC_Integer periodicity;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pci-List","periodicity" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pci_List","periodicity" };
    }

    @Override
    public String getAsnName() {
        return "SSB-MTC2";
    }

    @Override
    public String getXmlTagName() {
        return "SSB-MTC2";
    }

}
