/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PCI_RangeIndex;

public class RRC_PCI_RangeElement extends RRC_Sequence {

    public RRC_PCI_RangeIndex pci_RangeIndex;
    public RRC_PCI_Range pci_Range;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pci-RangeIndex","pci-Range" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pci_RangeIndex","pci_Range" };
    }

    @Override
    public String getAsnName() {
        return "PCI-RangeElement";
    }

    @Override
    public String getXmlTagName() {
        return "PCI-RangeElement";
    }

}
