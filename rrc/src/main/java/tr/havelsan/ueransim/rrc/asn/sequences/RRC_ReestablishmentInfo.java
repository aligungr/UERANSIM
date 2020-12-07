/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_ShortMAC_I;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ReestabNCellInfoList;

public class RRC_ReestablishmentInfo extends AsnSequence {
    public RRC_PhysCellId sourcePhysCellId; // mandatory
    public RRC_ShortMAC_I targetCellShortMAC_I; // mandatory
    public RRC_ReestabNCellInfoList additionalReestabInfoList; // optional
}

