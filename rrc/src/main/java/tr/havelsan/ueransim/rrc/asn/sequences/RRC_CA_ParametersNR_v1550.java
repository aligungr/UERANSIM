/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_CA_ParametersNR_v1550 extends AsnSequence {
    public RRC_dummy_8 dummy; // optional

    public static class RRC_dummy_8 extends AsnEnumerated {
        public static final RRC_dummy_8 SUPPORTED = new RRC_dummy_8(0);
    
        private RRC_dummy_8(long value) {
            super(value);
        }
    }
}

