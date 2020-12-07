/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..63)
public class RRC_SSB_Index extends AsnInteger {
    public RRC_SSB_Index() {
    }
    
    public RRC_SSB_Index(long value) {
        super(value);
    }
}

