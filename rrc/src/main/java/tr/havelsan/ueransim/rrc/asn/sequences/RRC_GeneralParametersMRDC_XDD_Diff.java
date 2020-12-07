/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_GeneralParametersMRDC_XDD_Diff extends AsnSequence {
    public RRC_splitSRB_WithOneUL_Path splitSRB_WithOneUL_Path; // optional
    public RRC_splitDRB_withUL_Both_MCG_SCG splitDRB_withUL_Both_MCG_SCG; // optional
    public RRC_srb3 srb3; // optional
    public RRC_v2x_EUTRA v2x_EUTRA; // optional

    public static class RRC_srb3 extends AsnEnumerated {
        public static final RRC_srb3 SUPPORTED = new RRC_srb3(0);
    
        private RRC_srb3(long value) {
            super(value);
        }
    }

    public static class RRC_splitDRB_withUL_Both_MCG_SCG extends AsnEnumerated {
        public static final RRC_splitDRB_withUL_Both_MCG_SCG SUPPORTED = new RRC_splitDRB_withUL_Both_MCG_SCG(0);
    
        private RRC_splitDRB_withUL_Both_MCG_SCG(long value) {
            super(value);
        }
    }

    public static class RRC_splitSRB_WithOneUL_Path extends AsnEnumerated {
        public static final RRC_splitSRB_WithOneUL_Path SUPPORTED = new RRC_splitSRB_WithOneUL_Path(0);
    
        private RRC_splitSRB_WithOneUL_Path(long value) {
            super(value);
        }
    }

    public static class RRC_v2x_EUTRA extends AsnEnumerated {
        public static final RRC_v2x_EUTRA SUPPORTED = new RRC_v2x_EUTRA(0);
    
        private RRC_v2x_EUTRA(long value) {
            super(value);
        }
    }
}

