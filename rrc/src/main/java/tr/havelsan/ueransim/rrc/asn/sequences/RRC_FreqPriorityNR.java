/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CellReselectionSubPriority;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CellReselectionPriority;

public class RRC_FreqPriorityNR extends AsnSequence {
    public RRC_ARFCN_ValueNR carrierFreq; // mandatory
    public RRC_CellReselectionPriority cellReselectionPriority; // mandatory
    public RRC_CellReselectionSubPriority cellReselectionSubPriority; // optional
}

