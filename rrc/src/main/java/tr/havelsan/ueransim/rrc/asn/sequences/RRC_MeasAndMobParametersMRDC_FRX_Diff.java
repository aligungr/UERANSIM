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

public class RRC_MeasAndMobParametersMRDC_FRX_Diff extends AsnSequence {
    public RRC_simultaneousRxDataSSB_DiffNumerology_1 simultaneousRxDataSSB_DiffNumerology; // optional

    public static class RRC_simultaneousRxDataSSB_DiffNumerology_1 extends AsnEnumerated {
        public static final RRC_simultaneousRxDataSSB_DiffNumerology_1 SUPPORTED = new RRC_simultaneousRxDataSSB_DiffNumerology_1(0);
    
        private RRC_simultaneousRxDataSSB_DiffNumerology_1(long value) {
            super(value);
        }
    }
}

