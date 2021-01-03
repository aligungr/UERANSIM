/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.bit_strings;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.utils.bits.BitString;

// SIZE(48)
public class RRC_NG_5G_S_TMSI extends AsnBitString {
    public RRC_NG_5G_S_TMSI() {
    }
    
    public RRC_NG_5G_S_TMSI(BitString value) {
        super(value);
    }
}

