/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..95)
public class RRC_CSI_RS_Index extends AsnInteger {
    public RRC_CSI_RS_Index() {
    }
    
    public RRC_CSI_RS_Index(long value) {
        super(value);
    }
}

