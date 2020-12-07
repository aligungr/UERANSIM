/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TCI_StateId;

public class RRC_TCI_State extends AsnSequence {
    public RRC_TCI_StateId tci_StateId; // mandatory
    public RRC_QCL_Info qcl_Type1; // mandatory
    public RRC_QCL_Info qcl_Type2; // optional
}

