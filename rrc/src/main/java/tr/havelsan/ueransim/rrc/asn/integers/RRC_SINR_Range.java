/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..127)
public class RRC_SINR_Range extends AsnInteger {
    public RRC_SINR_Range() {
    }
    
    public RRC_SINR_Range(long value) {
        super(value);
    }
}

