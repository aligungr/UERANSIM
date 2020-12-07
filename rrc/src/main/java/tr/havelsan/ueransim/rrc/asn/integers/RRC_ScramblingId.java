/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..1023)
public class RRC_ScramblingId extends AsnInteger {
    public RRC_ScramblingId() {
    }
    
    public RRC_ScramblingId(long value) {
        super(value);
    }
}

