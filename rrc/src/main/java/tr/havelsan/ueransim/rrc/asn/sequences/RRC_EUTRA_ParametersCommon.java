/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_EUTRA_ParametersCommon extends AsnSequence {
    public RRC_mfbi_EUTRA mfbi_EUTRA; // optional
    public AsnBitString modifiedMPR_BehaviorEUTRA; // optional, SIZE(32)
    public RRC_multiNS_Pmax_EUTRA multiNS_Pmax_EUTRA; // optional
    public RRC_rs_SINR_MeasEUTRA rs_SINR_MeasEUTRA; // optional
    public RRC_ext1_14 ext1; // optional

    public static class RRC_ext1_14 extends AsnSequence {
        public RRC_ne_DC ne_DC; // optional
    
        public static class RRC_ne_DC extends AsnEnumerated {
            public static final RRC_ne_DC SUPPORTED = new RRC_ne_DC(0);
        
            private RRC_ne_DC(long value) {
                super(value);
            }
        }
    }

    public static class RRC_multiNS_Pmax_EUTRA extends AsnEnumerated {
        public static final RRC_multiNS_Pmax_EUTRA SUPPORTED = new RRC_multiNS_Pmax_EUTRA(0);
    
        private RRC_multiNS_Pmax_EUTRA(long value) {
            super(value);
        }
    }

    public static class RRC_rs_SINR_MeasEUTRA extends AsnEnumerated {
        public static final RRC_rs_SINR_MeasEUTRA SUPPORTED = new RRC_rs_SINR_MeasEUTRA(0);
    
        private RRC_rs_SINR_MeasEUTRA(long value) {
            super(value);
        }
    }

    public static class RRC_mfbi_EUTRA extends AsnEnumerated {
        public static final RRC_mfbi_EUTRA SUPPORTED = new RRC_mfbi_EUTRA(0);
    
        private RRC_mfbi_EUTRA(long value) {
            super(value);
        }
    }
}

