/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(1..1024)
public class RRC_FreqBandIndicatorNR extends AsnInteger {
    public RRC_FreqBandIndicatorNR() {
    }
    
    public RRC_FreqBandIndicatorNR(long value) {
        super(value);
    }
}

