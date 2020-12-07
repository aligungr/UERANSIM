/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..39)
public class RRC_SearchSpaceId extends AsnInteger {
    public RRC_SearchSpaceId() {
    }
    
    public RRC_SearchSpaceId(long value) {
        super(value);
    }
}

