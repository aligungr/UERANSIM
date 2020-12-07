/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCResumeRequest1;

public class RRC_UL_CCCH1_MessageType extends AsnChoice {
    public RRC_c1_2 c1;
    public RRC_messageClassExtension_5 messageClassExtension;

    public static class RRC_c1_2 extends AsnChoice {
        public RRC_RRCResumeRequest1 rrcResumeRequest1;
        public AsnNull spare3;
        public AsnNull spare2;
        public AsnNull spare1;
    }

    public static class RRC_messageClassExtension_5 extends AsnSequence {
    }
}

