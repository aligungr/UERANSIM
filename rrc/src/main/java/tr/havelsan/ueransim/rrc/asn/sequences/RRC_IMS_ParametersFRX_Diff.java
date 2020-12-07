/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_IMS_ParametersFRX_Diff extends AsnSequence {
    public RRC_voiceOverNR voiceOverNR; // optional

    public static class RRC_voiceOverNR extends AsnEnumerated {
        public static final RRC_voiceOverNR SUPPORTED = new RRC_voiceOverNR(0);
    
        private RRC_voiceOverNR(long value) {
            super(value);
        }
    }
}

