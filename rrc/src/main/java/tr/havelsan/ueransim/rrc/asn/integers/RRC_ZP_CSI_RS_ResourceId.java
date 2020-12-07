/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..31)
public class RRC_ZP_CSI_RS_ResourceId extends AsnInteger {
    public RRC_ZP_CSI_RS_ResourceId() {
    }
    
    public RRC_ZP_CSI_RS_ResourceId(long value) {
        super(value);
    }
}

