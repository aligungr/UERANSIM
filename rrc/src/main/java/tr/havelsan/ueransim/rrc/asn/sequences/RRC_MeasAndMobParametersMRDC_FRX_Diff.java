/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MeasAndMobParametersMRDC_FRX_Diff extends AsnSequence {
    public RRC_simultaneousRxDataSSB_DiffNumerology_1 simultaneousRxDataSSB_DiffNumerology; // optional

    public static class RRC_simultaneousRxDataSSB_DiffNumerology_1 extends AsnEnumerated {
        public static final RRC_simultaneousRxDataSSB_DiffNumerology_1 SUPPORTED = new RRC_simultaneousRxDataSSB_DiffNumerology_1(0);
    
        private RRC_simultaneousRxDataSSB_DiffNumerology_1(long value) {
            super(value);
        }
    }
}

