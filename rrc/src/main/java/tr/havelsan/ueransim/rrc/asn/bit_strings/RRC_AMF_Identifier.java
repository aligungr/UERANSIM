/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.bit_strings;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.utils.bits.BitString;

// SIZE(24)
public class RRC_AMF_Identifier extends AsnBitString {
    public RRC_AMF_Identifier() {
    }
    
    public RRC_AMF_Identifier(BitString value) {
        super(value);
    }
}

