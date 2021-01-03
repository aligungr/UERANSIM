/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(1..31)
public class RRC_SCellIndex extends AsnInteger {
    public RRC_SCellIndex() {
    }
    
    public RRC_SCellIndex(long value) {
        super(value);
    }
}

