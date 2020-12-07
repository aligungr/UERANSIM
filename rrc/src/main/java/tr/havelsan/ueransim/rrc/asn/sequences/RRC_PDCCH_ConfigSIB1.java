/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ControlResourceSetZero;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SearchSpaceZero;

public class RRC_PDCCH_ConfigSIB1 extends AsnSequence {
    public RRC_ControlResourceSetZero controlResourceSetZero; // mandatory
    public RRC_SearchSpaceZero searchSpaceZero; // mandatory
}

