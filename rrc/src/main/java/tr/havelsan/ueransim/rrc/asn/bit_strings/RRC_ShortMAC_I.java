/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.bit_strings;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.utils.bits.BitString;

// SIZE(16)
public class RRC_ShortMAC_I extends AsnBitString {
    public RRC_ShortMAC_I() {
    }
    
    public RRC_ShortMAC_I(BitString value) {
        super(value);
    }
}

