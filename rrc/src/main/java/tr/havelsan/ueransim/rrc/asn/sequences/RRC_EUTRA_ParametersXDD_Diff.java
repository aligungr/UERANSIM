/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_EUTRA_ParametersXDD_Diff extends AsnSequence {
    public RRC_rsrqMeasWidebandEUTRA rsrqMeasWidebandEUTRA; // optional

    public static class RRC_rsrqMeasWidebandEUTRA extends AsnEnumerated {
        public static final RRC_rsrqMeasWidebandEUTRA SUPPORTED = new RRC_rsrqMeasWidebandEUTRA(0);
    
        private RRC_rsrqMeasWidebandEUTRA(long value) {
            super(value);
        }
    }
}

