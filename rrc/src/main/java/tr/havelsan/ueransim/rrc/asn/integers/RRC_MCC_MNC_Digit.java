/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..9)
public class RRC_MCC_MNC_Digit extends AsnInteger {
    public RRC_MCC_MNC_Digit() {
    }
    
    public RRC_MCC_MNC_Digit(long value) {
        super(value);
    }
}

