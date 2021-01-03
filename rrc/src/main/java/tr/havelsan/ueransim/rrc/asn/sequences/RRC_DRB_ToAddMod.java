/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_DRB_Identity;

public class RRC_DRB_ToAddMod extends AsnSequence {
    public RRC_cnAssociation cnAssociation; // optional
    public RRC_DRB_Identity drb_Identity; // mandatory
    public RRC_reestablishPDCP_2 reestablishPDCP; // optional
    public RRC_recoverPDCP recoverPDCP; // optional
    public RRC_PDCP_Config pdcp_Config; // optional

    public static class RRC_recoverPDCP extends AsnEnumerated {
        public static final RRC_recoverPDCP TRUE = new RRC_recoverPDCP(0);
    
        private RRC_recoverPDCP(long value) {
            super(value);
        }
    }

    public static class RRC_cnAssociation extends AsnChoice {
        public AsnInteger eps_BearerIdentity; // VALUE(0..15)
        public RRC_SDAP_Config sdap_Config;
    }

    public static class RRC_reestablishPDCP_2 extends AsnEnumerated {
        public static final RRC_reestablishPDCP_2 TRUE = new RRC_reestablishPDCP_2(0);
    
        private RRC_reestablishPDCP_2(long value) {
            super(value);
        }
    }
}

