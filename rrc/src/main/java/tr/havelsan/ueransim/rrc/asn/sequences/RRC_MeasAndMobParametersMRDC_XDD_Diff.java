/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MeasAndMobParametersMRDC_XDD_Diff extends AsnSequence {
    public RRC_sftd_MeasPSCell sftd_MeasPSCell; // optional
    public RRC_sftd_MeasNR_Cell sftd_MeasNR_Cell; // optional

    public static class RRC_sftd_MeasNR_Cell extends AsnEnumerated {
        public static final RRC_sftd_MeasNR_Cell SUPPORTED = new RRC_sftd_MeasNR_Cell(0);
    
        private RRC_sftd_MeasNR_Cell(long value) {
            super(value);
        }
    }

    public static class RRC_sftd_MeasPSCell extends AsnEnumerated {
        public static final RRC_sftd_MeasPSCell SUPPORTED = new RRC_sftd_MeasPSCell(0);
    
        private RRC_sftd_MeasPSCell(long value) {
            super(value);
        }
    }
}

