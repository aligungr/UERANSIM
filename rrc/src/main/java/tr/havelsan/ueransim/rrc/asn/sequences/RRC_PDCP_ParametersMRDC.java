/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PDCP_ParametersMRDC extends AsnSequence {
    public RRC_pdcp_DuplicationSplitSRB pdcp_DuplicationSplitSRB; // optional
    public RRC_pdcp_DuplicationSplitDRB pdcp_DuplicationSplitDRB; // optional

    public static class RRC_pdcp_DuplicationSplitDRB extends AsnEnumerated {
        public static final RRC_pdcp_DuplicationSplitDRB SUPPORTED = new RRC_pdcp_DuplicationSplitDRB(0);
    
        private RRC_pdcp_DuplicationSplitDRB(long value) {
            super(value);
        }
    }

    public static class RRC_pdcp_DuplicationSplitSRB extends AsnEnumerated {
        public static final RRC_pdcp_DuplicationSplitSRB SUPPORTED = new RRC_pdcp_DuplicationSplitSRB(0);
    
        private RRC_pdcp_DuplicationSplitSRB(long value) {
            super(value);
        }
    }
}

