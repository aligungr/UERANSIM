/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MeasAndMobParametersMRDC_Common extends AsnSequence {
    public RRC_independentGapConfig_1 independentGapConfig; // optional

    public static class RRC_independentGapConfig_1 extends AsnEnumerated {
        public static final RRC_independentGapConfig_1 SUPPORTED = new RRC_independentGapConfig_1(0);
    
        private RRC_independentGapConfig_1(long value) {
            super(value);
        }
    }
}

