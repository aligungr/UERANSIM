/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..63)
public class RRC_NZP_CSI_RS_ResourceSetId extends AsnInteger {
    public RRC_NZP_CSI_RS_ResourceSetId() {
    }
    
    public RRC_NZP_CSI_RS_ResourceSetId(long value) {
        super(value);
    }
}

