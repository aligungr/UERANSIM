/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..65535)
public class RRC_SK_Counter extends AsnInteger {
    public RRC_SK_Counter() {
    }
    
    public RRC_SK_Counter(long value) {
        super(value);
    }
}

