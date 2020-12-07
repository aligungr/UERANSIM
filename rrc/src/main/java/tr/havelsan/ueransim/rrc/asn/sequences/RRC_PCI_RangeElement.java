/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PCI_RangeIndex;

public class RRC_PCI_RangeElement extends AsnSequence {
    public RRC_PCI_RangeIndex pci_RangeIndex; // mandatory
    public RRC_PCI_Range pci_Range; // mandatory
}

