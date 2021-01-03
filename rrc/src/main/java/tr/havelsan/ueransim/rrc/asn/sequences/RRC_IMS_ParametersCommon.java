/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_IMS_ParametersCommon extends AsnSequence {
    public RRC_voiceOverEUTRA_5GC voiceOverEUTRA_5GC; // optional
    public RRC_ext1_11 ext1; // optional

    public static class RRC_ext1_11 extends AsnSequence {
        public RRC_voiceOverSCG_BearerEUTRA_5GC voiceOverSCG_BearerEUTRA_5GC; // optional
    
        public static class RRC_voiceOverSCG_BearerEUTRA_5GC extends AsnEnumerated {
            public static final RRC_voiceOverSCG_BearerEUTRA_5GC SUPPORTED = new RRC_voiceOverSCG_BearerEUTRA_5GC(0);
        
            private RRC_voiceOverSCG_BearerEUTRA_5GC(long value) {
                super(value);
            }
        }
    }

    public static class RRC_voiceOverEUTRA_5GC extends AsnEnumerated {
        public static final RRC_voiceOverEUTRA_5GC SUPPORTED = new RRC_voiceOverEUTRA_5GC(0);
    
        private RRC_voiceOverEUTRA_5GC(long value) {
            super(value);
        }
    }
}

