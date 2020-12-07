/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_UL_CCCH1_MessageType;

public class RRC_UL_CCCH1_Message extends AsnSequence {
    public RRC_UL_CCCH1_MessageType message; // mandatory
}

