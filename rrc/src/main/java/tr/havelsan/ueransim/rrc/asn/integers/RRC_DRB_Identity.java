/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(1..32)
public class RRC_DRB_Identity extends AsnInteger {
    public RRC_DRB_Identity() {
    }
    
    public RRC_DRB_Identity(long value) {
        super(value);
    }
}

