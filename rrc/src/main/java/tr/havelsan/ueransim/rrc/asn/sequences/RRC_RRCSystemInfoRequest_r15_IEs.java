/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_RRCSystemInfoRequest_r15_IEs extends AsnSequence {
    public AsnBitString requested_SI_List; // mandatory, SIZE(32)
    public AsnBitString spare; // mandatory, SIZE(12)
}

