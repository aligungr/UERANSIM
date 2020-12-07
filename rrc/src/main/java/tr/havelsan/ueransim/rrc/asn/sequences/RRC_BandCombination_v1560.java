/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_BandCombination_v1560 extends AsnSequence {
    public RRC_ne_DC_BC ne_DC_BC; // optional
    public RRC_CA_ParametersNRDC ca_ParametersNRDC; // optional
    public RRC_CA_ParametersEUTRA_v1560 ca_ParametersEUTRA_v1560; // optional
    public RRC_CA_ParametersNR_v1560 ca_ParametersNR_v1560; // optional

    public static class RRC_ne_DC_BC extends AsnEnumerated {
        public static final RRC_ne_DC_BC SUPPORTED = new RRC_ne_DC_BC(0);
    
        private RRC_ne_DC_BC(long value) {
            super(value);
        }
    }
}

