/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..319)
public class RRC_TDD_UL_DL_SlotIndex extends AsnInteger {
    public RRC_TDD_UL_DL_SlotIndex() {
    }
    
    public RRC_TDD_UL_DL_SlotIndex(long value) {
        super(value);
    }
}

