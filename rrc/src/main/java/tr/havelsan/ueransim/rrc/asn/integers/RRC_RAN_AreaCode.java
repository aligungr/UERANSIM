/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..255)
public class RRC_RAN_AreaCode extends AsnInteger {
    public RRC_RAN_AreaCode() {
    }
    
    public RRC_RAN_AreaCode(long value) {
        super(value);
    }
}

