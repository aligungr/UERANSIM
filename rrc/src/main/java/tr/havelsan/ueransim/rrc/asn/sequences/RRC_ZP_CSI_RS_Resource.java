/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_ResourcePeriodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ZP_CSI_RS_ResourceId;

public class RRC_ZP_CSI_RS_Resource extends AsnSequence {
    public RRC_ZP_CSI_RS_ResourceId zp_CSI_RS_ResourceId; // mandatory
    public RRC_CSI_RS_ResourceMapping resourceMapping; // mandatory
    public RRC_CSI_ResourcePeriodicityAndOffset periodicityAndOffset; // optional
}

