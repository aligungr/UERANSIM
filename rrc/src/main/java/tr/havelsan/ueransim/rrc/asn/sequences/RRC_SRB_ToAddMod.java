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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRB_Identity;

public class RRC_SRB_ToAddMod extends AsnSequence {
    public RRC_SRB_Identity srb_Identity; // mandatory
    public RRC_reestablishPDCP_1 reestablishPDCP; // optional
    public RRC_discardOnPDCP discardOnPDCP; // optional
    public RRC_PDCP_Config pdcp_Config; // optional

    public static class RRC_discardOnPDCP extends AsnEnumerated {
        public static final RRC_discardOnPDCP TRUE = new RRC_discardOnPDCP(0);
    
        private RRC_discardOnPDCP(long value) {
            super(value);
        }
    }

    public static class RRC_reestablishPDCP_1 extends AsnEnumerated {
        public static final RRC_reestablishPDCP_1 TRUE = new RRC_reestablishPDCP_1(0);
    
        private RRC_reestablishPDCP_1(long value) {
            super(value);
        }
    }
}

