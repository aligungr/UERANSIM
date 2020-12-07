/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(1..65536)
public class RRC_BandCombinationIndex extends AsnInteger {
    public RRC_BandCombinationIndex() {
    }
    
    public RRC_BandCombinationIndex(long value) {
        super(value);
    }
}

