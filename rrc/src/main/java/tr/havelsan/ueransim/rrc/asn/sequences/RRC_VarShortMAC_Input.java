/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_CellIdentity;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RNTI_Value;

public class RRC_VarShortMAC_Input extends AsnSequence {
    public RRC_PhysCellId sourcePhysCellId; // mandatory
    public RRC_CellIdentity targetCellIdentity; // mandatory
    public RRC_RNTI_Value source_c_RNTI; // mandatory
}

